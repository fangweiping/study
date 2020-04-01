package factory;


/**
 *  简单工厂  根据传递的类名创建对象
 *
 * 发送工厂类
 */
public class SimpleSendFactory {

    public  Sender  produce(String type) {
        if ("mail".equals(type)) {
            return  new MailSender();
        } else if ("msg".equals(type)) {
            return new MsgSender();
        } else {
            System.out.println("请输入正确的类型!");
            return  null;
        }
    }

}
