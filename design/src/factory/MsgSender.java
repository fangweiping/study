package factory;

/**
 * 短信发送类
 */
public class MsgSender implements Sender {
    @Override
    public void send() {
        System.out.println("发送短信");
    }
}
