package com.fwp.demo;/**
 * @author Fang WeiPing
 * @date 2020/6/4 17:55
 */

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/6/4 17:55 
 */

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
    private static final String SAVE_PATH = "d:\\";

    public static void main(String[] args) throws IOException {

        for (int i = 10000; i <10050 ; i++) {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(URL);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity);
            Document doc = Jsoup.parse(html);


            Elements links = doc.select("a[href]"); //带有href属性的a元素
            Elements pngs = doc.select("img[src$=.png]"); //扩展名为.png的图片
            Element masthead = doc.select("div.masthead").first(); //class等于masthead的div标签
            Elements resultLinks = doc.select("h3.r > a"); //在h3元素之后的a元素


            //群名
            Elements head = doc.select("h1");
            String title = head.first().text();



        }



    }

    public String getURL(String url, int no) {
        URL = "http://m.qunfenxiang.net/group/" + no + ".html";
        return URL;
    }
}
