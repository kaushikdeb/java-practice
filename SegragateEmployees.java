import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// class Employee{
//     private int eid;
//     private String department;
//     private String gender;

//     Employee(int eid, String dept, String gender){
//         this.eid = eid;
//         this.department = dept;
//         this.gender = gender;
//     }

//     public String getDepartment() {
//         return department;
//     }

//     public String getGender() {
//         return gender;
//     }

//     @Override
//     public String toString() {
//         StringBuilder sb = new StringBuilder();
//         sb.append("Employee ID: ")
//           .append(this.eid)
//           .append(", Department: ")
//           .append(this.department)
//           .append(", Gender: ")
//           .append(this.gender)
//           .append("\n");
//         return sb.toString();
//     }
// }

record Employee(int eid, String department, String gender){}

class SegregateEmployees{
    public static void main(String args[]){
        List<Employee> employees = createEmployees();
        printEmployees(employees);

        Map<String, Integer> deptCountMap = getDepartmentCountMap(employees);
        printMap(deptCountMap);

        Map<String, Map<String, Integer>> deptGenderCountMap = new HashMap<String, Map<String, Integer>>();
        for(Employee emp : employees){
            if(deptGenderCountMap.containsKey(emp.department())){
                Map<String, Integer> genderCountMap = deptGenderCountMap.get(emp.department());
                if(genderCountMap.containsKey(emp.gender())){
                    genderCountMap.put(emp.gender(), genderCountMap.get(emp.gender()) + 1);
                }else{
                    genderCountMap.put(emp.gender(), 1);
                }
            }else{
                Map<String, Integer> genderCountMap = new HashMap<String, Integer>();
                genderCountMap.put(emp.gender(), 1);
                deptGenderCountMap.put(emp.department(), genderCountMap);
            }
        }

        printTwoLayerMap(deptGenderCountMap);
        
    }

    private static void printTwoLayerMap(Map<String, Map<String, Integer>> deptGenderCountMap){
        System.out.println(deptGenderCountMap);
    }

    private static void printMap(Map<String, Integer> deptCountMap){
        System.out.println(deptCountMap);
    }

    private static Map<String, Integer> getDepartmentCountMap(List<Employee> employeeList){
        Map<String, Integer> deptCountMap = new HashMap<String, Integer>();

        for(Employee emp : employeeList){
            if(deptCountMap.containsKey(emp.department())){
                deptCountMap.put(emp.department(), deptCountMap.get(emp.department()) + 1);
            }else{
                deptCountMap.put(emp.department(), 1);
            }
        }

        return deptCountMap;
    }

    private static void printEmployees(List<Employee> employeeList){
        System.out.println(employeeList);
    }

    private static List<Employee> createEmployees(){
        List<Employee> employeeList = new ArrayList<Employee>();

        employeeList.add(new Employee(1, "sales", "male"));
        employeeList.add(new Employee(2, "sales", "male"));
        employeeList.add(new Employee(3, "hr", "female"));
        employeeList.add(new Employee(4, "hr", "female"));
        employeeList.add(new Employee(5, "hr", "male"));
        employeeList.add(new Employee(6, "engineering", "female"));
        employeeList.add(new Employee(7, "engineering", "male"));

        return employeeList;
    }
}