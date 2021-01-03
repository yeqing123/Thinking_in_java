package io;
import java.util.*;
import nu.xom.*;
import java.io.*;

class WordsInfo extends HashMap<String, Integer> {
	private Element record; 
	public WordsInfo(String fileName) throws Exception {
		Document doc = new Builder().build(new File(fileName));
		Elements elements = doc.getRootElement().getChildElements();
		for(int i = 0; i < elements.size(); i++) {
		    record = elements.get(i);
	    	put(record.getFirstChildElement("word").getValue(),
					Integer.valueOf(record.getFirstChildElement("freq").getValue()));
		}
	}
}


public class Ex32_WordsInfoXML {
	// Produce an XML Element from this Map.Entry object:
    public static Element getXML(Map.Entry<String, Integer> info) {
    	Element record = new Element("record");
    	Element word = new Element("word");
    	Element freq = new Element("freq");
    	word.appendChild(info.getKey());
    	freq.appendChild(info.getValue().toString());
    	record.appendChild(word);
    	record.appendChild(freq);
    	return record;
    }
    public static void format(OutputStream out, Document doc) throws IOException {
		Serializer serializer = new Serializer(out, "ISO-8859-1");
		serializer.setIndent(4);
		serializer.setMaxLength(60);
		serializer.write(doc);
		serializer.flush();
	}
	public static void main(String[] args) throws Exception {
        TextFile tf = new TextFile("./src/io/Ex32_WordsInfoXML.java", "\\W+");
        Map<String, Integer> wordsStat = new HashMap<String, Integer>();
        for(String word : tf) {
            Integer freq = wordsStat.get(word);
            wordsStat.put(word, freq == null ? 1 : freq + 1);
        }
        System.out.println("Before writing:\n" + wordsStat);
        Element root = new Element("wordsInfo");
        Set<Map.Entry<String, Integer>> entrys = wordsStat.entrySet();
        for(Iterator<Map.Entry<String, Integer>> it = entrys.iterator(); it.hasNext();) {
        	root.appendChild(getXML(it.next()));
        }
        Document doc = new Document(root);
        format(System.out, doc);
        BufferedOutputStream out = new BufferedOutputStream(
        		new FileOutputStream("./src/io/WordsInfo.xml"));
        format(out, doc);
        // Now read the file produce a WordsInfo object:
        WordsInfo wordsInfo = new WordsInfo("./src/io/WordsInfo.xml");
        System.out.println("After writing:\n" + wordsInfo);
        // Determine whether the read data is correct :
        Set<Map.Entry<String, Integer>> entrys2 = wordsInfo.entrySet();
        if(entrys.size() == entrys2.size() && entrys.containsAll(entrys2)) {
        	System.out.println("XML file's content correct！");
        }else {
        	System.err.println("XML file's content error！");
    		System.exit(1);
        }
	}

}
