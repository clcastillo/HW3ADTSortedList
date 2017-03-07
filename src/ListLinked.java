
public class ListLinked<T> implements ListInterface<T> {
	private Node firstNode;
	private int numberOfEntries = 0;

	public ListLinked() {
		clear();
	}

	private Node getNodeAt(int givenPosition) {
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;

		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.getNextNode();
		assert currentNode != null;
		return currentNode;
	}

	private class Node {
		private T data;
		private Node next;

		private Node(T dataPortion) {
			this(dataPortion, null);
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getNextNode() {
			return next;
		}

		public void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}

	@Override
	public void add(T item) {
		Node newNode = new Node(item);
		if (isEmpty())
			firstNode = newNode;
		else {
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode);
		}
		numberOfEntries++;

	}

	@Override
	public void add(int location, T item) {
		if ((location >= 1) && (location <= numberOfEntries + 1)) {
			Node newNode = new Node(item);
			if (location == 1) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			} else {
				Node nodeBefore = getNodeAt(location - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
		}
	}

	@Override
	public T remove(int location) {

		T result = null;
		if ((location >= 1) && (location <= numberOfEntries)) {
			assert !isEmpty();
			if (location == 1) {
				result = firstNode.getData();
				firstNode = firstNode.getNextNode();
			} else {
				Node nodeBefore = getNodeAt(location - 1);
				Node nodeToRemove = nodeBefore.getNextNode();
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);

				result = nodeToRemove.getData();
			}
			numberOfEntries--;
		}
		return result;
	}

	@Override
	public void clear() {
		for (int i = 0; i < numberOfEntries; i++) {
			remove(i);
		}
		firstNode = null;
		numberOfEntries = 0;
	}

	@Override
	public T replace(int location, T item) {
		T result = null;
		if ((location >= 1) && (location <= numberOfEntries)) {
			assert !isEmpty();
			Node desiredNode = getNodeAt(location);
			result = desiredNode.getData();
			desiredNode.setData(item);
		}
		return result;
	}

	@Override
	public T getEntry(int item) {
		T result = null;
		if ((item >= 1) && (item <= numberOfEntries)) {
			assert !isEmpty();
			result = getNodeAt(item).getData();
		}
		return result;
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}
		return result;
	}

	@Override
	public int getLength() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		boolean result;
		if (numberOfEntries == 0) {
			assert firstNode == null;
			result = true;
		} else {
			assert firstNode != null;
			result = false;
		}
		return result;
	}

	public boolean contains(T item) {
		boolean found = false;
		T result = null;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (item.equals(currentNode.getData())) {
				found = true;
				result = currentNode.getData();
			} else
				currentNode = currentNode.getNextNode();
		}
		return found;
	}
}