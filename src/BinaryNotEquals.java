
public class BinaryNotEquals {
	Item itemObj1, itemObj2;
	public BinaryNotEquals(Item item, Item item2){
		this.itemObj1 = item;
		this.itemObj2 = item2;
	}

	public Boolean checkConstraint(){
		if(this.itemObj1.getAssignment() != this.itemObj2.getAssignment()) {
			return true;
		} else {
			return false;
		}
	}

}
