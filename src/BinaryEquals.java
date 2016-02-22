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

		System.out.println(itemObj1.name + " " +this.itemObj1.getAssignment() + " XX "+itemObj2.name + " " +this.itemObj2.getAssignment() );


		if(this.itemObj1.getAssignment() == null || this.itemObj2.getAssignment() == null){
			/***/
			System.out.println("Null setting true");
			return true;
		}
		else if(this.itemObj1.getAssignment() == this.itemObj2.getAssignment()) {
			return true;
		} else {
			return false;
		}

	}

	public String toString(){
		return this.itemObj1 + " " + this.itemObj2;
	}
	public Item getItemObj1() {
		return itemObj1;
	}
	public Item getItemObj2() {
		return itemObj2;
	}

}
