package domain;

import domain.Controller.LoginController;
import domain.Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginView.fxml"));

        Scene LoginScene = new Scene(root);
        stage.setScene(LoginScene);
        stage.setTitle("Login");
        stage.show();
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    static Service GetService(){
        ApplicationContext context=new ClassPathXmlApplicationContext("BeanXML.xml");
        Service service =context.getBean(Service.class);
        return service;
    }



}