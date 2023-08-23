import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class CPUSchedulerSimulation {
    private ArrayList<Process> processArrayList = new ArrayList<Process>();
    int waitingTime = 0;
    private int contextSwitching;
    int numberOfProcesses;

    public CPUSchedulerSimulation(int ctxSwitching) {
        contextSwitching = ctxSwitching;
    }

    void addProcesses(Process p) {
        processArrayList.add(p);
        numberOfProcesses++;
    }
    void Priority() {

        int time = 0;
        Process prevProccess = null;
        final int quntum = 10;

        while(true) {
            int elapsedTime;
            Process runningProcess;
            int nextArrivalTime = getNextArrivalTime(time);

            final int constTime = time;
            ArrayList<Process> activeProccess = processArrayList.stream()
                    .filter(p -> p.arrivalTime <= constTime && p.timeLeft > 0 )
                    .sorted((p1, p2) -> p2.priority - p1.priority )
                    .collect(Collectors.toCollection(ArrayList::new));

            if (activeProccess.size() == 0 && nextArrivalTime == -1) {
                break;
            }

            if (activeProccess.size() == 0) {
                time = nextArrivalTime;
                continue;
            }

            runningProcess = activeProccess.get(0);

            if (quntum < runningProcess.timeLeft && time + quntum < nextArrivalTime) {
                elapsedTime = quntum;
                for (Process p: activeProccess) {
                    // if (p == runningProcess) continue;
                    p.priority += 1;
                }
            } else if (time + runningProcess.timeLeft > nextArrivalTime && nextArrivalTime != -1) {
                elapsedTime = nextArrivalTime - time;
            } else {
                elapsedTime = runningProcess.timeLeft;
                runningProcess.finishTime = time + elapsedTime;
            }

            System.out.println(time + " " + runningProcess.name + ": TimeLeft: " + runningProcess.timeLeft);

            runningProcess.timeLeft -= elapsedTime;
            time += elapsedTime;

            if ( prevProccess != null && runningProcess != prevProccess) {
                time += contextSwitching;
            }
            prevProccess = runningProcess;
        }

        System.out.println(time + " " + prevProccess.name + ": TimeLeft: " + prevProccess.timeLeft  );
        //System.out.println(time + " " + prevProccess.name + ": TimeLeft: " + prevProccess.timeLeft  + " Quantum: " + prevProccess.quantum);
        for (Process p: processArrayList) {
            int estimatedFinishTime = p.arrivalTime + p.burstTime;
            p.waitingTime = p.finishTime - estimatedFinishTime;
        }

        printProccess();
    }
    
    void printProccess() {

    	float  avgWaiting = 0;
    	float  avgTurnAroundTime = 0;

        ArrayList<Process> sortedProccess = processArrayList.stream()
                .sorted((p1, p2) -> p1.finishTime - p2.finishTime)
                .collect(Collectors.toCollection(ArrayList::new));
        for (Process p: sortedProccess) {
            int TurnaroundTime = p.finishTime - p.arrivalTime;
            avgWaiting += p.waitingTime;
            avgTurnAroundTime += TurnaroundTime;
            System.out.println(p.name + " WaitingTime: " + p.waitingTime + " Turnaround  Time: " + TurnaroundTime );
        }

        avgWaiting /= sortedProccess.size();
        avgTurnAroundTime /= sortedProccess.size();

        System.out.println("Avg Waiting: " + avgWaiting);
        System.out.println("Avg Turn Around Time: " + avgTurnAroundTime);



    }
    int getNextArrivalTime(int currentTime) {
        final int constTime = currentTime;
        int nextArrivalTime = -1;
        try {
            nextArrivalTime = processArrayList.stream()
                    .filter(p -> p.arrivalTime > constTime)
                    .min((p1, p2) -> p1.arrivalTime - p2.arrivalTime)
                    .get().arrivalTime;
        } catch (NoSuchElementException ignore) {}
        return nextArrivalTime;
    }
}
