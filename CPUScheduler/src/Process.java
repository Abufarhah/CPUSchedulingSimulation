public class Process {
	private static int numOfProcesses;
	private int pid;
	private int burst;
	private int arrivalTime;
	private int startTime = -1;
	private int endTime;
	private int turnTime;
	private int waitTime;
	private int remainingTime;
	private int level = 1;
	
	//default constructor gives the attributes random numbers
	public Process() {
		pid = numOfProcesses % 12;
		burst = (int) (Math.random() * 990) + 10;
		arrivalTime = (int) (Math.random() * 1500);
		numOfProcesses++;
	}
	
	//method to reset the process as a new process
	public void reset() {
		startTime = -1;
		endTime = 0;
		turnTime = 0;
		waitTime = 0;
		remainingTime = 0;
		level = 1;
	}

	public static int getNumOfProcesses() {
		return numOfProcesses;
	}

	public static void setNumOfProcesses(int numOfProcesses) {
		Process.numOfProcesses = numOfProcesses;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}

	public int getBurst() {
		return burst;
	}

	public void setBurst(int burst) {
		this.burst = burst;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getTurnTime() {
		return turnTime;
	}

	public void setTurnTime(int turnTime) {
		this.turnTime = turnTime;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Process [pid=" + pid + ", burst=" + burst + ", arrivalTime=" + arrivalTime + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", turnTime=" + turnTime + ", waitTime=" + waitTime + "]";
	}

}
