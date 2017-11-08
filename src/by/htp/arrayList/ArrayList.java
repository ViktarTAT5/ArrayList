package by.htp.library.logic;

import java.util.Collection;

public class ArrayList<E> {

	private static final int DEFAULT_SIZE = 10;

	private int size;
	private Object[] mas;

	public ArrayList() {
		mas = new Object[DEFAULT_SIZE];
	}
	
	public ArrayList(int size) {
		mas = new Object[size];
	}

	public int size() {
		return size;
	}

	public E set(int index, E e) {
		rangeCheck(index);
		E temp = (E) mas[index];
		mas[index] = e;
		return temp;
	}

	public void add(E e) {
		rangeSize();
		set(size, e);
		size++;
	}

	public void add(int index, E e) {
		rangeCheck(index);
		rangeSize();
		E temp = set(index, e);
		size++;
		shiftMasAdd(index , temp);
	}

	public E get(int index) {
		return elementMas(index);
	}
	
	public E remove(int index) {
		rangeCheck(index);
		E temp = get(index);
		shiftMasRemove(index);
		return temp;
	}
	
	public boolean remove(E e) {
		int index = indexOf(e);
		if(index >= 0) {
			remove(index);
			return true;
		}
		return false;
	}
	
	public boolean contains(E e) {
		if(indexOf(e) >= 0) {
			return true;
		}
		return false;
	}
	
	public int indexOf(E e) {
		if(e != null) {
			for(int i = 0; i < size; i ++) {
				if(e.equals(mas[i])) {
				return i;	
				}
			}
		}
		return -1;
	}

	private E elementMas(int index) {
		E temp = (E) mas[index];
		return temp;
	}

	private void rangeCheck(int index) {
		if (index > size | index < 0) {
			throw new IndexOutOfBoundsException(errorBoundsMsg(index));
		}
	}
	
	private void rangeSize() {
		if(size >= mas.length * 0.8) {
			increaseMas();
		}
	}

	private String errorBoundsMsg(int index) {
		String text = "Index: " + index + "; Size: " + size + ";";
		return text;
	}
	
	private void shiftMasAdd(int index, E e) {
		E temp;
		for(int i = index + 1; i < size; i++) {
			temp = set(i, e);
			e = temp;
		}
	}
	
	private void shiftMasRemove(int index) {
		for(int i = index; i < size; i++) {
			mas[i] = mas[i + 1];
		}
		size--;
	}
	
	private void increaseMas() {
		int newSize = mas.length * 3 / 2 + 1;
		Object[] tempMas = new Object[newSize];
		overwriting(tempMas);
	}
	
	private void overwriting(Object[] tempMas) {
		for(int i = 0; i < mas.length; i++) {
			tempMas[i] = mas[i];
		}
		mas = tempMas;
	}
	
	public ArrayList<E> cloned() {
		ArrayList<E> clon = new ArrayList<>(mas.length);
		for(int i = 0; i < size; i++) {
			clon.mas[i] = mas[i];
		}
		clon.size = size;
		return clon;
	}

}
