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
        	return t2.hashCode() * 37 + third.hashCode();
        }
        @SuppressWarnings("unchecked")
		public boolean equals(Object o) {
        	if(!(o instanceof T3))
        		return false;
        	T3<A, B, C> t = (T3<A, B, C>)o;
        	return t2.equals(new T2<A, B>(t.getFirst(), t.getSecond())) &&
        			third == null ? t.getThird() == null : third.equals(t.getThird());
        }
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(T3<A, B, C> arg) {
			int t2Compare = t2.compareTo(new T2<A, B>(arg.getFirst(), arg.getSecond()));
			if(t2Compare != 0)
				return t2Compare;
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
        	return t3.hashCode() *37 + fourth.hashCode();
        }
        @SuppressWarnings("unchecked")
		public boolean equals(Object o) {
        	if(!(o instanceof T4))
        		return false;
        	T4<A, B, C, D> t = (T4<A, B, C, D>)o;
        	return t3.equals(new T3<A, B, C>(t.getFirst(), t.getSecond(), t.getThird())) && 
        			fourth == null ? t.getFourth() == null : fourth.equals(t.getFourth());
        }
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(T4<A, B, C, D> arg) {
            int t3Compare = t3.compareTo(new T3<A, B, C>(arg.getFirst(), arg.getSecond(), arg.getThird()));
            if(t3Compare != 0)
            	return t3Compare;
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
        	return t4.hashCode() * 37 + fifth.hashCode();
        }
        @SuppressWarnings("unchecked")
		public boolean equals(Object o) {
        	if(!(o instanceof T5))
        		return false;
        	T5<A, B, C, D, E> t = (T5<A, B, C, D, E>)o;
        	return t4.equals(new T4<A, B, C, D>(t.getFirst(), t.getSecond(), t.getThird(), t.getFourth())) &&
        			fifth == null ? t.getFifth() == null : fifth.equals(t.getFifth());
        }
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(T5<A, B, C, D, E> arg) {
			int t4Compare = t4.compareTo(
					new T4<A, B, C, D>(arg.getFirst(), arg.getSecond(), arg.getThird(), arg.getFourth()));
			if(t4Compare != 0)
				return t4Compare;
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
		Set<T2<?, ?>> t2Set = new TreeSet<T2<?, ?>>();
		Set<T3<?, ?, ?>> t3Set = new TreeSet<T3<?, ?, ?>>();
        T2<String, Integer> t2_1 =  Tuple.tuple("C", 10);
        T2<String, Integer> t2_2 =  Tuple.tuple("B", 8);
        T2<String, Integer> t2_3 =  Tuple.tuple("A", 14);
        T2<String, Integer> t2_4 =  Tuple.tuple("B", 9);
        T3<String, Integer, Character> t3_1 = Tuple.tuple("D", 17, 'c');
        T3<String, Integer, Character> t3_2 = Tuple.tuple("F", 19, 'b');
        T3<String, Integer, Character> t3_3 = Tuple.tuple("E", 15, 'd');
        T3<String, Integer, Character> t3_4 = Tuple.tuple("F", 19, 'e');
        t2Set.add(t2_1);
        t2Set.add(t2_2);
        t2Set.add(t2_3);
        t2Set.add(t2_4);
        t3Set.add(t3_1);
        t3Set.add(t3_2);
        t3Set.add(t3_3);
        t3Set.add(t3_4);
        System.out.println(t2Set);
        System.out.println(t3Set);
	}

}
