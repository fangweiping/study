package factory;

/**
 * 多个工厂方法   针对每个实现类提供一个创建对象的非实例方法
 */
public class MultipleFactory {

    public Sender produceMail() {
        return new MailSender();
    }

    public Sender produceMsg() {
        return  new MsgSender();
    }

}
