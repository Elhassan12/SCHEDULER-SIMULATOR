import java.util.ArrayList;

public class Process {
	public String processName;
	public int arrivalTime; // The time when a process enters into the ready state and is ready for its execution
	public int burstTime; // The amount of time required by a process for executing on CPU 
	public int completionTime; // The time the process completes its execution
	public int priority;
	public int turnAroundTime; // The time taken by a process since it enters a ready queue till the completion of its execution
	// TAT = completionTime - arrivalTime
	public int waitingTime; // The total time spent by the process in the ready state waiting for CPU
	// WT = turnAroundTime - burstTime 
    public ArrayList<Integer> startTime;
    public ArrayList<Integer> endTime;
	
	// default constructor to initialize variables
	public Process() {
		processName= ""; 
		arrivalTime= -1;
		burstTime= -1; 
		completionTime= -1;
		priority = 0; 
		waitingTime= 0; 
		turnAroundTime= 0; 
		startTime = new ArrayList<>(); 
		endTime = new ArrayList<>(); 
		
	}
	
}
