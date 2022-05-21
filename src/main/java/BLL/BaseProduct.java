package BLL;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BaseProduct extends MenuItem implements Serializable {

    public BaseProduct(String title, float rating, int calories, int proteins, int fats, int sodium, int price) {
        super(title, rating, calories, proteins, fats, sodium, price);
        orderedTimes = 0;
    }

    public BaseProduct() {
        super();
    }

    public ArrayList<BaseProduct> processInputFile(String filePath) {
        int ok=0;
        ArrayList<BaseProduct> products = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<List<String>> items = lines.skip(1).map(line -> Arrays.asList(line.split(","))).toList();
            for(List<String> item : items){
                BaseProduct p = new BaseProduct(item.get(0),Float.parseFloat(item.get(1)),Integer.parseInt(item.get(2)),Integer.parseInt(item.get(3)),Integer.parseInt(item.get(4)),Integer.parseInt(item.get(5)),Integer.parseInt(item.get(6)));
                for(BaseProduct pr :products){
                    if (p.getTitle().equals(pr.getTitle())) {
                        ok = 1;
                        break;
                    }
                }
                if(ok==0)
                    products.add(p);
                else ok=0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public int computePrice() {
        return getPrice();
    }
}
