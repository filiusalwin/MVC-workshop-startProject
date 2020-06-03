package view;

import controller.KlantenLijstController;
import controller.NieuweKlantController;
import controller.WelcomeController;
import controller.WijzigKlantController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;

public class SceneManager {

    private Stage primaryStage;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showWelcomeScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/welcomeScene.fxml"));
            Parent root = loader.load();
            WelcomeController controller = loader.getController();
            controller.setup();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNewCustomerScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NieuweKlant.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showExistingCustomerScene(Customer customer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WijzigKlant.fxml"));
            Parent root = loader.load();
            WijzigKlantController controller = loader.getController();
            controller.setup(customer);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCustomerListScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/KlantenLijst.fxml"));
            Parent root = loader.load();
            KlantenLijstController controller = loader.getController();
            controller.setup();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
