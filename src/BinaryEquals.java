/**
 * Binary constraint that says to put two items in the same bag.
 * @author amartinez, jameschow
 *
 */
public class BinaryEquals {
	/**One of the items related by a binary constraint, connected on the constraint graph.*/
	Item itemObj1, itemObj2;
	/**
	 * Constructor
	 * @param item, first item.
	 * @param item2, second item.
	 */
	public BinaryEquals(Item item, Item item2){
		this.itemObj1 = item;
		this.itemObj2 = item2;
	}

	/**
	 * Check constraint method that determines if all the constraints for an item are met.
	 * @return True if all constraints are met, false otherwise.
	 */
	public Boolean checkConstraint(){



		if(this.itemObj1.getAssignment() == null || this.itemObj2.getAssignment() == null){
			/**Returns true if one item has not assignment yet.*/
			return true;
		}
		else if(this.itemObj1.getAssignment() == this.itemObj2.getAssignment()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * To String method for debug purposes.
	 */
	public String toString(){
		return this.itemObj1 + " " + this.itemObj2;
	}

	/**Getter for the item object.*/
	public Item getItemObj1() {
		return itemObj1;
	}
	/**Getter for the item object.*/
	public Item getItemObj2() {
		return itemObj2;
	}

}
