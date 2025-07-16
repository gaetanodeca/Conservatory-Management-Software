package boundary.docenteboundary;

import control.ControllerVerbale;
import dto.DataDTO;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;

public class InterfacciaCreaVerbale extends JFrame {
/**
 * Finestra per la creazione di un verbale.
 * <p>
 * L'interfaccia consente l'inserimento della matricola del docente e della data
 * per creare un nuovo verbale. Permette inoltre la selezione di un corso e
 * l'inserimento degli esami associati.
 */

    private static final long serialVersionUID = 1L;
    private JTextField txtData;
    private JTextField txtMatricola;
    private JLabel lblRisultato;
    private JLabel lblCorsoScelto;
    private JButton btnRegistraEsami;
    private int id_verbale;
    private List<String> lista_corsi;

    /**
     * Costruttore che inizializza e visualizza l'interfaccia per la creazione del verbale.
     */

    public InterfacciaCreaVerbale() {
        setTitle("Creazione Verbale");
        setSize(550, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Inserisci Dati Verbale");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(25, 25, 112));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        contentPane.add(lblTitle, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 1;

        JLabel lblMatricola = new JLabel("Matricola Docente:");
        lblMatricola.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(lblMatricola, gbc);

        txtMatricola = new JTextField(20);
        txtMatricola.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(txtMatricola, gbc);

        JLabel lblData = new JLabel("Data:");
        lblData.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(lblData, gbc);

        txtData = new JTextField(20);
        txtData.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPane.add(txtData, gbc);

        JButton btnConferma = new JButton("Conferma");
        styleButton(btnConferma);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(btnConferma, gbc);

        lblRisultato = new JLabel("");
        lblRisultato.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblRisultato.setForeground(new Color(0, 102, 51));
        gbc.gridy = 5;
        contentPane.add(lblRisultato, gbc);

        lblCorsoScelto = new JLabel("Nessun corso selezionato");
        lblCorsoScelto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCorsoScelto.setForeground(Color.DARK_GRAY);
        gbc.gridy = 6;
        contentPane.add(lblCorsoScelto, gbc);

        btnRegistraEsami = new JButton("Registra Esami");
        styleButton(btnRegistraEsami);
        btnRegistraEsami.setEnabled(false);
        gbc.gridy = 7;
        contentPane.add(btnRegistraEsami, gbc);

        btnRegistraEsami.addActionListener(e -> {
            InterfacciaInserisciEsami esameFrame = new InterfacciaInserisciEsami(id_verbale);
            esameFrame.setVisible(true);
            dispose();
        });

        JButton btnIndietro = new JButton("Indietro");
        btnIndietro.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnIndietro.setBackground(new Color(220, 20, 60)); // rosso scuro
        btnIndietro.setForeground(Color.WHITE);
        btnIndietro.setFocusPainted(false);
        gbc.gridy = 8;
        contentPane.add(btnIndietro, gbc);

        btnIndietro.addActionListener(e -> {
            new InterfacciaDocente().setVisible(true);
            dispose();
        });


        btnConferma.addActionListener(e -> {
            String data = txtData.getText().trim();
            String matricola = txtMatricola.getText().trim();
        
            ControllerVerbale ctrl = ControllerVerbale.getInstance();
        
            if (!controllaCampi(data, matricola)) {
                return;
            }
        
            DataDTO datas = ctrl.InserisciVerbale(data, matricola);
        
            if (datas == null) {
                JOptionPane.showMessageDialog(this,
                    "Si è verificato un errore inatteso.",
                    "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            id_verbale = datas.getId_verbale();
            lista_corsi = datas.getCorsi();
        
            if (id_verbale == -1) {
                JOptionPane.showMessageDialog(this,
                    "Matricola Docente non trovata.",
                    "Errore", JOptionPane.ERROR_MESSAGE);
                txtMatricola.setText("");
                return;
            }
        
            if (id_verbale == -2) {
                JOptionPane.showMessageDialog(this,
                    "Il docente non è mai stato assegnato a nessun corso.",
                    "Errore", JOptionPane.ERROR_MESSAGE);
                txtMatricola.setText("");
                return;
            }
        
            if (lista_corsi == null || lista_corsi.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Non ci sono corsi associati al docente per quell'anno accademico.",
                    "Errore", JOptionPane.ERROR_MESSAGE);
                txtMatricola.setText("");
                return;
            }
        
            lblRisultato.setText("Verbale creato correttamente: " + data);
            btnRegistraEsami.setEnabled(true);
        
            InterfacciaSceltaCorso scelta = new InterfacciaSceltaCorso(datas, new CorsoSelectionListener() {
                @Override
                public void onCorsoSelected(String denominazioneCorso) {
                    lblCorsoScelto.setText("Hai scelto: " + denominazioneCorso);
                }
            });
        
            scelta.setVisible(true);
        });
    }

    /**
     * Verifica che la stringa della data inserita sia nel formato corretto e rappresenti una data valida.
     *
     * @param data la data in formato stringa da validare
     * @return {@code true} se la data è valida, altrimenti {@code false}
     */

     private boolean controllaCampi(String data, String matricola) {
        StringBuilder errori = new StringBuilder();
        
        if (matricola.isEmpty()) {
            errori.append("Il campo matricola è obbligatorio.\n");
            txtMatricola.setText("");
        } else if (!matricola.matches("D\\d{3}")) {
            errori.append("Formato matricola non valido. Deve essere nel formato D seguito da 3 cifre (es: D123).\n");
            txtMatricola.setText("");}

        if (data.isEmpty()) {
            errori.append("Il campo data è obbligatorio.\n");
            txtData.setText("");
        } else if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
            errori.append("Formato data inserita non valido: usa gg/mm/aaaa (es. 25/12/2025).\n");
            txtData.setText("");
        } else if (!isDataValida(data)) {
            errori.append("Non possiamo ancora predirre il futuro.\n");
            txtData.setText("");
        }
    
        if (errori.length() > 0) {
            JOptionPane.showMessageDialog(this,
                    errori.toString(),
                    "Errore!",
                    JOptionPane.ERROR_MESSAGE);
                    
            return false;
        }
    
        return true;
    }
    
    private boolean isDataValida(String data) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date dataInserita = sdf.parse(data);
            Date oggi = new Date();
    
            if(dataInserita.after(oggi)) {
                return false;
            }
    
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    /**
     * Applica uno stile personalizzato a un pulsante Swing.
     *
     * @param button il pulsante da stilizzare
     */
    
    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }
}