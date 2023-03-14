import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearManager yearManager = new YearManager("resources/y.2021.csv");
        MonthManager monthManager = new MonthManager();


        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                monthManager.loadFile(1, "resources/m.202101.csv");
                monthManager.loadFile(2, "resources/m.202102.csv");
                monthManager.loadFile(3, "resources/m.202103.csv");
            } else if (command == 2) {

            } else if (command == 3) {
                Checker checker = new Checker(yearManager, monthManager);
                checker.check();
            } else if (command == 4) {
                monthManager.MonthInform();
            } else if (command == 5) {
                yearManager.YearInform();
            } else if (command == 911) {
                return;
            } else {
                System.out.println("Вы ыыели несуществующую комманду");
            }
        }
    }


    public static void printMenu() {
        System.out.println();
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 — Считать все месячные отчёты");
        System.out.println("2 — Считать годовой отчёт");
        System.out.println("3 — Сверить отчёты");
        System.out.println("4 — Вывести информацию о всех месячных отчётах");
        System.out.println("5 — Вывести информацию о годовом отчёте");
        System.out.println("Для выхода введите — 911");
        System.out.println();
    }


}

