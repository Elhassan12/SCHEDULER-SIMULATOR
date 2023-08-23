
public class ProcessInCPU {
	public Process currentProcess;
	public int timer;
	
	// default constructor to initialize variables
	public ProcessInCPU() {
		currentProcess = new Process();
		timer= 0; 
	}

	public void setCurrentProcess(Process currentProcess) {
		this.currentProcess = currentProcess;
	}
}
