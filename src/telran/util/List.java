package telran.util;

import java.util.Comparator;
import java.util.function.Predicate;

public interface List<T> {
/**
 * adds a given element at end of list
 * @param element
 */
	void add(T element);
	
	/**
	 * adds a given element at a given index
	 * @param index
	 * @param element
	 * @return true if the index is correct, false - incorrect (index < 0 || index > size)
	 */
	boolean add(int index, T element);
	
	/**
	 * 
	 * @return amount of all elements in a list
	 */
	int size();
	
	/**
	 * 
	 * @param index
	 * @return reference to an element at a given index or null if the index is incorrect (index < 0 || index >= size)
	 */
	T get(int index);
	
	/**
	 * removes element from a list at a given index
	 * @param index
	 * @return reference to being removed element or null if the index is incorrect (index < 0 || index >= size)
	 */
	T remove(int index);
	
	/**
	 * check existence of an object
	 * @param pattern
	 * @return true if there is at least one object equaled to a given pattern, otherwise - false
	 */
	default boolean contains(T pattern) {
		
		return indexOf(new isEqualsPredicate<T>(pattern)) != -1;
	}
	
	/**
	 * 
	 * @param pattern
	 * @return index of the first occurrence of an object equaled to a given pattern, or -1 if no such object
	 */
	default int indexOf(T pattern) {
		
		return indexOf(new isEqualsPredicate<T>(pattern));
	}
	
	/**
	 * 
	 * @param pattern
	 * @return index of the last occurrence of an object equaled to a given pattern, or -1 if no such object
	 */
	default int lastIndexOf(T pattern) {
	
		return lastIndexOf(new isEqualsPredicate<T>(pattern));
	}
	
	/**
	 * 
	 * @param predicate
	 * @return true in the case the list contains at least one object matching a condition of a given predicate, otherwise - false
	 */
	default boolean contains(Predicate<T> predicate) {
		
		return indexOf(predicate) >= 0;
	}
	/**
	 * 
	 * @param pattern
	 * @return index of the first occurrence of an object matching  a given predicate, or -1 if no such object
	 */
	int indexOf(Predicate<T> predicate);
	
	/**
	 * 
	 * @param pattern
	 * @return index of the last occurrence of an object matching  a given predicate, or -1 if no such object
	 */
	int lastIndexOf(Predicate<T> predicate);
	
	/**
	 * removes all objects matching a given predicate
	 * @param predicate
	 * @return true if at least one object has been removed otherwise - false
	 */
	boolean removeIf(Predicate<T> predicate);
	
	/**
	 * sorts array of T objects in accordance with the natural order
	 */
	default void sort() {
		//TODO write default implementation based on the method sort with comparator
	}
	
	/**
	 * sorts array of T objects in accordance with  a given comparator object (comp)
	 * @param comp
	 */
	void sort(Comparator<T> comp);
	
	/**
	 * removes first occurrence in the list that is equaled to a given pattern
	 * @param pattern
	 * @return reference to being removed object or null if no such object
	 */
	default T remove (T pattern) {
		
		return removeIf(new isEqualsPredicate<T>(pattern)) == true ? pattern : null;
	}
	
	/**
	 * removes all objects from "this" list that exist in a given list
	 * @param list
	 * @return true if at least one object has been removed
	 */
	default boolean removeAll(List<T> list) {
		
		return removeIf(new RemoveAllPredicate<>(list));
	}
	
	/**
	 * removes all objects from "this" list that don't exist in a given list
	 * @param list
	 * @return true if at least one object has been removed
	 */
	default boolean retainAll(List<T> list) {
		
		return removeIf(new RetainAllPredicate<>(list));
	}

}
