package Presentation;



import BLL.DeliveryService;
import BLL.User;
import DAO.Serializator;

import javax.swing.*;

public class Login extends JFrame {
    private final JTextField userTxt;
    private final JTextField passTxt;

    Employee employee;
    public int employeeView = 1;

    public Login(DeliveryService deliveryService) {
        employee = new Employee(deliveryService);
        deliveryService.addObserver(employee);

        this.setTitle("Login");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100, 100, 400, 300);
        this.getContentPane().setLayout(null);

        JLabel userLbl = new JLabel("Username:");
        userLbl.setBounds(56, 57, 62, 13);
        getContentPane().add(userLbl);

        JLabel passLbl = new JLabel("Password:");
        passLbl.setBounds(56, 93, 62, 13);
        getContentPane().add(passLbl);

        userTxt = new JTextField();
        userTxt.setBounds(124, 54, 96, 19);
        getContentPane().add(userTxt);
        userTxt.setColumns(10);

        passTxt = new JTextField();
        passTxt.setColumns(10);
        passTxt.setBounds(124, 90, 96, 19);
        getContentPane().add(passTxt);

        JButton regBtn = new JButton("Register client");
        regBtn.setBounds(111, 157, 120, 21);
        getContentPane().add(regBtn);

        JButton logBtn = new JButton("Login");
        logBtn.setBounds(111, 196, 120, 21);
        getContentPane().add(logBtn);
        regBtn.addActionListener(e -> {
            int found = 0;
            String pass = passTxt.getText();
            for (User i : deliveryService.users) {
                if (i.getUsername().equals(userTxt.getText()) && i.getPassword().equals(pass)) {
                    found = 1;
                }
            }
            if (found == 0) {
                User u = deliveryService.registerUser(userTxt.getText(), pass);
                Serializator.serialization(deliveryService);
                new Client(deliveryService, u);
                JOptionPane.showMessageDialog(null, "Client registered.", null, JOptionPane.INFORMATION_MESSAGE);
            } else
                JOptionPane.showMessageDialog(null, "Client already exists.", null, JOptionPane.INFORMATION_MESSAGE);
        });


        logBtn.addActionListener(e -> {
            String pass = passTxt.getText();
            if (userTxt.getText().equals("admin") && pass.equals("admin")) {
                new Administrator(deliveryService);
            } else {
                int found = 0;
                for (User i : deliveryService.users) {
                    if (i.getUsername().equals(userTxt.getText()) && i.getPassword().equals(pass)) {
                        new Client(deliveryService, i);
                        found = 1;
                        break;
                    }
                }
                if (found == 0) {
                    if (userTxt.getText().equals("emp") && pass.equals("emp")) {
                        if (employeeView == 1) {
                            employee.frame.setVisible(true);
                            employeeView = 0;
                        } else {
                            employee.frame.dispose();
                            employeeView = 1;
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "User not found.", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        this.setVisible(true);
    }
}


