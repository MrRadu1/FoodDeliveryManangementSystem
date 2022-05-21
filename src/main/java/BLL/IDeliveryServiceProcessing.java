package BLL;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The DeliveryService interface which is implemented by the DeliveryService
 * It contains the main operations that can be executed by the administrator and the client
 */

public interface IDeliveryServiceProcessing {


    /**
     * Method used to create a new BaseProduct
     * @pre item!=null
     * @post  old size +1 =new size
     */
    void addProduct(BaseProduct menuItem);

    /**
     * Method used to delete a given product
     * @pre item!=null
     * @post  old size = new size + 1
     */
     boolean deleteProduct(String menuItemName);

    /**
     * Method used to modify the price of a product
     * @pre itemName!=NULL
     * @post item.price=price
     */
     void updateProduct(String itemName, int price);

    /**
     * Method used to create a new CompositeProduct
     * @pre itemName!=NULL
     * @post item.price=price
     */
     void createCompositeProduct(String title);

     void addBaseToCompositeProduct(String prod1);



     User registerUser(String username, String password);

    /**
     * Method used to create a new order
     * @pre userID !=0
     * @post the given order == created order
     */
     void createOrder(int userID, ArrayList<MenuItem> items, PrintWriter printWriter) ;

    /**
     * Method used to compute the total price of the order
     * @post price>0
     */
     int computeOrderPrice(ArrayList<MenuItem> items);

    /**
     * Method used to generate the bill
     * @pre  orderID>0

     */
     void generateBill(int orderID,int price,PrintWriter printWriter);

     List<MenuItem> searchByTitle(String title);

     List<MenuItem> searchByPrice(int price);

     List<MenuItem> searchByTitleAndPrice(String title, int price);

     void generateReport1(LocalDateTime start, LocalDateTime finish, PrintWriter printWriter);

     void generateReport2(int nr, PrintWriter printWriter);

     void generateReport3(int nr, int value,PrintWriter printWriter);

     void generateReport4(LocalDate date,PrintWriter printWriter) ;

}
