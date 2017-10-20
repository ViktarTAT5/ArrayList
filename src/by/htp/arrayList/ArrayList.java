package by.htp.arrayList;

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

	private E set(int index, E e) {
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
		shiftMas(index , temp);
	}

	public E get(int index) {
		return elementMas(index);
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
	
	private void shiftMas(int index, E e) {
		E temp;
		for(int i = index + 1; i < size; i++) {
			temp = set(i, e);
			e = temp;
		}
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

}
