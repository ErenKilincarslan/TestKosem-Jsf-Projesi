/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.Kategori;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javaKodları.Baglanti;

/**
 *
 * @author celal
 */
public class KategoriJpaController implements Serializable {

    public KategoriJpaController() {
    }

    private void create(Kategori kategori) {
        Baglanti b = new Baglanti();
        try {
            b.baglan();
            PreparedStatement ps = b.conn.prepareStatement(
                    "insert into kategori (kategori) values (?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, kategori.getKategori());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                kategori.setKategoriid(rs.getInt(1));
            }
        } catch (SQLException ex) {
            throw new Error(ex.getMessage());

        } finally {
            b.baglantiKes();
        }
    }
    public  void getKategoriID(Kategori kategori){
        Baglanti b = new Baglanti();
        try {
            b.baglan();
            PreparedStatement ps = b.conn.prepareStatement(
                    "select * from kategori where kategori=?",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, kategori.getKategori());
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kategori.setKategoriid(rs.getInt(1));
            }else{
                create(kategori);
            }
        } catch (SQLException ex) {
            throw new Error(ex.getMessage());

        } finally {
            b.baglantiKes();
        }
    }
    public List<Kategori> getAllKategori() {
        Baglanti b = new Baglanti();
        List<Kategori> list = new ArrayList<>();
        try {
            b.baglan();
            PreparedStatement ps = b.conn.prepareStatement("select * from kategori  ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Kategori k = new Kategori();
                k.setKategoriid(rs.getInt(1));
                k.setKategori(rs.getString(2));
                list.add(k);
            }
        } catch (SQLException ex) {
            throw new Error(ex.getMessage());
        } finally {
            b.baglantiKes();
        }
        return list;
    }

}
