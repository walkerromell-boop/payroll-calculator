package com.pluralsight;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PayrollCalculator {
    public static void main(String[] args) {
        Employee[] employees = new Employee[10];
        int nextEmployee = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which file do you want to read?(ex: employees.csv)");
        String filename = scanner.nextLine();
//        Read employees from the file the user entered
        nextEmployee = readEmployees(employees, filename);
        System.out.println("Enter the name of the New file to create(ex: payroll_report.csv)");
        String newFilename = scanner.nextLine();


//        readEmployees(employees, nextEmployee);
        try {
            // create a FileWriter
            FileWriter fileWriter = new FileWriter(newFilename);
            // create a BufferedWriter
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // write to the file
            bufferedWriter.write("Id| Name| Gross Pay|");
            bufferedWriter.newLine();

//            Loop through employees and write each one
            for (int i = 0; i < nextEmployee; i++) {
                Employee emp = employees[i];

//                write formatted text to the file
                String line = String.format("id: %d, name: %s,grosspay:%.2f  "
                        , emp.getEmployeeId(), emp.getName(), emp.getGrosspay());

                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.newLine();
            // always close the writer
            bufferedWriter.close();
            System.out.println("New file created successfully:" + newFilename);

        } catch (IOException e) {
            System.out.println("ERROR: An unexpected error occurred");
//            e.getStackTrace();
        }


    }

    private static int readEmployees(Employee[] employees, String filename) {
        int nextEmployee = 0; //  define inside method

        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line;

            bufReader.readLine(); // skip header
            while ((line = bufReader.readLine()) != null) {
                String[] parts = line.split(Pattern.quote("|"));

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double hoursWorked = Double.parseDouble(parts[2]);
                double payrate = Double.parseDouble(parts[3]);
                employees[nextEmployee] = new Employee(id, name, hoursWorked, payrate);
                nextEmployee++;
            }

            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < nextEmployee; i++) {
            System.out.println(employees[i]);
        }

        return nextEmployee; //  return the count
    }

}
