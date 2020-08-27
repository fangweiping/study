package utils;/**
 * @author Fang WeiPing
 * @date 2020/5/29 11:42
 */

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/5/29 11:42
 */
public class FileUtils {

    /**
     * 获取类路径资源文件
     */
    @Test
    public  void  getClassPathFile() {
        ClassPathResource resource = new ClassPathResource("stockin.xls");
        try {
            InputStream inputStream = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
