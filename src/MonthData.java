public class MonthData {
    public String itemName;
    public Boolean isExpense;
    public int quantity;
    public int sumOfOne;
    public int month;

    public MonthData(String itemName, Boolean isExpense, int quantity, int sumOfOne, int month) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.month = month;
    }
}
