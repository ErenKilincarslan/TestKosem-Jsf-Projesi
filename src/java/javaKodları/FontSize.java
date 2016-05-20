/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaKodları;

import entity.controller.SoruJpaController;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author celal
 */
public class FontSize {

    private String size;
    private Map<String, String> map = new HashMap<>();

    public FontSize() {
        map.put("8px", "8px");
        map.put("10px", "10px");
        map.put("12px", "12px");
        map.put("13px", "13px");
        map.put("14px", "14px");
        map.put("15px", "15px");
        map.put("16px", "16px");
        map.put("17px", "17px");
        map.put("18px", "18px");
        map.put("20px", "20px");
        map.put("22px", "22px");
        map.put("24px", "24px");
        size=map.get("18px");
    }

    public void onSizeChange() {
        FacesMessage message2 = new FacesMessage("Succesful", size + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message2);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

}
