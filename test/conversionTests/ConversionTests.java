package conversionTests;


import conversion.Conversion;
import org.junit.Test;
import static org.junit.Assert.*;
import conversion.CurrencyHelper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rachelmills
 */
public class ConversionTests {
    
    @Test
    public void testSimpleCalculation() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("glob");
        line.add("glob");
        line.add("glob");
        int expectedResult = 3;
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        List<String>symbols = ch.extractSymbols(line, "");
        assertTrue(con.calculateValue("", symbols) == expectedResult);
    }
    
    @Test
    public void testLargerNumbers() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("prok");
        line.add("glob");
        int expectedResult = 6;
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        String metal = ch.getMetal(line);
        List<String>symbols = ch.extractSymbols(line, metal);
        assertTrue(con.calculateValue(metal, symbols) == expectedResult);
    }
    
    @Test
    public void testAllDenominations() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("tegj");
        line.add("pish");
        line.add("tegj");
        line.add("glob");
        line.add("prok");
        int expectedResult = 94;
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        List<String>symbols = ch.extractSymbols(line, "");
        assertTrue(con.calculateValue("", symbols) == expectedResult);
    }
    
    @Test
    public void testGoldMultiplier() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("pish");
        line.add("pish");
        line.add("pish");
        line.add("Gold");
        int expectedResult = 433500;
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        List<String>symbols = ch.extractSymbols(line, "Gold");
        assertTrue(con.calculateValue("Gold", symbols) == expectedResult);
    }
    
    @Test
    public void testSilverMultiplier() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("prok");
        line.add("glob");
        line.add("glob");
        line.add("Silver");
        int expectedResult = 119;
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        List<String>symbols = ch.extractSymbols(line, "Silver");
        assertTrue(con.calculateValue("Silver", symbols) == expectedResult);
    }
    
    @Test
    public void testIronMultiplier() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("tegj");
        line.add("pish");
        line.add("prok");
        line.add("Iron");
        double expectedResult = 12707.50;
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        List<String>symbols = ch.extractSymbols(line, "Iron");
        assertTrue(con.calculateValue("Iron", symbols) == expectedResult);
    }
    
    @Test (expected = Exception.class)
    public void testFailureWithNoDirtSymbols() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("Silver");
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        String metal = ch.getMetal(line);
        List<String>symbols = ch.extractSymbols(line, metal);
        con.calculateValue(metal, symbols);
    }
    
    @Test
    public void testTegjValue() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("tegj");
        double expectedResult = 50;
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        String metal = ch.getMetal(line);
        List<String>symbols = ch.extractSymbols(line, metal);
        assertTrue(con.calculateValue(metal, symbols) == expectedResult);
    }
    
    @Test
    public void testPishBeforeTegjValue() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("pish");
        line.add("tegj");
        double expectedResult = 40;
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        String metal = ch.getMetal(line);
        List<String>symbols = ch.extractSymbols(line, metal);
        assertTrue(con.calculateValue(metal, symbols) == expectedResult);
    }
    
    @Test
    public void testGlobBeforePishValue() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("tegj");
        line.add("glob");
        line.add("pish");
        double expectedResult = 59;
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        String metal = ch.getMetal(line);
        List<String>symbols = ch.extractSymbols(line, metal);
        assertTrue(con.calculateValue(metal, symbols) == expectedResult);
    }
    
    @Test(expected = Exception.class)
    public void testMoreThanThreeGlobInARowThrowsException() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("tegj");
        line.add("glob");
        line.add("glob");
        line.add("glob");
        line.add("glob");
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        String metal = ch.getMetal(line);
        List<String>symbols = ch.extractSymbols(line, metal);
        con.calculateValue(metal, symbols);
    }
    
    @Test(expected = Exception.class)
    public void testMoreThanThreePishInARowThrowsException() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("pish");
        line.add("pish");
        line.add("pish");
        line.add("pish");
        line.add("pish");
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        List<String>symbols = ch.extractSymbols(line, "");
        con.calculateValue("", symbols);
    }
    
    @Test(expected = Exception.class)
    public void testMoreThanOneProkInARowThrowsException() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("pish");
        line.add("prok");
        line.add("prok");
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        List<String>symbols = ch.extractSymbols(line, "");
        con.calculateValue("", symbols);
    }
    
    @Test(expected = Exception.class)
    public void testMoreThanOneTegjInARowThrowsException() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("tegj");
        line.add("tegj");
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        List<String>symbols = ch.extractSymbols(line, "");
        con.calculateValue("", symbols);
    }
    
    @Test(expected = Exception.class)
    public void testInvalidOrderProkBeforeTegj() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("prok");
        line.add("tegj");
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        List<String>symbols = ch.extractSymbols(line, "");
        con.calculateValue("", symbols);
    }
    
    @Test(expected = Exception.class)
    public void testInvalidFormatProkGlobProk() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("prok");
        line.add("glob");
        line.add("prok");
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        List<String>symbols = ch.extractSymbols(line, "");
        con.calculateValue("", symbols);
    }
    
    @Test
    public void testPishGlobPishValid() throws Exception {
        List<String> line = new ArrayList<>();
        line.add("how");
        line.add("many");
        line.add("credits");
        line.add("is");
        line.add("pish");
        line.add("glob");
        line.add("pish");
        double expectedResult = 19;
        Conversion con = new Conversion();
        CurrencyHelper ch = new CurrencyHelper();
        List<String>symbols = ch.extractSymbols(line, "");
        assertTrue(con.calculateValue("", symbols) == expectedResult);
    }
}
