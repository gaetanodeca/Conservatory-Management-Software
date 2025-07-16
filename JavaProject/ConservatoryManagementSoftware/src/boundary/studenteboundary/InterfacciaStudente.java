package boundary.studenteboundary;

import boundary.InterfacciaHome;
import boundary.InterfacciaLavoriInCorso;
import entity.Studente;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

 /**
 * Finestra principale per l'area Studente.
 * Mostra le informazioni dello studente e permette l'accesso a varie funzionalità.
 */

public class InterfacciaStudente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Studente studente;

     /**
     * Costruisce una nuova finestra dell'area Studente.
     *
     * @param studente l'oggetto Studente contenente le informazioni da visualizzare
     */
    
    public InterfacciaStudente(Studente studente) {
        this.studente = studente;
        setupFrame();
        setupUI();
    }

    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 550);
        setLocationRelativeTo(null);
        setTitle("Area Studente");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
        contentPane.setBackground(new Color(240, 248, 255));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
    }

     /**
     * Costruisce e aggiunge gli elementi grafici (label, pulsanti, pannelli) alla finestra.
     */

    private void setupUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 8, 10, 8);
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel lblTitle = new JLabel("Benvenuto nell'Area Studente");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(25, 25, 112));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        contentPane.add(lblTitle, gbc);

        
        JPanel nomeCognomePanel = new JPanel(new GridBagLayout());
        nomeCognomePanel.setOpaque(false); // trasparente per mantenere il background

        GridBagConstraints gbcNc = new GridBagConstraints();
        gbcNc.insets = new Insets(0, 5, 0, 5);
        gbcNc.gridx = 0;
        gbcNc.gridy = 0;

        JLabel lblNome = new JLabel(studente.getNome());
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblNome.setForeground(new Color(25, 25, 112));
        nomeCognomePanel.add(lblNome, gbcNc);

        gbcNc.gridx = 1;
        JLabel lblCognome = new JLabel(studente.getCognome());
        lblCognome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblCognome.setForeground(new Color(25, 25, 112));
        nomeCognomePanel.add(lblCognome, gbcNc);

        gbc.gridy = 1;
        contentPane.add(nomeCognomePanel, gbc);

        JLabel lblMatricola = new JLabel(studente.getMatricola());
        lblMatricola.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblMatricola.setHorizontalAlignment(SwingConstants.CENTER);
        lblMatricola.setForeground(new Color(25, 25, 112));
        gbc.insets = new Insets(0, 8, 10, 8);
        gbc.gridy = 2;
        contentPane.add(lblMatricola, gbc);

        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        contentPane.add(createStyledButton("Visualizza Corsi Disponibili", this::openLavoriInCorsoFrame), createGbc(3));
        contentPane.add(createStyledButton("Crea Piano di Studi", () -> {
            new InterfacciaCreazionePiano(studente).setVisible(true);
            setVisible(false);
            studente.CreaPianoDiStudio();
        }), createGbc(4));
        contentPane.add(createStyledButton("Visualizza Esami Sostenuti", this::openLavoriInCorsoFrame), createGbc(5));
        contentPane.add(createStyledButton("Visualizza Media", this::openLavoriInCorsoFrame), createGbc(6));
        contentPane.add(createStyledButton("Visualizza Media Ponderata", this::openLavoriInCorsoFrame), createGbc(7));

        GridBagConstraints gbcLogout = createGbc(8);
        gbcLogout.insets = new Insets(30, 8, 8, 8);
        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogout.setBackground(new Color(220, 20, 60));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(e -> {
            dispose();
            new InterfacciaHome().setVisible(true);
        });
        contentPane.add(btnLogout, gbcLogout);
    }

    /**
     * Crea un oggetto GridBagConstraints con le impostazioni base per una riga specifica.
     *
     * @param y la riga in cui posizionare il componente
     * @return un oggetto GridBagConstraints configurato
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
     * Crea un JButton con stile personalizzato e un'azione opzionale associata.
     *
     * @param text   testo del pulsante
     * @param onClick azione da eseguire al click (può essere null)
     * @return il JButton creato
     */

    private JButton createStyledButton(String text, Runnable onClick) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        if (onClick != null) {
            button.addActionListener(e -> onClick.run());
        }
        return button;
    }

    private void openLavoriInCorsoFrame() {
        new InterfacciaLavoriInCorso().setVisible(true);
    }
}
