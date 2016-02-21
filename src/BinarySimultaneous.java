import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author jameschow
 *
 */
public class BinarySimultaneous {
	Item itemObj1, itemObj2;
	List<Bag> validBagsFor1, validBagsFor2;
	public BinarySimultaneous(Item item, Item item2){
		this.itemObj1 = item;
		this.itemObj2 = item2;
		this.validBagsFor1 = new CopyOnWriteArrayList<Bag>();
		this.validBagsFor2 = new CopyOnWriteArrayList<Bag>();
	}

	public Boolean checkConstraint(){
		if (this.validBagsFor1.size() == 1 && this.validBagsFor2.size() == 1) {
			if(this.itemObj1.getAssignment() == this.validBagsFor1.get(0)) {
				
				System.out.println("Returning True 1");
				return true;
			} else {
				System.out.println("Returning False 1");

				return false;
			}
		} else {
			if (this.validBagsFor1.size() > 1) {

				if (this.itemObj2.getAssignment() != this.validBagsFor2.get(0)) {
					System.out.println("Returning True 2");

					return true;
				} else {
					System.out.println("Returning False 2");

					return false;
				}
			} else  {
				if (this.itemObj1.getAssignment() != this.validBagsFor1.get(0)) {
					System.out.println("Returning True 3");

					return true;
				} else {
					System.out.println("Returning False 3");

					return false;
				}
			}
		}
	}
	public void addTo1Bags(Bag bag) {
		if (!this.validBagsFor1.contains(bag)) {
			this.validBagsFor1.add(bag);
		}
	}

	public void addTo2Bags(Bag bag) {
		if (!this.validBagsFor2.contains(bag)) {
			this.validBagsFor2.add(bag);
		}
	}
}
