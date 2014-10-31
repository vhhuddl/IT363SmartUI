
public class Appliance {
	
	private String name;
	private String roomName;
	private String[] partNum;
	private int[] totalWatts;
	private int powerSetting;
	private double[] cost;
	private double totalWattsUsed = 0;
	private double individualCost = 0;
	
	public double getIndividualCost() {
		return individualCost;
	}
	public void setIndividualCost(double individualCost) {
		this.individualCost = individualCost;
	}
	public Appliance (String name, String[] partNum) {
		this.name = name;
		this.partNum = partNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getPartNum() {
		return partNum;
	}
	public void setPartNum(String[] partNum) {
		this.partNum = partNum;
	}
	public int[] getTotalWatts() {
		return totalWatts;
	}
	public void setTotalWatts(int[] totalWatts) {
		this.totalWatts = totalWatts;
	}
	public int getPowerSetting() {
		return powerSetting;
	}
	public void setPowerSetting(int powerSetting) {
		this.powerSetting = powerSetting;
	}
	public double[] getCost() {
		return cost;
	}
	public void setCost(double[] cost) {
		this.cost = cost;
	}
	public double getTotalWattsUsed() {
		return totalWattsUsed;
	}
	public void setTotalWattsUsed(double totalWattsUsed) {
		this.totalWattsUsed = totalWattsUsed;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
}
