
package com.accountmanagement.controllers;

import com.accountmanagement.models.Product;
import com.accountmanagement.repositories.product.ProductSqliteRepository;
import com.accountmanagement.utils.AlertMaker;
import com.accountmanagement.utils.Constants;
import com.accountmanagement.utils.DateFormater;
import com.accountmanagement.utils.NotificationMaker;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;


public class ProductsController implements Initializable {
    
    
//    private final ProductSqliteRepository productRepo;
    ProductSqliteRepository productRepo = new ProductSqliteRepository();
    @FXML
    private TableColumn<Product, String> colBuyerName;
    @FXML
    private TableColumn<Product, String> colPhone;
    
//    public ProductsController(ProductSqliteRepository productRepo) {
////        this.productRepo = productRepo;
//    }

    @FXML
    private TextField txtSerial;
    @FXML
    private TextField txtBuyerName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private DatePicker txtSubsDate;
    @FXML
    private TextField txtSubsValue;
    @FXML
    private TextField txtSerialSearch;
    @FXML
    private TextField txtBNSearch;
    @FXML
    private TableView<Product> tbProducts;
    @FXML
    private TextField txtSerialSearchPL;
    @FXML
    private TextField txtBNSearchPL;
    @FXML
    private TableView<Product> tbProductsPL;
    @FXML
    private Label lbStatus;
    @FXML
    private TableColumn<Product, Integer> colId;
    @FXML
    private TableColumn<Product, String> colSerial;
    private TableColumn<Product, String> colByerName;
    @FXML
    private TableColumn<Product, String> colEmail;
    @FXML
    private TableColumn<Product, String> colPassword;
    @FXML
    private TableColumn<Product, String> colSubsDate;
    @FXML
    private TableColumn<Product, Double> colSubsValue;
    @FXML
    private TableColumn<Product, Integer> colIdPL;
    @FXML
    private TableColumn<Product, String> colSerialPL;
    @FXML
    private TableColumn<Product, String> colBuyerNamePL;
    @FXML
    private TableColumn<Product, String> colPhonePL;
    @FXML
    private TableColumn<Product, String> colEmailPL;
    @FXML
    private TableColumn<Product, String> colPasswordPL;
    @FXML
    private TableColumn<Product, String> colSubsDatePL;
    @FXML
    private TableColumn<Product, Double> colSubsValuePL;
    
    DateTimeFormatter dateTimeFormater = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // set tables data
        settbProductsData();
        settbProductsPLData();
        
        // set date
        txtSubsDate.setValue(LocalDate.now());
        
        // table selection listener
        
