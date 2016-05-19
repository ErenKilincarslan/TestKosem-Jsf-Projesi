/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.Kategori;
import entity.Test;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author celal
 */
@SessionScoped
public class TestJpaController {

    public void addTest(Test t) {
        Baglanti b = new Baglanti();
        try {
            b.baglan();
            PreparedStatement ps = b.conn.prepareStatement(
                    "insert into test (kategoriid) values (?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, t.getKategoriid().getKategoriid());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                t.setTestid(rs.getInt(1));
            }
        } catch (SQLException ex) {
            throw new Error(ex.getMessage());

        } finally {
            b.baglantiKes();
        }
    }

    public List<Test> getAllTestFromKategori(Kategori kategori) {
        Baglanti b = new Baglanti();
        List<Test> list = new ArrayList<>();
        try {
            b.baglan();
            PreparedStatement ps = b.conn.prepareStatement("select * from test where kategoriid=?  ");
            ps.setInt(1, kategori.getKategoriid());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Test t = new Test();
                t.setKategoriid(kategori);
                t.setTestid(rs.getInt(1));
                list.add(t);
            }
        } catch (SQLException ex) {
            throw new Error(ex.getMessage());
        } finally {
            b.baglantiKes();
        }
        return list;
    }

    public Kategori getKategoriFromTestID(int testid) {
        Baglanti b = new Baglanti();
        Kategori k;
        try {
            b.baglan();
            PreparedStatement ps = b.conn.prepareStatement("select * from kategori where kategoriid=(Select kategoriid from test where testid=?)");
            ps.setInt(1, testid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            k = new Kategori();
            k.setKategoriid(rs.getInt(1));
            k.setKategori(rs.getString(2));

        } catch (SQLException ex) {
            throw new Error(ex.getMessage());
        } finally {
            b.baglantiKes();
        }
        return k;
    }

}
