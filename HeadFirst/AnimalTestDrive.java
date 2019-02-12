//building an animalList that stores any animal

abstract class Animal {
	private String animalFood;
	private String animalSize;
	
	public void setFood(String food) {
		animalFood = food;
	}
	
	public void setSize(String size) {
		animalSize = size;
	}
	
	public abstract void eat(String food);
	public abstract void sleep();
	
}

class Cat extends Animal {
	
	public void eat(String food) {
		if (food.equals("fish")) {
			System.out.println("meow");
		}
		else {
			System.out.println("Meowww!");
		}
	}
	
	public void sleep() {
		System.out.println("Purr");
	}
}

class Dog extends Animal {
	
	public void eat(String food) {
		if (food.equals("Meat") || food.equals("Bone") || food.equals("dog food")) {
			System.out.println("Ruff");
		}
		else {
			System.out.println("Woof! Woof!");
		}
	}
	
	public void sleep() {
		System.out.println("...");
	}
}

class MyAnimalList {
	private Animal[] animal = new Animal[5];
	private int nextIndex = 0;
	
	public void add(Animal a) {
		if (nextIndex < animal.length) {
			animal[nextIndex] = a;
			System.out.println("Animal added at " + nextIndex);
			nextIndex++;
		}
	}
	
}

public class AnimalTestDrive {
	public static void main (String[] args) {
		Dog myDog = new Dog();
		Cat myCat = new Cat();
		MyAnimalList list = new MyAnimalList();
		list.add(myDog);
		list.add(myCat);
	}
}