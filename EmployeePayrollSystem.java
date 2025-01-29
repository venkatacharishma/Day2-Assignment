import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee {
    String name;
    int id;
    double baseSalary;

    Employee(String name, int id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }

    double calculateSalary() {
        return baseSalary;
    }

    void displayDetails() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + calculateSalary());
    }
}

class PermanentEmployee extends Employee {
    double bonus;

    PermanentEmployee(String name, int id, double baseSalary, double bonus) {
        super(name, id, baseSalary);
        this.bonus = bonus;
    }

    @Override
    double calculateSalary() {
        return baseSalary + bonus;
    }
}

class ContractualEmployee extends Employee {
    double hourlyRate;
    int hoursWorked;

    ContractualEmployee(String name, int id, double hourlyRate, int hoursWorked) {
        super(name, id, hourlyRate * hoursWorked);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

class EmployeePayrollSystem {
    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Permanent Employee");
            System.out.println("2. Add Contractual Employee");
            System.out.println("3. Generate Payroll Report");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name1 = scanner.next();
                    System.out.print("Enter ID: ");
                    int id1 = scanner.nextInt();
                    System.out.print("Enter Base Salary: ");
                    double salary1 = scanner.nextDouble();
                    System.out.print("Enter Bonus: ");
                    double bonus = scanner.nextDouble();
                    Employee e1 = new PermanentEmployee(name1, id1, salary1, bonus);
                    employeeList.add(e1);
                    System.out.println("Permanent Employee added successfully!");
                    break;
                
                case 2:
                    System.out.print("Enter Name: ");
                    String name2 = scanner.next();
                    System.out.print("Enter ID: ");
                    int id2 = scanner.nextInt();
                    System.out.print("Enter Hourly Rate: ");
                    double hourlyRate = scanner.nextDouble();
                    System.out.print("Enter Hours Worked: ");
                    int hoursWorked = scanner.nextInt();
                    Employee e2 = new ContractualEmployee(name2, id2, hourlyRate, hoursWorked);
                    employeeList.add(e2);
                    System.out.println("Contractual Employee added successfully!");
                    break;
                
                case 3:
                    System.out.println("\nPayroll Report:");
                    for (Employee emp : employeeList) {
                        emp.displayDetails();
                    }
                    break;
                
                case 4:
                    exit = true;
                    System.out.println("Exiting Payroll System...");
                    break;
                
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}
