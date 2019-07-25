package automacao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author usertqi
 * @version $Revision: $<br/>
 * $Id: $
 * @since 25/07/19 17:01
 */
public final class JsonFileReader {

    private JsonFileReader(){
        super();
    }
    
    public static String getFileAsString(String fileName){
        ClassLoader classLoader = new JsonFileReader().getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        //File is found
        System.out.println("File Found : " + file.exists());

        //Read File Content
        String content = null;
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;

    }
}
