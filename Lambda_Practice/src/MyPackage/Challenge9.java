package MyPackage;

import java.util.Arrays;
import java.util.List;

public class Challenge9 {
    public static void main(String[] args) {
        List<String> topNames2015 = Arrays.asList(
                "Amelia",
                "Olivia",
                "emily",
                "Isla",
                "Ava",
                "oliver",
                "Jack",
                "Charlie",
                "harry",
                "Jacob"
        );
       /* topNames2015
                .stream()
                .map(names -> names.substring(0,1).toUpperCase()+names.substring(1))
                .sorted().
                forEach(System.out::println);
        long filterNames =topNames2015
                .stream()
                .map(names -> names.substring(0,1).toUpperCase()+names.substring(1))
                .filter(name -> name.startsWith("A"))
                .count();
        System.out.println(filterNames);*/

        topNames2015
                .stream()
                .map(names -> names.substring(0,1).toUpperCase()+names.substring(1))
                .peek(name -> System.out.println(name))
                .sorted().count();

    }
}
