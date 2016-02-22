import java.util.ArrayList;
import java.util.List;

//authors 
//amartinez
//yychow


//class to hold unary exclusive constraint 
public class UnaryExclusive {
	Item itemObj; //item that constraint maps to 
	List<Bag> validDomain = new ArrayList<Bag>(); //domain of the object 

	//constructor for unary exlusive constraint that takes an item
	public UnaryExclusive(Item item){
		this.itemObj = item;
	}

	//function checks the constraints of the bag and determines whether
	//it is valid or not by checking if the valid domain constains our assignment 
	public Boolean checkConstraint(Bag assignment){
		if(this.validDomain.contains(assignment)) {
			return false;
		} else {
			return true;
		}
	}

	
	//getter for the item
	public Item getItemObj() {
		return itemObj;
	}

	//setter for the item
	public void setItemObj(Item itemObj) {
		this.itemObj = itemObj;
	}

	//getter for the domain
	public List<Bag> getValidDomain() {
		return validDomain;
	}

	//setter for the domain
	public void setValidDomain(List<Bag> validDomain) {
		this.validDomain = validDomain;
	}

	//adds item to the domain
	public void addToDomain(Bag validBag) {
		this.validDomain.add(validBag);
	}
}
