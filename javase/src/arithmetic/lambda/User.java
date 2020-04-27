package arithmetic.lambda;

public class User {
    private int age ;
    private int grade;

    public User() {
    }

    public User(int age, int grade) {
        this.age = age;
        this.grade = grade;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", grade=" + grade +
                '}';
    }

}
