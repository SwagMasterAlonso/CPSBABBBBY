import java.util.ArrayList;
import java.util.List;

/**
Alonso, jameschow
*/

//class to hold unary inclusive
public class UnaryInclusive {
	Item itemObj; //item object to map constraint to
	List<Bag> validDomain = new ArrayList<Bag>(); //array list to hold the valid domain
	
	
	//constructor for unary inclusive
	public UnaryInclusive(Item item){
		this.itemObj = item;
	}


	//function checks the constraints for the bag
	public Boolean checkConstraint(Bag assignment){
		
		
		//if the valid domain constains the assignment, then return true
		if(this.validDomain.contains(assignment)) {
			return true;
		} else {
			return false;
		}
		
	}

	
	//various getters and setters for variable names
	public Item getItemObj() {
		return itemObj;
	}


	public void setItemObj(Item itemObj) {
		this.itemObj = itemObj;
	}


	public List<Bag> getValidDomain() {
		return validDomain;
	}


	public void setValidDomain(List<Bag> validDomain) {
		this.validDomain = validDomain;
	}


	public void addToDomain(Bag validBag) {
		this.validDomain.add(validBag);
	}

}
