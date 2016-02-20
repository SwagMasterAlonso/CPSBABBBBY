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
						beq = new BinaryEquals(item1, item2);
						item1.setbEquals(beq);
						beq = new BinaryEquals(item2, item1);
						item2.setbEquals(beq);
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
						bNotEq = new BinaryNotEquals(item1, item2);
						item1.setbNotEquals(bNotEq);
						bNotEq = new BinaryNotEquals(item2, item1);
						item2.setbNotEquals(bNotEq);
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

		Item tempItem;
		boolean isDone = false;
		boolean result = false;

		if(itemList.isEmpty()){
			System.out.println("IsEmpty");
			for(Bag b:bagList){
				if(b.fc.checkConstraint()){

					for(int i = 0; i < b.getListOfItems().size();i++){
						if(b.getListOfItems().get(i).superXXCheckAllConstraintsXXsuper()){
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




		System.out.println("Starting Backtrack Bittttch");

		System.out.println("Before "+itemList.size());
		tempItem = itemList.remove(0);
		System.out.println("After " + itemList.size());

		System.out.println("Removing " + tempItem);
		for(Bag c: bagList){

			System.out.println("In bag " + c);


			if(c.fc.checkConstraint()){

				System.out.println("Passed fitting");
				
				
				for(Item turnUp:c.getListOfItems()){
					System.out.println("Constraints all pass");
					if(turnUp.superXXCheckAllConstraintsXXsuper()){
						c.getListOfItems().add(tempItem);

						result = backTrack(bagList,itemList);


						if(result != false){
							return true;
						}

						c.getListOfItems().remove(tempItem);

					}


				}


			}


		}

		return false;
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
}
