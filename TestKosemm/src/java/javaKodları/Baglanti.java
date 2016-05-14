
package javaKodları;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Baglanti {

    public static final String SERVER = "jdbc:mysql://localhost:3306/webproje";
    public static final String KULLANICIADI = "root";
    public static final String SIFRE = "1234";
    public Connection conn = null;

    public void baglan() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(SERVER, KULLANICIADI, SIFRE);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void baglantiKes() {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
