package BLL;

import java.io.Serializable;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable {
    private List<MenuItem> items;

    public CompositeProduct(String title, float rating, int calories, int proteins, int fats, int sodium, int price) {
        super(title, rating, calories, proteins, fats, sodium, price);
        orderedTimes = 0;
    }

    public CompositeProduct() {
        super();
    }

    @Override
    public int computePrice() {
        int price = 0;
        for (MenuItem i : items) {
            price = price + i.getPrice();
        }
        this.setPrice(price);
        return price;
    }

    public void compute() {
        this.setCalories(0);
        this.setProteins(0);
        this.setFats(0);
        this.setRating(0);
        this.setSodium(0);
        for (MenuItem i : items) {
            this.setCalories(this.getCalories()+i.getCalories());
            this.setProteins(this.getProteins()+i.getProteins());
            this.setFats(this.getFats()+i.getFats());
            this.setRating(this.getRating()+i.getRating());
            this.setSodium(this.getSodium()+i.getSodium());
        }
        this.setRating(this.getRating()/items.size());

    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
}
