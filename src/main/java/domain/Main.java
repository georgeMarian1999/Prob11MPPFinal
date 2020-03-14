package domain;

import domain.Models.Angajat;
import domain.Models.Cursa;
import domain.Models.Participant;
import domain.Models.Echipa;
import domain.Repositories.AngajatRepo;
import domain.Repositories.CursaRepo;
import domain.Repositories.EchipaRepo;
import domain.Repositories.ParticipantRepo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
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
        public static void main(String[] args) {
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

            System.out.println("AngajatRepo size is "+A.size());
            System.out.println("CursaRepo size is "+C.size());
            System.out.println("EchipaRepo size is "+E.size());
            System.out.println("ParticipantRepo size is "+P.size());
            System.out.println(A.findOne(1).getUsername());
            System.out.println(C.findOne(3).getCapacitate());
            System.out.println(E.findOne(25).getNume());
            System.out.println(P.findOne(3).getNume());
            Angajat Ang=new Angajat(200,"test","test2");
            A.save(Ang);
            System.out.println(A.findOne(200).getUsername());
            Participant part=new Participant(800,"John",25);
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

        }
    }
