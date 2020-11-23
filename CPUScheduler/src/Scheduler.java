import java.util.LinkedList;
import java.util.Queue;

public class Scheduler {

	public Process a[];

	public Scheduler() {
		a = new Process[12];
		for (int i = 0; i < 12; i++) {
			a[i] = new Process();
		}
	}

	// method to simulate FCFS algorithm
	// gives each process end time and start time
	// calculate turn around time and waiting for each process
	// calculate AWT nad ATT for the 12 processes
	public void FCFS() {
		//each simulation method shall reset the processes data
		for (int i = 0; i < 12; i++) {
			a[i].reset();
		}
		int t;
		//this queue represent the ready queue
		Queue<Process> queue = new LinkedList<Process>();
		
		//sort the array in term of the arrival time of each process
		Process temp;
		for (int i = 0; i < 12; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[j].getArrivalTime() < a[i].getArrivalTime()) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
		}
		//add the sorted process to the queue
		for (int i = 0; i < 12; i++) {
			queue.add(a[i]);
		}
		//begin time is the time of the first process arrived
		t = queue.peek().getArrivalTime();
		while (!queue.isEmpty()) {
			temp = queue.poll();
			//the start time of the next process is the current time or it's arrival time
			temp.setStartTime(temp.getArrivalTime() <= t ? t : temp.getArrivalTime());
			//update the time as if the process was finished
			t = temp.getStartTime() + temp.getBurst();
			//set the end time of the process
			temp.setEndTime(t);
			//calculate the average waiting time and the average turnaround time 
			temp.setTurnTime(temp.getEndTime() - temp.getArrivalTime());
			temp.setWaitTime(temp.getTurnTime() - temp.getBurst());
			//replace or update the process with a finished process
			a[temp.getPid()] = temp;
		}
	}

	// method to simulate SJF algorithm
	// gives each process end time and start time
	// calculate turn around time and waiting for each process
	// calculate AWT nad ATT for the 12 processes
	public void SJF() {
		//each simulation method shall reset the processes data
		for (int i = 0; i < 12; i++) {
			a[i].reset();
		}
		int t;
		
		//sort the array in term of the arrival time of each process
		Process temp;
		for (int i = 0; i < 12; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[j].getArrivalTime() < a[i].getArrivalTime()) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
		}
		int index = 0;
		int c = 0;
		//c represents the number of finished processes
		//begin time is the time of the first process arrived
		t = a[0].getArrivalTime();
		while (c < 12) {
			//search for the first process can execute
			for (int i = 0; i < 12; i++) {
				if (a[i].getEndTime() == 0) {
					index = i;
					break;
				}
			}
			//search for if there are another arrived process and shorter 
			for (int i = 0; i < 12; i++) {
				if (a[i].getEndTime() == 0 && a[i].getArrivalTime() <= t && a[i].getBurst() < a[index].getBurst())
					index = i;
			}
			//this the process to execute
			temp = a[index];
			//the start time of the next process is the current time or it's arrival time
			temp.setStartTime(temp.getArrivalTime() <= t ? t : temp.getArrivalTime());
			//update the time as if the process was finished
			t += temp.getBurst();
			//set the end time of the process
			temp.setEndTime(t);
			//calculate the average waiting time and the average turnaround time 
			temp.setTurnTime(temp.getEndTime() - temp.getArrivalTime());
			temp.setWaitTime(temp.getTurnTime() - temp.getBurst());
			//increment the number of the finished processes
			c++;
		}
		//return the order of the process in the array based on the id
		for (int i = 0; i < 12; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[j].getPid() < a[i].getPid()) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
		}
	}

	// method to simulate RR algorithm
	// gives each process end time and start time
	// calculate turn around time and waiting for each process
	// calculate AWT nad ATT for the 12 processes
	public void RR() {
		//each simulation method shall reset the processes data
		for (int i = 0; i < 12; i++) {
			a[i].reset();
		}
		int t;
		Queue<Process> queue = new LinkedList<Process>();
		
		//sort the array in term of the arrival time of each process
		Process temp;
		for (int i = 0; i < 12; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[j].getArrivalTime() < a[i].getArrivalTime()) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
		}
		//for each process set the remaining time to the it's burst
		for (int i = 0; i < 12; i++) {
			a[i].setRemainingTime(a[i].getBurst());
			queue.add(a[i]);
		}
		//begin time is the time of the first process arrived
		t = queue.peek().getArrivalTime();
		while (!queue.isEmpty()) {
			// System.out.println("lkjhghjk "+queue.size());
			temp = queue.poll();
			// this loop means that the process temp and the other processes
			// are not in the queue so the cpu deals with the processes in the
			// queue
			// this loop return the cpu to the first process and so on
			int c = 0;
			while (t > 0 && t < temp.getArrivalTime() && c < queue.size()) {
				queue.add(temp);
				temp = queue.poll();
				++c;
			}
			//if there is no process arrived the update the time to the first arrived process
			if (c == queue.size())
				t = t < temp.getArrivalTime() ? temp.getArrivalTime() : t;

			temp.setStartTime(temp.getStartTime() == -1 ? Math.max(temp.getArrivalTime(), t) : temp.getStartTime());
			t += Math.min(temp.getRemainingTime(), 50);
			// set the remaining time
			temp.setRemainingTime(temp.getRemainingTime() - Math.min(temp.getRemainingTime(), 50));
			// if the remaining time=0 this mean that the process is finished so
			// it go out the queue
			if (temp.getRemainingTime() == 0) {
				temp.setEndTime(t);
				temp.setTurnTime(temp.getEndTime() - temp.getArrivalTime());
				temp.setWaitTime(temp.getTurnTime() - temp.getBurst());
				a[temp.getPid()] = temp;
			} else {
				queue.add(temp);
			}
		}
	}

	// method to simulate MFQ algorithm
	// gives each process end time and start time
	// calculate turn around time and waiting for each process
	// calculate AWT nad ATT for the 12 processes
	public void MFQ() {
		//each simulation method shall reset the processes data
		for (int i = 0; i < 12; i++) {
			a[i].reset();
		}
		int t;
		//there are three queues
		//the first queue depends on RR with Q=20
		//the second queue depends on RR with Q=100
		//the third queue depends on FCFS
		Queue<Process> queue1 = new LinkedList<Process>();
		Queue<Process> queue2 = new LinkedList<Process>();
		Queue<Process> queue3 = new LinkedList<Process>();

		//sort the in term of the arrival time of each process
		Process temp;
		for (int i = 0; i < 12; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[j].getArrivalTime() < a[i].getArrivalTime()) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
		}
		//for each process set the remaining time to the it's burst
		//add the processes to the first queue
		for (int i = 0; i < 12; i++) {
			a[i].setRemainingTime(a[i].getBurst());
			queue1.add(a[i]);
		}
		//begin time is the time of the first process arrived
		t = queue1.peek().getArrivalTime();
		temp = queue1.peek();
		while (!(queue1.isEmpty() && queue2.isEmpty() && queue3.isEmpty())) {
			if (!queue1.isEmpty()) {
				temp = queue1.peek();
				if (t < temp.getArrivalTime() && !queue2.isEmpty()) {
					temp = queue2.poll();
				} else if (t < temp.getArrivalTime() && queue2.isEmpty() && !queue3.isEmpty()) {
					temp = queue3.poll();
				} else if (!queue1.isEmpty()) {
					temp = queue1.poll();
				}
			} else if (!queue2.isEmpty()) {
				temp = queue2.peek();
				if (t < temp.getArrivalTime() && !queue3.isEmpty()) {
					temp = queue3.poll();
				} else if (!queue2.isEmpty()) {
					temp = queue2.poll();
				}
			} else {
				temp = queue3.poll();
			}

			temp.setStartTime(temp.getStartTime() == -1 ? Math.max(temp.getArrivalTime(), t) : temp.getStartTime());
			t += Math.min(temp.getRemainingTime(),
					temp.getLevel() == 1 ? 20 : temp.getLevel() == 2 ? 100 : temp.getRemainingTime());
			temp.setRemainingTime(temp.getRemainingTime() - Math.min(temp.getRemainingTime(),
					temp.getLevel() == 1 ? 20 : temp.getLevel() == 2 ? 100 : temp.getRemainingTime()));

			if (temp.getRemainingTime() == 0) {
				temp.setEndTime(t);
				temp.setTurnTime(temp.getEndTime() - temp.getArrivalTime());
				temp.setWaitTime(temp.getTurnTime() - temp.getBurst());
				a[temp.getPid()] = temp;
			} else {
				//the process in queue1 gives Q=20 the go to queue2
				if (temp.getLevel() == 1) {
					temp.setLevel(2);
					queue2.add(temp);
				//the process in queue2 gives Q=20 the go to queue3
				} else if (temp.getLevel() == 2) {
					temp.setLevel(3);
					queue3.add(temp);
				}
			}
		}
	}

}
