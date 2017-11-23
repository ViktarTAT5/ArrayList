package by.htp.myCollection.myLinkedList;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

public class MyLInkedListTest {
	private MyLinkedList<String> list;
	private String[] data;
	private List<String> dataAdd;
	private List<String> dataRemove;
	private String temp = new String("temp");

	@Before
	public void init() {
		list = new MyLinkedList<>();
		data = new String[] { "asdf", "qwer", "qwer", "zxcv", "1234" };
		dataAdd = new ArrayList<>();
		dataRemove = new ArrayList<>();
	}

	private void initData() {
		list.clear();
		for (String i : data)
			list.add(i);
		dataAdd.add("UIO");
		dataAdd.add("JKL");
		dataAdd.add("BNM");

		dataRemove.add("qwer");
		dataRemove.add("1234");

	}

	@Test
	public void testSize() {
		list.clear();
		assertEquals(list.size(), 0);
		initData();
		assertEquals(list.size(), data.length);
		list.remove(data[2]);
		assertEquals(list.size(), data.length - 1);
	}

	@Test
	public void testIsEmpty() {
		list.clear();
		assertEquals(list.isEmpty(), true);
		list.add(data[1]);
		assertEquals(list.isEmpty(), false);
	}

	@Test
	public void testContains() {
		list.clear();
		assertEquals(list.contains(data[1]), false);
		initData();
		assertEquals(list.contains(data[1]), true);
		assertEquals(list.contains("777"), false);
	}

	@Test
	public void testToArray() {
		initData();
		Object[] mas = new Object[] { "asdf", "qwer", "qwer", "zxcv", "1234" };
		Object[] tempMas = list.toArray();
		for (int i = 0; i < tempMas.length; i++) {
			assertEquals(mas[i], tempMas[i]);
		}
	}

	@Test
	public void testToArrayT() {
		// TODO
	}

	@Test
	public void testAddFirst() {
		list.clear();
		String temp = new String("123");
		list.addFirst(temp);
		assertEquals(list.getFirst(), "123");
		initData();
		temp = new String("456");
		list.addFirst(temp);
		assertEquals("456", list.getFirst());
		int size = list.size();
		assertEquals(list.size(), size++);
	}

	@Test
	public void testAddLast() {
		list.clear();
		String temp = new String("123");
		list.addLast(temp);
		assertEquals(list.getLast(), "123");
		initData();
		temp = new String("456");
		int size = list.size();
		list.addLast(temp);
		assertEquals("456", list.getLast());
		assertEquals(list.size(), size + 1);
	}

	@Test
	public void testAdd() {
		initData();
		int size = list.size();
		String temp = new String("123");
		list.add(temp);
		assertEquals(list.getLast(), temp);
		assertEquals(list.size(), size + 1);
	}

	@Test
	public void testRemove() {
		initData();
		int size = list.size();
		list.remove(data[1]);
		assertEquals(list.size(), size - 1);
		assertEquals(list.contains(data[2]), true);
	}

	@Test
	public void testAddAll() {
		initData();
		int size = list.size();
		String oldLast = list.getLast();
		list.addAll(dataAdd);
		size += dataAdd.size();
		assertEquals(size, list.size());
		assertEquals(oldLast, list.get(size - dataAdd.size() - 1));
		int j = dataAdd.size() - 1;
		int end = list.size() - dataAdd.size() - 1;
		for (int i = list.size() - 1; i > end; i--, j--) {
			assertEquals(list.get(i), dataAdd.get(j));
		}
	}

	@Test
	public void testAddAllIndex() {
		initData();
		int index = 2;
		int size = list.size() + dataAdd.size();
		String oldLast = list.get(index);
		list.addAll(index, dataAdd);
		assertEquals(size, list.size());
		assertEquals(oldLast, list.get(index + dataAdd.size()));
		int end = list.size() - dataAdd.size() - 1;
		for (int i = index, j = 0; i < end; i++, j++) {
			assertEquals(list.get(i), dataAdd.get(j));
		}
	}

	@Test
	public void testRemoveAll() {
		initData();
		int size = list.size() - dataRemove.size(); // TODO contains
		list.removeAll(dataRemove);
		assertEquals(size, list.size());
	}

	@Test
	public void testRetainAll() {
		// TODO Auto-generated method stub
	}

	@Test
	public void testClear() {
		list.clear();
		assertEquals(0, list.size());
		assertEquals(null, list.getFirst());
		assertEquals(null, list.getLast());
	}

	@Test
	public void testGet() {
		initData();
		assertEquals(data[1], list.get(1));
		assertEquals(data[3], list.get(3));
	}

	@Test
	public void testGetFirst() {
		initData();
		assertEquals(data[0], list.getFirst());
		list.addFirst(temp);
		assertEquals(temp, list.getFirst());
	}

	@Test
	public void testGetLast() {
		initData();
		assertEquals(data[data.length - 1], list.getLast());
		list.addLast(temp);
		assertEquals(temp, list.getLast());
	}

	@Test
	public void testSet() {
		initData();
		int size = list.size();
		int index = 2;
		String old = list.get(index);
		assertEquals(old, list.set(index, temp));
		assertEquals(size, list.size());
		assertEquals(temp, list.get(index));
	}

	@Test
	public void testAddIndex() {
		initData();
		int size = list.size();
		int index = 2;
		list.add(index, temp);
		assertEquals(size + 1, list.size());
		assertEquals(temp, list.get(index));

	}

	@Test
	public void testRemoveIndex() {
		initData();
		int size = list.size();
		int index = 3;
		String old = list.get(index);
		assertEquals(old, list.remove(index));
		assertEquals(size - 1, list.size());
		assertNotEquals(temp, list.get(index));
	}
	
	@Test
	public void testIndexOf(){
		initData();
		int index = 1;
		assertEquals(index, list.indexOf(data[index]));
	}
	
	@Test
	public void testLastIndexOf(){
		initData();
		int index = 2;
		assertEquals(index, list.lastIndexOf(data[index]));
	}
}
