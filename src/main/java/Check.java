import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Check {

    private String fileName;
    Menu menu = new Menu();

    public void checkSeconds(int seconds) {
        if (seconds % 2 == 0) {
            setFileName("example1.txt");
        } else {
            setFileName("example2.txt");
        }
    }

    public void checkingTheQuantity(String nameFile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(nameFile), Charset.defaultCharset());
        int numberOfLines = lines.size();
        if (numberOfLines == 10) {
            try {
                PrintWriter pw = new PrintWriter(nameFile);
                pw.close();
                menu.mainMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
