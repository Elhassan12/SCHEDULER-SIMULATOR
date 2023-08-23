package com.company;

import STLs.Pair;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Vector;

public class AG {

    public AG(Vector<Process> processes) {
        this.processes = processes;
    }

    Vector<Process> processes ;
    Vector<Process> arrivedProcesses = new Vector<>() ;
    Vector<Process> terminatedProcesses =new Vector<>() ;
    Vector<Integer> quantumHistory =new Vector<>() ;
    Vector<Pair> processesOrderExecution = new Vector<>();

    public Vector<Process> getTerminatedProcesses() {
        return terminatedProcesses;
    }

    Queue<Process> readyQueue = new LinkedList<>();
    int time =0;

    void updateQueue(Process process) {
        Queue<Process> q2 = new LinkedList<>();

        int size = this.readyQueue.size();
        for (int i = 0; i <size ; i++) {
            Process poll = this.readyQueue.poll();
            assert poll != null;
            if(!Objects.equals(poll.name, process.name)){
                q2.add(poll);
            }

        }
        this.readyQueue = q2;
    }

    Process getHighestPriority() {
        int min =arrivedProcesses.get(0).priority;
        int index =0;
        for (int i = 1; i < arrivedProcesses.size(); i++) {
            if(arrivedProcesses.get(i).priority<min)
            {
                min = arrivedProcesses.get(i).priority;
                index = i;
            }

        }
        return arrivedProcesses.get(index);
    }

    Process getLowestRemainingTime() {
        int min =arrivedProcesses.get(0).remainingTime;
        int index =0;
        for (int i = 1; i < arrivedProcesses.size(); i++) {
            if(arrivedProcesses.get(i).remainingTime<min)
            {
                min = arrivedProcesses.get(i).remainingTime;
                index = i;
            }

        }
//        System.out.println("lowest is: "+min);
        return arrivedProcesses.get(index);
    }

    void addToQuantumHistory() {
        for (Process process : processes) {
            quantumHistory.add((int) process.quantum);
        }
    }

    void addToArrivedVector(){
        for (Process process : processes) {
            if (this.time >= process.arrivalTime) {
               if(!process.isAdded){
                   arrivedProcesses.add(process);
                   process.setAdded(true);
               }
            }
        }
    }

    void addToReadyQueue(){
        for (int i = 0; i < arrivedProcesses.size(); i++) {
            if(!arrivedProcesses.get(i).toQueue)
            {
                readyQueue.add(arrivedProcesses.get(i));
                arrivedProcesses.get(i).setToQueue(true);

            }
        }
    }

     void  addToTerminatedList(Process p){
         for (Process process : processes) {
             if (Objects.equals(p.name, process.name)) {
                 process.setFinished(true);
                 terminatedProcesses.add(process);
                 break;
             }
         }


     }

    void increaseWaitingTime(Process process,int t){
        for (Process value : processes) {
            if (!Objects.equals(process.name, value.name))
                if (!value.isFinished)
                    value.setWaitingTime(value.waitingTime + t);
        }

    }

    void orderExecution(Process process){
        Pair pair = new Pair(process.name,this.time);
        processesOrderExecution.add(pair);
    }

    void finishedProcess(Process process){
        this.time += process.remainingTime;
        increaseWaitingTime(process,process.remainingTime);
        orderExecution(process);
        process.setQuantum(0);
        addToArrivedVector();
        addToReadyQueue();
        addToQuantumHistory();
        addToTerminatedList(process);
    }

