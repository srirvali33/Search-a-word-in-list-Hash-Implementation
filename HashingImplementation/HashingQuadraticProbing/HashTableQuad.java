import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashTableQuad {
	private String[] hashTablequa;
	public int collisionsnumber;
	public int load;

	public HashTableQuad() {
		hashTablequa = new String[50];
		for (int i = 0; i < 50; i++)
			hashTablequa[i] = null;
		collisionsnumber = 0;
		load = 0;

	}

	public HashTableQuad(int n) {
		hashTablequa = new String[n];
		for (int i = 0; i < n; i++)
			hashTablequa[i] = null;
		collisionsnumber = 0;
		load = 0;

	}

	public void loadfile(File file) throws IOException {
		System.out.println("table size at starting is" + hashTablequa.length);
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
			c += (int) wr[i];
		}
		int h = 7;
		for (int i = 0; i < word.length(); i++)
			h = h * 31 + c;
		return c;
	}

	public void addkey(String word) {
		if (load + 1 >= hashTablequa.length)
			expandHashTable();
		int hashKey = hashCode(word) % hashTablequa.length;
		int probfac = 1;
		if (hashTablequa[hashKey] != null)
			collisionsnumber++;
		while (true) {
			if (hashTablequa[hashKey] == null) {
				hashTablequa[hashKey] = word;
				load++;
				break;
			}

			hashKey += (probfac * probfac);
			probfac++;
			hashKey = hashKey % hashTablequa.length;

		}

	}

	private void expandHashTable() {
		System.out.println("*********Expanding Table size to : " + hashTablequa.length * 2 + "*******");
		String[] prevTable = hashTablequa;
		collisionsnumber = 0;
		load = 0;
		hashTablequa = new String[hashTablequa.length * 2];
		for (int i = 0; i < prevTable.length; i++)
			if (prevTable[i] != null)
				addkey(prevTable[i]);
	}

	boolean searchword(String word) {
		int hashKey = hashCode(word) % hashTablequa.length;
		int probfac = 1;
		if (hashTablequa[hashKey] == null) {
			return false;
		} else if (hashTablequa[hashKey].equalsIgnoreCase(word)) {
			return true;
		}
		while (hashTablequa[hashKey] != null) {
			hashKey += (probfac * probfac);
			probfac++;
			hashKey = hashKey % hashTablequa.length;
			if (hashTablequa[hashKey] == null)
				return false;
			if (hashTablequa[hashKey].equalsIgnoreCase(word))
				return true;
			if (hashKey == hashTablequa.length)
				hashKey = 0;
			if (hashKey == hashCode(word) % hashTablequa.length)
				return false;
		}
		return false;
	}

}
