package dao;

import entity.CorsoDiStudio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO per l'accesso ai dati relativi ai corsi di studio.
 * Fornisce metodi per recuperare corsi dal database in base a vari criteri,
 * come docente assegnato o categoria di corso.
 */
public class DAOCorsiDiStudio {

    /**
     * Recupera la lista dei nomi dei corsi assegnati a un docente in un determinato anno accademico.
     * L'anno accademico è determinato dalla data passata come parametro.
     * 
     * @param matricola_doc matricola del docente
     * @param data data di riferimento (formato "dd/MM/yyyy") per determinare l'anno accademico
     * @return lista di nomi dei corsi assegnati al docente per l'anno accademico,
     *         null se il docente non ha corsi assegnati,
     *         lista vuota se non ci sono corsi per l'anno specificato
     */
    public List<String> findbyDocente(String matricola_doc, String data) {
        List<String> corsi = new ArrayList<>();
    
        String parts[] = data.split("/");
    
        int anno = Integer.parseInt(parts[2]); // 2023
        int annooprecedente = anno - 1;        // 2022
        int annosuccessivo = anno + 1;
        String annoAccademico2 = anno + "/" + annosuccessivo;
        String annoAccademico1 = annooprecedente + "/" + anno;
    
        try {
            String sqlTot = "SELECT COUNT(*) AS total FROM edizionicorso WHERE docente_assegnato = '" + matricola_doc + "'";
            ResultSet rsTot = DBManager.selectQuery(sqlTot);
            if (rsTot.next() && rsTot.getInt("total") == 0) {
                System.out.println("Docente senza corsi assegnati");
                return null;
            }
    
            String sqlAnno = "SELECT COUNT(*) AS total FROM edizionicorso WHERE docente_assegnato = '" + matricola_doc + "' " +
                             "AND (annoAccademico = '" + annoAccademico1 + "' OR annoAccademico = '" + annoAccademico2 + "')";
            ResultSet rsAnno = DBManager.selectQuery(sqlAnno);
            if (rsAnno.next() && rsAnno.getInt("total") == 0) {
                System.out.println("Nessun corso per l'anno accademico richiesto");
                return corsi;
            }
       
            String sql = "SELECT * FROM edizionicorso WHERE docente_assegnato = '" + matricola_doc + "' " +
                         "AND (annoAccademico = '" + annoAccademico1 + "' OR annoAccademico = '" + annoAccademico2 + "')";
    
            ResultSet rs1 = DBManager.selectQuery(sql);
    
            while (rs1.next()) {
                String id_corso = rs1.getString("cds");
    
                String query = "SELECT denominazione FROM corsidistudio WHERE codice = '" + id_corso + "'";
                ResultSet rs2 = DBManager.selectQuery(query);
    
                if (rs2.next()) {
                    String nome_corso = rs2.getString("denominazione");
                    corsi.add(nome_corso);
                }            
            }
              
            return corsi;
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return corsi;
    }

    /**
     * Recupera la lista dei corsi di Canto dal database.
     * 
     * @return lista di corsi di Canto, oppure null in caso di errore di accesso al database
     */
    public List<CorsoDiStudio> getCorsiPerCanto() {
        System.out.println("CorsoDAO: Caricando corsi di Canto dal database MySQL.");
        String sql = "SELECT CFU, codice, denominazione FROM corsidistudio WHERE codice LIKE 'CANT%'";
        List<CorsoDiStudio> corsi = new ArrayList<>();
        
        try {
            ResultSet rs = DBManager.selectQuery(sql);
            while (rs.next()) {
                int CFU = rs.getInt("CFU");
                String codice = rs.getString("codice");
                String denominazione = rs.getString("denominazione");
                
                CorsoDiStudio corso = new CorsoDiStudio(CFU, codice, denominazione);
                corsi.add(corso);
            }
            
            System.out.println("CorsoDAO: Caricati " + corsi.size() + " corsi di Canto");
            return corsi;
            
        } catch (SQLException e) {
            System.err.println("Errore database durante il caricamento corsi di Canto: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Classe non trovata nel database: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Recupera la lista dei corsi di Strumento dal database.
     * 
     * @return lista di corsi di Strumento, oppure null in caso di errore di accesso al database
     */
    public List<CorsoDiStudio> getCorsiPerStrumento() {
        System.out.println("CorsoDAO: Caricando corsi di Strumento dal database MySQL.");
        String sql = "SELECT CFU, codice, denominazione FROM corsidistudio WHERE codice LIKE 'STRU%'";
        List<CorsoDiStudio> corsi = new ArrayList<>();
        
        try {
            ResultSet rs = DBManager.selectQuery(sql);
            while (rs.next()) {
                int CFU = rs.getInt("CFU");
                String codice = rs.getString("codice");
                String denominazione = rs.getString("denominazione");
                
                CorsoDiStudio corso = new CorsoDiStudio(CFU, codice, denominazione);
                corsi.add(corso);
            }
            
            System.out.println("CorsoDAO: Caricati " + corsi.size() + " corsi di Strumento");
            return corsi;
            
        } catch (SQLException e) {
            System.err.println("Errore database durante il caricamento corsi di Strumento: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Classe non trovata nel database: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Recupera la lista dei corsi di Composizione dal database.
     * 
     * @return lista di corsi di Composizione, oppure null in caso di errore di accesso al database
     */
    public List<CorsoDiStudio> getCorsiPerComposizione() {
        System.out.println("CorsoDAO: Caricando corsi di Composizione dal database MySQL.");
        String sql = "SELECT CFU, codice, denominazione FROM corsidistudio WHERE codice LIKE 'COMP%'";
        List<CorsoDiStudio> corsi = new ArrayList<>();
        
        try {
            ResultSet rs = DBManager.selectQuery(sql);
            while (rs.next()) {
                int CFU = rs.getInt("CFU");
                String codice = rs.getString("codice");
                String denominazione = rs.getString("denominazione");
                
                CorsoDiStudio corso = new CorsoDiStudio(CFU, codice, denominazione);
                corsi.add(corso);
            }
            
            System.out.println("CorsoDAO: Caricati " + corsi.size() + " corsi di Composizione");
            return corsi;
            
        } catch (SQLException e) {
            System.err.println("Errore database durante il caricamento corsi di Composizione: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Classe non trovata nel database: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Recupera la lista dei corsi di Musica d'Insieme dal database.
     * 
     * @return lista di corsi di Musica d'Insieme, oppure null in caso di errore di accesso al database
     */
    public List<CorsoDiStudio> getCorsiPerInsieme() {
        System.out.println("CorsoDAO: Caricando corsi di Musica d'Insieme dal database MySQL.");
        String sql = "SELECT CFU, codice, denominazione FROM corsidistudio WHERE codice LIKE 'INS%'";
        List<CorsoDiStudio> corsi = new ArrayList<>();
        
        try {
            ResultSet rs = DBManager.selectQuery(sql);
            while (rs.next()) {
                int CFU = rs.getInt("CFU");
                String codice = rs.getString("codice");
                String denominazione = rs.getString("denominazione");
                
                CorsoDiStudio corso = new CorsoDiStudio(CFU, codice, denominazione);
                corsi.add(corso);
            }
            
            System.out.println("CorsoDAO: Caricati " + corsi.size() + " corsi di Musica d'Insieme");
            return corsi;
            
        } catch (SQLException e) {
            System.err.println("Errore database durante il caricamento corsi di Musica d'Insieme: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Classe non trovata nel database: " + e.getMessage());
            return null;
        }
    }
     
    /**
     * Recupera la lista dei corsi di Didattica dal database.
     * 
     * @return lista di corsi di Didattica, oppure null in caso di errore di accesso al database
     */
    public List<CorsoDiStudio> getCorsiPerDidattica() {
        System.out.println("CorsoDAO: Caricando corsi di Didattica dal database MySQL.");
        String sql = "SELECT CFU, codice, denominazione FROM corsidistudio WHERE codice LIKE 'DID%'";
        List<CorsoDiStudio> corsi = new ArrayList<>();
        
        try {
            ResultSet rs = DBManager.selectQuery(sql);
            while (rs.next()) {
                int CFU = rs.getInt("CFU");
                String codice = rs.getString("codice");
                String denominazione = rs.getString("denominazione");
                
                CorsoDiStudio corso = new CorsoDiStudio(CFU, codice, denominazione);
                corsi.add(corso);
            }
            
            System.out.println("CorsoDAO: Caricati " + corsi.size() + " corsi di Didattica");
            return corsi;
            
        } catch (SQLException e) {
            System.err.println("Errore database durante il caricamento corsi di Didattica: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Classe non trovata nel database: " + e.getMessage());
            return null;
        }
    }
    
}
