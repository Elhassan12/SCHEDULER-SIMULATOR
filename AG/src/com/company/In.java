package com.company;

// takes input from the user

import java.util.Scanner;
import java.util.Vector;

public class In {
    Vector<Process> processes ;


     public In()
    {
        processes = new Vector<>();
    }

    public Vector<Process> getProcesses() {
        return processes;
    }

    public void takeInputs(int number)
    {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < number; i++) {

            System.out.println("enter process Name: ");
            String name = scanner.next();
            System.out.println("enter process Burst time: ");
            int time = scanner.nextInt();
            System.out.println("enter process Arrival time: ");
            int arrivalTime = scanner.nextInt();
            System.out.println("enter process Priority: ");
            int priority = scanner.nextInt();
            System.out.println("enter process Quantum: ");
            int quantum = scanner.nextInt();
            Process process = new Process(name,time,priority,quantum,arrivalTime);
            this.processes.add(process);
        }

    }
}
