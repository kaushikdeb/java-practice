import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;

record Employee(String name, String department, String gender){
}

class EmployeeGrouping{

public static void main(String args[]){
  System.out.println("Hi!");

  List<Employee> eList = new ArrayList<>();
  eList.add(new Employee("Alice", "HR", "Female"));
  eList.add(new Employee("Bob", "Engineering", "Male"));
  eList.add(new Employee("Charlie", "HR", "Male"));
  eList.add(new Employee("David", "Engineering", "Male"));
  eList.add(new Employee("Eve", "Marketing", "Female"));
  eList.add(new Employee("Frank", "Marketing", "Male"));
  eList.add(new Employee("Grace", "Engineering", "Female"));

  System.out.println("Full list of Employees: ");
  System.out.println(eList);

  System.out.println("Showing only HR:");
  List<Employee> HRList = eList.stream()
       .filter(e->e.department().equals("HR"))
       .collect(Collectors.toList());
  System.out.println(HRList);

  System.out.println("Showing only Engineering:");
  List<Employee> enggList = eList.stream()
       .filter(e->e.department().equals("Engineering"))
       .collect(Collectors.toList());
  System.out.println(enggList);

  System.out.println("Showing only Female Engineers:");
  List<Employee> enggFemaleList = eList.stream()
       .filter(e->e.department().equals("Engineering") && e.gender().equals("Female"))
       .collect(Collectors.toList());
  System.out.println(enggFemaleList);

  System.out.println("Creating map to group employees by department:");
  Map<String, List<Employee>> eMap = eList.stream()
       .collect(Collectors.groupingBy(Employee::department));
  System.out.println(eMap);

  System.out.println("Creating map to group employees by department then by gender:");
  Map<String, Map<String, List<Employee>>> eMap2 = eList.stream()
       .collect(Collectors.groupingBy(Employee::department,
                Collectors.groupingBy(Employee::gender)));
  System.out.println(eMap2);

//  eMap.forEach((dept, map1)->{System.out.println("Employee Map: " + map1)});

}

}