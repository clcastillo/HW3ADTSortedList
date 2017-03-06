import java.util.Vector;

public class List<T> implements ListInterface<T> {
	private Vector<T> list;
	private int count = 0;

	public List() {
		list = new Vector<T>();
	}

	public List(int initalSize) {
		list = new Vector<T>(initalSize);
	}

	@Override
	public void add(T newEntry) {
		count++;
		list.add(newEntry);
	}

	@Override
	public void add(int newPosition, T newEntry) {
		if (newPosition < 1 || newPosition > getLength() + 1) {
			throw new IndexOutOfBoundsException("Index  is out of bounds!");
		} else {
			list.add(newEntry);
			count++;
		}
	}

	@Override
	public T remove(int givenPosition) {
		T result = null;

		if (givenPosition < 1 || givenPosition > getLength()) {
			throw new IndexOutOfBoundsException("Index  is out of bounds!");
		}
		if ((givenPosition >= 1) && (givenPosition <= list.size())) {
			assert !isEmpty();
			result = list.remove(givenPosition - 1);
		}
		count--;
		return result;
	}

	@Override
	public void clear() {
		if (!isEmpty()) {
			for (int i = 0; i < count; i++) {
				remove(i);
			}
			count = 0;
		}
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		if (givenPosition < 1 || givenPosition > getLength()) {
			throw new IndexOutOfBoundsException("Index  is out of bounds!");
		}
		T item = null;
		if ((givenPosition >= 1) && (givenPosition <= list.size())) {
			assert !isEmpty();
			item = list.get(givenPosition);
			list.set(givenPosition - 1, newEntry);
		}
		return item;
	}

	@Override
	public T getEntry(int givenPosition) {
		T result = null;

		if (givenPosition < 1 || givenPosition > getLength()) {
			throw new IndexOutOfBoundsException("Index  is out of bounds!");
		} else if ((givenPosition >= 1) && (givenPosition <= list.size())) {
			assert !isEmpty();
			result = list.get(givenPosition - 1);
		}
		return result;
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) list.toArray();
		return result;
	}

	@Override
	public boolean contains(T anEntry) {
		boolean end = false;
		T result = null;
		for (int i = 0; i < count; i++) {
			if (list.contains(anEntry)) {
				result = anEntry;
			}
		}
		if (result != null)
			end = true;

		return end;
	}

	@Override
	public int getLength() {
		return this.count;

	}

	@Override
	public boolean isEmpty() {
		if (count != 0)
			return false;
		else
			return true;
	}

}
