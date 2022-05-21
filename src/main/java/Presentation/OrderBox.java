package Presentation;

import BLL.CompositeProduct;
import BLL.MenuItem;
import BLL.Order;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrderBox {

    private final JScrollPane scrollPane;
    private final Order order;
    public Box box;

    public OrderBox(Order order, ArrayList<MenuItem> content) {
        this.order = order;
        box = Box.createVerticalBox();
        JLabel orderNumber = new JLabel("Order number: " + order.getOrderID());
        orderNumber.setForeground(Color.BLUE);
        box.add(orderNumber);
        JLabel prod = new JLabel("Products: ");
        box.add(prod);
        int s = 0;
        for (MenuItem m : content) {
            s = s+m.getPrice();
            JLabel temp = new JLabel("   " + m.getTitle());
            box.add(temp);
            if (m instanceof CompositeProduct) {
                for (MenuItem item : ((CompositeProduct) m).getItems()) {
                    if (item.getTitle()!=null) {
                        JLabel subTemp = new JLabel("   " + item.getTitle());
                        box.add(subTemp);
                    }
                }
            }
        }
        JLabel price = new JLabel("\nTotal price: " + s);
        box.add(price);
        scrollPane = new JScrollPane(box);
        scrollPane.setPreferredSize(new Dimension(250, 250));
    }

    public JScrollPane getScrollablePane() {
        return scrollPane;
    }

    public Order getOrder() {
        return order;
    }
}