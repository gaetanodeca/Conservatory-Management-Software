package boundary.segreteriaboundary;

import boundary.InterfacciaHome;
import boundary.InterfacciaLavoriInCorso;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Finestra principale dell'area Segreteria.
 */

public class InterfacciaSegreteria extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Costruttore: configura frame e interfaccia utente.
     */

    public InterfacciaSegreteria() {
        setupFrame();
        setupUI();
    }

    /**
     * Configura il frame principale: dimensioni, titolo, layout, colori.
     */

    private void setupFrame() {
        setTitle("Area Segreteria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
        contentPane.setBackground(new Color(240, 248, 255));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
    }

     /**
     * Configura l'interfaccia utente aggiungendo titolo e pulsanti.
     */

    private void setupUI() {
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.insets = new Insets(0, 0, 30, 0);
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 2;
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Benvenuto nell'Area Segreteria");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(25, 25, 112));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitle, gbcTitle);

        addButton("Gestione Docenti", 1, e -> {
            InterfacciaGestioneDocenti f = new InterfacciaGestioneDocenti();
            f.setVisible(true);
            dispose();
        });

        addButton("Gestione Corsi", 2, e -> {
            InterfacciaLavoriInCorso f = new InterfacciaLavoriInCorso(); 
            f.setVisible(true);
        });

        addButton("Associa Docente e Corso", 3, e -> {
            InterfacciaLavoriInCorso f = new InterfacciaLavoriInCorso(); 
            f.setVisible(true);
        });

        GridBagConstraints gbcLogout = createGbc(4);
        gbcLogout.insets = new Insets(30, 8, 8, 8);
        JButton btnHome = createStyledButton("Torna alla Home");
        btnHome.setBackground(new Color(220, 20, 60));
        btnHome.addActionListener(e -> {
            new InterfacciaHome().setVisible(true);
            dispose();
        });
        contentPane.add(btnHome, gbcLogout);
    }

    /**
     * Metodo helper per aggiungere un pulsante con testo, riga e azione.
     * 
     * @param text testo del pulsante
     * @param y riga nella griglia
     * @param action azione associata al click
     */

    private void addButton(String text, int y, ActionListener action) {
        GridBagConstraints gbc = createGbc(y);
        JButton button = createStyledButton(text);
        button.addActionListener(action);
        contentPane.add(button, gbc);
    }

     /**
     * Metodo helper per creare GridBagConstraints per una riga y.
     * 
     * @param y la riga desiderata
     * @return GridBagConstraints configurato per riga y e larghezza 2 colonne
     */

    private GridBagConstraints createGbc(int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 8, 12, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        return gbc;
    }
        
     /**
     * Metodo helper per creare un JButton con stile coerente.
     * 
     * @param text testo del pulsante
     * @return JButton stilizzato
     */

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
}
