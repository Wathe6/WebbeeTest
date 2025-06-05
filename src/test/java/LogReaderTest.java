import io.envoi.io.LogReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class LogReaderTest {
    @Test
    public void testInput() {
        LogReader reader = new LogReader("input");

        List<File> logFiles = reader.getLogFiles();
        for (File logFile : logFiles) {
            System.out.println("Найден лог-файл: " + logFile.getName());
        }
    }

}
