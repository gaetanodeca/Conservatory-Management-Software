package dao;

import entity.Docente;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe DAO per l'accesso e la manipolazione dei dati relativi ai docenti.
 * Contiene metodi per trovare un docente tramite matricola e per salvare un nuovo docente nel database.
 */
public class DAODocente {

    private String matricola;
    private String nome;
    private String cognome;
    private String password;

    /**
     * Costruttore di default.
     */
    public DAODocente() {}

    /**
     * Costruttore che crea un'istanza di DAODocente con tutti i campi,
     * e salva immediatamente l'oggetto nel database.
     * 
     * @param matricola matricola del docente
     * @param nome nome del docente
     * @param cognome cognome del docente
     * @param password password del docente
     */
    public DAODocente(String matricola, String nome, String cognome, String password) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        save();
    }

    /**
     * Cerca un docente nel database tramite la sua matricola.
     * 
     * @param matricola matricola del docente da cercare
     * @return un oggetto Docente se trovato, null altrimenti
     */
    public Docente findByMatricola(String matricola) {
        String query = "SELECT * FROM docenti WHERE matricola = '" + matricola + "'";
        ResultSet resultSet;

        try {
            resultSet = DBManager.selectQuery(query);

            // È necessario chiamare next() per spostarsi al primo risultato
            if (resultSet.next()) {
                String matricola_doc = resultSet.getString("matricola");
                String nome = resultSet.getString("nome");
                String corsodistudio = resultSet.getString("ed_corso"); // corso di studio assegnato al docente
                String cognome = resultSet.getString("cognome");
                String password = resultSet.getString("password");

                return new Docente(matricola_doc, nome, cognome, password, corsodistudio);
            }

        } catch (Exception e) {
            e.printStackTrace(); // utile per debug in fase di sviluppo
        }

        return null;
    }

    /**
     * Inserisce un nuovo docente nel database utilizzando i dati di questa istanza.
     * Il metodo genera una query SQL di tipo INSERT.
     */
    public void save() {
        String insert = "INSERT INTO docenti (matricola, nome, cognome, password) VALUES ('"
                        + this.matricola + "','" + this.nome + "','" + this.cognome + "','" + this.password + "')";

        try {
            DBManager.updateQuery(insert);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
