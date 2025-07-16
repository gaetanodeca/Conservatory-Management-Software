package entity;
import dao.DAOVerbale;

/**
 * Rappresenta un verbale di esami con dati relativi all'appello, corso, docente e stato.
 */
public class Verbale {

    private int ID;
    private String dataAppello;
    private String codiceCorso;
    private String stato;
    private String matricola_docente;

    private DAOVerbale daoV;

    /**
     * Costruttore per creare un Verbale dato solo l'id.
     * @param id_verbale identificativo univoco del verbale
     */
    public Verbale(int id_verbale)
    {
    	this.ID = id_verbale;
    }
    
    /**
     * Costruttore completo per creare un nuovo verbale.
     * Inizializza anche il DAO per salvare i dati.
     * 
     * @param id_verbale identificativo del verbale
     * @param dataAppello data dell'appello
     * @param codiceCorso codice del corso associato
     * @param matricola_docente matricola del docente responsabile
     */
    public Verbale(int id_verbale, String dataAppello, String codiceCorso, String matricola_docente) {
        this.ID=id_verbale;
        this.dataAppello = dataAppello;
        this.codiceCorso = codiceCorso;
        this.stato = "APERTO";
        this.matricola_docente = matricola_docente;

        this.daoV = new DAOVerbale(id_verbale, dataAppello, codiceCorso,"APERTO", matricola_docente);
    }

    /**
     * Inserisce un esame associato a questo verbale.
     * Crea un nuovo oggetto Esame con i dati forniti.
     * 
     * @param matricola_studente matricola dello studente che ha sostenuto l'esame
     * @param voto valutazione ottenuta
     * @param lode indica se è stata assegnata la lode
     * @param note eventuali note aggiuntive
     */
    public void inserisciEsame(String matricola_studente, int voto, boolean lode, String note){
        Esame esame= new Esame(matricola_studente, voto, lode, note, this.ID);
    }
    
}
