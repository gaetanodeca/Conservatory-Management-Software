package entity;

import dao.DAODocente;
import java.util.Random;

/**
 * Rappresenta un docente con matricola, nome, cognome, password e corso di studio assegnato.
 */
public class Docente {

    private String matricola;
    private String nome;
    private String cognome;
    private String password;
    private String edCorso;

    /**
     * Costruttore che crea un docente e salva i dati tramite DAO.
     * 
     * @param matricola matricola univoca del docente
     * @param nome nome del docente
     * @param cognome cognome del docente
     * @param password password di accesso del docente
     */
    public Docente(String matricola, String nome, String cognome, String password) {
        DAODocente daoDocente = new DAODocente(matricola, nome, cognome, password);
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
    }

    /**
     * Costruttore che inizializza un docente con corso di studio assegnato.
     * 
     * @param matricola matricola univoca del docente
     * @param nome nome del docente
     * @param cognome cognome del docente
     * @param password password di accesso del docente
     * @param CorsodiStudio codice del corso di studio assegnato al docente
     */
    public Docente(String matricola, String nome, String cognome, String password, String CorsodiStudio) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.edCorso = CorsodiStudio;
    }

    /**
     * Genera un ID casuale e crea un nuovo verbale associato al docente.
     * 
     * @param data data dell'appello/esame
     * @return l'ID del verbale creato
     */
    public int inserisciVerbale(String data) {
        long millis = System.currentTimeMillis();
        Random r = new Random(millis);
        int id_verbale = r.nextInt(1000);

        Verbale v = new Verbale(id_verbale, data, this.edCorso, this.matricola);

        return id_verbale;
    }

    // Getters e setters (se servono) possono essere aggiunti qui
}
