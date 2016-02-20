
public class FittingConstraint {
	Bag bagObj;
	public FittingConstraint(Bag bag) {
		this.bagObj = bag;
	}

	public boolean checkConstraint() {
		int sum = 0;
		Boolean isValid = false;
		if(this.bagObj.listOfItems.size() >= this.bagObj.getMin() && this.bagObj.listOfItems.size() <= this.bagObj.getMax()) {
			isValid = true;
		} else {
			isValid = false;
		}
		for (Item i: this.bagObj.getListOfItems()) {
			sum += i.getWeight();
		}
		if (sum >= this.bagObj.weight) {
			isValid = false;
		} else {
			isValid = true;
		}
		return isValid;
	}
}
