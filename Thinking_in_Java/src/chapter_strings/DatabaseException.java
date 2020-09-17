package chapter_strings;

public class DatabaseException extends Exception {
    public DatabaseException(int transactionID, int queryID, String massage) {
    	super(String.format("(t%d, q%d) %s", transactionID, queryID, massage));
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {
        	throw new DatabaseException(3, 7, "Write failed");
        } catch(Exception e) {
        	System.out.println(e);
        }
	}

}
