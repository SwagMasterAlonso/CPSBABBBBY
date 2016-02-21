
import java.util.ArrayList;
import java.util.List;

/**
Alonso
 */
public class Item {


	String name;
	Bag assignment;
	int weight;
	//FittingConstraint fConstraint;
	List<Bag> domain;
	List<Bag> range;
	UnaryExclusive uExclusive;
	UnaryInclusive uInclusive;
	List<BinaryEquals> bEquals = new ArrayList<BinaryEquals>();
	List<BinaryNotEquals> bNotEquals = null;
	BinarySimultaneous bSim;
	public Item(String nameItem, int weightItem){
		this.name = nameItem;
		this.weight = weightItem;
		this.assignment = null;
		this.uExclusive = null;
		this.uInclusive = null;
		this.bSim = null;
		this.bNotEquals	= null;
		this.domain = new ArrayList<Bag>();
	}

	public String toString(){
		return this.name;
	}

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

	public List<Bag> getDomain() {
		return this.domain;
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

	public List<BinaryEquals> getbEquals() {
		return bEquals;
	}

	public List<BinaryNotEquals> getbNotEquals() {
		return bNotEquals;
	}

	public void setbNotEquals(List<BinaryNotEquals> bNotEquals) {
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


		System.out.println("Checking All Constraints bby");
		if(this.uExclusive !=null){
			isValid = this.uExclusive.checkConstraint(assignment);


			//			System.out.println("1 "+isValid);


			if(isValid == false){
				return false;
			}
		}


		if(this.uInclusive != null){
			isValid = this.uInclusive.checkConstraint(assignment);


			//			System.out.println("2 "+isValid);

			if(isValid == false){
				return false;
			}
		}


		if(this.bEquals != null){
			for (BinaryEquals beq: this.bEquals) {
				isValid = beq.checkConstraint();


				//				System.out.println("3 "+isValid);

				if(isValid == false){
					return false;
				}
			}
		}

		if(this.bSim != null){
			isValid = this.bSim.checkConstraint();


			//			System.out.println("4 "+isValid);

			if(isValid == false){
				return false;
			}
		}

		if(this.bNotEquals != null){


			for(Item i:this.assignment.getListOfItems()){
				for(BinaryNotEquals bneq:bNotEquals){

					System.out.println("This bNotEquals is: "+bNotEquals);

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
