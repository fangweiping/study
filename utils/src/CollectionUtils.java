import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CollectionUtils {

    /**
     * 判断字符串是否为null或""
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if(str==null||str.trim().isEmpty())return true;
        return false;
    }

    /**
     * 判断集合是否为null或空集合
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        if(collection==null||collection.isEmpty())return true;
        return false;
    }

    /**
     * 判断map是否为null或空map
     */
    public static boolean isEmpty(Map map) {
        if(map==null||map.isEmpty())return true;
        return false;
    }
}
