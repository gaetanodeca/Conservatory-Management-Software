package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe di gestione per i corsi selezionati in sessione (ArrayList locale).
 * Gestisce la logica di business per la selezione temporanea dei corsi.
 * Implementa il pattern Singleton per mantenere una singola istanza durante la sessione.
 */
public class GestioneCorsiSelezionati {
    
    private static GestioneCorsiSelezionati uniqueInstance;
    private List<CorsoDiStudio> corsiSelezionati;
    
    /**
     * Costruttore privato per il singleton.
     * Inizializza la lista dei corsi selezionati come vuota.
     */
    private GestioneCorsiSelezionati() {
        this.corsiSelezionati = new ArrayList<>();
    }
    
    /**
     * Restituisce l'istanza singleton di GestioneCorsiSelezionati.
     * @return istanza unica di GestioneCorsiSelezionati
     */
    public static GestioneCorsiSelezionati getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GestioneCorsiSelezionati();
        }
        return uniqueInstance;
    }
    
    /**
     * Aggiunge un corso alla selezione temporanea.
     * Controlla che il corso non sia già presente tramite il codice.
     * @param corso corso da aggiungere
     * @return true se aggiunto correttamente, false altrimenti (es. se già presente o nullo)
     */
    public boolean aggiungiCorso(CorsoDiStudio corso) {
        if (corso == null) {
            return false;
        }

        for (CorsoDiStudio c : corsiSelezionati) {
            if (c.getCodice().equals(corso.getCodice())) {
                return false; // Corso già presente
            }
        }

        return corsiSelezionati.add(corso);
    }

    /**
     * Rimuove un corso dalla selezione temporanea.
     * @param corso corso da rimuovere
     * @return true se rimosso, false se non trovato o parametro nullo
     */
    public boolean rimuoviCorso(CorsoDiStudio corso) {
        if (corso == null) {
            return false;
        }
        return corsiSelezionati.removeIf(c -> c.getCodice().equals(corso.getCodice()));
    }
    
    /**
     * Rimuove un corso dalla selezione temporanea identificandolo per codice.
     * @param codice codice del corso da rimuovere
     * @return true se rimosso, false se non trovato o codice nullo
     */
    public boolean rimuoviCorsoPerCodice(String codice) {
        if (codice == null) {
            return false;
        }
        return corsiSelezionati.removeIf(c -> c.getCodice().equals(codice));
    }
    
    /**
     * Ottiene la lista dei corsi selezionati.
     * @return copia della lista di corsi selezionati
     */
    public List<CorsoDiStudio> getCorsiSelezionati() {
        return new ArrayList<>(corsiSelezionati);
    }
    
    /**
     * Svuota la selezione temporanea dei corsi.
     */
    public void svuotaPianoStudi() {
        corsiSelezionati.clear();
    }
    
    /**
     * Calcola il totale dei CFU dei corsi selezionati.
     * @return somma dei CFU dei corsi selezionati
     */
    public int getTotaleCFU() {
        return corsiSelezionati.stream().mapToInt(CorsoDiStudio::getCFU).sum();
    }
    
    /**
     * Ottiene il numero di corsi selezionati.
     * @return numero di corsi
     */
    public int getNumeroCorsiSelezionati() {
        return corsiSelezionati.size();
    }
    
    /**
     * Cerca un corso selezionato per codice.
     * @param codice codice del corso da cercare
     * @return corso se trovato, null altrimenti
     */
    public CorsoDiStudio cercaCorsoSelezionatoPerCodice(int codice) {
        if (codice == 0) {
            return null;
        }
        return corsiSelezionati.stream()
                .filter(c -> c.getCodice().equals(codice))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Verifica se un corso è selezionato dato il codice.
     * @param codice codice del corso da verificare
     * @return true se corso selezionato, false altrimenti
     */
    public boolean isCorsoSelezionato(int codice) {
        CorsoDiStudio corso = cercaCorsoSelezionatoPerCodice(codice);
        return corso != null;
    }
    
    /**
     * Verifica se il piano di studi è valido, cioè se il totale CFU è esattamente 180.
     * @return true se piano valido, false altrimenti
     */
    public boolean isPianoDiStudiValido() {
        return getTotaleCFU() == 180;
    }
    
    /**
     * Ottiene un sommario testuale del piano di studi temporaneo.
     * @return stringa con numero corsi, totale CFU e validità del piano
     */
    public String getSommarioPianoStudi() {
        StringBuilder sb = new StringBuilder();
        sb.append("Piano di Studi Temporaneo\n");
        sb.append("Numero corsi: ").append(getNumeroCorsiSelezionati()).append("\n");
        sb.append("Totale CFU: ").append(getTotaleCFU()).append("/180\n");
        sb.append("Piano valido: ").append(isPianoDiStudiValido() ? "Sì" : "No").append("\n");
        return sb.toString();
    }
    
    /**
     * Seleziona un corso tramite parametri forniti dalla GUI.
     * @param CFU numero di CFU del corso
     * @param codice codice identificativo del corso
     * @param denominazione denominazione del corso
     * @return true se il corso è stato aggiunto correttamente, false altrimenti
     */
    public boolean selezionaCorsoPerGUI(int CFU, String codice, String denominazione) {
        CorsoDiStudio corso = new CorsoDiStudio(CFU, codice, denominazione);
        return aggiungiCorso(corso);
    }
    
    /**
     * Stampa statistiche sintetiche dei corsi selezionati su console.
     */
    public void stampaStatistiche() {
        System.out.println("=== STATISTICHE CORSI SELEZIONATI ===");
        System.out.println("Numero corsi: " + getNumeroCorsiSelezionati());
        System.out.println("Totale CFU: " + getTotaleCFU());
        System.out.println("Piano valido: " + (isPianoDiStudiValido() ? "Sì" : "No"));
        System.out.println("=====================================");
    }
    
    /**
     * Mostra i corsi selezionati come stringa formattata.
     * @return stringa con elenco dei corsi selezionati e totale CFU
     */
    public String mostraCorsiSelezionati() {
        if (corsiSelezionati.isEmpty()) {
            return "Nessun corso selezionato";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Corsi selezionati:\n");
        for (CorsoDiStudio corso : corsiSelezionati) {
            sb.append("- ").append(corso.getCodice()).append(": ")
              .append(corso.getDenominazione()).append(" (")
              .append(corso.getCFU()).append(" CFU)\n");
        }
        sb.append("Totale: ").append(getTotaleCFU()).append(" CFU");
        return sb.toString();
    }
}
