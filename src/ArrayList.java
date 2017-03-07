import java.util.Arrays;

public class ArrayList<T> implements ListInterface<T> {
	private T[] list;
	private int numberOfEntries;
	private static final int DEFAULT_INITIAL_CAPACITY = 10;

	public ArrayList() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	public ArrayList(int initialCapacity) {
		numberOfEntries = 0;
		@SuppressWarnings("unchecked")
		T[] tempList = (T[]) new Object[initialCapacity];
		list = tempList;
	}

	public void add(T newEntry) {
		ensureCapacity();
		list[numberOfEntries] = newEntry;
		numberOfEntries++;
	}

	public void add(int newPosition, T newEntry) {
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
			ensureCapacity();
			makeRoom(newPosition);
			list[newPosition - 1] = newEntry;
			numberOfEntries++;
		}
	}

	private void makeRoom(int newPosition) {
		assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
		int newIndex = newPosition - 1;
		int lastIndex = numberOfEntries - 1;
		for (int index = lastIndex; index >= newIndex; index--)
			list[index + 1] = list[index];
	}

	public T remove(int givenPosition) {
		T result = null;
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			result = list[givenPosition - 1];
			if (givenPosition < numberOfEntries)
				removeGap(givenPosition);
			numberOfEntries--;
		} // end if
		return result;
	}

	private void removeGap(int givenPosition) {
		assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
		int removedIndex = givenPosition - 1;
		int lastIndex = numberOfEntries - 1;
		for (int index = removedIndex; index < lastIndex; index++)
			list[index] = list[index + 1];
	}

	public void clear() {
	}

	public T replace(int givenPosition, T newEntry) {
		T result = null;
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			result = list[givenPosition - 1];
			list[givenPosition - 1] = newEntry;
		}
		return result;
	}

	public T getEntry(int givenPosition) {
		T result = null;
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			result = list[givenPosition - 1];
		}
		return result;
	}

	public boolean contains(T anEntry) {
		boolean found = false;
		for (int index = 0; !found && (index < numberOfEntries); index++) {
			if (anEntry.equals(list[index]))
				found = true;
		}
		return found;
	}

	public int getLength() {
		return numberOfEntries;
	} 

	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		for (int index = 0; index < numberOfEntries; index++) {
			result[index] = list[index];
		} 
		return result;
	} 

	private void ensureCapacity() {
		if (numberOfEntries == list.length)
			list = Arrays.copyOf(list, 2 * list.length);
	}
}