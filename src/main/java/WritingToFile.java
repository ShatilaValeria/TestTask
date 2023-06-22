import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WritingToFile {

    public void firstFile(String fileName) {
        Random random = new Random();
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            int x = random.nextInt();
            System.out.println(x);
            fileWriter.append(String.valueOf(x));
            fileWriter.append('\n');
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
