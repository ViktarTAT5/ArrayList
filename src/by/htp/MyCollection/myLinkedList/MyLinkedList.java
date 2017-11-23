package by.htp.myCollection.myLinkedList;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E>, Deque<E> {

	private int size;
	Node<E> first;
	Node<E> last;
	Node<E> iterator = first;

	public MyLinkedList() {
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (first == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		if (o != null) {
			for (Node<E> temp = first; temp != null; temp = temp.next) {
				if (o.equals(temp.item)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return listIterator();
	}

	@Override
	public Object[] toArray() {
		Object[] tepm = new Object[size];
		int i = 0;
		for (Node<E> node = first; node != null; node = node.next) {
			tepm[i++] = node.item;
		}
		return tepm;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		Object[] temp;
		if (a.length < size) {
			temp = new Object[size];
		} else {
			temp = new Object[a.length];
		}
		int i = 0;
		for (Node<E> node = first; node != null; node = node.next) {
			temp[i++] = node.item;
		}
		a = (T[]) temp;
		return a;
	}

	public void addFirst(E element) {
		if (first != null) {
			first.prev = new Node<E>(null, element, first);
			size++;
			first = first.prev;
		} else {
			first = new Node<E>(null, element, null);
			size++;
			last = first;
		}
	}

	public void addLast(E element) {
		if (last != null) {
			last = new Node<E>(last, element, null);
			size++;
		} else {
			addFirst(element);
		}
	}

	@Override
	public boolean add(E e) {

		Node<E> temp;
		if (size != 0) {
			temp = new Node<E>(last, e, null);
			last.next = temp;
			last = temp;
			size++;
		} else {
			addFirst(e);
		}
		return true;

	}

	@Override
	public boolean remove(Object o) {
		if (o == null)
			for (Node<E> temp = first; temp != null; temp = temp.next) {
				if (o == temp.item) {
					castOut(temp);
					return true;
				}
			}

		for (Node<E> temp = first; temp != null; temp = temp.next) {
			if (o.equals(temp.item)) {
				castOut(temp);
				return true;
			}
		}
		return false;
	}

	private void castOut(Node<E> node) {
		if (size == 1) {
			clear();
			return;
		}
		if (node == first) {
			first = node.next;
			node.next.prev = null;
		} else if (node == last) {
			last = node.prev;
			node.prev.next = null;
		} else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}
		size--;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection c) {
		return addAll(size - 1, c);
	}

	@Override
	public boolean addAll(int index, Collection c) {
		chekIndex(index);
		Object[] mas = c.toArray();
		if (index != size - 1) {
			Node<E> node = getNode(index);
			for (Object i : mas) {
				Node<E> temp = new Node<E>(node.prev, (E) i, node);
				node.prev.next = temp;
				node.prev = temp;
				size++;
			}
		} else {
			for (Object i : mas)
				add((E) i);
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		int oldSize = size;
		Object[] o = c.toArray();
		E[] mas = (E[]) o;
		for (E i : mas) {
			remove(i);
		}
		if (oldSize == size)
			return false;
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	@Override
	public E get(int index) {
		chekIndex(index);
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node<E> cell = getNode(index);
		if (cell != null) {
			return cell.item;
		} else {
			return null;
		}
	}

	@Override
	public E getFirst() {
		if (first != null)
			return first.item;
		return null;
	}

	@Override
	public E getLast() {
		if (last != null)
			return last.item;
		return null;
	}

	private Node<E> getNode(int index) {
		chekIndex(index);
		Node<E> temp = null;
		if (index < size) {

			if (index < size / 2) {
				temp = first;
				for (int i = 0; i < index; i++) {
					temp = temp.next;
				}

			} else {
				temp = last;
				for (int i = size - 1; i > index; i--) {
					temp = temp.prev;
				}
			}
		}
		return temp;
	}

	private void chekIndex(int index) {
		if (!(index >= 0 && index < size))
			throw new IndexOutOfBoundsException();
	}

	@Override
	public E set(int index, E e) {
		chekIndex(index);
		Node<E> node = getNode(index);
		E temp = node.item;
		node.item = e;
		return temp;
	}

	@Override
	public void add(int index, E e) {
		chekIndex(index);
		Node<E> node = getNode(index);
		thrustIn(node, e);
	}

	private void thrustIn(Node<E> node, E e) {
		Node<E> temp;
		if (node == first)
			addFirst(e);
		temp = new Node<E>(node.prev, e, node);
		node.prev.next = temp;
		node.prev = temp;
		size++;
	}

	@Override
	public E remove(int index) {
		chekIndex(index);
		Node<E> node = getNode(index);
		castOut(node);
		return node.item;
	}

	@Override
	public int indexOf(Object o) {
		Node<E> temp = null;
		if (o != null) {
			temp = first;
			for (int i = 0; i < size; i++) {
				if (temp.item.equals(o))
					return i;
				temp = temp.next;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		Node<E> temp = null;
		if (o != null) {
			temp = last;
			for (int i = size - 1; i >= 0; i--) {
				if (temp.item.equals(o))
					return i;
				temp = temp.prev;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() {
		return new ListItr(0);
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new ListItr(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev;

		Node(Node<E> prev, E element, Node<E> next) {
			this.item = element;
			this.prev = prev;
			this.next = next;
		}
	}

	@Override
	public boolean offerFirst(E e) {
		addFirst(e);
		return true;
	}

	@Override
	public boolean offerLast(E e) {
		addLast(e);
		return true;
	}

	@Override
	public E removeFirst() {
		if (first == null)
			return null;
		Node<E> node = first;
		castOut(node);
		return node.item;
	}

	@Override
	public E removeLast() {
		if (last == null)
			return null;
		Node<E> node = last;
		castOut(node);
		return node.item;
	}

	@Override
	public E pollFirst() {
		return removeFirst();
	}

	@Override
	public E pollLast() {
		return removeLast();
	}

	@Override
	public E peekFirst() {
		if (first != null)
			return first.item;
		return null;
	}

	@Override
	public E peekLast() {
		if (last != null)
			return last.item;
		return null;
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		return remove(o);
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		if (o == null) {
			for (Node<E> node = last; node.prev != null; node = node.prev) {
				if (node.item == null) {
					node.prev.next = node.next;
					node.next.prev = node.prev;
					return true;
				}
			}
		} else {
			for (Node<E> node = last; node.prev != null; node = node.prev) {
				if (o.equals(o)) {
					node.prev.next = node.next;
					node.next.prev = node.prev;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean offer(E e) {
		return offerLast(e);
	}

	@Override
	public E remove() {
		return removeFirst();
	}

	@Override
	public E poll() {
		return removeFirst();
	}

	@Override
	public E element() {
		return getFirst();
	}

	@Override
	public E peek() {
		return peekFirst();
	}

	@Override
	public void push(E e) {
		addFirst(e);
	}

	@Override
	public E pop() {
		return removeFirst();
	}

	@Override
	public Iterator<E> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	private class ListItr implements ListIterator<E> {
		private Node<E> lastActual;
		private Node<E> next;
		private int indexItr;

		ListItr(int index) {
			next = getNode(index);
			if (index == size) {
				next = null;
				lastActual = last;
			}
			if (index > 0)
				lastActual = next.prev;
			indexItr = index;
		}

		@Override
		public boolean hasNext() {
			if (next != null)
				return true;
			return false;
		}

		@Override
		public E next() {
			lastActual = next;
			next = lastActual.next;
			indexItr++;
			return lastActual.item;
		}

		@Override
		public boolean hasPrevious() {
			if (lastActual != null)
				return true;
			return false;
		}

		@Override
		public E previous() {
			next = lastActual;
			lastActual = next.prev;
			indexItr--;
			return next.item;
		}

		@Override
		public int nextIndex() {
			return indexItr;
		}

		@Override
		public int previousIndex() {
			return indexItr--;
		}

		@Override
		public void remove() {
			if (lastActual == null)
				throw new IllegalStateException();
			castOut(lastActual);
			// TODO This call can be made only if neither remove nor add have
			// been called after the last call to next or previous.
		}

		@Override
		public void set(E e) {
			lastActual.item = e;
			// TODO This call can be made only if neither remove nor add have
			// been called after the last call to next or previous.}
		}

		@Override
		public void add(E e) {
			thrustIn(lastActual, e);
		}
	}
}
