/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author celal
 */
public class Soru implements Serializable {
    private String secilenCevap;
    private static final long serialVersionUID = 1L;
    private Integer soruid;
    private String soru;
    private List<Cevap> cevapCollection;
    private Collection<Dogrucevap> dogrucevapCollection;
    private Test test;

    public Soru(Integer soruid, String soru) {
        this.soruid = soruid;
        this.soru = soru;
    }

    public String getSecilenCevap() {
        return secilenCevap;
    }

    public void setSecilenCevap(String secilenCevap) {
        this.secilenCevap = secilenCevap;
    }

   

    public Soru(String soru, Test testid) {
        this.soru = soru;
        this.test = testid;
    }

    public Soru(Integer soruid) {
        this.soruid = soruid;
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

    public List<Cevap> getCevapCollection() {
        return cevapCollection;
    }

    public void setCevapCollection(List<Cevap> cevapCollection) {
        this.cevapCollection = cevapCollection;
    }

    public Collection<Dogrucevap> getDogrucevapCollection() {
        return dogrucevapCollection;
    }

    public void setDogrucevapCollection(Collection<Dogrucevap> dogrucevapCollection) {
        this.dogrucevapCollection = dogrucevapCollection;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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
        return getSoru();
    }
    
}
