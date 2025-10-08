package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class PayrollCalculator {
    public static void main(String[] args) {
        Employee[] employees = new Employee[10];
        int nextEmployee = 0;

        try {
            // create a FileReader object connected to the File
            FileReader fileReader = new FileReader("employees.csv");
            // create a BufferedReader to manage line stream
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line;
            // read until there is no more data

            bufReader.readLine(); //This skips the first line if it's a header
            while ((line = bufReader.readLine()) != null ) {
                String[] parts = line.split(Pattern.quote("|"));

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double hoursWorked = Double.parseDouble(parts[2]);
                double payrate = Double.parseDouble(parts[3]);
                employees[nextEmployee]=new Employee(id,name,hoursWorked,payrate);
                nextEmployee++;

            }

            // close the stream and release the resources
            bufReader.close();
        } catch (IOException e) {
            // display stack trace if there was an error
            e.printStackTrace();
        }
        for (int i=0;i<nextEmployee;i++){
            System.out.println(employees[i]);
        }
        

    }
}
