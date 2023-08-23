import java.util.Scanner;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Process process;
        Scanner scanner = new Scanner(System.in);        
        int NumberOfProcesses, ArrivalTime,BurstTime,Priority,Quantum,ContextSwitching;
        String ProcessName;
        System.out.println("Enter Number Of Processes ");
        NumberOfProcesses = scanner.nextInt();
        System.out.println("Enter Context Switching: ");
        ContextSwitching = scanner.nextInt();
        CPUSchedulerSimulation cpuSchedulerSimulation = new CPUSchedulerSimulation(ContextSwitching);
        for(int i=0;i<NumberOfProcesses;i++)
        {

            System.out.println("Enter The "+ (i+1) + "th" + "Process Name: ");
            ProcessName = scanner.next();
            System.out.println("Enter The "+ (i+1) + "th" + " Process Arrival Time: ");
            ArrivalTime = scanner.nextInt();
            System.out.println("Enter The "+ (i+1) + "th" + " Process Burst Time: ");
            BurstTime = scanner.nextInt();
            System.out.println("Enter The "+ (i+1) + "th" + " Process Priority: ");
            Priority = scanner.nextInt();
            //System.out.println("Enter The "+ (i+1) + "th" + " Process Quantum Time: ");
            //Quantum = scanner.nextInt();


            process = new Process(ProcessName,ArrivalTime,BurstTime,0,Priority);
            cpuSchedulerSimulation.addProcesses(process);
        }
        System.out.println("Enter Number Of Scheduling Algorithm: ");
        System.out.println("1-Priority ");

        int choice = scanner.nextInt();
        scanner.close();

        switch (choice) {
            case 1:
                cpuSchedulerSimulation.Priority();
                break;
        }

    }
}


/* 
  =================high Priority == high number ===========
test case
\//////////////////// 
input 
name->Arrival Time	> Burst Time  >Priority	 
p1   >0              >8           >2 
p2   >1              >1           >4 
p3   >2              >3           >3 
p4   >3              >2           >2 
p5   >4              >6           >1 
///////////////////////
 out
 
 p1>0-1    have 8 done 1 left 7
 p2>1-2    have 1 done 1 left 0 finish
 p3>2-5    have 3 done 3 left 0 finish
 p1>5-12   have 7 done 7 left 0 finish
 p4>12-14  have 2 done 2 left 0 finish
 p5>14-20  have 6 done 6 left 0 finish
 
 ////////////////////////////
 order is 
 p2-->p3-->p1-->p4-->p5
////////////////////////////
p2 WaitingTime: 0 Turnaround  Time: 1
p3 WaitingTime: 0 Turnaround  Time: 3
p1 WaitingTime: 4 Turnaround  Time: 12
p4 WaitingTime: 9 Turnaround  Time: 11
p5 WaitingTime: 10 Turnaround  Time: 16 
/////////////////////////////
Avg Waiting: 4.6
Avg Turn Around Time: 8.6

*/