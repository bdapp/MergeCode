package com.cashlai.creditfactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Info 合并整个Android项目源代码（java+xml）到一个文件，用于申请软著权
 * @Auth Bello
 * @Time 18-10-31 下午5:44
 * @Ver
 */
public class MergeCode {
    static List<File> codeFiles = new ArrayList<>();
    static String mergeFile = "./code.txt";

    public static void main(String[] args) throws Exception {

        String path = "/路径/app/src/main/";
        if (!new File(path).exists()) {
            System.out.println("path is not exist!");
            return;
        }

        getListFiles(new File(path));

        merge();
    }


    /**
     * 遍历文件夹获取文件路径
     *
     * @param file
     */
    public static void getListFiles(File file) throws Exception {
        File[] listFiles = file.listFiles();
        for (File f : listFiles) {
            //只获取java和xml文件
            if (f.isFile() && (f.getName().endsWith(".java") || f.getName().endsWith(".xml"))) {
                codeFiles.add(f.getAbsoluteFile());
            } else if (f.isDirectory()) {
                getListFiles(f);
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
