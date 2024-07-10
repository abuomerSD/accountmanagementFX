
package com.accountmanagement.controllers;

import com.accountmanagement.models.Customer;
import com.accountmanagement.models.SProduct;
import com.accountmanagement.models.SalesInvoiceDetails;
import com.accountmanagement.models.SalesInvoiceHeader;
import com.accountmanagement.repositories.currency.CurrencySqliteRepository;
import com.accountmanagement.repositories.sCustomer.ScustomerSqliteRepository;
import com.accountmanagement.repositories.salesinvoicedetails.SalesInvoiceDetailsSqliteRepository;
import com.accountmanagement.repositories.salesinvoiceheader.SalesInvoiceHeaderSqliteRepository;
import com.accountmanagement.utils.AlertMaker;
import com.accountmanagement.utils.Constants;
import com.accountmanagement.utils.NotificationMaker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class SalesInvoicesController implements Initializable {

    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextField txtCustomerPhone;
    @FXML
    private Label lbCustomerStatus;
    @FXML
    private TextField txtCustomerNameSearch;
    @FXML
    private TableView<Customer> tbCustomer;
    @FXML
    private TableColumn<Customer, Integer> colCustomerId;
    @FXML
    private TableColumn<Customer, String> colCustomerName;
    @FXML
    private TableColumn<Customer, String> colCustomerPhone;
    @FXML
    private DatePicker txtDate;
    @FXML
    private ComboBox<String> cbInvoiceType;
    @FXML
    private TextField txtFilePath;
    @FXML
    private ComboBox<String> cbCustomerName;
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtPrice;
    @FXML
    private Label lbDiscount;
    @FXML
    private Label lbTax;
    @FXML
    private TextField txtTax;
    @FXML
    private TextField txtDiscount;
    @FXML
    private TextField txtComment;
    @FXML
    private Label lbInvoiceStatus;
    @FXML
    private TextField txtInvoiceIdSearch;
    @FXML
    private ComboBox<String> cbCustomerSearch;
    @FXML
    private ComboBox<String> cbInvoiceTypeSearch;
    @FXML
    private Button resetInvoicesListTable;
    @FXML
    private TableView<SalesInvoiceHeader> tbInvoicesList;
    @FXML
    private TableView<SProduct> tbProducts;
    @FXML
    private TableColumn<SProduct, String> colProductName;
    @FXML
    private TableColumn<SProduct, Double> colProductQty;
    @FXML
    private TableColumn<SProduct, Double> colProductPrice;
    @FXML
    private TableColumn<SProduct, Double> colProductTotal;
    @FXML
    private Label lbProductsTotal;
    @FXML
    private Label lbInvoiceTotal;
    private boolean isUpdateInvoice = false;
    @FXML
    private Button btnSaveInvoice;
    @FXML
    private Button btnPrintInvoice;
    @FXML
    private Button btnNewInvoice;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnDeleteProduct;
    @FXML
    private Button btnOpenFileChooser;
    @FXML
    private Button btnDeleteAllProducts;
    @FXML
    private Label lbInvoiceId;
    @FXML
    private Tab col;
    @FXML
    private TableColumn<SalesInvoiceHeader, Long> colIdIL;
    @FXML
    private TableColumn<SalesInvoiceHeader, String> colDateIL;
    @FXML
    private TableColumn<SalesInvoiceHeader, String> colCustomerNameIL;
    @FXML
    private TableColumn<SalesInvoiceHeader, String> colCommentIL;
    @FXML
    private TableColumn<SalesInvoiceHeader, Double> colTaxIL;
    @FXML
    private TableColumn<SalesInvoiceHeader, Double> colDiscountIL;
    @FXML
    private TableColumn<SalesInvoiceHeader, Double> colTotalIL;
    @FXML
    private TableColumn<SalesInvoiceHeader, String> colInvoiceTypeIL;
    @FXML
    private TableColumn<SalesInvoiceHeader, String> colFilePathIL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // fill tables
        fillSalesInvoicesTable();
        fillCustomerTable();
        
        // cb's
        setAllCbsCustomerName();        
        setAllCbsInvoiceType();        
        setInvoicesTypeCb();
        
        // date
        setInvoiceDate();
        
        // table selection listeners
        
        tbCustomer.getFocusModel().focusedCellProperty().addListener(new ChangeListener<TablePosition>() {
            @Override
            public void changed(ObservableValue<? extends TablePosition> observable, TablePosition oldValue, TablePosition newValue) {
                try {
                    Customer customer = tbCustomer.getSelectionModel().getSelectedItem();
                    
                    if(customer == null) return;
                    
                    txtCustomerName.setText(customer.getName());
                    txtCustomerPhone.setText(customer.getPhone());
                    
                } catch(Exception e) {
                    e.printStackTrace();
                    AlertMaker.showErrorALert(e.toString());
                }
            }
            
        });
        
        btnPrintInvoice.setDisable(true);
        
    }   
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
    DecimalFormat numberFormater =  new DecimalFormat("#,###,###.##");
    ScustomerSqliteRepository customerRepo = new ScustomerSqliteRepository();
    CurrencySqliteRepository currencyRepo = new CurrencySqliteRepository();
    SalesInvoiceHeaderSqliteRepository headerRepo = new SalesInvoiceHeaderSqliteRepository();
    SalesInvoiceDetailsSqliteRepository detailsRepo = new SalesInvoiceDetailsSqliteRepository();
    ObservableList<SProduct> productObList = FXCollections.observableArrayList();

    @FXML
    private void saveCustomer(ActionEvent event) {
        try {
            // validation 
            
            if(txtCustomerName.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Customer Name");
                return;
            }
            
            if(txtCustomerPhone.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Customer Phone");
                return;
            }
            
            String name = txtCustomerName.getText();
            String phone = txtCustomerPhone.getText();
            
            Customer customer = Customer.builder()
                    .name(name)
                    .phone(phone)
                    .build();
            
            if(customerRepo.save(customer)) {
                setAllCbsCustomerName();        
                fillCustomerTable();
                clearCustomerTf();
                txtCustomerName.requestFocus();
                lbCustomerStatus.setText(customer.getName() + " Saved");
                NotificationMaker.showInformation(customer.getName() + " Saved");
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void updateCustomer(ActionEvent event) {
        try {
            // validation 
            
            if(txtCustomerName.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Customer Name");
                return;
            }
            
            if(txtCustomerPhone.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Customer Phone");
                return;
            }
            
            String name = txtCustomerName.getText();
            String phone = txtCustomerPhone.getText();
            
            Customer oldCustomer = tbCustomer.getSelectionModel().getSelectedItem();
            
            if(oldCustomer == null) {
                AlertMaker.showErrorALert("Choose Customer First");
                return;
            }
            
            Customer newCustomer = Customer.builder()
                    .id(oldCustomer.getId())
                    .name(name)
                    .phone(phone)
                    .build();
            
            Optional<ButtonType> response = AlertMaker.showConfirmationAlert("Update " + oldCustomer.getName() + " ?");
            
            if(response.get() == ButtonType.OK) {
                if(customerRepo.update(newCustomer)) {
                    setAllCbsCustomerName();
                    fillCustomerTable();
                    clearCustomerTf();
                    txtCustomerName.requestFocus();
                    lbCustomerStatus.setText(oldCustomer.getName() + " Updated");
                    NotificationMaker.showInformation(oldCustomer.getName() + " Updated");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void deleteCustomer(ActionEvent event) {
        try {
            Customer customer = tbCustomer.getSelectionModel().getSelectedItem();
            
            if(customer == null) {
                AlertMaker.showErrorALert("Choose Customer First");
                return;
            }
            
            Optional<ButtonType> response = AlertMaker.showConfirmationAlert("Delete " + customer.getName() + " ?");
            
            if(response.get() == ButtonType.OK) {
                if(customerRepo.delete(customer.getId())) {
                    setAllCbsCustomerName();
                    fillCustomerTable();
                    clearCustomerTf();
                    txtCustomerName.requestFocus();
                    lbCustomerStatus.setText(customer.getName() + " Deleted");
                    NotificationMaker.showInformation(customer.getName() + " Deleted");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void filterCustomerTable(KeyEvent event) {
        try {
            String customerName = txtCustomerNameSearch.getText();
            ArrayList<Customer> list = customerRepo.findBySearchWords(customerName);
            ObservableList<Customer> data = FXCollections.observableArrayList(list);
            
            tbCustomer.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void openFileChooser(ActionEvent event) {
        try {
            // validation
            
            if(!cbInvoiceType.getSelectionModel().getSelectedItem().equals("From File")) {
                AlertMaker.showErrorALert("Set Invoice Type as 'From File'");
                return;
            }
            
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            
            if(file == null) return;
            
            System.out.println(file.getAbsolutePath());
            txtFilePath.setText(file.getAbsolutePath());
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void addProductToTable(ActionEvent event) {
        try {
            // validation
            
            if(txtProductName.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Product Name");
                return;
            }
            
            if(txtQty.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Product Quantity");
                return;
            }
            
            if(txtPrice.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Product Price");
                return;
            }
            
            String name = txtProductName.getText();
            double qty = Double.valueOf(txtQty.getText());
            double price = Double.valueOf(txtPrice.getText());
            double total = qty * price ;
            
            SProduct product = new SProduct(name, qty, price, total);
            
            colProductName.setCellValueFactory(new PropertyValueFactory<SProduct, String>("name"));
            colProductQty.setCellValueFactory(new PropertyValueFactory<SProduct, Double>("qty"));
            colProductPrice.setCellValueFactory(new PropertyValueFactory<SProduct, Double>("price"));
            colProductTotal.setCellValueFactory(new PropertyValueFactory<SProduct, Double>("total"));
            
            productObList.add(product);
            
            tbProducts.setItems(productObList);
            
            clearProductTf();
            
            calculateInvoiceTotal();
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void deleteProduct(ActionEvent event) {
        try {
            SProduct product = tbProducts.getSelectionModel().getSelectedItem();
            
            if(product == null) {
                AlertMaker.showErrorALert("Choose Product First");
                return;
            }
            
            productObList.remove(product);
            
            calculateInvoiceTotal();
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void deleteAllProducts(ActionEvent event) {
        try {
            Optional<ButtonType> response = AlertMaker.showConfirmationAlert("Delete All Products ?");
            
            if(!(response.get() == ButtonType.OK)) return;
            
            productObList.clear();
            
            calculateInvoiceTotal();
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void saveInvoice(ActionEvent event) {
        try {
            if(!isUpdateInvoice) {
                saveSalesInvoice();
            } else {
                updateSalesInvoice();
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void printInvoice(ActionEvent event) {
        try {
            long headerId = 0;
        
            headerId = Long.valueOf(lbInvoiceId.getText());
        
            printSalesInvoice(headerId);
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void newInvoice(ActionEvent event) {
        try {
            enableControls();
            resetInvoice();
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void viewInvoice(ActionEvent event) {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void updateInvoice(ActionEvent event) {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void deleteInvoice(ActionEvent event) {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void fillSalesInvoicesTable() {
        try {
            ArrayList<SalesInvoiceHeader> list = headerRepo.findAllDesc();
            ObservableList<SalesInvoiceHeader> ob = FXCollections.observableArrayList(list);
            
            for (SalesInvoiceHeader header : ob) {
                header.setCustomerName(customerRepo.findById(header.getCustomerId()).getName());
                if(header.isIsFileType()) {
                    header.setInvoiceType("From File");
                } else {
                    header.setInvoiceType("Normal Invoice");
                }
            }
            
            colIdIL.setCellValueFactory(new PropertyValueFactory<>("id"));
            colDateIL.setCellValueFactory(new PropertyValueFactory<>("date"));
            colCustomerNameIL.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            colCommentIL.setCellValueFactory(new PropertyValueFactory<>("comment"));
            colTaxIL.setCellValueFactory(new PropertyValueFactory<>("tax"));
            colDiscountIL.setCellValueFactory(new PropertyValueFactory<>("discount"));
            colTotalIL.setCellValueFactory(new PropertyValueFactory<>("total"));
            colInvoiceTypeIL.setCellValueFactory(new PropertyValueFactory<>("invoiceType"));
            colFilePathIL.setCellValueFactory(new PropertyValueFactory<>("filePath"));
            
            tbInvoicesList.setItems(ob);
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

//    private void fillCustomersTable() {
//        try {
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            AlertMaker.showErrorALert(e.toString());
//        }
//    }

    private void setInvoicesTypeCb() {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void setInvoiceDate() {
        try {
            txtDate.setValue(LocalDate.now());
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void fillCustomerTable() {
        try {
            ArrayList<Customer> list = customerRepo.findAll();
            ObservableList<Customer> ob = FXCollections.observableArrayList(list);
            
            colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            
            tbCustomer.setItems(ob);
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void clearCustomerTf() {
        try {
            txtCustomerName.clear();
            txtCustomerPhone.clear();
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void setAllCbsCustomerName() {
        try {
            ArrayList<Customer> list = customerRepo.findAll();
            ObservableList<String> obList = FXCollections.observableArrayList();
                
            list.forEach(customer -> obList.add(customer.getName()));
            
            cbCustomerName.getItems().clear();
            cbCustomerName.getItems().addAll(obList);
            cbCustomerName.getSelectionModel().selectFirst();
            
            cbCustomerSearch.getItems().clear();
            cbCustomerSearch.getItems().addAll(obList);
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void setAllCbsInvoiceType() {
        try{
            ObservableList<String> ob = FXCollections.observableArrayList();
            ob.add("Normal Invoice");
            ob.add("From File");
            
            cbInvoiceType.getItems().clear();
            cbInvoiceTypeSearch.getItems().clear();
            
            cbInvoiceType.getItems().addAll(ob);
            cbInvoiceTypeSearch.getItems().addAll(ob);
            
            cbInvoiceType.setValue("Normal Invoice");
            
            
        } catch(Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void clearProductTf() {
        try {
            txtProductName.setText("");
            txtPrice.setText("");
            txtQty.setText("");
            txtProductName.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

   
    private void calculateInvoiceTotal() {
        try{
           double productsTotal = 0.00;
           
            for (SProduct product : productObList) {
                productsTotal += product.getTotal();
            }
            
            lbProductsTotal.setText(String.valueOf(productsTotal));
            
            if(txtDiscount.getText().isEmpty()) {
                txtDiscount.setText("0.00");
            }
            
            if(txtTax.getText().isEmpty()) {
                txtTax.setText("0.00");
            }
            
//            if(!"java.lang.Double".equals(Double.valueOf(txtDiscount.getText()).getClass().getName())) {
//                txtDiscount.setText("0.00");
//            }
//            System.out.println(Double.valueOf(txtDiscount.getText()).getClass().getName());
//            if(!"java.lang.Double".equals(Double.valueOf(txtTax.getText()).getClass().getName())) {
//                txtTax.setText("0.00");
//            }
            
            double discount = Double.valueOf(txtDiscount.getText());
            double tax = Double.valueOf(txtTax.getText());
            
            double total = productsTotal + tax - discount;
            
            lbTax.setText(String.valueOf(tax));
            lbDiscount.setText(String.valueOf(discount));
            lbInvoiceTotal.setText(String.valueOf(total));
            
        } catch(Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }
    
    @FXML
    private void calculateTaxAndDiscount(KeyEvent event) {
        calculateInvoiceTotal();
    }

    private void saveSalesInvoice() {
        try {
            
            if(cbInvoiceType.getSelectionModel().getSelectedItem().equals("From File")) {
                saveInvoiceAsFile();
                return;
            }
            
            if(productObList.size() < 1) {
                AlertMaker.showErrorALert("Enter Some Products First");
                return;
            }
            
            Optional<ButtonType> response = AlertMaker.showConfirmationAlert("Save Invoice ?");
            
            if(!(response.get() == ButtonType.OK)) return;
            
            
            // invoice header
            
            Date dateBeforeFormat = Date.from(txtDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String date = dateFormat.format(dateBeforeFormat);
            
            String customerName = cbCustomerName.getSelectionModel().getSelectedItem();
            if(customerName == null) {
                AlertMaker.showErrorALert("Choose Customer");
                return;
            }
            
            HashMap customersMap = getCustomerHashMap();
            int customerId = (int) customersMap.get(customerName);
            
            boolean isFileType;
            String filePath = null;
            
            if(cbInvoiceType.getSelectionModel().getSelectedItem().equals("From File")){
                isFileType = true;
                filePath = txtFilePath.getText();
            } else {
                isFileType = false;
            }
            
            double tax = Double.valueOf(txtTax.getText());
            double discount = Double.valueOf(txtDiscount.getText());
            String comment = txtComment.getText();
            double invoiceTotal = Double.valueOf(lbInvoiceTotal.getText());
            
            SalesInvoiceHeader header = SalesInvoiceHeader.builder()
                    .date(date)
                    .customerId(customerId)
                    .total(invoiceTotal)
                    .isFileType(isFileType)
                    .filePath(filePath)
                    .tax(tax)
                    .discount(discount)
                    .comment(comment)
                    .build();
            
            long headerId = headerRepo.save(header);
            lbInvoiceStatus.setText("Saving ...");
            
            // invoice details
            
            productObList.forEach(product -> {
                SalesInvoiceDetails details = SalesInvoiceDetails.builder()
                        .headerId(headerId)
                        .productName(product.getName())
                        .productQty(product.getQty())
                        .productPrice(product.getPrice())
                        .productTotal(product.getTotal())
                        .build();
                
                detailsRepo.save(details);
            });

            resetInvoice();
            btnPrintInvoice.setDisable(false);
            btnSaveInvoice.setDisable(true);
            lbInvoiceStatus.setText("Sales Invoice Saved with Id : " + headerId);
            NotificationMaker.showInformation("Sales Invoice Saved with Id : " + headerId);
            lbInvoiceId.setText(String.valueOf(headerId));
            fillSalesInvoicesTable();
            disableInvoiceControls();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void saveInvoiceAsFile() {
        try {

            if(txtFilePath.getText().isEmpty()) {
                AlertMaker.showErrorALert("Choose File");
                return;
            }
            
            File src = new File(txtFilePath.getText());
            
            String extension = "";

            int i = src.getName().lastIndexOf('.');
            if (i > 0) {
                extension = src.getName().substring(i+1);
            }
            
            String newFileName = UUID.randomUUID().toString() + "." + extension;
            
            String distPath = System.getProperty("user.dir") + "/invoices/" + newFileName ;
            File dist = new File(distPath);
            
            Optional<ButtonType> response = AlertMaker.showConfirmationAlert("Save Invoice ?");
            
            if(!(response.get() == ButtonType.OK)) return;
            

            copyFile(src, dist);

            // invoice header
            
            Date dateBeforeFormat = Date.from(txtDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String date = dateFormat.format(dateBeforeFormat);
            
            String customerName = cbCustomerName.getSelectionModel().getSelectedItem();
            
            if(customerName == null) {
                AlertMaker.showErrorALert("Choose Customer");
                return;
            }
            
            HashMap customersMap = getCustomerHashMap();
            int customerId = (int) customersMap.get(customerName);
            
            boolean isFileType;
            String filePath = null;
            
            if(cbInvoiceType.getSelectionModel().getSelectedItem().equals("From File")){
                isFileType = true;
                filePath = distPath;
            } else {
                isFileType = false;
            }
            
            double tax = 0;
            double discount = 0;
            String comment = txtComment.getText();
            
            
            SalesInvoiceHeader header = SalesInvoiceHeader.builder()
                    .date(date)
                    .customerId(customerId)
                    .total(0)
                    .isFileType(isFileType)
                    .filePath(filePath)
                    .tax(tax)
                    .discount(discount)
                    .comment(comment)
                    .build();
            
            lbInvoiceStatus.setText("Saving Invoice ...");
            
            long headerId = headerRepo.save(header);
            
            if(headerId > 0) {
                lbInvoiceStatus.setText("Invoice Saved with id : " + headerId);
                NotificationMaker.showInformation("Invoice Saved with id : " + headerId);
                resetInvoice();
                fillSalesInvoicesTable();
                disableInvoiceControls();
                btnSaveInvoice.setDisable(true);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private HashMap<String, Integer> getCustomerHashMap() {
        ArrayList<Customer> list = customerRepo.findAll();
        HashMap<String, Integer> map = new HashMap();
        
        for (Customer customer : list) {
            map.put(customer.getName(), customer.getId());
        }
        
        return map;
    }
    

    private void updateSalesInvoice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void copyFile(File src, File dist) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;
        
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dist);
            
            
            
            int c;
            
            while ((c = in.read()) != -1) {
                
                out.write(c);
                System.out.println(c);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    private void disableInvoiceControls() {
        try {
            txtDate.setDisable(true);
            cbInvoiceType.setDisable(true);
            btnOpenFileChooser.setDisable(true);
            cbCustomerName.setDisable(true);
            txtProductName.setDisable(true);
            txtQty.setDisable(true);
            btnAddProduct.setDisable(true);
            btnDeleteProduct.setDisable(true);
            btnDeleteAllProducts.setDisable(true);
            txtTax.setDisable(true);
            txtDiscount.setDisable(true);
            txtComment.setDisable(true);
            btnSaveInvoice.setDisable(true);
            productObList.clear();
            tbProducts.setDisable(true);
  
            
        } catch(Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }
    
    private void enableControls() {
        try {
            txtDate.setDisable(false);
            cbInvoiceType.setDisable(false);
            btnOpenFileChooser.setDisable(false);
            cbCustomerName.setDisable(false);
            txtProductName.setDisable(false);
            txtQty.setDisable(false);
            btnAddProduct.setDisable(false);
            btnDeleteProduct.setDisable(false);
            btnDeleteAllProducts.setDisable(false);
            txtTax.setDisable(false);
            txtDiscount.setDisable(false);
            txtComment.setDisable(false);
            btnSaveInvoice.setDisable(false);
            tbProducts.setDisable(false);
  
            
        } catch(Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void resetInvoice() {
        try {
            txtDate.setValue(LocalDate.now());
            cbInvoiceType.setValue("Normal Invoice");
            txtFilePath.clear();
            cbCustomerName.setValue("");
            txtProductName.clear();
            txtQty.clear();
            txtPrice.clear();
            txtTax.setText("0.00");
            txtDiscount.setText("0.00");
            txtComment.clear();
            lbInvoiceStatus.setText("");
            lbProductsTotal.setText("");
            lbTax.setText("");
            lbDiscount.setText("");
            lbInvoiceTotal.setText("");
            btnPrintInvoice.setDisable(true);
            cbCustomerName.getSelectionModel().selectFirst();
            
        } catch(Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void printSalesInvoice(long headerId) {
        try {
            HashMap customersMap = getCustomerHashMap();
            
            SalesInvoiceHeader header = headerRepo.findById(headerId);
            
            
            int customerId = header.getCustomerId();
            Customer customer = customerRepo.findById(customerId);
            String date = header.getDate();
            String comment = header.getComment();
            double total = header.getTotal();
            double tax = header.getTax();
            double discount = header.getDiscount();
            
            String reportName = Constants.REPORTS_PATH +"salesInvoice.jasper";
            

            HashMap map = new HashMap();

            map.put("id", headerId);
            map.put("date", date);
            map.put("customerName", customer.getName());
            map.put("total", numberFormater.format(total));
            map.put("tax", numberFormater.format(tax));
            map.put("comment", comment);
            map.put("discount", numberFormater.format(discount));
            
            ArrayList<SalesInvoiceDetails> list = detailsRepo.findByHeaderID(headerId);            
            
            InputStream report;
            report = getClass().getResourceAsStream(reportName);
            
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);
            

            JasperPrint jPrint = JasperFillManager.fillReport(report, map, ds);
            
            JasperViewer.viewReport(jPrint, false);
            
            lbInvoiceStatus.setText("Invoice: " + headerId + " printed");
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }
    

}
    

