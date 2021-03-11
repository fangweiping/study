package df;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class MainApplication {

    public static void main(String[] args) throws AWTException {
        /**
         * delay方法
         * delay方法用作我们动作的延迟，比如你模拟按两个键，如果没有延迟，程序会飞快的帮你按下且你不管按多少键，每个键中间的延迟都很低且相等，这就是不是有点可疑，就像是机器人的操作，所以为了避免这种可以我们可以使用delay方法加上延迟，但键与键之间的延迟也不想等，所以我们可以用random（随机函数）生成一个随机延迟。
         */
        Robot robot = new Robot();
        //robot.delay(5000);

        while (true) {
            robot.keyPress(KeyEvent.VK_J);
            robot.delay(3000);
            robot.keyPress(KeyEvent.VK_3);
        }
    }
}
