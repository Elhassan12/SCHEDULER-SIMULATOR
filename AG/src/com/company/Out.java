package com.company;
import STLs.Pair;

import java.util.Scanner;
import java.util.Vector;

public class Out {
    Vector<Pair> processesOrderExecution;
    Vector<Process> processes ;
    Vector<Integer> quantumHistory ;

    public Out(Vector<Pair> processesOrderExecution, Vector<Process> processes, Vector<Integer> quantumHistory) {
        this.processesOrderExecution = processesOrderExecution;
        this.processes = processes;
        this.quantumHistory = quantumHistory;
    }

    void OrderExecution()
    {
        int number = processesOrderExecution.size();

        for (int i = 0; i < number; i++) {
            System.out.print(processesOrderExecution.get(i).processName+ "  ");

        }
        System.out.println("\n");
        System.out.print("0");
        for (int i = 0; i < number; i++) {
            System.out.print(" "+processesOrderExecution.get(i).time);
        }
        System.out.println("\n");
    }
    int waitingTime()
    {
        int number = processes.size();
        int sum = 0;
        for (int i = 0; i < number; i++) {
            System.out.println(processes.get(i).name + " " + processes.get(i).waitingTime);
            sum += processes.get(i).waitingTime;
        }
        return  sum;
    }
    int turnAroundTime()
    {
        int number = processes.size();
        int sum =0;
        for (int i = 0; i < number; i++) {
            System.out.println(processes.get(i).name+" "+processes.get(i).turnAroundTime());
            sum+= processes.get(i).turnAroundTime();
        }
        return sum;
    }
    void getAverageWaitingTime()
    {
        System.out.println("Average Waiting Time"+this.waitingTime()/processes.size());
    }

    void getAverageTurnAroundTime()
    {
        System.out.println("Average Turnaround Time"+this.turnAroundTime()/processes.size());
    }
    void QuantumHistory()
    {
        int number = quantumHistory.size()/processes.size();
        int n = 1;
        for (int i = 0; i < quantumHistory.size(); i++) {
//            System.out.print("(");
//            for (int j = 0; j < processes.size(); j++) {
//                System.out.print(quantumHistory.get(i+j));
//                if(processes.size()>1 && j!= processes.size()-1)
//                    System.out.print(",");
//            }
//            System.out.print(")\n");

            System.out.print(quantumHistory.get(i)+" ");
            if(i+1==processes.size())
                System.out.println();

            if(n%processes.size()==0)
                System.out.println();
            ++n;
        }
        System.out.println();
    }

    void showOptions()
    {
        Scanner scanner = new Scanner(System.in);
        int choice =0;


        while (true){

            System.out.println("1-print processes execution order.");
            System.out.println("2-print waiting time for each process.");
            System.out.println("3-print turnaround time for each process.");
            System.out.println("4-print average waiting time.");
            System.out.println("5-print average turnaround time .");
            System.out.println("6-print AG scheduler.");
            System.out.println("7-Exit.");

            System.out.println("enter choice");
            choice = scanner.nextInt();

            if (choice==1)
            {
                this.OrderExecution();
            }else if(choice==2){
                this.waitingTime();
            }else if(choice==3){
                this.turnAroundTime();
            }else if(choice==4)
            {
                this.getAverageWaitingTime();
            }else if(choice==5){
                this.getAverageTurnAroundTime();
            }else if(choice==6){
                this.QuantumHistory();
            }else if(choice==7) {
                break;
            }
        }

    }



}
