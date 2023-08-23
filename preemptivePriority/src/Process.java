public class Process {
    public String name;
    public int arrivalTime;
    public int burstTime;
    public int timeLeft;
    public int quantum;
    public int priority;
    public int waitingTime;
    public int finishTime;

    public Process(String name, int arriveTime, int burstTime, int quantum, int priority) {
        this.name = name;
        this.arrivalTime = arriveTime;
        this.burstTime = burstTime;
        this.timeLeft = burstTime;
        this.quantum = quantum;
        this.priority = priority;
        waitingTime = 0;
    }
}
