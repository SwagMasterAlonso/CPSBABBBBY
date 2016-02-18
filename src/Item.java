import java.util.List;

/**
Alonso
*/
public class Item {


	String name;
	Bag assignment;
	int weight;

	public Item(String nameItem, int weightItem){
		this.name = nameItem;
		this.weight = weightItem;
		this.assignment = null;
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

	public Bag getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Bag bagSol) {
		this.assignment = bagSol;
	}
}
