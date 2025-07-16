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
import javax.swing.BorderFactory; // Import for BorderFactory

/**
 * Interfaccia grafica che consente allo studente di selezionare corsi di Strumento
 * da aggiungere al proprio piano di studi. I corsi disponibili sono caricati dal database
 * tramite il controller.
 */
public class InterfacciaStrumento extends JFrame {

	private static final long serialVersionUID = 1L;

	/** Pannello principale dell'interfaccia. */
	private JPanel contentPane;

	/** Controller per la gestione dei corsi. */
	private ControllerCorso controller;

	/** Lista dei corsi di strumento disponibili. */
	private List<CorsoDiStudio> corsiStrumento;

	/** Lista temporanea dei corsi selezionati. */
	private List<CorsoDiStudio> corsiStrumentoSelezionati = new ArrayList<>();

	/** Lista dei checkbox associati ai corsi. */
	private List<JCheckBox> checkBoxes;

	/** Riferimento alla finestra chiamante (InterfacciaCreazionePiano). */
	private InterfacciaCreazionePiano parentFrame;

	/**
	 * Costruttore di default. Richiama il costruttore con parametro null.
	 */
	public InterfacciaStrumento() {
		this(null);
	}

	/**
	 * Costruttore con riferimento alla finestra padre.
	 * @param parent la finestra chiamante InterfacciaCreazionePiano
	 */
	public InterfacciaStrumento(InterfacciaCreazionePiano parent) {
		this.parentFrame = parent;

		controller = new ControllerCorso(FacadeConservatorio.getInstance());
		checkBoxes = new ArrayList<>();

		// Recupera i corsi di strumento dal database
		corsiStrumento = controller.getCorsiPerStrumento();

		// Altezza dinamica in base al numero di corsi
		int altezzaFinestra = Math.max(350, 200 + (corsiStrumento.size() * 30) + 80);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, altezzaFinestra);
		setLocationRelativeTo(null);
		setTitle("Corsi di Strumento"); // Added title for clarity

		contentPane = new JPanel();
		// Match InterfacciaStudente background color
		contentPane.setBackground(new Color(240, 248, 255)); 
		// Match InterfacciaStudente padding/border
		contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Titolo
		JLabel lblTitle = new JLabel("Corsi di Strumento Disponibili");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Larger font for title
		lblTitle.setForeground(new Color(25, 25, 112)); // Match InterfacciaStudente title color
		lblTitle.setBounds(0, 20, 520, 30);
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(lblTitle);

		// Crea dinamicamente i checkbox per i corsi
		int yPosition = 80;
		for (int i = 0; i < corsiStrumento.size(); i++) {
			CorsoDiStudio corso = corsiStrumento.get(i);

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

		// Bottone "Aggiungi alla Lista"
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

		// Bottone "Indietro"
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
	 * Aggiunge i corsi selezionati alla lista temporanea `corsiStrumentoSelezionati`,
	 * li invia al controller e aggiorna la finestra padre se presente.
	 */
	private void aggiungiCorsiSelezionatiAllaLista() {
		int corsiAggiunti = 0;
		corsiStrumentoSelezionati.clear(); // Clear previous selections to prevent duplicates on multiple clicks

		for (int i = 0; i < checkBoxes.size(); i++) {
            JCheckBox checkbox = checkBoxes.get(i);
            CorsoDiStudio corso = corsiStrumento.get(i);

			if (checkbox.isSelected()) {
                corsiStrumentoSelezionati.add(corso); // Add to local list first
				boolean aggiunto = controller.aggiungiCorsoSelezionato(
					corso.getCFU(), corso.getCodice(), corso.getDenominazione());
				if (aggiunto) {
					corsiAggiunti++;
				} else {
                    corsiStrumentoSelezionati.remove(corso); // If not added by controller (e.g., due to CFU limit), remove from our local selection
                }
			}
		}

		String messaggio = corsiAggiunti > 0
			? "Aggiunti " + corsiAggiunti + " corsi di Strumento."
			: "Nessun nuovo corso selezionato, o i corsi selezionati superano il limite CFU."; // More informative message
		javax.swing.JOptionPane.showMessageDialog(this, messaggio);

		if (parentFrame != null) {
			parentFrame.aggiornaCorsiStrumento(corsiStrumentoSelezionati); // Always update parent with current selection
            parentFrame.refreshUI(); // Ensure parent UI is refreshed
		}
	}

}