package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe utility per la gestione della connessione al database MySQL
 * e per l'esecuzione di query di selezione e aggiornamento.
 */
public class DBManager {

    private static String URL = "jdbc:mysql://localhost:3306/conservatoriodb";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String USER = "root"; 
    private static String PASSWORD = "FrAnc3sc027112003!"; 
    private static Connection conn = null;

    /**
     * Restituisce una nuova connessione al database.
     * 
     * @return connessione aperta al database
     * @throws ClassNotFoundException se il driver JDBC non viene trovato
     * @throws SQLException se si verifica un errore durante la connessione
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }

    /**
     * Chiude la connessione al database.
     * 
     * @param connection connessione da chiudere
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connessione al database chiusa.");
            } catch (SQLException e) {
                System.err.println("Errore durante la chiusura della connessione: " + e.getMessage());
            }
        }
    }

    /**
     * Esegue una query di selezione (SELECT) e restituisce il ResultSet.
     * 
     * @param query stringa SQL della query di selezione
     * @return ResultSet con i dati restituiti dalla query
     * @throws ClassNotFoundException se il driver JDBC non viene trovato
     * @throws SQLException se si verifica un errore durante l'esecuzione della query
     */
    public static ResultSet selectQuery(String query) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        ResultSet ret = statement.executeQuery(query);
        return ret;
    }

    /**
     * Esegue una query di aggiornamento (INSERT, UPDATE, DELETE).
     * 
     * @param query stringa SQL della query di aggiornamento
     * @return numero di righe interessate dall'operazione
     * @throws ClassNotFoundException se il driver JDBC non viene trovato
     * @throws SQLException se si verifica un errore durante l'esecuzione della query
     */
    public static int updateQuery(String query) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        int ret = statement.executeUpdate(query);
        conn.close();
        return ret;
    }
}
