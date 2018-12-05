package com.xxx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class code {

    static List<File> codeFiles = new ArrayList<>();
    static String mergeFile = "./code.txt";
    static String nameFile = "./file.txt";

    static String path = "/home/ubt/workspaces/WorkSpace_CashLai/EasyLoan/app/src/main/";

    public static void main(String[] args) throws Exception {

        if (!new File(path).exists()) {
            System.out.println("path is not exist!");
            return;
        }


        getListFilesFromName();

//        getListFiles(new File(path));
        merge();
    }


    /**
     * 按文件名称来获取源码
     * @throws Exception
     */
    private static void getListFilesFromName() throws Exception {
        FileInputStream isr = new FileInputStream(new File(nameFile));
        BufferedReader reader = new BufferedReader(new InputStreamReader(isr));
        String str = "";
        while ((str = reader.readLine())!=null){
            if (!str.isEmpty())
                getListFiles(new File(path), str);
        }
        reader.close();
        isr.close();
    }


    /**
     * 遍历文件夹获取文件路径
     *
     * @param file
     */
    public static void getListFiles(File file, String fileName) throws Exception {
        File[] listFiles = file.listFiles();
        for (File f : listFiles) {
            //只获取java和xml文件
//            if (f.isFile() && (f.getName().endsWith(".java") || f.getName().endsWith(".xml"))) {
            //按文件名称
            if (f.isFile() && (f.getName().equals(fileName))) {
                codeFiles.add(f.getAbsoluteFile());
            } else if (f.isDirectory()) {
                getListFiles(f, fileName);
            }
        }
    }


    /**
     * 合并
     */
    public static void merge() throws Exception {

        for (int i=0; i<codeFiles.size(); i++) {
            File f = codeFiles.get(i);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mergeFile, true)));
            //插入文件名
            bw.write("\r\n\n" +(i+1)+"、"+ f.getPath() + "\r\n\n");

            //读取要追加的文件
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String str = null;
            while((str = reader.readLine())!=null) {
                bw.write(str + "\n");
            }


            bw.close();

        }

        System.out.println("over~~");
    }

}

