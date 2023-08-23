
public class Main {

	public static void main(String[] args) {
		Preemptive_SJF pSjf = new Preemptive_SJF(); 
		pSjf.input();
		pSjf.preemptive_SJF();
		pSjf.display();
	}

}
// Test case 

//Enter the number of processes: 
//5
//Enter the Context Switching Time: 
//0
//Enter for each processes names, Arrival times, Burst times, Priority in that order: 
//p1 0 4 1
//p2 1 8 2
//p3 3 2 3
//p4 10 6 4
//p5 12 5 5

// Output 

//Order in Which Processes gets executed: 
//p1 p3 p2 p5 p4 
//*************************************
//Name	TAT	WT
//p1	4	0
//p3	3	1
//p2	13	5
//p5	7	2
//p4	15	9
//*************************************
//Execution for process p1:  0 1 2 3 End: 4, 
//Execution for process p3:  4 5 End: 6, 
//Execution for process p2:  6 7 8 9 10 11 12 13 End: 14, 
//Execution for process p5:  14 15 16 17 18 End: 19, 
//Execution for process p4:  19 20 21 22 23 24 End: 25, 
//*************************************
//Avarage Waiting Time = 3.4
//Avarage Turnaround Time = 8.4