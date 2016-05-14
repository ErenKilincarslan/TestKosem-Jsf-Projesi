/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

public class Cevap implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer cevapid;
    private String cevap;
    private Soru soruid;
    public Cevap() {
    }
    public Cevap(Integer cevapid) {
        this.cevapid = cevapid;
    }

    public Cevap(String cevap, Soru soruid) {
        this.cevap = cevap;
        this.soruid = soruid;
    }
    
    public Integer getCevapid() {
        return cevapid;
    }
    public void setCevapid(Integer cevapid) {
        this.cevapid = cevapid;
    }
    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
    }

    public Soru getSoruid() {
        return soruid;
    }

    public void setSoruid(Soru soruid) {
        this.soruid = soruid;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cevapid != null ? cevapid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cevap)) {
            return false;
        }
        Cevap other = (Cevap) object;
        if ((this.cevapid == null && other.cevapid != null) || (this.cevapid != null && !this.cevapid.equals(other.cevapid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getCevap();
    }
    
}
