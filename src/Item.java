import java.util.List;

/**
Alonso
*/
public class Item {


	String name;

	int weight;

	public Item(String nameItem, int weightItem){
		this.name = nameItem;
		this.weight = weightItem;
	}

	public String toString(){
		return this.name;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
