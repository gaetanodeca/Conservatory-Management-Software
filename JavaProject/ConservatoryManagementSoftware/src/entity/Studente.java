package entity;
import dao.DAOStudente;

/**
 * Rappresenta uno studente con attributi personali e metodi di autenticazione.
 */
public class Studente {

    private String nome;
    private String cognome;
    private String username;
    private String password;
    private int PIN;
    private String matricola;

    private DAOStudente studenteDAO;

    /**
     * Costruttore completo di Studente.
     * @param nome nome dello studente
     * @param cognome cognome dello studente
     * @param username username di login
     * @param password password di login
     * @param PIN codice PIN dello studente
     * @param matricola matricola identificativa dello studente
     */
    public Studente(String nome, String cognome, String username, String password, int PIN, String matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.PIN = PIN;
        this.matricola = matricola;
    }

    /**
     * Costruttore con DAO per accesso a dati esterni.
     * @param studenteDAO oggetto DAO per operazioni sul database
     */
    public Studente(DAOStudente studenteDAO) {
        this.studenteDAO = studenteDAO;
    }

    /** @return nome dello studente */
    public String getNome() {
        return nome;
    }

    /** @return cognome dello studente */
    public String getCognome() {
        return cognome;
    }

    /** @return username per il login */
    public String getUsername() {
        return username;
    }

    /** @return password per il login */
    public String getPassword() {
        return password;
    }

    /** @return codice PIN dello studente */
    public int getPIN() {
        return PIN;
    }

    /** @return matricola dello studente */
    public String getMatricola() {
        return matricola;
    }

    /**
     * Verifica se la password fornita coincide con quella salvata.
     * @param passwordFornita password da verificare
     * @return true se la password è corretta, false altrimenti
     */
    public boolean checkPassword(String passwordFornita) { 
        return this.password != null && this.password.equals(passwordFornita); 
    }

    /**
     * Recupera le credenziali di uno studente dal database tramite username.
     * @param username username dello studente da cercare
     * @return oggetto Studente con credenziali trovate
     * @throws IllegalStateException se il DAO non è stato inizializzato
     */
    public Studente getCredenziali(String username) { 
        if (studenteDAO == null) {
            throw new IllegalStateException("StudenteDAO non è stato raggiunto");
        }
        return studenteDAO.getCredenziali(username); 
    }
    
    /**
     * Crea un nuovo piano di studio (istanza locale).
     */
    public void CreaPianoDiStudio(){
    	PianoDiStudio Piano = new PianoDiStudio();
    }

}
