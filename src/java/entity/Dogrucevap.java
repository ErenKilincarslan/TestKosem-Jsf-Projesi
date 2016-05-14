/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author celal
 */
public class Dogrucevap implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer dogrucevapid;
    private Soru soru;
    private Cevap cevap;

    public Dogrucevap(Soru soru, Cevap cevap) {
        this.soru = soru;
        this.cevap = cevap;
    }

    public Dogrucevap() {
    }

    public Dogrucevap(Integer dogrucevapid) {
        this.dogrucevapid = dogrucevapid;
    }

    public Integer getDogrucevapid() {
        return dogrucevapid;
    }

    public void setDogrucevapid(Integer dogrucevapid) {
        this.dogrucevapid = dogrucevapid;
    }

    public Soru getSoruid() {
        return soru;
    }

    public void setSoruid(Soru soruid) {
        this.soru = soruid;
    }

    public Cevap getCevapid() {
        return cevap;
    }

    public void setCevapid(Cevap cevapid) {
        this.cevap = cevapid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dogrucevapid != null ? dogrucevapid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dogrucevap)) {
            return false;
        }
        Dogrucevap other = (Dogrucevap) object;
        if ((this.dogrucevapid == null && other.dogrucevapid != null) || (this.dogrucevapid != null && !this.dogrucevapid.equals(other.dogrucevapid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Dogrucevap[ dogrucevapid=" + dogrucevapid + " ]";
    }
    
}
