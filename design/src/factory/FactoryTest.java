package src.factory;

public class FactoryTest {
    public static void main(String[] args) {
        SimpleSendFactory factory = new SimpleSendFactory();
        //简单工厂模式测试
        Sender mailSender = factory.produce("mail");
        Sender msgSender = factory.produce("msg");
        System.out.println("简单工厂模式测试");
        mailSender.send();
        msgSender.send();
        System.out.println("====================");

        //多个工厂方法模式测试
        MultipleFactory multipleFactory = new MultipleFactory();
        mailSender = multipleFactory.produceMail();
        msgSender = multipleFactory.produceMsg();
        System.out.println("多个工厂方法模式测试");
        mailSender.send();
        msgSender.send();
        System.out.println("====================");


        //静态工厂方法模式测试
        mailSender = StaticFactory.produceMail();
        msgSender = StaticFactory.produceMsg();
        System.out.println("静态工厂方法模式测试");
        mailSender.send();
        msgSender.send();
        System.out.println("====================");


        //抽象工厂模式测试
        MailFactory mailFactory = new MailFactory();
        MsgFactory msgFactory = new MsgFactory();
        mailSender = mailFactory.produce();
        msgSender = msgFactory.produce();
        System.out.println("抽象工厂模式测试");
        mailSender.send();
        msgSender.send();


    }
}
