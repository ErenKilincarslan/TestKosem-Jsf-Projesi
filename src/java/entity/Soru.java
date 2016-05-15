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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author celal
 */
@Entity
@Table(name = "soru")
@NamedQueries({
    @NamedQuery(name = "Soru.findAll", query = "SELECT s FROM Soru s")})
public class Soru implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "soruid")
    private Integer soruid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "soru")
    private String soru;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cevap1")
    private String cevap1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cevap2")
    private String cevap2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cevap3")
    private String cevap3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cevap4")
    private String cevap4;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dogrucevap")
    private int dogrucevap;
    @JoinColumn(name = "testid", referencedColumnName = "testid")
    @ManyToOne(optional = false)
    private Test testid;
    private String secilenCevap;

    public String getSecilenCevap() {
        return secilenCevap;
    }

    public void setSecilenCevap(String secilenCevap) {
        this.secilenCevap = secilenCevap;
    }
    public Soru() {
    }

    public Soru(int soruid,String soru, String cevap1, String cevap2, String cevap3, String cevap4, int dogrucevap) {
        this.soruid=soruid;
        this.soru = soru;
        this.cevap1 = cevap1;
        this.cevap2 = cevap2;
        this.cevap3 = cevap3;
        this.cevap4 = cevap4;
        this.dogrucevap = dogrucevap;
    }

    public Soru(Integer soruid) {
        this.soruid = soruid;
    }

    public Soru(String soru, String cevap1, String cevap2, String cevap3, String cevap4, int dogrucevap,Test test) {
        this.soru = soru;
        this.cevap1 = cevap1;
        this.cevap2 = cevap2;
        this.cevap3 = cevap3;
        this.cevap4 = cevap4;
        this.dogrucevap = dogrucevap;
        this.testid=test;
    }

    public Integer getSoruid() {
        return soruid;
    }

    public void setSoruid(Integer soruid) {
        this.soruid = soruid;
    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getCevap1() {
        return cevap1;
    }

    public void setCevap1(String cevap1) {
        this.cevap1 = cevap1;
    }

    public String getCevap2() {
        return cevap2;
    }

    public void setCevap2(String cevap2) {
        this.cevap2 = cevap2;
    }

    public String getCevap3() {
        return cevap3;
    }

    public void setCevap3(String cevap3) {
        this.cevap3 = cevap3;
    }

    public String getCevap4() {
        return cevap4;
    }

    public void setCevap4(String cevap4) {
        this.cevap4 = cevap4;
    }

    public int getDogrucevap() {
        return dogrucevap;
    }

    public void setDogrucevap(int dogrucevap) {
        this.dogrucevap = dogrucevap;
    }

    public Test getTestid() {
        return testid;
    }

    public void setTestid(Test testid) {
        this.testid = testid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (soruid != null ? soruid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Soru)) {
            return false;
        }
        Soru other = (Soru) object;
        if ((this.soruid == null && other.soruid != null) || (this.soruid != null && !this.soruid.equals(other.soruid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Soru[ soruid=" + soruid + " ]";
    }
    
}
