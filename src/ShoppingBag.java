import java.util.HashMap;
import java.util.Map;

public class ShoppingBag <T extends PricedItem<Integer>> {
    private Map<T, Integer> shoppingBag;

    ShoppingBag () {
        shoppingBag = new HashMap<>();
    }

    public void addItem(T item) {
        shoppingBag.put(item, shoppingBag.get(item) == null ? 1 : shoppingBag.get(item) + 1);
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for(Map.Entry<T, Integer> entry : shoppingBag.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        System.out.println("You've got in the bag: ");
        shoppingBag.forEach((k, v) -> System.out.print(v + " -> " + k));
        return "";
    }

}