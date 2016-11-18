package Assignment3;

public class FoodItem {
	
	private String name;
	private double volume;
	private double weight;

	public FoodItem(String name, double volume, double weight){
		this.name = name;
		this.volume = volume;
		this.weight = weight;

	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getVolume() {
		return volume;
	}
	
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
