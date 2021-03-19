package MyPackage;

public class Program1 {
    public static void main(String[] args) {
        Runnable runnable = () ->{
            String mySplit = "Let's Split this into an Array";
            String[] parts = mySplit.split(" ");
            for(String p : parts)
            {
                System.out.println(p);
            }
        };
        Thread thread = new Thread(runnable);
    }
}
