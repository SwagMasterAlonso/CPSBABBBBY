
import java.util.ArrayList;
import java.util.List;

/**
 * Item class that holds its weight and holds its constraints.
 * @author jameschow, amartinez
 * */
public class Item {
	/**Identifier for the item*/
	String name;
	/**Assigned bag for item.*/
	Bag assignment;
	/**Weight of the item.*/
	int weight;
	/**Domain of the item.*/
	List<Bag> domain;
	/**All possible bags of the item.*/
	List<Bag> range;
	/**Unary inclusive constraint for the item. Item must be in these bags*/
	UnaryExclusive uExclusive;
	/**Unary exclusive constraint for the item. Item must not be in these bags.*/
	UnaryInclusive uInclusive;
	/**List of binary constraints for an item.*/
	List<BinaryEquals> bEquals;
	/**List of Binary constraints such that two items must not be in the same bag.*/
	List<BinaryNotEquals> bNotEquals = null;
	/**Binary constraint that indicates mutual inclusion or exclusion.*/
	BinarySimultaneous bSim;

	/**
	 * Constructor that initializes the item with its weight,
	 * constraints, and domain.
	 * @param nameItem
	 * @param weightItem
	 */
	public Item(String nameItem, int weightItem){
		this.name = nameItem;
		this.weight = weightItem;
		this.assignment = null;
		this.uExclusive = null;
		this.uInclusive = null;
		this.bSim = null;
		this.bEquals =   new ArrayList<BinaryEquals>();
		this.bNotEquals	= new ArrayList<BinaryNotEquals>();
		this.domain = new ArrayList<Bag>();
	}

	/**ToString method for debugging purposes.*/
	public String toString(){
		return this.name;
	}

	/**Sets the domain for an item by taking into account only unary constraints.*/
	public void createDomain() {
		if (this.uInclusive != null) {
			for(int i = 0; i < this.uInclusive.getValidDomain().size(); i++) {
				this.domain.add(this.uInclusive.getValidDomain().get(i));
			}
		}
		if (this.uExclusive != null) {
			for (int j = 0; j < this.range.size(); j++) {
				for(int i = 0; i < this.uExclusive.getValidDomain().size(); i++) {
					if (!this.range.get(i).name.equals(this.uExclusive.getValidDomain().get(j))) {
						this.domain.add(this.range.get(i));
					}
				}
			}
		}
	}

	/**Getter for the item name.*/
	public String getName() {
		return this.name;
	}

	/**Setter for the item name.*/
	public void setName(String name) {
		this.name = name;
	}

	/**Getter for the item weight.*/
	public int getWeight() {
		return weight;
	}

	/**Setter for the item weight.*/
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**Getter for the assignment of the item.*/
	public Bag getAssignment() {
		return this.assignment;
	}

	/**Getter for the domain.*/
	public List<Bag> getDomain() {
		return this.domain;
	}

	/**Getter for the unary exclusive constraint.*/
	public UnaryExclusive getuExclusive() {
		return uExclusive;
	}

	/**Setter for the unary exclusive constraint.*/
	public void setuExclusive(UnaryExclusive uExclusive) {
		this.uExclusive = uExclusive;
	}

	/**Getter for the unary inclusive constraint.*/
	public UnaryInclusive getuInclusive() {
		return uInclusive;
	}

	/**Setter for the unary inclusive constraint.*/
	public void setuInclusive(UnaryInclusive uInclusive) {
		this.uInclusive = uInclusive;
	}

	/**Getter for the binary equals constraint.*/
	public List<BinaryEquals> getbEquals() {
		return bEquals;
	}

	/**Getter for the binary notEquals constraint.*/
	public List<BinaryNotEquals> getbNotEquals() {
		return bNotEquals;
	}

	/**Setter for the binary notEquals constraint.*/
	public void setbNotEquals(List<BinaryNotEquals> bNotEquals) {
		this.bNotEquals = bNotEquals;
	}

	/**Getter for the binary simultaneous constraint.*/
	public BinarySimultaneous getbSim() {
		return bSim;
	}

	/**Setter for the binary simultaneous constraint.*/
	public void setbSim(BinarySimultaneous bSim) {
		this.bSim = bSim;
	}

	/**Setter for the assignment.*/
	public void setAssignment(Bag bagSol) {
		this.assignment = bagSol;
	}

	/**
	 * Determines if all the constraints are satisfied by an assignment of the variables.
	 * @return true or false.
	 */
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
			/**Must iterate through all binary constraints to determine constraint
			 * Satisfaction.*/
			for (BinaryEquals beq: this.bEquals) {
				isValid = beq.checkConstraint();
				if(isValid == false){
					return false;
				}
			}
		}

		if(this.bSim != null){
			isValid = this.bSim.checkConstraint();
			if(isValid == false){
				return false;
			}
		}

		if(this.bNotEquals != null){
			/**Must iterate through all binary constraints to determine constraint
			 * Satisfaction.*/
			for(Item i:this.assignment.getListOfItems()){
				for(BinaryNotEquals bneq:bNotEquals){


					isValid = bneq.checkConstraint();

					if(isValid == false){
						return false;
					}
				}
			}
		}
		return isValid;
	}
}
