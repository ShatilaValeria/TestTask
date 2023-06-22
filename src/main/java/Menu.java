import java.util.Scanner;

public class Menu {

    CurrentTime currentTime = new CurrentTime();

    public void mainMenu() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("Было записано и удалено 10 значений. Желаете прододжить запись?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
        selection = input.nextInt();

        switch (selection) {
            case 1 -> currentTime.timeInSeconds();
            case 2 -> System.exit(0);
            default -> {
                System.out.println("Некорректный ввод данных. Повторите ввод");
                mainMenu();
            }
        }
    }
}
