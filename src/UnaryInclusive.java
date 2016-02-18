import java.util.List;
import java.util.ArrayList;

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
		if(this.validDomain.contains(assignment)) {
			return true;
		} else {
			return false;
		}
	}

	public void addToDomain(Bag validBag) {
		this.validDomain.add(validBag);
	}

}
