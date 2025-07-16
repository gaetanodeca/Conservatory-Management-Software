package control;

import dto.DataDTO;
import entity.FacadeConservatorio;

/**
 * Controller singleton per la gestione delle operazioni sui verbali.
 * Fornisce un'interfaccia tra la GUI e la FacadeConservatorio per la gestione dei verbali.
 */
public class ControllerVerbale {

    // Istanza singleton di ControllerVerbale
    private static ControllerVerbale uniqueinstance;

    /**
     * Restituisce l'istanza singleton di ControllerVerbale.
     * Se non esiste, la crea.
     *
     * @return istanza singleton di ControllerVerbale
     */
    public static ControllerVerbale getInstance(){
        if(uniqueinstance == null) {
            uniqueinstance = new ControllerVerbale();
        }
        return uniqueinstance;
    }

    // Costruttore privato per Singleton
    private ControllerVerbale(){}

    /**
     * Inserisce un nuovo verbale nel sistema.
     *
     * @param data data del verbale (formato stringa, es. "dd/MM/yyyy")
     * @param matricola matricola dello studente a cui associare il verbale
     * @return un oggetto DataDTO contenente i dati del verbale inserito
     */
    public DataDTO InserisciVerbale(String data, String matricola){
        FacadeConservatorio fa = FacadeConservatorio.getInstance();
        return fa.InserisciVerbale(data, matricola);
    }
    
}
