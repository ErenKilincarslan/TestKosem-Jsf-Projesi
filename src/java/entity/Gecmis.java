/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
/**
 *
 * @author celal
 */

public class Gecmis implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer gid;
    private String testgecmisi;
    private Date tarih;
    private Kategori kategori;
    private List<Soru> soruList;
    private int testID;

    public String getKategori() {
        return kategori.toString();
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public List<Soru> getSoruList() {
        return soruList;
    }

    public void setSoruList(List<Soru> soruList) {
        this.soruList = soruList;
    }
    public Gecmis() {
    }

    public Gecmis(Integer gid) {
        this.gid = gid;
    }

    public Gecmis(Integer gid, String testgecmisi, Date tarih) {
        this.gid = gid;
        this.testgecmisi = testgecmisi;
        this.tarih = tarih;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getTestgecmisi() {
        return testgecmisi;
    }

    public void setTestgecmisi(String testgecmisi) {
        this.testgecmisi = testgecmisi;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gid != null ? gid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gecmis)) {
            return false;
        }
        Gecmis other = (Gecmis) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Gecmis[ gid=" + gid + " ]";
    }
    
}
