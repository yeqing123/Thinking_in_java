package enumerated;
import java.util.EnumMap;

interface Command2 { boolean handle(Mail m); }

public class Ex09_PostOffice {
	static EnumMap<MailHandler, Command2> em = 
			new EnumMap<MailHandler, Command2>(MailHandler.class);
	static {
        em.put(MailHandler.GENERAL_DELIVERY, new Command2() {
        	public boolean handle(Mail m) {
    			switch(m.generalDelivery) {
    			    case YES:
    			    	System.out.println("Using general delivery for " + m);
    			    	return true;
    			    default: return false;
    			}
    		}
        });
        em.put(MailHandler.MACHING_SCAN, new Command2() {
            public boolean handle(Mail m) {
    			switch(m.scannability) {
    			    case UNSCANNABLE: return false;
    			    default:
    			    	System.out.println("Delivering " + m + " automatically");
    			    	return true;
    			}
    		}
        });
        em.put(MailHandler.VISUAL_INSPECTION, new Command2() {
        	public boolean handle(Mail m) {
    			switch(m.readability) {
    			    case ILLEGIBLE: return false;
    			    default:
    			    	System.out.println("Delivering " + m + " normally");
    			    	return true;
    			}
    		}
        });
        em.put(MailHandler.RETURN_TO_SENDER, new Command2() {
        	public boolean handle(Mail m) {
    			switch(m.returnAddress) {
    			    case MISSING: return false;
    			    default:
    			    	System.out.println("Returning " + m + " to sender");
    			    	return true;
    			}
    		}
        });
    }
    static void handle(Mail m) {
    	for(Command2 cmd : em.values())
    		if(cmd.handle(m))
    			return ;
    	System.out.println(m + " is a dead letter");
    }
    enum MailHandler {
    	GENERAL_DELIVERY, MACHING_SCAN,
        VISUAL_INSPECTION, RETURN_TO_SENDER;
    }
	public static void main(String[] args) {
        for(Mail mail : Mail.generator(10)) {
        	System.out.println(mail.details());
        	handle(mail);
            System.out.println("**********");
        }
	}
}
