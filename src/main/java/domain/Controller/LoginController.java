package domain.Controller;

import domain.Repositories.AngajatRepo;
import domain.Service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Service service;
    Stage stage;

    private static final Logger logger = LogManager.getLogger(LoginController.class);


    @FXML
    TextField TFUser;
    @FXML
    TextField PFPass;
    @FXML
    Button LoginButton;


    @FXML
    public void handleLogin(){
        logger.traceEntry("Se efectueaza LocalLogin()");
        String user=TFUser.getText();
        String pass=PFPass.getText();

        if(this.service.LocalLogin(user,pass)){
            //stage.close();
            try{
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainView.fxml"));

                Scene MainScene = new Scene(root);

                Stage MainStage=new Stage();
                MainStage.setTitle("Curse");
                MainStage.setScene(MainScene);
                MainStage.show();
                Stage current=(Stage)LoginButton.getScene().getWindow();
                current.close();
                //stage.setScene(MainScene);
                //stage.setTitle("Main");
                //stage.show();
            }catch (IOException ex){
                logger.error(ex);
            }
        }
        else{
            Alert msg=new Alert(Alert.AlertType.ERROR);
            msg.setHeaderText("Login Failed!!!");
            msg.setContentText("Wrong username or password!!!");
            msg.showAndWait();
        }
    }
    public void SetService(Service service1,Stage stage1){
        this.service=service1;
        this.stage=stage1;
    }
    static Service getService(){
        ApplicationContext context=new ClassPathXmlApplicationContext("BeanXML.xml");
        Service service=context.getBean(Service.class);
        return service;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            this.service=getService();

    }
}
