import javax.swing.*;

public class gestion_des_chambres extends JFrame{
    private JPanel panelr;
    private JTextField tfNuméro;
    private JTextField tfLabel;
    private JButton btnSupprimer;
    private JButton btnAjouter;
    private JButton btnModifier;
    private JComboBox cbxType;
    private JComboBox cbxCatégorie;

    public gestion_des_chambres() {
        setContentPane(panelr);
        setTitle("GESTION DES CHAMBRES");
        setSize(700, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main (String[]args){
        gestion_des_chambres myRoom = new gestion_des_chambres();
    }
}