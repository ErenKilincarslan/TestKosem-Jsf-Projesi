/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author celal
 */
public class Kategori implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer kategoriid;
    private String kategori;
    private Collection<Test> testCollection;

    public Kategori() {
    }

    public Kategori(Integer kategoriid) {
        this.kategoriid = kategoriid;
    }

    public Integer getKategoriid() {
        return kategoriid;
    }

    public void setKategoriid(Integer kategoriid) {
        this.kategoriid = kategoriid;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    @XmlTransient
    public Collection<Test> getTestCollection() {
        return testCollection;
    }

    public void setTestCollection(Collection<Test> testCollection) {
        this.testCollection = testCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kategoriid != null ? kategoriid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategori)) {
            return false;
        }
        Kategori other = (Kategori) object;
        if ((this.kategoriid == null && other.kategoriid != null) || (this.kategoriid != null && !this.kategoriid.equals(other.kategoriid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getKategori();
    }
    
}
