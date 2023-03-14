import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YearManager {
    public ArrayList<YearData> allYearData = new ArrayList<>();

    public YearManager (String path) {
        List<String> lines = readFileContents(path);
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            YearData yearData = new YearData(month, amount, isExpense);
            allYearData.add(yearData);
        }
    }


    void YearInform() {
        int profitByMonth;
        int totalIncome = 0;
        int totalExpense = 0;

        for (int i = 0; i < allYearData.size(); i = i + 2) {
            profitByMonth = Math.abs(allYearData.get(i).amount - allYearData.get(i + 1).amount);
            System.out.println("Прибыль за " + allYearData.get(i).month + " месяц составила " + profitByMonth + " рублей");
            if (!allYearData.get(i).isExpense){
                totalIncome += allYearData.get(i).amount;
                totalExpense += allYearData.get(i + 1).amount;
            } else {
                totalIncome += allYearData.get(i + 1).amount;
                totalExpense += allYearData.get(i).amount;
            }
        }
        int midIncome = totalIncome / (allYearData.size() / 2);
        int midExpense = totalExpense / (allYearData.size() / 2);

        System.out.println("Средний расход за все месяцы в году: " + midExpense);
        System.out.println("Средний доход за все месяцы в году: " + midIncome);
    }


    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
