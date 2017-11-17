package by.htp.myCollection.myLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E> {

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
			Node<E> temp = first;
			for (int i = 0; i < size; i++) {
				if (o.equals(temp)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Object e) {
		if (e != null) {
			Node<E> temp;
			if (size != 0) {
				temp = new Node(last, e, null);
				last.next = temp;
				last = temp;
				size++;
			} else {
				temp = new Node(null, e, null);
				first = temp;
				last = temp;
				size++;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {
		if (o != null) {
			Node<E> temp = first;
			for (int i = 0; i < size; i++) {
				if (o.equals(temp)) {
					temp.prev.prev = temp.next.next;
					temp.next.next = temp.prev.prev;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
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

	private Node<E> getNode(int index) {
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

	@Override
	public E set(int index, Object element) {
		Node<E> node = getNode(index);
		E e = node.item;
		node.item = (E) element;
		return e;
	}

	@Override
	public void add(int index, Object element) {
		Node<E> node = getNode(index);
		Node<E> temp = new Node(node.prev, element, node);
		node.prev = temp;
	}

	@Override
	public E remove(int index) {
		Node<E> node = getNode(index);
		Node<E> nodeNext = node.next;
		Node<E> nodePrev = node.prev;
		//TODO fix
		nodeNext.prev = nodePrev;
		nodePrev.next = nodeNext;
		return node.item;
	}

	@Override
	public int indexOf(Object o) {
		Node<E> temp = null;
		if (o != null) {
			temp = first;
			for (int i = 0; i < size; i++) {
				temp = temp.next;
				if (temp.item.equals(o))
					return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		Node<E> temp = null;
		if (o != null) {
			temp = first;
			for (int i = size; i > 0; i--) {
				temp = temp.next;
				if (temp.item.equals(o))
					return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
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

}
