package conversion;

import exceptions.InvalidFormatException;
import java.util.List;

/**
 *
 * @author rachelmills
 */
public class Conversion {

    private final static int GOLD = 14450;
    private final static int SILVER = 17;
    private final static double IRON = 195.50;
    
    private final static int GLOB = 1;
    private final static int PROK = 5;
    private final static int PISH = 10;
    private final static int TEGJ = 50;
    private final static int PISH_TEGJ = 30;
    private final static int GLOB_PROK = 3;
    private final static int GLOB_PISH = 8;
    
    public double calculateValue(String metal, List<String> symbols) throws Exception {
        
        double runningTotal = 0;
        int countGlob = 0;
        int countProk = 0;
        int countPish = 0;
        
        int i = 0;
        
        if (symbols.isEmpty()) {
            throw new InvalidFormatException("There are no dirt symbols in this currency");
        }
        
        do {
            if (symbols.get(i).equalsIgnoreCase("tegj")) {
                // tegj can only be in first 3 places
                if (i == 0) {
                    runningTotal += TEGJ;
                } else if ((i == 1 || i == 2) && symbols.get(i - 1).equalsIgnoreCase("pish")) {
                    runningTotal += PISH_TEGJ;
                } else throw new InvalidFormatException("The symbol tegj cannot be used in this position");
                
            } else if (symbols.get(i).equalsIgnoreCase("pish")) {
                countPish++;
                
                if (i > 0 && symbols.get(i-1).equalsIgnoreCase("glob")) {
                    runningTotal += GLOB_PISH;
                    break;
                // throws exception if more than 3 pish in a row
                } else if (countPish > 3) {
                    throw new InvalidFormatException("There cannot be more than 3 pish in a row");
                } else {
                    runningTotal += PISH;
                }
                
            } else if (symbols.get(i).equalsIgnoreCase("prok")) {
                countProk++;
                if (countProk > 1) {
                    throw new InvalidFormatException("There cannot be more than two proks");
                }
                if (i > 0 && symbols.get(i-1).equalsIgnoreCase("glob")) {
                    runningTotal += GLOB_PROK;
                    break;
                } else {
                    runningTotal += PROK;
                }
            } else if (symbols.get(i).equalsIgnoreCase("glob")) {
                countGlob++;
                if (countGlob > 3) {
                    throw new InvalidFormatException("There cannot be more than 3 glob in a row");
                }
                runningTotal += GLOB;
                
            }
            i++;
        } while (i < symbols.size());
        
        if (!metal.isEmpty()) {
            if (metal.equalsIgnoreCase("gold")) {
                runningTotal *= GOLD;
             } else if (metal.equalsIgnoreCase("silver")) {
                 runningTotal *= SILVER;
             } else if (metal.equalsIgnoreCase("iron")) {
                 runningTotal *= IRON;
             }
        }

        return runningTotal;        
    }
}
