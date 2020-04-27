package design.factory;

/**
 * 短信发送工厂类
 */
public class MsgFactory implements Provider {
    @Override
    public Sender produce() {
        return  new MsgSender();
    }
}
