package telran.util;

import java.util.function.Predicate;

public class isEqualsPredicate<T> implements Predicate<T> {
	T pattern;

	public isEqualsPredicate(T pattern) {
		super();
		this.pattern = pattern;
	}

	@Override
	public boolean test(T t) {
		
		return t.equals(pattern);
	}

}
