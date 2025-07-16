package boundary.docenteboundary;

/**
 * Interfaccia per gestire la selezione di un corso.
 * Implementatori di questa interfaccia devono definire cosa succede
 * quando un corso viene selezionato tramite il metodo {@code onCorsoSelected}.
 */
public interface CorsoSelectionListener {
    
    /**
     * Metodo chiamato quando un corso viene selezionato.
     * 
     * @param denominazioneCorso la denominazione del corso selezionato
     */
    void onCorsoSelected(String denominazioneCorso);
}
