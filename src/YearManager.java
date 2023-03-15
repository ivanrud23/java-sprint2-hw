import java.util.ArrayList;
import java.util.List;

public class YearManager {
    public ArrayList<YearData> allYearData = new ArrayList<>();
    public FileReader fileReader = new FileReader();


    public void loadFile(Integer year, String path) {
        List<String> lines = fileReader.readFileContents(path);
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            YearData yearData = new YearData(month, amount, isExpense, year);
            allYearData.add(yearData);
        }
    }


    void yearInform() {
        int profitByMonth;
        int totalIncome = 0;
        int totalExpense = 0;
        System.out.println("Данные за " + allYearData.get(0).year + " год");
        System.out.println();

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


}
