package com.company;

public class Process {

    String name ="P0";
    int time =0;
    int priority=0;

    boolean isAdded = false;
    boolean toQueue = false;

    public boolean isToQueue() {
        return toQueue;
    }

    public void setToQueue(boolean toQueue) {
        this.toQueue = toQueue;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    boolean isFinished = false;

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    public void setQuantum(double quantum) {
        this.quantum = quantum;
    }

    public void setTime(int time) {
        this.time = time;
    }

    double quantum=0;
    int arrivalTime=0;


    double remainingQuantum;
    int remainingTime=0;
    int waitingTime=0;

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Process(){}

    public Process(String name, int time, int priority, int quantum,
                   int arrivalTime) {
        this.name = name;
        this.time = time;
        this.priority = priority;
        this.quantum = quantum;
        this.arrivalTime = arrivalTime;
        this.remainingQuantum = this.quantum;
        this.remainingTime =this.time;
    }

    public double getRemainingQuantum() {
        return remainingQuantum;
    }

    public void setRemainingQuantum(double remainingQuantum) {
        this.remainingQuantum = remainingQuantum;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public int getPriority() {
        return priority;
    }

    public double getQuantum() {
        return quantum;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }


    int turnAroundTime()
    {
       return this.time + this.waitingTime;
    }

}
