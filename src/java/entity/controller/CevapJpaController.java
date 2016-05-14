/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.Cevap;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Soru;
import entity.Dogrucevap;
import entity.Kategori;
import entity.Test;
import entity.controller.exceptions.NonexistentEntityException;
import entity.controller.exceptions.RollbackFailureException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javaKodları.Baglanti;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author celal
 */
public class CevapJpaController implements Serializable {


    public void create(Cevap cevap) {
        Baglanti b=new Baglanti();
        try {
            b.baglan();
            PreparedStatement ps=b.conn.prepareStatement(
                    "insert into cevap (soruid,cevap) values (?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cevap.getSoruid().getSoruid());
            ps.setString(2, cevap.getCevap());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next())
                cevap.setCevapid(rs.getInt(1));
        } catch (SQLException ex) {
            throw new Error(ex.getMessage());
        }
        finally{
            b.baglantiKes();
        }
    }
 public List<Cevap> getAllCevapFromSoruID(Soru soru){
        Baglanti b = new Baglanti();
        List<Cevap> list=new ArrayList<>();
        try {
            b.baglan();
            PreparedStatement ps = b.conn.prepareStatement("select * from cevap where soruid=?  ");
            ps.setInt(1, soru.getSoruid());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cevap c=new Cevap();
                c.setCevapid(rs.getInt(1));
                c.setSoruid(soru);
                c.setCevap(rs.getString(3));
                list.add(c);
            }
        } catch (SQLException ex){
            throw new Error(ex.getMessage());
        } finally {
            b.baglantiKes();
        }
        return list;
    }

    
}
