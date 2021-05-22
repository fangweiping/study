package com.hand.report.reptile.common;

/**
 * @author Fang WeiPing
 * @date 2020/6/4 17:55
 */
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/6/4 17:55
 */

@SuppressWarnings("all")
@Slf4j
public class Reptile {

    // 访问地址
    private static String URL = "http://www.ktbdqn.com/";
    // 输出编码
    private static final String ECODING = "utf-8";
    // 获取img标签正则表达式
    private static final String IMGURL_REG = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "(?x)(src|SRC|background|BACKGROUND)=('|\")/?(([\\w-]+/)*([\\w-]+\\." +
            "(jpg|JPG|png|PNG|gif|GIF)))('|\")";
    // img本地保存路径
    private static final String SAVE_PATH = "d:\\wxcode";

    private static Map<String, String> agentMap = new HashMap<>();

    private static List<AgentInfo> agentInfoList;

    private static final String AGENT_URL = "http://piping.mogumiao" +
            ".com/proxy/api/get_ip_bs?appKey=988c5cd25f7d42e090791d1ff644063c&count=10&expiryDate=0&format=1&newLine=2";

    private static final String PRE_URL = "http://m.qunfenxiang.net/";

    static {
        System.out.println("加载代理地址中......");
        loadAgentAddress();
        System.out.println("代理地址加载成功!");
    }


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
        pageDownImage();
    }


    /**
     * 加载代理地址
     */
    public static void loadAgentAddress() {
        try {
            RestTemplate restTemplate = new RestTemplate();
//                ResponseEntity<AgentResponse> entity = restTemplate.getForEntity(AGENT_URL, AgentResponse.class);
                ResponseEntity<String> exchange = restTemplate.exchange(AGENT_URL, HttpMethod.GET, null,
                        String.class);
                String text = exchange.getBody();
                Thread.currentThread().sleep(1000l);
                AgentResponse agentResponse = JSON.parseObject(text, AgentResponse.class);
                List<AgentInfo> msg = agentResponse.msg;
                agentInfoList = new ArrayList<>();
                agentInfoList.addAll(msg);
//      msg.stream().forEach(agentInfo -> agentMap.put(agentInfo.ip,agentInfo.port));
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *随机获取一个代理地址
     * @return
     */
    public static AgentInfo getAgentInfo() {
        Random random = new Random();
        int i = random.nextInt(agentInfoList.size());
        return agentInfoList.get(i);
    }

    /**
     * 获取单个img
     */
    public static void getSingleImg() throws InterruptedException {
        for (int i = 10000; i < 10500; i++) {
            String website = getURL(null, i);
            Document doc = null;
            try {
                //
                trustAllHttpsCertificates();
                HostnameVerifier hv = new HostnameVerifier() {
                    public boolean verify(String urlHostName, SSLSession session) {
                        System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
                        return true;
                    }
                };
                HttpsURLConnection.setDefaultHostnameVerifier(hv);
                doc = Jsoup.connect(website)
                        // .data("query", "Java")
                        .userAgent("Mozilla")
                        // .cookie("auth", "token")
                        // .timeout(3000)
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //群名
            Elements head = doc.select("h1");
            if (head.size() == 0) {
                continue;
            }
            String title = head.first().text();
            if (isFilter(title)) {
                continue;
            }
            //二维码//swiper-slide
            Elements div = doc.getElementsByAttributeValue("class", "p1");
            String imgUrl = div.first().child(0).child(0).attr("src");
            System.out.println("imgUrl = " + imgUrl);

            downImage(title, imgUrl);
        }
    }


    /**
     * 分页获取img
     */
    public static void pageDownImage() throws IOException, InterruptedException {
        String websiteTemplate = "http://m.qunfenxiang.net/list/194-0-%s.html";
        String website = null;
        Document doc =null;
        for (int i = 1; i <=295 ; i++) {
            website = String.format(websiteTemplate,i);
            log.info("爬取地址:{}",website);

            try {
                trustAllHttpsCertificates();
                HostnameVerifier hv = new HostnameVerifier() {
                    public boolean verify(String urlHostName, SSLSession session) {
                        System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
                        return true;
                    }
                };
                HttpsURLConnection.setDefaultHostnameVerifier(hv);

                AgentInfo agentInfo = getAgentInfo();

//                System.setProperty("https.proxySet", "true");
//                System.getProperties().put("https.proxyHost", agentInfo.getIp());
//                System.getProperties().put("https.proxyPort", agentInfo.port);

                doc = Jsoup.connect(website)
                        .userAgent(getUserAgent())
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .header("Accept-Encoding", "gzip, deflate, sdch")
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, " +
                                "like Gecko) Chrome/55.0.2883.87 Safari/537.36")
//                        .header("Referer",String.format(website,i-1))
//                        .proxy(agentInfo.ip,Integer.valueOf(agentInfo.port))
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Elements imgs = doc.select("a[href*=group]").select("a[href*=html]");
            String url = null;
            for (int j = 0; j < imgs.size(); j++) {
                Thread.currentThread().sleep(500L);
                Element img = imgs.get(j);
                String src = img.attr("href");
                url = PRE_URL+src;
                System.out.println("二维码详情地址:" + url);//详情地址中有二维码地址

                //重新创建连接
                AgentInfo agentInfo = getAgentInfo();
                doc = Jsoup.connect(url)
                        .userAgent(getUserAgent())
//                        .proxy(agentInfo.ip,Integer.valueOf(agentInfo.port))
                        .get();

                //群名
                Elements head = doc.select("h1");
                if (head.size() == 0) {
                    continue;
                }
                String title = head.first().text();
                if (isFilter(title)) {
                    log.info("过滤无效微信群:{}",title);
                    continue;
                }
                //二维码//swiper-slide
                Elements div = doc.getElementsByAttributeValue("class", "p1");
                String imgUrl = div.first().child(0).child(0).attr("src");
                System.out.println("imgUrl = " + imgUrl);
                downImage(title, imgUrl);

                if (i % 10 == 0) {
                    //重新获取ip
                    getAgentInfo();
                }
            }
              log.info("第{}页采集成功!",i);
        }
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
    private static void downImage(String title, String imgUrl) throws InterruptedException {
        Thread.currentThread().sleep(500L);
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
                FileUtils.copyToFile(in, new File(SAVE_PATH + "\\" + title + getLastFileName(imgUrl)));
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
        javax.net.ssl.TrustManager tm = new miTM();
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
