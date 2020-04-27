package design.prototype;

/**
 * 1.抽象原型类：规定了具体原型对象必须实现的接口。 这里就是 Cloneable 接口
 *2.具体原型类：实现抽象原型类的 clone() 方法，它是可被复制的对象。
 */
public class Realizetype  implements  Cloneable{
     private int age ;
     private String name;

    public Realizetype() {

    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return (Realizetype)super.clone();
    }

    public Realizetype(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
