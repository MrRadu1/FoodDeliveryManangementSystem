package BLL;


import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    public HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<>();
    public List<Order> orderss = new ArrayList<>();
    public List<MenuItem> products =  new ArrayList<>();
    public ArrayList<MenuItem> items =  new ArrayList<>();
    public List<User> users = new ArrayList<>();
    private final List<MenuItem> items1 = new ArrayList<>();

    public DeliveryService() {
        BaseProduct b = new BaseProduct();
        List<BaseProduct> prod = b.processInputFile("products.csv");
        products.addAll(prod);
    }


    @Override
    public void addProduct(BaseProduct menuItem) {
        assert menuItem != null;
        int size = products.size();
        products.add(menuItem);
        assert size + 1 == products.size();
    }

    @Override
    public boolean deleteProduct(String menuItemName) {
        assert menuItemName != null;
        int size = products.size();
        for (MenuItem i : products) {
            if (menuItemName.equals(i.getTitle())) {
                products.remove(i);
                assert size - 1 == products.size();
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateProduct(String itemName, int price) {
        assert itemName != null;
        int p;

        for (MenuItem i : products) {
            if (i instanceof BaseProduct) {
                if (itemName.equals(i.getTitle())) {
                    i.setPrice(price);
                    p = i.getPrice();
                    assert p == i.getPrice();
                }
            }
        }
    }

    @Override
    public void addBaseToCompositeProduct(String prod1) {
        for (MenuItem i : products) {
            if (i.getTitle().equals(prod1+" ") || i.getTitle().equals(prod1)) {
                BaseProduct p1 = (BaseProduct) i;
                items1.add(p1);
            }
        }
    }
    @Override
    public void createCompositeProduct(String title) {
        int size=products.size();
        CompositeProduct c = new CompositeProduct();
        c.setItems(items1);
        c.setTitle(title);
        c.computePrice();
        c.compute();
        products.add(c);
        items1.clear();
        assert size + 1 == products.size();
    }

    @Override
    public User registerUser(String username, String password) {
        User u =new User(users.size()+1, username, password);
        users.add(u);
        return u;
    }

    @Override
    public void createOrder(int userID, ArrayList<MenuItem> items2, PrintWriter printWriter)  {
        assert userID != 0;
        assert items2 != null;

        Order order = new Order(orders.size()+1, userID);
        ArrayList<MenuItem> itemsss = new ArrayList<>(items2);
        orderss.add(order);
        orders.put(order, itemsss);
        setChanged();
        notifyObservers(order);

        for (User u : users) {
            if (u.getId() == userID)
                u.nrOrders++;
        }
        generateBill(order.getOrderID(),computeOrderPrice(items2),printWriter);
        items.clear();

    }

    @Override
    public int computeOrderPrice(ArrayList<MenuItem> items2) {
        int price = 0;
        for (MenuItem m : items2) {
            price += m.computePrice();
        }

        assert price >= 0;
        return price;
    }

    @Override
    public void generateBill(int orderID,int price, PrintWriter printWriter) {
        assert orderID != 0;
            printWriter.println("Order: " + orderID);
            for (Map.Entry<Order, ArrayList<MenuItem>> i : orders.entrySet()) {
                if (orderID == i.getKey().getOrderID()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    printWriter.println("Date: " + i.getKey().getOrderDate().format(formatter));
                    printWriter.println("Client: " + i.getKey().getClientID());
                    printWriter.println("Price: " + price);
                    printWriter.println("\nProducts: ");
                    for (MenuItem m : i.getValue()) {
                        printWriter.print("Name: " + m.getTitle());
                        printWriter.println(" Price: " + m.getPrice());
                    }
                }
            }
    }

    @Override
    public List<MenuItem> searchByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title+" ") || p.getTitle().equals(title)).collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> searchByPrice(int price) {
        return products.stream().filter(p -> p.getPrice() >= price).collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> searchByTitleAndPrice(String title, int price) {
    return products.stream().filter(p -> p.getTitle().equals(title+" ") && p.getPrice() >= price)
                .collect(Collectors.toList());

    }


    public void addOrderItem(String item) {
        for (MenuItem i : products) {

            if (i.getTitle().equals(item+" ") || i.getTitle().equals(item)) {
                items.add(i);
                i.orderedTimes++;
                break;
            }
        }
    }

    @Override
    public void generateReport1(LocalDateTime start, LocalDateTime finish,PrintWriter printWriter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


        List<Order> ord = orderss.stream().filter(o -> o.getOrderDate().isAfter(start)).filter(o -> o.getOrderDate().isBefore(finish)).toList();
        for (Order i : ord) {
            printWriter.print("ID: " + i.getOrderID() + "  ");
            printWriter.print("Date: " + i.getOrderDate().format(formatter) + "  ");
        }
    }

    @Override
    public void generateReport2(int nr, PrintWriter printWriter){
        List<MenuItem> prod = products.stream().filter(p -> p.orderedTimes > nr).toList();
        for (MenuItem i : prod) {
            printWriter.println("Product: " + i.getTitle() + ", ordered " + i.orderedTimes + " times");
        }

    }

    @Override
    public void generateReport3(int nr, int value, PrintWriter printWriter)  {


        List<User> u = users.stream().filter(n -> n.nrOrders > nr).toList();
        List<User> u2 = new ArrayList<>();
        int found =0;
        for (User x : u) {
            for (Map.Entry<Order, ArrayList<MenuItem>> i : orders.entrySet()) {
                if (i.getKey().clientID==x.getId()) {
                    if (computeOrderPrice(i.getValue())>value) {
                        for (User x2 : u2)
                            if (x.equals(x2)) {
                                found = 1;
                                break;
                            }
                        if (found == 0)
                            u2.add(x);
                        else found = 0;
                    }
                }
            }
        }
        u2.forEach(o -> printWriter.println("CLient: " + o.getId()));
        printWriter.close();
    }

    @Override
    public void generateReport4(LocalDate date, PrintWriter printWriter) {
        List<Order> ord = orderss.stream().filter(o -> o.getOrderDate().toLocalDate().equals(date)).toList();
        List<MenuItem> prod = new ArrayList<>();
        int ok=0;
        for (Order i : ord) {
            for (Map.Entry<Order, ArrayList<MenuItem>> j : orders.entrySet()) {
                if (j.getKey().equals(i)) {
                    for (MenuItem m : j.getValue()) {
                        for(MenuItem pr :prod){
                            if (pr.getTitle().equals(m.getTitle())) {
                                ok = 1;
                                break;
                            }
                        }
                        if(ok==0)
                            prod.add(m);
                        else ok=0;
                    }
                }
            }
        }
        for (MenuItem o : prod) {
            printWriter.print("Product: " + o.getTitle() + "  ");
            printWriter.println("Nr. of times ordered: " + o.orderedTimes + "  ");
        }
    }
}
