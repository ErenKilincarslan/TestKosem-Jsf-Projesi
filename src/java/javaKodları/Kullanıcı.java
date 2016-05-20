
package javaKodları;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "giris")
@NamedQueries({
    @NamedQuery(name = "giris.findAll", query = "SELECT user FROM giris user")})
public class Kullanıcı implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private int kullaniciid;
    @Column(name = "adi")
    private String adi;
    @Column(name = "soyadi")
    private String soyadi;
    @Column(name = "email")
    private String email;

    @Column(name = "kullaniciadi")
    private String kullaniciAdi;
    @Column(name = "sifre")
    private String sifre;
    @Column(name = "yetki")
    private Integer yetki;

    public Kullanıcı() {
    }

    
    public Kullanıcı(int kullaniciid, String adi, String soyadi, String email, String kullaniciAdi, String sifre, Integer yetki) {
        this.kullaniciid = kullaniciid;
        this.adi = adi;
        this.soyadi = soyadi;
        this.email = email;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.yetki = yetki;
    }
    
    public int getKullaniciid() {
        return kullaniciid;
    }

    public void setKullaniciid(int kullaniciid) {
        this.kullaniciid = kullaniciid;
    }
    
    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public Integer getYetki() {
        return yetki;
    }

    public void setYetki(Integer yetki) {
        this.yetki = yetki;
    }
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kullanıcı)) {
            return false;
        }
        Kullanıcı other = (Kullanıcı) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getAdi()+getEmail()+getKullaniciAdi()+getSifre()+getSoyadi();
    }
    
}
