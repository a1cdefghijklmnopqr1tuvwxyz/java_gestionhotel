import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class admin  extends JFrame {
    private JPanel panelr;
    private JTextField tfIdAdmin;
    private JTextField tfNom;
    private JTextField tfPrénom;
    private JTextField tfTel;
    private JTextField tfPoste;
    private JComboBox cmbSalaire;
    private JButton btnAjouter;
    private JButton btnSupprimer;
    private JButton btnModifier;


    public admin() {
        setContentPane(panelr);
        setTitle("Admin");
        setSize(550, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    Integer ID_ADMIN = Integer.valueOf(tfIdAdmin.getText());
                    String NOM = tfNom.getText();
                    String PRÉNOM = tfPrénom.getText();
                    Integer TEL = Integer.valueOf(tfTel.getText());
                    String POSTE = tfPoste.getText();
                    String SALIRE_REÇUE = cmbSalaire.getSelectedItem().toString();
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel", "debian-sys-maint", "Uo9LrT7AYHA8FJss");
                                System.out.println("done");
                        String sql = "Insert into admin values (? , ? , ? , ? , ?, ?)";
                        PreparedStatement ajouter = conn.prepareStatement(sql);
                        ajouter.setInt(1, ID_ADMIN);
                        ajouter.setString(2, NOM);
                        ajouter.setString(3, PRÉNOM);
                        ajouter.setInt(4, TEL);
                        ajouter.setString(5, POSTE);
                        ajouter.setString(6, SALIRE_REÇUE);
                        ajouter.executeUpdate();
                        UIManager message_erreur_rouge = new UIManager();
                        message_erreur_rouge.put("OptionPane.messageForeground", Color.magenta);
                        JOptionPane.showMessageDialog(null, "Votre admin a bien été ajouté à la base de donnée");
                        conn.close();
                    } catch (Exception e) {
                        System.out.println("not done");
                        JOptionPane.showMessageDialog(null, e);
                    }
            }
        });
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btnSupprimer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Integer ID_ADMIN = Integer.valueOf(tfIdAdmin.getText());
                        //lbGESTIONDEHOTELS.setText("");
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel", "debian-sys-maint", "Uo9LrT7AYHA8FJss");
                            System.out.println("done");
                            String sql = "DELETE FROM admin WHERE ID_ADMIN =" + ID_ADMIN;
                            PreparedStatement delete = conn.prepareStatement(sql);
                            delete.executeUpdate();
                            UIManager message_erreur_rouge = new UIManager();
                            message_erreur_rouge.put("OptionPane.messageForeground", Color.magenta);
                            JOptionPane.showMessageDialog(null, "Votre admin a bien été supprimé de la base de donnée");
                            conn.close();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }


    public static void main(String[] args) {
        admin myadmin = new admin();
    }
}