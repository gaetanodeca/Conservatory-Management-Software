package boundary.studenteboundary;

import control.ControllerCorso;
import entity.CorsoDiStudio;
import entity.FacadeConservatorio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

/**
 * Interfaccia grafica che permette allo studente di visualizzare e selezionare i corsi di Canto disponibili.
 * <p>
 * L'interfaccia carica i corsi da database e consente di aggiungerli
 * temporaneamente al piano di studi tramite interazione con {@link ControllerCorso}.
 */
public class InterfacciaCanto extends JFrame {

    private static final long serialVersionUID = 1L;

    /** Pannello principale dell'interfaccia. */
    private JPanel contentPane;

    /** Controller per gestire l'interazione con il database dei corsi. */
    private ControllerCorso controller;

    /** Lista di tutti i corsi di canto disponibili. */
    private List<CorsoDiStudio> corsiCanto = new ArrayList<>();

    /** Lista dei corsi selezionati dallo studente. */
    private List<CorsoDiStudio> corsiCantoSelezionati = new ArrayList<>();

    /** Checkbox grafici associati ai corsi. */
    private List<JCheckBox> checkBoxes;

    /** Riferimento alla finestra chiamante (InterfacciaCreazionePiano), se presente. */
    private InterfacciaCreazionePiano parentFrame;

    /**
     * Costruttore senza parametri, usato solo se non si vuole mantenere il riferimento alla finestra principale.
     */
    public InterfacciaCanto() {
        this(null);
    }

    /**
     * Costruttore che inizializza l'interfaccia con un riferimento alla finestra di creazione piano.
     *
     * @param parent riferimento alla {@link InterfacciaCreazionePiano}, per notificare aggiornamenti
     */
    public InterfacciaCanto(InterfacciaCreazionePiano parent) {
        this.parentFrame = parent;
        controller = new ControllerCorso(FacadeConservatorio.getInstance());
        checkBoxes = new ArrayList<>();
        corsiCanto = controller.getCorsiPerCanto();

        int altezzaFinestra = Math.max(350, 200 + (corsiCanto.size() * 30) + 80);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, altezzaFinestra);
        setLocationRelativeTo(null);
        setTitle("Corsi di Canto"); // Added title for clarity

        contentPane = new JPanel();
        // Match InterfacciaStudente background color
        contentPane.setBackground(new Color(240, 248, 255));
        // Match InterfacciaStudente padding/border
        contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Titolo
        JLabel lblTitle = new JLabel("Corsi di Canto Disponibili");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Larger font for title
        lblTitle.setForeground(new Color(25, 25, 112)); // Match InterfacciaStudente title color
        lblTitle.setBounds(0, 20, 520, 30);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(lblTitle);

        // Genera dinamicamente le righe con checkBox e CFU
        int yPosition = 80;
        for (int i = 0; i < corsiCanto.size(); i++) {
            CorsoDiStudio corso = corsiCanto.get(i);

            JCheckBox checkBox = new JCheckBox(corso.getDenominazione());
            checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 15)); // Match general text font size
            checkBox.setBackground(new Color(240, 248, 255)); // Make checkbox background match content pane
            checkBox.setBounds(30, yPosition, 300, 30); // Adjusted position and width
            contentPane.add(checkBox);
            checkBoxes.add(checkBox);

            JLabel lblCFU = new JLabel(corso.getCFU() + " CFU");
            lblCFU.setFont(new Font("Segoe UI", Font.PLAIN, 15)); // Match general text font size
            lblCFU.setForeground(new Color(70, 70, 70)); // Slightly darker for readability
            lblCFU.setBounds(350, yPosition, 80, 30); // Adjusted position
            contentPane.add(lblCFU);

            yPosition += 30;
        }

        // Bottone: aggiungi i corsi selezionati
        JButton btnAggiungiCorsi = new JButton("Aggiungi corsi");
        btnAggiungiCorsi.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Match InterfacciaStudente button font
        btnAggiungiCorsi.setBackground(new Color(70, 130, 180)); // Match standard button color
        btnAggiungiCorsi.setForeground(Color.WHITE);
        btnAggiungiCorsi.setFocusPainted(false);
        btnAggiungiCorsi.setBounds(50, yPosition + 40, 180, 45); // Adjusted size and position
        btnAggiungiCorsi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aggiungiCorsiSelezionatiAllaLista();
            }
        });
        contentPane.add(btnAggiungiCorsi);

        // Bottone: torna indietro alla finestra precedente
        JButton btnIndietro = new JButton("Indietro");
        btnIndietro.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Match InterfacciaStudente button font
        btnIndietro.setBackground(new Color(220, 20, 60)); // Match InterfacciaStudente logout button color
        btnIndietro.setForeground(Color.WHITE);
        btnIndietro.setFocusPainted(false);
        btnIndietro.setBounds(270, yPosition + 40, 180, 45); // Adjusted size and position
        btnIndietro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                if (parentFrame != null) {
                    parentFrame.setVisible(true);
                    parentFrame.refreshUI();
                }
                // No need for InterfacciaCanto.this.setVisible(false); as setVisible(false) already handles it
            }
        });
        contentPane.add(btnIndietro);
    }

    /**
     * Aggiunge i corsi selezionati tramite checkbox alla lista temporanea
     * {@code corsiCantoSelezionati} e li registra tramite il controller.
     */
    private void aggiungiCorsiSelezionatiAllaLista() {
        int corsiAggiunti = 0;
        corsiCantoSelezionati.clear(); // Clear previous selections to prevent duplicates on multiple clicks
        
        // It's good practice to get the current list from the controller if it's mutable
        // However, based on the current logic, corsiCanto is initialized once.
        // If the list of available courses can change, you should re-fetch it here.
        // For now, assuming corsiCanto is static for the life of this frame.

        for (int i = 0; i < checkBoxes.size(); i++) {
            JCheckBox checkbox = checkBoxes.get(i);
            CorsoDiStudio corso = corsiCanto.get(i); // Ensure index is within bounds

            if (checkbox.isSelected()) {
                // Add to internal list first
                corsiCantoSelezionati.add(corso);
                
                // Then try to add via controller (which might have its own checks like CFU limit)
                boolean aggiunto = controller.aggiungiCorsoSelezionato(
                                        corso.getCFU(), corso.getCodice(), corso.getDenominazione());
                if (aggiunto) {
                    corsiAggiunti++;
                } else {
                    // If not added by controller (e.g., due to CFU limit), remove from our local selection
                    corsiCantoSelezionati.remove(corso); 
                }
            }
        }

        String messaggio = corsiAggiunti > 0
                ? "Aggiunti " + corsiAggiunti + " corsi di Canto."
                : "Nessun nuovo corso selezionato, o i corsi selezionati superano il limite CFU."; // More informative message
        JOptionPane.showMessageDialog(this, messaggio);

        if (parentFrame != null) {
            // Always update the parent frame, even if no new courses were added
            // The parent frame needs the full updated list of selected canto courses
            parentFrame.aggiornaCorsiCanto(corsiCantoSelezionati); 
            parentFrame.refreshUI(); // Ensure parent UI is refreshed
        }
    }

}