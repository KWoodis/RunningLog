package application;
	
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;

import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Collection;
import java.util.Comparator;
import java.io.File;

public class RunningLogFinalProject extends Application {
	
	//objects used by project including text fields, listview, main arraylist for sorting, observable list to pass to listfiew
	//file csv, decimal formatter
	
	
	TextField name = new TextField();
	TextField distance = new TextField();
	TextField minutes = new TextField();
	ListView<String> listview;
	ArrayList<RunnerClass> aL = new ArrayList<>();
	ObservableList oL = FXCollections.observableArrayList();
	java.io.File file = new java.io.File("finalProjectRunners.txt");
	DecimalFormat formatter = new DecimalFormat("###.0");
	
	public static void main(String[] args) {
		launch(args);         //all string methods from start passed to launch as arguments
	}
	
	@Override
	public void start(Stage stage) {
		
		//create root node. Scene object created. Event Handler class object created
		Group rootN = new Group();
		Scene scene = new Scene(rootN, Color.BLANCHEDALMOND);


		
		
		//create image icon
		Image iconImg = new Image("SmoleEmote.png");
		stage.getIcons().add(iconImg);
		
		//create and set title
		stage.setTitle("Running Log");
		//set the scene to the stage
		stage.setScene(scene);
		
		//set stage parameters
		stage.setWidth(900);
		stage.setHeight(600);
		
		/*-------------------Section for child nodes from root----------------------------------------------------------------------------*/
		
		//Text node
		Text txt = new Text();
		txt.setText("Welcome to your Running Log");
		
		txt.setX(50);
		txt.setY(50);
		txt.setFont(Font.font("Lucida Calligraphy",20));
		txt.setFill(Color.BLACK);
		txt.setStrokeWidth(0.5);
		txt.setStroke(Color.FUCHSIA);
		
		rootN.getChildren().add(txt); 
		
		//create labels for text fields and place them
		Label label1 = new Label("Runner's Name");
		label1.setLayoutX(50);
		label1.setLayoutY(120);
		label1.setFont(Font.font("Comic Sans",14));

		rootN.getChildren().add(label1); 

		
		Label label2 = new Label("Runner's distance (miles)");
		label2.setLayoutX(50);
		label2.setLayoutY(170);
		label2.setFont(Font.font("Comic Sans",14));

		rootN.getChildren().add(label2); 

		Label label3 = new Label("Time (minutes)");
		label3.setLayoutX(50);
		label3.setLayoutY(220);
		label3.setFont(Font.font("Comic Sans",14));

		rootN.getChildren().add(label3); 
		

		//place textfields
		name.setLayoutX(250);
		name.setLayoutY(120);
		distance.setLayoutX(250);
		distance.setLayoutY(170);
		minutes.setLayoutX(250);
		minutes.setLayoutY(220);
		
		rootN.getChildren().addAll(name, distance, minutes);
		
		

/*---------------------The Add Runner to List button control----------------------------------------------------------------*/
		
		
		//create buttons and place
		Button addButton = new Button();
		addButton.setText("Add run to list");
		addButton.setLayoutX(250);
		addButton.setLayoutY(270);
		addButton.setOnAction(e -> {
			
			//parse text fields
			String n = name.getText();
			String dis = distance.getText();
			String min = minutes.getText();
			
			//create new runner object and pass text fields as arguments
			RunnerClass runner = new RunnerClass(n, dis, min);
			
			//add the runner to main list
			aL.add(runner);

			
			//ObservableList oL = FXCollections.observableArrayList();
			//oL.add(runner.display());

			
			///pass oL to listview
			listview.getItems().add(runner.display());
			
		});
		
		
		
/*---------------------------------		//create radio buttons and place-----------------------------------*/
	
		
		RadioButton rButton1 = new RadioButton("Name");
		RadioButton rButton2 = new RadioButton("Distance");
		RadioButton rButton3 = new RadioButton("Time");
		RadioButton rButton4 = new RadioButton("Pace");

		ToggleGroup radioGroup = new ToggleGroup();
		rButton1.setToggleGroup(radioGroup);
		rButton2.setToggleGroup(radioGroup);
		rButton3.setToggleGroup(radioGroup);
		rButton4.setToggleGroup(radioGroup);

		//place radio buttons
		rButton1.setLayoutX(50);
		rButton1.setLayoutY(380);
		rButton2.setLayoutX(50);
		rButton2.setLayoutY(410);
		rButton3.setLayoutX(50);
		rButton3.setLayoutY(440);
		rButton4.setLayoutX(50);
		rButton4.setLayoutY(470);
		
		rootN.getChildren().addAll(rButton1, rButton2, rButton3, rButton4);
		
		
/*--------------------------------------The sort button section with button creation and actions---------------------------*/
		
		
		Button sortB = new Button("Sort by: ");
		sortB.setLayoutX(50);
		sortB.setLayoutY(330);
		
		//sort action event handling
		sortB.setOnAction(e -> {
			
			//clear the listview and observable list because the arraylist is about to be re-sorted and repassed to oL to display
			//in listview again
			
			listview.getItems().clear();
			oL.clear();
			
			//OVERRIDE COMPARE METHOD FOR STRING NAME PROPERTY FOR RUNNER OBJECTS FOR SORTING IN ASCENDING ORDER
			if(rButton1.isSelected()) {
				
				Collections.sort(aL, new Comparator<RunnerClass>()
				{
					@Override
					public int compare(RunnerClass r1, RunnerClass r2)
					{
						return r1.getName().compareTo(r2.getName());
					}
				});
			}
			//OVERRIDE COMPARE METHOD FOR DISTANCE PROPERTY FOR RUNNER OBJECTS FOR SORTING IN DESCENDING ORDER
			//OVERRIDE FOR COMPARE METHOD INSTEAD OF COMPARETO
			if(rButton2.isSelected()) {
				
				Collections.sort(aL, new Comparator<RunnerClass>() 
						
				{
					@Override
					public int compare(RunnerClass r1, RunnerClass r2)
					{
						return Double.compare(r2.getDistance(), r1.getDistance());
					}
				});
									
						
			}
			//OVERRIDE COMPARE METHOD FOR TIME PROPERTY FOR RUNNER OBJECTS FOR SORTING IN ASCENDING ORDER
			if(rButton3.isSelected()) {
				
				Collections.sort(aL, (r1, r2) -> Double.compare(r1.getTime(), r2.getTime()));
			}
			
			//OVERRIDE COMPARE METHOD FOR PACE PROPERTY FOR RUNNER OBJECTS FOR SORTING IN ASCENDING ORDER
			if(rButton4.isSelected()) {
				
				Collections.sort(aL, (r1, r2) -> Double.compare(r1.getPace(), r2.getPace()));

			}

			

			
			
			//after array is sorted, re-add names to observable list and place in listview
			for(int i=0; i < aL.size(); i++) {
				oL.add(aL.get(i).display());
				
				
			}
			
			listview.getItems().addAll(oL);

			
			
		});
		
		rootN.getChildren().addAll(addButton, sortB);
		

		//create listview and place
		listview = new ListView<>();
		listview.setBorder(new Border(new BorderStroke(Color.FUCHSIA, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		listview.setLayoutX(450);
		listview.setLayoutY(50);
		listview.setPrefSize(400,400);
		



		rootN.getChildren().add(listview);
		
		//create labels for listview and place
		Label label4 = new Label("Name");
		label4.setLayoutX(450);
		label4.setLayoutY(30);
		label4.setFont(Font.font("Comic Sans",12));
		
		Label label5 = new Label("Distance(miles)");
		label5.setLayoutX(560);
		label5.setLayoutY(30);
		label5.setFont(Font.font("Comic Sans",12));
		
		Label label6 = new Label("Time(minutes)");
		label6.setLayoutX(670);
		label6.setLayoutY(30);
		label6.setFont(Font.font("Comic Sans",12));
		
		Label label7 = new Label("Avg Pace(minutes)");
		label7.setLayoutX(780);
		label7.setLayoutY(30);
		label7.setFont(Font.font("Comic Sans",12));

		

		rootN.getChildren().addAll(label4, label5, label6, label7); 
		
/*-------------------------		create and place CSV button----------------------------*/
		
		
		Button csv = new Button("Save list to CSV");

		csv.setOnAction(e -> {
			
			try {
				
				//create writer object
				java.io.PrintWriter writer = new java.io.PrintWriter(file);
				
				for (int i = 0; i < aL.size(); i++) {
						
					//data as variables
					String name = aL.get(i).getName();
					double distance = aL.get(i).getDistance();
					double time = aL.get(i).getTime();
					double pace = aL.get(i).getPace();
					
						
					writer.println(name + "," + formatter.format(distance) + ","+ formatter.format(time) + ","+ formatter.format(pace));
					
				}
				
				writer.close();
				}
			catch(IOException ex) {

				ex.printStackTrace();
			}
		});
		csv.setLayoutX(600);
		csv.setLayoutY(470);
		
		rootN.getChildren().addAll(csv);
		
		
		
		
		
		
		
		stage.show();    //shows the stage 
	}
	



	

	

}
