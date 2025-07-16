package control;

import entity.FacadeConservatorio;

/**
 * Controller singleton per la gestione delle operazioni sugli esami.
 * Fornisce un'interfaccia tra la GUI e la FacadeConservatorio.
 */
public class ControllerEsame {

    // Istanza singleton
    private static ControllerEsame uniqueInstance;

    /**
     * Restituisce l'istanza singleton di ControllerEsame.
     * Se non esiste, la crea.
     *
     * @return istanza singleton di ControllerEsame
     */
    public static ControllerEsame getInstance(){
        if(uniqueInstance == null) {
            uniqueInstance = new ControllerEsame();
        }
        return uniqueInstance;
    }

    // Costruttore privato per Singleton
    private ControllerEsame(){
    }
    
    /**
     * Inserisce un nuovo esame nel sistema.
     * 
     * @param matricola matricola dello studente che ha sostenuto l'esame
     * @param voto voto conseguito all'esame
     * @param lode true se l'esame è stato conseguito con lode, false altrimenti
     * @param note eventuali note aggiuntive sull'esame
     * @param id_verbale identificativo del verbale associato all'esame
     * @return true se l'inserimento è andato a buon fine, false altrimenti
     */
    public boolean InserisciEsame(String matricola, int voto, boolean lode, String note, int id_verbale){
        FacadeConservatorio fcd = FacadeConservatorio.getInstance();
        return fcd.InserisciEsame(matricola, voto, lode, note, id_verbale);
    }
}
