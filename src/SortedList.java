
public class SortedList<T extends Comparable<? super T>> implements SortedListInterface<T> {

	int numberofEntries;
	private ListInterface<T> list;

	public SortedList() {
		list = new ListLinked<T>();
	}

	public void add(T newEntry) {
		int newPosition = Math.abs(getPosition(newEntry));
		list.add(newPosition, newEntry);
		numberofEntries++;
	}

	@Override
	public boolean remove(T anEntry) {
		boolean result = false;
		int position = getPosition(anEntry);
		if (position > 0) {
			list.remove(position);
			numberofEntries--;
			result = true;
		}
		return result;
	}

	@Override
	public int getPosition(T anEntry) {
		int position = 1;
		int length = list.getLength();
		while ((position <= length) && (anEntry.compareTo(list.getEntry(position)) > 0)) {
			position++;
		}
		if ((position > length) || (anEntry.compareTo(list.getEntry(position)) != 0)) {
			position = -position;
		}
		return position;
	}

	@Override
	public T getEntry(int givenPosition) {
		return list.getEntry(givenPosition);
	}

	@Override
	public boolean contains(T anEntry) {
		if (list.contains(anEntry))
			return true;
		return false;
	}

	@Override
	public T remove(int givenPosition) {
		T item = null;
		item = list.remove(givenPosition);
		return item;
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public int getLength() {
		return numberofEntries;
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public T[] toArray() {		System.out.println("In");

		return list.toArray();

	}

}
