import java.io.IOException;

public class Controller {
    public void start() {
        Check check = new Check();
        WritingToFile writingToFile = new WritingToFile();
        Timeout time = new Timeout();
        String nameFile;

        while (true) {
            //Получаем секунды от текущего времени
            check.currentTimeInSeconds();
            //Проверяем чётная ли секуда
            check.checkSeconds(check.getSeconds());
            //Получаем имя нужного файла
            nameFile = check.getFileName();
            //Записывем в файл сгенерированное число
            writingToFile.firstFile(nameFile);
            //Проверяем кол-во значений в текущем файле
            try {
                check.checkingTheQuantity(nameFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Перерыв 1 секудна
            time.timeout();
        }
    }
}
