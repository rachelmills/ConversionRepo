package conversion;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 *
 * @author rachelmills
 */
public class ImportText {
    
    final static Charset ENCODING = StandardCharsets.UTF_8;
    
    public List<String> readFile(String fileName)  {
        
        List<String> myFiles = null;
        Path path = Paths.get(fileName);
        
        try {
            myFiles =  Files.readAllLines(path, ENCODING);
        }
        
        catch (NoSuchFileException e) {
            System.out.println("There is no file of this name");
        }
        
        catch (IOException e) {
            System.out.println("There is a problem reading the file");
        }

        return myFiles;      
    }
}
