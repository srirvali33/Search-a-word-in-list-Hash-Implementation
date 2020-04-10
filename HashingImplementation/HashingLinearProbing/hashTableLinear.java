import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class hashTableLinear {
	private String[] hashTablelin;
	public int collisionsnumber;
	public int load;

	public hashTableLinear() {
		hashTablelin = new String[50];
		for (int i = 0; i < 50; i++)
			hashTablelin[i] = null;
		load = 0;
		collisionsnumber = 0;

	}

	public hashTableLinear(int n) {
		hashTablelin = new String[n];
		for (int i = 0; i < n; i++)
			hashTablelin[i] = null;
		load = 0;
		collisionsnumber = 0;

	}

	public void loadfile(File file) throws IOException {
		System.out.println("table size at starting is" + hashTablelin.length);
		BufferedReader bfr = new BufferedReader(new FileReader(file));
		String newword;
		while ((newword = bfr.readLine()) != null) {
			addkey(newword);
		}
		bfr.close();
	}

	public int hashCode(String word) {
		char[] wr = word.toCharArray();
		int c = 0;
		for (int i = 0; i < wr.length; i++) {
			c = c + (int) wr[i];
		}
		int h = 7;
		for (int i = 0; i < word.length(); i++)
			h = h * 31 + c;
		return c;
	}

	public void addkey(String value) {
		if (load + 1 >= hashTablelin.length)
			expandhashTablelin();
		int hashingKey = hashCode(value) % hashTablelin.length;
		if (hashTablelin[hashingKey] != null)
			collisionsnumber++;
		while (true) {
			if (hashTablelin[hashingKey] == null) {
				hashTablelin[hashingKey] = value;
				load++;
				break;
			}

			hashingKey++;
			if (hashingKey == hashTablelin.length)
				hashingKey = 0;

		}

	}

	private void expandhashTablelin() {
		System.out.println("*********Expanding Table size to : " + hashTablelin.length * 2 + "*******");
		String[] prevTable = hashTablelin;
		load = 0;
		collisionsnumber = 0;
		hashTablelin = new String[hashTablelin.length * 2];
		for (int i = 0; i < prevTable.length; i++)
			if (prevTable[i] != null)
				addkey(prevTable[i]);
	}

	boolean searchword(String word) {
		int hashKey = hashCode(word) % hashTablelin.length;
		if (hashTablelin[hashKey] == null) {
			return false;
		} else if (hashTablelin[hashKey].equalsIgnoreCase(word)) {
			return true;
		}
		while (hashTablelin[hashKey] != null) {
			hashKey++;
			if (hashTablelin[hashKey] == null)
				return false;
			if (hashTablelin[hashKey].equalsIgnoreCase(word))
				return true;
			if (hashKey == hashTablelin.length)
				hashKey = 0;
			if (hashKey == hashCode(word) % hashTablelin.length)
				return false;
		}
		return false;
	}

}
