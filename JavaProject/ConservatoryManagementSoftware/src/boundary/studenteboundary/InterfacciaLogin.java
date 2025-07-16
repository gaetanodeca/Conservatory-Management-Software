package boundary.studenteboundary;

import boundary.InterfacciaHome;
import control.ControllerLogin;
import entity.Studente;
import exceptions.LoginFailedException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Interfaccia grafica per il login degli utenti nel conservatorio.
 */
public class InterfacciaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUsername; // Campo testo per username
    private JPasswordField passwordField; // Campo password

    private ControllerLogin controllerLogin; // Controller per gestire l'autenticazione

    /**
     * Costruttore che inizializza il controller, la finestra e la UI.
     */
    public InterfacciaLogin() {
        initializeDependencies();
        setupFrame();
        setupUI();
    }

    /**
     * Inizializza il controller singleton per la login.
     */
    private void initializeDependencies() {
        controllerLogin = ControllerLogin.getInstance();       
    }

    /**
     * Configura la finestra principale (dimensioni, titolo, layout).
     */
    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        setLocationRelativeTo(null);
        setTitle("Accesso Area Studente");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
        contentPane.setBackground(new Color(240, 248, 255));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
    }

    /**
     * Costruisce e aggiunge tutti i componenti UI sulla finestra.
     */
    private void setupUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label titolo centrato in alto
        JLabel lblTitle = new JLabel("Benvenuto nel Conservatorio");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(new Color(25, 25, 112));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        contentPane.add(lblTitle, gbc);

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.gridwidth = 1;

        // Label per username allineata a destra
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblUsername.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(lblUsername, gbc);

        // Campo testo per username
        textFieldUsername = new JTextField(20);
        textFieldUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(textFieldUsername, gbc);

        // Label per password allineata a destra
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPassword.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(lblPassword, gbc);

        // Campo password
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPane.add(passwordField, gbc);

        // Pulsante "Accedi"
        JButton btnConferma = new JButton("Accedi");
        btnConferma.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnConferma.setBackground(new Color(70, 130, 180));
        btnConferma.setForeground(Color.WHITE);
        btnConferma.setFocusPainted(false);
        btnConferma.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // Azione al click: prova il login
        btnConferma.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleLoginAttempt();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 8, 8, 8);
        contentPane.add(btnConferma, gbc);
        
        // Pulsante "Torna alla Home" per tornare alla schermata principale
        JButton btnTornaHome = new JButton("Torna alla Home");
        btnTornaHome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnTornaHome.setBackground(new Color(220, 20, 60)); // rosso scuro
        btnTornaHome.setForeground(Color.WHITE);
        btnTornaHome.setFocusPainted(false);
        btnTornaHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTornaHome.addActionListener(e -> {
            dispose(); // Chiudi InterfacciaLogin
            InterfacciaHome home = new InterfacciaHome();
            home.setVisible(true);
        });
        gbc.gridy = 4; // riga sotto il pulsante "Accedi"
        gbc.insets = new Insets(10, 8, 8, 8);
        contentPane.add(btnTornaHome, gbc);
    }

    /**
     * Gestisce il tentativo di login: verifica username e password con ControllerLogin,
     * mostra messaggi di errore o apre la schermata studente in caso di successo.
     */
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
    private void handleLoginAttempt() {
        String username = textFieldUsername.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Compila tutti i campi obbligatori (username e password).",
                    "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean authenticationSuccessful = controllerLogin.autenticazione(username, password);

            if (authenticationSuccessful) {
                JOptionPane.showMessageDialog(this,
                        "Accesso riuscito. Benvenuto/a " + username + "!",
                        "Successo",
                        JOptionPane.INFORMATION_MESSAGE);
                // Ottieni l'oggetto Studente corrispondente all'username
                Studente studente = controllerLogin.getStudenteByUsername(username);
                // Apri interfaccia studente passando l'oggetto Studente
                InterfacciaStudente finestraStudente = new InterfacciaStudente(studente);
                finestraStudente.setVisible(true);
                this.dispose();
            }
        } catch (LoginFailedException ex) {
            // Messaggio di errore se autenticazione fallita
            JOptionPane.showMessageDialog(
                this,
                "Errore di autenticazione: " + ex.getMessage(),
                "Login Fallito",
                JOptionPane.ERROR_MESSAGE
            );
            textFieldUsername.setText("");
            passwordField.setText("");
        } catch (Exception ex) {
            // Gestione di eventuali errori imprevisti
            System.err.println("Errore inatteso durante l'autenticazione: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Si è verificato un errore inatteso. Contatta l'amministratore.",
                "Errore di Sistema",
                JOptionPane.ERROR_MESSAGE
            );
            textFieldUsername.setText("");
            passwordField.setText("");
        } finally {
            // Pulizia della password dalla memoria per sicurezza
            Arrays.fill(passwordChars, ' ');
        }
    }
}
