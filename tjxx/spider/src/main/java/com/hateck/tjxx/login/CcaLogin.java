package com.hateck.tjxx.login;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by apple on 15/5/21.
 */
public class CcaLogin {
    public static void login() {
        try {
            // 下载验证码到本地
            URL url = new URL("http://ts.cca.org.cn/login.do?method=showVerifyPassword");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            connection.setRequestProperty("Cache-Control", "max-age=0");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Host", "ts.cca.org.cn");
            connection.setRequestProperty("Cookie", "JSESSIONID=3CF739C518542B6CD5D85C4760403209.tomcat0");
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36");
            connection.connect();

            File imgCodeFile = new File("/Users/apple/Desktop/cc/"
                    + System.currentTimeMillis() + ".gif");
            BufferedImage image = ImageIO.read(connection.getInputStream());
            ImageIO.write(image, "gif", imgCodeFile);

            String checkCode = GetCheckCode();

            //用户要输入值
            String userName = "120000leader";
            String password = "111111";
            String identifyingCode = checkCode;

            String paramStr = "&username=" + userName + "&userpassword=" + password + "&userverifypassword=" + identifyingCode;
            /*--------------------------以上请求为获取验证码----------------------------*/


            url = new URL("http://ts.cca.org.cn/login.do?method=login");
            connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            connection.setRequestProperty("Accept-Encoding","gzip, deflate");
            connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
            connection.setRequestProperty("Cache-Control","max-age=0");
            connection.setRequestProperty("Connection","keep-alive");
            connection.setRequestProperty("Host","ts.cca.org.cn");
            connection.setRequestProperty("Origin","http://ts.cca.org.cn");
            connection.setRequestProperty("Referer","http://ts.cca.org.cn/login.do?method=login");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(paramStr.length()));
            connection.setRequestProperty("Cookie", "JSESSIONID=3CF739C518542B6CD5D85C4760403209.tomcat0");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36");
            connection.connect();
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
            dos.writeBytes(paramStr);
            dos.flush();
            dos.close();
            int res = connection.getResponseCode();

            /*--------------------------以上请求为登录----------------------------*/
            if (res == 200) {
                url = new URL("http://ts.cca.org.cn/queryRegister.do?method=queryAppeal&curPage=1");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                connection.setRequestProperty("Accept-Encoding","gzip, deflate, sdch");
                connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
                connection.setRequestProperty("Cache-Control","max-age=0");
                connection.setRequestProperty("Connection","keep-alive");
                connection.setRequestProperty("Host","ts.cca.org.cn");

                connection.setRequestProperty("Cookie", "JSESSIONID=3CF739C518542B6CD5D85C4760403209.tomcat0");
                connection.setRequestProperty("User-Agent",
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36");
                connection.connect();

                int code = connection.getResponseCode();

                //System.out.println(code);

                if(code == 200){
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                    String retVal;
                    while ((retVal = in.readLine()) != null) {
                        //System.out.println(retVal);
                    }
                }

                /*--------------------------以上请求为最终查询投诉列表页----------------------------*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String GetCheckCode() {
        Scanner sc = new Scanner(System.in);
        System.out.print("验证码在/Users/apple/Desktop/cc/目录下 ,请查看并输入:");
        String checkCode = sc.next();
        System.out.println("您输入的验证码是:" + checkCode);
        return checkCode;
    }
}
