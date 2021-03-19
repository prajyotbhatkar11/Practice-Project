package MyPackage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;


public class Practice2 {
    public static void main(String[] args){
        List <Employee> employees = new ArrayList<>();
        Employee sonal = new Employee("Sonal", 20);
        Employee tanvi = new Employee("Tanvi", 24);
        Employee Kailash = new Employee("Kailash", 22);
        Employee Prajyot = new Employee("Prajyot", 23);
        Employee Rohit = new Employee("Rohit", 25);
        Employee Sayali = new Employee("Sayali", 21);

        employees.add(sonal);
        employees.add(tanvi);
        employees.add(Kailash);
        employees.add(Prajyot);
        employees.add(Rohit);
        employees.add(Sayali);

        Collections.sort(employees, ( employee1,  employee2) -> employee1.getName().compareTo(employee2.getName()));
        employees.forEach(employee -> System.out.println(employee.getName()));

        String a = stuff((s1, s2) -> s1.toUpperCase() + s2.toUpperCase(), employees.get(0).getName(), employees.get(1).getName() );
        System.out.println(a);

        employees.forEach(employee -> System.out.println(employee.getName()+" " +employee.getAge()));


    }
    public final static String stuff(doStuff d, String s1, String s2 ){
        return d.upperConcat(s1 , s2);
    }
}



