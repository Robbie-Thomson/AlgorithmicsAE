import java.io.*;
import java.util.*;

/**
 * program to find compression ratio using LZW compression
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();
		String inputFileName = args[0];
		FileReader reader = new FileReader(inputFileName);

		// initialise array list of characters
		List<Character> aChars = new ArrayList<>();

		// initialise iterator
		int i;
		// iterate the reader

		while ((i = reader.read()) != -1) {

			// add to char list
			aChars.add((char) i);
		}
		reader.close();

		// Initialise variables, Trie, Dictionary size, wordSize, totalSize
		Trie trie = new Trie();
		int dSize = 128;
		int wSize = 8;
		int tSize = 0;

		// set codes
		String lastCode = "";
		String nextCode = "";

		// iterate through to insert into trie
		for (int chr = 0; chr <= 128; chr++) {
			trie.insert(((char) chr) + "");
		}

		// iterate through the input characters
		for (char current : aChars) {

			// set new code
			nextCode = lastCode + current;

			// check if new code is not in trie
			if (!trie.search(nextCode)) {

				// insert new code node in trie
				trie.insert(nextCode);

				// increase the total size
				tSize += wSize;

				// check if the lenghth of the code can fir in the word size
				if (dSize >= Math.pow(2, wSize)) {

					// increment length
					wSize += 1;
				}
				// increment dictionary size
				dSize += 1;
				
				// reinitialise next code
				lastCode = current + "";
				
			} else {

				// reinitialise next code
				lastCode = nextCode;
			}
		}

		// check if new Code is not empty
		if (nextCode != "") {

			// add code into the trie
			trie.insert(nextCode);

			// add the word sixe to the final size
			tSize += wSize;
		}

		// calculate the original file size
		float oFile = (aChars.size() * 8);
		// calculate the compression ratio
		float ratio = (tSize / oFile);

		// write out the results here
		System.out.println("Original file length in bits = " + (aChars.size() * 8));
		System.out.println("Compressed file length in bits = " + tSize);
		System.out.println("Compression ratio = " + ratio);
		// print the time taken for the compression to be calculated
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
	}

}
