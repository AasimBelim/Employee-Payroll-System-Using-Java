import java.util.Scanner;

// Custom exception for handling invalid hours worked
class InvalidHoursException extends Exception {
    public InvalidHoursException() {
        super("Total Number of hours worked less than TOS.");
    }
}

// Class to hold common variables and methods
class Keywords {
    double company_rate = 400;
    double house_rent_allowance = 120000;
    double company_rate_allowance = 100000;
    double fed_tax = 7.1;
    double state_tax = 1.8;
    double tax;
    double total_deductions;
    String emp;
    String Manager_name;
    Scanner sc = new Scanner(System.in);
}

// Class representing an Employee
class Employee extends Keywords {
    double noofhours;
    double provident_fund;
    double profession_tax;
    double Insurance_premium;
    double pay;
    double gross_salary;

    // Method to accept employee details
    public void accept() {
        System.out.println("|Enter the Manager's name:");
        Manager_name = sc.nextLine();
        System.out.println("|Enter the employee's name:");
        emp = sc.nextLine();
        System.out.println("Enter the total hours worked:");
        noofhours = sc.nextDouble();
        System.out.println("Enter your provident fund amount:");
        provident_fund = sc.nextDouble();
        System.out.println("Enter your profession tax amount:");
        profession_tax = sc.nextDouble();
    }

    // Method to validate hours worked
    public void validateHours() throws InvalidHoursException {
        if (noofhours < 100) {
            throw new InvalidHoursException();
        }
    }

    // Method to calculate gross salary
    public void calculate() {
        pay = noofhours * company_rate;
        tax = (fed_tax + state_tax) * 1000;
        total_deductions = provident_fund + profession_tax + Insurance_premium + tax;
        gross_salary = pay + house_rent_allowance + company_rate_allowance - total_deductions;
    }

    // Method to calculate additional benefits
    public void calculateBenefits() {
        // Example: Calculate medical allowance based on employee category
        double medical_allowance = 0;
        // Example logic, replace with actual business rules
        if (emp.contains("Manager")) {
            medical_allowance = 15000;
        } else if (emp.contains("Staff")) {
            medical_allowance = 10000;
        }
        // Add more calculations for other benefits as needed
        gross_salary += medical_allowance;
    }

    // Method to calculate performance bonus
    public void calculatePerformanceBonus(double bonusPercentage) {
        double performance_bonus = pay * (bonusPercentage / 100);
        gross_salary += performance_bonus;
    }

    // Method to calculate annual increment
    public void calculateAnnualIncrement(double incrementPercentage) {
        double increment_amount = gross_salary * (incrementPercentage / 100);
        gross_salary += increment_amount;
    }

    // Method to generate pay slip
    public void generatePaySlip() {
        System.out.println("Pay Slip for Employee: " + emp);
        System.out.println("Gross Salary: " + gross_salary);
        System.out.println("Total Deductions: " + total_deductions);
        // Include other details such as allowances, benefits, net salary, etc.
    }

    // Method to update employee information
    public void updateEmployeeInfo(String managerName, double hoursWorked, double providentFund, double professionTax) {
        this.Manager_name = managerName;
        this.noofhours = hoursWorked;
        this.provident_fund = providentFund;
        this.profession_tax = professionTax;
    }
}

// Test class to run the payroll system
public class PayrollSystem {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int noofemp;
        System.out.println("|Enter the number of employees:");
        noofemp = s.nextInt();
        s.nextLine(); // Consume newline character
        
        Employee[] employees = new Employee[noofemp];
        
        for (int i = 0; i < noofemp; i++) {
            System.out.println("\nEmployee " + (i + 1) + ":");
            employees[i] = new Employee();
            employees[i].accept();
            
            try {
                employees[i].validateHours();
            } catch (InvalidHoursException e) {
                System.out.println(e.getMessage());
                // Handle the exception (e.g., ask for hours again or skip employee)
                continue;
            }
            
            employees[i].calculate();
            employees[i].calculateBenefits();
            
            // Optional: Calculate performance bonus or annual increment
            // employees[i].calculatePerformanceBonus(5); // Example: 5% performance bonus
            // employees[i].calculateAnnualIncrement(3); // Example: 3% annual increment
            
            employees[i].generatePaySlip();
        }
    }
}
