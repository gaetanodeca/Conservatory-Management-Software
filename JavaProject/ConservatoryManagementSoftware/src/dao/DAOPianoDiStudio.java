package dao;

import entity.CorsoDiStudio;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe DAO per la gestione dei piani di studio.
 * Consente la creazione e il salvataggio di piani di studio nel database.
 */
public class DAOPianoDiStudio {
    private int CFU;
    private int idpiano;
    private String Nome;

    /**
     * Costruttore vuoto.
     */
    public DAOPianoDiStudio() {
    }

    /**
     * Costruttore che inizializza un piano di studio con id e nome,
     * impostando i CFU a 180 di default.
     * 
     * @param idpiano identificativo del piano di studio
     * @param Nome nome del piano di studio
     */
    public DAOPianoDiStudio(int idpiano, String Nome) {
        this.CFU = 180;
        this.idpiano = idpiano;
        this.Nome = Nome;
    }

    /**
     * Crea un nuovo piano di studi nel database con CFU fissati a 180.
     * 
     * @param idpiano identificativo del piano di studio
     * @param Nome nome del piano di studio
     * @return true se la creazione è andata a buon fine, false in caso di errore
     */
    public boolean creaNuovoPianoDiStudi(int idpiano, String Nome) {
        try {
            String insertSql = "INSERT INTO pianidistudio (CFU, idpiano, nome) VALUES (" + 180 + ", " + idpiano + ", '" + Nome + "')";
            DBManager.updateQuery(insertSql);
            return true;

        } catch (SQLException e) {
            System.err.println("PianoDiStudiDAO: Errore database durante la creazione del piano di studi: " + e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            System.err.println("PianoDiStudiDAO: Classe non trovata nel database: " + e.getMessage());
            return false;
        }
    }

    /**
     * Salva la lista di corsi selezionati associandoli a un piano di studio.
     * 
     * @param idpiano identificativo del piano di studio
     * @param corsiSelezionati lista dei corsi da associare al piano di studio
     * @return true se il salvataggio ha avuto successo, false in caso di errore o dati non validi
     */
    public boolean salvaPianoDiStudi(int idpiano, List<CorsoDiStudio> corsiSelezionati) {

        if (corsiSelezionati == null || corsiSelezionati.isEmpty()) {
            System.out.println("PianoDiStudiDAO: Nessun corso da salvare.");
            return false;
        }

        if (idpiano <= 0) {
            System.out.println("IdPiano non valido!");
            return false;
        }

        try {
            for (CorsoDiStudio corso : corsiSelezionati) {
                String insertSql = "INSERT INTO corsiselezionati (id_piano, id_corso) " +
                                   "VALUES ('" + idpiano + "', '" + corso.getCodice() + "')";
                DBManager.updateQuery(insertSql);
            }
            return true;

        } catch (SQLException e) {
            System.err.println("PianoDiStudiDAO: Errore database durante il salvataggio piano studi: " + e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            System.err.println("PianoDiStudiDAO: Classe non trovata nel database: " + e.getMessage());
            return false;
        }
    }
}
