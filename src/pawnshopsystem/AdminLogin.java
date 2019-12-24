/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pawnshopsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginKey(KeyEvent event) {
    }

    @FXML
    private void handleLoginBtn(ActionEvent event) {
    }

    @FXML
    private void backBtn(ActionEvent event) {
    }
    
}
