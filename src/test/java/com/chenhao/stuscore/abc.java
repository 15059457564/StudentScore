package com.chenhao.stuscore;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * @author ch
 * @date 2020/10/19
 **/
public class abc {
    @Test
    public void abc(){
        //获取当前网络时间
        String webUrl="http://www.baidu.com";//百度时间
        long webTime=getNetworkTime(webUrl);
        System.out.println("当前网络时间为："+webTime);
    }
    public static long getNetworkTime(String webUrl) {
        try {
            URL url=new URL(webUrl);
            URLConnection conn=url.openConnection();
            conn.connect();
            long dateL=conn.getDate();
            Date date=new Date(dateL);


            System.out.println("getTime"+date.getTime());
            return date.getTime();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return 0;
    }
    
}
