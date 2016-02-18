import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


	static List<Bag> listOfBags = new ArrayList<Bag>();

	static List<Item> listOfItems = new ArrayList<Item>();


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
					if (line.contains("#####") && line.contains("unary exclusive")) {
						System.out.println("entering section "+line);
						counter++;
						break;
					} else {
						System.out.println("Reading unary inclusive info.");
						continue;
					}
				case 4:
					if (line.contains("#####") && line.contains("binary equals")) {
						System.out.println("entering section "+line);
						counter++;
						break;
					} else {
						System.out.println("Reading unary exclusive info.");
						continue;
					}
				case 5:
					if (line.contains("#####") && line.contains("binary not")) {
						System.out.println("entering section "+line);
						counter++;
						break;
					} else {
						System.out.println("Reading binary equals info.");
						String[] fields = line.split(" ");
						String item1 = fields[0];
						String item2 = fields[1];
						System.out.println("item "+item1+" must be with "+item2);
						continue;
					}
				case 6:
					if (line.contains("#####") && line.contains("binary simultaneous")) {
						System.out.println("entering section "+line);
						counter++;
						break;
					} else {
						System.out.println("Reading binary not equals info.");
						String[] fields = line.split(" ");
						String item1 = fields[0];
						String item2 = fields[1];
						System.out.println("item "+item1+" cannot be with "+item2);
						continue;
					}
				case 7:
					System.out.println("Reading binary simultaneous info.");
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

}
