import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Map;

Class Java8Deatures{
    public static void main(String []args){
        //Created a random interger list with help of stream
        Random random = new Random();
        List<Integer> intList = random.ints().limit(10).collect(Collectors.toList());

        }
}