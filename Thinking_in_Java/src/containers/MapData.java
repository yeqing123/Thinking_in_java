package containers;

import java.util.LinkedHashMap;

import net.mindview.util.Generator;

public class MapData<K, V> extends LinkedHashMap<K, V> {
    // A single Pair Generator
	public MapData(Generator<Pair<K, V>> gen, int quantity) {
		for(int i = 0; i < quantity; i++) {
			Pair<K, V> p = gen.next();
			this.put(p.key, p.value);
		}
	}
    // Two separate Generators
	public MapData(Generator<K> Kgen, Generator<V> Vgen, int quantity) {
		for(int i = 0; i < quantity; i++)
			this.put(Kgen.next(), Vgen.next());
	}
	// A key Generator and a single value
	public MapData(Generator<K> Kgen, V value, int quantity) {
		for(int i = 0; i < quantity; i++)
			this.put(Kgen.next(), value);
	}
	// An Iterable and a value Generator
	public MapData(Iterable<K> it, Generator<V> Vgen) {
		for(K key : it)
			this.put(key, Vgen.next());
	}
	// An Iterable and a single value
	public MapData(Iterable<K> it, V value) {
		for(K key : it)
			this.put(key, value);
	}
	// Generic convenience methods:
	public static <K, V> MapData<K, V> map(Generator<Pair<K, V>> gen, int quantity) {
	    return new MapData<K, V>(gen, quantity);
	}
	public static <K, V> MapData<K, V> map(Generator<K> Kgen, Generator<V> Vgen, int quantity) {
		return new MapData<K, V>(Kgen, Vgen, quantity);
	}
	public static <K, V> MapData<K, V> map(Generator<K> Kgen, V	value, int quantity) {
		return new MapData<K, V>(Kgen, value, quantity);
	}
	public static <K, V> MapData<K, V> map(Iterable<K> it, Generator<V> Vgen) {
		return new MapData<K, V>(it, Vgen);
	}
	public static <K, V> MapData<K, V> map(Iterable<K> it, V value) {
		return new MapData<K, V>(it, value);
	}
}
