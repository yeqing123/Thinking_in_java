package generics;

import java.math.*;
import java.util.concurrent.atomic.*;
import java.util.*;
import static net.mindview.util.Print.*;

// Different types of function objects: 
interface Combiner<T> {
	T combine(T x, T y);
}

interface UnaryFunction<R, T> {
	R function(T x);
}

interface Collector<T> extends UnaryFunction<T, T> {
	T result(); // Extract result of collecting parameter
}

interface UnaryPredicate<T> {
	boolean test(T x);
}

public class Functional {
	// 调用每个元素上的combiner对象将其与运行结果组合，最后返回：
	public static <T> T reduce(Iterable<T> seq, Combiner<T> combiner) {
		Iterator<T> it = seq.iterator();
		if (it.hasNext()) {
			T result = it.next();
			while (it.hasNext())
				result = combiner.combine(result, it.next());
			return result;
		}
		// 如果seq是空列表：
		return null; // Or throw exception
	}

	// 取一个函数对象并在列表中的每个对象上调用它，忽略返回值。 函数对象可以充当集合参数，因此在末尾返回。
	public static <T> Collector<T> forEach(Iterable<T> seq, Collector<T> func) {
		for (T t : seq)
			func.function(t);
		return func;
	}

	// 通过为列表中的每个对象调用函数对象来创建结果列表：
	public static <R, T> List<R> transform(Iterable<T> seq, UnaryFunction<R, T> func) {
		List<R> result = new ArrayList<R>();
		for (T t : seq)
			result.add(func.function(t));
		return result;
	}

	// 对序列中的每个项目应用一元谓词，并返回产生“true”的项目列表：
	public static <T> List<T> filter(Iterable<T> seq, UnaryPredicate<T> pred) {
		List<T> result = new ArrayList<T>();
		for (T t : seq)
			if (pred.test(t))
				result.add(t);
		return result;
	}

	// 为了使用上述通用方法，我们需要创建函数对象以适应我们的特殊需求：
	static class IntegerAdder implements Combiner<Integer> {
		public Integer combine(Integer x, Integer y) {
			return x + y;
		}
	}

	static class IntegerSubtracter implements Combiner<Integer> {
		public Integer combine(Integer x, Integer y) {
			return x - y;
		}
	}

	static class BigDecimalAdder implements Combiner<BigDecimal> {
		public BigDecimal combine(BigDecimal x, BigDecimal y) {
			return x.add(y);
		}
	}

	static class BigIntegerAdder implements Combiner<BigInteger> {
		public BigInteger combine(BigInteger x, BigInteger y) {
			return x.add(y);
		}
	}

	static class AtomicLongAdder implements Combiner<AtomicLong> {
		public AtomicLong combine(AtomicLong x, AtomicLong y) {
			// 不清楚这是否有意义：
			return new AtomicLong(x.addAndGet(y.get()));
		}
	}

	// 我们甚至可以用“ULP”制作一个一元函数
	// （单位排在最后）：
	static class BigDecimalUlp implements UnaryFunction<BigDecimal, BigDecimal> {
		public BigDecimal function(BigDecimal x) {
			return x.ulp();
		}
	}

	static class GreaterThan<T extends Comparable<T>> implements UnaryPredicate<T> {
		private T bound;

		public GreaterThan(T bound) {
			this.bound = bound;
		}

		public boolean test(T x) {
			return x.compareTo(bound) > 0;
		}
	}

	static class MultiplyingIntegerCollector implements Collector<Integer> {
		private Integer val = 1;

		public Integer function(Integer x) {
			val *= x;
			return val;
		}

		public Integer result() {
			return val;
		}
	}

	public static void main(String[] args) {
		// Generics, varargs & boxing working together:
		List<Integer> li = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		Integer result = reduce(li, new IntegerAdder());
		print(result);
		result = reduce(li, new IntegerSubtracter());
		print(result);
		print(filter(li, new GreaterThan<Integer>(4)));
		print(forEach(li, new MultiplyingIntegerCollector()).result());
		print(forEach(filter(li, new GreaterThan<Integer>(4)), new MultiplyingIntegerCollector()).result());
		MathContext mc = new MathContext(7);
		List<BigDecimal> lbd = Arrays.asList(new BigDecimal(1.1, mc), new BigDecimal(2.2, mc), new BigDecimal(3.3, mc),
				new BigDecimal(4.4, mc));
		BigDecimal rbd = reduce(lbd, new BigDecimalAdder());
		print(rbd);
		print(filter(lbd, new GreaterThan<BigDecimal>(new BigDecimal(3))));
		// Use the prime-generation facility of BigInteger:
		List<BigInteger> lbi = new ArrayList<BigInteger>();
		BigInteger bi = BigInteger.valueOf(11);
		for (int i = 0; i < 11; i++) {
			lbi.add(bi);
			bi = bi.nextProbablePrime();
		}
		print(lbi);
		BigInteger rbi = reduce(lbi, new BigIntegerAdder());
		print(rbi);
		// The sum of this list of primes is also prime:
		print(rbi.isProbablePrime(5));
		List<AtomicLong> lal = Arrays.asList(new AtomicLong(11), new AtomicLong(47), new AtomicLong(74),
				new AtomicLong(133));
		AtomicLong ral = reduce(lal, new AtomicLongAdder());
		print(ral);
		print(transform(lbd, new BigDecimalUlp()));
	}
} /*
	 * Output: 28 -26 [5, 6, 7] 5040 210 11.000000 [3.300000, 4.400000] [11, 13, 17,
	 * 19, 23, 29, 31, 37, 41, 43, 47] 311 true 265 [0.000001, 0.000001, 0.000001,
	 * 0.000001]
	 */// :~