
public class FittingConstraint {
	Bag bagObj;
	public FittingConstraint(Bag bag) {
		this.bagObj = bag;
	}

	public boolean checkConstraint() {
		int sum = 0;
		Boolean isWeightValid = false;
		Boolean isFitValid = false;

		if(this.bagObj.listOfItems.size() >= this.bagObj.getMin() && this.bagObj.listOfItems.size() <= this.bagObj.getMax()) {
			isFitValid = true;
		} else {
			isFitValid = false;
		}
		
		
		//System.out.println("James code returned " + isValid );
		 
		
		for (Item i: this.bagObj.getListOfItems()) {
			sum += i.getWeight();
			//System.out.println("In bag " + this.bagObj.name + " has " + i);
		}
		
		
		
		System.out.println("Sum is: " + sum + " Bag Weight is: "+Math.floor(this.bagObj.weight) * .9);
		if(sum> Math.floor(this.bagObj.weight*.9)){
			isWeightValid = true;
		} else {
			isWeightValid = false;
		}
		
		
		if (sum > this.bagObj.weight) {
			isWeightValid = false;
		} else {
			isWeightValid = true;
		}
		
		//System.out.println("James code 2 returned " + isValid );

		
		System.out.println("Is Weight Valid: " + isWeightValid + "    Is Fit Valid: "+isFitValid );
		System.out.println("Bag Size is: " + bagObj.getListOfItems().size());
		if(isWeightValid&&isFitValid){
			return true;
		} else {
			return false;
		}
	}
}
