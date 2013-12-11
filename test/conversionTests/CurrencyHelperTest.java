package conversionTests;

import conversion.CurrencyHelper;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rachelmills
 */
public class CurrencyHelperTest {
    
    @Test
    public void testQuestionFlaggedAsTrue() {
        String line = "test test test ?";
        CurrencyHelper ch = new CurrencyHelper();
        assertTrue(ch.isQuestion(line));
    }
    
    @Test
    public void testNonQuestionFlaggedAsFalse() {
        String line = "test test test";
        CurrencyHelper ch = new CurrencyHelper();
        assertFalse(ch.isQuestion(line));
    }
    
    @Test
    public void testCurrencyWithNoMetalsReturnsNoMetals() {
        List<String> line = new ArrayList<>();
        line.add("test");
        line.add("test");
        line.add("test");
        CurrencyHelper ch = new CurrencyHelper();
        String metal = ch.getMetal(line);
        assertTrue(metal.isEmpty());
    }
    
    @Test
    public void testCurrencyWithOneMetalReturnsOneMetal() {
        List<String> line = new ArrayList<>();
        line.add("test");
        line.add("test");
        line.add("test");
        line.add("Iron");
        String expectedResult = "Iron";
        CurrencyHelper ch = new CurrencyHelper();
        String metal = ch.getMetal(line);
        assertTrue(metal.equals(expectedResult));
    }
    
    @Test
    public void testCurrencyWithDirtSymbolsExtracted() {
        List<String> line = new ArrayList<>();
        line.add("test");
        line.add("glob");
        line.add("prok");
        line.add("pish");
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("glob");
        expectedResult.add("prok");
        expectedResult.add("pish");
        CurrencyHelper ch = new CurrencyHelper();
        List<String> metals = ch.extractSymbols(line, "");
        assertEquals(expectedResult, metals);
    }
    
    @Test
    public void testCurrencyWithDirtSymbolsAndCurrencyExtracted() {
        List<String> line = new ArrayList<>();
        line.add("test");
        line.add("test");
        line.add("glob");
        line.add("prok");
        line.add("Silver");
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("glob");
        expectedResult.add("prok");
        CurrencyHelper ch = new CurrencyHelper();
        List<String> metals = ch.extractSymbols(line, "Silver");
        assertEquals(expectedResult, metals);
    }
    
}
