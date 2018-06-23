package com.vorlock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

class SearchExt {
    private static final String OUTFILE = "report.txt";
//    private static final String DIRNAME = "C:\\TMP";
//    private static final String DIRNAME = "TMP";
//    private static String strOutFile =  "report" + "_" + DIRNAME + ".txt";
//    private static String OUTFILE = "report" + "_" + DIRNAME + ".txt";

    //    private static final String OUTFILE = "C:\\TMP\\file.txt";
    private static final String DIRNAME = "C:\\Program Files\\Java\\jdk1.8.0_172";

    void searchExtends() {
//        System.out.println("sdfdsfsfd " + OUTFILE);
        checkFile(OUTFILE);
        System.out.println("Current DataTime: " + new Date().toString());
        printExtToFile("Current DataTime: " + new Date().toString() + "\n");

        TreeMap<String, Integer> hmExt = new TreeMap<>();
        int count = 0;
        String strExt;

        ArrayList<String> res = new ArrayList<>();
        try {
            getListOfFilesAndFolders(DIRNAME, res);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Scan a directory: " + DIRNAME);
        System.out.println("~~~");
        System.out.println("Discovered extensions:");
        System.out.println("===");
        printExtToFile("Scan a directory: " + DIRNAME + "\n");
        printExtToFile("~~~" + "\n");
        printExtToFile("Discovered extensions:" + "\n");
        printExtToFile("===" + "\n");
        for (String value : res) {
            strExt = getFileExtension(value);
            if (strExt.length() >= 2 && strExt.length() <= 4) {
                if (hmExt.containsKey(strExt)) {
                    hmExt.put(strExt, hmExt.get(strExt) + 1);
                } else {
                    hmExt.put(strExt, 1);
                    count++;
                }
            }
        }

        for (HashMap.Entry<String, Integer> pair : hmExt.entrySet()) {
            System.out.printf(pair.getKey() + " : " + pair.getValue() + "\n");
            printExtToFile(pair.getKey() + " : " + pair.getValue() + "\n");
        }
        System.out.println("~~~");
        System.out.println("Total unique extensions: " + count);
        System.out.println("~~~");
        printExtToFile("~~~" + "\n");
        printExtToFile("Total unique extensions: " + count + "\n");
        printExtToFile("~~~" + "\n");
    }

    void printExtToFile(String str) {
        try (FileWriter fw = new FileWriter(OUTFILE, true)) {
            fw.write(str);
        } catch (IOException e) {
            System.out.println("An I/O Error Occurred");
        }
    }

    private void checkFile(String name) {
        File file = new File(name);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    private void getListOfFilesAndFolders(String dirPath, ArrayList<String> listOfFilesAndFolders) throws IOException {
        File dir = new File(dirPath);
        File[] list = dir.listFiles();

        for (File f : list != null ? list : new File[0]) {
            if (f.isFile()) {
                listOfFilesAndFolders.add(f.getCanonicalPath());
            } else {
//                res.add(f.getCanonicalPath());
                getListOfFilesAndFolders(f.getCanonicalPath(), listOfFilesAndFolders);
            }
        }
    }

    private static String getFileExtension(String str) {
        if (str.lastIndexOf(".") != -1 && str.lastIndexOf(".") != 0) {
            return str.substring(str.lastIndexOf(".") + 1);
        } else {
            return "No file extension!";
        }
    }

}
