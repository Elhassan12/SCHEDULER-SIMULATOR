import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Preemptive_SJF {
	public ArrayList<Process> processesList = new ArrayList<>(); // list for all the process   
	public ProcessInCPU cpu = new ProcessInCPU(); 
	
	private int numOfProcess; 
	private static float avarageTurnAroundTime; // avg = SUM processes turnaround time / numOfProcess 
	private static float avarageWaitingTime; // avg = SUM processes waiting time / numOfProcess
	
	public Process[] processes;
	public Queue<Process> readyQueue = new LinkedList<Process>(); // Queue for processes in the ready state 
	public Queue<Process> completedQueue = new LinkedList<Process>(); //Queue for processes completed its execution
	public float ContextSwitching; // The time taken (spent) between two processes 
	// Setter & Getter 
	public void setNumOfProcess(int numOfProcess) {
		this.numOfProcess = numOfProcess; 
	}
	
	public void setProcessArray() {
		processes = new Process[numOfProcess]; // initialize the process array with the size of processes given
		for (int i = 0 ; i < numOfProcess ; i++) {
			processes[i] = new Process(); 
		}
	}
	
	public static float getAvarageTurnAroundTime() {
		return avarageTurnAroundTime; 
	}
	
	public static float getAvarageWaitingTime() {
		return avarageWaitingTime; 
	}
	
	public void addToCompleteQueue(Process process, int time) {
		completedQueue.offer(process); // offer = add, but insert it if its possible to do without violating any capacity restrictions
		process.completionTime = time;  // initialize process completionTime with the time given
		process.turnAroundTime = process.completionTime - process.arrivalTime; // calculate the turnAroundTime for the given process
	}
	
	// method to take the arrival time given and compare it with each process in the processes[process] 
	// and insert the process with the same arrival time in the readyQueue<Process> to start the execution 
	public void recieveProcess(int processArrivalTime) {  
		for(int i = 0 ; i < numOfProcess ; i++) {
			if (processes[i].arrivalTime == processArrivalTime) {
				insertIntoReadyQueue(processes[i]); 
			}
		}
	}

	public void insertIntoReadyQueue(Process process) {
		if (readyQueue.size() == 0 ) {
			readyQueue.offer(process); 
			return; 
		}
		
		Process temp; // the first (head) process in the readyQueue
		boolean inserted = false; // to check if this process has been inserted into the readyQueue or not 
		int size = readyQueue.size(); 
		for (int i = 0; i < size ; i++) {
			temp = readyQueue.poll(); // Retrieves and removes the head of this queue,or returns null if this queue is empty.
			if (process.burstTime < temp.burstTime && !inserted) {
				inserted = true; 
				readyQueue.add(process); 
				// if the given new process burst time less than the head of the readyQueue (process using the CPU) burst time
				// insert the less burst time process instead of that process and take the CPU away from it. 
			}
			// if no keep everything as it is for now
			readyQueue.add(temp);
		}
		// and insert the new given process in the readyQueue
		if (!inserted) {
			readyQueue.add(process);
		}
	}
	
	public void preemptive_SJF() {
		int time = 0; 
		float contextSwitching = 0;
		while (completedQueue.size() != numOfProcess) {
		// check while if there is space in the comletedQueue
			recieveProcess(time); // start receive new process(arrivalTime);  
			if (cpu.currentProcess.burstTime < 0) {
				// check if the current process using the CPU burstTime < 0 
				if (readyQueue.size() != 0) {
					cpu.currentProcess = readyQueue.poll(); //pool()
				}
			}
			
			if (readyQueue.size() != 0) {
				if (readyQueue.peek().burstTime < cpu.currentProcess.burstTime && cpu.currentProcess.burstTime > 0) {
					cpu.currentProcess.endTime.add(time); 
					contextSwitching = ContextSwitching; 
					while (contextSwitching > 0) {
						contextSwitching--; 
						cpu.currentProcess.waitingTime++; 
						
						for (Process process : readyQueue) {
							process.waitingTime++;
						}
						time++; 
						recieveProcess(time);
					}
					insertIntoReadyQueue(cpu.currentProcess);
					cpu.currentProcess = readyQueue.poll();
				}
			}
			
			if (cpu.currentProcess.burstTime == 0) {
				contextSwitching = ContextSwitching; 
				while(contextSwitching > 0) {
					contextSwitching--;
					cpu.currentProcess.waitingTime++; 
					
					for(Process process : readyQueue) {
						process.waitingTime++;
					}
					time++; 
					recieveProcess(time);
				}
				
				cpu.currentProcess.endTime.add(time);
				addToCompleteQueue(cpu.currentProcess, time);
				if (readyQueue.size() == 0) {
					cpu.currentProcess = new Process(); 
				}
				else {
					cpu.currentProcess = readyQueue.poll();
					
				}
			}
			
			if (cpu.currentProcess.burstTime > 0) {
				cpu.currentProcess.startTime.add(time);
				cpu.currentProcess.burstTime--;
				for(Process process : readyQueue) {
					process.waitingTime++; 
				}
			}
			time++; 
		}
		float turnaroudSum = 0, waitingSum = 0 ;
		for(Process process : completedQueue) {
			waitingSum += process.waitingTime; 
			turnaroudSum += process.turnAroundTime; 
		}
		
		avarageWaitingTime = waitingSum / numOfProcess; 
		avarageTurnAroundTime = turnaroudSum / numOfProcess;
		
	}
	
	public void input() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of processes: ");
		int numOfProcess = scanner.nextInt(); 
		setNumOfProcess(numOfProcess);
		setProcessArray();
		
		System.out.println("Enter the Context Switching Time: ");
		ContextSwitching = scanner.nextFloat(); 
		scanner.nextLine(); 
		System.out.println("Enter for each processes names, Arrival times, Burst times, Priority in that order: ");
		for(int i = 0 ; i< numOfProcess ; i++) {
			processes[i].processName = scanner.next(); 
			processes[i].arrivalTime = scanner.nextInt(); 
			processes[i].burstTime = scanner.nextInt();
			processes[i].priority = scanner.nextInt();
		}
	}
	
	public void display() { 
		System.out.println("Order in Which Processes gets executed: ");
		for(Process process : completedQueue) {
			System.out.print(process.processName + " ");
			processesList.add(process);
		}
		System.out.println();
		System.out.println("*************************************");
		
        System.out.println("Name\tTAT\tWT");
        for (Process process : completedQueue) {
        	System.out.println(process.processName + 
        			"\t" + process.turnAroundTime + 
        			"\t" + process.waitingTime);
        }        
		System.out.println("*************************************");
		
		for (Process process : processesList) {
			System.out.print("Execution for process " + process.processName + ": " + " ");
			ArrayList<Integer> executionOrder = new ArrayList<>();  
			executionOrder.addAll(process.startTime);
			executionOrder.addAll(process.endTime);
			executionOrder.sort(null);
			for (int startTime : executionOrder) {
				if (process.endTime.contains(startTime)) {
					System.out.print("End: " + startTime + ", " );
				} else {
					System.out.print(startTime + " ");
				}
			}
			System.out.println();
		}
		System.out.println("*************************************");
		System.out.println("Avarage Waiting Time = " + Preemptive_SJF.getAvarageWaitingTime());
		System.out.println("Avarage Turnaround Time = " + Preemptive_SJF.getAvarageTurnAroundTime());
	}
	
}
