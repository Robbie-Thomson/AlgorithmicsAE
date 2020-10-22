import java.io.*;
import java.util.*;

/**
 * program to find compression ratio using Huffman coding
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();
		String inputFileName = args[0];
		FileReader reader = new FileReader(inputFileName);

		// initialise array of all characters
		ArrayList<Character> aChars = new ArrayList<>();
		// initialise a set of the unique characters
		Set<Character> uChars = new HashSet<Character>();

		// initialise iterator
		int i;
		// while i in reader != -1
		while (!((i = reader.read()) == -1)) {

			// add to char list
			aChars.add((char) i);
			// add to unique
			uChars.add((char) i);
		}

		reader.close();

		// priority queue
		PriorityQueue<Integer> priorityQ = new PriorityQueue<>();

		// iterate through each unique character
		for (Character unique : uChars) {
			// initialise frequency of character
			int count = 0;
			// loop through all chars

			for (Character curChar : aChars) {

				// check if the character at the current position is the same as the unique character
				if (curChar == unique) {

					// add to the frequency of the characer
					count += 1;
				}
			}

			// add the frequency to the queue
			priorityQ.add(count);
		}

		// set overall weight to 0
		int totalWeight = 0;

		// loop until the priority queue has less than or only 1 value in it
		while (priorityQ.size() > 1) {

			// find next 2 children in priority queue
			int left = priorityQ.poll();
			int right = priorityQ.poll();

			// add 2 children to overall weight
			totalWeight += (left + right);
			// insert the sum of the children back into the priority queue
			priorityQ.add(left + right);
		}

		// calculate the original file size
		float oFile = (aChars.size() * 8);
		// calculate the compression ratio
		float ratio = (totalWeight / oFile);

		// output the results here
		System.out.println("Original file length in bits = " + (aChars.size() * 8));
		System.out.println("Compressed file length in bits = " + totalWeight);
		System.out.println("Compression ratio = " + ratio);

		// end timer and print elapsed time as last line of output
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
	}

}
