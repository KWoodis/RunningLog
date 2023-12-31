package application;
import java.text.DecimalFormat;
import java.util.Scanner;

public class RunnerClass {
	
	private String name, distance, time;
	private double avgPace = 0.0;
	DecimalFormat formatter = new DecimalFormat("###.00");
	
	//no arg constructor
	public RunnerClass() {
		
	}
	//argument constructor
	public RunnerClass(String nameNew, String d, String t) {
		name = nameNew;
		distance = d;
		time = t;
	}
	
	//Getter/setter methods
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getDistance() {
		
		double d = Double.parseDouble(distance);
		
		return d;
	}
	
	public void setDistance(String d) {
		
		this.distance = d;
	}
	
	public double getTime() {
		
		double t = Double.parseDouble(time);
		return t;
	}
	
	public void setTime(String t) {
		
		this.time = t;
	}
	
	public double getPace() {
		
				
		return avgPace;
	}


	
	public double getAvgPace() {
		
		calculateAvgPace(distance,time);
		
		
		return avgPace;
	}
	
	public void calculateAvgPace(String d, String t) {
		
		double distance = Double.parseDouble(d);
		double time = Double.parseDouble(t);
		
		avgPace = time/distance;
		
		
	}
	
	
	
	public String display() {
		
		String s = String.format("%-30s%-30.2f%-30.2f%-30.2f\n", name, getDistance(), getTime(), getAvgPace());
	
	    return s;
	
	}
	

}
