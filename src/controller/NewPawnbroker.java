/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import java.sql.Statement;
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
public class NewPawnbroker implements Initializable {

    @FXML
    private JFXTextField fname_txtf;
    @FXML
    private JFXTextField lname_txtf;
    @FXML
    private JFXTextField address_txtf;
    @FXML
    private JFXTextField email_txtf;
    @FXML
    private JFXTextField mobilenum_txtf;
    @FXML
    private JFXComboBox<String> gender_cbox;
    @FXML
    private JFXTextField uname_txtf;
    @FXML
    private JFXTextField password_txtf;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXButton ok_btn;
    @FXML
    private Button imageBtn;
    @FXML
    private JFXButton back_btn;
    @FXML
    private JFXTextArea pathArea;
    private Stage stage;
    private Image image;
    private File file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pathArea.setEditable(false);
        gender_cbox.getItems().addAll(
                "Male", "Female"
        );
    }

    @FXML
    private void numOnly(KeyEvent event) {
    }

    @FXML
    private void handleOkBtn(ActionEvent event) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        try {
            if (uname_txtf.equals("") || password_txtf.equals("") || fname_txtf.equals("") || lname_txtf.equals("") || address_txtf.equals("")
                    || gender_cbox.equals("") || email_txtf.equals("") || pathArea.equals("")) {
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
                String insertQuery = "INSERT INTO New_PawnBroker (Username, Password, FirstName, LastName, Address, Gender, MobileNum, Email, Image) VALUES(?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(insertQuery);
                String uname = uname_txtf.getText();
                String pssword = password_txtf.getText();
                String fname = fname_txtf.getText();
                String lname = lname_txtf.getText();
                String address = address_txtf.getText();
                String gender = (String) gender_cbox.getValue();
                String email = email_txtf.getText();
                String mobile_nos = mobilenum_txtf.getText();
                pst.setString(1, uname);
                pst.setString(2, pssword);
                pst.setString(3, fname);
                pst.setString(4, lname);
                pst.setString(5, address);
                pst.setString(6, gender);
                pst.setString(7, email);
                pst.setString(8, mobile_nos);
                FileInputStream fis = new FileInputStream(file);
                pst.setBinaryStream(9, (InputStream) fis, (int) file.length());
                ResultSet rs = pst.executeQuery();
                JOptionPane.showMessageDialog(null, "New Pawn Broker");

                //transition to Existing Visitor Form    
                Parent changeToMain = FXMLLoader.load(getClass().getResource("/view/PawnShopForm.fxml"));
                Scene changeMainScene = new Scene(changeToMain);
                Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                mainStage.setScene(changeMainScene);
                mainStage.show();
                JOptionPane.showMessageDialog(null, "Successfully Registered!");

            }
        } catch (HeadlessException | IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void imageBtn(ActionEvent event) {

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
                    imageView.setImage(image);

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void handleBackBtn(ActionEvent event) throws IOException {
        Parent changeToForm = FXMLLoader.load(getClass().getResource("/view/StartForm.fxml"));
        Scene changeFormScene = new Scene(changeToForm);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(changeFormScene);
        mainStage.centerOnScreen();
        mainStage.show();
    }

}
