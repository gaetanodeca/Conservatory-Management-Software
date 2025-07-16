package entity;

import dao.DAOStudente;
import exceptions.LoginFailedException;

/**
 * Classe singleton per la gestione degli studenti.
 * Fornisce metodi per autenticazione e recupero dati studente.
 */
public class GestioneStudenti {

    private static GestioneStudenti uniqueInstance;

    /**
     * Costruttore privato per implementare il pattern Singleton.
     */
    private GestioneStudenti() {};

    /**
     * Restituisce l'istanza unica di GestioneStudenti.
     * @return istanza singleton di GestioneStudenti
     */
    public static GestioneStudenti getInstance(){
        if(uniqueInstance == null)
        {
            uniqueInstance = new GestioneStudenti();
        }
        return uniqueInstance;
    }
    
    private DAOStudente stDAO = new DAOStudente();

    /**
     * Controlla se uno studente esiste nel database tramite la matricola.
     * @param matricola_studente matricola dello studente da verificare
     * @return true se lo studente esiste, false altrimenti
     */
    public boolean checkStudente(String matricola_studente){
        return stDAO.findByMatricola(matricola_studente);
    }
    
    /**
     * Recupera un oggetto Studente tramite username.
     * @param username username dello studente
     * @return oggetto Studente se trovato, null altrimenti
     */
    public Studente getStudenteByUsername(String username) {
        return stDAO.getCredenziali(username);
    }
    
    /**
     * Effettua l'autenticazione di uno studente tramite username e password.
     * @param username username dello studente
     * @param password password inserita per l'autenticazione
     * @return true se autenticazione ha successo
     * @throws LoginFailedException se username non esiste o password errata
     */
    public boolean autenticazione(String username, String password) throws LoginFailedException {
        System.out.println("GestioneStudenti: Avvio processo di autenticazione.");

        Studente foundStudent = stDAO.getCredenziali(username);

        if (foundStudent == null) {
            System.out.println("GestioneStudenti: Studente con username '" + username + "' non trovato nel database.");
            throw new LoginFailedException("Username non esistente.");
        }

        boolean isAuthenticated = foundStudent.checkPassword(password);

        if (isAuthenticated) {
            System.out.println("GestioneStudenti: Autenticazione riuscita per username: " + username);
            return true;
        } else {
            System.out.println("GestioneStudenti: Autenticazione fallita per username: " + username + " (password errata).");
            throw new LoginFailedException("Password non corretta.");
        }
    }
   
}
