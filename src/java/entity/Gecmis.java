/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author celal
 */
public class Gecmis implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer gecmisid;
    private String vercevap;
    private Test testid;
    private Kullanici kullaniciid;

    public Gecmis() {
    }

    public Gecmis(Integer gecmisid) {
        this.gecmisid = gecmisid;
    }

    public Integer getGecmisid() {
        return gecmisid;
    }

    public void setGecmisid(Integer gecmisid) {
        this.gecmisid = gecmisid;
    }

    public String getVercevap() {
        return vercevap;
    }

    public void setVercevap(String vercevap) {
        this.vercevap = vercevap;
    }

    public Test getTestid() {
        return testid;
    }

    public void setTestid(Test testid) {
        this.testid = testid;
    }

    public Kullanici getKullaniciid() {
        return kullaniciid;
    }

    public void setKullaniciid(Kullanici kullaniciid) {
        this.kullaniciid = kullaniciid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gecmisid != null ? gecmisid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gecmis)) {
            return false;
        }
        Gecmis other = (Gecmis) object;
        if ((this.gecmisid == null && other.gecmisid != null) || (this.gecmisid != null && !this.gecmisid.equals(other.gecmisid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Gecmis[ gecmisid=" + gecmisid + " ]";
    }
    
}
