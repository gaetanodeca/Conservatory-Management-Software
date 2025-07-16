package boundary.segreteriaboundary;

import control.ControllerDocente;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Interfaccia grafica per l'inserimento di un nuovo docente nel sistema.
 * <p>
 * L'utente Segreteria può inserire nome, cognome e password del docente.
 * I dati verranno gestiti tramite il ControllerDocente.
 */
public class InterfacciaInserisciDocente extends JFrame {

    private static final long serialVersionUID = 1L;

    /** Pannello principale che contiene tutti i componenti */
    private JPanel contentPane;

    /** Campo di testo per l'inserimento del nome del docente */
    private JTextField txtNome;

    /** Campo di testo per l'inserimento del cognome del docente */
    private JTextField txtCognome;

    /** Campo di testo per l'inserimento della password del docente */
    private JPasswordField passwordField;

    /**
     * Costruttore che inizializza la finestra e i suoi componenti.
     */
    public InterfacciaInserisciDocente() {
        setupFrame();
        setupUI();
    }

    /**
     * Imposta le proprietà principali della finestra, come titolo,
     * dimensioni, chiusura e layout iniziale.
     */
    private void setupFrame() {
        setTitle("Inserisci Docente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
        contentPane.setBackground(new Color(240, 248, 255));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
    }

    /**
     * Inizializza e aggiunge tutti i componenti grafici (etichette,
     * campi di testo, pulsanti) al contenuto della finestra.
     */
    private void setupUI() {
        contentPane.setLayout(new BorderLayout());

        // Titolo in alto
        JLabel lblTitle = new JLabel("Inserisci Nuovo Docente", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(25, 25, 112));
        lblTitle.setBorder(new EmptyBorder(20, 0, 20, 0));
        contentPane.add(lblTitle, BorderLayout.NORTH);

        // Pannello centrale per i campi di input
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;

        // Etichette
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblCognome = new JLabel("Cognome:");
        JLabel lblPassword = new JLabel("Password:");

        lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCognome.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblNome, gbc);

        gbc.gridy = 1;
        formPanel.add(lblCognome, gbc);

        gbc.gridy = 2;
        formPanel.add(lblPassword, gbc);

        // Campi di input
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        txtNome = new JTextField(20);
        txtCognome = new JTextField(20);
        passwordField = new JPasswordField(10);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(txtNome, gbc);

        gbc.gridy = 1;
        formPanel.add(txtCognome, gbc);

        gbc.gridy = 2;
        formPanel.add(passwordField, gbc);

        contentPane.add(formPanel, BorderLayout.CENTER);

        // Pannello inferiore con i pulsanti
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setOpaque(false);

        JButton btnBack = createStyledButton("Indietro", new Color(220, 20, 60));
        btnBack.addActionListener(e -> {
            InterfacciaGestioneDocenti frame = new InterfacciaGestioneDocenti();
            frame.setVisible(true);
            dispose();
        });

        JButton btnInserisci = createStyledButton("Inserisci", new Color(70, 130, 180));
        btnInserisci.addActionListener(e -> inserisciDocente());

        buttonPanel.add(btnBack);
        buttonPanel.add(btnInserisci);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Crea un pulsante con stile personalizzato (colore, font, bordo).
     *
     * @param text    Testo da visualizzare sul pulsante
     * @param bgColor Colore di sfondo del pulsante
     * @return JButton configurato
     */
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return button;
    }

    /**
     * Recupera i dati dai campi e tenta di inserire un nuovo docente tramite il controller.
     * <p>
     * I campi devono essere tutti compilati. In caso contrario, viene mostrato
     * un messaggio di errore. Se l'inserimento è valido, viene mostrato un
     * messaggio di successo.
     */

    private boolean checkEmpty(String nome, String cognome, String password)
    {
         if(nome.isEmpty() || cognome.isEmpty() || password.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                    "Tutti i campi devono essere compilati!",
                    "Errore",
                    JOptionPane.WARNING_MESSAGE);
                    return false;
        }else{
            return true;
        }
    }


    private boolean checkNome(String nome, StringBuilder messaggiErrore)
    {
        boolean valido = true;

        if (nome.length() > 20) {
            messaggiErrore.append("Il nome non può superare i 20 caratteri.\n");
            valido = false;
        } else if (!nome.matches("[a-zA-Z ]+")) {
            messaggiErrore.append("Il nome può contenere solo lettere.\n");
            valido = false;
        }

        return valido;
    }


    private boolean checkCognome(String cognome, StringBuilder messaggiErrore)
    {
        boolean valido = true;
        // Controlli per il cognome
        if (cognome.length() > 20) {
            messaggiErrore.append("Il cognome non può superare i 20 caratteri.\n");
            valido = false;
        } else if (!cognome.matches("[a-zA-Z]+")) {
            messaggiErrore.append("Il cognome può contenere solo lettere.\n");
            valido = false;
        }

        return valido;
    }


    private boolean checkPassword(String password, StringBuilder messaggiErrore)
    {
        boolean valido = true;
        // Controllo per la password
        if (password.length() != 10) {
            messaggiErrore.append("La password deve essere esattamente di 10 caratteri.\n");
            valido = false;
        }else if (!password.matches("[a-zA-Z0-9]+")) {
            messaggiErrore.append("La password può contenere solo caratteri alfanumerici.\n");
            valido = false;
        }

        return valido;
    }



   private void inserisciDocente() {
    String nome = txtNome.getText().trim();
    String cognome = txtCognome.getText().trim();
    String password = new String(passwordField.getPassword()).trim();

    boolean valido = true;
    StringBuilder messaggiErrore = new StringBuilder();

    if (!checkEmpty(nome, cognome, password)) {
        return;
    }

    if (!checkNome(nome, messaggiErrore)) {
        valido = false;
    }

    if (!checkCognome(cognome, messaggiErrore)) {
        valido = false;
    }

    if (!checkPassword(password, messaggiErrore)) {
        valido = false;
    }

    if (!valido) {
        JOptionPane.showMessageDialog(this, messaggiErrore.toString(), "Errore di validazione", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Se tutti i controlli sono superati, procedi con l'inserimento
    ControllerDocente CC = ControllerDocente.getInstance();
    String esito = CC.aggiungiDocente(nome, cognome, password);

    if (esito == null) {
        JOptionPane.showMessageDialog(this,
                "La matricola è già presente nel database.",
                "Errore",
                JOptionPane.WARNING_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this,
                "Inserimento avvenuto con successo.\nDati inseriti: " + esito + ", " + nome + ", " + cognome,
                "Successo",
                JOptionPane.INFORMATION_MESSAGE);
    }

    txtNome.setText("");
    txtCognome.setText("");
    passwordField.setText("");
    }

}