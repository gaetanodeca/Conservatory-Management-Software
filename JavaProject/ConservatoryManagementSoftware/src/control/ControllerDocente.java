package control;

import entity.FacadeConservatorio;

/**
 * Controller singleton per la gestione delle operazioni relative ai Docenti.
 * Fa da intermediario tra la GUI e la FacadeConservatorio.
 */
public class ControllerDocente {

    // Istanza singleton della classe
    private static ControllerDocente uniqueInstance;

    // Istanza della Facade per delegare le operazioni
    private FacadeConservatorio facade = FacadeConservatorio.getInstance();

    /**
     * Costruttore privato per garantire il pattern Singleton.
     */
    private ControllerDocente() {}

    /**
     * Restituisce l'istanza singleton di ControllerDocente.
     * Se non esiste, la crea.
     * 
     * @return l'istanza singleton di ControllerDocente
     */
    public static ControllerDocente getInstance(){
        if(uniqueInstance == null) {
            uniqueInstance = new ControllerDocente();
        }
        return uniqueInstance;
    }

    /**
     * Metodo per aggiungere un nuovo docente.
     * Delegato alla FacadeConservatorio.
     * 
     * @param nome nome del docente
     * @param cognome cognome del docente
     * @param password password per il docente
     * @return esito dell'operazione, solitamente un messaggio di conferma o errore
     */
    public String aggiungiDocente(String nome, String cognome, String password) {
       return facade.aggiungiDocente(nome, cognome, password);
    }
}
