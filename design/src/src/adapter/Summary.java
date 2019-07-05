package src.adapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * 适配器模式  作用： 就是将一个接口适配到另一个接口，此处的接口是广义的接口（类或接口）
 *
 * target（目标接口）:所要转换成的所期待的接口
 * Adaptee（源角色）：需要适配的类
 * Adapter（适配器）：将源角色适配成目标接口，一般持有源接口的引用（或者继承源接口），且实现目标接口。
 *
 * 在IO中的体现有：
 * InputStreamReader将InputStream抽象类适配到Reader抽象类;
 *
 * InputSteamReader 适配器
 * InputStream      目标接口
 * Reader           源角色
 *
 */
public class Summary {
    public static void main(String[] args) throws FileNotFoundException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("d://a.txt"));
    }
}
