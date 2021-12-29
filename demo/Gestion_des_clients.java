import javax.swing.*;
import java.awt.*;

public class Gestion_des_clients extends JFrame {
    private JPanel panels;
    private JTextField tfNuméro;
    private JTextField tfPrénom;
    private JTextField tfNom;
    private JTextField tfCin;
    private JTextField tfTel;
    private JButton btnAjouter;
    private JButton btnSupprimer;
    private JButton btnModifier;

    public Gestion_des_clients() {
        setContentPane(panels);
        setTitle("GESTION CLIENTS");
        setSize(700, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main (String[]args){
        Gestion_des_clients myClient = new Gestion_des_clients();
    }
}
