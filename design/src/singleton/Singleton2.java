package singleton;

/**
 * 懒汉式加载,  线程不安全,通过双重锁机制来保证数据安全
 */
public class Singleton2 {

    //volatile  关键字 保证线程每次获取修改后的最新数据
    private static volatile Singleton2 singleton ;

    //私有构造器
    private Singleton2() {

    }

    //对外提获取实例对象的静态方法  双重判定来保证数据安全
    public static Singleton2 getInstance() {
        if (singleton==null) {
            synchronized (Singleton2.class) {
                if (singleton==null) {
                    return new Singleton2();
                }
            }
        }
        return singleton;
    }
}
