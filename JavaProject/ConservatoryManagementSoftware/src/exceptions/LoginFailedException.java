package exceptions;

/**
 * Eccezione personalizzata lanciata quando il login fallisce.
 */
public class LoginFailedException extends Exception {

    /**
     * Costruttore che accetta un messaggio di errore.
     * 
     * @param message messaggio di errore da associare all'eccezione
     */
    public LoginFailedException(String message) {
        super(message);
    }

    /**
     * Stampa un messaggio di errore generico sullo stream di errore standard.
     */
    public void stampaMessaggioErrore() {
        System.err.println("Login fallito");
    }
    
}
