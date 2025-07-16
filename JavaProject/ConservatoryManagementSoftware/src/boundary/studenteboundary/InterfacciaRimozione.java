package boundary.studenteboundary; // Changed package to studenteboundary for consistency

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory; // Import added for BorderFactory

import control.ControllerCorso;
import entity.CorsoDiStudio;
import entity.FacadeConservatorio;
import entity.Studente; // Import Studente class

/**
 * <p>
 * Interfaccia grafica che permette allo studente di visualizzare e rimuovere
 * corsi dal proprio piano di studi provvisorio.
 * I corsi possono essere rimossi singolarmente o tutti in una volta.
 * </p>
 * <p>
 * L'interfaccia interagisce con un {@link ControllerCorso} per la gestione dei dati
 * e, se disponibile, con un {@link InterfacciaCreazionePiano} parent per aggiornare
 * le liste di corsi in tempo reale.
 * </p>
 */
public class InterfacciaRimozione extends JFrame {

	private static final long serialVersionUID = 1L;

	/** Pannello principale della finestra. */
	private JPanel contentPane;

	/** Controller per la gestione logica dei corsi. */
	private ControllerCorso controller;

	/** Lista di {@link JCheckBox} associati a ciascun corso visualizzato. */
	private List<JCheckBox> checkBoxList;

	/** Pannello scrollabile che contiene i checkbox dei corsi. */
	private JPanel corsiPanel;

	/** L'oggetto {@link Studente} associato all'operazione di rimozione. */
	private Studente studente;

	/** Riferimento alla finestra padre {@link InterfacciaCreazionePiano}, se presente. */
	private InterfacciaCreazionePiano parentFrame;

