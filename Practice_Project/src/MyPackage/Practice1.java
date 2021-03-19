package MyPackage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Practice1 {
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




    }

}


class Employee{
    private String name;
    private int age;

    Employee(String name, int age){
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

interface doStuff{
     String upperConcat(String s1, String s2);
}
