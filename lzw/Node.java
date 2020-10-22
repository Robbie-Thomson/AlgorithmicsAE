
public class Node {

	private char letter; // label on incoming branch
	private boolean isWord; // true when node represents a word
	private Node sibling; // next sibling (when it exists)
	private Node child; // first child (when it exists)

	/** create a new node with letter c */
	public Node(char c) {
		setLetter(c);
		setWord(false);
		setSibling(null);
		setChild(null);
	}

	public Node getChild() {
		return child;
	}

	public void setChild(Node child) {
		this.child = child;
	}

	public Node getSibling() {
		return sibling;
	}

	public void setSibling(Node sibling) {
		this.sibling = sibling;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public boolean isWord() {
		return isWord;
	}

	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}
}
