package control;

import entity.CorsoDiStudio;
import entity.FacadeConservatorio;
import java.util.List;

/**
 * Controller per la gestione dei corsi.
 * Fa da intermediario tra la GUI e la FacadeConservatorio,
 * delegando la maggior parte delle operazioni al facade.
 */
public class ControllerCorso {
    private FacadeConservatorio facade;
    
    /**
     * Costruttore che inietta la FacadeConservatorio da utilizzare.
     * 
     * @param facade l'istanza di FacadeConservatorio
     */
    public ControllerCorso(FacadeConservatorio facade) {
        this.facade = facade;
    }
    
    /**
     * Aggiunge un corso tramite la facade.
     * 
     * @param corso il corso da aggiungere
     * @return true se l'aggiunta ha avuto successo, false altrimenti
     */
    public boolean aggiungiCorso(CorsoDiStudio corso) {
        return facade.aggiungiCorso(corso);
    }
    
    /**
     * Rimuove un corso tramite la facade.
     * 
     * @param corso il corso da rimuovere
     * @return true se la rimozione ha avuto successo, false altrimenti
     */
    public boolean rimuoviCorso(CorsoDiStudio corso) {
        return facade.rimuoviCorso(corso);
    }
    
    /**
     * Rimuove un corso tramite codice identificativo.
     * 
     * @param codice codice del corso da rimuovere
     * @return true se la rimozione ha avuto successo, false altrimenti
     */
    public boolean rimuoviCorsoPerCodice(String codice) {
        return facade.rimuoviCorsoPerCodice(codice);
    }
    
    /**
     * Restituisce la lista dei corsi selezionati nel piano di studi.
     * 
     * @return lista dei corsi selezionati
     */
    public List<CorsoDiStudio> getCorsiSelezionati() {
        return facade.getCorsiSelezionati();
    }
    
    /**
     * Restituisce il totale dei CFU dei corsi selezionati.
     * 
     * @return totale CFU
     */
    public int getTotaleCFU() {
        return facade.getTotaleCFU();
    }
    
    /**
     * Restituisce il numero totale di corsi selezionati.
     * 
     * @return numero di corsi selezionati
     */
    public int getNumeroCorsi() {
        return facade.getNumeroCorsiSelezionati();
    }
    
    /**
     * Svuota completamente il piano di studi.
     */
    public void svuotaPianoStudi() {
        facade.svuotaPianoStudi();
    }
    
    /**
     * Cerca un corso selezionato tramite codice.
     * 
     * @param codice codice identificativo del corso
     * @return corso trovato o null se non esiste
     */
    public CorsoDiStudio cercaCorsoPerCodice(int codice) {
        return facade.cercaCorsoSelezionatoPerCodice(codice);
    }
    
    /**
     * Controlla se un corso è già selezionato nel piano.
     * 
     * @param codice codice identificativo del corso
     * @return true se il corso è selezionato, false altrimenti
     */
    public boolean isCorsoSelezionato(int codice) {
        return facade.isCorsoSelezionato(codice);
    }
    
    /**
     * Ottiene la lista dei corsi per la categoria "Canto".
     * 
     * @return lista dei corsi di canto
     */
    public List<CorsoDiStudio> getCorsiPerCanto() {
        return facade.getCorsiPerCanto();
    }
    
    /**
     * Ottiene la lista dei corsi per la categoria "Strumento".
     * 
     * @return lista dei corsi di strumento
     */
    public List<CorsoDiStudio> getCorsiPerStrumento() {
        return facade.getCorsiPerStrumento();
    }
    
    /**
     * Ottiene la lista dei corsi per la categoria "Composizione".
     * 
     * @return lista dei corsi di composizione
     */
    public List<CorsoDiStudio> getCorsiPerComposizione() {
        return facade.getCorsiPerComposizione();
    }
    
    /**
     * Ottiene la lista dei corsi per la categoria "Musica d'Insieme".
     * 
     * @return lista dei corsi di musica d'insieme
     */
    public List<CorsoDiStudio> getCorsiPerInsieme() {
        return facade.getCorsiPerInsieme();
    }
    
    /**
     * Ottiene la lista dei corsi per la categoria "Didattica".
     * 
     * @return lista dei corsi di didattica
     */
    public List<CorsoDiStudio> getCorsiPerDidattica() {
        return facade.getCorsiPerDidattica();
    }
    
    /**
     * Aggiunge un corso selezionato tramite i parametri forniti.
     * Metodo utilizzato dalla GUI per selezionare corsi.
     * 
     * @param CFU numero di crediti formativi
     * @param codice codice identificativo corso
     * @param denominazione nome del corso
     * @return true se aggiunto con successo, false se già presente
     */
    public boolean aggiungiCorsoSelezionato(int CFU, String codice, String denominazione) {
        return facade.selezionaCorsoPerGUI(CFU, codice, denominazione);
    }
    
    /**
     * Stampa sul terminale o log le statistiche dei corsi selezionati.
     */
    public void stampaCorsiSelezionati() {
        facade.stampaStatistiche();
    }
    
    /**
     * Restituisce un sommario testuale del piano di studi.
     * 
     * @return sommario del piano
     */
    public String getSommarioPianoStudi() {
        return facade.getSommarioPianoStudi();
    }
    
    /**
     * Mostra i corsi selezionati in formato stringa per la GUI.
     * 
     * @return stringa con corsi selezionati
     */
    public String mostraCorsiSelezionati() {
        return facade.mostraCorsiSelezionati();
    }
    
    /**
     * Salva un piano di studi completo con i corsi selezionati.
     * 
     * @param idpiano id del piano di studi
     * @param corsiSelezionati lista di corsi da salvare
     * @return true se il salvataggio ha avuto successo, false altrimenti
     */
    public boolean salvaPianoDiStudiCompleto(int idpiano, List<CorsoDiStudio> corsiSelezionati) {
        return facade.salvaPianoDiStudi(idpiano, corsiSelezionati);
    }
    
    /**
     * Crea un nuovo piano di studi con un id e nome specificati.
     * 
     * @param idPiano id univoco del piano di studi
     * @param nomePiano nome del piano di studi
     * @return true se creato con successo, false altrimenti
     */
    public boolean creaNuovoPianoDiStudi(int idPiano, String nomePiano) {
        return facade.creaNuovoPianoDiStudi(idPiano, nomePiano);
    }
}
