/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaKodları;

/**
 *
 * @author celal
 */
import entity.Cevap;
import entity.Dogrucevap;
import entity.Kategori;
import entity.Soru;
import entity.Test;
import entity.controller.CevapJpaController;
import entity.controller.DogrucevapJpaController;
import entity.controller.KategoriJpaController;
import entity.controller.SoruJpaController;
import entity.controller.TestJpaController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class FileUploadView {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (file != null) {
            String path = new File(".").getAbsolutePath();
            FacesMessage message = new FacesMessage("Succesful", path + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            try {
                save();
            } catch (IOException ex) {
                Logger.getLogger(FileUploadView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void save() throws IOException {
        String filename = FilenameUtils.getName(getFile().getFileName());
        InputStream input = getFile().getInputstream();
        File file = new File("C:\\Users\\celal\\OneDrive\\Belgeler\\NetBeansProjects\\TestKosem\\xmlfiles", filename);
        OutputStream output = new FileOutputStream(file);
        try {
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
        parseXML(file);
    }

    private void parseXML(File xmlFile) {
        SAXBuilder builder = new SAXBuilder();

        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("sorum");

            Kategori k = new Kategori();
            k.setKategori(rootNode.getAttributeValue("kategori"));
            KategoriJpaController kjp = new KategoriJpaController();
            kjp.getKategoriID(k);

            Test test = new Test(k);
            TestJpaController tja = new TestJpaController();
            tja.addTest(test);

            for (int i = 0; i < list.size(); i++) {

                Element node = (Element) list.get(i);
                String s = node.getChildText("soru");
                Soru soru = new Soru(s, test);
                SoruJpaController sjc=new SoruJpaController();
                sjc.create(soru);
                
                int dogruCevapId = Integer.parseInt(node.getChildText("dcevap"));
                
                for (int j = 1; j < 5; j++) {
                    Cevap c = new Cevap(node.getChildText("cevap" + j), soru);
                    CevapJpaController cjc = new CevapJpaController();
                    cjc.create(c);
                    if (j == dogruCevapId) {
                        Dogrucevap dogrucevap = new Dogrucevap(soru,c);
                        DogrucevapJpaController djc=new DogrucevapJpaController();
                        djc.create(dogrucevap);
                    }
                }
            }

        } catch (IOException io) {
            throw new Error(io.getMessage());
        } catch (JDOMException jdomex) {
            throw new Error(jdomex.getMessage());
        }
    }
}
