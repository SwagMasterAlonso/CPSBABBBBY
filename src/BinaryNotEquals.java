
public class BinaryNotEquals {
	Bag bag;
	Item itemObj1, itemObj2;
	public BinaryNotEquals(Item item, Item item2){
		this.itemObj1 = item;
		this.itemObj2 = item2;
	}

	public Boolean checkConstraint(){
		System.out.println(itemObj1.name + " " +this.itemObj1.getAssignment() + " XX "+itemObj2.name + " " +this.itemObj2.getAssignment() );
		if(this.itemObj1.getAssignment() != this.itemObj2.getAssignment()) {
			
			System.out.println("ASSIGNMENT FOR BINARY NOT EQUALS IS VALID");
			return true;
		} else {
			return false;
		}
	}

	
	public String toString(){
		return this.itemObj1.getName() + " " + this.itemObj2.getName();
	}
	
	
	
	public Boolean checkBagConstraint(){

		if (this.bag.getListOfItems().contains(this.itemObj1) && this.bag.listOfItems.contains(itemObj2)){
			return false;
		} else {
			return true;
		}
		
		
	}
}
