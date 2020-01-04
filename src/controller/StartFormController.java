/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Gillian
 */
public class StartFormController implements Initializable {
    
    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passField;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXButton registerBtn;

    @FXML
    private JFXButton adminBtn;
    @FXML
    private Label userlabel;
    @FXML
    private Label passlabel;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginBtn(ActionEvent event) throws IOException, ClassNotFoundException {
        
                    try {
                //checking the username textfield if empty
                if (usernameField.getText().trim().isEmpty() && passField.getText().trim().isEmpty()) {
                    //display the label if username and password are empty
                    userlabel.setVisible(true);
                    passlabel.setVisible(true);
                    userlabel.setText("username is empty");
                    passlabel.setText("password is empty");
                    JOptionPane.showMessageDialog(null, "username or password is empty");
                }
                if (usernameField.getText().trim().isEmpty()) {
                    userlabel.setVisible(true);
                    userlabel.setText("username is empty");
                    JOptionPane.showMessageDialog(null, "username is empty");
                } else if (usernameField.getText().trim().isEmpty()) {
                    userlabel.setVisible(true);
                    userlabel.setText("username is empty");

                } else if (passField.getText().trim().isEmpty()) {
                    passlabel.setVisible(true);
                    passlabel.setText("password is empty");
                    JOptionPane.showMessageDialog(null, "password is empty");
                } else {
                    Connection con = DBconnect.connect();
                    String temp = "SELECT * FROM New_PawnBroker WHERE Username = ? and Password = ? ";
                    PreparedStatement ps = con.prepareStatement(temp);
                    ps.setString(1, usernameField.getText());
                    ps.setString(2, passField.getText());
                    ResultSet res = ps.executeQuery();
                    if (res.next()) {
                        Parent changeToVform = FXMLLoader.load(getClass().getResource("/view/PawnshopForm.fxml"));
                        Scene changeVformScene = new Scene(changeToVform);
                        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        mainStage.centerOnScreen();
                        mainStage.setScene(changeVformScene);
                        mainStage.centerOnScreen();
                        mainStage.show();
                        JOptionPane.showMessageDialog(null, "Welcome " + usernameField.getText());
                    } else {
                        JOptionPane.showMessageDialog(null, "username or password are incorrect");
                        usernameField.setText("");
                        passField.setText("");
                    }
                }

            } catch (HeadlessException | IOException | SQLException e) {
                JOptionPane.showConfirmDialog(null, e);
            }
        
        
    }

    @FXML
    private void registerBtn(ActionEvent event) throws IOException {
        Parent changeToForm = FXMLLoader.load(getClass().getResource("/view/NewPawnbroker.fxml"));
        Scene changeFormScene = new Scene(changeToForm);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(changeFormScene);
        mainStage.centerOnScreen();
        mainStage.show();
    }

    @FXML
    private void adminBtn(ActionEvent event) throws IOException {
        Parent changeToForm = FXMLLoader.load(getClass().getResource("/view/AdminLogin.fxml"));
        Scene changeFormScene = new Scene(changeToForm);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(changeFormScene);
        mainStage.centerOnScreen();
        mainStage.show();
    }
    
}
