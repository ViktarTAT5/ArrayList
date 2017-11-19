package by.htp.myCollection.myLinkedList;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MyLInkedListTest {
	private MyLinkedList<String> list;

	@Before
	public void init() {
		list = new MyLinkedList<>();

	}

	private void initData() {
		list.clear();
		list.add(new String("asdf"));
		list.add(new String("qwer"));
		list.add(new String("qwer"));
		list.add(new String("zxcv"));
	}

	@Test
	public void testSize() {
		list.clear();
		assertEquals(list.size(), 0);
		initData();
		assertEquals(list.size(), 4);
		list.remove(new String("qwer"));
		assertEquals(list.size(), 3);
	}

	@Test
	public void testIsEmpty() {
		list.clear();
		assertEquals(list.isEmpty(), true);
		list.add(new String("asdf"));
		assertEquals(list.isEmpty(), false);
	}

	@Test
	public void testContains() {
		list.clear();
		assertEquals(list.contains("asdf"), false);
		initData();
		assertEquals(list.contains("asdf"), true);
		assertEquals(list.contains("123"), false);
	}

	@Test
	public void testToArray() {
		initData();
		Object[] mas = new Object[] { "asdf", "qwer", "qwer", "zxcv" };
		Object[] tempMas = list.toArray();
		for (int i = 0; i < tempMas.length; i++) {
			assertEquals(mas[i], tempMas[i]);
		}
	}
	
	@Test
	public void testToArrayT(){
		//TODO
	}
	
	@Test
	public void testAddFirst(){
		list.clear();
		String temp = new String("123");
		list.addFirst(temp);
		assertEquals(list.getFirst(), "123");
		initData();
		temp = list.getFirst();
		list.addFirst("456");
		assertEquals("456", list.getFirst());
		int size = list.size();
		assertEquals(list.size(), size++);
	}
	
	
	
	
	
	
}
