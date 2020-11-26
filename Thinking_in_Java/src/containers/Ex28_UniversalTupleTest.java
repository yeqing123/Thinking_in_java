package containers;
import java.util.Set;
import java.util.TreeSet;
import containers.Tuple.*;

class Tuple {
	public static class T2<A, B> implements Comparable<T2<A, B>> {
		private final A first;
		private final B second;
		public T2(A a, B b) {
			first = a;
			second = b;
		}
		public String toString() {
			return "(" + first + ", " + second + ")";
		}
		public A getFirst() { return first; }
        public B getSecond() { return second; }
		public int hashCode() {
			int result = 17;
			result = result * 37 + first.hashCode();
			result = result * 37 + second.hashCode();
			return result;
		}
		@SuppressWarnings("unchecked")
		public boolean equals(Object o) {
			if(!(o instanceof T2))
				return false;
			T2<A, B> t = (T2<A, B>)o;
			return first == null ? t.getFirst() == null : t.getFirst().equals(first) &&
			       second == null ? t.getSecond() == null : t.getSecond().equals(second);		
		}
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(T2<A, B> arg) {
			int firstCompare = ((Comparable<A>)first).compareTo(arg.getFirst());
			if(firstCompare != 0)
				return firstCompare;
			return ((Comparable<B>)second).compareTo(arg.getSecond());
		}
	}
	public static class T3<A, B, C> implements Comparable<T3<A, B, C>> {
        private final T2<A, B> t2;
        private final C third;
        public T3(A a, B b, C c) {
        	t2 = new T2<A, B>(a, b);
        	third = c;
        }
        public A getFirst() { return t2.getFirst(); }
        public B getSecond() { return t2.getSecond(); }
        public C getThird() { return third; }
        public String toString() {
        	return "(" + t2.getFirst() + ", " + t2.getSecond() + ", " + third + ")";
        }
        public int hashCode() {
        	int result = t2.hashCode();
        	if(third != null)
        		result = result * 37 + third.hashCode();
        	return result;
        }
        @SuppressWarnings("unchecked")
		public boolean equals(Object o) {
        	if(!(o instanceof T3))
        		return false;
        	T3<A, B, C> tuple = (T3<A, B, C>)o;
        	return t2.equals(tuple.t2) &&
        			third == null ? tuple.getThird() == null : third.equals(tuple.getThird());
        }
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(T3<A, B, C> arg) {
			int res = t2.compareTo(arg.t2);
			if(res != 0)
				return res;
			return ((Comparable<C>)third).compareTo(arg.getThird());
		}
	}
	public static class T4<A, B, C, D> implements Comparable<T4<A, B, C, D>> {
        private final T3<A, B, C> t3;
        private final D fourth;
        public T4(A a, B b, C c, D d) {
        	t3 = new T3<A, B, C>(a, b, c);
        	fourth = d;
        }
        public A getFirst() { return t3.getFirst(); }
        public B getSecond() { return t3.getSecond(); }
        public C getThird() { return t3.getThird(); }
        public D getFourth() { return fourth; }
        public String toString() {
        	return "(" + t3.getFirst() + ", " + t3.getSecond() + 
        			", " + t3.getThird() + ", " + fourth + ")";
        }
        public int hashCode() {
        	int result = t3.hashCode();
        	if(fourth != null)
        		result = result * 37 + fourth.hashCode();
        	return result;
        }
        @SuppressWarnings("unchecked")
		public boolean equals(Object o) {
        	if(!(o instanceof T4))
        		return false;
        	T4<A, B, C, D> tuple = (T4<A, B, C, D>)o;
        	return t3.equals(tuple.t3) && 
        			fourth == null ? tuple.getFourth() == null : fourth.equals(tuple.getFourth());
        }
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(T4<A, B, C, D> arg) {
            int res = t3.compareTo(arg.t3);
            if(res != 0)
            	return res;
			return ((Comparable<D>)fourth).compareTo(arg.getFourth());
		}
	}
	public static class T5<A, B, C, D, E> implements Comparable<T5<A, B, C, D, E>> {
        private final T4<A, B, C, D> t4;
        private final E fifth;
        public T5(A a, B b, C c, D d, E e) {
        	t4 = new T4<A, B, C, D>(a, b, c, d);
        	fifth = e;
        }
        public A getFirst() { return t4.getFirst(); }
        public B getSecond() { return t4.getSecond(); }
        public C getThird() { return t4.getThird(); }
        public D getFourth() { return t4.getFourth(); }
        public E getFifth() { return fifth; }
        public String toString() {
        	return "(" + t4.getFirst() + ", " + t4.getSecond() + ", " + 
                t4.getThird() + ", " + t4.getFourth() + ", " + fifth + ")";
        }
        public int hashCode() {
        	int result = t4.hashCode();
        	if(fifth != null)
        	    result = result * 37 + fifth.hashCode();
        	return result;
        }
        @SuppressWarnings("unchecked")
		public boolean equals(Object o) {
        	if(!(o instanceof T5))
        		return false;
        	T5<A, B, C, D, E> tuple = (T5<A, B, C, D, E>)o;
        	return t4.equals(tuple.t4) &&
        			fifth == null ? tuple.getFifth() == null : fifth.equals(tuple.getFifth());
        }
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(T5<A, B, C, D, E> arg) {
			int res = t4.compareTo(arg.t4);
			if(res != 0)
				return res;
			return ((Comparable<E>)fifth).compareTo(arg.getFifth());
		}
	}
	public static <A, B> T2<A, B> tuple(A a, B b) {
		return new T2<A, B>(a, b);
	}
	public static <A, B, C> T3<A, B, C> tuple(A a, B b, C c) {
		return new T3<A, B, C>(a, b, c);
	}
	public static <A, B, C, D> T4<A, B, C, D> tuple(A a, B b, C c, D d) {
		return new T4<A, B, C, D>(a, b, c, d);
	}
	public static <A, B, C, D, E> T5<A, B, C, D, E> tuple(A a, B b, C c, D d, E e) {
		return new T5<A, B, C, D, E>(a, b, c, d, e);
	}
}



public class Ex28_UniversalTupleTest {
 
	public static void main(String[] args) {
		Set<T5<?, ?, ?, ?, ?>> t5Set = new TreeSet<T5<?, ?, ?, ?, ?>>();
        T5<String, Float, Integer, Character, Boolean> 
        t5_1 = Tuple.tuple("C", (float)3, 10, 'd', true),
        t5_2 = Tuple.tuple("B", (float)4, 12, 'b', false),
        t5_3 = Tuple.tuple("A", (float)7, 15, 'e', true),
        t5_4 = Tuple.tuple("A", (float)7, 17, 'a', false);
        t5Set.add(t5_1);
        t5Set.add(t5_2);
        t5Set.add(t5_3);
        t5Set.add(t5_4);
        System.out.println("t5_1:" + t5_1);
        System.out.println("t5_2:" + t5_2);
        System.out.println("t5_3:" + t5_3);
        System.out.println("t5_4:" + t5_4);
        System.out.println("t5_1.equals(t5_2):" + t5_1.equals(t5_2));
        System.out.println("t5_1.equals(t5_1):" + t5_1.equals(t5_1));
        System.out.println("t5_1.compareTo(t5_2):" + t5_1.compareTo(t5_2));
        System.out.println("t5_1.compareTo(t5_1):" + t5_1.compareTo(t5_1));
        System.out.println("t5_3.compareTo(t5_4):" + t5_3.compareTo(t5_4));
        // As you can see, TreeSet in the collection elements sorted automatically.
        System.out.println(t5Set);
	}

}
