/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Gillian
 */
public class StartFormController implements Initializable {
    
    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXTextField passField;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXButton registerBtn;

    @FXML
    private JFXButton adminBtn;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginBtn(ActionEvent event) {
    }

    @FXML
    private void registerBtn(ActionEvent event) {
    }

    @FXML
    private void adminBtn(ActionEvent event) {
    }
    
}
