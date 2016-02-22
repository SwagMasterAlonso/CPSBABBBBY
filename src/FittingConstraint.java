//Authors alonso martines and yychow


public class FittingConstraint {
	Bag bagObj; //bag object for our class to map the constraint to

	//constructor for the fitting constraint
	public FittingConstraint(Bag bag) {
		this.bagObj = bag;
	}


	//checks the constraints.
	public boolean checkConstraint() {
		int sum = 0; //variable to hold the sum of all the weight
		Boolean isWeightValid = false; //boolean to determine if the weight is valid 
		Boolean isFitValid = false; //boolean to determine if the fit is valid 


		//if the bag is within the given size limits, then set the fit valid to true
		if(this.bagObj.listOfItems.size() >= this.bagObj.getMin() && this.bagObj.listOfItems.size() <= this.bagObj.getMax()) {
			isFitValid = true;
		} else {
			isFitValid = false;
		}





		//for all of the items in the bag, sum up the weights
		for (Item i: this.bagObj.getListOfItems()) {
			sum += i.getWeight();
		}

		//if the sum is greater than the capacity, return false, otherwise true
		if (sum > this.bagObj.weight) {
			isWeightValid = false;
		} else {
			isWeightValid = true;
		}

		//if the weight is valid and the fit is valid, then the constraints all passed
		if(isWeightValid&&isFitValid){
			return true;
		} else {
			return false;
		}
	}
}