    void agAlgorithm(){
        addToQuantumHistory();
        addToArrivedVector();
        addToReadyQueue();
        Process p = null;

        p = readyQueue.poll();
        while (!readyQueue.isEmpty()|| terminatedProcesses.size()!= processes.size()){
                  if(p==null){
                      p= readyQueue.poll();
                  }
            if(p.remainingTime<= (int)Math.ceil( p.quantum/4)){
                finishedProcess(p);
//
                p = readyQueue.poll();
//
            }else
            {
                this.time +=(int) Math.ceil(p.quantum/4);
                p.setRemainingTime( p.getRemainingTime()-(int) Math.ceil(p.quantum/4));
                p.setRemainingQuantum(p.getRemainingQuantum()-Math.ceil(p.quantum/4));
                increaseWaitingTime(p,(int)Math.ceil(p.quantum/4));
                addToArrivedVector();
                addToReadyQueue();

//               check highest priority.
                if(Objects.equals(p.name, getHighestPriority().name)){
                    if(p.remainingTime<= (int)Math.ceil( p.quantum/4))
                    {
                        finishedProcess(p);
                        addToArrivedVector();
                        addToReadyQueue();
                        p = readyQueue.poll();
                    }else {
                        this.time +=(int) Math.ceil(p.quantum/4);
//

                        p.setRemainingTime( p.getRemainingTime()-(int) Math.ceil(p.quantum/4));
                        p.setRemainingQuantum(p.getRemainingQuantum()-Math.ceil(p.quantum/4));

                        increaseWaitingTime(p,(int)Math.ceil(p.quantum/4));
                        addToArrivedVector();
                        addToReadyQueue();

//                        lowest remainging time
                        if(Objects.equals(p.name, getLowestRemainingTime().getName())){
                            if(p.remainingTime<= (int)Math.ceil( p.quantum/4))
                            {
                                finishedProcess(p);
                                addToArrivedVector();
                                addToReadyQueue();
                                p = readyQueue.poll();
                            }else {

                                if (p.getRemainingQuantum()<p.getRemainingTime())
                                {
                                    this.time += (int) Math.ceil(p.remainingQuantum);
                                    p.setRemainingTime(p.getRemainingTime()-(int)Math.ceil(p.remainingQuantum));
                                    increaseWaitingTime(p,(int)Math.ceil(p.remainingQuantum));
                                    p.setQuantum(2);
                                    p.setRemainingQuantum(2);
                                    addToQuantumHistory();
                                    orderExecution(p);
                                    readyQueue.add(p);
                                    addToArrivedVector();
                                    addToReadyQueue();
                                    p= readyQueue.poll();
                                }else {
                                    this.time += (int) Math.ceil(p.remainingTime);
                                    increaseWaitingTime(p,(int)Math.ceil(p.remainingTime));
                                    p.setRemainingTime(0);
                                    p.setQuantum(0);
                                    p.setRemainingQuantum(0);
                                    orderExecution(p);
                                    addToQuantumHistory();
                                    addToTerminatedList(p);
                                    addToArrivedVector();
                                    addToReadyQueue();
                                    p= readyQueue.poll();
                                }
                            }
                        }else {

                            p.setQuantum(p.getRemainingQuantum()+p.getQuantum());
                            p.setRemainingQuantum(p.getQuantum());
                            addToQuantumHistory();
                            orderExecution(p);
                            addToArrivedVector();
                            addToReadyQueue();
                            readyQueue.add(p);
                            p= getLowestRemainingTime();
                            updateQueue(p);
                        }
                    }
                }else {
                    p.setQuantum(p.getQuantum()+Math.ceil(p.getRemainingQuantum()/2));
                    p.setRemainingQuantum(p.getQuantum());
                    addToQuantumHistory();
                    orderExecution(p);
                    readyQueue.add(p);
                    p = getHighestPriority();
                    updateQueue(p);
                }
            }
        }
    }





//    void updateArrivedList(Process process, boolean quantum){
//        if(quantum){
//            for (Process arrivedProcess : arrivedProcesses) {
//                if (Objects.equals(arrivedProcess.name, process.name)) {
//                    arrivedProcess.setQuantum(process.getQuantum());
//                    break;
//                }
//
//            }
//        }else
//        {
//            for (Process arrivedProcess : arrivedProcesses) {
//                if (Objects.equals(arrivedProcess.name, process.name)) {
//                    arrivedProcess.setRemainingTime(process.getRemainingTime());
//                    break;
//                }
//
//            }
//        }
//    }








//    for quantum history
//    after every modification in quantum for any process call update process quantum and give it the process
//    add their
//    quantum to Quantum history vector by call the method

//    check before give the processor to any anthor process.
//    if a process arrived put it in ready Queue and in arrived Vector.

//    what if the current process is has the lowest remaining time??
//
    //    void updateProcessQuantum(Process process){
//        for (int i = 0; i < processes.size(); i++) {
//            if(Objects.equals(process.name, processes.get(i).name))
//            {
//                processes.get(i).setQuantum(process.getQuantum());
//            }
//        }
//    }
}
