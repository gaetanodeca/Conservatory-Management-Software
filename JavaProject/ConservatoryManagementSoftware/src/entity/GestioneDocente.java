package entity;

import dao.DAODocente;
import java.util.Random;

/**
 * Classe singleton per la gestione delle operazioni relative ai docenti.
 */
public class GestioneDocente {

    private static GestioneDocente uniqueInstance;

    /**
     * Costruttore privato per implementare il pattern Singleton.
     */
    private GestioneDocente() {};

    /**
     * Restituisce l'istanza unica di GestioneDocente.
     * @return istanza singleton di GestioneDocente
     */
    public static GestioneDocente getInstance(){
        if(uniqueInstance == null)
        {
            uniqueInstance = new GestioneDocente();
        }
        return uniqueInstance;
    }
    
    DAODocente daoDocente = new DAODocente();

    /**
     * Aggiunge un nuovo docente generando una matricola casuale.
     * Se la matricola esiste già, ritorna null.
     * @param nome nome del docente
     * @param cognome cognome del docente
     * @param password password del docente
     * @return la matricola generata del nuovo docente o null in caso di matricola già esistente
     */
    public String aggiungiDocente(String nome, String cognome, String password) {
    	
    	long millis = System.currentTimeMillis();
        Random r = new Random(millis);
        int num_matricola = r.nextInt(1000); // Valore tra 0 e 999
        String matricola = "D" + String.format("%03d", num_matricola);

        Docente esistente = daoDocente.findByMatricola(matricola);

        if (esistente != null) {
            return null; 
        }else{

        Docente nuovoDocente = new Docente(matricola, nome, cognome, password);

        return matricola;
        }
    }

    /**
     * Cerca e restituisce un docente tramite la matricola.
     * @param matricola matricola del docente da cercare
     * @return oggetto Docente trovato, o null se non esiste
     */
    public Docente checkDocente(String matricola){
        return daoDocente.findByMatricola(matricola);
    }
    
}
