package domain;

import domain.Controller.LoginController;
import domain.Models.*;
import domain.Repositories.*;
import domain.Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main extends Application {
    static AngajatRepo getAngajatRepo(){
        ApplicationContext context = new ClassPathXmlApplicationContext("BeanXML.xml");
        AngajatRepo pers = context.getBean(AngajatRepo.class);
        return pers;
    }
    static ParticipantRepo getParticipantRepo(){
        ApplicationContext context = new ClassPathXmlApplicationContext("BeanXML.xml");
        ParticipantRepo part = context.getBean(ParticipantRepo.class);
        return part;
    }
    static CursaRepo getCursaRepo(){
        ApplicationContext context = new ClassPathXmlApplicationContext("BeanXML.xml");
        CursaRepo curs = context.getBean(CursaRepo.class);
        return curs;
    }
    static EchipaRepo getEchipaRepo(){
        ApplicationContext context = new ClassPathXmlApplicationContext("BeanXML.xml");
        EchipaRepo e = context.getBean(EchipaRepo.class);
        return e;
    }
    static InscriereRepo getInscriereRepo(){
        ApplicationContext context=new ClassPathXmlApplicationContext("BeanXML.xml");
        InscriereRepo insc= context.getBean(InscriereRepo.class);
        return insc;
    }
    static Service getService(){
        ApplicationContext context=new ClassPathXmlApplicationContext("BeanXML.xml");
        Service service=context.getBean(Service.class);
        return service;
    }
    public void ConsoleRun(){
        Properties props = new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AngajatRepo A=getAngajatRepo();
        CursaRepo C=getCursaRepo();
        EchipaRepo E=getEchipaRepo();
        ParticipantRepo P=getParticipantRepo();
        InscriereRepo I=getInscriereRepo();

        System.out.println("AngajatRepo size is "+A.size());
        System.out.println("CursaRepo size is "+C.size());
        System.out.println("EchipaRepo size is "+E.size());
        System.out.println("ParticipantRepo size is "+P.size());
        System.out.println("InscriereRepo size is "+I.size());
        System.out.println(A.findOne(1).getUsername());
        System.out.println(C.findOne(3).getCapacitate());
        System.out.println(E.findOne(1).getNume());
        System.out.println(P.findOne(3).getNume());
        Angajat Ang=new Angajat(200,"test","test2");
        A.save(Ang);
        System.out.println(A.findOne(200).getUsername());
        Participant part=new Participant(800,"John",1);
        P.save(part);
        System.out.println(P.findOne(800).getNume());
        Echipa Ec=new Echipa(34,"Audi");
        E.save(Ec);
        System.out.println(E.findOne(34).getNume());
        Cursa cursa=new Cursa(111,400);
        C.save(cursa);
        System.out.println(C.findOne(111).getCapacitate());
        A.delete(200);
        P.delete(800);
        E.delete(34);
        C.delete(111);
        System.out.println(A.LocalLogin("mgar1992","12234"));
        System.out.println(A.LocalLogin("mgar1992","22"));
        System.out.println(A.LocalLogin("afsnajf","ajf3"));
        Vector<DTOBJCursa> curse=C.GroupByCapacitate();
        for(int i=0;i<curse.size();i++){
            System.out.print(curse.elementAt(i).getCapacitate());
            System.out.print(" ");
            System.out.println(curse.elementAt(i).getNrinscrisi());
        }
        Vector<DTOBJPartCapa> test=E.cautare("Suzuki");
        for(int i=0;i<test.size();i++){
            System.out.print(test.elementAt(i).getNume());
            System.out.print(" ");
            System.out.println(test.elementAt(i).getCapactiate());
        }
        System.out.println("Id ul echipei BMW este "+E.FindidByName("BMW"));
        System.out.println("Id ul maxim din Participant este "+P.findMaxId());

        Service S=getService();
        System.out.println(S.AngajatiSize());
        System.out.println(S.CurseSize());
        System.out.println(S.EchipeSize());
        System.out.println(S.ParticipantiSize());
        // S.InscriereParticipant(500,"Marcel Mihai","Suzuki");

    }
        public static void main(String[] args) {
            //this.ConsoleRun();
            launch(args);
        }

    @Override
    public void start(Stage primaryStage) throws Exception {
            try{
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("/View/LoginView.fxml"));
                AnchorPane root=loader.load();
                Stage LoginStage=new Stage();
                Scene loginScene = new Scene(root);
                LoginStage.setScene(loginScene);
                LoginStage.setTitle("Login");
                LoginStage.initModality(Modality.WINDOW_MODAL);
                LoginController ctrl=loader.getController();
                ctrl.setService(getService(),LoginStage);
                LoginStage.show();
            }catch (IOException ex){
                ex.printStackTrace();
            }

    }

}

