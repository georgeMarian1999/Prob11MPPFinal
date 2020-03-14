package domain.Repositories;

import domain.Models.Cursa;
import domain.Models.Participant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CursaRepo implements CursaRepository {
    private JDBC utils;

    private static final Logger logger = LogManager.getLogger(CursaRepo.class);

    public CursaRepo(Properties props){
        logger.info("Initializing PersoaneREPO with properties: {} ",props);
        utils=new JDBC(props);
    }

    @Override
    public Iterable<Cursa> filterByCapacitate(Integer capacitate) {
        return null;
    }

    @Override
    public int size() {
        logger.traceEntry();

        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Cursa")) {
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
    public void save(Cursa entity) {
        logger.traceEntry("se salveaza Cursa  {} ",entity);

        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Cursa values (?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setInt(2,entity.getCapacitate());
            int result=preStmt.executeUpdate();

        }catch(SQLException ex){
            logger.error(ex);
        }
        logger.info("s a salvat Cursa {} "+entity);
        logger.traceExit();
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("se sterge cursa cu id-ul {}",integer);

        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Cursa where idCursa=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }
        catch (SQLException ex){
            logger.error(ex);
        }

        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Cursa entity) {
        logger.traceEntry("se updateaza cursa cu id-ul {}",integer);

        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Cursa set idCursa=?,capacitate=? where idCursa=?")){
            preStmt.setInt(1,entity.getId());
            preStmt.setInt(2,entity.getCapacitate());
            preStmt.setInt(3,integer);
        }catch (SQLException ex){
            logger.error(ex);
        }
        logger.traceExit();
    }

    @Override
    public Cursa findOne(Integer integer) {
        logger.traceEntry("se cauta cursa cu id-ul {}",integer);

        Connection con=utils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Cursa where idCursa=?")){
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()){
                if (result.next()){
                    Integer id,capac;
                    id=result.getInt("idCursa") ;
                    capac=result.getInt("capacitate");
                    Cursa C=new Cursa(id,capac);
                    logger.traceExit(C);
                    return C;
                }
            }

        }catch (SQLException ex){
            logger.error(ex);
        }

        return null;
    }

    @Override
    public Iterable<Cursa> findAll() {
        Connection con=utils.getConnection();
        List<Cursa> Curse=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Cursa")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("idCursa");
                    Integer capac;
                    capac=result.getInt("capacitate");
                    Cursa P=new Cursa(id,capac);
                    Curse.add(P);
                }
            }
        } catch (SQLException e) {
            logger.error(e);

            System.out.println("Error DB "+e);
        }
        logger.traceExit(Curse);

        return Curse;
    }
}
