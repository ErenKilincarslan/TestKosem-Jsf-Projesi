/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

/**
 *
 * @author celal
 */
@Entity
@Table(name = "test")
@NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t")})
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "testid")
    private Integer testid;
    @JoinColumn(name = "kategoriid", referencedColumnName = "kategoriid")
    @ManyToOne
    private Kategori kategoriid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testid")
    private Collection<Soru> soruCollection;

    public Test(Kategori kategoriid) {
        this.kategoriid = kategoriid;
    }

    public Test() {
    }

    public Test(Integer testid) {
        this.testid = testid;
    }

    public Integer getTestid() {
        return testid;
    }

    public void setTestid(Integer testid) {
        this.testid = testid;
    }

    public Kategori getKategoriid() {
        return kategoriid;
    }

    public void setKategoriid(Kategori kategoriid) {
        this.kategoriid = kategoriid;
    }

    public Collection<Soru> getSoruCollection() {
        return soruCollection;
    }

    public void setSoruCollection(Collection<Soru> soruCollection) {
        this.soruCollection = soruCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testid != null ? testid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.testid == null && other.testid != null) || (this.testid != null && !this.testid.equals(other.testid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Test[ testid=" + testid + " ]";
    }
    
}
