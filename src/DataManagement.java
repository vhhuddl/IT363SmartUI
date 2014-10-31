import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class DataManagement {

	private ArrayList<Appliance> appliances;
	private Appliance appliance;
	private String testString;
	private String roomName;
	private String appName;
	private String[] partNums;
	private int[] totalWatts;
	private double[] costs;
	private int powerSetting;

	public ArrayList<Appliance> getAppliances() {
		return appliances;
	}

	public void setAppliances(ArrayList<Appliance> appliances) {
		this.appliances = appliances;
	}

	public void serial() throws IOException {
		
		appliances = new ArrayList<Appliance>();
		String[] powerSettings = new String[] { "0", "1", "2", "3" };
		Random random = new Random();
		File applianceFile = new File("appliances.txt");
		Scanner fileReader = new Scanner(applianceFile);
		int numAppliances = fileReader.nextInt();
		int numSettings = fileReader.nextInt();
		fileReader.nextLine();
		
		partNums = new String[numSettings];
		totalWatts = new int[numSettings];
		costs = new double[numSettings];
		
		for (int i = 0; i < numAppliances; i++) {
			fileReader.nextLine();
			testString = fileReader.nextLine();
			if (testString.equalsIgnoreCase("Kitchen") || testString.equalsIgnoreCase("Bathroom/Bedroom") || testString.equalsIgnoreCase("Utility Room")) {
				roomName = testString;
				appName = fileReader.nextLine();
			}
			else {
				appName = testString;
			}
			for (int j = 0; j < numSettings; j++) {
				String partNum = fileReader.next();
				partNums[j] = partNum;
			}
			appliance = new Appliance(appName, partNums);
			appliance.setRoomName(roomName);
			for (int k = 0; k < numSettings; k++) {
				int watt = Integer.parseInt(fileReader.next());
				totalWatts[k] = watt;
			}
			appliance.setTotalWatts(totalWatts);
			
			for (int l = 0; l < numSettings; l++) {
				double cost = Double.parseDouble(fileReader.next());
				costs[l] = cost;
			}
			appliance.setCost(costs);
			
			powerSetting = Integer.parseInt(powerSettings[random.nextInt(powerSettings.length)]);
			
			appliance.setPowerSetting(powerSetting);
			
			appliances.add(appliance);
			
		}
		
	    //String device_List = "We don't have any data yet";
	    //loop through number of devices
	    //0 = standard meter
	    //1 = smart meter
	    //1 = low 50-110
	    //2 = medium 250-450
	    //3 = high > 600
	    
	                    //device_list+=random(0,1,2,3)
	
	    //return device_List;
	}
}
