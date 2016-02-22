/**
 *
 * @author jameschow
 *
 */
public class BinaryEquals {
	Item itemObj1, itemObj2;
	public BinaryEquals(Item item, Item item2){
		this.itemObj1 = item;
		this.itemObj2 = item2;
	}
	public Boolean checkConstraint(){

		System.out.println(itemObj1.name + " " +this.itemObj1.getAssignment() + " XX "+itemObj2.name + " " +this.itemObj2.getAssignment() );


		if(this.itemObj1.getAssignment() == null || this.itemObj2.getAssignment() == null){
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
