package boundary.docenteboundary;
import dto.DataDTO;
import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 * Finestra per la scelta di un corso tra quelli disponibili.
 */

public class InterfacciaSceltaCorso extends JFrame {

    private static final long serialVersionUID = 1L;
    
    /**
     * Costruttore che crea una finestra con pulsanti per ogni corso disponibile.
     * 
     * @param datas Oggetto DataDTO che contiene la lista dei corsi.
     * @param listener Listener che riceve la notifica della scelta di un corso.
     */

    public InterfacciaSceltaCorso(DataDTO datas, CorsoSelectionListener listener) {
        setTitle("Corsi disponibili");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<String> corsi = datas.getCorsi();

        for (String corso : corsi) {
            JButton btnCorso = new JButton(corso);
            btnCorso.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            btnCorso.setBackground(new Color(70, 130, 180));
            btnCorso.setForeground(Color.WHITE);
            btnCorso.setFocusPainted(false);

            // Azione al click
            btnCorso.addActionListener(e -> {
                // Comunica la scelta al listener
                if (listener != null) {
                    listener.onCorsoSelected(corso);
                }
                dispose(); // Chiude la finestra dopo la selezione
            });

            panel.add(btnCorso);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        setContentPane(scrollPane);
    }
}
