package com.vorlock.multy_client_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server {
    private static final int port = 5555;
    private static SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yy HH:mm:ss");//форматирование даты и времени дя сообщений
    private static volatile List<ConnectedClient> list = Collections.synchronizedList(new LinkedList<>());//синхронизированная коллекция

    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(port)) {

            while (true) {
                Socket socket = ss.accept();//ожидание клиентов
                System.out.println("Got a new Client!");
                ConnectedClient client = new ConnectedClient(socket);//выделение отдельного потока для клиента
                list.add(client);
                client.start();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeAll();
        }
    }

    private static void closeAll() {//метод для закрытия всех подключенных клиентов
        try {
            synchronized (list) {
                for (ConnectedClient cl : list) {
                    cl.close();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static class ConnectedClient extends Thread {
        private DataInputStream in;
        private DataOutputStream out;
        private Socket socket;
        private String name;

        ConnectedClient(Socket socket) {
            this.socket = socket;
            try {
                this.in = new DataInputStream(socket.getInputStream());
                this.out = new DataOutputStream(socket.getOutputStream());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override
        public void run() {
            try {
                this.name = in.readUTF();
                System.out.println("User with name: " + name + " joined us");//системная инфа
                System.out.println("Count of users == " + list.size());//системная инфа
                synchronized (list) {
                    for (ConnectedClient cl : list)
                        cl.out.writeUTF("[" + currentDate.format(System.currentTimeMillis()) + "]" + name + " joined us");//сообщение всем, что пользователь подключен
                }
                while (true) {
                    String message = in.readUTF();//ожидание сообщений от пользователя
                    synchronized (list) {//передача всем пользователям сообщения
                        for (ConnectedClient cl : list)
                            cl.out.writeUTF("[" + currentDate.format(System.currentTimeMillis()) + "]" + name + ": " + message);
                    }
                }
            } catch (SocketException ex) {//если пользователь отключился
                synchronized (list) {
                    System.out.println("started searching");
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).socket == socket) {
                            try {
                                list.get(i).socket.close();//закрытие сокета пользователя
                                list.remove(i);//удаление его из списка
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    synchronized (list) {
                        for (ConnectedClient cl : list)
                            try {//уведомление всех, что пользователь отключен
                                String message = "[" + currentDate.format(System.currentTimeMillis()) + "]" + name + " left us :(";
                                cl.out.writeUTF(message);
                                cl.out.flush();
                                System.out.println(message);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                    System.out.println("Count of users == " + list.size());//системная инфа
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {//закрытие всего
                close();
            }
        }

        private void close() {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
                if (socket != null)
                    socket.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
