package singleton;
/**
 * 饿汉式   基于类的初始化来保证线程安全, 调用一个类的静态方法会导致该类初始化
 * 缺点  类初始化时创建对象,之后如果没有使用对象,会导致资源的浪费
 */
public class Singleton1 {
    private static Singleton1 singleton = new Singleton1();

    /**
     * 私有构造器
     */
    private Singleton1() {

    }

    /**
     * 对外提供一个获取实例对象的静态方法
     */

    public static Singleton1 getInstance(){
        return singleton;
    }
}
