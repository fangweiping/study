package com.fwp.study.reptile.jiaqunzhushou;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fwp.study.reptile.utils.HttpsUtils;
import com.fwp.study.reptile.Reptile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@SuppressWarnings("all")
public class MainTest {
    // user-agent浏览器标识
    private static final String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 " +
                    "Safari/537.36 OPR/37.0.2178.32",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57" +
                    ".2",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 " +
                    "Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 " +
                    "Safari/537.36 Edge/13.10586",
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 " +
                    "BIDUBrowser/8.3 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 " +
                    "Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 " +
                    "UBrowser/5.6.12150.8 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 " +
                    "Safari/537.36 SE 2.X MetaSr 1.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 " +
                    "Safari/537.36 TheWorld 7",
            "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};

    public static String parm = "{username:16651687165,password:sjy19960724}"; //账号密码

    public static String loginUrl = "http://app.jiaqun8.cn/portal/user/login"; //登陆地址

    public static String savePath = "D:\\jiaqunzhushou"; //图片保存路径

    public static String token ;

    public static void main(String[] args) throws Exception {
        //1.获取token
         getToken(loginUrl);

        for (int i = 0; i < 10; i++) {
            //2.获取图片   date:是日期   no:是图片序号
            getImage(token,1,i);
        }
    }

    /**
     * 模拟登录,获取token
     *
     * @param urls
     * @return
     */
    public static void getToken(String urls) {
        if (token != null) {
            return;
        }
        try {
            URL url = new URL(urls);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            String parm = "{username:16651687165,password:sjy19960724}";
            //username: "16651687165", password: "sjy19960724"
            HashMap<String, String> map = new HashMap<>();
            map.put("username", "16651687165");
            map.put("password", "sjy19960724");
            String s = JSON.toJSONString(map);
            // 将参数输出到连接
            dataout.writeBytes(s);
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
            System.out.println(connection.getResponseCode());

            //BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder res = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                res.append(line);
            }
            br.close();
            connection.disconnect();
            JSONObject jsonObject = JSON.parseObject(res.toString());
             token = jsonObject.get("data").toString();
            System.out.println(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取图片url
     */
    public static void getImage(String token, int date, int no) {
        try {
            String requestUrl = generateRequestUrl(date, no);
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("x-access-token", token);
            connection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            connection.disconnect();
            JSONObject jsonObject = JSONObject.parseObject(String.valueOf(sb));
            Map data = (Map) jsonObject.get("data");
            String imageUrl = (String) data.get("image");
            System.out.println(imageUrl);
            downImage(savePath,date+"-"+no+".jpg",imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
         log.info("请求失败");
        }
    }

    /**
     * 获取模板
     * @param date
     * @param no
     * @return
     */
    public static String generateRequestUrl(int date, int no) {
        String urlTemplate = "http://app.jiaqun8.cn/portal/group/view/%s/%s?all=false";
        return String.format(urlTemplate, date, no);
    }

    /**
     * 随机模拟浏览器
     *
     * @return
     */
    public static String getUserAgent() {
        Random random = new Random();
        int i = random.nextInt(ua.length);
        return ua[i];
    }

    /**
     * 下载图片
     *
     * @param title
     * @param imgUrl
     */
    public static void downImage(String savePath, String title, String imgUrl) throws InterruptedException {
        Thread.currentThread().sleep(1000L);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(imgUrl);
        httpget.setHeader("User-Agent", getUserAgent());
        httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpget.setHeader("referer", "http://app.jiaqun8.cn/");

        try {
            CloseableHttpClient sslClientDefault = HttpsUtils.createSSLClientDefault();
            HttpResponse resp = sslClientDefault.execute(httpget);
            if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
                log.info("图片下载请求状态码:{}", HttpStatus.SC_OK);
                HttpEntity entity = resp.getEntity();
                InputStream in = entity.getContent();
                FileUtils.copyToFile(in, new File(savePath + "\\" + title));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }


    /**
     * 是否过滤
     *
     * @return
     */
    public static boolean isFilter(String title) {
        List<String> list = Stream.of("淘宝", "京东", "优惠卷", "抢购", "秒杀", "宝妈", "微商", "股票", "期权", "代购", "代理", "金融",
                "育儿", "加群", "拼多多").collect(Collectors.toList());
        for (String key : list) {
            if (title.contains(key)) {
                return true;
            }
        }
        return false;
    }

    public static String getLastFileName(String url) {
        //获取最后一个.的位置
        int lastIndexOf = url.lastIndexOf(".");
        //获取文件的后缀名 .jpg
        return url.substring(lastIndexOf);
    }

    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new Reptile.miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }


    static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }
}
