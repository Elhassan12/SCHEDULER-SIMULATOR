package com.company;

import STLs.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

//        q.add(2);
//        q.add(3);
//        System.out.println(q.size());
//        Queue<Integer> q2 = new LinkedList<>();
//        int size = q.size();
//        for (int i = 0; i <size ; i++) {
//            int num = q.poll();
//            if(num !=2){
//                q2.add(num);
//            }
//
//        }
//        q = q2;
//        System.out.println(q.poll());
//        v.clear();
//        Process process1 = new Process("p2",1,1,4,0);
//        v.add(process);
//        q.add(v.get(0));
//        q.add(process1);
//        p = process;
//        p.setTime(11);
//        System.out.println(process.time);
//        System.out.println(p.time);
//        System.out.println(process.time);
//        p = process1;
//        p.setTime(8);
//        System.out.println(process.time);
//        System.out.println(process1.time);
//        System.out.println(p.time);
//        q.poll().setTime(5);
//       p.setTime(5);
//      System.out.println(process.time);
//      System.out.println(v.get(0).time);
//      System.out.println(p.time);
//        Vector<Process> v = new Vector<>();
//        Vector<Process> v1 = new Vector<>();
//        Queue<Process> q =new LinkedList<>();
//        Process process = new Process("p1",10,1,4,0);
//        v.add(process);
//        v1.add(v.get(0));
//        v.get(0).setTime(3);
//        Process p = null;
//        try foreach and reference.
//        for (int i = 0; i < v1.size(); i++) {
//            q.add(v1.get(i));
//        }
//
//        p = q.poll();
//        assert p != null;
//        p.setTime(5);
//        System.out.println(process.time);
//        System.out.println(v.get(0).time);
//        System.out.println(v1.get(0).time);
//        System.out.println(p.time);
//        System.out.println(4+(int) Math.ceil(3.5));
        Scanner scanner = new Scanner(System.in);
        Vector<Process> processes = new Vector<>();
        int number = scanner.nextInt();
        In in = new In();
        double n = 9;
        System.out.println(Math.ceil(n/4));
        in.takeInputs(number);
        processes = in.getProcesses();
        AG ag = new AG(processes);
        ag.agAlgorithm();
        processes = ag.processes;
//        for (int i = 0; i < ag.quantumHistory.size(); i++) {
//            System.out.println(ag.quantumHistory.get(i));;
//        }
        Out out = new Out(ag.processesOrderExecution,ag.processes,ag.quantumHistory);
        out.showOptions();



    }
}
