import java.util.*;


class Laptop {		//this class describes a Laptop
	static private int laptopCount;
	static private ArrayList<Laptop> laptopCollection = new ArrayList<>();
	private String laptopName;
	private String laptopModel;
	private Date laptopManufactureDate;
	private double laptopPrice;
	private ArrayList<String> laptopSpecList;
	
	//Getters and Setters
	public Laptop(String name, String model, Date manufactureDate, double price) {
		laptopName = name;
		laptopModel = model;
		laptopManufactureDate = manufactureDate;
		laptopPrice = price;
		laptopSpecList = LaptopHelper.generateLaptopSpec();
		laptopCount++;
	}
	
	public String getLaptopName() {
		return laptopName;
	}
	public String getLaptopModel() {
		return laptopModel;
	}
	public Date getLaptopManufactureDate() {
		return laptopManufactureDate;
	}
	public double getLaptopPrice() {
		return laptopPrice;
	}
	public ArrayList<String> getLaptopSpec() {
		return laptopSpecList;
	}
	static public int getNumberOfLaptops() {
		return laptopCount;
	}
	static public void addLaptop(Laptop newLaptop) {
		laptopCollection.add(newLaptop);
	}
	static public void removeLaptop(Laptop laptopToRemove) {
		laptopCollection.remove(laptopToRemove);
	}
}
	
class LaptopHelper {
	static public ArrayList<String> generateLaptopSpec() {
		Random rand = new Random();
		String[] processor = {"CPU: Core i3", "CPU: Core i5", "CPU: Core i7"};
		String[] ramSize = {"RAM: 4gb", "RAM: 8gb", "RAM: 16gb"};
		String[] storageSize = {"HDD: 500gb", "HDD: 1TB", "HDD: 2TB"};
		ArrayList<String[]> specHolderArray = new ArrayList<>();
		specHolderArray.add(processor);
		specHolderArray.add(ramSize);
		specHolderArray.add(storageSize);
		
		ArrayList<String> laptopSpecList = new ArrayList<>();
		Map<Integer, String> newMap = new HashMap<>();
		int index = 0;
		
		for (String[] specs : specHolderArray) {
			for (String s : specs ) {
				newMap.put(index, s);
				index++;
			}
			laptopSpecList.add(newMap.get(rand.nextInt(3)));
			index = 0;
		}
		return laptopSpecList;
	}
}

class LaptopManager {		//this class models the function of a laptop manager
	public static Laptop newLaptop(String name, String model, Date manufactureDate, double price) {
		Laptop newLaptop = new Laptop(name, model, manufactureDate, price);
		Laptop.addLaptop(newLaptop);
		System.out.println("Laptop count is now: " + Laptop.getNumberOfLaptops());
		System.out.printf("%s %s added!\n", name, model);
		LaptopManager.printLaptopSpec(newLaptop);
		return newLaptop;
	}
	static LaptopManager() {}
	
	public static void removeLaptop(Laptop laptopToRemove) {
		Laptop.removeLaptop(laptopToRemove);
	}
	
	public static void addLaptop(Laptop laptopToAdd) {
		Laptop.addLaptop(laptopToAdd);
	}
	
	public static void printLaptopSpec(Laptop laptopToPrint) {
		for (String s : laptopToPrint.getLaptopSpec()) {
			System.out.println(s);
		}
	}
}

class LaptopViewer {
	public static void main(String[] args) {
		Laptop laptop_1 = LaptopManager.newLaptop("Lenovo", "Y510p", new Date(), 799);
	}
}