
import BLL.DeliveryService;
import DAO.Serializator;
import Presentation.Login;




public class MainClass {

    public static void main(String[] args) {
        //DeliveryService deliveryService = new DeliveryService();
            DeliveryService deliveryService = Serializator.deserialize();
            new Login(deliveryService);


    }
}