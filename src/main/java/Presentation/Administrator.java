package Presentation;

import BLL.BaseProduct;
import BLL.DeliveryService;
import DAO.Serializator;

import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Administrator extends JFrame {

    private final JTextField titleTxt;
    private final JTextField textField;
    private final JTextField caloriesTxt;
    private final JTextField proteinTxt;
    private final JTextField fatTxt;
    private final JTextField sodiumTxt;
    private final JTextField priceTxt;
    private final JTextField baseTxt;
    private final JTextField nameTxt;
    private final JTextField rep1Txt;
    private final JTextField rep2Txt;
    private final JTextField rep3Txt;
    private final JTextField rep4Txt;
    private final JTextField rep5Txt;
    private final JTextField rep6Txt;


    DeliveryService deliveryService;

    public Administrator(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Admin");
        this.setBounds(100, 100, 546, 545);
        this.getContentPane().setLayout(null);
        JLabel titleLbl = new JLabel("Title:");
        titleLbl.setBounds(20, 57, 62, 13);
        getContentPane().add(titleLbl);

        JLabel ratingLbl = new JLabel("Rating:");
        ratingLbl.setBounds(20, 93, 62, 13);
        getContentPane().add(ratingLbl);

        titleTxt = new JTextField();
        titleTxt.setBounds(85, 54, 76, 19);
        getContentPane().add(titleTxt);
        titleTxt.setColumns(10);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(85, 90, 76, 19);
        getContentPane().add(textField);

        JLabel lblCalories = new JLabel("Calories:");
        lblCalories.setBounds(20, 128, 62, 13);
        getContentPane().add(lblCalories);

        caloriesTxt = new JTextField();
        caloriesTxt.setColumns(10);
        caloriesTxt.setBounds(85, 125, 76, 19);
        getContentPane().add(caloriesTxt);

        JLabel proteinLbl = new JLabel("Protein:");
        proteinLbl.setBounds(20, 164, 62, 13);
        getContentPane().add(proteinLbl);

        proteinTxt = new JTextField();
        proteinTxt.setColumns(10);
        proteinTxt.setBounds(85, 161, 76, 19);
        getContentPane().add(proteinTxt);

        JLabel fatLbl = new JLabel("Fat:");
        fatLbl.setBounds(20, 200, 62, 13);
        getContentPane().add(fatLbl);

        fatTxt = new JTextField();
        fatTxt.setColumns(10);
        fatTxt.setBounds(85, 197, 76, 19);
        getContentPane().add(fatTxt);

        JLabel sodiumLbl = new JLabel("Sodium:");
        sodiumLbl.setBounds(20, 235, 62, 13);
        getContentPane().add(sodiumLbl);

        sodiumTxt = new JTextField();
        sodiumTxt.setColumns(10);
        sodiumTxt.setBounds(85, 232, 76, 19);
        getContentPane().add(sodiumTxt);

        JLabel priceLbl = new JLabel("Price:");
        priceLbl.setBounds(20, 273, 62, 13);
        getContentPane().add(priceLbl);

        priceTxt = new JTextField();
        priceTxt.setColumns(10);
        priceTxt.setBounds(85, 270, 76, 19);
        getContentPane().add(priceTxt);

        JButton addProdBtn = new JButton("Add product");
        addProdBtn.setBounds(309, 89, 85, 21);
        getContentPane().add(addProdBtn);
        addProdBtn.addActionListener(e -> {
            BaseProduct product = new BaseProduct(titleTxt.getText(), Float.parseFloat(textField.getText()),
                    Integer.parseInt(caloriesTxt.getText()), Integer.parseInt(proteinTxt.getText()),
                    Integer.parseInt(fatTxt.getText()), Integer.parseInt(sodiumTxt.getText()),
                    Integer.parseInt(priceTxt.getText()));
            deliveryService.addProduct(product);
            Serializator.serialization(deliveryService);
        });

        JButton btnDeleteProduct = new JButton("Delete product");
        btnDeleteProduct.setBounds(309, 124, 85, 21);
        getContentPane().add(btnDeleteProduct);
        btnDeleteProduct.addActionListener(e -> {
            deliveryService.deleteProduct(titleTxt.getText());
            Serializator.serialization(deliveryService);
        });

        JButton modBtn = new JButton("Modify product");
        modBtn.setBounds(309, 160, 85, 21);
        getContentPane().add(modBtn);
        modBtn.addActionListener(e -> {
            deliveryService.updateProduct(titleTxt.getText(), Integer.parseInt(priceTxt.getText()));
            Serializator.serialization(deliveryService);
        });
        baseTxt = new JTextField();
        baseTxt.setColumns(10);
        baseTxt.setBounds(309, 200, 96, 19);
        getContentPane().add(baseTxt);

        nameTxt = new JTextField();
        nameTxt.setColumns(10);
        nameTxt.setBounds(309, 232, 96, 19);
        getContentPane().add(nameTxt);

        JButton addBaseBtn = new JButton("Add");
        addBaseBtn.setBounds(416, 199, 85, 21);
        getContentPane().add(addBaseBtn);
        addBaseBtn.addActionListener(e -> deliveryService.addBaseToCompositeProduct(baseTxt.getText()));

        JButton createBtn = new JButton("Create");
        createBtn.setBounds(415, 231, 85, 21);
        getContentPane().add(createBtn);
        createBtn.addActionListener(e -> {
            deliveryService.createCompositeProduct(nameTxt.getText());
            Serializator.serialization(deliveryService);
        });

        JButton viewBtn = new JButton("View menu");
        viewBtn.setBounds(349, 269, 85, 21);
        getContentPane().add(viewBtn);
        viewBtn.addActionListener(e -> new ViewMenu(deliveryService.products));

        rep1Txt = new JTextField();
        rep1Txt.setColumns(10);
        rep1Txt.setBounds(20, 373, 76, 19);
        getContentPane().add(rep1Txt);

        rep2Txt = new JTextField();
        rep2Txt.setColumns(10);
        rep2Txt.setBounds(20, 402, 76, 19);
        getContentPane().add(rep2Txt);

        rep3Txt = new JTextField();
        rep3Txt.setColumns(10);
        rep3Txt.setBounds(146, 387, 76, 19);
        getContentPane().add(rep3Txt);

        rep4Txt = new JTextField();
        rep4Txt.setColumns(10);
        rep4Txt.setBounds(268, 373, 76, 19);
        getContentPane().add(rep4Txt);

        rep5Txt = new JTextField();
        rep5Txt.setColumns(10);
        rep5Txt.setBounds(268, 402, 76, 19);
        getContentPane().add(rep5Txt);

        rep6Txt = new JTextField();
        rep6Txt.setColumns(10);
        rep6Txt.setBounds(382, 387, 76, 19);
        getContentPane().add(rep6Txt);

        JButton rep1Btn = new JButton("Rep1");
        rep1Btn.setBounds(20, 441, 76, 21);
        getContentPane().add(rep1Btn);
        rep1Btn.addActionListener(e-> {
            String start = rep1Txt.getText();
            String finish = rep2Txt.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startData = LocalDateTime.parse(start, formatter);
            LocalDateTime finishData = LocalDateTime.parse(finish, formatter);
            try {
                File f = new File("Rep.txt");
                PrintWriter printWriter = new PrintWriter(f);
                deliveryService.generateReport1(startData, finishData,printWriter);
                printWriter.close();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                });

        JButton rep2Btn = new JButton("Rep2");
                rep2Btn.setBounds(146, 441, 76, 21);
                getContentPane().add(rep2Btn);
                rep2Btn.addActionListener(e -> {
                    int nr = Integer.parseInt(rep3Txt.getText());
                    try {
                        File f = new File("Report2.txt");
                        PrintWriter printWriter = new PrintWriter(f);
                        deliveryService.generateReport2(nr,printWriter);
                        printWriter.close();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                });

        JButton rep3Btn = new JButton("Rep3");
                rep3Btn.setBounds(268, 441, 76, 21);
                getContentPane().add(rep3Btn);
                rep3Btn.addActionListener(e -> {
                    int nr = Integer.parseInt(rep4Txt.getText());
                    int value = Integer.parseInt(rep5Txt.getText());
                    try {
                        File f = new File("Report3.txt");
                        PrintWriter printWriter = new PrintWriter(f);
                        deliveryService.generateReport3(nr, value,printWriter);
                        printWriter.close();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                });
        JButton rep4Btn = new JButton("Rep4");
                rep4Btn.setBounds(382, 441, 76, 21);
                getContentPane().add(rep4Btn);
                rep4Btn.addActionListener(e -> {
                    String date = rep6Txt.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateTime = LocalDate.parse(date, formatter);
                    try {
                        File f = new File("Report4.txt");
                        PrintWriter printWriter = new PrintWriter(f);
                        deliveryService.generateReport4(dateTime,printWriter);
                        printWriter.close();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                });

                this.setVisible(true);

            }
        }
