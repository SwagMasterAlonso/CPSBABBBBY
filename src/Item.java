/**
Alonso
*/
public class Item {


	String name;
	Bag assignment;
	int weight;
	//FittingConstraint fConstraint;
	UnaryExclusive uExclusive;
	UnaryInclusive uInclusive;
	BinaryEquals bEquals;
	BinaryNotEquals bNotEquals;
	BinarySimultaneous bSim;
	public Item(String nameItem, int weightItem){
		this.name = nameItem;
		this.weight = weightItem;
		this.assignment = null;
		this.uExclusive = null;
		this.uInclusive = null;
		this.bEquals = null;
		this.bNotEquals = null;
		this.bSim = null;
	}

	public String toString(){
		return this.name;
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Bag getAssignment() {
		return this.assignment;
	}

	public UnaryExclusive getuExclusive() {
		return uExclusive;
	}

	public void setuExclusive(UnaryExclusive uExclusive) {
		this.uExclusive = uExclusive;
	}

	public UnaryInclusive getuInclusive() {
		return uInclusive;
	}

	public void setuInclusive(UnaryInclusive uInclusive) {
		this.uInclusive = uInclusive;
	}

	public BinaryEquals getbEquals() {
		return bEquals;
	}

	public void setbEquals(BinaryEquals bEquals) {
		this.bEquals = bEquals;
	}

	public BinaryNotEquals getbNotEquals() {
		return bNotEquals;
	}

	public void setbNotEquals(BinaryNotEquals bNotEquals) {
		this.bNotEquals = bNotEquals;
	}

	public BinarySimultaneous getbSim() {
		return bSim;
	}

	public void setbSim(BinarySimultaneous bSim) {
		this.bSim = bSim;
	}

	public void setAssignment(Bag bagSol) {
		this.assignment = bagSol;
	}
	
	//Senpai Alonso Martinez, the greatest programmer ever, named this function.
	public Boolean superXXCheckAllConstraintsXXsuper(){
		
		Boolean isValid = true;
		
		
		if(this.uExclusive !=null){
			isValid = this.uExclusive.checkConstraint(assignment);
			
				if(isValid == false){
					return false;
				}
		}
		
		
		if(this.uInclusive != null){
			isValid = this.uInclusive.checkConstraint(assignment);
			
			if(isValid == false){
				return false;
			}
		}
		
		
		if(this.bEquals != null){
			isValid = this.bEquals.checkConstraint();
			
			if(isValid == false){
				return false;
			}
		}
		
		if(this.bSim != null){
			isValid = this.bSim.checkConstraint();
			
			if(isValid == false){
				return false;
			}
		}
		
		if(this.bNotEquals != null){
			isValid = this.bNotEquals.checkConstraint();
			
			if(isValid == false){
				return false;
			}
		}
		
		
		return isValid;
	}
}
