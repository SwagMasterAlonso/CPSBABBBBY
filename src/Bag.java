import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
Alonso
*/
public class Bag {


	String name;
	int weight;
	List<Item> listOfItems = new CopyOnWriteArrayList<Item>();
	int fittingMin, fittingMax;
	FittingConstraint fc;




	public Bag(String nameOfBag, int weightOfBag){
		this.name = nameOfBag;
		this.weight = weightOfBag;
		//this.listOfItems = itemList;
		this.fittingMin = 0;
		this.fittingMax = 20;
		this.fc = new FittingConstraint(this);
	}

	public String toString(){
		//return this.name+" with capacity "+this.weight+" from "+this.fittingMin+" to max "+this.fittingMax;
		return this.name + " " +listOfItems;

	}

	public void setMin(int fitMin) {
		this.fittingMin = fitMin;
	}
	public void setMax(int fitMax) {
		this.fittingMax = fitMax;
	}

	public int getMin() {
		return this.fittingMin;
	}
	public int getMax() {
		return this.fittingMax;
	}

	public List<Item> getListOfItems() {
		return listOfItems;
	}

	public void setListOfItems(List<Item> listOfItems) {
		this.listOfItems = listOfItems;
	}

}
