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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

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
    private JFXComboBox<?> gender_cbox;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void numOnly(KeyEvent event) {
    }

    @FXML
    private void handleOkBtn(ActionEvent event) {
    }

    @FXML
    private void imageBtn(ActionEvent event) {
    }

    @FXML
    private void handleBackBtn(ActionEvent event) {
    }
    
}
