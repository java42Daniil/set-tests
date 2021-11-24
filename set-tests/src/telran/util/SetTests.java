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
		//set = new TreeSet<>();
		set = new HashSet<>(3);
		fillSet();
	}

	private  void fillSet() {
		fillSetFromArray(set, initialNumbers);
		
		
	}
	@Test
	void removeRoot() {
		Integer expected[] = {
				1, 2, 3, 4, 5,  20, 25, 40, 60
		};
		set.remove(10);
		assertArrayEquals(expected, getArrayFromSet(set));
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
		//Set<Integer> setNumbers = new TreeSet<>();
		Set<Integer> setNumbers = new HashSet<>();
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
		//Set<Integer> numbersSet = new TreeSet<>();
		Set<Integer> numbersSet = new HashSet<>();
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
		if (!(set instanceof TreeSet)) {
			Arrays.sort(res);
		}
		return res;
	}
	@Test
	void removeContainsBased() {
		Integer expected[] = {
				1, 2, 3, 4,  10, 20, 25, 40, 60
		};
		set.remove(5);
		assertFalse(set.contains(5));
		for (Integer num: expected) {
			assertTrue(set.contains(num));
		}
	}
	
	

}