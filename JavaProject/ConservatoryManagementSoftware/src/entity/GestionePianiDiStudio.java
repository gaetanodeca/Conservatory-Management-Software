package entity;

import dao.DAOPianoDiStudio;
import dao.DBManager;
import java.util.List;

/**
 * Classe singleton che gestisce le operazioni relative ai piani di studio.
 * Fa da ponte tra la Facade e la classe DAO per il piano di studio.
 */
public class GestionePianiDiStudio {

    DAOPianoDiStudio PD;

    /**
     * Costruttore privato per implementare il pattern Singleton.
     */
    private GestionePianiDiStudio(){
        PD = new DAOPianoDiStudio();
    }

    /**
     * Restituisce una nuova istanza di GestionePianiDiStudio.
     * (NB: non è un vero singleton, crea una nuova istanza ogni volta)
     * @return nuova istanza di GestionePianiDiStudio
     */
    public static GestionePianiDiStudio getInstance() {
        return new GestionePianiDiStudio();
    }       

    /**
     * Crea un nuovo piano di studi nel database.
     * @param idPiano id univoco del piano di studi
     * @param nomePiano nome del piano di studi
     * @return true se la creazione ha successo, false altrimenti
     */
    public boolean creaNuovoPianoDiStudi(int idPiano, String nomePiano) {
        try {
            DAOPianoDiStudio dao = new DAOPianoDiStudio(idPiano, nomePiano);
            return dao.creaNuovoPianoDiStudi(idPiano, nomePiano);
        } catch (Exception e) {
            System.err.println("GestionePianoDiStudi: Errore durante la creazione del piano: " + e.getMessage());
            return false;
        }
    }

    /**
     * Salva il piano di studi con la lista di corsi selezionati.
     * @param idpiano id del piano di studi
     * @param corsiSelezionati lista dei corsi da salvare
     * @return true se il salvataggio ha successo, false altrimenti
     */
    public boolean salvaPianoDiStudi(int idpiano, List<CorsoDiStudio> corsiSelezionati) {
        if (corsiSelezionati == null || corsiSelezionati.isEmpty()) {
            return false;
        }
        return PD.salvaPianoDiStudi(idpiano, corsiSelezionati);
    }

    /**
     * Svuota completamente il piano di studi associato a uno studente.
     * @param matricolaStudente matricola dello studente
     * @return true se l'operazione ha successo, false altrimenti
     */
    public boolean svuotaPianoStudi(String matricolaStudente) {
        if (matricolaStudente == null || matricolaStudente.trim().isEmpty()) {
            System.out.println("GestionePianoDiStudi: Matricola studente non valida");
            return false;
        }
        try {
            String deleteSql = "DELETE FROM piano_studi WHERE matricola_studente = '" + matricolaStudente + "'";
            DBManager.updateQuery(deleteSql);
            System.out.println("GestionePianoDiStudi: Piano di studi svuotato per " + matricolaStudente);
            return true;
        } catch (Exception e) {
            System.err.println("GestionePianoDiStudi: Errore durante lo svuotamento piano studi: " + e.getMessage());
            return false;
        }
    }

}
