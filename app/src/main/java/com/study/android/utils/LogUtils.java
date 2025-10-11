package com.study.android.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class LogUtils {

    //生成文件
    private static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    //生成文件夹
    private static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    public static void writeStrToFile(String type, String content) {
        Log.d("fresco_test", "print log: type = " + type + ", content = " + content);
        try {
            String file = Environment.getExternalStorageDirectory().getPath()  + "/frescolog/mylog.txt";
            makeFilePath(Environment.getExternalStorageDirectory().getPath()  + "/frescolog/", "mylog.txt");
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(file, true);
            // 每次写入时，都换行写
            String strContent = content + "\r\n";
            writer.write(strContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String fileName){
        StringBuilder sb = new StringBuilder("");
        try {
            File file = new File(fileName);
            //打开文件输入流
            FileInputStream inputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = inputStream.read(buffer);
            //读取文件内容
            while (len > 0) {
                sb.append(new String(buffer, 0, len));
                //继续将数据放到buffer中
                len = inputStream.read(buffer);
            }
            //关闭输入流
            inputStream.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return sb.toString();
    }
}
