package boundary;

import boundary.docenteboundary.InterfacciaDocente;
import boundary.segreteriaboundary.InterfacciaSegreteria;
import boundary.studenteboundary.InterfacciaLogin;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * Finestra principale del portale del Conservatorio.
 * <p>
 * Consente all'utente di accedere alle aree: Studente, Docente e Segreteria.
 * All'avvio viene applicato il tema Nimbus per l'interfaccia.
 */

public class InterfacciaHome extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Metodo principale per l'avvio dell'applicazione.
     * <p>
     * Imposta il Look and Feel Nimbus e avvia l'interfaccia grafica in un thread dedicato all'EDT.
     *
     * @param args argomenti da linea di comando (non utilizzati)
     */

    public static void main(String[] args) {

    	try {
            
            UIManager.setLookAndFeel(new NimbusLookAndFeel()); 
            
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Errore nell'impostazione del Look and Feel Nimbus: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) { 
            System.err.println("Errore generico nell'impostazione del Look and Feel: " + e.getMessage());
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            try {
                InterfacciaHome frame = new InterfacciaHome();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Costruttore che inizializza l'interfaccia della Home.
     */

    public InterfacciaHome() {
        setupFrame();
        setupUI();
    }

    /**
     * Configura le impostazioni di base della finestra: titolo, dimensioni, layout e colore.
     */

    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 780); 
        setLocationRelativeTo(null);
        setTitle("Home Conservatorio");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
        contentPane.setBackground(new Color(240, 248, 255));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
    }

    /**
     * Costruisce i componenti dell'interfaccia utente: titolo, immagine e pulsanti di accesso.
     */

    private void setupUI() {
        
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.insets = new Insets(0, 0, 20, 0);
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 2;
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Benvenuto nel Portale del Conservatorio");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28)); // Più grande
        lblTitle.setForeground(new Color(25, 25, 112));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitle, gbcTitle);

        GridBagConstraints gbcImage = new GridBagConstraints();
        gbcImage.gridx = 0;
        gbcImage.gridy = 1;
        gbcImage.gridwidth = 2;
        gbcImage.insets = new Insets(0, 0, 0, 0);

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/logo.png"));
            Image scaledImg = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH); // Più grande
            JLabel lblImage = new JLabel(new ImageIcon(scaledImg));
            lblImage.setHorizontalAlignment(SwingConstants.CENTER);
            contentPane.add(lblImage, gbcImage);
        } catch (Exception e) {
            JLabel lblError = new JLabel("Immagine non trovata");
            lblError.setForeground(Color.RED);
            lblError.setHorizontalAlignment(SwingConstants.CENTER);
            contentPane.add(lblError, gbcImage);
        }

    /**
     * Crea un insieme di vincoli {@link GridBagConstraints} preconfigurati per posizionare un componente.
     *
     * @param y la riga verticale nella griglia
     * @return vincoli per {@link GridBagLayout}
     */
        
        GridBagConstraints gbc1 = createGbc(2);
        JButton btnStudente = createStyledButton("Area Studente");
        btnStudente.addActionListener(e -> {
            InterfacciaLogin loginStudente = new InterfacciaLogin();
            loginStudente.setVisible(true);
            dispose();
        });
        contentPane.add(btnStudente, gbc1);

       
        GridBagConstraints gbc2 = createGbc(3);
        JButton btnDocente = createStyledButton("Area Docente");
        btnDocente.addActionListener(e -> {
            InterfacciaDocente interfacciaDocente = new InterfacciaDocente();
            interfacciaDocente.setVisible(true);
            dispose();
        });
        contentPane.add(btnDocente, gbc2);

        
        GridBagConstraints gbc3 = createGbc(4);
        JButton btnSegreteria = createStyledButton("Area Segreteria");
        btnSegreteria.addActionListener(e -> {
            InterfacciaSegreteria interfacciaSegreteria = new InterfacciaSegreteria();
            interfacciaSegreteria.setVisible(true);
            dispose();
        });
        contentPane.add(btnSegreteria, gbc3);
    }

    private GridBagConstraints createGbc(int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(16, 10, 16, 10); // Più spazio
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        return gbc;
    }
    
    /**
     * Crea un pulsante stilizzato con font e colori personalizzati.
     *
     * @param text il testo del pulsante
     * @return un nuovo {@link JButton} con stile applicato
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Più grande
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
}
