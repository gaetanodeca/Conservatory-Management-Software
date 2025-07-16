package boundary.studenteboundary;

import control.ControllerCorso;
import entity.CorsoDiStudio;
import entity.FacadeConservatorio;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Interfaccia grafica per la selezione dei corsi di Musica d'Insieme.
 * Permette allo studente di visualizzare e selezionare i corsi disponibili
 * e comunicarli all'interfaccia principale per la composizione del piano di studi.
 */
public class InterfacciaInsieme extends JFrame {

    private static final long serialVersionUID = 1L;

    /** Pannello principale dell'interfaccia. */
    private JPanel contentPane;

    /** Controller per la gestione dei corsi. */
    private ControllerCorso controller;

    /** Lista dei corsi di Musica d'Insieme ottenuti dal database. */
    private List<CorsoDiStudio> corsiInsieme;

    /** Lista dei corsi di Musica d'Insieme selezionati dall'utente. */
    private List<CorsoDiStudio> corsiInsiemeSelezionati = new ArrayList<>();

    /** Lista dei checkbox associati ai corsi. */
    private List<JCheckBox> checkBoxes;

    /** Riferimento al frame principale per aggiornamento e navigazione. */
    private InterfacciaCreazionePiano parentFrame;

    /**
     * Costruttore senza parametri.
     * Utilizzato se non si desidera aggiornare l'interfaccia principale.
     */
    public InterfacciaInsieme() {
        this(null);
    }

    /**
     * Costruttore con riferimento all'interfaccia principale.
     * * @param parent il frame chiamante, per aggiornamenti e ritorno
     */
    public InterfacciaInsieme(InterfacciaCreazionePiano parent) {
        this.parentFrame = parent;
        controller = new ControllerCorso(FacadeConservatorio.getInstance());
        checkBoxes = new ArrayList<>();

        corsiInsieme = controller.getCorsiPerInsieme();

        int altezzaFinestra = Math.max(350, 200 + (corsiInsieme.size() * 30) + 80);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, altezzaFinestra);
        setLocationRelativeTo(null);
        setTitle("Corsi di Musica d'Insieme"); // Added title for clarity

        contentPane = new JPanel();
        // Match InterfacciaStudente background color
        contentPane.setBackground(new Color(240, 248, 255)); 
        // Match InterfacciaStudente padding/border
        contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Corsi di Musica d'Insieme Disponibili");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Larger font for title
        lblTitle.setForeground(new Color(25, 25, 112)); // Match InterfacciaStudente title color
        lblTitle.setBounds(0, 20, 520, 30);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(lblTitle);

        int yPosition = 80;
        for (int i = 0; i < corsiInsieme.size(); i++) {
            CorsoDiStudio corso = corsiInsieme.get(i);

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
                    parentFrame.refreshUI(); // Refresh the parent's UI
                }
            }
        });
        contentPane.add(btnIndietro);
    }

    /**
     * Verifica i checkbox selezionati e aggiunge i relativi corsi
     * alla lista locale e al controller.
     * Se esiste un frame principale, lo aggiorna con i corsi scelti.
     */
    private void aggiungiCorsiSelezionatiAllaLista() {
        int corsiAggiunti = 0;
        corsiInsiemeSelezionati.clear(); // Clear previous selections

        for (int i = 0; i < checkBoxes.size(); i++) {
            JCheckBox checkbox = checkBoxes.get(i);
            CorsoDiStudio corso = corsiInsieme.get(i);

            if (checkbox.isSelected()) {
                corsiInsiemeSelezionati.add(corso); // Add to local list first
                boolean aggiunto = controller.aggiungiCorsoSelezionato(corso.getCFU(), corso.getCodice(), corso.getDenominazione());
                if (aggiunto) {
                    corsiAggiunti++;
                } else {
                    corsiInsiemeSelezionati.remove(corso); // If not added by controller, remove from local list
                }
            }
        }

        String messaggio = corsiAggiunti > 0
            ? "Aggiunti " + corsiAggiunti + " corsi di Musica d'Insieme."
            : "Nessun nuovo corso selezionato, o i corsi selezionati superano il limite CFU."; // More informative message
        javax.swing.JOptionPane.showMessageDialog(this, messaggio);

        if (parentFrame != null) {
            parentFrame.aggiornaCorsiInsieme(corsiInsiemeSelezionati); // Always update parent with current selection
            parentFrame.refreshUI(); // Ensure parent UI is refreshed
        }
    }

}