        tbProducts.getFocusModel().focusedCellProperty().addListener(new ChangeListener<TablePosition>() {
            @Override
            public void changed(ObservableValue<? extends TablePosition> observable, TablePosition oldValue, TablePosition newValue) {
                try {
                    Product product = tbProducts.getSelectionModel().getSelectedItem();
                    
                    if(product == null) return;
                    
                    txtSerial.setText(product.getSerial());
                    txtBuyerName.setText(product.getBuyerName());
                    txtPhone.setText(product.getBuyerPhone());
                    txtEmail.setText(product.getBuyerEmail());
                    txtPassword.setText(product.getPassword());
                    txtSubsDate.setValue(LocalDate.parse(product.getSubscribtionDate(), dateTimeFormater));
                    txtSubsValue.setText(String.valueOf(product.getSubscribtionValue()));
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    AlertMaker.showErrorALert(e.toString());
                }
            }
            
        });
    }    

    @FXML
    private void save(ActionEvent event) {
        try {
            
            // validation
            if(txtSerial.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter the serial");
                return;
            }
            
            if(txtBuyerName.getText().isEmpty()){
                AlertMaker.showErrorALert("Enter buyer name");
                return;
            }
            
            if(txtPhone.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Phone");
                return;
            }
            
            if(txtEmail.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Email");
                return;
            }
            
            if(txtPassword.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Password");
                return;
            }
            
            if(txtSubsDate.getValue().equals(null)) {
                AlertMaker.showErrorALert("Choose Date");
                return;
            }
            
            if(txtSubsValue.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Subscribtion Value");
                return;
            }
            
            
            
            String serial = txtSerial.getText();
            String buyerName = txtBuyerName.getText();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            String password = txtPassword.getText();
            Date d = Date.from(txtSubsDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String date = DateFormater.format(d);
            double value = Double.valueOf(txtSubsValue.getText());
            
            
            
            // create product
            Product product = Product.builder()
                    .serial(serial)
                    .buyerName(buyerName)
                    .buyerPhone(phone)
                    .buyerEmail(email)
                    .password(password)
                    .subscribtionDate(date)
                    .subscribtionValue(value)
                    .build();
            
            if(productRepo.save(product)) {
                lbStatus.setText(product.getSerial() + " Saved");
                NotificationMaker.showInformation(product.getSerial() + " Saved");
                settbProductsData();
                settbProductsPLData();
                clearTextFields();
                txtSerial.requestFocus();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.getMessage());
        }
    }

    @FXML
    private void update(ActionEvent event) {
        
        try {
//            System.out.println("update");
            // validation
            if(txtSerial.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter the serial");
                return;
            }
            
            if(txtBuyerName.getText().isEmpty()){
                AlertMaker.showErrorALert("Enter buyer name");
                return;
            }
            
            if(txtPhone.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Phone");
                return;
            }
            
            if(txtEmail.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Email");
                return;
            }
            
            if(txtPassword.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Password");
                return;
            }
            
            if(txtSubsDate.getValue().equals(null)) {
                AlertMaker.showErrorALert("Choose Date");
                return;
            }
            
            if(txtSubsValue.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Subscribtion Value");
                return;
            }
            
            
            
            String serial = txtSerial.getText();
            String buyerName = txtBuyerName.getText();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            String password = txtPassword.getText();
            Date d = Date.from(txtSubsDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String date = DateFormater.format(d);
            double value = Double.valueOf(txtSubsValue.getText());
//            int id = tbProducts.getSelectionModel().getSelectedItem().getId();
            
            Product oldProduct = tbProducts.getSelectionModel().getSelectedItem();
            
            if(oldProduct == null) {
                AlertMaker.showErrorALert("Choose Product First");
                return;
            }
            
            Product product = Product.builder()
                    .id(oldProduct.getId())
                    .serial(serial)
                    .buyerName(buyerName)
                    .buyerPhone(phone)
                    .buyerEmail(email)
                    .password(password)
                    .subscribtionDate(date)
                    .subscribtionValue(value)
                    .build();
//            System.out.println(product.getId());
            Optional<ButtonType> response = AlertMaker.showConfirmationAlert("update product with serial: "+ product.getSerial() + " ?");
            
            if(response.get() == ButtonType.OK) {
                if(productRepo.update(product)) {
                    lbStatus.setText(oldProduct.getSerial() + " Updated");
                    NotificationMaker.showInformation(oldProduct.getSerial() + " Updated");
                    settbProductsData();
                    settbProductsPLData();
                    clearTextFields();
                    txtSerial.requestFocus();
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.getMessage());
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        try {
            Product product = tbProducts.getSelectionModel().getSelectedItem();
            
            if(product == null) {
                AlertMaker.showErrorALert("choose product first");
                return;
            }
            
            
            Optional<ButtonType> response = AlertMaker.showConfirmationAlert("delete product with serial : " + product.getSerial() + " ?");
            
            if(response.get() == ButtonType.OK) {
                if(productRepo.delete(product.getId())) {
                    settbProductsData();
                    settbProductsPLData();
                    lbStatus.setText(product.getSerial() + " Deleted");
                    NotificationMaker.showInformation(product.getSerial() + " Deleted");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void settbProductsData() {
        try {
            List list = productRepo.findAll();
            ObservableList<Product> data = FXCollections.observableArrayList(list);
            
            
            colId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
            colSerial.setCellValueFactory(new PropertyValueFactory<Product, String>("serial"));
            colBuyerName.setCellValueFactory(new PropertyValueFactory<Product, String>("buyerName"));
            colPhone.setCellValueFactory(new PropertyValueFactory<Product, String>("buyerPhone"));
            colEmail.setCellValueFactory(new PropertyValueFactory<Product, String>("buyerEmail"));
            colPassword.setCellValueFactory(new PropertyValueFactory<Product, String>("password"));
            colSubsDate.setCellValueFactory(new PropertyValueFactory<Product, String>("subscribtionDate"));
            colSubsValue.setCellValueFactory(new PropertyValueFactory<Product, Double>("subscribtionValue"));
            
            tbProducts.setItems((javafx.collections.ObservableList<Product>) data);
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.getMessage());
        }
    }

    private void settbProductsPLData() {
        try {
            List list = productRepo.findAll();
            ObservableList<Product> data = FXCollections.observableArrayList(list);
            
            colIdPL.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
            colSerialPL.setCellValueFactory(new PropertyValueFactory<Product, String>("serial"));
            colBuyerNamePL.setCellValueFactory(new PropertyValueFactory<Product, String>("buyerName"));
            colPhonePL.setCellValueFactory(new PropertyValueFactory<Product, String>("buyerPhone"));
            colEmailPL.setCellValueFactory(new PropertyValueFactory<Product, String>("buyerEmail"));
            colPasswordPL.setCellValueFactory(new PropertyValueFactory<Product, String>("password"));
            colSubsDatePL.setCellValueFactory(new PropertyValueFactory<Product, String>("subscribtionDate"));
            colSubsValuePL.setCellValueFactory(new PropertyValueFactory<Product, Double>("subscribtionValue"));
            
             tbProductsPL.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.getMessage());
        }
    }

    private void clearTextFields() {
        try {
            txtSerial.clear();
            txtBuyerName.clear();
            txtPhone.clear();
            txtEmail.clear();
            txtPassword.clear();
            txtSubsDate.setValue(LocalDate.now());
            txtSubsValue.clear();
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void filterTableProducts(KeyEvent event) {
        try {
            String serial = txtSerialSearch.getText();
            String BuyerName = txtBNSearch.getText();
            
            ArrayList list = productRepo.findBySerialOrBuyerName(serial, BuyerName);
            ObservableList<Product> data = FXCollections.observableArrayList(list);
            
            tbProducts.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void filterTableProductsList(KeyEvent event) {
        try {
            String serial = txtSerialSearchPL.getText();
            String BuyerName = txtBNSearchPL.getText();
            
            ArrayList list = productRepo.findBySerialOrBuyerName(serial, BuyerName);
            ObservableList<Product> data = FXCollections.observableArrayList(list);
            
            tbProductsPL.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
        private void printProductDetails(ActionEvent event) {
        try {
            
            Product product = tbProducts.getSelectionModel().getSelectedItem();
            
            if(product == null) {
                AlertMaker.showMessageAlert("Choose Product First");
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("serial", product.getSerial());
            hashMap.put("buyerName", product.getBuyerName());
            hashMap.put("buyerPhone", product.getBuyerPhone());
            hashMap.put("buyerEmail", product.getBuyerEmail());
            hashMap.put("password", product.getPassword());
            hashMap.put("subscribtionDate", product.getSubscribtionDate());
            hashMap.put("subscribtionValue", product.getSubscribtionValue());
            
            showProductCard(hashMap);
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void printProductDetailsFromProductList(ActionEvent event) {
        try {
            
            Product product = tbProductsPL.getSelectionModel().getSelectedItem();
            
            if(product == null) {
                AlertMaker.showMessageAlert("Choose Product First");
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("serial", product.getSerial());
            hashMap.put("buyerName", product.getBuyerName());
            hashMap.put("buyerPhone", product.getBuyerPhone());
            hashMap.put("buyerEmail", product.getBuyerEmail());
            hashMap.put("password", product.getPassword());
            hashMap.put("subscribtionDate", product.getSubscribtionDate());
            hashMap.put("subscribtionValue", product.getSubscribtionValue());
            
            showProductCard(hashMap);
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }
    
    private void showProductCard(HashMap map) {
        try {
            String reportName = Constants.REPORTS_PATH +"productCard.jasper";
            InputStream report;
            report = getClass().getResourceAsStream(reportName);
            ArrayList list = new ArrayList();     
            
            JasperPrint jPrint = JasperFillManager.fillReport(report, map, new JREmptyDataSource(1));
            
            JasperViewer.viewReport(jPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
    }
    
    
}
