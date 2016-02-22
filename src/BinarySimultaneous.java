import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Constraint that says that if one item is in the designated bag
 * the other item must be in its corresponding bag.
 * Alternatively, for mutual exclusion the first item must not be in its
 * deginated bag since it would require the item to be in two bags at once.
 * @author jameschow, amartinez
 *
 */
public class BinarySimultaneous {
	/**The item related by binary simultaneous.*/
	Item itemObj1, itemObj2;
	/**The valid bags that an item can go into.*/
	List<Bag> validBagsFor1, validBagsFor2;
	/**
	 * Constructor that creates an binary simultaneous object with
	 * the valid information.
	 * @param item, first item.
	 * @param item2, second item.
	 */
	public BinarySimultaneous(Item item, Item item2){
		this.itemObj1 = item;
		this.itemObj2 = item2;
		this.validBagsFor1 = new CopyOnWriteArrayList<Bag>();
		this.validBagsFor2 = new CopyOnWriteArrayList<Bag>();
	}

	/**Getter for the first object.*/
	public Item getItemObj1() {
		return itemObj1;
	}

	/**Getter for the second object.*/
	public Item getItemObj2() {
		return itemObj2;
	}

	/**Getter for the first object's valid bags.*/
	public List<Bag> getValidBagsFor1() {
		return validBagsFor1;
	}

	/**Getter for the second object's valid bags.*/
	public List<Bag> getValidBagsFor2() {
		return validBagsFor2;
	}

	/**
	 * Determines if the items are mutually inclusive or mutually
	 * exclusive.
	 * @return
	 */
	public Boolean checkConstraint(){
		if (this.validBagsFor1.size() == 1 && this.validBagsFor2.size() == 1) {
			/**Condition for when the domains of each object are the same.*/
			if(this.itemObj1.getAssignment() == this.validBagsFor1.get(0)) {

				System.out.println("Returning True 1");
				return true;
			} else {
				System.out.println("Returning False 1");

				return false;
			}
		} else {
			if (this.validBagsFor1.size() > 1) {
				/**Condition for when the valid bag list for item1 is more than 1*/
				if (this.itemObj2.getAssignment() != this.validBagsFor2.get(0)) {
					System.out.println("Returning True 2");

					return true;
				} else {
					System.out.println("Returning False 2");

					return false;
				}
			} else  {
				/**Condition for when the valid bag list for item2 is more than 1*/
				if (this.itemObj1.getAssignment() != this.validBagsFor1.get(0)) {
					System.out.println("Returning True 3");

					return true;
				} else {
					System.out.println("Returning False 3");

					return false;
				}
			}
		}
	}

	/**Method that adds bags to the valid domain of item 1.*/
	public void addTo1Bags(Bag bag) {
		if (!this.validBagsFor1.contains(bag)) {
			this.validBagsFor1.add(bag);
		}
	}

	/**Method that adds bags to the valid domain of item 2.*/
	public void addTo2Bags(Bag bag) {
		if (!this.validBagsFor2.contains(bag)) {
			this.validBagsFor2.add(bag);
		}
	}
}
