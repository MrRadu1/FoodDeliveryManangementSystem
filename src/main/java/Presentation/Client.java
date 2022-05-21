package Presentation;

import BLL.DeliveryService;
import BLL.MenuItem;
import BLL.User;
import DAO.Serializator;

import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.List;

public class Client extends JFrame {
    private final JTextField textField;
    private final JTextField textField_1;
    private final JTextField textField_7;
    DeliveryService deliveryService;

    public Client(DeliveryService deliveryService, User user) {
        this.setTitle("Client");
        getContentPane().setLayout(null);
        this.deliveryService = deliveryService;

        JButton btnMenu = new JButton("View menu");
        btnMenu.setBounds(176, 29, 123, 21);
        getContentPane().add(btnMenu);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 509, 314);
        btnMenu.setBounds(180, 23, 142, 21);

        btnMenu.addActionListener(e -> new ViewMenu(deliveryService.products));
        getContentPane().add(btnMenu);

        JLabel lblNewLabel = new JLabel("Product name:");
        lblNewLabel.setBounds(34, 134, 88, 13);
        getContentPane().add(lblNewLabel);

        textField_7 = new JTextField();
        textField_7.setBounds(14, 157, 155, 19);
        getContentPane().add(textField_7);
        textField_7.setColumns(10);

        JButton btnNewButton = new JButton("Add product");
        btnNewButton.setBounds(180, 156, 142, 21);
        btnNewButton.addActionListener(e -> deliveryService.addOrderItem(textField_7.getText()));
        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Finish order");
        btnNewButton_1.setBounds(341, 156, 138, 21);
        btnNewButton_1.addActionListener(es -> {
            try {
                File f = new File("bill"+ deliveryService.orderss.size()+".txt");
                PrintWriter printWriter = new PrintWriter(f);
                deliveryService.createOrder(user.getId(), deliveryService.items,printWriter);
                deliveryService.items.clear();
                Serializator.serialization(deliveryService);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        getContentPane().add(btnNewButton_1);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(34, 54, 45, 13);
        getContentPane().add(lblTitle);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(162, 54, 45, 13);
        getContentPane().add(lblPrice);

        textField = new JTextField();
        textField.setBounds(10, 78, 125, 19);
        getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(155, 78, 73, 19);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);


        JButton btnNewButton_2 = new JButton("Search product");
        btnNewButton_2.setBounds(337, 77, 142, 21);
        btnNewButton_2.addActionListener(e -> {

            if (!textField.getText().isEmpty() && textField_1.getText().isEmpty()) {
                // search by title
                List<MenuItem> products = deliveryService.searchByTitle(textField.getText());
                new ViewMenu(products);
            } else if (!textField.getText().isEmpty() && !textField_1.getText().isEmpty()) {
                // search by title,price
                List<MenuItem> products  = deliveryService.searchByTitleAndPrice(textField.getText(),Integer.parseInt(textField_1.getText()));
                new ViewMenu(products);
            } else if (textField.getText().isEmpty() && !textField_1.getText().isEmpty()) {
                List<MenuItem> products  = deliveryService.searchByPrice(Integer.parseInt(textField_1.getText()));
                new ViewMenu(products);
            }
        });
        getContentPane().add(btnNewButton_2);



        this.setVisible(true);
    }
}
