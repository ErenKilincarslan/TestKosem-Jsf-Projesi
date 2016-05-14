/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Soru;
import entity.Cevap;
import entity.Dogrucevap;
import entity.controller.exceptions.NonexistentEntityException;
import entity.controller.exceptions.RollbackFailureException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javaKodları.Baglanti;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author celal
 */
public class DogrucevapJpaController implements Serializable {

    public void create(Dogrucevap dogrucevap) {
         Baglanti b=new Baglanti();
        try {
            b.baglan();
            PreparedStatement ps=b.conn.prepareStatement(
                    "insert into dogrucevap (soruid,cevapid) values (?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, dogrucevap.getSoruid().getSoruid());
            ps.setInt(2, dogrucevap.getCevapid().getCevapid());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next())
                dogrucevap.setDogrucevapid(rs.getInt(1));
        } catch (SQLException ex) {
            throw new Error(ex.getMessage());
        }
        finally{
            b.baglantiKes();
        }
    }
}
