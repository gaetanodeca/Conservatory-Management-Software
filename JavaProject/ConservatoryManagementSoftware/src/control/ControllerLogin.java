package control;

import entity.FacadeConservatorio;
import entity.Studente;
import exceptions.LoginFailedException;

/**
 * Controller singleton per la gestione delle operazioni di login.
 * Fornisce un'interfaccia tra la GUI e la FacadeConservatorio per l'autenticazione degli utenti.
 */
public class ControllerLogin {

    // Istanza singleton di ControllerLogin
    private static ControllerLogin uniqueInstance;

    /**
     * Restituisce l'istanza singleton di ControllerLogin.
     * Se non esiste, la crea.
     *
     * @return istanza singleton di ControllerLogin
     */
    public static ControllerLogin getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ControllerLogin();
        }
        return uniqueInstance;
    }

    // Costruttore privato per Singleton
    private ControllerLogin() {}

    /**
     * Recupera l'oggetto Studente corrispondente a un dato username.
     * 
     * @param username username dello studente da cercare
     * @return l'oggetto Studente associato allo username, oppure null se non trovato
     */
    public Studente getStudenteByUsername(String username) {
        return FacadeConservatorio.getInstance().getStudenteByUsername(username);
    }

    /**
     * Effettua l'autenticazione dello studente tramite username e password.
     * 
     * @param username username dello studente
     * @param password password dello studente
     * @return true se l'autenticazione ha successo, false altrimenti
     * @throws LoginFailedException se l'autenticazione fallisce (da gestire nel metodo chiamante)
     */
    public boolean autenticazione(String username, String password) throws LoginFailedException {
        System.out.println("LoginController: Avvio processo di autenticazione tramite FacadeConservatorio.");
        return FacadeConservatorio.getInstance().autenticaStudente(username, password);
    }
}
