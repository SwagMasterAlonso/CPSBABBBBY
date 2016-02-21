import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
Alonso
*/
public class Bag {


	String name;
	int weight;
	List<Item> listOfItems = new ArrayList<Item>();
	//Queue<Item> listOfItems = new LinkedList<Item>();

	int fittingMin, fittingMax;
	FittingConstraint fc;
	BinaryNotEquals bneq;



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

//	public void setListOfItems(List<Item> listOfItems) {
//		this.listOfItems = listOfItems;
//	}

}