	/**
	 * Costruttore dell'interfaccia `InterfacciaRimozione`.
	 * Inizializza la finestra, il controller, e carica i corsi selezionati
	 * dello studente per la rimozione. Imposta anche il Look and Feel per coerenza.
	 * * @param studente L'oggetto {@link Studente} di cui si sta gestendo il piano di studi.
	 * @param parentFrame Un riferimento alla {@link InterfacciaCreazionePiano} che ha chiamato questa finestra.
	 * Può essere {@code null} se la finestra viene creata indipendentemente.
	 */
	public InterfacciaRimozione(Studente studente, InterfacciaCreazionePiano parentFrame) {
		this.studente = studente;
		this.parentFrame = parentFrame;

		// Inizializza il controller e la lista dei checkbox
		controller = new ControllerCorso(FacadeConservatorio.getInstance());
		checkBoxList = new ArrayList<>();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed to DISPOSE_ON_CLOSE for better application flow
		setBounds(100, 100, 600, 480); // Adjusted height for better spacing
		setLocationRelativeTo(null); 
		setTitle("Rimuovi Corsi"); // Added title for clarity

		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255)); // Match consistent background color
		contentPane.setBorder(new EmptyBorder(25, 25, 25, 25)); // Consistent padding
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Titolo della finestra
		JLabel lblTitle = new JLabel("Rimuovi corsi dal piano di studi");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Larger font for title
		lblTitle.setForeground(new Color(25, 25, 112)); // Match main title color
		lblTitle.setBounds(0, 20, 600, 30);
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(lblTitle);
		
		// Etichetta informativa per l'utente
		JLabel lblInfo = new JLabel("Seleziona i corsi che vuoi rimuovere:");
		lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 15)); // Consistent text font
		lblInfo.setForeground(new Color(50, 50, 50)); // Slightly darker for readability
		lblInfo.setBounds(30, 65, 350, 25); // Adjusted position
		contentPane.add(lblInfo);
		
		// Pannello per la lista dei corsi (con scroll)
		corsiPanel = new JPanel();
		corsiPanel.setBackground(Color.WHITE);
		corsiPanel.setLayout(null); // Use null layout for precise positioning of checkboxes
		corsiPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200))); // Add a subtle border

		JScrollPane scrollPane = new JScrollPane(corsiPanel);
		scrollPane.setBounds(30, 100, 540, 250); // Adjusted position and width
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);
		
		// Carica dinamicamente i corsi selezionati all'avvio
		caricaCorsiSelezionati();
		
		// Pulsante "Rimuovi corsi selezionati"
		JButton btnRimuovi = new JButton("Rimuovi selezionati"); // Shorter text
		btnRimuovi.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Consistent button font
		btnRimuovi.setBackground(new Color(220, 20, 60)); // Consistent danger color
		btnRimuovi.setForeground(Color.WHITE);
		btnRimuovi.setBounds(30, 370, 180, 45); // Adjusted size and position
		btnRimuovi.setFocusPainted(false);
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rimuoviCorsiSelezionati();
			}
		});
		contentPane.add(btnRimuovi);
		
		// Pulsante "Rimuovi tutto"
		JButton btnRimuoviTutto = new JButton("Rimuovi tutto");
		btnRimuoviTutto.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnRimuoviTutto.setBackground(new Color(255, 140, 0)); // A more distinct "danger" shade (dark orange)
		btnRimuoviTutto.setForeground(Color.WHITE);
		btnRimuoviTutto.setBounds(215, 370, 150, 45); // Adjusted size and position
		btnRimuoviTutto.setFocusPainted(false);
		btnRimuoviTutto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rimuoviTuttiICorsi();
			}
		});
		contentPane.add(btnRimuoviTutto);
		
		// Bottone "Indietro"
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Consistent button font
		btnIndietro.setBackground(new Color(70, 130, 180)); // Consistent primary action color
		btnIndietro.setForeground(Color.WHITE);
		btnIndietro.setBounds(420, 370, 150, 45); // Adjusted size and position
		btnIndietro.setFocusPainted(false);
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parentFrame != null) {
					parentFrame.setVisible(true);
					parentFrame.refreshUI(); // Ensure parent UI is refreshed
				} else {
					// Fallback: if no parent frame, create a new InterfacciaCreazionePiano
					InterfacciaCreazionePiano frame = new InterfacciaCreazionePiano(studente);
					frame.setVisible(true);
				}
				dispose(); // Chiude questa finestra
			}
		});
		contentPane.add(btnIndietro);
	}
	
	/**
	 * Carica e visualizza i corsi attualmente selezionati dallo studente nel piano di studi.
	 * I corsi vengono recuperati dal controller (o dal parent frame) e aggiunti come
	 * checkbox interattivi all'interno del pannello scrollabile {@code corsiPanel}.
	 */
	private void caricaCorsiSelezionati() {
		// Pulisce il pannello prima di ricaricare i corsi
		corsiPanel.removeAll();
		checkBoxList.clear(); // Clear the list of checkboxes as well

		List<CorsoDiStudio> corsiSelezionati = new ArrayList<>();
		
		// Se c'è un parent frame, ottiene i corsi direttamente dalle sue liste locali
		// Questo assicura che le modifiche siano sincronizzate con la finestra di creazione del piano.
		if (parentFrame != null) {
			corsiSelezionati.addAll(parentFrame.getCorsiCantoSelezionati());
			corsiSelezionati.addAll(parentFrame.getCorsiComposizioneSelezionati());
			corsiSelezionati.addAll(parentFrame.getCorsiStrumentoSelezionati());
			corsiSelezionati.addAll(parentFrame.getCorsiDidatticaSelezionati());
			corsiSelezionati.addAll(parentFrame.getCorsiInsiemeSelezionati());
		} else {
			// Fallback: se non c'è parent frame, recupera i corsi direttamente dal controller.
			corsiSelezionati = controller.getCorsiSelezionati();
		}
		
		if (corsiSelezionati.isEmpty()) {
			JLabel lblNessunCorso = new JLabel("Nessun corso selezionato nel piano di studi");
			lblNessunCorso.setFont(new Font("Segoe UI", Font.ITALIC, 16));
			lblNessunCorso.setForeground(Color.GRAY);
			lblNessunCorso.setBounds(20, 20, 500, 30); // Adjusted width
			lblNessunCorso.setHorizontalAlignment(JLabel.CENTER);
			corsiPanel.add(lblNessunCorso);
			corsiPanel.setPreferredSize(new java.awt.Dimension(540, 60)); // Match scrollpane width
			corsiPanel.revalidate();
			corsiPanel.repaint();
			return;
		}
		
		int yPosition = 15; // Starting Y position for the first checkbox
		
		for (CorsoDiStudio corso : corsiSelezionati) {
			JCheckBox checkBox = new JCheckBox(corso.getCodice() + " - " + corso.getDenominazione());
			checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			checkBox.setBounds(20, yPosition, 380, 25); // Adjusted width
			checkBox.setBackground(Color.WHITE); // Ensure checkbox background matches panel
			checkBox.putClientProperty("corso", corso); // Associa l'oggetto CorsoDiStudio al checkbox
			checkBoxList.add(checkBox);
			corsiPanel.add(checkBox);
			
			// Aggiungi etichetta per i CFU
			JLabel lblCFU = new JLabel(corso.getCFU() + " CFU");
			lblCFU.setFont(new Font("Segoe UI", Font.BOLD, 14));
			lblCFU.setForeground(new Color(0, 102, 204));
			lblCFU.setBounds(410, yPosition, 80, 25); // Adjusted position
			corsiPanel.add(lblCFU);
			
			yPosition += 30; // Spacing between checkboxes
		}
		
		// Imposta la dimensione preferita del pannello per abilitare lo scroll se necessario
		// Aggiungi un po' di spazio extra in fondo per i totali
		int preferredHeight = yPosition + 60; // Extra space for total CFU label
		corsiPanel.setPreferredSize(new java.awt.Dimension(540, preferredHeight));
		
		// Calcola CFU totali e numero corsi per l'etichetta finale
		int totaleCFU = corsiSelezionati.stream().mapToInt(CorsoDiStudio::getCFU).sum();
		int numeroCorsi = corsiSelezionati.size();
		
		// Aggiungi etichetta con informazioni totali
		JLabel lblTotale = new JLabel("Totale: " + numeroCorsi + " corsi - " + totaleCFU + " CFU");
		lblTotale.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTotale.setForeground(new Color(0, 128, 0)); // Green color for totals
		lblTotale.setBounds(20, yPosition + 20, 400, 30); // Position below the last course
		corsiPanel.add(lblTotale);
		
		// Assicurati che il pannello sia ricalcolato e ridisegnato
		corsiPanel.revalidate();
		corsiPanel.repaint();
	}
	
	/**
	 * Rimuove i corsi selezionati dall'utente.
	 * Chiede conferma all'utente prima di procedere con la rimozione.
	 * Se un {@link InterfacciaCreazionePiano} parent è presente, aggiorna le sue liste interne;
	 * altrimenti, delega la rimozione al {@link ControllerCorso}.
	 */
	private void rimuoviCorsiSelezionati() {
		List<CorsoDiStudio> corsiDaRimuovere = new ArrayList<>();
		
		// Raccoglie i corsi selezionati tramite i checkbox
		for (JCheckBox checkBox : checkBoxList) {
			if (checkBox.isSelected()) {
				CorsoDiStudio corso = (CorsoDiStudio) checkBox.getClientProperty("corso");
				corsiDaRimuovere.add(corso);
			}
		}
		
		if (corsiDaRimuovere.isEmpty()) {
			javax.swing.JOptionPane.showMessageDialog(this, 
				"Seleziona almeno un corso da rimuovere!", 
				"Nessun corso selezionato", 
				javax.swing.JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// Chiede conferma all'utente
		int result = javax.swing.JOptionPane.showConfirmDialog(this,
			"Sei sicuro di voler rimuovere " + corsiDaRimuovere.size() + " corso/i?",
			"Conferma rimozione",
			javax.swing.JOptionPane.YES_NO_OPTION);
		
		if (result == javax.swing.JOptionPane.YES_OPTION) {
			int rimossi = 0;
			
			if (parentFrame != null) {
				// Rimuove direttamente dalle liste locali del parent frame
				for (CorsoDiStudio corso : corsiDaRimuovere) {
					boolean removed = false;
					// Si assume che CorsoDiStudio abbia un equals() correttamente implementato
					// per confrontare gli oggetti in base al loro contenuto (es. codice corso).
					if (parentFrame.getCorsiCantoSelezionati().remove(corso)) removed = true;
					else if (parentFrame.getCorsiComposizioneSelezionati().remove(corso)) removed = true;
					else if (parentFrame.getCorsiStrumentoSelezionati().remove(corso)) removed = true;
					else if (parentFrame.getCorsiDidatticaSelezionati().remove(corso)) removed = true;
					else if (parentFrame.getCorsiInsiemeSelezionati().remove(corso)) removed = true;
					
					if (removed) {
						rimossi++;
					}
				}
				// Sincronizza il controller del parent frame dopo la rimozione
				parentFrame.sincronizzaController(); 
			} else {
				// Fallback: se non c'è parent frame, delega al controller principale
				for (CorsoDiStudio corso : corsiDaRimuovere) {
					if (controller.rimuoviCorso(corso)) { // Assicurati che controller.rimuoviCorso aggiorni lo stato interno
						rimossi++;
					}
				}
			}
			
			javax.swing.JOptionPane.showMessageDialog(this,
				rimossi + " corso/i rimosso/i con successo!",
				"Rimozione completata",
				javax.swing.JOptionPane.INFORMATION_MESSAGE);
			
			// Ricarica la lista per riflettere le modifiche
			caricaCorsiSelezionati();
		}
	}
	
	/**
	 * Rimuove tutti i corsi dal piano di studi.
	 * Chiede una conferma esplicita all'utente, data la natura irreversibile dell'azione.
	 * Simile a {@code rimuoviCorsiSelezionati()}, aggiorna le liste del parent frame o delega al controller.
	 */
	private void rimuoviTuttiICorsi() {
		// Verifica se ci sono corsi da rimuovere
		boolean hasCorsi = false;
		if (parentFrame != null) {
			hasCorsi = !parentFrame.getCorsiCantoSelezionati().isEmpty() ||
					   !parentFrame.getCorsiComposizioneSelezionati().isEmpty() ||
					   !parentFrame.getCorsiStrumentoSelezionati().isEmpty() ||
					   !parentFrame.getCorsiDidatticaSelezionati().isEmpty() ||
					   !parentFrame.getCorsiInsiemeSelezionati().isEmpty();
		} else {
			hasCorsi = controller.getNumeroCorsi() > 0;
		}
		
		if (!hasCorsi) {
			javax.swing.JOptionPane.showMessageDialog(this,
				"Non ci sono corsi da rimuovere!",
				"Nessun corso presente",
				javax.swing.JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		// Chiede conferma esplicita per la rimozione totale
		int result = javax.swing.JOptionPane.showConfirmDialog(this,
			"Sei sicuro di voler rimuovere TUTTI i corsi dal piano di studi?\n" +
			"Questa azione non può essere annullata!",
			"Conferma rimozione totale",
			javax.swing.JOptionPane.YES_NO_OPTION,
			javax.swing.JOptionPane.WARNING_MESSAGE);
		
		if (result == javax.swing.JOptionPane.YES_OPTION) {
			if (parentFrame != null) {
				// Svuota direttamente tutte le liste locali del parent frame
				parentFrame.getCorsiCantoSelezionati().clear();
				parentFrame.getCorsiComposizioneSelezionati().clear();
				parentFrame.getCorsiStrumentoSelezionati().clear();
				parentFrame.getCorsiDidatticaSelezionati().clear();
				parentFrame.getCorsiInsiemeSelezionati().clear();
				// Sincronizza il controller del parent frame dopo aver svuotato
				parentFrame.sincronizzaController();
			} else {
				// Fallback: se non c'è parent frame, delega al controller principale
				controller.svuotaPianoStudi(); // Assicurati che controller.svuotaPianoStudi aggiorni lo stato interno
			}
			
			javax.swing.JOptionPane.showMessageDialog(this,
				"Tutti i corsi sono stati rimossi dal piano di studi!",
				"Rimozione completata",
				javax.swing.JOptionPane.INFORMATION_MESSAGE);
			
			// Ricarica la lista per riflettere le modifiche (dovrebbe mostrare "Nessun corso")
			caricaCorsiSelezionati();
		}
	}
}