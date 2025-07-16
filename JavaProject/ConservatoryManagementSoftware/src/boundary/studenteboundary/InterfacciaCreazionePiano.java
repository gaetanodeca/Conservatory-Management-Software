package boundary.studenteboundary;

import control.ControllerCorso;
import entity.CorsoDiStudio;
import entity.FacadeConservatorio;
import entity.Studente;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder; // Import added for BorderFactory

/**
 * <p>
 * Interfaccia grafica che consente allo studente di creare e gestire il proprio piano di studi.
 * Permette la selezione di corsi da diverse categorie (Canto, Composizione, Strumento, Didattica, Musica d'Insieme),
 * la visualizzazione dello stato attuale del piano (CFU totali, numero corsi), la rimozione di corsi,
 * e la conferma finale del piano di studi.
 * </p>
 * <p>
 * L'interfaccia si aggiorna dinamicamente per mostrare il totale dei CFU e il numero dei corsi selezionati.
 * Un bottone di conferma diventa visibile solo quando il totale dei CFU raggiunge i 180.
 * </p>
 */
public class InterfacciaCreazionePiano extends JFrame {

	private static final long serialVersionUID = 1L;

	/** Pannello principale della finestra. */
	private JPanel contentPane;

	/** Controller per la gestione logica dei corsi e del piano di studi. */
	private ControllerCorso controller;

	/** Bottone per concludere e salvare il piano di studi. */
	private JButton btnConcludi;

	/** Lista totale di tutti i corsi selezionati per il piano di studi. */
	private List<CorsoDiStudio> corsiTotale;

	/** Etichetta che mostra il contatore dei CFU e il numero di corsi. */
	private JLabel lblCFUCounter;

	/** Campo di testo per l'inserimento del nome del piano di studi. */
	private JTextField txtNomePiano; 
	
	/** Lista dei corsi di Canto selezionati dall'utente. */
	private List<CorsoDiStudio> corsiCantoSelezionati = new ArrayList<>();
	/** Lista dei corsi di Composizione selezionati dall'utente. */
	private List<CorsoDiStudio> corsiComposizioneSelezionati = new ArrayList<>();
	/** Lista dei corsi di Strumento selezionati dall'utente. */
	private List<CorsoDiStudio> corsiStrumentoSelezionati = new ArrayList<>();
	/** Lista dei corsi di Didattica della Musica selezionati dall'utente. */
	private List<CorsoDiStudio> corsiDidatticaSelezionati = new ArrayList<>();
	/** Lista dei corsi di Musica d'Insieme selezionati dall'utente. */
	private List<CorsoDiStudio> corsiInsiemeSelezionati = new ArrayList<>();

	/** L'oggetto Studente associato a questa interfaccia. */
	private Studente studente;

