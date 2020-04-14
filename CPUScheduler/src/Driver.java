/*public class Driver{
	public static void main(String[] args){
		Scheduler s=new Scheduler();
		s.MFQ();
		for (int i = 0; i < 12; i++) {
			System.out.println(s.a[i].toString()+"\n");
		}
	}
}*/
import javax.swing.DefaultBoundedRangeModel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Driver extends Application {
	public static final String MainStyle = "style.css";
	private Scheduler s;
	private Scheduler schedular;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Text textHeading = new Text("CPU Scheduling Algorithms");
		textHeading.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
		textHeading.setFill(Color.WHITE);
		HBox heading = new HBox(textHeading);
		heading.setPadding(new Insets(25, 0, 20, 105));
		ComboBox<String> alg = new ComboBox<>();
		alg.getItems().addAll("FCFS", "SJF", "RR", "MFQ");
		alg.setValue("FCFS");
		alg.setPadding(new Insets(5, 15, 5, 10));
		alg.setId("alg");
		// label for combo
		Label lalg = new Label("Scheduling Algorithm");
		lalg.setLabelFor(alg);
		lalg.setTextFill(Color.WHITE);
		// lalg.setPadding(new Insets(0,10,10,10));
		VBox valg = new VBox(lalg, alg);
		valg.setPadding(new Insets(0, 100, 10, 360));
		Button b1 = new Button("Generate Procceses\n ");
		Button b2 = new Button("Simulation of 12\n procceses");
		Button b3 = new Button("Statistics of\nalgorithm");
		HBox b = new HBox(70, b1, b2, b3);
		b.setPadding(new Insets(80, 30, 0, 30));
		Button b4 = new Button("Exit");
		HBox exit = new HBox(b4);
		exit.setPadding(new Insets(50, 20, 0, 710));
		Button b5 = new Button("Statistics of\nall algorithms");
		Button b6 = new Button("Bar Chart of\nall algorithms");
		b5.setId("b5");
		b6.setId("b6");
		HBox main = new HBox(50,b5,b6);
		main.setPadding(new Insets(40, 30, 0, 150));
		VBox v = new VBox(heading, valg, b, main, exit);
		Scene scene = new Scene(v, 790, 625);
		scene.getStylesheets().add(MainStyle);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("CPU Scheduling Algorithms");
		primaryStage.getIcons().add(new Image("file:ico.png"));
		primaryStage.show();
		b1.setOnAction(e -> {
			s = new Scheduler();
		});

		b2.setOnAction(e -> {
			try {
				TableView tableView = new TableView();

				TableColumn<String, Process> column1 = new TableColumn<>("Pid");
				column1.setCellValueFactory(new PropertyValueFactory<>("pid"));

				TableColumn<String, Process> column2 = new TableColumn<>("Burst");
				column2.setCellValueFactory(new PropertyValueFactory<>("burst"));

				TableColumn<String, Process> column3 = new TableColumn<>("Arrival Time");
				column3.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));

				TableColumn<String, Process> column4 = new TableColumn<>("Start Time");
				column4.setCellValueFactory(new PropertyValueFactory<>("startTime"));

				TableColumn<String, Process> column5 = new TableColumn<>("End Time");
				column5.setCellValueFactory(new PropertyValueFactory<>("endTime"));

				TableColumn<String, Process> column6 = new TableColumn<>("Turn around Time");
				column6.setCellValueFactory(new PropertyValueFactory<>("turnTime"));

				TableColumn<String, Process> column7 = new TableColumn<>("Wait Time");
				column7.setCellValueFactory(new PropertyValueFactory<>("waitTime"));

				tableView.getColumns().add(column1);
				tableView.getColumns().add(column2);
				tableView.getColumns().add(column3);
				tableView.getColumns().add(column4);
				tableView.getColumns().add(column5);
				tableView.getColumns().add(column6);
				tableView.getColumns().add(column7);
				if (alg.getValue().equals("FCFS")) {
					s.FCFS();
				} else if (alg.getValue().equals("SJF")) {
					s.SJF();
				} else if (alg.getValue().equals("RR")) {
					s.RR();
				} else {
					s.MFQ();
				}
				for (int i = 0; i < 12; ++i) {
					tableView.getItems().add(s.a[i]);
				}

				VBox vbox = new VBox(tableView);
				Scene sceneSim = new Scene(vbox, 500, 400);
				Stage stageSim = new Stage();
				stageSim.setScene(sceneSim);
				stageSim.setTitle(alg.getValue() + " Simulation");
				stageSim.getIcons().add(new Image("file:ico.png"));
				stageSim.show();
			} catch (Exception ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setResizable(false);
				alert.setHeaderText(null);
				alert.setContentText("you should generate processes first");
				alert.showAndWait();
			}
		});

		b3.setOnAction(e -> {
			int sum1 = 0, summation1 = 0, sum2 = 0, summation2 = 0;
			double avg = 0, avg1 = 0, avg2 = 0, avg3 = 0, avg4 = 0, avg5 = 0, avg6 = 0, avg7 = 0, avg8 = 0, avg9 = 0;
			for (int i = 0; i < 100000; ++i) {
				schedular = new Scheduler();
				if (alg.getValue().equals("FCFS")) {
					schedular.FCFS();
				} else if (alg.getValue().equals("SJF")) {
					schedular.SJF();
				} else if (alg.getValue().equals("RR")) {
					schedular.RR();
				} else {
					schedular.MFQ();
				}
				sum1 = 0;
				sum2 = 0;
				for (int j = 0; j < 12; ++j) {
					sum1 += schedular.a[j].getTurnTime();
					sum2 += schedular.a[j].getWaitTime();
				}
				// System.out.println(sum1+"****"+sum2);
				avg = sum1 / 12.0;
				avg5 = sum2 / 12.0;
				summation1 += avg;
				summation2 += avg5;
				if (i == 99) {
					avg1 = summation1 / 100.0;
					avg1 *= 100;
					avg1 = (int) avg1;
					avg1 /= 100;
					avg6 = summation2 / 100.0;
					avg6 *= 100;
					avg6 = (int) avg6;
					avg6 /= 100;
				}
				if (i == 999) {
					avg2 = summation1 / 1000.0;
					avg2 *= 100;
					avg2 = (int) avg2;
					avg2 /= 100;
					avg7 = summation2 / 1000.0;
					avg7 *= 100;
					avg7 = (int) avg7;
					avg7 /= 100;
				}
				if (i == 9999) {
					avg3 = summation1 / 10000.0;
					avg3 *= 100;
					avg3 = (int) avg3;
					avg3 /= 100;
					avg8 = summation2 / 10000.0;
					avg8 *= 100;
					avg8 = (int) avg8;
					avg8 /= 100;
				}
				if (i == 99999) {
					avg4 = summation1 / 100000.0;
					avg4 *= 100;
					avg4 = (int) avg4;
					avg4 /= 100;
					avg9 = summation2 / 100000.0;
					avg9 *= 100;
					avg9 = (int) avg9;
					avg9 /= 100;
				}
			}

			TableView table1 = new TableView();

			TableColumn<String, Row> column11 = new TableColumn<>("");
			column11.setCellValueFactory(new PropertyValueFactory<>("name"));

			TableColumn<String, Row> column22 = new TableColumn<>("100");
			column22.setCellValueFactory(new PropertyValueFactory<>("avg1"));

			TableColumn<String, Row> column33 = new TableColumn<>("1000");
			column33.setCellValueFactory(new PropertyValueFactory<>("avg2"));

			TableColumn<String, Row> column44 = new TableColumn<>("10000");
			column44.setCellValueFactory(new PropertyValueFactory<>("avg3"));

			TableColumn<String, Row> column55 = new TableColumn<>("100000");
			column55.setCellValueFactory(new PropertyValueFactory<>("avg4"));

			table1.getColumns().add(column11);
			table1.getColumns().add(column22);
			table1.getColumns().add(column33);
			table1.getColumns().add(column44);
			table1.getColumns().add(column55);

			table1.getItems().add(new Row("ATT", avg1, avg2, avg3, avg4));
			table1.getItems().add(new Row("AWT", avg6, avg7, avg8, avg9));
			VBox vbox = new VBox(table1);
			Scene sceneSim = new Scene(vbox, 500, 320);
			Stage stageSim = new Stage();
			stageSim.setScene(sceneSim);
			stageSim.setTitle(alg.getValue() + " Statistics");
			stageSim.getIcons().add(new Image("file:ico.png"));
			stageSim.show();
		});

		b5.setOnAction(e -> {
			try {
				TableView table = new TableView();
				TableView table1 = new TableView();

				TableColumn<String, Row> column0 = new TableColumn<>("Algorithm");
				column0.setCellValueFactory(new PropertyValueFactory<>("algorithm"));

				TableColumn<String, Row> column1 = new TableColumn<>("Average");
				column1.setCellValueFactory(new PropertyValueFactory<>("name"));

				TableColumn<String, Row> column2 = new TableColumn<>("100");
				column2.setCellValueFactory(new PropertyValueFactory<>("avg1"));

				TableColumn<String, Row> column3 = new TableColumn<>("1000");
				column3.setCellValueFactory(new PropertyValueFactory<>("avg2"));

				TableColumn<String, Row> column4 = new TableColumn<>("10000");
				column4.setCellValueFactory(new PropertyValueFactory<>("avg3"));

				TableColumn<String, Row> column5 = new TableColumn<>("100000");
				column5.setCellValueFactory(new PropertyValueFactory<>("avg4"));

				TableColumn<String, Row> column00 = new TableColumn<>("Algorithm");
				column00.setCellValueFactory(new PropertyValueFactory<>("algorithm"));

				TableColumn<String, Row> column11 = new TableColumn<>("Average");
				column11.setCellValueFactory(new PropertyValueFactory<>("name"));

				TableColumn<String, Row> column22 = new TableColumn<>("100");
				column22.setCellValueFactory(new PropertyValueFactory<>("avg1"));

				TableColumn<String, Row> column33 = new TableColumn<>("1000");
				column33.setCellValueFactory(new PropertyValueFactory<>("avg2"));

				TableColumn<String, Row> column44 = new TableColumn<>("10000");
				column44.setCellValueFactory(new PropertyValueFactory<>("avg3"));

				TableColumn<String, Row> column55 = new TableColumn<>("100000");
				column55.setCellValueFactory(new PropertyValueFactory<>("avg4"));

				table.getColumns().add(column0);
				table.getColumns().add(column1);
				table.getColumns().add(column2);
				table.getColumns().add(column3);
				table.getColumns().add(column4);
				table.getColumns().add(column5);

				table1.getColumns().add(column00);
				table1.getColumns().add(column11);
				table1.getColumns().add(column22);
				table1.getColumns().add(column33);
				table1.getColumns().add(column44);
				table1.getColumns().add(column55);

				int sum1 = 0, summation1 = 0, sum2 = 0, summation2 = 0, summation3 = 0, summation4 = 0, summation5 = 0, summation6 = 0, summation7 = 0, summation8 = 0;
				double avg = 0, avg1 = 0, avg2 = 0, avg3 = 0, avg4 = 0, avg5 = 0, avg6 = 0, avg7 = 0, avg8 = 0,avg9 = 0;
				Row row1=new Row();
				Row row2=new Row();
				Row row3=new Row();
				Row row4=new Row();
				Row row5=new Row();
				Row row6=new Row();
				Row row7=new Row();
				Row row8=new Row();
				for (int i = 0; i < 100000; ++i) {
					schedular = new Scheduler();
					
					schedular.FCFS();
					sum1 = 0;
					sum2 = 0;
					for (int j = 0; j < 12; ++j) {
						sum1 += schedular.a[j].getTurnTime();
						sum2 += schedular.a[j].getWaitTime();
					}
					avg = sum1 / 12.0;
					avg5 = sum2 / 12.0;
					summation1 += avg;
					summation2 += avg5;
					
					
					schedular.SJF();
					sum1 = 0;
					sum2 = 0;
					for (int j = 0; j < 12; ++j) {
						sum1 += schedular.a[j].getTurnTime();
						sum2 += schedular.a[j].getWaitTime();
					}
					avg = sum1 / 12.0;
					avg5 = sum2 / 12.0;
					summation3 += avg;
					summation4 += avg5;
					
					schedular.RR();
					sum1 = 0;
					sum2 = 0;
					for (int j = 0; j < 12; ++j) {
						sum1 += schedular.a[j].getTurnTime();
						sum2 += schedular.a[j].getWaitTime();
					}
					avg = sum1 / 12.0;
					avg5 = sum2 / 12.0;
					summation5 += avg;
					summation6 += avg5;
					
					schedular.MFQ();
					sum1 = 0;
					sum2 = 0;
					for (int j = 0; j < 12; ++j) {
						sum1 += schedular.a[j].getTurnTime();
						sum2 += schedular.a[j].getWaitTime();
					}
					avg = sum1 / 12.0;
					avg5 = sum2 / 12.0;
					summation7 += avg;
					summation8 += avg5;	
					if (i == 99) {
						avg1 = summation1 / 100.0;
						avg1 *= 100;
						avg1 = (int) avg1;
						avg1 /= 100;
						avg6 = summation2 / 100.0;
						avg6 *= 100;
						avg6 = (int) avg6;
						avg6 /= 100;
						row1.setAvg1(avg1);
						row2.setAvg1(avg6);
						avg1 = summation3 / 100.0;
						avg1 *= 100;
						avg1 = (int) avg1;
						avg1 /= 100;
						avg6 = summation4 / 100.0;
						avg6 *= 100;
						avg6 = (int) avg6;
						avg6 /= 100;
						row3.setAvg1(avg1);
						row4.setAvg1(avg6);
						avg1 = summation5 / 100.0;
						avg1 *= 100;
						avg1 = (int) avg1;
						avg1 /= 100;
						avg6 = summation6 / 100.0;
						avg6 *= 100;
						avg6 = (int) avg6;
						avg6 /= 100;
						row5.setAvg1(avg1);
						row6.setAvg1(avg6);
						avg1 = summation7 / 100.0;
						avg1 *= 100;
						avg1 = (int) avg1;
						avg1 /= 100;
						avg6 = summation8 / 100.0;
						avg6 *= 100;
						avg6 = (int) avg6;
						avg6 /= 100;
						row7.setAvg1(avg1);
						row8.setAvg1(avg6);
					}
					if (i == 999) {
						avg2 = summation1 / 1000.0;
						avg2 *= 100;
						avg2 = (int) avg2;
						avg2 /= 100;
						avg7 = summation2 / 1000.0;
						avg7 *= 100;
						avg7 = (int) avg7;
						avg7 /= 100;
						row1.setAvg2(avg2);
						row2.setAvg2(avg7);
						avg2 = summation3 / 1000.0;
						avg2 *= 100;
						avg2 = (int) avg2;
						avg2 /= 100;
						avg7 = summation4 / 1000.0;
						avg7 *= 100;
						avg7 = (int) avg7;
						avg7 /= 100;
						row3.setAvg2(avg2);
						row4.setAvg2(avg7);
						avg2 = summation5 / 1000.0;
						avg2 *= 100;
						avg2 = (int) avg2;
						avg2 /= 100;
						avg7 = summation6 / 1000.0;
						avg7 *= 100;
						avg7 = (int) avg7;
						avg7 /= 100;
						row5.setAvg2(avg2);
						row6.setAvg2(avg7);
						avg2 = summation7 / 1000.0;
						avg2 *= 100;
						avg2 = (int) avg2;
						avg2 /= 100;
						avg7 = summation8 / 1000.0;
						avg7 *= 100;
						avg7 = (int) avg7;
						avg7 /= 100;
						row7.setAvg2(avg2);
						row8.setAvg2(avg7);
					}
					if (i == 9999) {
						avg3 = summation1 / 10000.0;
						avg3 *= 100;
						avg3 = (int) avg3;
						avg3 /= 100;
						avg8 = summation2 / 10000.0;
						avg8 *= 100;
						avg8 = (int) avg8;
						avg8 /= 100;
						row1.setAvg3(avg3);
						row2.setAvg3(avg8);
						avg3 = summation3 / 10000.0;
						avg3 *= 100;
						avg3 = (int) avg3;
						avg3 /= 100;
						avg8 = summation4 / 10000.0;
						avg8 *= 100;
						avg8 = (int) avg8;
						avg8 /= 100;
						row3.setAvg3(avg3);
						row4.setAvg3(avg8);
						avg3 = summation5 / 10000.0;
						avg3 *= 100;
						avg3 = (int) avg3;
						avg3 /= 100;
						avg8 = summation6 / 10000.0;
						avg8 *= 100;
						avg8 = (int) avg8;
						avg8 /= 100;
						row5.setAvg3(avg3);
						row6.setAvg3(avg8);
						avg3 = summation7 / 10000.0;
						avg3 *= 100;
						avg3 = (int) avg3;
						avg3 /= 100;
						avg8 = summation8 / 10000.0;
						avg8 *= 100;
						avg8 = (int) avg8;
						avg8 /= 100;
						row7.setAvg3(avg3);
						row8.setAvg3(avg8);	
					}
					if (i == 99999) {
						avg4 = summation1 / 100000.0;
						avg4 *= 100;
						avg4 = (int) avg4;
						avg4 /= 100;
						avg9 = summation2 / 100000.0;
						avg9 *= 100;
						avg9 = (int) avg9;
						avg9 /= 100;
						row1.setAvg4(avg4);
						row2.setAvg4(avg9);	
						avg4 = summation3 / 100000.0;
						avg4 *= 100;
						avg4 = (int) avg4;
						avg4 /= 100;
						avg9 = summation4 / 100000.0;
						avg9 *= 100;
						avg9 = (int) avg9;
						avg9 /= 100;
						row3.setAvg4(avg4);
						row4.setAvg4(avg9);	
						avg4 = summation5 / 100000.0;
						avg4 *= 100;
						avg4 = (int) avg4;
						avg4 /= 100;
						avg9 = summation6 / 100000.0;
						avg9 *= 100;
						avg9 = (int) avg9;
						avg9 /= 100;
						row5.setAvg4(avg4);
						row6.setAvg4(avg9);	
						avg4 = summation7 / 100000.0;
						avg4 *= 100;
						avg4 = (int) avg4;
						avg4 /= 100;
						avg9 = summation8 / 100000.0;
						avg9 *= 100;
						avg9 = (int) avg9;
						avg9 /= 100;
						row7.setAvg4(avg4);
						row8.setAvg4(avg9);	
					}
					row1.setAlgorithm("FCFS");
					row2.setAlgorithm("FCFS");
					row3.setAlgorithm("SJF");
					row4.setAlgorithm("SJF");
					row5.setAlgorithm("RR");
					row6.setAlgorithm("RR");
					row7.setAlgorithm("MFQ");
					row8.setAlgorithm("MFQ");
					row1.setName("ATT");
					row2.setName("AWT");
					row3.setName("ATT");
					row4.setName("AWT");
					row5.setName("ATT");
					row6.setName("AWT");
					row7.setName("ATT");
					row8.setName("AWT");
				}

				table.getItems().add(row1);
				table.getItems().add(row3);
				table.getItems().add(row5);
				table.getItems().add(row7);

				table1.getItems().add(row2);
				table1.getItems().add(row4);
				table1.getItems().add(row6);
				table1.getItems().add(row8);

				// *******************************
				Button button = new Button("                 Average Waiting Time ---->           ");
				button.setStyle(
						"-fx-font-size: 18.0pt;-fx-background-color: linear-gradient(#2B1EA0, #0B7EA0);-fx-text-fill: #FFFF");
				Text t = new Text("Average Turnaround Time");
				t.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
				t.setFill(Color.WHITE);
				HBox h = new HBox(t);
				VBox vbox = new VBox(table, button);
				Scene sceneSim = new Scene(vbox, 500, 320);
				Stage stageSim = new Stage();
				stageSim.setScene(sceneSim);
				stageSim.setTitle("Statistics of all Algorithms");
				stageSim.getIcons().add(new Image("file:ico.png"));
				stageSim.setResizable(false);
				Button button1 = new Button("           <--- Average Turnaround Time            ");
				button1.setStyle(
						"-fx-font-size: 18.0pt;-fx-background-color: linear-gradient(#2B1EA0, #0B7EA0);-fx-text-fill: #FFFF");
				VBox vbox1 = new VBox(table1, button1);
				Scene sceneSim1 = new Scene(vbox1, 500, 320);
				Stage stageSim1 = new Stage();
				stageSim1.setScene(sceneSim1);
				stageSim1.setTitle("Statistics of all Algorithms");
				stageSim1.getIcons().add(new Image("file:ico.png"));
				stageSim1.setResizable(false);
				stageSim.show();
				button.setOnAction(e2 -> {
					stageSim.hide();
					stageSim1.show();
				});
				button1.setOnAction(e2 -> {
					stageSim1.hide();
					stageSim.show();
				});
			} catch (Exception ex) {
			}
		});

		b4.setOnAction(e -> {
			primaryStage.close();
		});
		
		b6.setOnAction(e->{
			try {
		        XYChart.Series series1 = new XYChart.Series();
		        series1.setName("FCFS");     
		        XYChart.Series series2 = new XYChart.Series();
		        series2.setName("SJF");       
		        XYChart.Series series3 = new XYChart.Series();
		        series3.setName("RR"); 
		        XYChart.Series series4 = new XYChart.Series();
		        series4.setName("MFQ"); 
		        XYChart.Series series11 = new XYChart.Series();
		        series11.setName("FCFS");     
		        XYChart.Series series12 = new XYChart.Series();
		        series12.setName("SJF");       
		        XYChart.Series series13 = new XYChart.Series();
		        series13.setName("RR"); 
		        XYChart.Series series14 = new XYChart.Series();
		        series14.setName("MFQ"); 

				int sum1 = 0, summation1 = 0, sum2 = 0, summation2 = 0, summation3 = 0, summation4 = 0, summation5 = 0, summation6 = 0, summation7 = 0, summation8 = 0;
				double avg = 0, avg1 = 0, avg2 = 0, avg3 = 0, avg4 = 0, avg5 = 0, avg6 = 0, avg7 = 0, avg8 = 0,avg9 = 0;
				for (int i = 0; i < 100000; ++i) {
					schedular = new Scheduler();
					
					schedular.FCFS();
					sum1 = 0;
					sum2 = 0;
					for (int j = 0; j < 12; ++j) {
						sum1 += schedular.a[j].getTurnTime();
						sum2 += schedular.a[j].getWaitTime();
					}
					avg = sum1 / 12.0;
					avg5 = sum2 / 12.0;
					summation1 += avg;
					summation2 += avg5;
					
					
					schedular.SJF();
					sum1 = 0;
					sum2 = 0;
					for (int j = 0; j < 12; ++j) {
						sum1 += schedular.a[j].getTurnTime();
						sum2 += schedular.a[j].getWaitTime();
					}
					avg = sum1 / 12.0;
					avg5 = sum2 / 12.0;
					summation3 += avg;
					summation4 += avg5;
					
					schedular.RR();
					sum1 = 0;
					sum2 = 0;
					for (int j = 0; j < 12; ++j) {
						sum1 += schedular.a[j].getTurnTime();
						sum2 += schedular.a[j].getWaitTime();
					}
					avg = sum1 / 12.0;
					avg5 = sum2 / 12.0;
					summation5 += avg;
					summation6 += avg5;
					
					schedular.MFQ();
					sum1 = 0;
					sum2 = 0;
					for (int j = 0; j < 12; ++j) {
						sum1 += schedular.a[j].getTurnTime();
						sum2 += schedular.a[j].getWaitTime();
					}
					avg = sum1 / 12.0;
					avg5 = sum2 / 12.0;
					summation7 += avg;
					summation8 += avg5;	
					if (i == 99) {
						avg1 = summation1 / 100.0;
						avg1 *= 100;
						avg1 = (int) avg1;
						avg1 /= 100;
						avg6 = summation2 / 100.0;
						avg6 *= 100;
						avg6 = (int) avg6;
						avg6 /= 100;
				        series1.getData().add(new XYChart.Data("100", avg1));
				        series11.getData().add(new XYChart.Data("100", avg6));
						avg1 = summation3 / 100.0;
						avg1 *= 100;
						avg1 = (int) avg1;
						avg1 /= 100;
						avg6 = summation4 / 100.0;
						avg6 *= 100;
						avg6 = (int) avg6;
						avg6 /= 100;
				        series2.getData().add(new XYChart.Data("100", avg1));
				        series12.getData().add(new XYChart.Data("100", avg6));
						avg1 = summation5 / 100.0;
						avg1 *= 100;
						avg1 = (int) avg1;
						avg1 /= 100;
						avg6 = summation6 / 100.0;
						avg6 *= 100;
						avg6 = (int) avg6;
						avg6 /= 100;
				        series3.getData().add(new XYChart.Data("100", avg1));
				        series13.getData().add(new XYChart.Data("100", avg6));
						avg1 = summation7 / 100.0;
						avg1 *= 100;
						avg1 = (int) avg1;
						avg1 /= 100;
						avg6 = summation8 / 100.0;
						avg6 *= 100;
						avg6 = (int) avg6;
						avg6 /= 100;
				        series4.getData().add(new XYChart.Data("100", avg1));
				        series14.getData().add(new XYChart.Data("100", avg6));
					}
					if (i == 999) {
						avg2 = summation1 / 1000.0;
						avg2 *= 100;
						avg2 = (int) avg2;
						avg2 /= 100;
						avg7 = summation2 / 1000.0;
						avg7 *= 100;
						avg7 = (int) avg7;
						avg7 /= 100;
				        series1.getData().add(new XYChart.Data("1000", avg2));
				        series11.getData().add(new XYChart.Data("1000", avg7));
						avg2 = summation3 / 1000.0;
						avg2 *= 100;
						avg2 = (int) avg2;
						avg2 /= 100;
						avg7 = summation4 / 1000.0;
						avg7 *= 100;
						avg7 = (int) avg7;
						avg7 /= 100;
				        series2.getData().add(new XYChart.Data("1000", avg2));
				        series12.getData().add(new XYChart.Data("1000", avg7));
						avg2 = summation5 / 1000.0;
						avg2 *= 100;
						avg2 = (int) avg2;
						avg2 /= 100;
						avg7 = summation6 / 1000.0;
						avg7 *= 100;
						avg7 = (int) avg7;
						avg7 /= 100;
				        series3.getData().add(new XYChart.Data("1000", avg2));
				        series13.getData().add(new XYChart.Data("1000", avg7));
						avg2 = summation7 / 1000.0;
						avg2 *= 100;
						avg2 = (int) avg2;
						avg2 /= 100;
						avg7 = summation8 / 1000.0;
						avg7 *= 100;
						avg7 = (int) avg7;
						avg7 /= 100;
				        series4.getData().add(new XYChart.Data("1000", avg2));
				        series14.getData().add(new XYChart.Data("1000", avg7));
					}
					if (i == 9999) {
						avg3 = summation1 / 10000.0;
						avg3 *= 100;
						avg3 = (int) avg3;
						avg3 /= 100;
						avg8 = summation2 / 10000.0;
						avg8 *= 100;
						avg8 = (int) avg8;
						avg8 /= 100;
				        series1.getData().add(new XYChart.Data("10000", avg3));
				        series11.getData().add(new XYChart.Data("10000", avg8));
						avg3 = summation3 / 10000.0;
						avg3 *= 100;
						avg3 = (int) avg3;
						avg3 /= 100;
						avg8 = summation4 / 10000.0;
						avg8 *= 100;
						avg8 = (int) avg8;
						avg8 /= 100;
				        series2.getData().add(new XYChart.Data("10000", avg3));
				        series12.getData().add(new XYChart.Data("10000", avg8));
						avg3 = summation5 / 10000.0;
						avg3 *= 100;
						avg3 = (int) avg3;
						avg3 /= 100;
						avg8 = summation6 / 10000.0;
						avg8 *= 100;
						avg8 = (int) avg8;
						avg8 /= 100;
				        series3.getData().add(new XYChart.Data("10000", avg3));
				        series13.getData().add(new XYChart.Data("10000", avg8));
						avg3 = summation7 / 10000.0;
						avg3 *= 100;
						avg3 = (int) avg3;
						avg3 /= 100;
						avg8 = summation8 / 10000.0;
						avg8 *= 100;
						avg8 = (int) avg8;
						avg8 /= 100;
				        series4.getData().add(new XYChart.Data("10000", avg3));
				        series14.getData().add(new XYChart.Data("10000", avg8));
					}
					if (i == 99999) {
						avg4 = summation1 / 100000.0;
						avg4 *= 100;
						avg4 = (int) avg4;
						avg4 /= 100;
						avg9 = summation2 / 100000.0;
						avg9 *= 100;
						avg9 = (int) avg9;
						avg9 /= 100;
				        series1.getData().add(new XYChart.Data("100000", avg4));
				        series11.getData().add(new XYChart.Data("100000", avg9));
						avg4 = summation3 / 100000.0;
						avg4 *= 100;
						avg4 = (int) avg4;
						avg4 /= 100;
						avg9 = summation4 / 100000.0;
						avg9 *= 100;
						avg9 = (int) avg9;
						avg9 /= 100;
				        series2.getData().add(new XYChart.Data("100000", avg4));
				        series12.getData().add(new XYChart.Data("100000", avg9));
						avg4 = summation5 / 100000.0;
						avg4 *= 100;
						avg4 = (int) avg4;
						avg4 /= 100;
						avg9 = summation6 / 100000.0;
						avg9 *= 100;
						avg9 = (int) avg9;
						avg9 /= 100;
				        series3.getData().add(new XYChart.Data("100000", avg4));
				        series13.getData().add(new XYChart.Data("100000", avg9));
						avg4 = summation7 / 100000.0;
						avg4 *= 100;
						avg4 = (int) avg4;
						avg4 /= 100;
						avg9 = summation8 / 100000.0;
						avg9 *= 100;
						avg9 = (int) avg9;
						avg9 /= 100;
				        series4.getData().add(new XYChart.Data("100000", avg4));
				        series14.getData().add(new XYChart.Data("100000", avg9));
					}
				}				
				//******************************
				CategoryAxis xAxis = new CategoryAxis();
		        NumberAxis yAxis = new NumberAxis();
		        BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
		        bc.setTitle("Average Turnaround Time");
		        xAxis.setLabel("No. of Iterations for 12 Processes");       
		        yAxis.setLabel("Units of Time");
		        yAxis.setMaxHeight(4000);
		        CategoryAxis xAxis1 = new CategoryAxis();
		        NumberAxis yAxis1 = new NumberAxis();
		        BarChart<String,Number> bc1 = new BarChart<String,Number>(xAxis1,yAxis1);
		        bc1.setTitle("Average Waiting Time");
		        xAxis1.setLabel("No. of Iterations for 12 Processes");       
		        yAxis1.setLabel("Units of Time");
		        yAxis1.setTickUnit(2000);
				//********************************
		        Button button = new Button("                 Average Waiting Time ---->           ");
				button.setStyle(
						"-fx-font-size: 18.0pt;-fx-background-color: linear-gradient(#2B1EA0, #0B7EA0);-fx-text-fill: #FFFF");
				Text t = new Text("Average Turnaround Time");
				t.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
				t.setFill(Color.WHITE);
				HBox h = new HBox(t);
				VBox vbox = new VBox(bc, button);
		        bc.getData().addAll(series2, series1, series4,series3);
				Scene sceneSim = new Scene(vbox, 500, 320);
				Stage stageSim = new Stage();
				stageSim.setScene(sceneSim);
				stageSim.setTitle("Bar Chart of all Algorithms");
				stageSim.getIcons().add(new Image("file:ico.png"));
				stageSim.setResizable(false);
				Button button1 = new Button("           <--- Average Turnaround Time            ");
				button1.setStyle(
						"-fx-font-size: 18.0pt;-fx-background-color: linear-gradient(#2B1EA0, #0B7EA0);-fx-text-fill: #FFFF");
				VBox vbox1 = new VBox(bc1, button1);
		        bc1.getData().addAll(series12, series11, series14,series13);
				Scene sceneSim1 = new Scene(vbox1, 500, 320);
				Stage stageSim1 = new Stage();
				stageSim1.setScene(sceneSim1);
				stageSim1.setTitle("Bar Chart of all Algorithms");
				stageSim1.getIcons().add(new Image("file:ico.png"));
				stageSim1.setResizable(false);
				stageSim.show();
				button.setOnAction(e2 -> {
					stageSim.hide();
					stageSim1.show();
				});
				button1.setOnAction(e2 -> {
					stageSim1.hide();
					stageSim.show();
				});
			} catch (Exception ex) {
			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}
