package df;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * v1
 */
public class TestMainApplication {
    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        while (true) {
            robot.keyPress(KeyEvent.VK_J);
            robot.delay(3000);
            robot.keyPress(KeyEvent.VK_3);
        }
    }
}
