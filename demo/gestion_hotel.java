import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class gestion_hotel extends JFrame {
    private JTextField tfCODE_Hotel;
    private JTextField tfNAME;
    private JButton btnAjouter;
    private JPanel mainPanel;
    private JLabel lbGESTIONDEHOTELS;
    private JTextField tfVille;
    private JTextField tfRegion;
    private JTextField tfCodepostal;
    private JTextField tfCodehotel;
    private JTextField tfNom;
    private JComboBox cmbNbétoile;
    private JButton btnConsulter;
    private JButton quitterButton;
    private JButton btnSupprimer;
    private JTextArea resultat;

    public gestion_hotel() {
        setContentPane(mainPanel);
        setTitle("GESTION DES HOTELS");
        setSize(900, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Integer CODE_HOTEL = Integer.valueOf(tfCodehotel.getText());
                String NOM = tfNom.getText();
                String Ville = tfVille.getText();
                String REGION = tfRegion.getText();
                Integer CODE_POSTAL = Integer.valueOf(tfCodepostal.getText());
                String NB_ÉTOILES = cmbNbétoile.getSelectedItem().toString();
                lbGESTIONDEHOTELS.setText("GESTION DES HOTELS " + CODE_HOTEL + NOM);

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel", "debian-sys-maint", "Uo9LrT7AYHA8FJss");
                    System.out.println("done");
                    String sql = "Insert into hotel values (? , ? , ? , ? , ?, ?)";
                    PreparedStatement ajouter = conn.prepareStatement(sql);
                    ajouter.setInt(1, CODE_HOTEL);
                    ajouter.setString(2, NOM);
                    ajouter.setString(3, NB_ÉTOILES);
                    ajouter.setString(4, Ville);
                    ajouter.setString(5, REGION);
                    ajouter.setInt(6, Integer.parseInt(String.valueOf(CODE_POSTAL)));
                    ajouter.executeUpdate();
                    UIManager message_erreur_rouge = new UIManager();
                    message_erreur_rouge.put("OptionPane.messageForeground", Color.magenta);
                    JOptionPane.showMessageDialog(null, "Votre hotel a bien été ajouté à la base de donnée");
                    conn.close();
                } catch (Exception e) {
                    System.out.println("not done");
                    JOptionPane.showMessageDialog(null, e);
                }

            }
            //btnAjouter.setForeground(Color.GREEN);

            //AFFICHER UNE ERREUUUUUUUUUUR
            //JOptionPane.showMessageDialog(null, "notremessage");
        });

        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Integer CODE_HOTEL = Integer.valueOf(tfCodehotel.getText());
                lbGESTIONDEHOTELS.setText("");

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel", "debian-sys-maint", "Uo9LrT7AYHA8FJss");
                    System.out.println("done");
                    String sql = "DELETE FROM hotel WHERE CODE_HOTEL =" + CODE_HOTEL;
                    PreparedStatement delete = conn.prepareStatement(sql);
                    delete.executeUpdate();
                    UIManager message_erreur_rouge = new UIManager();
                    message_erreur_rouge.put("OptionPane.messageForeground", Color.magenta);
                    JOptionPane.showMessageDialog(null, "Votre hotel a bien été supprimé de la base de donnée");
                    conn.close();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });

        btnConsulter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Integer CODE_HOTEL = Integer.valueOf(tfCodehotel.getText());
                lbGESTIONDEHOTELS.setText("");

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel", "debian-sys-maint", "Uo9LrT7AYHA8FJss");
                    System.out.println("done");
                    String sql = "SELECT * FROM hotel WHERE CODE_HOTEL =" + CODE_HOTEL;
                    PreparedStatement selected = conn.prepareStatement(sql);
                    ResultSet consulter = selected.executeQuery(sql);
                    String _code_hotel = "";
                    int compteur = 0;
                    while(consulter.next()){
                        String NOM = consulter.getString(2);
                        compteur++;
                        resultat.append(NOM); //Result est le nom de la fenetre blanche dans la form
                    }
                    UIManager message_erreur_rouge = new UIManager();
                    message_erreur_rouge.put("OptionPane.messageForeground", Color.magenta);
                    JOptionPane.showMessageDialog(null, "Votre hotel a bien été trouvé dans la base de donnée");
                    conn.close();

                } catch (SQLException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Votre hotel n'a été trouvé dans la base de donnée");
                }
            }
        });
    }

    public static void main(String[] args) {

        gestion_hotel myHotel = new gestion_hotel();
    }
}