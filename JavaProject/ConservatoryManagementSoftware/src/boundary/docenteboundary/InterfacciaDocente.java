package boundary.docenteboundary;

import boundary.InterfacciaHome;
import boundary.InterfacciaLavoriInCorso;
import java.awt.*;
import javax.swing.*;

public class InterfacciaDocente extends JFrame {
/**
 * Finestra dell'area riservata ai docenti.
 * <p>
 * Fornisce accesso alle funzionalità principali per i docenti:
 * creazione di un verbale, validazione di verbali e aggiornamento del registro.
 */

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    /**
     * Costruttore che inizializza l'interfaccia grafica per l'area docente.
     */
    
    public InterfacciaDocente() {
        setTitle("Area Docente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(16, 40, 16, 40); 
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblTitle = new JLabel("Benvenuto nell'Area Docente", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(new Color(25, 25, 112));
        gbc.gridy = 0;
        contentPane.add(lblTitle, gbc);

        JButton btnCreaVerbale = createStyledButton("Crea Verbale");
        gbc.gridy = 1;
        contentPane.add(btnCreaVerbale, gbc);

        JButton btnValidaVerbale = createStyledButton("Valida Verbale");
        gbc.gridy = 2;
        contentPane.add(btnValidaVerbale, gbc);

        JButton btnAggiornaRegistro = createStyledButton("Aggiorna Registro");
        gbc.gridy = 3;
        contentPane.add(btnAggiornaRegistro, gbc);

        JButton btnIndietro = new JButton("Torna alla Home");
        btnIndietro.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnIndietro.setBackground(new Color(220, 20, 60));
        btnIndietro.setForeground(Color.WHITE);
        btnIndietro.setFocusPainted(false);
        btnIndietro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridy = 4;
        contentPane.add(btnIndietro, gbc);

        // Azioni
        btnCreaVerbale.addActionListener(e -> {
            InterfacciaCreaVerbale f = new InterfacciaCreaVerbale();
            f.setVisible(true);
            dispose();
        });

        btnValidaVerbale.addActionListener(e -> {
            InterfacciaLavoriInCorso f = new InterfacciaLavoriInCorso();
            f.setVisible(true);
        });

        btnAggiornaRegistro.addActionListener(e -> {
            InterfacciaLavoriInCorso f = new InterfacciaLavoriInCorso();
            f.setVisible(true);
        });

        btnIndietro.addActionListener(e -> {
            InterfacciaHome home = new InterfacciaHome();
            home.setVisible(true);
            dispose();
        });
    }

    /**
     * Crea un pulsante stilizzato con font, colore e cursore personalizzati.
     *
     * @param text il testo da visualizzare sul pulsante
     * @return un nuovo {@link JButton} con stile applicato
     */

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
}
