import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Acceuil extends JFrame {
    private JPanel panelm;
    private JTextField tfLogin;
    private JPasswordField tfPassword;
    private JButton btnConnexion;
    private JButton btnSortir;
    private JLabel lbAcceuil;


    public Acceuil() {
        setContentPane(panelm);
        setTitle("Acceuil");
        setSize(550, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String LOGIN = tfLogin.getText();
                String PASSWORD = tfPassword.getText();
                JPasswordField tfPasssword = new JPasswordField(PASSWORD);


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel", "debian-sys-maint", "Uo9LrT7AYHA8FJss");
                    System.out.println("done");
                    String sql = "Insert into acceuil values (? , ?)";
                    PreparedStatement connexion = conn.prepareStatement(sql);
                    connexion.setString(1, LOGIN);
                    connexion.setString(2, PASSWORD);
                    connexion.executeUpdate();
                    UIManager message_erreur_rouge = new UIManager();
                    message_erreur_rouge.put("OptionPane.messageForeground", Color.blue);
                    JOptionPane.showMessageDialog(null, "Vous avez bien été connecté");
                    conn.close();
                } catch (Exception e) {
                    System.out.println("not done at all :)");
                    JOptionPane.showMessageDialog(null, e);
                }

                /*btnConnexion.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        //AFFICHER UNE ERREUUUUUUUUUUR
                        JOptionPane.showMessageDialog(null, "Votre mot de pass est incorrect");
                    }*/
            }
        });
    }
    public static void main(String[] args) {
        Acceuil myAcceuil = new Acceuil();
    }
}