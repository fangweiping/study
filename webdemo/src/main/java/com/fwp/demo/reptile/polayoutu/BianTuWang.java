package com.fwp.demo.reptile.polayoutu;

import com.fwp.demo.reptile.utils.HttpsUtils;
import com.fwp.demo.reptile.Reptile;
import com.fwp.demo.reptile.utils.AgentInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 泼辣有图片获取
 *
 * 根据一下路径进行分类搜索,  by_tags/后面是搜索关键字
 * https://www.polayoutu.com/collections/get_entries_by_tags/%E5%A4%A9%E7%A9%BA
 *
 */
@Slf4j
@SuppressWarnings("all")
public class BianTuWang {

    // 访问地址
    private static String URL = "pic.netbian.com";
    // 输出编码
    private static final String ECODING = "utf-8";
    // 获取img标签正则表达式
    private static final String IMGURL_REG = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "(?x)(src|SRC|background|BACKGROUND)=('|\")/?(([\\w-]+/)*([\\w-]+\\." +
            "(jpg|JPG|png|PNG|gif|GIF)))('|\")";
    // img本地保存路径
    private static final String SAVE_PATH = "d:\\image\\biantuwang";

    private static Map<String, String> agentMap = new HashMap<>();

    private static List<AgentInfo> agentInfoList;

    private static final String AGENT_URL = "http://piping.mogumiao" +
            ".com/proxy/api/get_ip_bs?appKey=988c5cd25f7d42e090791d1ff644063c&count=10&expiryDate=0&format=1&newLine=2";

    private static final String PRE_URL = "http://m.qunfenxiang.net/";




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

    public static void main(String[] args) throws Exception {
        String websiteTemplate = "https://pic.netbian.com/4kdongman";
                pageDownImage(websiteTemplate,SAVE_PATH);
    }



    /**
     * 天空系列
     */
    @Test
    public static void pageDownImage(  String websiteTemplate,String savePath) throws IOException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Response> exchange = restTemplate.exchange(websiteTemplate, HttpMethod.GET, null, Response.class);
        List<ResEntity> data = exchange.getBody().getData();
        data.stream().forEach(entity->{
            String full_res = entity.getFull_res();
            LocalDateTime created = entity.getCreated();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String format = formatter.format(created);
            String title = format+".jpg";
            try {
                downImage(savePath,title,full_res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }



    /**
     * 随机模拟浏览器
     * @return
     */
    public static String getUserAgent() {
        Random random = new Random();
        int i = random.nextInt(ua.length);
        return ua[i];
    }

    /**
     * 下载图片
     * @param title
     * @param imgUrl
     */
    private static void downImage(String savePath ,String title, String imgUrl) throws InterruptedException {
        Thread.currentThread().sleep(1000L);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(imgUrl);
        httpget
                .setHeader(
                        "User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0" +
                                ".1180" +
                                ".79 Safari/537.1");
        httpget
                .setHeader("Accept",
                        "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

        try {
            CloseableHttpClient sslClientDefault = HttpsUtils.createSSLClientDefault();
            HttpResponse resp = sslClientDefault.execute(httpget);
            if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
                log.info("图片下载请求状态码:{}",HttpStatus.SC_OK);
                HttpEntity entity = resp.getEntity();
                InputStream in = entity.getContent();
                FileUtils.copyToFile(in, new File(savePath + "\\" + title ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }


    public static String getURL(String url, int no) {
        URL = "http://m.qunfenxiang.net/group/" + no + ".html";
        return URL;
    }

    /**
     * 是否过滤
     * @return
     */
    public static boolean isFilter(String title) {
        List<String> list = Stream.of("淘宝", "京东", "优惠卷", "抢购", "秒杀", "宝妈", "微商", "股票", "期权", "代购", "代理", "金融",
                "育儿", "加群","拼多多").collect(Collectors.toList());

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
