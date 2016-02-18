
public class FittingConstraint {
	Bag bagObj;
	public FittingConstraint(Bag bag) {
		this.bagObj = bag;
	}

	public boolean checkConstraint() {
		if(this.bagObj.listOfItems.size() >= this.bagObj.getMin() && this.bagObj.listOfItems.size() <= this.bagObj.getMax()) {
			return true;
		} else {
			return false;
		}
	}
}
