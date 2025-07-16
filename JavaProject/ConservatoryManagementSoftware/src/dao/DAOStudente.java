package dao;

import entity.Studente;
import java.sql.ResultSet;

/**
 * Classe DAO per l'accesso ai dati dello studente.
 * Gestisce il recupero delle credenziali e la ricerca di studenti tramite matricola.
 */
public class DAOStudente {
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private int pin;
    private String matricola;

    /**
     * Costruttore vuoto.
     */
    public DAOStudente() {}

    /**
     * Costruttore che inizializza il DAO per uno specifico username,
     * caricando le credenziali dallo database.
     * 
     * @param username username dello studente da cercare
     */
    public DAOStudente(String username) {
        this.username = username;
        getCredenziali(username);
    }

    /**
     * Recupera le credenziali dello studente dal database tramite username.
     * 
     * @param username username dello studente da cercare
     * @return oggetto Studente con i dati caricati se presente, altrimenti null
     */
    public Studente getCredenziali(String username) {
        System.out.println("StudenteDAO: Cercando credenziali per " + username + " nel database MySQL.");
        String query = "SELECT nome, cognome, username, password, PIN, matricola FROM studenti WHERE username = '" + username + "'";

        try {
            ResultSet rs = DBManager.selectQuery(query);

            if (rs != null && rs.next()) {
                this.nome = rs.getString("nome");
                this.cognome = rs.getString("cognome");
                this.username = rs.getString("username");
                this.password = rs.getString("password");
                this.pin = rs.getInt("PIN");
                this.matricola = rs.getString("matricola");

                System.out.println("StudenteDAO: Trovato utente " + this.username + " nel database.");
                return new Studente(nome, cognome, username, password, pin, matricola);
            } else {
                System.out.println("StudenteDAO: Utente " + username + " non trovato nel database.");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Errore durante il recupero credenziali: " + e.getMessage());
            return null;
        }
    }

    /**
     * Controlla se esiste uno studente con la matricola specificata nel database.
     * 
     * @param matricola matricola dello studente da cercare
     * @return true se esiste uno studente con quella matricola, false altrimenti
     */
    public boolean findByMatricola(String matricola){
        ResultSet test = null;
        String query = "SELECT * FROM studenti WHERE matricola = '" + matricola + "'";
        try {
            test = DBManager.selectQuery(query);
            if(test != null && test.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return false;
    }
}
