package com.casstime.ec.cloud.finance.test;

import com.google.common.collect.Lists;

import java.io.*;
import java.util.Base64;
import java.util.List;

/**
 * @description:
 * @author: biao.xu
 * @date 2020/10/21
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            new HelloClassLoader().findClass("jvm.Hello").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        List<String> strings = read("E:/jeektime/Hello.class");
        Byte[] bytes = strings.toArray(new Byte[strings.size()]);
        return defineClass(name, bytes, 0, bytes.length);
    }

    public List<String> read(String filePath) {
        List<String> strings = Lists.newArrayList();
        InputStream inputStream = null;
        InputStreamReader fileInputStream = null;
        BufferedReader br = null;
        try {
            inputStream = new FileInputStream(filePath);
            fileInputStream = new InputStreamReader(inputStream);
            br = new BufferedReader(fileInputStream);
            String str = null;
            try {
                while ((str = br.readLine()) != null) {
                    strings.add(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return strings;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fileInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
