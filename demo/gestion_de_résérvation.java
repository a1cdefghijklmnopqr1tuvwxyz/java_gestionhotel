import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class gestion_de_résérvation extends JFrame {
    private JPanel panela;
    private JTextField tfNuméro;
    private JTextField tfNombredenuit;
    private JTextField tfNombresdepersonnes;
    private JTextField tfDatederésérvation;
    private JTextField tfDateenteré;
    private JTextField tfDatededépart;
    private JButton btnAjouter;
    private JButton btnModifier;
    private JButton btnSpprimer;
    private JTextField tfNuméroclient;
    private JTextField tfNumérodechambre;

    public gestion_de_résérvation() {
        setContentPane(panela);
        setTitle("GESTION DE RÉSÉRVATION");
        setSize(700, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btnAjouter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Integer Numéro = Integer.valueOf(tfNuméro.getText());
                        Integer NuméroClient = Integer.valueOf(tfNuméroclient.getText());
                        Integer Numérodechambre = Integer.valueOf(tfNumérodechambre.getText());
                        Integer Nombredenuit = Integer.valueOf(tfNombredenuit.getText());
                        Integer Nombredepersonnes = Integer.valueOf(tfNombresdepersonnes.getText());
                        Integer Datederésérvation = Integer.valueOf(tfDatederésérvation.getText());
                        Integer Dateentré = Integer.valueOf(tfDateenteré.getText());
                        Integer Datededépart = Integer.valueOf(tfDatededépart.getText());

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel", "debian-sys-maint", "Uo9LrT7AYHA8FJss");
                            System.out.println("done");
                            String sql = "Insert into résérvation values (? , ? , ? , ? , ?, ?, ?, ?)";
                            PreparedStatement ajouter = conn.prepareStatement(sql);
                            ajouter.setInt(1, Numéro);
                            ajouter.setInt(2, Integer.parseInt(String.valueOf(tfNuméroclient)));
                            ajouter.setInt(3, Integer.parseInt(String.valueOf(tfNumérodechambre)));
                            ajouter.setInt(4, Nombredenuit);
                            ajouter.setInt(5, Nombredepersonnes);
                            ajouter.setInt(6, Datederésérvation);
                            ajouter.setInt(7, Integer.parseInt(String.valueOf(Dateentré)));
                            ajouter.setInt(8, Integer.parseInt(String.valueOf(tfDatededépart)));
                            ajouter.executeUpdate();
                            UIManager message_erreur_rouge = new UIManager();
                            message_erreur_rouge.put("OptionPane.messageForeground", Color.magenta);
                            JOptionPane.showMessageDialog(null, "Votre résérvation a bien été ajouté à la base de donnée");
                            conn.close();
                        } catch (Exception e) {
                            System.out.println("not done");
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
            }
        });
        /*btnSpprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Integer CODE_HOTEL = Integer.valueOf(tfNuméro.getText());
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel", "debian-sys-maint", "Uo9LrT7AYHA8FJss");
                    System.out.println("done");
                    String sql = "DELETE FROM résérvation WHERE Numéro =" +Numéro;
                    PreparedStatement delete = conn.prepareStatement(sql);
                    delete.executeUpdate();
                    UIManager message_erreur_rouge = new UIManager();
                    message_erreur_rouge.put("OptionPane.messageForeground", Color.magenta);
                    JOptionPane.showMessageDialog(null, "Votre résérvation a bien été supprimé de la base de donnée");
                    conn.close();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });*/
    }

    public static void main(String[] args) {
        gestion_de_résérvation myReservation = new gestion_de_résérvation();
    }

   /*private void createUIComponents() {
        // TODO: place custom component creation code here
    }*/
}
