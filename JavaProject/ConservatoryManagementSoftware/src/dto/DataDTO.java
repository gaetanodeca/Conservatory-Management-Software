package dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) che rappresenta i dati di un verbale con i corsi associati.
 */
public class DataDTO {

    private int id_verbale;
    private List<String> corsi;

    /**
     * Costruttore completo.
     * 
     * @param id_verb identificativo del verbale
     * @param corsi lista di nomi o codici dei corsi associati al verbale
     */
    public DataDTO(int id_verb, List<String> corsi) {
        this.id_verbale = id_verb;
        this.corsi = corsi;
    }

    /**
     * Costruttore con solo l'id del verbale.
     * 
     * @param id identificativo del verbale
     */
    public DataDTO(int id) {
        this.id_verbale = id;
    }

    /**
     * Restituisce l'id del verbale.
     * 
     * @return id del verbale
     */
    public int getId_verbale() {
        return id_verbale;
    }

    /**
     * Restituisce la lista dei corsi associati.
     * 
     * @return lista di corsi
     */
    public List<String> getCorsi() {
        return corsi;
    }
}
