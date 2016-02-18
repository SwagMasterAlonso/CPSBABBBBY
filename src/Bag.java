import java.util.ArrayList;
import java.util.List;

/**
Alonso
*/
public class Bag {


	String name;
	int weight;
	List<Item> listOfItems = new ArrayList<Item>();
	int fittingMin, fittingMax;


	public Bag(String nameOfBag, int weightOfBag){
		this.name = nameOfBag;
		this.weight = weightOfBag;
		//this.listOfItems = itemList;
	}

	public String toString(){
		return this.name+" with capacity "+this.weight+" from "+this.fittingMin+" to max "+this.fittingMax;
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

}
