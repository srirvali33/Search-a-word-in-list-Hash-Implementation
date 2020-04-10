import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class quadtester {

	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		HashTableQuad hashobj = new HashTableQuad(53);
		hashobj.loadfile(new File("..\\ASSIGNMENT 6\\textfile\\wordlist.txt"));
		System.out.println("total number of words inserted : " + hashobj.load);
		System.out.println("total number of collisionsnumber : " + hashobj.collisionsnumber);
		String wordentered = null;
		while (true) {
			System.out.println("enter the word you want to find or enter stop to end the search operation :");
			try {
				wordentered = bfr.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
