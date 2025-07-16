package dao;

import entity.Verbale;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe DAO per la gestione delle operazioni sul Verbale.
 * Permette l'inserimento e la lettura dei dati relativi ai verbali dal database.
 */
public class DAOVerbale {

    private String dataAppello;
    private String codiceCorso;
    private String stato;
    private String matricola_docente;
    private int id_verbale;

    /**
     * Costruttore vuoto.
     */
    public DAOVerbale() {
    }

    /**
     * Costruttore che inizializza i campi e inserisce un nuovo verbale nel database.
     * 
     * @param id_verbale identificativo del verbale
     * @param dataAppello data dell'appello
     * @param codiceCorso codice del corso associato al verbale
     * @param stato stato del verbale
     * @param matricola_docente matricola del docente associato
     */
    public DAOVerbale(int id_verbale, String dataAppello, String codiceCorso, String stato, String matricola_docente) {
        this.dataAppello = dataAppello;
        this.codiceCorso = codiceCorso;
        this.stato = stato;
        this.matricola_docente = matricola_docente;
        this.id_verbale = id_verbale;

        InserisciVerbale(dataAppello, codiceCorso, stato, matricola_docente);
    }

    /**
     * Inserisce un nuovo verbale nel database.
     * 
     * @param dataAppello data dell'appello
     * @param codiceCorso codice del corso associato al verbale
     * @param stato stato del verbale
     * @param matricola_docente matricola del docente associato
     * @return l'id del verbale inserito
     */
    public int InserisciVerbale(String dataAppello, String codiceCorso, String stato, String matricola_docente) {
        String sql = "INSERT INTO verbali (idVerbali, dataAppello, codiceCorso, stato, docente) VALUES ('" 
            + this.id_verbale + "','" 
            + this.dataAppello + "','" 
            + this.codiceCorso + "','" 
            + this.stato + "', '" 
            + this.matricola_docente + "')";

        try {
            DBManager.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return id_verbale;     
    }

    /**
     * Recupera un verbale dal database tramite il suo id.
     * 
     * @param id_verbale identificativo del verbale da recuperare
     * @return oggetto Verbale se trovato, altrimenti null
     */
    public Verbale readVerbale(int id_verbale) {
        String sql = "SELECT * FROM verbali WHERE idVerbali = '" + id_verbale + "'";
        try {
            ResultSet rs = DBManager.selectQuery(sql);

            if (rs.next()) {
                // Estrai i valori dalla ResultSet (anche se non usati qui, puoi implementarli)
                int idVerbale = rs.getInt("idverbali");
                String dataAppello = rs.getString("dataAppello");
                String codiceCorso = rs.getString("codiceCorso");
                String docente = rs.getString("docente");

                Verbale v = new Verbale(idVerbale);
                return v;

            } else {
                return null; 
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }
}
