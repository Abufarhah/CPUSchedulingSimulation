//this class represent the objects of the table entry
public class Row {
	private String algorithm;
	private String name;
	private double avg1;
	private double avg2;
	private double avg3;
	private double avg4;

	public Row() {
		super();
	}

	public Row(String name, double avg1, double avg2, double avg3, double avg4) {
		this.name = name;
		this.avg1 = avg1;
		this.avg2 = avg2;
		this.avg3 = avg3;
		this.avg4 = avg4;
	}

	public Row(String algoritm, String name, double avg1, double avg2, double avg3, double avg4) {
		this.algorithm = algoritm;
		this.name = name;
		this.avg1 = avg1;
		this.avg2 = avg2;
		this.avg3 = avg3;
		this.avg4 = avg4;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAvg1() {
		return avg1;
	}

	public void setAvg1(double avg1) {
		this.avg1 = avg1;
	}

	public double getAvg2() {
		return avg2;
	}

	public void setAvg2(double avg2) {
		this.avg2 = avg2;
	}

	public double getAvg3() {
		return avg3;
	}

	public void setAvg3(double avg3) {
		this.avg3 = avg3;
	}

	public double getAvg4() {
		return avg4;
	}

	public void setAvg4(double avg4) {
		this.avg4 = avg4;
	}

	@Override
	public String toString() {
		return "Row [name=" + name + ", avg1=" + avg1 + ", avg2=" + avg2 + ", avg3=" + avg3 + ", avg4=" + avg4 + "]";
	}
}
