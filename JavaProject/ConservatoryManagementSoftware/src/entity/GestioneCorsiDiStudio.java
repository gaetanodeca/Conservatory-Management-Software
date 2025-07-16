package entity;

import dao.DAOCorsiDiStudio;
import java.util.List;

/**
 * Singleton per la gestione dei corsi di studio.
 * Fornisce metodi per ottenere liste di corsi filtrati per categoria o docente.
 */
public class GestioneCorsiDiStudio {

    private static GestioneCorsiDiStudio uniqueInstance = null;
    
    /**
     * Costruttore privato per Singleton.
     */
    private GestioneCorsiDiStudio() {}

    /**
     * Restituisce l'istanza singleton di GestioneCorsiDiStudio.
     * @return istanza unica di GestioneCorsiDiStudio
     */
    public static GestioneCorsiDiStudio getInstance(){
        if (uniqueInstance == null) {
            uniqueInstance = new GestioneCorsiDiStudio();
        }
        return uniqueInstance;
    }

    private DAOCorsiDiStudio dao = new DAOCorsiDiStudio();

    /**
     * Trova la lista di corsi associati a un docente per una specifica data.
     * 
     * @param matricola_doc matricola del docente
     * @param data data di riferimento (es. data appello)
     * @return lista di codici corsi associati al docente e alla data
     */
    public List<String> findbyDocente(String matricola_doc, String data) {
        return dao.findbyDocente(matricola_doc, data);
    }
    
    /**
     * Ottiene la lista dei corsi della categoria "Canto".
     * 
     * @return lista di corsi di tipo Canto
     */
    public List<CorsoDiStudio> getCorsiPerCanto() {
        return dao.getCorsiPerCanto();
    }
    
    /**
     * Ottiene la lista dei corsi della categoria "Strumento".
     * 
     * @return lista di corsi di tipo Strumento
     */
    public List<CorsoDiStudio> getCorsiPerStrumento() {
        return dao.getCorsiPerStrumento();
    }
    
    /**
     * Ottiene la lista dei corsi della categoria "Composizione".
     * 
     * @return lista di corsi di tipo Composizione
     */
    public List<CorsoDiStudio> getCorsiPerComposizione() {
        return dao.getCorsiPerComposizione();
    }
    
    /**
     * Ottiene la lista dei corsi della categoria "Insieme".
     * 
     * @return lista di corsi di tipo Insieme
     */
    public List<CorsoDiStudio> getCorsiPerInsieme() {
        return dao.getCorsiPerInsieme();
    }
    
    /**
     * Ottiene la lista dei corsi della categoria "Didattica".
     * 
     * @return lista di corsi di tipo Didattica
     */
    public List<CorsoDiStudio> getCorsiPerDidattica() {
        return dao.getCorsiPerDidattica();
    }
}
