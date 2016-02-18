import java.util.List;

/**
Alonso
*/
public class Item {


	String name;

	int weight;
	List<Bag> domain;

	public Item(String nameItem, int weightItem){
		this.name = nameItem;
		this.weight = weightItem;
	}

	public String toString(){
		return this.name;
	}
	public void setDomain (List<Bag> bags) {
		this.domain = bags;
	}
}
