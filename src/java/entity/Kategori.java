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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author celal
 */
@Entity
@Table(name = "kategori")
@NamedQueries({
    @NamedQuery(name = "Kategori.findAll", query = "SELECT k FROM Kategori k")})
public class Kategori implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kategoriid")
    private Integer kategoriid;
    @Size(max = 45)
    @Column(name = "kategori")
    private String kategori;
    @OneToMany(mappedBy = "kategoriid")
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
        return "entity.Kategori[ kategoriid=" + kategoriid + " ]";
    }
    
}
