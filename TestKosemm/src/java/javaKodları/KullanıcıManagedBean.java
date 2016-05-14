package javaKodları;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

public class KullanıcıManagedBean extends Baglanti implements Serializable {

    private Kullanıcı user;
    private List<Kullanıcı> users;

    public List<Kullanıcı> getUsers() {
        return users;
    }

    public void setUsers(List<Kullanıcı> users) {
        this.users = users;
    }

    public KullanıcıManagedBean() {

        user = new Kullanıcı();
        user.setKullaniciid(0);
        user.setAdi("");
        user.setEmail("");
        user.setKullaniciAdi("");
        user.setSifre("");
        user.setSoyadi("");
        user.setYetki(0);

    }

    @PostConstruct
    public void init() {
        users = kullaniciListele();
    }

    public String giris() {
        baglan();

        boolean userVarMi = false;
        int userYetki = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("Select * from kullanici where kullaniciadi=? and sifre=?");
            ps.setString(1, user.getKullaniciAdi());
            ps.setString(2, user.getSifre());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (user.getKullaniciAdi().equals(rs.getString("kullaniciadi"))
                        && user.getSifre().equals(rs.getString("sifre"))) {
                    userVarMi = true;
                    userYetki = rs.getInt("yetki");
                }
            }
        } catch (Exception e) {
            return null;
        }
        if (userVarMi) {
            if (userYetki == 1) {
                return "admin/adminAnasayfa.xhtml?faces-redirect=true";
            } else {
                return "kullaniciAnasayfa.xhtml?faces-redirect=true";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Yanlış Veri Girdiniz"));
            return "giris.xhtml";
        }
    }

    public String kayitEkle() {
        baglan();

        try {
            PreparedStatement ps = conn.prepareStatement("insert into kullanici (adi,soyadi,email,kullaniciadi,sifre,yetki) values (?,?,?,?,?,?)");
            ps.setString(1, user.getAdi());
            ps.setString(2, user.getSoyadi());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getKullaniciAdi());
            ps.setString(5, user.getSifre());
            ps.setInt(6, 2);
            ps.executeUpdate();
            setUsers(kullaniciListele());

        } catch (Exception e) {
            return null;
        }
        return "kayitTamamlandi.xhtml";
    }

    public String kayitSil(int gelenId) {
        baglan();

        try {
            PreparedStatement ps = conn.prepareStatement("delete from kullanici where id=?");
            ps.setInt(1, gelenId);

            ps.executeUpdate();

        } catch (Exception e) {

        }

        return "adminKullaniciSil.xhtml?faces-redirect=true";
    }

    private List<Kullanıcı> kullaniciListele() {
        List<Kullanıcı> liste = new ArrayList<>();
        baglan();

        try {
            PreparedStatement ps = conn.prepareStatement("select * from kullanici");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Kullanıcı yeniKullanici = new Kullanıcı(rs.getInt("id"), rs.getString("adi"), rs.getString("soyadi"),
                        rs.getString("email"), rs.getString("kullaniciadi"), rs.getString("sifre"), rs.getInt("yetki"));

                liste.add(yeniKullanici);
            }

        } catch (Exception e) {
        }
        return liste;
    }

    public String deleteAction(Kullanıcı k) {
        FacesMessage ms2 = new FacesMessage("Kullanıcı başarıyla silindi", k.getKullaniciAdi() + "");
        FacesContext.getCurrentInstance().addMessage(null, ms2);
        try {
            users.remove(k);
            baglan();
            PreparedStatement ps = conn.prepareCall("delete from kullanici where id=?");
            ps.setInt(1, k.getKullaniciid());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(KullanıcıManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void onRowEdit(RowEditEvent event) {
        baglan();
        Kullanıcı kul = (Kullanıcı) event.getObject();
        FacesMessage ms2 = new FacesMessage("Kullanıcı başarıyla güncellendi", ((Kullanıcı) event.getObject()).getKullaniciAdi() + "");
        FacesContext.getCurrentInstance().addMessage(null, ms2);

        System.out.println("güncellendi");
        try {
            PreparedStatement ps = conn.prepareStatement("update kullanici set adi=?,soyadi=?,email=?,kullaniciadi=?,sifre=?,yetki=? where id=?");
            ps.setString(1, kul.getAdi());
            ps.setString(2, kul.getSoyadi());
            ps.setString(3, kul.getEmail());
            ps.setString(4, kul.getKullaniciAdi());
            ps.setString(5, kul.getSifre());
            ps.setInt(6, kul.getYetki());
            ps.setInt(7, kul.getKullaniciid());
            ps.executeUpdate();
            FacesContext context = FacesContext.getCurrentInstance();

        } catch (Exception e) {
        }
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Kullanıcı getUser() {
        return user;
    }

    public void setUser(Kullanıcı user) {
        this.user = user;
    }

}
