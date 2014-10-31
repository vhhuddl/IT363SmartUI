import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;

public class Simulation {
	
	private int length = 0;
	private int timeOfDay = 0000;
	private String ampm;
	private int delay = 1000;
	private int counter = 0;
	private double accumulatingTotalWattsUsed = 0;
	private double accumulatingCost = 0;
	private ArrayList<Appliance> appliances;
	private Timer timer;
	
	public Simulation() {
		super();
		timer = new Timer(delay, updateInfo);
	}
	
	public static void main (String[] args) throws IOException {
		DataManagement dao = new DataManagement();
		dao.serial();
	}
	
	ActionListener updateInfo = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (counter < length) {
				//Timer activity each second. 
				DataManagement dao = new DataManagement();
				try {
					dao.serial();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("DOH!");
				}
				appliances = dao.getAppliances();
				calculateRates(appliances);
				counter += 1;
				System.out.println(counter);
			} else {
				timer.stop();
				counter = 0;
			}
		}
	};
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getTimeOfDay() {
		return timeOfDay;
	}
	public void setTimeOfDay(int timeOfDay, String ampm) {
		this.timeOfDay = timeOfDay;
		this.ampm = ampm;
		
	}
	
	public void updateInfo() {
		//Info will be updated each second.
	}
	
	private void calculateRates(ArrayList<Appliance> appliances) {
		int powerSetting = 0;
		int accumulatingTotalWatts = 0;
		double costPerTwoMinutes = 0;
		double wattsPerTwoMinutes = 0;
		double individualCost = 0;
		double totalWattsUsed = 0;
		for (int i = 0; i < appliances.size(); i++) {
			powerSetting = appliances.get(i).getPowerSetting();
			individualCost = appliances.get(i).getIndividualCost();
			totalWattsUsed = appliances.get(i).getTotalWattsUsed();
			if (powerSetting == 1) {
				costPerTwoMinutes = appliances.get(i).getCost()[0] / 365 / 24 / 30;
				System.out.println(costPerTwoMinutes);
				individualCost += costPerTwoMinutes;
				accumulatingCost += individualCost;
				appliances.get(i).setIndividualCost(individualCost);
				accumulatingTotalWatts += appliances.get(i).getTotalWatts()[0];
				wattsPerTwoMinutes = appliances.get(i).getTotalWatts()[0] / 30;
				totalWattsUsed += wattsPerTwoMinutes;
				appliances.get(i).setTotalWattsUsed(totalWattsUsed);
				accumulatingTotalWattsUsed += totalWattsUsed;
			} else if (powerSetting == 2) {
				costPerTwoMinutes = appliances.get(i).getCost()[1] / 365 / 24 / 30;
				individualCost += costPerTwoMinutes;
				accumulatingCost += individualCost;
				appliances.get(i).setIndividualCost(individualCost);
				accumulatingTotalWatts += appliances.get(i).getTotalWatts()[1];
				wattsPerTwoMinutes = appliances.get(i).getTotalWatts()[1] / 30;
				totalWattsUsed += wattsPerTwoMinutes;
				appliances.get(i).setTotalWattsUsed(totalWattsUsed);
				accumulatingTotalWattsUsed += totalWattsUsed;
			} else if (powerSetting == 3) {
				costPerTwoMinutes = appliances.get(i).getCost()[2] / 365 / 24 / 30;
				individualCost += costPerTwoMinutes;
				accumulatingCost += individualCost;
				appliances.get(i).setIndividualCost(individualCost);
				accumulatingTotalWatts += appliances.get(i).getTotalWatts()[2];
				wattsPerTwoMinutes = appliances.get(i).getTotalWatts()[2] / 30;
				totalWattsUsed += wattsPerTwoMinutes;
				appliances.get(i).setTotalWattsUsed(totalWattsUsed);
				accumulatingTotalWattsUsed += totalWattsUsed;
			} else {
				accumulatingCost += 0;
				accumulatingTotalWatts += 0;
				accumulatingTotalWattsUsed += 0;
			}
			//UI Display Method
			testCalculations(individualCost, totalWattsUsed, accumulatingCost, accumulatingTotalWatts, accumulatingTotalWattsUsed);
		}
	}
	
	public static void testCalculations(double individualCost, double indTotalWattsUsed, double accumulatingCost, double accumulatingTotalWatts, double accumulatingTotalWattsUsed) {
		System.out.println("Fridge");
		System.out.println("Ind. Cost: " + individualCost);
		System.out.println("Ind. Total Watts Used: " + indTotalWattsUsed);
		System.out.println("Accumulating Costs of all Appliances: " + accumulatingCost);
		System.out.println("Total Watts of all active Appliances: " + accumulatingTotalWatts);
		System.out.println("Total Watts Used during the Simulation: " + accumulatingTotalWattsUsed);
	}
	
	public void start() {
		timer.start();
	}
	
	public void resume() {
		timer.start();
	}
	
	public void pause() {
		timer.stop();
	}
	
	public void reset() {
		counter = 0;
		timer.restart();
		timer.stop();
	}
	public ArrayList<Appliance> getAppliances() {
		return appliances;
	}
	public void setAppliances(ArrayList<Appliance> appliances) {
		this.appliances = appliances;
	}
	

}

