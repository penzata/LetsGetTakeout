public class Food implements PricedItem<Integer> {
    private String name;
    private String description;
    private int price;

    Food(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public Integer getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(Integer price) {
    this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s: %s    Cost: %d\n", this.name, this.description, this.price);
    }

    public String getName() {
        return this.name;
    }

}