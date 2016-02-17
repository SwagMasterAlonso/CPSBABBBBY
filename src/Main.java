import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		String fileName = null, line;
		String item, bag;
		int weight = 0;
		int counter = 0;
		//get command line arguments, there should only be 2
		if(args.length==2){
			String inputString = args[0];

			fileName = inputString;
		} else {
			System.out.println("Not enough input arguments");
			System.exit(0);
		}

		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				switch(counter) {
					case 0:
						if (line.contains("#####") && line.contains("variables")) {
							break;
						} else if (line.contains("#####") && line.contains("values")) {
							counter++;
							break;
						} else {
							String[] fields = line.split(" ");
							item = fields[0];
							weight = Integer.parseInt(fields[1]);
							continue;
						}
					case 1:
						if (line.contains("#####") && line.contains("values")) {
							break;
						} else if (line.contains("#####") && line.contains("fitting limits")) {
							counter++;
							break;
						} else {
							String[] fields = line.split(" ");
							bag = fields[0];
							weight = Integer.parseInt(fields[1]);
							continue;
						}
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
