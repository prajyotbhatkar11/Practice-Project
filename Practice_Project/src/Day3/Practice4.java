package Day3;

import NewPackage.Employee;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


public class Practice4 {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        Employee sonal = new Employee("Sonal Farad", 20);
        Employee tanvi = new Employee("Tanvi Ambre", 24);
        Employee Kailash = new Employee("Kailash Choudhary", 22);
        Employee Prajyot = new Employee("Prajyot Bhatkar", 23);
        Employee Rohit = new Employee("Rohit Mudaliar", 25);
        Employee Sayali = new Employee("Sayali Phanase", 21);

        employees.add(sonal);
        employees.add(tanvi);
        employees.add(Kailash);
        employees.add(Prajyot);
        employees.add(Rohit);
        employees.add(Sayali);
       // Stream<String> firstHalf = Stream.of("Prajyot", "Sonal", "Tanvi");
        //Stream<String> seaconfHalf = Stream.of("Rohit", "Sayali", "Kailash");
        //Stream<String> full = Stream.concat(firstHalf, seaconfHalf);
        //System.out.println(full.sorted().peek(System.out::println).count());

        List<String> sortedEmployess = employees
                .stream()
                .filter(s -> s.getName().startsWith("p"))
                .map(s-> s.getName().toUpperCase())
                .collect(ArrayList::new , ArrayList::add, ArrayList::addAll);
        sortedEmployess.stream().forEach(e -> System.out.println(e.toUpperCase()));
    }
}
