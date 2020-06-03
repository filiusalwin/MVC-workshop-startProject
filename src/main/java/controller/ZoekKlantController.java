package controller;

import javadb.CustomerDAO;
import javadb.DBaccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Customer;
import view.ApplicationLauncher;

public class ZoekKlantController {
    private CustomerDAO cdao;
    private DBaccess db;

    @FXML
    private TextField klantnummerTextfield;

    @FXML
    private TextField voorlettersTextfield;

    @FXML
    private TextField tussenvoegselTextfield;

    @FXML
    private TextField achternaamTextfield;

    @FXML
    private TextField mobielTextfield;

    public ZoekKlantController() {
        super();
        this.db = ApplicationLauncher.getDBaccess();;
        this.cdao = new CustomerDAO(db.getConnection());
    }

    @FXML
    public void doSearch(ActionEvent event) {
        int id = Integer.parseInt(klantnummerTextfield.getText());

        Customer customer = cdao.getCustomerById(id);
        db.closeConnection();
        ApplicationLauncher.getSceneManager().showExistingCustomerScene(customer);

        //achternaamTextfield.setText(customer.getSurName());
    }

    public void doBackToMenu(ActionEvent actionEvent) {
        ApplicationLauncher.getSceneManager().showWelcomeScene();

    }




}
