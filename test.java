import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


class keywords {
    double noofemp;
    double company_rate = 400;
    double noofhours;
    double house_rent_allowance = 120000;
    double company_rate_allowance = 100000;
    double provident_fund;
    double profession_tax;
    double Insurance_premium;
    double pay;
    double gross_salary;
    double fed_tax = 7.1;
    double state_tax = 1.8;
    double tax;
    double total_deductions;
    String emp;
    String Manager_name;
    Scanner sc = new Scanner(System.in);
}

class InvalidHoursException extends Exception {
    public InvalidHoursException() {
        System.out.println("Total Number of hours worked less than TOS.");
    }
}

class Employee extends keywords {
    // Existing fields and methods...

    public void writeToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println("Payment Details");
            writer.println("\nName of Employee \t" + emp);
            writer.println("\nHours worked \t" + noofhours);
            writer.println("\nProvident Fund Amount \t" + provident_fund);
            writer.println("\nProfession Tax Amount \t" + profession_tax);
            writer.println("\nTax Amount \t" + tax);
            writer.println("\nTotal Deductions \t" + total_deductions);
            writer.println("\nHouse and rent Allowance\t" + house_rent_allowance);
            writer.println("\nRemaining Allowances' \t" + company_rate_allowance);
            writer.println("\n--------------------------------------------\t");
            writer.println("\nGross Salary is \t" + gross_salary);
            writer.println(); // for a blank line between entries
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public void createFileIfNotExists(String fileName) {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }



    public void accept() {
        System.out.println("|Enter the Manager's name:");
        Manager_name = sc.nextLine();
        System.out.println("|Enter the employee's names :");
        emp = sc.nextLine();
        System.out.println("Enter the total hour's worked");
        noofhours = sc.nextDouble();
        System.out.println("Enter your provident fund amount:");
        provident_fund = sc.nextDouble();
        System.out.println("Enter your profession tax amount:");
        profession_tax = sc.nextDouble();
    }

    public void hours() {
        try {
            if (noofhours < 100) {
                throw new InvalidHoursException();
            }
        } catch (InvalidHoursException e) {
            System.out.println(e);
        }
    }

    public void calculate() {
        pay = noofhours * company_rate;
        tax = (fed_tax + state_tax) * 1000;
        total_deductions = provident_fund + profession_tax + Insurance_premium + tax;
        gross_salary = pay + house_rent_allowance + company_rate_allowance - total_deductions;
    }

    public void display() {
        System.out.println("Payment Details");
        System.out.println("\nName of Employee \t" + emp);
        System.out.println("\nHours worked \t" + noofhours);
        System.out.println("\nProvident Fund Amount \t" + provident_fund);
        System.out.println("\nProfession Tax Amount \t" + profession_tax);
        System.out.println("\nTax Amount \t" + tax);
        System.out.println("\nTotal Deductions \t" + total_deductions);
        System.out.println("\nHouse and rent Allowance\t" + house_rent_allowance);
        System.out.println("\nRemaining Allowances' \t" + company_rate_allowance);
        System.out.println("\n--------------------------------------------\t");
        System.out.println("\nGross Salary is \t" + gross_salary);
    }
}



class test {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        Employee e = new Employee();
        int noofemp;
        System.out.println("|Enter the no of employees'");
        noofemp = s.nextInt();
        s.nextLine(); // consume newline left by nextInt()

        String fileName = "salary_details.txt"; // specify your file name here

        e.createFileIfNotExists(fileName); // Ensure file exists

        int x;
        for (x = 1; x <= noofemp; x++) {
            e.accept();
            e.calculate();
            e.display();
            e.writeToFile(fileName); // Write to file after displaying
        }

        System.out.println("Salary details have been saved to " + fileName);
    }
}
