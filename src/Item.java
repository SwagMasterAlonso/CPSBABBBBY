import java.util.ArrayList;
import java.util.List;

/**
Alonso
*/
public class Item {

	
	String name;
	
	int weight;
	List<Bag> domain = new ArrayList<Bag>();

	public Item(String nameItem, int weightItem){
		this.name = nameItem;
		this.weight = weightItem;
	}
}
