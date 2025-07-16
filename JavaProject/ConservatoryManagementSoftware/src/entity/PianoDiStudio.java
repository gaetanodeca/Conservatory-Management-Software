package entity;

import dao.DAOPianoDiStudio;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un piano di studio con un insieme di corsi selezionati.
 * Gestisce i corsi, i CFU totali e controlla la validità del piano.
 */
public class PianoDiStudio {
    private int CFU;
    private int idpiano;
    private String Nome;

    private List<CorsoDiStudio> corsiSelezionati;
    private static final int CFU_MASSIMI = 180;
    
    /**
     * Costruttore con parametri.
     * Inizializza il piano con id, nome e una lista vuota di corsi.
     * @param idpiano identificatore del piano
     * @param Nome nome del piano di studio
     */
    public PianoDiStudio(int idpiano,String Nome) {
        this.CFU = 180;
        this.idpiano=idpiano;
        this.Nome = Nome;
        this.corsiSelezionati = new ArrayList<>();
        DAOPianoDiStudio piano= new DAOPianoDiStudio(idpiano, Nome);
    }
    
    /**
     * Costruttore vuoto.
     */
    public PianoDiStudio() {}
    
    /**
     * Restituisce il numero totale di CFU massimi per il piano.
     * @return CFU massimi (180)
     */
    public int getCFU() {
        return CFU;
    }
    
    /**
     * Imposta il numero totale di CFU massimi per il piano.
     * @param CFU numero CFU
     */
    public void setCFU(int CFU) {
        this.CFU = CFU;
    }
    
    /**
     * Restituisce il nome del piano di studio.
     * @return nome del piano
     */
    public String getNome() {
        return Nome;
    }
    
    /**
     * Imposta il nome del piano di studio.
     * @param nome nome del piano
     */
    public void setNome(String nome) {
        this.Nome = nome;
    }
    
    /**
     * Aggiunge un corso al piano di studio.
     * Verifica validità, duplicati e compatibilità CFU.
     * @param corso corso da aggiungere
     * @return true se aggiunto con successo, false altrimenti
     */
    public boolean aggiungiCorso(CorsoDiStudio corso) {
        if (corso == null || !corso.isValido()) {
            return false;
        }
        
        if (isCorsoGiaSelezionato(corso.getCodice())) {
            return false; // Corso già presente
        }
        
        if (!corso.isCompatibileConPiano(getTotaleCFU(), CFU_MASSIMI)) {
            return false;
        }
        
        corsiSelezionati.add(corso);
        return true;
    }
    
    /**
     * Rimuove un corso dal piano di studio.
     * @param corso corso da rimuovere
     * @return true se rimosso, false se non trovato
     */
    public boolean rimuoviCorso(CorsoDiStudio corso) {
        return corsiSelezionati.remove(corso);
    }
    
    /**
     * Restituisce la lista dei corsi selezionati.
     * @return lista di corsi
     */
    public List<CorsoDiStudio> getCorsiSelezionati() {
        return new ArrayList<>(corsiSelezionati);
    }
    
    /**
     * Calcola il totale CFU dei corsi selezionati.
     * @return somma dei CFU
     */
    public int getTotaleCFU() {
        return corsiSelezionati.stream().mapToInt(CorsoDiStudio::getCFU).sum();
    }
    
    /**
     * Restituisce il numero di corsi selezionati.
     * @return numero corsi
     */
    public int getNumeroCorsi() {
        return corsiSelezionati.size();
    }
    
    /**
     * Svuota la lista dei corsi selezionati.
     */
    public void svuotaPiano() {
        corsiSelezionati.clear();
    }
    
    /**
     * Cerca un corso nel piano per codice.
     * @param codice codice del corso da cercare
     * @return corso trovato o null se non presente
     */
    public CorsoDiStudio cercaCorsoPerCodice(String codice) {
        return corsiSelezionati.stream()
                .filter(corso -> corso.getCodice().equals(codice))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Verifica se un corso è già stato selezionato.
     * @param codice codice del corso da controllare
     * @return true se il corso è già selezionato
     */
    public boolean isCorsoGiaSelezionato(String codice) {
        return corsiSelezionati.stream()
                .anyMatch(corso -> corso.getCodice().equals(codice));
    }
    
    /**
     * Verifica se il piano di studi è valido.
     * Deve avere esattamente 180 CFU e almeno un corso selezionato.
     * @return true se valido, false altrimenti
     */
    public boolean isPianoValido() {
        int totaleCFU = getTotaleCFU();
        return totaleCFU == CFU_MASSIMI && !corsiSelezionati.isEmpty();
    }
    
    /**
     * Verifica se il piano di studi è completo,
     * ovvero se ha almeno 180 CFU.
     * @return true se completo
     */
    public boolean isPianoCompleto() {
        return getTotaleCFU() >= CFU_MASSIMI;
    }
}
