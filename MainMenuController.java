package c482task1.kaleb.chatland;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kaleb Chatland
 */
public class MainMenuController implements Initializable
{

    Stage stage;
    Parent scene;
    private static int index; 
    
    //method to be able to grab selected part or product index from other controllers
    public static int modifyIndex() {
        return index;
    }
    
    @FXML
    private TableView<Part> partsTableview;

    @FXML
    private TableColumn<Part, Integer> partID;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Integer> partsInventLvL;

    @FXML
    private TableColumn<Part, Double> partsPriceCost;

    @FXML
    private TextField searchPartsTxt;
    
    @FXML
    private Button modifyPartsBTN;
    
    @FXML
    private Button  modifyProductsBTN;

    @FXML
    private TableView<Product> productsTableview;

    @FXML
    private TableColumn<Product, Integer> productID;

    @FXML
    private TableColumn<Product, String> productName;

    @FXML
    private TableColumn<Product, Integer> ProdInventLvL;

    @FXML
    private TableColumn<Product, Double> productPriceCost;

    @FXML
    private TextField searchProductsTxt;
    
    @FXML
    public ObservableList<Part> tempPart=FXCollections.observableArrayList();
    public ObservableList<Product> tempProduct=FXCollections.observableArrayList();
    
    //Initializes the controller class
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        partsTableview.setItems(Inventory.getAllParts());
        
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventLvL.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productsTableview.setItems(Inventory.getAllProducts());
        
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProdInventLvL.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));       
    }   
    
    public static void deleteConfirmation()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("");
        alert.setContentText("Item successfully deleted");
        alert.showAndWait();
    }
    
    @FXML
    void onActionDeletePart(ActionEvent event) 
    {
            
        Part part = partsTableview.getSelectionModel().getSelectedItem();
        Inventory.deletePart(part);  
        deleteConfirmation();
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) 
    {
            
        Product product = productsTableview.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(product);
        deleteConfirmation();
    }

    @FXML
    void onActionExitProgram(ActionEvent event) 
    {
        Platform.exit();
    }

    @FXML
    void onActionGoToAddPartScreen(ActionEvent event) throws IOException 
    {
            
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }

    @FXML
    void onActionGoToModifyPartScreen(ActionEvent event) throws IOException 
    {
        Part selectedPart = partsTableview.getSelectionModel().getSelectedItem();
        index = Inventory.getAllParts().indexOf(selectedPart);
            
        Stage stage; 
        Parent root;       
            
        stage=(Stage) modifyPartsBTN.getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
        root =loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
              
        ModifyPartController controller = loader.getController();
        Part part = partsTableview.getSelectionModel().getSelectedItem();
        controller.setPart(part);
    }
    
    @FXML
    void onActionGoToAddProductScreen(ActionEvent event) throws IOException 
    {
            
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
            
    }

    @FXML
    void onActionGoToModifyProductScreen(ActionEvent event) throws IOException 
    {
        Product selectedProduct = productsTableview.getSelectionModel().getSelectedItem();
        index = Inventory.getAllProducts().indexOf(selectedProduct);
        
        Stage stage; 
        Parent root;       
            
        stage=(Stage) modifyProductsBTN.getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
        root =loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
              
        ModifyProductController controller = loader.getController();
        Product product = productsTableview.getSelectionModel().getSelectedItem();
        controller.setProduct(product);
            
    }
    
    @FXML
    void onActionSearchParts(ActionEvent event) 
    {
        String searchItem=searchPartsTxt.getText();
        if (searchItem.equals("")){
                partsTableview.setItems(Inventory.getAllParts());
        } else
        {
            boolean found=false;
            try
            {
                int itemNumber=Integer.parseInt(searchItem);
                Part p = Inventory.lookupPart(itemNumber);
                    if(p != null)
                    {
                        infoMessage(itemNumber);                       
                        found=true;
                        tempPart.clear();
                        tempPart.add(p);
                        partsTableview.setItems(tempPart);
                    } 
                    if (found==false)
                    {
                        partsTableview.setItems(Inventory.getAllParts());
                        errorMessage();                
                    }
            }
            //catches strings used instead of numbers 
            catch(NumberFormatException e)
            {              
                String itemName = searchItem;
                Part n = Inventory.lookupPart(itemName);
                    if(n != null)
                    {
                        infoMessage(itemName);
                        found=true;
                        tempPart.clear();
                        tempPart.add(n);
                        partsTableview.setItems(tempPart);
                    }
            
                    if (found==false)
                    {
                        partsTableview.setItems(Inventory.getAllParts());
                        errorMessage();           
                    }
            }
        }
    }

    @FXML
    void onActionSearchProducts(ActionEvent event) 
    {
        String searchItem=searchProductsTxt.getText();
        if (searchItem.equals(""))
        {
                productsTableview.setItems(Inventory.getAllProducts());
        } else 
        {
            boolean found=false;
            try
            {
                int itemNumber=Integer.parseInt(searchItem);
                Product p = Inventory.lookupProduct(itemNumber);
                    if(p != null)
                    {                                              
                        found=true;
                        infoMessage(itemNumber);
                        tempProduct.clear();
                        tempProduct.add(p);
                        productsTableview.setItems(tempProduct);
                    }
                    if (found==false)
                    {
                        productsTableview.setItems(Inventory.getAllProducts());
                        errorMessage();
                    }
            }
            catch(NumberFormatException e)
            {
                
                String itemName = searchItem;
                Product n = Inventory.lookupProduct(itemName);
                    if(n != null)
                    {
                        found=true;
                        infoMessage(itemName);
                        tempProduct.clear();
                        tempProduct.add(n);
                        productsTableview.setItems(tempProduct);
                    }         
                   
                    if (found==false)
                    {
                        productsTableview.setItems(Inventory.getAllProducts());
                        errorMessage();
                    }
            }
        }   
    }        
    
    void errorMessage()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Item not found");
        alert.setContentText("Please search by item ID# or exact Name");
        alert.showAndWait();
    }
    
    void infoMessage(int item)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Item ID:"+item+" Successfully Found");
        alert.setContentText("Item is displayed in table");
        alert.showAndWait();
    }
    
    void infoMessage(String item)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Item \""+item+"\" successfully found");
        alert.setContentText("Item is displayed in table");
        alert.showAndWait();
    }
}
