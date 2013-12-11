package conversionTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import conversion.ImportText;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author rachelmills
 */
public class ImportTextTest {

    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    @Before
    public void setUp() {
        System.setOut((new PrintStream(outContent)));
    }
    
    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    
    @Test
    public void testErrorMessageWhenInvalidFileName() {
        ImportText it = new ImportText();
        String name = "invalidFileName.text";
        it.readFile(name);
        assertEquals("There is no file of this name", outContent.toString().trim());
    }
    
    @Test
    public void testErrorMessageWhenEmptyFilename() {
        ImportText it = new ImportText();
        String name = "";
        it.readFile(name);
        assertEquals("There is a problem reading the file", outContent.toString().trim());
    }
    
    @Test
    public void testReadValidFile() {
        ImportText it = new ImportText();
        String name = "TextToConvert.txt";
        List<String> file = it.readFile(name);
        assertTrue(!file.isEmpty());
    }
}
