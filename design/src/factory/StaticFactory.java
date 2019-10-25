package src.factory;

/**
 * 静态工厂   就是多个工厂方法变为静态方法
 */
public class StaticFactory {

    public static Sender produceMail() {
        return new MailSender();
    }

    public static Sender produceMsg() {
        return  new MsgSender();
    }
}
