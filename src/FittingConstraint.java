
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
		
		
		//System.out.println("James code returned " + isValid );
		
		
		for (Item i: this.bagObj.getListOfItems()) {
			sum += i.getWeight();
			//System.out.println("In bag " + this.bagObj.name + " has " + i);
		}
		
		if (sum > this.bagObj.weight) {
			isValid = false;
		} else {
			isValid = true;
		}
		
		//System.out.println("James code 2 returned " + isValid );

		return isValid;
	}
}
