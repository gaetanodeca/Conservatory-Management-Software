package dao;

import java.sql.SQLException;

/**
 * Classe DAO per la gestione degli esami.
 * Consente l'inserimento di un esame nel database.
 */
public class DAOEsame {

    private String matricola;
    private int valutazione;
    private int lode;
    private String note;
    private int idVerbale;

    /**
     * Costruttore che inizializza un DAOEsame con i dati forniti
     * e inserisce immediatamente l'esame nel database.
     * 
     * @param matricola matricola dello studente che ha sostenuto l'esame
     * @param valutazione voto conseguito all'esame
     * @param lode valore intero che indica se l'esame ha lode (tipicamente 0 o 1)
     * @param note eventuali note associate all'esame
     * @param idVerbale identificativo del verbale associato all'esame
     */
    public DAOEsame(String matricola, int valutazione, int lode, String note, int idVerbale) {
        this.matricola = matricola;
        this.valutazione = valutazione;
        this.lode = lode;
        this.note = note;
        this.idVerbale = idVerbale;

        InserisciEsame();
    }

    /**
     * Inserisce l'esame nel database costruendo e eseguendo
     * una query SQL di tipo INSERT con i dati forniti.
     */
    public void InserisciEsame() {
        String sql = "INSERT INTO esami (matricola_studente, lode, note, voto, idVerbale) VALUES ('"
                     + this.matricola + "','" + this.lode + "','" + this.note + "','" + this.valutazione + "', '" + this.idVerbale + "')";

        try {
            DBManager.updateQuery(sql);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
