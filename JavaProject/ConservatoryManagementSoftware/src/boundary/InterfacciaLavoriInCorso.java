package boundary;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Finestra che mostra una GIF animata e un messaggio di "lavori in corso".
 * Utile per indicare funzionalità non ancora implementate.
 */
public class InterfacciaLavoriInCorso extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore della finestra "Lavori in Corso".
     * Imposta titolo, dimensioni, posizione, layout e componenti grafici.
     */
    public InterfacciaLavoriInCorso() {
        setTitle("Work in Progress");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(587, 511);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

        // Label che mostra la GIF animata
        JLabel lblGif = new JLabel();
        lblGif.setHorizontalAlignment(SwingConstants.CENTER);

        // Carica la GIF dal percorso risorse
        ImageIcon gif = new ImageIcon(getClass().getResource("/images/lavoriincorso.gif"));
        lblGif.setIcon(gif);
        lblGif.setBounds(0, -2, 571, 376);
        getContentPane().add(lblGif);

        // Label per il messaggio testuale
        JLabel lblTesto = new JLabel("CI STIAMO LAVORANDO...");
        lblTesto.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTesto.setForeground(Color.DARK_GRAY);
        lblTesto.setHorizontalAlignment(SwingConstants.CENTER);
        lblTesto.setBounds(0, 385, 587, 30);
        getContentPane().add(lblTesto);

        // Bottone per chiudere la finestra
        JButton btnchiudi = new JButton("Chiudi");
        btnchiudi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        btnchiudi.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnchiudi.setBounds(222, 426, 125, 35);
        getContentPane().add(btnchiudi);
    }
}
