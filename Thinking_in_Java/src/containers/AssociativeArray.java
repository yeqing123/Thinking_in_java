package containers;

public class AssociativeArray<K, V> {
    private Object[][] pairs;
    private int index = 0;
    public AssociativeArray(int length) {
    	pairs = new Object[length][2];
    }
    public void put(K key, V value) {
    	if(index < pairs.length)
    		pairs[index++] = new Object[] {key, value};
    	else
    		throw new ArrayIndexOutOfBoundsException();
    }
    @SuppressWarnings("unchecked")
	public V get(K key) {
    	for(int i = 0; i < index; i++)
    		if(key.equals(pairs[i][0]))
    				return (V)pairs[i][1];
    	return null;
    }
    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	for(int i = 0; i < index; i++) {
    		buffer.append(pairs[i][0].toString());
    		buffer.append(" : ");
    		buffer.append(pairs[i][1].toString());
    		if(i < pairs.length - 1)
    			buffer.append("\n");
    	}
    	return buffer.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        AssociativeArray<String, String> map = 
        		new AssociativeArray<String, String>(6);
        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "brown");
        map.put("earth", "tall");
        map.put("sun", "warm");
        try {
        	map.put("extra", "object"); // Past the end
        } catch(ArrayIndexOutOfBoundsException e) {
        	System.out.println("Too many objects!");
        }
        System.out.println(map);
        System.out.println(map.get("ocean"));
	}

}
