import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension; //awt allows for stuff like colors, dimensions, layouts

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities; 

public class Main {
    public static JFrame window;
    public static JPanel contentPanel;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            window = new JFrame("Banque");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(600, 400);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            
            int BankNum, PinCode;
            double UserBal;
            String AccountName;
            boolean Status;
        });
    }
}