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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

@SessionScoped
@ManagedBean
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

    public Kullanıcı getUserFromSession(HttpSession session) {
        return (Kullanıcı) session.getAttribute("user");
    }

    public String giris(HttpSession session) {
        baglan();

        boolean userVarMi = false;
        int userYetki = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("Select * from kullanici where kullaniciadi=? and sifre=?");
            ps.setString(1, user.getKullaniciAdi());
            ps.setString(2, user.getSifre());
            ResultSet rs = ps.executeQuery();
            Kullanıcı girisKullanıcı;
            while (rs.next()) {
                if (user.getKullaniciAdi().equals(rs.getString("kullaniciadi"))
                        && user.getSifre().equals(rs.getString("sifre"))) {
                    userVarMi = true;
                    girisKullanıcı = new Kullanıcı(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getInt(7));
                    session.setAttribute("user", girisKullanıcı);

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

    public String kayitSil(Kullanıcı k) {
        FacesMessage ms2 = new FacesMessage("Kullanıcı başarıyla silindi", k.getKullaniciAdi() + "");
        FacesContext.getCurrentInstance().addMessage(null, ms2);
        try {
            baglan();
            PreparedStatement ps = conn.prepareCall("delete from kullanici where id=?");
            ps.setInt(1, k.getKullaniciid());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(KullanıcıManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            users = kullaniciListele();
        }
        return null;
    }

    public void guncelle(RowEditEvent event) {
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

    public Kullanıcı getUser() {
        return user;
    }

    public void setUser(Kullanıcı user) {
        this.user = user;
    }

}
