package conversion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author rachelmills
 */
public class CurrencyHelper {

    public boolean isQuestion(String line) {
        boolean question = false;
        if ((line.length() > 0) && line.charAt(line.length() - 1) == '?') {
            question = true;
        }
        return question;
    }

    public String getMetal(List<String> line) {
        HashSet<String> metals = new HashSet<>();
        metals.add("Silver");
        metals.add("Gold");
        metals.add("Iron");
        String metal = "";
      
        for (int i = line.size() - 1; i >= 0; i--) {
            if (metals.contains(line.get(i))) {
                metal = line.get(i);
            } 
        }
        return metal;
    }

    public List extractSymbols(List<String> line2, String metal) {
        
        if (!metal.isEmpty()) {
            line2.remove(metal);
        }
        
        HashSet<String> symbols = new HashSet<>();
        symbols.add("glob");
        symbols.add("prok");
        symbols.add("pish");
        symbols.add("tegj");
        List<String> myWords = new ArrayList<>();
        
        for (int i = line2.size() - 1; i >= 0; i--) {
            if (symbols.contains(line2.get(i))) {
                myWords.add(0, line2.get(i));
            } else {
                return myWords;
            }
        }
        return myWords;
    }

    public String cleanLine(String line) {
        // Remove question mark from end of line of text
        return line.substring(0, line.length()-1);
    }

    public List<String> createListFromLine(String line) {
        // Split into individual words
        String[] splitLine = line.split(" ");
               
        // Create new array list and add all words to it
        List<String> textList = new ArrayList<>();
        textList.addAll(Arrays.asList(splitLine));
        return textList;
    }

    public String formatOutputLine(List<String> extractedSymbols, String metal, double conversionValue) {
        
        String symbols = "";
        String outputLine;
        
        for (int i = 0; i < extractedSymbols.size(); i++) {
            if (i == extractedSymbols.size()-1) {
                symbols += extractedSymbols.get(i);
            } else {
                symbols += extractedSymbols.get(i) + ", ";
            }
        }
        
        if (metal.isEmpty()) {
            outputLine = symbols + " is " + conversionValue;
        } else {
            outputLine = symbols + " " + metal + " is " + conversionValue + " Credits";
        }
        
        return outputLine;
    }
}


    
