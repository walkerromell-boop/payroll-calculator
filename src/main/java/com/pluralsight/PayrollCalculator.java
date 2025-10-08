package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PayrollCalculator {
    public static void main(String[] args) {
        Employee[] employees = new Employee[10];
        int count = 0;

        try {
            // create a FileReader object connected to the File
            FileReader fileReader = new FileReader("employees.csv");
            // create a BufferedReader to manage input stream
            BufferedReader bufReader = new BufferedReader(fileReader);
            String input;
            // read until there is no more data

            bufReader.readLine(); //This skips the first line if it's a header
            while ((input = bufReader.readLine()) != null && count < employees.length) {
                String[] parts = input.split("\\|");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double hoursWorked = Double.parseDouble(parts[2]);
                double payrate = Double.parseDouble(parts[3]);
                employees[count]=new Employee(id,name,hoursWorked,payrate);
                count++;
            }
            // close the stream and release the resources
            bufReader.close();
        } catch (IOException e) {
            // display stack trace if there was an error
            e.printStackTrace();
        }
        for (int i=0;i<count;i++){
            System.out.println(employees[i]);
        }
        

    }
}
