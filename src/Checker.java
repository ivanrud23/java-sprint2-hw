import java.util.Collection;
import java.util.HashMap;

public class Checker {
    public YearManager yearManager;
    public MonthManager monthManager;

    public Checker(YearManager yearManager, MonthManager monthManager) {
        this.yearManager = yearManager;
        this.monthManager = monthManager;
    }


    public void check() {
        HashMap<Integer, HashMap<Boolean, Integer>> checkYear = yearMap();
        HashMap<Integer, HashMap<Boolean, Integer>> checkMonth = monthMap();
        boolean flag = true;
        for (Integer month : checkYear.keySet()) {
            for (Boolean isExpense : checkYear.get(month).keySet()) {
                int month1 = checkYear.get(month).get(isExpense);
                int month2 = checkMonth.get(month).get(isExpense);
                if (month1 != month2) {
                    flag = false;
                    System.out.println("В " + month + " месяце обнаруженна ошибка." );
                }
            }

        }
        if (flag) {
            System.out.println("Ошибок не обнаружено" );
        }
    }


    public HashMap<Integer, HashMap<Boolean, Integer>> yearMap() {
        HashMap<Integer, HashMap<Boolean, Integer>> checkYear = new HashMap<>();
        for (YearData month : yearManager.allYearData) {
            if (!checkYear.containsKey(month.month)){
                checkYear.put(month.month, new HashMap<>());
            }
            HashMap<Boolean, Integer> monthData = checkYear.get(month.month);
            monthData.put(month.isExpense, month.amount);
        }
        return checkYear;
    }


    public HashMap<Integer, HashMap<Boolean, Integer>> monthMap() {
        HashMap<Integer, HashMap<Boolean, HashMap<String, Integer>>> allMonthInfo = monthManager.allMonthCollect();

        HashMap<Integer, HashMap<Boolean, Integer>> checkMonth = new HashMap<>();
        for (Integer month : allMonthInfo.keySet()) {
            if (!checkMonth.containsKey(month)) {
                checkMonth.put(month, new HashMap<>());
            }
            HashMap<Boolean, Integer> monthInfo = checkMonth.get(month);
            for (Boolean isExpense : allMonthInfo.get(month).keySet()) {
                monthInfo.put(isExpense, sum(allMonthInfo.get(month).get(isExpense).values()));
            }
        }
        return checkMonth;
    }


    public Integer sum(Collection<Integer> arrayList) {
        int sum = 0;
        for (Integer num : arrayList) {
            sum += num;
        }
        return sum;
    }

}
