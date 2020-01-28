package c482task1.kaleb.chatland;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kaleb Chatland
 */
public class ModifyPartController implements Initializable
{
    Stage stage;
    Parent scene;
    
    @FXML
    private RadioButton inHouseTogBTN;

    @FXML
    private ToggleGroup InOutTG;

    @FXML
    private RadioButton outsourcedTogBTN;
    
    @FXML
    private Label companyMachineLabel;
    
    @FXML
    private TextField idTxt;
    
    @FXML
    private TextField nameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceCostTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField machineCompanyTxt;
    
    private OutsourcedPart selectedOutPart;
    private InhousePart selectedInPart;
    int partIndex = MainMenuController.modifyIndex();
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        InOutTG = new ToggleGroup();
        this.inHouseTogBTN.setToggleGroup(InOutTG);
        this.outsourcedTogBTN.setToggleGroup(InOutTG);
    }    
    
    public void onActoinRadioBTN()
    {
         if (this.InOutTG.getSelectedToggle().equals(this.inHouseTogBTN))
         {
            companyMachineLabel.setText("Machine ID");
            machineCompanyTxt.setPromptText("Machine ID");
         }
         if (this.InOutTG.getSelectedToggle().equals(this.outsourcedTogBTN))
         {
            companyMachineLabel.setText("Company Name");
            machineCompanyTxt.setPromptText("Company Name");
         }
    }   
    
    @FXML
    void onActionGoToMainMenu(ActionEvent event) throws IOException 
        {   
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }      

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException 
        {
            int itemID = Integer.parseInt(idTxt.getText());
            String name = nameTxt.getText();
            int stock = Integer.parseInt(invTxt.getText());
            double price = Double.parseDouble(priceCostTxt.getText());
            int min = Integer.parseInt(maxTxt.getText());
            int max = Integer.parseInt(minTxt.getText());
            
            if ((this.InOutTG.getSelectedToggle().equals(this.inHouseTogBTN))) 
            {
                int machineID = Integer.parseInt(machineCompanyTxt.getText());
                Inventory.updatePart(partIndex, new InhousePart(itemID, name, price, stock, min, max, machineID));
            }else
                {
                    String companyName = machineCompanyTxt.getText();
                    Inventory.updatePart(partIndex, new OutsourcedPart(itemID, name, price, stock, min, max, companyName));
                }
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            
        }    
            
        public void setPart(Part part) 
        {       
            idTxt.setText(Integer.toString(part.getPartID()));
            nameTxt.setText(part.getName());
            invTxt.setText(Integer.toString(part.getStock()));
            priceCostTxt.setText(Double.toString(part.getPrice()));
            minTxt.setText(Integer.toString(part.getMin()));
            maxTxt.setText(Integer.toString(part.getMax()));

            if (part instanceof InhousePart) 
                {
                    selectedInPart = (InhousePart) part;
                    companyMachineLabel.setText("Machine ID");
                    inHouseTogBTN.selectedProperty().set(true);
                    machineCompanyTxt.setText(Integer.toString(selectedInPart.getMachineID()));
                } else 
                    {
                        selectedOutPart = (OutsourcedPart) part;
                        companyMachineLabel.setText("Company Name");
                        outsourcedTogBTN.selectedProperty().set(true);
                        machineCompanyTxt.setText(selectedOutPart.getCompanyName());
                    }
            
        }      
    
}
