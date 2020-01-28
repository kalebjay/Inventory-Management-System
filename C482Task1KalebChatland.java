package c482task1.kaleb.chatland;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Kaleb Chatland
 */
public class C482Task1KalebChatland extends Application
{
    
    
        @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    
    }

    public static void main(String[] args)
    {
        
        //pre-populated table data for testing and program usage 
        InhousePart part1 = new InhousePart(1, "sprocket", 20.00, 1, 1, 5, 2045);
        InhousePart part2 = new InhousePart(2, "Data Link", 150.11, 1, 3, 9, 2089);
        InhousePart part3 = new InhousePart(3, "VSAT G400k", 2036.56, 0, 1, 5, 3075);
        InhousePart part4 = new InhousePart(4, "Dynimo 45t", 2.99, 1, 6, 18, 9044);
        
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        
        ArrayList<Part> invParts1 = new ArrayList<>();
        Product product1 = new Product(1, "Neogenic recombinator", 2, 23000.27, 2, 4, invParts1);
        ArrayList<Part> invParts2 = new ArrayList<>();
        Product product2 = new Product(2, "Shrink Ray", 3, 93000.01, 2, 4, invParts2);
        ArrayList<Part> invParts3 = new ArrayList<>();
        Product product3 = new Product(3, "Sub-Atomic cathode IGS", 7, 564.99, 2, 4, invParts3);
        ArrayList<Part> invParts4 = new ArrayList<>();
        Product product4 = new Product(4, "Death Ray", 1, 4400000.00, 2, 4, invParts4);
        
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
        
        launch(args);
    }
    
}
