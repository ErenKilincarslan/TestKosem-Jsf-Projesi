/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaKodları;

import entity.Soru;
import entity.controller.SoruJpaController;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author celal
 */
public class SorularManagedBean {

    private List<Soru> sorular;

    public List<Soru> getSorular() {
        return sorular;
    }

    public void setSorular(List<Soru> sorular) {
        this.sorular = sorular;
    }

    public SorularManagedBean() {
        SoruJpaController sjc = new SoruJpaController();
        sorular = sjc.getAllSoru();
    }

    public String kayitSil(Soru k) {
        FacesMessage ms2 = new FacesMessage("Kullanıcı başarıyla silindi", k.getSoruid() + "");
        FacesContext.getCurrentInstance().addMessage(null, ms2);
        Baglanti b = new Baglanti();
        try {
            b.baglan();
            PreparedStatement ps = b.conn.prepareCall("delete from soru where soruid=?");
            ps.setInt(1, k.getSoruid());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(KullanıcıManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            SoruJpaController sjc = new SoruJpaController();
            sorular = sjc.getAllSoru();
        }
        return null;
    }

    public void guncelle(RowEditEvent event) {
        FacesMessage ms2 = new FacesMessage("Kullanıcı başarıyla silindi", "Güncellendi");
        FacesContext.getCurrentInstance().addMessage(null, ms2);
        Baglanti b = new Baglanti();
        b.baglan();
        Soru kul = (Soru) event.getObject();
        System.out.println("güncellendi");
        try {
            PreparedStatement ps = b.conn.prepareStatement("update soru set soru=?,cevap1=?,cevap2=?,cevap3=?,cevap4=?,dogrucevap=? where soruid=?");
            
            ps.setString(1, kul.getSoru());
            ps.setString(2, kul.getCevap1());
            ps.setString(3, kul.getCevap2());
            ps.setString(4, kul.getCevap3());
            ps.setString(5, kul.getCevap4());
            ps.setInt(6, kul.getDogrucevap());
            ps.setInt(7, kul.getSoruid());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
