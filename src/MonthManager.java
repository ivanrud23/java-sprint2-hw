import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MonthManager {
    public ArrayList<MonthData> allMonthData = new ArrayList<>();

    public void loadFile(Integer month, String path) {
        List<String> lines = readFileContents(path);
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");
            String itemName = parts[0];
            Boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);

            MonthData monthData = new MonthData(itemName, isExpense, quantity, sumOfOne, month);
            allMonthData.add(monthData);
        }
    }


    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }


    public void MonthInform() {
        HashMap<Integer, HashMap<Boolean, HashMap<String, Integer>>> allMonthInfo = new HashMap<>();
        for (MonthData itemData : allMonthData) {

            if (!allMonthInfo.containsKey(itemData.month)){
                allMonthInfo.put(itemData.month, new HashMap<>());
            }
            HashMap<Boolean, HashMap<String, Integer>> monthInfo = allMonthInfo.get(itemData.month);

            if (!monthInfo.containsKey(itemData.isExpense)) {
                monthInfo.put(itemData.isExpense, new HashMap<>());
            }
            HashMap<String, Integer> itemInfo = monthInfo.get(itemData.isExpense);
            itemInfo.put(itemData.itemName, itemData.sumOfOne * itemData.quantity);
        }


        for (Integer month : allMonthInfo.keySet()) {
            System.out.println("В " + month + " месяце:");
            String topItem = "";
            int maxItem = 0;
            String topExpense = "";
            int maxExpense = 0;
            for (Boolean isExpense : allMonthInfo.get(month).keySet()) {
                if (isExpense) {
                    for (String expense : allMonthInfo.get(month).get(true).keySet()) {
                        if (allMonthInfo.get(month).get(true).get(expense) > maxExpense) {
                            maxExpense = allMonthInfo.get(month).get(true).get(expense);
                            topExpense = expense;
                        }
                    }
                }
                if (!isExpense) {
                    for (String item : allMonthInfo.get(month).get(false).keySet()) {
                        if (allMonthInfo.get(month).get(false).get(item) > maxItem) {
                            maxItem = allMonthInfo.get(month).get(false).get(item);
                            topItem = item;
                        }
                    }
                }
            }
            System.out.println("Самый прибыльный товар - " + topItem + " " + maxItem);
            System.out.println("Самая большая трата - " + topExpense + " " + maxExpense);
            System.out.println();
        }
    }
}
