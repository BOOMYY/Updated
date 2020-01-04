/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private JFXTextField otherIdNum_txtf;
    @FXML
    private JFXButton addCustomerBtn;
    @FXML
    private ImageView ivCustomer;
    @FXML
    private TableView<CustomerList> customerTbl;
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
    @FXML
    private JFXTextField searchField;
    @FXML
    private Label customerCountLbl;
    @FXML
    private Label itemCountLbl;
    @FXML
    private TableView<?> dashTbl;

    //for table Observable list (encapsulation)
    ObservableList<CustomerList> customer_list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<CustomerList, String> cusFnameCol;
    @FXML
    private TableColumn<CustomerList, String> cusLnameCol;
    @FXML
    private TableColumn<CustomerList, String> cusAddressCol;
    @FXML
    private TableColumn<CustomerList, String> cusMobileCol;

    private Statement st;
    private ResultSet rs;
    private Connection con;
    private PreparedStatement pst;
    @FXML
    private TableColumn<CustomerList, String> cusIdCol;
    @FXML
    private JFXDatePicker datePicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tblToFieldVoutList();

        try {
            HashSet<CustomerList> db = new HashSet<>();
            con = DBconnect.connect();
            CustomerList c;
            st = con.createStatement();
            rs = st.executeQuery("select * from Add_Customer");
            while (rs.next()) {
                int id = rs.getShort("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String address = rs.getString("address");
                String mobile_num = rs.getString("mobile_num");
                c = new CustomerList(id, fname, lname, address, mobile_num);
                customer_list.add(c);
                customerTbl.setItems(customer_list);
            }

            rs.close();
            refreshCustomerTbl();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PawnshopForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        //table in the customer
        cusIdCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        cusFnameCol.setCellValueFactory(
                new PropertyValueFactory<>("fname"));
        cusLnameCol.setCellValueFactory(
                new PropertyValueFactory<>("lname"));
        cusAddressCol.setCellValueFactory(
                new PropertyValueFactory<>("address"));
        cusMobileCol.setCellValueFactory(
                new PropertyValueFactory<>("mobile_num"));
        customerTbl.setItems(customer_list);

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
                    || mobilenum_txtf.equals("") || pathArea.equals("")) {
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
                String insertQuery = "INSERT INTO Add_Customer (fname, lname, address, mobile_num, image) VALUES(?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(insertQuery);

                String fname = fname_txtf.getText();
                String lname = lname_txtf.getText();
                String address = address_txtf.getText();
                String mobile_nos = mobilenum_txtf.getText();

                pst.setString(1, fname);
                pst.setString(2, lname);
                pst.setString(3, address);
                pst.setString(4, mobile_nos);

                FileInputStream fis = new FileInputStream(file);
                pst.setBinaryStream(5, (InputStream) fis, (int) file.length());

                ResultSet rs = pst.executeQuery();
                JOptionPane.showMessageDialog(null, "New Customer Added");

                refreshCustomerTbl();
                clearCustomerField();
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

    private void refreshCustomerTbl() throws ClassNotFoundException {
        customer_list.clear();

        try {
            String dash_query = "select * from Add_Customer";
            con = DBconnect.connect();
            pst = con.prepareStatement(dash_query);
            rs = pst.executeQuery();

            while (rs.next()) {
                customer_list.add(new CustomerList(rs.getShort("id"), rs.getString("fname"),
                        rs.getString("lname"), rs.getString("address"), rs.getString("mobile_num")));
            }
            customerTbl.setItems(customer_list);
            pst.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e);
        }
    }

    private void tblToFieldVoutList() {
        try {

            customerTbl.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
                CustomerList customerList = customerTbl.getSelectionModel().getSelectedItem();
                //VisitorOutList vOutList = vTime_OutTbl.getItems().get(vTime_OutTbl.getSelectionModel().getSelectedIndex());
                //NoTxt.setText(vOutList.getNo());
                fname_txtf.setText(customerList.getFname());
                lname_txtf.setText(customerList.getLname());
                address_txtf.setText(customerList.getAddress());
                mobilenum_txtf.setText(customerList.getMobile_num());
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e);
        }
    }

    private void clearCustomerField() {
        fname_txtf.setText("");
        lname_txtf.setText("");
        address_txtf.setText("");
        mobilenum_txtf.setText("");
        pathArea.setText("");
    }

}
