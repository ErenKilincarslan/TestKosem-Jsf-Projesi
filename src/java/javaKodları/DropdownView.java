package javaKodları;

import entity.Gecmis;
import entity.Kategori;
import entity.Soru;
import entity.Test;
import entity.controller.GecmisController;
import entity.controller.KategoriJpaController;
import entity.controller.SoruJpaController;
import entity.controller.TestJpaController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped
public class DropdownView {

    private Map<String, Map<String, String>> data = new HashMap<>();
    private String kategori;
    private String test1;
    private Map<String, String> kategoriler;
    private Map<String, String> testler;

    public DropdownView() {
        li = null;
        kategoriler = new HashMap<>();
        KategoriJpaController kjc = new KategoriJpaController();
        TestJpaController tjc = new TestJpaController();
        List<Kategori> kategoriList = kjc.getAllKategori();

        for (Kategori k : kategoriList) {
            kategoriler.put(k.getKategori(), k.getKategori());
            Map<String, String> map = new HashMap<>();
            List<Test> test = tjc.getAllTestFromKategori(k);
            for (Test t : test) {
                map.put(t.getTestid() + "", t.getTestid() + "");
            }
            data.put(k.getKategori(), map);
        }
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

    public void onKategoriChange() {
        FacesMessage message2 = new FacesMessage("Succesful", kategori + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message2);
        if (kategori != null) {
            testler = data.get(kategori);
        } else {
            testler = new HashMap<String, String>();
        }

    }
    public List<Soru> li;

    public List<Soru> getLi() {
        return li;
    }

    public void setLi(List<Soru> li) {
        this.li = li;
    }

    public List<Gecmis> getCözdüğümTestler(HttpSession session) {
        GecmisController test = new GecmisController();
        return test.getAllTestFromKullaniciId(getUserFromSession(session).getKullaniciid());

    }

    public String gecmisEkle(HttpSession session) {
        GecmisController gc = new GecmisController();
        gc.gecmisEkle(li, getUserFromSession(session).getKullaniciid(), test1);
        return "goster.xhtml?faces-redirect=true";
    }

    public Kullanıcı getUserFromSession(HttpSession session) {
        return (Kullanıcı) session.getAttribute("user");
    }

    public String testGecmisiGoster(Gecmis gecmis) {
        SoruJpaController sjc = new SoruJpaController();
        li = sjc.getAllSoruFromTestID(gecmis.getTestID() + "");
        String verilenCevap = gecmis.getTestgecmisi();
        for (int i = 0; i < verilenCevap.length(); i++) {
            if (verilenCevap.charAt(i) == '1') {
                li.get(i).setSecilenCevap(li.get(i).getCevap1());
            } else if (verilenCevap.charAt(i) == '2') {
                li.get(i).setSecilenCevap(li.get(i).getCevap2());
            } else if (verilenCevap.charAt(i) == '3') {
                li.get(i).setSecilenCevap(li.get(i).getCevap3());
            } else if (verilenCevap.charAt(i) == '4') {
                li.get(i).setSecilenCevap(li.get(i).getCevap4());
            }
        }
        return "goster.xhtml?faces-redirect=true";
    }

    public String displayLocation() {
        String k;
        String te;
        if (test1 != null && kategori != null) {
            k = kategori;
            te = test1;

            SoruJpaController sjc = new SoruJpaController();
            li = sjc.getAllSoruFromTestID(te);
            return "sorular.xhtml?faces-redirect=true";
        } else {
            FacesMessage message1 = new FacesMessage("Error", "Lütfen kaegori ve/veya test seçiniz");
            FacesContext.getCurrentInstance().addMessage(null, message1);
            return null;
        }
    }
}
