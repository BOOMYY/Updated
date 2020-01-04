/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Gillian
 */
public class PawnshopForm implements Initializable {

    @FXML
    private JFXButton signOutBtn;
    @FXML
    private JFXTextField fname_txtf;
    @FXML
    private JFXTextField lname_txtf;
    @FXML
    private JFXTextField address_txtf;
    @FXML
    private JFXTextField mobilenum_txtf;
    @FXML
    private JFXTextField otherIdNum_txtf;
    @FXML
    private JFXButton addCustomerBtn;
    @FXML
    private ImageView ivCustomer;
    @FXML
    private TableView<?> customerTbl;
    @FXML
    private Tab pawnItemTbl;
    @FXML
    private ImageView ivPawnItem;
    @FXML
    private JFXButton addItemBtn;
    @FXML
    private JFXTextField pathArea;
    @FXML
    private Button addImageBtn;

    private Image image;
    private File file;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void signOutBtn(ActionEvent event) throws IOException {
        Parent changeToForm = FXMLLoader.load(getClass().getResource("/view/StartForm.fxml"));
        Scene changeFormScene = new Scene(changeToForm);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(changeFormScene);
        mainStage.centerOnScreen();
        mainStage.show();
    }

    @FXML
    private void numOnly(KeyEvent event) {
    }

    @FXML
    private void addCustomerBtn(ActionEvent event) throws ClassNotFoundException, FileNotFoundException {
        try {
            if (fname_txtf.equals("") || lname_txtf.equals("") || address_txtf.equals("")
                    || mobilenum_txtf.equals("") || otherIdNum_txtf.equals("") || pathArea.equals("")) {
                JOptionPane.showMessageDialog(null, "Incomplete Details!");
//            } else {
//                Connection con = DBconnect.connect();
//                Statement s = con.createStatement();
//                String query = "SELECT * FROM New_PawnBroker where MobileNum='" + mobilenum_txtf + "'";
//                ResultSet rs = s.executeQuery(query);
//                if (rs.next()) {
//                    JOptionPane.showMessageDialog(null, "Mobile Number already exist!");
            } else {
                Connection con = DBconnect.connect();
                String insertQuery = "INSERT INTO add_customer (FirtName, LastName, Address, MobileNum, OtherID, Image) VALUES(?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(insertQuery);

                String fname = fname_txtf.getText();
                String lname = lname_txtf.getText();
                String address = address_txtf.getText();
                String mobile_nos = mobilenum_txtf.getText();
                String other_id_num = otherIdNum_txtf.getText();

                pst.setString(1, fname);
                pst.setString(2, lname);
                pst.setString(3, address);
                pst.setString(4, mobile_nos);
                pst.setString(5, other_id_num);

                FileInputStream fis = new FileInputStream(file);
                pst.setBinaryStream(6, (InputStream) fis, (int) file.length());

                ResultSet rs = pst.executeQuery();
                JOptionPane.showMessageDialog(null, "New Customer Added");

//                //transition to Existing Visitor Form    
//                Parent changeToMain = FXMLLoader.load(getClass().getResource("/view/PawnShopForm.fxml"));
//                Scene changeMainScene = new Scene(changeToMain);
//                Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                mainStage.setScene(changeMainScene);
//                mainStage.show();
//                JOptionPane.showMessageDialog(null, "Successfully Registered!");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void addItemBtn(ActionEvent event) {
    }

    @FXML
    private void addImageBtn(ActionEvent event) {
        
                try {
            FileChooser fc = new FileChooser();
            file = fc.showOpenDialog(stage);
            if (file != null) {
                System.out.println(file.getAbsolutePath());
                image = new Image(file.toURI().toString(), 150, 100, true, true);
                BufferedImage bf;
                try {
                    bf = ImageIO.read(file);
                    WritableImage newImage = SwingFXUtils.toFXImage(bf, null);
                    ivCustomer.setImage(image);

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    

}
