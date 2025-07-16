package entity;

/**
 * Rappresenta un corso di studio con CFU, codice e denominazione.
 */
public class CorsoDiStudio {
    private int CFU;
    private String codice;
    private String Denominazione;

    /**
     * Costruttore che inizializza un corso di studio.
     * 
     * @param CFU numero di crediti formativi universitari del corso
     * @param codice codice univoco del corso
     * @param Denominazione nome o descrizione del corso
     */
    public CorsoDiStudio(int CFU, String codice, String Denominazione) {
        this.CFU = CFU;
        this.codice = codice;
        this.Denominazione = Denominazione;
    }

    /**
     * Restituisce il numero di CFU del corso.
     * 
     * @return CFU del corso
     */
    public int getCFU() {
        return CFU;
    }

    /**
     * Restituisce il codice del corso.
     * 
     * @return codice del corso
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Restituisce la denominazione del corso.
     * 
     * @return denominazione del corso
     */
    public String getDenominazione() {
        return Denominazione;
    }

    /**
     * Controlla se il corso è valido.
     * 
     * @return true se il corso ha CFU > 0 e codice e denominazione non nulli o vuoti, false altrimenti
     */
    public boolean isValido() {
        return CFU > 0 && codice != null && Denominazione != null && !Denominazione.trim().isEmpty();
    }

    /**
     * Controlla se il corso è compatibile con il piano di studi dati i CFU attuali e massimi.
     * 
     * @param cfuAttuali CFU già presenti nel piano di studi
     * @param cfuMassimi CFU massimi consentiti nel piano di studi
     * @return true se aggiungendo il corso non si supera il limite massimo di CFU
     */
    public boolean isCompatibileConPiano(int cfuAttuali, int cfuMassimi) {
        return (cfuAttuali + this.CFU) <= cfuMassimi;
    }

    @Override
    public String toString() {
        return "CorsoDiStudio{" +
                "CFU=" + CFU +
                ", codice='" + codice + '\'' +
                ", Denominazione='" + Denominazione + '\'' +
                '}';
    }

}
