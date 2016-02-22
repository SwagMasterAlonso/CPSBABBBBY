import java.util.ArrayList;
import java.util.List;

/**
 * Bag class that holds the list of items assigned to it
 * as well as the capacity of the bag.
 * @author jameschow, amartinez
 *
 */
public class Bag {
	/**Identifier for the bag.*/
	String name;
	/**Weight capacity for the bag.*/
	int weight;
	/**list of item assigned to this bag object.*/
	List<Item> listOfItems = new ArrayList<Item>();
	/**Fitting limits include a minimum and a maximum.*/
	int fittingMin, fittingMax;
	/**Fitting constraint.*/
	FittingConstraint fc;
	BinaryNotEquals bneq;


	/**
	 * Constructor that constructs a bag object that holds list of
	 * assignments and information of the bag.
	 * @param nameOfBag, identifier.
	 * @param weightOfBag, weight capacity.
	 */
	public Bag(String nameOfBag, int weightOfBag){
		this.name = nameOfBag;
		this.weight = weightOfBag;
		//this.listOfItems = itemList;
		this.fittingMin = 0;
		this.fittingMax = 20;
		this.fc = new FittingConstraint(this);
	}

	public BinaryNotEquals getBneq() {
		return bneq;
	}

	public void setBneq(BinaryNotEquals bneq) {
		this.bneq = bneq;
	}

	/**To string method for debugging purposes.*/
	public String toString(){
		//return this.name+" with capacity "+this.weight+" from "+this.fittingMin+" to max "+this.fittingMax;
		return this.name + " " +listOfItems;

	}

	/**Setter for the minimum fitting limit.*/
	public void setMin(int fitMin) {
		this.fittingMin = fitMin;
	}

	/**Setter for the max fitting limit.*/
	public void setMax(int fitMax) {
		this.fittingMax = fitMax;
	}

	/**Getter for the minimum fitting limit.*/
	public int getMin() {
		return this.fittingMin;
	}

	/**Setter for the max fitting limit.*/
	public int getMax() {
		return this.fittingMax;
	}

	/**Getter for the list of assignments.*/
	public List<Item> getListOfItems() {
		return listOfItems;
	}

}
