package entity;

import dao.DAOVerbale;

/**
 * Classe singleton per la gestione dei verbali.
 * Fornisce metodi per operazioni relative ai verbali e agli esami.
 */
public class GestioneVerbali {

    private static GestioneVerbali uniqueInstance;

    /**
     * Costruttore privato per implementare il pattern Singleton.
     */
    private GestioneVerbali() {};

    /**
     * Restituisce l'istanza unica di GestioneVerbali.
     * @return istanza singleton di GestioneVerbali
     */
    public static GestioneVerbali getInstance(){
        if(uniqueInstance == null)
        {
            uniqueInstance = new GestioneVerbali();
        }
        return uniqueInstance;
    }      
    
    private DAOVerbale dao = new DAOVerbale();

    /**
     * Inserisce un esame associato a un verbale specifico.
     * Recupera il verbale tramite ID e poi aggiunge l'esame.
     * 
     * @param matricola_studente matricola dello studente che ha sostenuto l'esame
     * @param voto voto ottenuto nell'esame
     * @param lode indicatore di lode (true se presente)
     * @param note eventuali note sull'esame
     * @param id_verbale identificativo del verbale associato
     */
    public void inserisciEsame(String matricola_studente, int voto, boolean lode, String note, int id_verbale) {
        Verbale v = dao.readVerbale(id_verbale);
        if (v != null) {
            v.inserisciEsame(matricola_studente, voto, lode, note);
        } else {
            System.err.println("GestioneVerbali: Verbale con id " + id_verbale + " non trovato.");
        }
    }
}
