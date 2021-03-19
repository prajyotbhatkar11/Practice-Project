package MyPackage;

import java.util.function.Function;
import java.util.function.Supplier;

public class Program2 {
    public static void main(String[] args) {
        Function<String ,String> lambadaFunction = s ->{
            StringBuilder returnVal = new StringBuilder();
            for(int i=0 ; i<s.length();i++)
            {
                if(i%2 ==1)
                    returnVal.append(s.charAt(i));
            }
            return returnVal.toString();
        };
        //System.out.println(lambadaFunction.apply("12345567890"));
        System.out.println(everySecondChar(lambadaFunction,"1234567890"));

        Supplier<String> ILoveJava = () ->"I love Java";
        String supplierJava = ILoveJava.get();
        System.out.println(supplierJava);



    }
    public static String everySecondChar(Function<String , String> lambadaFucntion, String s){
        return lambadaFucntion.apply(s);
    }
}
