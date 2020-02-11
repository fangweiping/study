import java.io.*;

public class SerializableUtils {

    /**
     * 把对象转变成二进制
     *
     * @param obj
     *            待转换的对象
     * @return 返回二进制数组
     */
    public static byte[] writeInto(Object obj) {
        ByteArrayOutputStream bos;
        bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            // 读取对象并转换成二进制数据
            oos.writeObject(obj);
            return bos.toByteArray();
        } catch (IOException e) {
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 把二进制数组的数据转回对象
     *
     * @param b
     * @return
     */
    public static Object restore(byte[] b) {
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            // 读取二进制数据并转换成对象
            bis = new ByteArrayInputStream(b);
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
