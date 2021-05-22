package df;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * v1
 */
public class TestMainApplication {
    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        while (true) {
            robot.keyPress(KeyEvent.VK_I);
            robot.delay(1000);
            robot.keyRelease(KeyEvent.VK_I);
            robot.delay(1000);

            ////1.buff
            //releaseBuff(robot);
            //System.out.println("  buff= " );
            //
            ////2.移动
            //right(robot);
            //
            ////3.释放技能
            //release(robot,KeyEvent.VK_F);
            //
            ////4.移动
            //right(robot);
            //
            ////5.释放技能
            //release(robot,KeyEvent.CTRL_MASK);
            //
            ////6.移动
            //right(robot);
            //
            ////7.释放技能
            //release(robot,KeyEvent.VK_H);
        }
    }


    public static int  getDelay() {
      return new Random().nextInt(1000);
    }

    public static int  getDelay(int i) {
        return new Random().nextInt(i);
    }

    /**
     * 释放buff
     * @param robot
     * @throws InterruptedException
     */
    public static void releaseBuff(Robot robot) throws InterruptedException {
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_UP);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_UP);

        robot.delay(100);
        robot.keyPress(KeyEvent.VK_UP);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_UP);

        robot.delay(100);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
    }

    /**
     * 向右移动
     * @param robot
     * @throws InterruptedException
     */
    public static void right(Robot robot) throws InterruptedException {
        robot.delay(getDelay(200));
        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.delay(getDelay(200));
        robot.keyRelease(KeyEvent.VK_RIGHT);
        robot.delay(getDelay(200));
        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.delay(getDelay(200));
        Thread.sleep(5000);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        robot.delay(3000);
    }

    /**
     * 释放技能
     */
    public static void release(Robot robot,int keyCode) {
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_F);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_F);
    }
}
