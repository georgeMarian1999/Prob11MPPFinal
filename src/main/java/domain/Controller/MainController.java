package domain.Controller;

import domain.Models.DTOBJCursa;
import domain.Models.DTOBJPartCapa;
import domain.Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MainController implements Initializable {
    private Service service;

    ObservableList<DTOBJCursa> Curse= FXCollections.observableArrayList();
    ObservableList<DTOBJPartCapa> Participanti=FXCollections.observableArrayList();

    @FXML
    Button LogoutButton;
    @FXML
    TableView<DTOBJCursa> TabelCurse;
    @FXML
    TableColumn<DTOBJCursa,Integer> IdCol;
    @FXML
    TableColumn<DTOBJCursa,Integer> CapCol;
    @FXML
    TableColumn<DTOBJCursa,Integer> NrCol;
    @FXML
    Button clearsearch;
    @FXML
    TextField TFSearch;
    @FXML
    Button SearchByTeam;
    @FXML
    TableView<DTOBJPartCapa> TabelPart;
    @FXML
    TableColumn<DTOBJPartCapa,Integer> IDPart;
    @FXML
    TableColumn<DTOBJPartCapa,Integer> NumePart;
    @FXML
    TableColumn<DTOBJPartCapa,Integer> CapacitatePart;
    @FXML
    Button InscButton;
    @FXML
    ComboBox<Integer> CapBox;
    @FXML
    TextField TFNumePart;
    @FXML
    TextField TFNumeEchipa;

    @FXML
    public void handleclear(){
        TFSearch.setText("");
        TFNumePart.setText("");
        TFNumeEchipa.setText("");
    }
    @FXML
    public void handlesearch(){
        Iterable<DTOBJPartCapa> participanti=service.cautare(TFSearch.getText());
        List<DTOBJPartCapa> listapart=StreamSupport.stream(participanti.spliterator(),false)
                .collect(Collectors.toList());
        Participanti.setAll(listapart);
    }
    @FXML
    public void handleInscriere(){

            String NumePart=TFNumePart.getText();
            String NumeEchipa=TFNumeEchipa.getText();
            int capacitate=Integer.valueOf(CapBox.getValue());
            service.InscriereParticipant(capacitate,NumePart,NumeEchipa);

            Curse.setAll((List<DTOBJCursa>)StreamSupport.stream(service.GroupByCapacitate().spliterator(), false).collect(Collectors.toList()));

            Alert message = new Alert(Alert.AlertType.CONFIRMATION);
            message.setContentText("Participant inscris cu succes!");
            message.showAndWait();


    }

    static Service getService(){
        ApplicationContext context=new ClassPathXmlApplicationContext("BeanXML.xml");
        Service service=context.getBean(Service.class);
        return service;
    }
    @FXML
    public void handleLogout(){

        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginView.fxml"));

            Scene LoginScene = new Scene(root);
            Stage LoginStage=new Stage();
            LoginStage.setScene(LoginScene);
            LoginStage.setTitle("Login");
            LoginStage.show();
            Stage current=(Stage)LogoutButton.getScene().getWindow();
            current.close();
        }catch (IOException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            this.service=getService();
            IdCol.setCellValueFactory(new PropertyValueFactory<DTOBJCursa,Integer>("id"));
            CapCol.setCellValueFactory(new PropertyValueFactory<DTOBJCursa,Integer>("capacitate"));
            NrCol.setCellValueFactory(new PropertyValueFactory<DTOBJCursa,Integer>("Nrinscrisi"));
            TabelCurse.setItems(Curse);

            IDPart.setCellValueFactory(new PropertyValueFactory<DTOBJPartCapa,Integer>("id"));
            NumePart.setCellValueFactory(new PropertyValueFactory<DTOBJPartCapa,Integer>("Nume"));
            CapacitatePart.setCellValueFactory(new PropertyValueFactory<DTOBJPartCapa,Integer>("capactiate"));
            TabelPart.setItems(Participanti);

            Iterable<DTOBJCursa> cursedto=service.GroupByCapacitate();
            List<DTOBJCursa> listcurse= StreamSupport.stream(cursedto.spliterator(),false)
                    .collect(Collectors.toList());
            Curse.setAll(listcurse);

            ObservableList<Integer> tipuriCurse=FXCollections.observableArrayList();
            for(DTOBJCursa c:service.GroupByCapacitate()){
                tipuriCurse.add(c.getCapacitate());
            }
            CapBox.setItems(tipuriCurse);
    }
}
