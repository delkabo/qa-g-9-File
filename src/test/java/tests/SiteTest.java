package tests;

import com.delkabo.config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SiteTest {

    Path props = Paths.get("src/test/resources/auth.properties");

    Path newFilePath = Paths.get("src/test/resources/auth1.properties");

    Path newFilePath3 = Paths.get("src/test/resources/auth2.properties");

    @BeforeEach
    public void clearFile() throws Exception {
        Files.deleteIfExists(props);
    }

    @Test
    public void testRemoteFile1() throws Exception{
        Files.deleteIfExists(newFilePath);
        String content = "test write file 1";
        Files.createFile(newFilePath);
        Files.write(newFilePath, content.getBytes());
    }


    @Test
    public void testRemoteFile3() throws Exception{

        Files.deleteIfExists(newFilePath3);
        File newFile = new File("src/test/resources/auth2.properties");

        assertThat(newFile.getName()).isEqualTo("auth2.properties");
        String content = "test write file 1";
        Files.write(newFilePath3, content.getBytes());

        BufferedReader reader = new BufferedReader(new FileReader(newFile));
        String currentLine =reader.readLine();
        reader.close();

        assertThat(content).isEqualTo(currentLine);
        System.out.println(currentLine);
    }
}
