package boundary.docenteboundary;
import control.ControllerEsame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Finestra grafica per l'inserimento di un nuovo esame associato a un verbale.
 * Consente di inserire matricola studente, voto, eventuale lode e note.
 * Effettua controlli di validità sui dati e interagisce con il controller per l'inserimento.
 */

public class InterfacciaInserisciEsami extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtMatricola, txtVoto, textNote;
    private JCheckBox chckbxLODE;
    private boolean conferma = false;
    
    /**
     * Costruttore che inizializza la finestra di inserimento esame.
     * @param id_v identificativo del verbale a cui associare l'esame
     */

    public InterfacciaInserisciEsami(int id_v) {
        setTitle("Inserisci Esame");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255));
        int id_verbale = id_v;
        
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 20);

        JLabel lblTitolo = new JLabel("Inserimento Esame");
        lblTitolo.setFont(titleFont);
        lblTitolo.setForeground(new Color(25, 25, 112));
        lblTitolo.setBounds(120, 10, 200, 30);
        getContentPane().add(lblTitolo);

        int labelWidth = 120;
        int fieldWidth = 200;
        int height = 25;
        int xLabel = 30;
        int xField = 160;
        int y = 60;
        int gap = 40;

        JLabel lblMatricola = new JLabel("Matricola:");
        lblMatricola.setFont(labelFont);
        lblMatricola.setBounds(-10, 84, labelWidth, height);
        lblMatricola.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblMatricola);

        txtMatricola = new JTextField();
        txtMatricola.setFont(labelFont);
        txtMatricola.setBounds(120, 83, fieldWidth, height);
        getContentPane().add(txtMatricola);

        y += gap;

        y += gap;

        txtVoto = new JTextField();
        txtVoto.setFont(labelFont);
        txtVoto.setBounds(120, 119, 100, 25);
        getContentPane().add(txtVoto);

        y += gap;
        JLabel lblVoto = new JLabel("Voto (18–30):");
        lblVoto.setFont(labelFont);
        lblVoto.setBounds(-10, 120, labelWidth, height);
        lblVoto.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblVoto);

        y += gap;

        y += gap;
        JLabel lblNote = new JLabel("Note:");
        lblNote.setFont(labelFont);
        lblNote.setBounds(-10, 156, labelWidth, height);
        lblNote.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblNote);

        textNote = new JTextField();
        textNote.setFont(labelFont);
        textNote.setBounds(120, 155, fieldWidth, height);
        getContentPane().add(textNote);

        y += gap;
        chckbxLODE = new JCheckBox("Lode");
        chckbxLODE.setFont(labelFont);
        chckbxLODE.setBackground(new Color(240, 248, 255));
        chckbxLODE.setBounds(226, 120, 100, height);
        getContentPane().add(chckbxLODE);

        y += gap + 10;

        JButton btnConferma = new JButton("Conferma");
        styleButton(btnConferma);
        btnConferma.setBounds(10, 212, 110, 30);
        getContentPane().add(btnConferma);

        JButton btnNuovo = new JButton("Nuovo Esame");
        styleButton(btnNuovo);
        btnNuovo.setBounds(165, 214, 110, 30);
        btnNuovo.setEnabled(false);
        getContentPane().add(btnNuovo);

        JButton btnInvia = new JButton("Indietro");
        
        styleButton(btnInvia);
        btnInvia.setBounds(314, 214, 110, 30);
        getContentPane().add(btnInvia);

        btnConferma.addActionListener(e -> {
            String matricola = txtMatricola.getText().trim();
            String votoStr = txtVoto.getText().trim();
            String note = textNote.getText().trim();
            boolean lode = chckbxLODE.isSelected();

            if (matricola.isEmpty() ||  votoStr.isEmpty()) {
                showError("Compila tutti i campi obbligatori.");
                txtMatricola.setText("");
                txtVoto.setText("");
                return;
            }
            
            if(matricola.length()!=6)
            {
                showError("Formato matricola non valido. es: Sxxxxx");
                txtMatricola.setText("");
                return;
            }

            int voto;
            try {
                voto = Integer.parseInt(votoStr);
                if (voto < 18 || voto > 30) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                showError("Il voto deve essere un numero tra 18 e 30.");
                txtVoto.setText("");
                return;
            }

            if (lode && voto != 30) {
                showError("La lode è applicabile solo al voto 30.");
                txtVoto.setText("");
                return;
            }

            ControllerEsame ctrl = ControllerEsame.getInstance();
            boolean esito = ctrl.InserisciEsame(matricola, voto, lode, note, id_verbale);     		

            if (esito) {
                StringBuilder msg = new StringBuilder();
                msg.append("Esame inserito:\n")
                   .append("Matricola: ").append(matricola).append("\n")
                   .append("Voto: ").append(voto).append(lode ? " con Lode\n" : "\n");
                
                if (!note.isEmpty()) msg.append("Note: ").append(note);
                JOptionPane.showMessageDialog(this, msg.toString(), "Successo", JOptionPane.INFORMATION_MESSAGE);
                conferma = true;
                btnNuovo.setEnabled(true);
            }
            else{

                JOptionPane.showMessageDialog(this, "Studente non trovato.", "Errore", JOptionPane.ERROR_MESSAGE);
                txtMatricola.setText("");
                
            }
            
        });

        btnNuovo.addActionListener(e -> {
            if (conferma) {
                new InterfacciaInserisciEsami(id_verbale).setVisible(true);
                dispose();
            }
        });

        btnInvia.addActionListener(e -> {
        	new InterfacciaCreaVerbale().setVisible(true);
            dispose();
        });
    }

    /**
     * Mostra un messaggio di errore con finestra di dialogo.
     * @param message testo del messaggio di errore
     */

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Errore", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Applica uno stile standard a un pulsante con effetti al passaggio del mouse.
     * @param button pulsante da stilizzare
     */

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        button.addMouseListener(new MouseAdapter() {
            Color original = button.getBackground();

            public void mouseEntered(MouseEvent e) {
                button.setBackground(original.darker());
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(original);
            }
        });
    }
}
