package utils;/**
 * @author Fang WeiPing
 * @date 2020/5/29 11:48
 */


import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/5/29 11:48
 */
public class DateUtils {

    /**
     * 获取mongo中的iso日期
     */
    @Test
    public void getISODate() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT"));
        System.out.println("now = " + now);
    }
}
