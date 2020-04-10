import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class lineartester {

	public static void main(String[] args) throws IOException {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		hashTableLinear hashobj = new hashTableLinear(53);
		hashobj.loadfile(new File("..\\ASSIGNMENT 6\\textfile\\wordlist.txt"));
		System.out.println("Number of words inserted : " + hashobj.load);
		System.out.println("Number of collisions : " + hashobj.collisionsnumber);
		String wordentered;
		while (true) {
			System.out.println("enter the word you want to find or enter stop to end the search operation :");
			wordentered = bfr.readLine();
			if (wordentered.equalsIgnoreCase("STOP")) {
				System.out.println("Shutting down");
				System.exit(0);
			}
			if (hashobj.searchword(wordentered))
				System.out.println("Required match found the given word ''" + wordentered + "''");
			else
				System.out.println("No match found for the given word");
		}

	}

}
