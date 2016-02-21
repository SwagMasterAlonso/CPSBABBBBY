import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
Alonso, jameschow
*/
public class UnaryInclusive {
	Item itemObj;
	List<Bag> validDomain = new ArrayList<Bag>();
	public UnaryInclusive(Item item){
		this.itemObj = item;
	}


	public Boolean checkConstraint(Bag assignment){
		
		System.out.println("Valid Domain is: "+ this.validDomain);
		
		
		if(this.validDomain.contains(assignment)) {
			return true;
		} else {
			return false;
		}
		
	}

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
