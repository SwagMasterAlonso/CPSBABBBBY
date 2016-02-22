import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


	static List<Bag> listOfBags = new ArrayList<Bag>();

	static List<Item> listOfItems = new ArrayList<Item>();

	static List<Bag> finalBag = null;

	static int count = 0;
	public static void main(String[] args) {





		String fileName = null;
		//get command line arguments, there should only be 2
		if(args.length==1){
			String inputString = args[0];

			fileName = inputString;
		} else {
			System.out.println("Not enough input arguments");
			System.exit(0);
		}



		parseData(fileName);
		backTrack(listOfBags,listOfItems);
		System.out.println("Count is: " + count);


		//backTrack(listOfBags,listOfItems);
		//forwardChecking(listOfBags, listOfItems, listOfBags);


	}

	static void parseData(String fileName){






		String line;
		String item, bag;
		int weight = 0;
		int counter = 0;




		BufferedReader br = null;




		try {

			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				switch(counter) {
				case 0:
					if (line.contains("#####") && line.contains("variables")) {
						System.out.println("Beginning header");
						break;
					} else if (line.contains("#####") && line.contains("values")) {
						System.out.println("entering section "+line);
						counter++;
						break;
					} else {
						String[] fields = line.split(" ");
						item = fields[0];
						weight = Integer.parseInt(fields[1]);
						Item itemObject = new Item(item,weight);
						listOfItems.add(itemObject);
						System.out.println("item "+item+" weighs "+weight);
						continue;
					}
				case 1:
					if (line.contains("#####") && line.contains("fitting limits")) {

						System.out.println("entering section "+line);
						counter++;
						break;
					} else {
						String[] fields = line.split(" ");
						bag = fields[0];
						weight = Integer.parseInt(fields[1]);
						Bag bagObject = new Bag(bag,weight);
						listOfBags.add(bagObject);
						System.out.println("bag "+bag+" can hold "+weight);
						continue;
					}
				case 2:
					if (line.contains("#####") && line.contains("unary inclusive")) {

						System.out.println("entering section "+line);
						counter++;
						break;
					} else {
						System.out.println("Reading fitting limits info.");
						String[] fields = line.split(" ");
						int min = Integer.parseInt(fields[0]);
						int max = Integer.parseInt(fields[1]);
						for (Bag b: listOfBags) {
							b.setMin(min);
							b.setMax(max);
						}
						for (Bag b: listOfBags) {
							System.out.println(b);
						}
						continue;
					}
				case 3:
					UnaryInclusive uin = null;
					if (line.contains("#####") && line.contains("unary exclusive")) {
						System.out.println("entering section "+line);
						counter++;
						break;
					} else {
						System.out.println("Reading unary inclusive info.");
						String[] fields = line.split(" ");
						for (Item i: listOfItems) {
							if (i.getName().equals(fields[0])) {
								uin = new UnaryInclusive(i);
								for (int k = 1; k< fields.length; k++) {
									uin.addToDomain(listOfBags.get(getBagIndex(fields[k])));
								}
								i.setuInclusive(uin);
							}

						}
						System.out.println("Read in "+line);
						continue;
					}
				case 4:
					UnaryExclusive uex = null;
					if (line.contains("#####") && line.contains("binary equals")) {
						System.out.println("entering section "+line);
						counter++;
						break;
					} else {
						System.out.println("Reading unary exclusive info.");
						String[] fields = line.split(" ");
						for (Item i: listOfItems) {
							if (i.getName().equals(fields[0])) {
								uex = new UnaryExclusive(i);
								for (int k = 1; k< fields.length; k++) {
									uex.addToDomain(listOfBags.get(getBagIndex(fields[k])));
								}
								i.setuExclusive(uex);
							}

						}
						System.out.println("Read in "+line);
						continue;
					}
				case 5:
					for(Item i: listOfItems){
						i.createDomain();
					}
					BinaryEquals beq = null;
					if (line.contains("#####") && line.contains("binary not")) {
						System.out.println("entering section "+line);
						counter++;
						break;
					} else {
						System.out.println("Reading binary equals info.");
						String[] fields = line.split(" ");
						Item item1 = null, item2 = null;

						for (Item i: listOfItems) {
							if (i.getName().equals(fields[0])) {
								item1 = i;
							} else if (i.getName().equals(fields[1])) {
								item2 = i;
							}
						}

						System.out.println("Item 1 is: " + item1 + " Item 2 is : " +item2);
						beq = new BinaryEquals(item1, item2);
						item1.getbEquals().add(beq);

						System.out.println("Item1  is" + item1.bEquals);
						BinaryEquals beq2 = new BinaryEquals(item2, item1);

						item2.getbEquals().add(beq2);
						System.out.println("Item2  is" + item2.bEquals);

						continue;
					}
				case 6:
					BinaryNotEquals bNotEq = null;
					if (line.contains("#####") && line.contains("binary simultaneous")) {
						System.out.println("entering section "+line);
						counter++;
						break;
					} else {
						System.out.println("Reading binary not equals info.");
						String[] fields = line.split(" ");
						Item item1 = null, item2 = null;

						for (Item i: listOfItems) {
							if (i.getName().equals(fields[0])) {
								item1 = i;
							} else if (i.getName().equals(fields[1])) {
								item2 = i;
							}
						}

						System.out.println(item1 + " "+item2);
						bNotEq = new BinaryNotEquals(item1, item2);
//						item1.bNotEquals = new ArrayList<BinaryNotEquals>();
//						item2.bNotEquals = new ArrayList<BinaryNotEquals>();

						item1.getbNotEquals().add(bNotEq);
						bNotEq = new BinaryNotEquals(item2, item1);
						item2.getbNotEquals().add(bNotEq);
						System.out.println("List is: "+ item1.getbNotEquals());
						System.out.println(item1.getbNotEquals() + " YY "+item2.getbNotEquals());
						continue;
					}
				case 7:
					BinarySimultaneous bsim = null;
					System.out.println("Reading binary simultaneous info.");
					String[] fields = line.split(" ");
					Item item1 = null, item2 = null;

					for (Item i: listOfItems) {
						if (i.getName().equals(fields[0])) {
							item1 = i;
						} else if (i.getName().equals(fields[1])) {
							item2 = i;
						}
					}
					if (itemSeenForBSim(item1)) {
						item1.getbSim().addTo1Bags(listOfBags.get(getBagIndex(fields[2])));
						item1.getbSim().addTo2Bags(listOfBags.get(getBagIndex(fields[3])));
					} else {
						bsim = new BinarySimultaneous(item1, item2);
						bsim.addTo1Bags(listOfBags.get(getBagIndex(fields[2])));
						bsim.addTo2Bags(listOfBags.get(getBagIndex(fields[3])));
						item1.setbSim(bsim);
					}
					if (itemSeenForBSim(item2)) {
						item2.getbSim().addTo1Bags(listOfBags.get(getBagIndex(fields[3])));
						item1.getbSim().addTo2Bags(listOfBags.get(getBagIndex(fields[2])));
					} else {
						bsim = new BinarySimultaneous(item2, item1);
						bsim.addTo1Bags(listOfBags.get(getBagIndex(fields[3])));
						bsim.addTo2Bags(listOfBags.get(getBagIndex(fields[2])));
						item2.setbSim(bsim);
					}
					continue;
				default:
					System.out.println("Already reached end of file");
				}
			}
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {e.printStackTrace();}
			}
		}
	}


	static Boolean backTrack(List<Bag> bagList, List<Item> itemList){
		count++;
		Item tempItem;
		boolean isDone = false;
		boolean result = false;
		List<Item> copy = new ArrayList<Item>();
		for(Item i: itemList){
			copy.add(i);
		}

		if(itemList.isEmpty()){
			System.out.println("IsEmpty");
			for(Bag b:bagList){
				if(b.fc.checkConstraint()){

					for(int i = 0; i < b.getListOfItems().size();i++){
						if(((List<Item>) b.getListOfItems()).get(i).superXXCheckAllConstraintsXXsuper()){

							isDone = true;
						} else {
							isDone = false;
						}
					}
				} else {
					isDone = false;
				}
			}
		}

		if(isDone){
			finalBag = bagList;
			System.out.println(finalBag);
			System.out.println("Finished with assignment, returning");
			return true;
		}


		System.out.println("");
		System.out.println("");
		System.out.println("");


		System.out.println("XXXXXXXXXX  STARTING AGAIN XXXXXXXXXX");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println(itemList);
		System.out.println("Before "+itemList.size());

		if(!copy.isEmpty()){
		//tempItem = copy.remove(minRemValue(copy));
		//tempItem = copy.remove(degreeHeuristic(copy));
		//tempItem = copy.remove(leastConstrainingValue(copy));
		tempItem = copy.remove(MRVANDDEGREE(copy));
		//tempItem = copy.remove(0);
		} else {
			return false;
		}
		System.out.println("After " + itemList.size());

		System.out.println("Removing " + tempItem);
		for(Bag c: bagList){

			System.out.println("In bag " + c);
			System.out.println("1");
			System.out.println("2");


			if(!c.getListOfItems().contains(tempItem)){
				c.getListOfItems().add(tempItem);
				tempItem.setAssignment(c);
			} else {
				tempItem.setAssignment(c);

				continue;
			}

			//	c.getListOfItems().add(tempItem);

			if(c.fc.checkConstraint()){

				System.out.println("Passed fitting");




				for(int i = 0; i < c.getListOfItems().size();i++){
					System.out.println("Starting for");


					if(c.getListOfItems().get(i).superXXCheckAllConstraintsXXsuper()){
						System.out.println("Doing backtrack again");
						System.out.println("Constraints all pass");


						System.out.println(c.name+ " "+c.getListOfItems());
						System.out.println("Starting Backtrack");
						result = backTrack(bagList,copy);
						System.out.println("Is Succesful");
						if(result != false){
							System.out.println("Current Assignment is: " + bagList);
							return true;

						}

						//c.getListOfItems().remove(0);
						System.out.println("FAILED TO RETURN TRUE AFTER BACKTRACK");
						c.getListOfItems().remove(tempItem);
						tempItem.setAssignment(null);
						c.fc = new FittingConstraint(c);
//						if(!itemList.contains(tempItem)){
//							itemList.add(tempItem);
//						}
						System.out.println("Current Assignment is: " + bagList);

					} else {
						System.out.println("Didnt pass all constraints");
						c.getListOfItems().remove(tempItem);
						tempItem.setAssignment(null);
						System.out.println("Current Assignment is: " + bagList);

					}


				}


			} else {
				System.out.println("DIDNT WORK AT ALL");

				System.out.println("Before Remove: "+ c.name+" " + c.getListOfItems());


				c.getListOfItems().remove(tempItem);
				tempItem.setAssignment(null);

				System.out.println("After Remove: " + c.name+" "+ c.getListOfItems());

				System.out.println("Before Add ItemList: " + itemList);

				//	c.fc = new FittingConstraint(c);
				//				if(!itemList.contains(tempItem)){
				//					itemList.add(tempItem);
				//				} else {
				//					continue;
				//				}

				System.out.println("After Add ItemList: " + itemList);

			}


		}
		finalBag = bagList;
		System.out.println(finalBag);
		System.out.println("Failing");
		return false;
	}


	private static int minRemValue(List<Item> listOfItems) {
		int temp = 0;
		int min = 1000;
		for (int i = 0; i< listOfItems.size(); i++){
			if (listOfItems.get(i).getDomain().size() < min) {
				temp = i;
				min = listOfItems.get(i).getDomain().size();
			}
		}
		return temp;
	}
	
	
	private static int MRVANDDEGREE(List<Item> listOfItems){
		int counter;
		int temp = 0;
		int max = 0;
		for (int i = 0; i< listOfItems.size(); i++){
			counter = 0;
			
			
			if(listOfItems.get(i).getDomain().size() != 0){
				
				counter+=listOfItems.get(i).getDomain().size();

			}
			
			
			if (listOfItems.get(i).getbEquals().size() != 0) {
				
				System.out.println("Increasing from bEquals");
				counter+=listOfItems.get(i).getbEquals().size();
			}
			if (listOfItems.get(i).getbNotEquals().size() != 0) {
				System.out.println("Increasing from bNotEquals");

				counter+=listOfItems.get(i).getbNotEquals().size();
			}
			if (listOfItems.get(i).getbSim() != null) {
				counter++;
			}
			
			System.out.println("Counter Var for " + listOfItems.get(i).name + " is : " +counter);

			if (counter >= max) {
				max = counter;
				temp = i;
			}
		}
		return temp;
	}
	

	private static int degreeHeuristic(List<Item> listOfItems) {
		int counter;
		int temp = 0;
		int max = 0;
		for (int i = 0; i< listOfItems.size(); i++){
			counter = 0;
			if (listOfItems.get(i).getbEquals().size() != 0) {
				
				System.out.println("Increasing from bEquals");
				counter+=listOfItems.get(i).getbEquals().size();
			}
			if (listOfItems.get(i).getbNotEquals().size() != 0) {
				System.out.println("Increasing from bNotEquals");

				counter+=listOfItems.get(i).getbNotEquals().size();
			}
			if (listOfItems.get(i).getbSim() != null) {
				counter++;
			}
			
			System.out.println("Counter Var for " + listOfItems.get(i).name + " is : " +counter);

			if (counter >= max) {
				max = counter;
				temp = i;
			}
		}
		return temp;
	}

	private static int leastConstrainingValue(List<Item> listOfItems) {
		int counter;
		int temp = 0;
		int max = 10;
		for (int i = 0; i< listOfItems.size(); i++){
			counter = 0;
			if (listOfItems.get(i).getbEquals().size() != 0) {
				counter+=listOfItems.get(i).getbEquals().size();
				System.out.println("Increasing from bEquals");

			}
			if (listOfItems.get(i).getbNotEquals().size() != 0) {
				counter+=listOfItems.get(i).getbNotEquals().size();
				System.out.println("Increasing from bNotEquals");

			}
			if (listOfItems.get(i).getbSim() != null) {
				counter++;
			}
			
			
			System.out.println("Counter Var for " + listOfItems.get(i).name + " is : " +counter);
			if (counter <= max) {
				max = counter;
				temp = i;
			}
		}
		return temp;
	}







	private static int getHighestWeight() {
		int temp = 0;
		int max = 0;
		for (int i = 0; i< listOfItems.size(); i++){
			if (listOfItems.get(i).getWeight() >= max) {
				temp = i;
				max = listOfItems.get(i).getWeight();
			}
		}
		return temp;
	}


	static public int getBagIndex(String character){

		for(int i = 0; i <listOfBags.size();i++){
			if(character.equals(listOfBags.get(i).name)){
				return i;
			}
		}
		return -1;

	}

	static public boolean itemSeenForBSim(Item i) {
		if (i.bSim != null) {
			return true;
		}
		return false;
	}
	static public Boolean forwardChecking(List<Bag> assignment, List<Item> itemList, List<Bag> domain) {
		Item tempItem;
		boolean isDone = false;
		boolean result = false;
		List<Item> copy = new ArrayList<Item>();
		List<Item> tempEdges = null;
		List<Bag> temp2Results = null;
		List<Bag> domainPrime;
		for(Item i: itemList){
			copy.add(i);
		}
		/**Begin check for complete assignment.*/
		if(itemList.isEmpty()){
			System.out.println("IsEmpty");
			for(Bag b:assignment){
				if(b.fc.checkConstraint()){
					for(int i = 0; i < b.getListOfItems().size();i++){
						if(((List<Item>) b.getListOfItems()).get(i).superXXCheckAllConstraintsXXsuper()){
							isDone = true;
						} else {
							isDone = false;
						}
					}
				} else {
					isDone = false;
				}
			}
		}

		if(isDone){
			finalBag = assignment;
			System.out.println(finalBag);
			System.out.println("Finished with assignment, returning");
			return true;
		}
		/*Finishing the check for if assignment is complete*/

		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("XXXXXXXXXX  STARTING AGAIN XXXXXXXXXX");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println(itemList);
		System.out.println("Before "+itemList.size());
		tempItem = copy.remove(0);
		System.out.println("Removing "+ tempItem);
		for(Bag c: domain){

			if(!c.getListOfItems().contains(tempItem)){
				c.getListOfItems().add(tempItem);
				tempItem.setAssignment(c);
			} else {
				continue;
			}
			//saving the domain
			domainPrime = new ArrayList<Bag>(domain);
			if(c.fc.checkConstraint()){
				System.out.println("Passed fitting constraints.");
				for(int i = 0; i < c.getListOfItems().size();i++){
					System.out.println("Starting for");
					if(c.getListOfItems().get(i).superXXCheckAllConstraintsXXsuper()){
						System.out.println("Doing forward checking again");
						System.out.println("Constraints all pass");
						tempEdges = new ArrayList<Item>();
						/*Going through all edges of unassigned variables.*/
						if (tempItem.getbEquals().size() != 0 || tempItem.getbNotEquals().size() != 0|| tempItem.getbSim() != null) {
							for (BinaryEquals beq: tempItem.getbEquals()) {
								tempEdges.add(beq.getItemObj2());
							}
							for (BinaryNotEquals bneq: tempItem.getbNotEquals()) {
								tempEdges.add(bneq.getItemObj2());
							}

							if (tempItem.getbSim() != null) {
								tempEdges.add(tempItem.getbSim().getItemObj2());
							}
						} else {
							for (Item nextItem: copy) {
								if (!nextItem.equals(tempItem)){
									tempEdges.add(nextItem);
								}
							}
						}

						temp2Results = new ArrayList<Bag>();
						for (Item y: tempEdges) {
							if (y.getAssignment() == null) {
								for (Bag b: domainPrime) {
									if(!b.getListOfItems().contains(y)){
										b.getListOfItems().add(y);
										y.setAssignment(b);
									} else {
										continue;
									}
									if(b.fc.checkConstraint()){
										System.out.println("Passed fitting constraints.");
										for(int counter = 0; counter < b.getListOfItems().size();counter++){

											if(b.getListOfItems().get(counter).superXXCheckAllConstraintsXXsuper()){
												System.out.println("Y=y is passing all constraints.");
												if (!temp2Results.contains(b)){
													temp2Results.add(b);
												}
												b.getListOfItems().remove(y);
												y.setAssignment(null);
											} else {
												b.getListOfItems().remove(y);
												y.setAssignment(null);
											}
										}
									} else {
										b.getListOfItems().remove(y);
										y.setAssignment(null);
									}
								}
							} else {
								continue;
							}
						}
						if (!temp2Results.isEmpty() || copy.isEmpty()) {
							result = forwardChecking(assignment,copy, temp2Results);
							System.out.println("Assignment is Successful");
							if(result != false){
								System.out.println("Current Assignment is: " + assignment);
								return true;

							}
						}

						System.out.println("FAILED TO RETURN TRUE AFTER Forward");
						c.getListOfItems().remove(tempItem);
						tempItem.setAssignment(null);
						c.fc = new FittingConstraint(c);
					} else {
						System.out.println("assignment did not pass all constraints");
						c.getListOfItems().remove(tempItem);
						tempItem.setAssignment(null);
					}
				}
			} else {
				System.out.println("Assignment DID NOT SATISFY AT ALL");

				System.out.println("Before Remove: "+ c.name+" " + c.getListOfItems());
				c.getListOfItems().remove(tempItem);
				tempItem.setAssignment(null);

				System.out.println("After Remove: " + c.name+" "+ c.getListOfItems());

				System.out.println("Before Add ItemList: " + itemList);
				System.out.println("After Add ItemList: " + itemList);

			}


		}
		finalBag = assignment;
		System.out.println(finalBag);
		System.out.println("Failing");
		return false;
	}


}
