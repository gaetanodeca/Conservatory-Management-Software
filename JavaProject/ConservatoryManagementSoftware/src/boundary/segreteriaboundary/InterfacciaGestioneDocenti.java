package boundary.segreteriaboundary;


import boundary.InterfacciaLavoriInCorso;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Finestra per la gestione dei docenti all'interno del sistema.
 * <p>
 * Consente di accedere alle funzionalità di inserimento e modifica dei docenti.
 * È accessibile, ad esempio, dall'interfaccia della segreteria.
 */

public class InterfacciaGestioneDocenti extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Costruttore che inizializza l'interfaccia per la gestione dei docenti.
     */

    public InterfacciaGestioneDocenti() {
        setupFrame();
        setupUI();
    }

    /**
     * Configura la finestra principale (titolo, dimensioni, layout, ecc.).
     */

    private void setupFrame() {
        setTitle("Gestione Docenti");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
        contentPane.setBackground(new Color(240, 248, 255));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
    }

    /**
     * Costruisce l'interfaccia utente aggiungendo pulsanti e layout.
     */

    private void setupUI() {
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.insets = new Insets(0, 0, 30, 0);
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 2;
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Pannello Gestione Docenti");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(25,25,112));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitle, gbcTitle);

        addButton("Aggiungi Docente", 1, e -> {
            InterfacciaInserisciDocente finestra = new InterfacciaInserisciDocente();
            finestra.setVisible(true);
            dispose();
        });

        addButton("Modifica Docente", 2, e -> {
            new InterfacciaLavoriInCorso().setVisible(true);
        });

        GridBagConstraints gbcBack = createGbc(3);
        gbcBack.insets = new Insets(30, 8, 8, 8);
        JButton btnBack = createStyledButton("Indietro");
        btnBack.setBackground((new Color(220, 20, 60)));
        btnBack.addActionListener(e -> {
            InterfacciaSegreteria finestra = new InterfacciaSegreteria();
            finestra.setVisible(true);
            dispose();
        });
        contentPane.add(btnBack, gbcBack);
    }
    
    /**
     * Aggiunge un pulsante all'interfaccia con posizione e azione associate.
     *
     * @param text   il testo da visualizzare sul pulsante
     * @param y      la posizione verticale nella griglia
     * @param action il listener da eseguire al click del pulsante
     */

    private void addButton(String text, int y, ActionListener action) {
        GridBagConstraints gbc = createGbc(y);
        JButton button = createStyledButton(text);
        button.addActionListener(action);
        contentPane.add(button, gbc);
    }
    
    /**
     * Crea un insieme di vincoli {@link GridBagConstraints} per posizionare un componente.
     *
     * @param y la riga verticale in cui posizionare il componente
     * @return un oggetto {@link GridBagConstraints} preconfigurato
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
     * Crea un pulsante con stile grafico uniforme.
     *
     * @param text il testo del pulsante
     * @return un nuovo {@link JButton} con font e colori personalizzati
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
