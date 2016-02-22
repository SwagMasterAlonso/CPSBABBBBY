/**
 * Binary constraint that says to avoid putting two items in the same bag.
 * @author amartinez, jameschow
 *
 */
public class BinaryNotEquals {
	/**One of the items that are related by binary constraint.*/
	Item itemObj1, itemObj2;
	/**
	 * Constructor that constructs a binary not equals constraint.
	 * @param item
	 * @param item2
	 */
	public BinaryNotEquals(Item item, Item item2){
		this.itemObj1 = item;
		this.itemObj2 = item2;
	}

	/**
	 * Determines if all the constraints for an item are met.
	 * @return a boolean value.
	 */
	public Boolean checkConstraint(){
		System.out.println(itemObj1.name + " " +this.itemObj1.getAssignment() + " XX "+itemObj2.name + " " +this.itemObj2.getAssignment() );
		if(this.itemObj1.getAssignment() != this.itemObj2.getAssignment()) {
			/**Makes sure that the assignment of the first item is not the same as the
			 * assignment of the second item.*/
			System.out.println("ASSIGNMENT FOR BINARY NOT EQUALS IS VALID");
			return true;
		} else {
			return false;
		}
	}

	/**Getter for the first item object.*/
	public Item getItemObj1() {
		return itemObj1;
	}

	/**Getter for the second item object.*/
	public Item getItemObj2() {
		return itemObj2;
	}

	/**To string method for debug purposes.*/
	public String toString(){
		return this.itemObj1.getName() + " " + this.itemObj2.getName();
	}

}
