package com.sys.mgr.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 *
 * @author zcl
 *
 */
public class NetUtil {

    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);


    /**
     * @param URL
     * @return
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    public static String getInformationFromInternet(String URL) throws IOException{
        URL urlGet = new URL(URL);
        HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        http.setDoOutput(true);
        http.setDoInput(true);
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
        System.setProperty("sun.net.client.defaultReadTimeout", "30000");
        http.connect();
        InputStream is = null;
        ByteOutputStream bos = null;
        try{
            is = http.getInputStream();
            byte[] _b = new byte[1024];
            int l = 0 ;
            bos = new ByteOutputStream();
            while((l= is.read(_b))>0){
                bos.write(_b, 0, l);
                l = 0 ;
            }
            return new String(bos.toByteArray(), "UTF-8");
        }finally{
            if(is != null)
                is.close();
            if(bos != null)
                bos.close();
        }
    }
    /**
     * @param URL
     * @return
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    public static int getHttpResponse(String URL) throws IOException {
        URL urlGet = new URL(URL);
        HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        http.setDoOutput(true);
        http.setDoInput(true);
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
        System.setProperty("sun.net.client.defaultReadTimeout", "30000");
        http.connect();
        return http.getResponseCode();
    }


    public static void main(String[] args) throws IOException {
        System.out.println(getHttpResponse("http://localhost//services/HelloService?wsdl"));
    }


    /**
     *
     * @param URL
     * @return
     * @throws IOException
     */
    public static void storeInformationFromInternet(String URL,String filePath) throws IOException{
        URL urlGet = new URL(URL);
        HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        http.setDoOutput(true);
        http.setDoInput(true);
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
        System.setProperty("sun.net.client.defaultReadTimeout", "30000");
        http.connect();
        File file = new File(filePath);
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        InputStream is = null; OutputStream out = null;
        try{
            is = http.getInputStream();
            byte[] _b = new byte[1024];
            int l = 0 ; out = new FileOutputStream(file);
            while((l= is.read(_b))>0){
                out.write(_b, 0, l);
                l = 0 ;
            }
        }finally{
            if(is != null)
                is.close();
            if(out != null)
                out.close();
        }
    }


    /**
     *
     * @param url
     * @param message
     * @return
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    public static String sendMessageToInternet(String url,String message) throws IOException{

        InputStream in =null;
        OutputStream out = null;
        try{
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            out = http.getOutputStream();
            out.write(message.getBytes("UTF-8"));
            in = http.getInputStream();
            ByteOutputStream bos = new ByteOutputStream();
            byte[] b = new byte[1024];
            int l;
            while((l=in.read(b))>0){
                bos.write(b,0,l);
            }
            bos.close();
            return new String(bos.toByteArray(),"UTF-8");
        }finally{
            if(in != null)
                in.close();
            if(out != null)
                out.close();
        }
    }

    /**
     *
     * @param url
     * @param message
     * @return
     * @throws IOException
     */
    public static String sendMessageToInternetByPost(String url,String message) throws IOException{

        InputStream in =null;
        OutputStream out = null;
        try{
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            out = http.getOutputStream();
            out.write(message.getBytes("UTF-8"));
            in = http.getInputStream();
            ByteOutputStream bos = new ByteOutputStream();
            byte[] b = new byte[1024];
            int l;
            while((l=in.read(b))>0){
                bos.write(b,0,l);
            }
            bos.close();
            return new String(bos.toByteArray(),"UTF-8");
        }finally{
            if(in != null)
                in.close();
            if(out != null)
                out.close();
        }
    }




    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    public static String sendMessageToInternet(String url) throws IOException{

        InputStream in =null;
        //OutputStream out = null;
        try{
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            //out = http.getOutputStream();
            //out.write(message.getBytes("UTF-8"));
            in = http.getInputStream();
            ByteOutputStream bos = new ByteOutputStream();
            byte[] b = new byte[1024];
            int l;
            while((l=in.read(b))>0){
                bos.write(b,0,l);
            }
            bos.close();
            return new String(bos.toByteArray(),"UTF-8");
        }finally{
            if(in != null)
                in.close();
        }
    }




}
