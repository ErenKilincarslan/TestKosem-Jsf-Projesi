package javaKodları;

import entity.Cevap;
import entity.Kategori;
import entity.Soru;
import entity.Test;
import entity.controller.CevapJpaController;
import entity.controller.KategoriJpaController;
import entity.controller.SoruJpaController;
import entity.controller.TestJpaController;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class DropdownView implements Serializable {

    private Map<String, Map<String, String>> data = new HashMap<>();
    private String kategori;
    private String test1;
    private Map<String, String> kategoriler;
    private Map<String, String> testler;
    private Map<Soru, Map<Cevap, Cevap>> sorular = new HashMap<>();

    public DropdownView() {
        kategoriler = new HashMap<>();
        KategoriJpaController kjc = new KategoriJpaController();
        TestJpaController tjc = new TestJpaController();
        List<Kategori> kategoriList = kjc.getAllKategori();
        for (Kategori k : kategoriList) {
            kategoriler.put(k.getKategori(), k.getKategori());
            kategori = k.getKategori();
            Map<String, String> map = new HashMap<>();
            List<Test> test = tjc.getAllTestFromKategori(k);
            for (Test t : test) {
                map.put(t.toString(), t.toString());
                test1 = t.toString();
            }
            data.put(k.getKategori(), map);
        }
        testler = data.get(kategori);
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    public Map<String, String> getKategoriler() {
        return kategoriler;
    }

    public Map<String, String> getTestler() {
        return testler;
    }

    public Map<Soru, Map<Cevap, Cevap>> getMap() {
        return sorular;
    }

    public void onKategoriChange() {
        FacesMessage message = new FacesMessage("Succesful", testler.size() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        if (kategori != null) {
            testler = data.get(kategori);
        } else {
            testler = new HashMap<>();
        }

    }
    public List<Soru> li;

    public List<Soru> getLi() {
        return li;
    }

    public void setLi(List<Soru> li) {
        this.li = li;
    }

    public void displayLocation() {
        String k;
        String te;

        if (test1 != null && kategori != null) {
            k = kategoriler.get(kategori);
            te = testler.get(test1);
            
            SoruJpaController sjc = new SoruJpaController();
            CevapJpaController cjc = new CevapJpaController();
            li = sjc.getAllSoruFromTestID(te);
            
            FacesMessage message = new FacesMessage("Succesful", k+te+ " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            for (Soru so : li) {
                List<Cevap> listCevap = cjc.getAllCevapFromSoruID(so);
                so.setCevapCollection(listCevap);
            }
        }
        
    }
}
