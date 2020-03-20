package domain.Repositories;

import domain.Models.Cursa;
import domain.Models.DTOBJPartCapa;
import domain.Models.Echipa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.image.CropImageFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class EchipaRepo implements EchipaRepository {
    private JDBC utils;

    private static final Logger logger = LogManager.getLogger(EchipaRepo.class);


    public EchipaRepo(Properties props){
        logger.info("Initializing PersoaneREPO with properties: {} ",props);
        utils=new JDBC(props);
    }

    @Override
    public int size() {
        logger.traceEntry();

        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Echipa")) {
            try(ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    logger.traceExit(result.getInt("SIZE"));
                    return result.getInt("SIZE");
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        return 0;
    }

    @Override
    public void save(Echipa entity) {
        logger.traceEntry("se salveaza echipa {} ",entity);

        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Echipa values (?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setString(2,entity.getNume());
            int result=preStmt.executeUpdate();

        }catch(SQLException ex){
            logger.error(ex);
        }
        logger.info("s a salvat echipa {} "+entity);
        logger.traceExit();
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("se sterge echipa cu id-ul {}",integer);

        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Echipa where idEchipa=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }
        catch (SQLException ex){
            logger.error(ex);
        }

        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Echipa entity) {
        logger.traceEntry("se updateaza echipa cu id-ul {}",integer);

        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Echipa set idEchipa=?,name=? where idEchipa=?")){
            preStmt.setInt(1,entity.getId());
            preStmt.setString(2,entity.getNume());
            preStmt.setInt(3,integer);
        }catch (SQLException ex){
            logger.error(ex);
        }
        logger.traceExit();
    }

    @Override
    public Echipa findOne(Integer integer) {
        logger.traceEntry("se cauta echipa cu id-ul {}",integer);

        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Echipa where idEchipa=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()){
                if (result.next()){
                    Integer id;
                    id=result.getInt("idEchipa") ;
                    String name;
                    name=result.getString("nume");
                    Echipa E=new Echipa(id,name);
                    logger.traceExit(E);
                    return E;
                }
            }

        }catch (SQLException ex){
            logger.error(ex);
        }

        return null;
    }

    @Override
    public Iterable<Echipa> findAll() {
        return null;
    }

    @Override
    public Vector<DTOBJPartCapa> cautare(String numeEchipa) {
        Vector<DTOBJPartCapa> obiecte=new Vector<>();
        logger.traceEntry("Se cauta numele participantilor din echipa {}",numeEchipa);

        Connection con=utils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select P.nume,C.capacitate from Cursa C INNER JOIN Inscriere I on C.idCursa = I.idCursa INNER JOIN Participant P on I.idParticipant = P.idParticipant INNER JOIN Echipa E on P.idEchipa = E.idEchipa WHERE E.nume=?")){
            preStmt.setString(1,numeEchipa);
            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()){
                String nume=result.getString("nume");
                int capacitate=result.getInt("capacitate");
                DTOBJPartCapa obj=new DTOBJPartCapa(nume,capacitate);
                obiecte.add(obj);
                }
            }

        }catch (SQLException ex){
            logger.error(ex);
        }
        return obiecte;
    }

    @Override
    public int FindidByName(String numeEchipa) {
        int id;
        logger.traceEntry("Se cauta id-ul echipei cu numele {}",numeEchipa);

        Connection con=utils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select idEchipa from Echipa where nume=?")){
            preStmt.setString(1,numeEchipa);
            try(ResultSet result=preStmt.executeQuery()){
                if(result.next()) {
                    id = result.getInt("idEchipa");
                    return id;
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
        }
        return 0;
    }
}
