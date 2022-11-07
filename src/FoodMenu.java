import java.util.ArrayList;
import java.util.List;

public class FoodMenu {
    private List<Food> menu;

    public FoodMenu() {
        menu = new ArrayList<>();
        menu.add(new Food("pizza", "margarita", 8));
        menu.add(new Food("burger", "veggie", 5));
        menu.add(new Food("fries", "curly", 3));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int number = 1;
        for (Food food : menu) {
            sb.append(number++);
            sb.append(". ");
            sb.append(food.toString());
        }
        return sb.toString();
    }

    public Food getFood(int index) {
        if (index < 0 || index > menu.size()) {
            return null;
        } else {
            return menu.get(index - 1);
        }
    }

    public Food getLowestCostFood() {
        List<Food> lowestCostFood = new ArrayList<>();
        int min = menu.get(0).getPrice();
        for (Food food : menu) {
            if (food.getPrice() <= min) {
                min = food.getPrice();
                lowestCostFood.clear();
                lowestCostFood.add(food);
            }
        }
        return lowestCostFood.get(0);
    }

}