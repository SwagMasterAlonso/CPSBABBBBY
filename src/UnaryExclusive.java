import java.util.ArrayList;
import java.util.List;

public class UnaryExclusive {
	Item itemObj;
	List<Bag> validDomain = new ArrayList<Bag>();
	public UnaryExclusive(Item item){
		this.itemObj = item;
	}


	public Boolean checkConstraint(Bag assignment){
		if(this.validDomain.contains(assignment)) {
			return false;
		} else {
			return true;
		}
	}

	public void addToDomain(Bag validBag) {
		this.validDomain.add(validBag);
	}
}
