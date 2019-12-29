/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Gillian
 */
public class AdminLogin implements Initializable {

    @FXML
    private JFXTextField unameTxtf;
    @FXML
    private JFXPasswordField pwordTxtf;
    @FXML
    private JFXButton login_btn;
    @FXML
    private Label userlabel;
    @FXML
    private Label passlabel;
    @FXML
    private JFXButton backBtn;

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginKey(KeyEvent event) {
    }

    @FXML
    private void handleLoginBtn(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        try {
            if(unameTxtf.getText().trim().isEmpty() && pwordTxtf.getText().trim().isEmpty()){
                userlabel.setVisible(true);
                passlabel.setVisible(true);
                userlabel.setText("username is empty");
                passlabel.setText("password is empty");
                JOptionPane.showMessageDialog(null, "username or password is empty");
            }
            if(unameTxtf.getText().trim().isEmpty()){
                userlabel.setVisible(true);
                userlabel.setText("username is empty");
                JOptionPane.showMessageDialog(null, "username is empty");
            } else if (unameTxtf.getText().trim().isEmpty()) {
                userlabel.setVisible(true);
                userlabel.setText("username is empty");

             } else if (pwordTxtf.getText().trim().isEmpty()) {
                passlabel.setVisible(true);
                passlabel.setText("password is empty");
                JOptionPane.showMessageDialog(null, "password is empty");
                  } else {
            Connection con = DBconnect.connect();
            String sq1 = "SELECT * FROM admin_table where username=? and password=?";
            PreparedStatement pst = con.prepareStatement(sq1);
            pst.setString(1, unameTxtf.getText());
            pst.setString(2, pwordTxtf.getText());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
               Parent changeToVform = FXMLLoader.load(getClass().getResource("/view/PawnshopForm.fxml"));
                    Scene changeVformScene = new Scene(changeToVform);
                    Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    mainStage.centerOnScreen();
                    mainStage.setScene(changeVformScene);
                    mainStage.show();
                    JOptionPane.showMessageDialog(null, "WELCOME " + unameTxtf.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "username or password are incorrect");
                    unameTxtf.setText("");
                    pwordTxtf.setText("");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void backBtn(ActionEvent event) throws IOException {
        Parent changeToForm = FXMLLoader.load(getClass().getResource("/view/StartForm.fxml"));
        Scene changeFormScene = new Scene(changeToForm);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(changeFormScene);
        mainStage.centerOnScreen();
        mainStage.show();
    }

}
