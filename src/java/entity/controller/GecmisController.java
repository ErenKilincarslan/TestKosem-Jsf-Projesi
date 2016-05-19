/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.Gecmis;
import entity.Kategori;
import entity.Soru;
import entity.Test;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author celal
 */
public class GecmisController {

    public void gecmisEkle(List<Soru> list, int kullaniciid, String test1) {
        Baglanti b = new Baglanti();
        PreparedStatement ps;
        FacesMessage message = new FacesMessage("Succesful", test1 + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        try {
            b.baglan();
            String sql = "insert into gecmis (testid,kullaniciid,testgecmisi,tarih) values(?,?,?,?)";
            ps = b.conn.prepareStatement(sql);
            ps.setInt(2, kullaniciid);
            ps.setString(1, test1);
            String verilenCevap = "";
            for (Soru soru : list) {
                String cevap;
                if (soru.getSecilenCevap() != null && !soru.getSecilenCevap().equals("")) {

                    verilenCevap += getCevap(soru);
                } else {
                    verilenCevap += "-";
                }
            }
            ps.setString(3, verilenCevap);
            Date date = new Date(System.currentTimeMillis());
            ps.setDate(4, date);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Error(e.getMessage());
        }

    }

    private String getCevap(Soru soru) {
        String cevap;
        if (soru.getCevap1().equals(soru.getSecilenCevap())) {
            cevap = "1";
        } else if (soru.getCevap2().equals(soru.getSecilenCevap())) {
            cevap = "2";
        } else if (soru.getCevap3().equals(soru.getSecilenCevap())) {
            cevap = "3";
        } else {
            cevap = "4";
        }
        return cevap;
    }

    public List<Gecmis> getAllTestFromKullaniciId(int kullaniciid) {
        Baglanti b = new Baglanti();
        List<Gecmis> list = new ArrayList<>();
        try {
            b.baglan();
            PreparedStatement ps = b.conn.prepareStatement("select * from gecmis where kullaniciid=?");
            ps.setInt(1, kullaniciid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Gecmis g = new Gecmis();
                g.setGid(rs.getInt(1));
                g.setTestID(rs.getInt(2));
                g.setTestgecmisi(rs.getString(4));
                g.setTarih(rs.getDate(5));
                TestJpaController tjc = new TestJpaController();
                g.setKategori(tjc.getKategoriFromTestID(g.getTestID()));
                list.add(g);
            }
        } catch (SQLException ex) {
            throw new Error(ex.getMessage());
        } finally {
            b.baglantiKes();
        }
        return list;
    }
}
