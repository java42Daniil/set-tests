package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetTests {
private static final int N_RANDOM_NUMBERS = 10000;
Integer[] initialNumbers = {
	10, 20, 40, 60, 5, 25, 3, 2, 4, 1	
};
Set<Integer> set;
	@BeforeEach
	void setUp() throws Exception {
		set = new TreeSet<>();
		fillSet();
	}

	private  void fillSet() {
		fillSetFromArray(set, initialNumbers);
		
		
	}
	@Test
	void removeRootJunction() {
		Integer expected[] = {
				1, 2, 3, 4, 5,  20, 25, 40, 60
		};
		set.remove(10);
		assertArrayEquals(expected, getArrayFromSet(set));
	}
	@Test
 	void removeRootNonJunction() {
 		Integer input[] = { 1,2,3,4,5};
 		 Integer expected[] = {2,3,4,5};
 		Set<Integer> x = new TreeSet<>();
 		fillSetFromArray(x, input);
 		x.remove(1);
 		
 		assertArrayEquals(expected, getArrayFromSet(x));
 	}
	@Test
	void removeJunction() {
		Integer expected[] = {
				1, 2,  4, 5, 10, 20, 25, 40, 60
		};
		set.remove(3);
		assertArrayEquals(expected, getArrayFromSet(set));
	}
	@Test
	void removeLeaf() {
		Integer expected[] = {
				 2, 3, 4, 5, 10, 20, 25, 40, 60
		};
		set.remove(1);
		assertArrayEquals(expected, getArrayFromSet(set));
	}
	@Test
	void removeNonJunctionRight() {
		Integer expected[] = {
				1, 2, 3, 4, 5, 10,  25, 40, 60
		};
		set.remove(20);
		assertArrayEquals(expected, getArrayFromSet(set));
	}
	void removeIfTst() {
 		Integer[] expected1 = { 10, 20, 40, 60, 5, 25, 3, 2, 4, 1};
 		Integer[] expected2 = { 10, 20,  40, 5, 25, 3, 2, 4};
 		Arrays.sort(expected1);
 		Arrays.sort(expected2);
 		set.removeIf(n -> n == 1);
 		assertArrayEquals(expected1, getArrayFromSet(set));
 		set.removeIf(n -> n==60);
 		assertArrayEquals(expected2, getArrayFromSet(set));
 	}
	@Test
	void removeNonJunctionLeft() {
		Integer expected[] = {
				1, 2, 3, 4,  10, 20, 25, 40, 60
		};
		set.remove(5);
		assertArrayEquals(expected, getArrayFromSet(set));
	}
	@Test
	void removeIfTest() {
		Integer randomNumbers[] = getRandomNumbers();
		Set<Integer> setNumbers = new TreeSet<>();
		fillSetFromArray(setNumbers, randomNumbers);
		setNumbers.removeIf(n -> n % 2 == 0);
		for(Integer num: setNumbers) {
			assertFalse(num % 2 == 0);
		}
	}

	private <T> void fillSetFromArray(Set<T> res, T[] array) {
		
		for(T num: array) {
			res.add(num);
		}
		
	}
	
	private Integer[] getRandomNumbers() {
		Integer[] res = new Integer[N_RANDOM_NUMBERS];
		for (int i = 0; i < res.length; i++) {
			res[i] = (int) (Math.random() * Integer.MAX_VALUE);
		}
		return res;
		
	}

	@Test
	void addTest() {
		assertEquals(initialNumbers.length, set.size());
		for(Integer num: initialNumbers) {
			assertTrue(set.contains(num));
		}
		assertTrue(set.add(80));
		assertFalse(set.add(80));
		
	}
	@Test
	void containsTest() {
		assertTrue(set.contains(60));
		assertFalse(set.contains(80));
	}
	@Test
	void iteratorNoRemoveTest() {
		Integer[] randomNumbers = getRandomNumbers();
		Set<Integer> numbersSet = new TreeSet<>();
		fillSetFromArray(numbersSet, randomNumbers);
		Arrays.sort(randomNumbers);
		assertArrayEquals(randomNumbers, getArrayFromSet(numbersSet));
	}
	@Test
	void treeSetInsensitiveOrderTest () {
		 String strings[] = {"Boris", "Asaf", "android", "band"};
		 String expected[] = {"android", "Asaf", "band", "Boris"};
		 TreeSet<String> treeSet = new TreeSet<>((s1, s2)-> s1.compareToIgnoreCase(s2));
		 fillSetFromArray(treeSet, strings);
		 assertArrayEquals(expected, getArrayFromSet(treeSet));
	}
	@SuppressWarnings("unchecked")
	private <T> T[] getArrayFromSet(Set<T> set) {
		T res[] = (T[]) new Object[set.size()];
		int ind = 0;
		for(T obj: set) {
			res[ind++] = obj;
		}
		return res;
	}
	@Test
 	void removeAllTest() {
 		Set<Integer> other = new TreeSet<>();
 		other.add(4);
 		other.add(25);
 		Integer expected[] = {1, 2, 3, /*4,*/ 5, 10, 20, /*25,*/ 40, 60};
 		set.removeAll(other);
 		assertArrayEquals(expected, getArrayFromSet(set));
 	}
 	@Test
 	void removeAllSameTest() {
 		assertTrue(set.removeAll(set));
 		assertArrayEquals(new Integer[0], getArrayFromSet(set));
 	}
 	@Test
 	void clearTest() {
 		set.clear();
 		assertArrayEquals(new Integer[0], getArrayFromSet(set));
 	}
 	@Test
 	void retainAllTest() {
 		Set<Integer> other = new TreeSet<>();
 		other.add(3);
 		other.add(40);
 		Integer expected[] = {3, 40};
 		set.retainAll(other);
 		assertArrayEquals(expected, getArrayFromSet(set));
 	}
 	@Test
 	void retainAllSameTest() {
 		Arrays.sort(initialNumbers);
 		set.retainAll(set);
 		assertArrayEquals(initialNumbers, getArrayFromSet(set));
 	}

}