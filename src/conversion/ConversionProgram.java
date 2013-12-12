package conversion;

import java.util.List;

import exceptions.InvalidFormatException;

/**
 *
 * @author rachelmills
 */
public class ConversionProgram {
    
    public static void main(String[] args) {
        
        ConversionProgram conv = new ConversionProgram();
        
        List<String> lines = conv.ReadFile(args);
        conv.ConvertCurrency(lines);
    }

    private List<String> ReadFile(String[] args) {
        
        ImportText text = new ImportText();
        
        String fileName;
       
        if (args.length > 0) {
            fileName = args[0];
        } else {
            fileName = "TextToConvert.txt";
        }

        List<String> lines = text.readFile(fileName);
        
        return lines;     
    }

    private void ConvertCurrency(List<String> lines) {
        
        CurrencyHelper currencyHelper = new CurrencyHelper();
        Conversion conversion = new Conversion();
        
        double conversionValue;
        String outputLine = "";
        
        if (null != lines) {
            
            if (lines.isEmpty()) {
                
                System.out.println("There is nothing to read in the file");
            
            } else {
                
                for (String line : lines) {
                    
                    if (line.length() == 0) {
                        
                        outputLine = "";
                    
                    } else if (currencyHelper.isQuestion(line)) {
                    
                        line = currencyHelper.cleanLine(line);

                        List<String> textList = currencyHelper.createListFromLine(line);

                        String metal = currencyHelper.getMetal(textList);

                        List<String> extractedSymbols = currencyHelper.extractSymbols(textList, metal);

                        try {
                            conversionValue = conversion.calculateValue(metal, extractedSymbols);
                        
                            outputLine = currencyHelper.formatOutputLine(extractedSymbols, metal, conversionValue);
                            
                        } catch (InvalidFormatException ex) {
                            
                            outputLine = ex.getMessage();
                            
                        }
                    } 
                    if (!outputLine.isEmpty()) {
                        System.out.println(outputLine);
                        // reset outputLine
                        outputLine = "";
                    }
                }
            }
        }
    }
}