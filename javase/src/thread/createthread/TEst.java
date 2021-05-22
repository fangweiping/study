package thread.createthread;

public class TEst {
    public static void main(String[] args) {

        long l = System.currentTimeMillis();
        Thread thread = new Thread();
        thread.start();
        System.out.println("thread = " + (System.currentTimeMillis() - l));
    }
}