	/**
	 * Costruttore dell'interfaccia `InterfacciaCreazionePiano`.
	 * Inizializza l'interfaccia grafica e i componenti, impostando i listener
	 * per i bottoni e avviando un timer per l'aggiornamento dinamico della UI.
	 * * @param studente L'oggetto {@link Studente} per cui viene creato il piano di studi.
	 */
	public InterfacciaCreazionePiano(Studente studente) {
		this.studente = studente;

		// Timer per aggiornare la UI ogni 10 millisecondi
		javax.swing.Timer timerAggiornamento = new javax.swing.Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateUI();
			}
		});
		timerAggiornamento.start();
		
		corsiTotale = new ArrayList<>();
		controller = new ControllerCorso(FacadeConservatorio.getInstance());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 520);
		setLocationRelativeTo(null); 
        setTitle("Creazione Piano di Studi"); // Aggiunto un titolo alla finestra

		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255)); // Colore di sfondo coerente
		contentPane.setBorder(new EmptyBorder(25, 25, 25, 25)); // Padding coerente
		setContentPane(contentPane);
		contentPane.setLayout(null); 

		JLabel lblTitle = new JLabel("Seleziona il tipo di corso che vuoi aggiungere");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22)); // Font più grande e prominente
		lblTitle.setForeground(new Color(25, 25, 112)); // Colore titolo coerente
		lblTitle.setBounds(35, 20, 450, 30);
		contentPane.add(lblTitle);
		
		// Contatore CFU - centrale e più visibile
		lblCFUCounter = new JLabel("CFU totali: 0/180 (0 corsi)", JLabel.CENTER);
		lblCFUCounter.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblCFUCounter.setForeground(new Color(0, 102, 204)); 
		lblCFUCounter.setBounds(50, 65, 420, 35); 
		lblCFUCounter.setBorder(BorderFactory.createCompoundBorder( // Utilizzo BorderFactory
			new LineBorder(new Color(200, 200, 200), 1), // Bordo semplice
			BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding interno
		));
		lblCFUCounter.setOpaque(true);
		lblCFUCounter.setBackground(new Color(248, 252, 255)); // Sfondo leggermente diverso per contrasto
		contentPane.add(lblCFUCounter);
	
		// Campo per l'inserimento del nome del piano di studi
		JLabel lblNomePiano = new JLabel("Nome del piano di studi:");
		lblNomePiano.setFont(new Font("Segoe UI", Font.PLAIN, 15)); // Font coerente per il testo generale
		lblNomePiano.setForeground(new Color(25, 25, 112)); // Colore testo coerente
		lblNomePiano.setBounds(35, 115, 200, 25); 
		contentPane.add(lblNomePiano);

		txtNomePiano = new JTextField();
		txtNomePiano.setFont(new Font("Segoe UI", Font.PLAIN, 15)); 
		txtNomePiano.setBounds(35, 145, 400, 35); 
		txtNomePiano.setBorder(new LineBorder(new Color(180, 180, 180), 1)); // Bordo sottile
		contentPane.add(txtNomePiano);
		txtNomePiano.setColumns(10);
		
		// Bottone per visualizzare i corsi selezionati
		JButton btnViewCorsi = new JButton("Corsi selezionati");
		btnViewCorsi.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Font leggermente più spesso
		btnViewCorsi.setBackground(new Color(255, 215, 0)); // Colore bottone standard (oro)
		btnViewCorsi.setForeground(Color.WHITE);
		btnViewCorsi.setFocusPainted(false);
		btnViewCorsi.setBounds(320, 190, 180, 38); // Bottone più alto
		btnViewCorsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraCorsiSelezionati();
			}
		});
		contentPane.add(btnViewCorsi);
		
		// Bottone per rimuovere corsi
		JButton btnRimuoviCorsi = new JButton("Rimuovi corsi");
		btnRimuoviCorsi.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnRimuoviCorsi.setBackground(new Color(220, 20, 60)); // Colore bottone di "logout" (rosso) coerente
		btnRimuoviCorsi.setForeground(Color.WHITE);
		btnRimuoviCorsi.setFocusPainted(false);
		btnRimuoviCorsi.setBounds(320, 238, 180, 38); 
		btnRimuoviCorsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Passa l'istanza di InterfacciaCreazionePiano come parentFrame
				InterfacciaRimozione frame = new InterfacciaRimozione(studente, InterfacciaCreazionePiano.this);
				frame.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnRimuoviCorsi);
		
		// Bottone Canto
		JButton btnCanto = new JButton("Canto");
		btnCanto.setFont(new Font("Segoe UI", Font.BOLD, 15)); // Font bottone generale
		btnCanto.setBackground(new Color(70, 130, 180)); // Colore bottone standard
		btnCanto.setForeground(Color.WHITE);
		btnCanto.setFocusPainted(false);
		btnCanto.setBounds(35, 190, 165, 45); // Dimensione coerente
		btnCanto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfacciaCanto frameCa = new InterfacciaCanto(InterfacciaCreazionePiano.this);
				frameCa.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnCanto);
		
		// Bottone Composizione
		JButton btnComposizione = new JButton("Composizione");
		btnComposizione.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnComposizione.setBackground(new Color(70, 130, 180));
		btnComposizione.setForeground(Color.WHITE);
		btnComposizione.setFocusPainted(false);
		btnComposizione.setBounds(35, 245, 165, 45);
		btnComposizione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfacciaComposizione frameCo = new InterfacciaComposizione(InterfacciaCreazionePiano.this);
				frameCo.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnComposizione);
		
		// Bottone Strumento
		JButton btnStrumento = new JButton("Strumento");
		btnStrumento.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnStrumento.setBackground(new Color(70, 130, 180));
		btnStrumento.setForeground(Color.WHITE);
		btnStrumento.setFocusPainted(false);
		btnStrumento.setBounds(35, 300, 165, 45);
		btnStrumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfacciaStrumento frameS = new InterfacciaStrumento(InterfacciaCreazionePiano.this);
				frameS.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnStrumento);
		
		// Bottone Musica d'Insieme
		JButton btnMusicaDinsieme = new JButton("Musica d'insieme");
		btnMusicaDinsieme.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnMusicaDinsieme.setBackground(new Color(70, 130, 180));
		btnMusicaDinsieme.setForeground(Color.WHITE);
		btnMusicaDinsieme.setFocusPainted(false);
		btnMusicaDinsieme.setBounds(35, 355, 165, 45);
		btnMusicaDinsieme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfacciaInsieme frameI = new InterfacciaInsieme(InterfacciaCreazionePiano.this);
				frameI.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnMusicaDinsieme);
		
		// Bottone Didattica
		JButton btnDidatticaDellaMusica = new JButton("Didattica");
		btnDidatticaDellaMusica.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnDidatticaDellaMusica.setBackground(new Color(70, 130, 180));
		btnDidatticaDellaMusica.setForeground(Color.WHITE);
		btnDidatticaDellaMusica.setFocusPainted(false);
		btnDidatticaDellaMusica.setBounds(35, 410, 165, 45);
		btnDidatticaDellaMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfacciaDidattica frameD = new InterfacciaDidattica(InterfacciaCreazionePiano.this);
				frameD.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnDidatticaDellaMusica);

		// Bottone per confermare il piano di studi
		JButton btnConcludi = new JButton("Conferma piano");
		btnConcludi.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Font bottone coerente
		btnConcludi.setBackground(new Color(60, 179, 113)); // Usa il colore primario del bottone
		btnConcludi.setForeground(Color.WHITE);
		btnConcludi.setFocusPainted(false);
		btnConcludi.setBounds(300, 395, 190, 60); 
		btnConcludi.setVisible(false); // Inizialmente non visibile
		btnConcludi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomePiano = txtNomePiano.getText().trim();
				if (nomePiano.isEmpty()) {
					javax.swing.JOptionPane.showMessageDialog(null,
						"Per favore, inserisci un nome per il piano di studi!",
						"Nome Piano Richiesto",
						javax.swing.JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String messaggioConferma = getMessaggioConfermaUI();
				int result = javax.swing.JOptionPane.showConfirmDialog(null,
					messaggioConferma,
					"Conferma Piano",
					javax.swing.JOptionPane.YES_NO_OPTION);
				
				if (result == javax.swing.JOptionPane.YES_OPTION) {
					// Genera un ID per il piano (o recuperalo da un sistema di gestione ID)
					int idPiano = (int) System.currentTimeMillis(); 
					
					boolean pianoCreato = controller.creaNuovoPianoDiStudi(idPiano, nomePiano);
					
					// Prepara la lista completa dei corsi selezionati da tutte le categorie
					corsiTotale.clear();
					corsiTotale.addAll(corsiCantoSelezionati);
					corsiTotale.addAll(corsiComposizioneSelezionati);
					corsiTotale.addAll(corsiStrumentoSelezionati);
					corsiTotale.addAll(corsiDidatticaSelezionati);
					corsiTotale.addAll(corsiInsiemeSelezionati);
					
					System.out.println("Totale corsi da salvare: " + corsiTotale.size());

					// Salva il piano di studi completo con tutti i corsi selezionati
					controller.salvaPianoDiStudiCompleto(idPiano, corsiTotale);
					
					if (pianoCreato) {
						javax.swing.JOptionPane.showMessageDialog(null,
							"Piano di studi '" + nomePiano + "' creato con successo!\n" + getMessaggioSuccesso());
					} else {
						javax.swing.JOptionPane.showMessageDialog(null,
							"Errore durante la creazione del piano di studi. Riprova!",
							"Errore",
							javax.swing.JOptionPane.ERROR_MESSAGE);
					}
					
				dispose();
				}
			}
		});
		this.btnConcludi = btnConcludi; // Assegna il bottone all'attributo di classe
		contentPane.add(btnConcludi);
		
		// Bottone Indietro
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Font bottone coerente
		btnIndietro.setBackground(new Color(220, 20, 60)); // Colore bottone di "logout" (rosso) coerente
		btnIndietro.setForeground(Color.WHITE);
		btnIndietro.setFocusPainted(false);
		btnIndietro.setBounds(320, 286, 180, 38); // Posizione aggiustata sotto il bottone "Rimuovi corsi"
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Torna all'InterfacciaStudente
				InterfacciaStudente interfacciaStudente = new InterfacciaStudente(studente);
				interfacciaStudente.setVisible(true);
				dispose(); // Chiude la finestra corrente
			}
		});
		contentPane.add(btnIndietro);

		updateUI(); // Aggiorna la UI iniziale
	}
	
	/**
	 * Aggiorna dinamicamente l'interfaccia utente.
	 * Questo metodo viene chiamato periodicamente da un timer e anche dopo azioni dell'utente
	 * per riflettere lo stato corrente del piano di studi (CFU, visibilità del bottone di conferma).
	 */
	private void updateUI() {
		lblCFUCounter.setText(getStatoPianoPerUI());
		
		if (isBottoneConfermaVisibile()) {
			btnConcludi.setVisible(true);
			btnConcludi.setText(getTestoBottoneConferma());
		} else {
			btnConcludi.setVisible(false);
		}
		
		lblCFUCounter.setForeground(getColoreContatoreCFU());
		
		repaint(); // Richiede un ridisegno dei componenti
	}
	
	/**
	 * Mostra un dialog box con un riepilogo dei corsi attualmente selezionati nel piano di studi.
	 */
	private void mostraCorsiSelezionati() {
		String messaggio = getMessaggioCorsiSelezionati();
		if (controller.getNumeroCorsi() == 0) {
			javax.swing.JOptionPane.showMessageDialog(this, messaggio);
		} else {
			javax.swing.JOptionPane.showMessageDialog(this, messaggio,
				"Corsi Selezionati", javax.swing.JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Forza un aggiornamento della UI chiamando il metodo {@code updateUI()}.
	 * Utile per le finestre figlie che hanno modificato lo stato del piano di studi.
	 */
	public void refreshUI() {
		updateUI();
	}
	
	/**
	 * Genera il messaggio da visualizzare nel dialog box che riassume i corsi selezionati.
	 * * @return Una stringa contenente il riepilogo dei corsi o un messaggio di assenza corsi.
	 */
	private String getMessaggioCorsiSelezionati() {
		if (controller.getNumeroCorsi() == 0) {
			return "Nessun corso selezionato ancora.\nSeleziona dei corsi dalle diverse categorie!";
		} else {
			return getSommarioPiano();
		}
	}
	
	/**
	 * Genera il messaggio di conferma da mostrare all'utente prima di salvare il piano di studi.
	 * Include il sommario del piano e una richiesta di conferma.
	 * * @return Una stringa per il messaggio di conferma del piano.
	 */
	private String getMessaggioConfermaUI() {
		return getSommarioPiano() + "\n\nVuoi confermare il piano di studi?";
	}
	
	/**
	 * Genera il messaggio di successo da mostrare dopo che il piano di studi è stato confermato e salvato.
	 * * @return Una stringa di conferma successo.
	 */
	private String getMessaggioSuccesso() {
		return "Piano di studi confermato con successo!";
	}
	
	/**
	 * Genera la stringa che mostra lo stato attuale dei CFU e il numero di corsi selezionati
	 * per l'etichetta del contatore nella UI.
	 * * @return Una stringa formattata con il totale CFU e il numero di corsi.
	 */
	private String getStatoPianoPerUI() {
		int totaleCFU = controller.getTotaleCFU();
		int numeroCorsi = controller.getNumeroCorsi();
		return "CFU totali: " + totaleCFU + "/180 (" + numeroCorsi + " corsi)";
	}
	
	/**
	 * Determina il testo da visualizzare sul bottone di conferma del piano di studi.
	 * Se i CFU totali sono esattamente 180, aggiunge questa informazione al testo.
	 * * @return Il testo per il bottone di conferma.
	 */
	private String getTestoBottoneConferma() {
		if (controller.getTotaleCFU() == 180) {
			return "Conferma piano";
		}
		return "Conferma piano";
	}
	
	/**
	 * Verifica se il bottone per confermare il piano di studi dovrebbe essere visibile.
	 * Diventa visibile solo quando il totale dei CFU è esattamente 180.
	 * * @return {@code true} se il bottone di conferma deve essere visibile, {@code false} altrimenti.
	 */
	private boolean isBottoneConfermaVisibile() {
		return controller.getTotaleCFU() == 180;
	}
	
	/**
	 * Genera un riepilogo dettagliato del piano di studi corrente, inclusi
	 * il numero totale di corsi, i CFU totali e rimanenti, e lo stato di validità del piano.
	 * Se ci sono corsi selezionati, elenca anche i dettagli di ciascun corso.
	 * * @return Una stringa contenente il sommario completo del piano di studi.
	 */
	private String getSommarioPiano() {
		StringBuilder sb = new StringBuilder();
		sb.append("Piano di Studi\n");
		sb.append("Numero corsi selezionati: ").append(controller.getNumeroCorsi()).append("\n");
		sb.append("Totale CFU: ").append(controller.getTotaleCFU()).append("/180\n");
		sb.append("CFU rimanenti: ").append(Math.max(0, 180 - controller.getTotaleCFU())).append("\n");
		sb.append("Piano valido: ").append(controller.getTotaleCFU() == 180 ? "Sì" : "No").append("\n\n");
		
		if (controller.getNumeroCorsi() > 0) {
			sb.append("Dettagli corsi:\n");
			// Assumiamo che controller.mostraCorsiSelezionati() restituisca una stringa formattata dei corsi
			sb.append(controller.mostraCorsiSelezionati()); 
		} else {
			sb.append("Nessun corso selezionato.\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * Determina il colore del contatore CFU in base al totale dei CFU.
	 * Rosso se i CFU sono meno di 180, verde se esattamente 180, arancione se superano 180.
	 * * @return Un oggetto {@link Color} per il contatore CFU.
	 */
	private java.awt.Color getColoreContatoreCFU() {
		int totaleCFU = controller.getTotaleCFU();
		if (totaleCFU < 180) {
			return new java.awt.Color(255, 0, 0); // Rosso
		} else if (totaleCFU == 180) {
			return new java.awt.Color(0, 128, 0); // Verde
		} else {
			return new java.awt.Color(255, 165, 0); // Arancione
		}
	}
	
	// Metodi callback per ricevere i corsi selezionati dalle diverse interfacce
		public void aggiornaCorsiCanto(List<CorsoDiStudio> corsiSelezionati) {
			this.corsiCantoSelezionati.addAll(corsiSelezionati);
			System.out.println("Ricevuti " + corsiSelezionati.size() + " corsi di Canto");
			sincronizzaController();
		}
		
		public void aggiornaCorsiComposizione(List<CorsoDiStudio> corsiSelezionati) { 
			this.corsiComposizioneSelezionati.addAll(corsiSelezionati);
			System.out.println("Ricevuti " + corsiSelezionati.size() + " corsi di Composizione");
			sincronizzaController();
		}
		
		public void aggiornaCorsiStrumento(List<CorsoDiStudio> corsiSelezionati) {
			this.corsiStrumentoSelezionati.addAll(corsiSelezionati);
			System.out.println("Ricevuti " + corsiSelezionati.size() + " corsi di Strumento");
			sincronizzaController();
		}
		
		public void aggiornaCorsiDidattica(List<CorsoDiStudio> corsiSelezionati) {
			this.corsiDidatticaSelezionati.addAll(corsiSelezionati);
			System.out.println("Ricevuti " + corsiSelezionati.size() + " corsi di Didattica");
			sincronizzaController();
		}
		
		public void aggiornaCorsiInsieme(List<CorsoDiStudio> corsiSelezionati) {
			this.corsiInsiemeSelezionati.addAll(corsiSelezionati);
			System.out.println("Ricevuti " + corsiSelezionati.size() + " corsi di Musica d'Insieme");
			sincronizzaController();
		}
		
		// Metodo per sincronizzare il controller con le liste locali
		public void sincronizzaController() {
			// Svuota il controller e ricarica tutti i corsi
			controller.svuotaPianoStudi();
			
			// Aggiungi tutti i corsi delle diverse categorie
			for (CorsoDiStudio corso : corsiCantoSelezionati) {
				controller.aggiungiCorso(corso);
			}
			for (CorsoDiStudio corso : corsiComposizioneSelezionati) {
				controller.aggiungiCorso(corso);
			}
			for (CorsoDiStudio corso : corsiStrumentoSelezionati) {
				controller.aggiungiCorso(corso);
			}
			for (CorsoDiStudio corso : corsiDidatticaSelezionati) {
				controller.aggiungiCorso(corso);
			}
			for (CorsoDiStudio corso : corsiInsiemeSelezionati) {
				controller.aggiungiCorso(corso);
			}
		}
		
		// Metodo per rimuovere un corso specifico dalle liste locali
		public void rimuoviCorsoDalleListeLocali(CorsoDiStudio corso) {
			corsiCantoSelezionati.remove(corso);
			corsiComposizioneSelezionati.remove(corso);
			corsiStrumentoSelezionati.remove(corso);
			corsiDidatticaSelezionati.remove(corso);
			corsiInsiemeSelezionati.remove(corso);
			sincronizzaController();
		}
		
		// Metodo per svuotare tutte le liste locali
		public void svuotaTutteLeListeLocali() {
			corsiCantoSelezionati.clear();
			corsiComposizioneSelezionati.clear();
			corsiStrumentoSelezionati.clear();
			corsiDidatticaSelezionati.clear();
			corsiInsiemeSelezionati.clear();
			controller.svuotaPianoStudi();
		}

		// Metodi getter per accedere alle liste locali dei corsi
		public List<CorsoDiStudio> getCorsiCantoSelezionati() {
			return corsiCantoSelezionati;
		}
		
		public List<CorsoDiStudio> getCorsiComposizioneSelezionati() {
			return corsiComposizioneSelezionati;
		}
		
		public List<CorsoDiStudio> getCorsiStrumentoSelezionati() {
			return corsiStrumentoSelezionati;
		}
		
		public List<CorsoDiStudio> getCorsiDidatticaSelezionati() {
			return corsiDidatticaSelezionati;
		}
		
		public List<CorsoDiStudio> getCorsiInsiemeSelezionati() {
			return corsiInsiemeSelezionati;
		}
	
	
}