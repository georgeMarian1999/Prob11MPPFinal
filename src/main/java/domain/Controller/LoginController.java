package domain.Controller;

import domain.Service.Service;
import javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoginController {
    private Service service;
    Stage stage;

    @FXML
    TextField TFUser;
    @FXML
    TextField TFPass;
    @FXML
    Button LoginButton;

    public void setService(Service service1,Stage stage1){
        this.service=service1;
        this.stage=stage1;
    }
    public void handleLogin(){

    }

    static Service getService(){
        ApplicationContext context=new ClassPathXmlApplicationContext("BeanXML");
        Service service=context.getBean(Service.class);
        return service;
    }
}
