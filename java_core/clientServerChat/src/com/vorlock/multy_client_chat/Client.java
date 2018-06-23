package com.vorlock.multy_client_chat;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    private static Thread thread;

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 5555;
        try (Socket socket = new Socket(ip, port);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Enter your name!");
            String name = reader.readLine();
            out.writeUTF(name);
            out.flush();
            thread = new Thread(() -> {//читает и выводит в косноль все сообщения
                while (true) {
                    try {
                        System.out.println(in.readUTF());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                }
            });
            thread.start();

            while (thread.isAlive()) {
                String message = reader.readLine();
                out.writeUTF(message);
                out.flush();
            }
        } catch (SocketException ex) {
            System.out.println("Server doesn't exist");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (thread != null)
                if (!thread.isInterrupted())
                    thread.interrupt();
        }
    }
}
