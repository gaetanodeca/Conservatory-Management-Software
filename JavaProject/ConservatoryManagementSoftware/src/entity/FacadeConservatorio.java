package entity;

import dto.DataDTO;
import exceptions.LoginFailedException;
import java.util.List;

/**
 * Facade singleton per il Conservatorio.
 * Fornisce un'interfaccia unificata per la gestione di studenti, docenti, esami, corsi e piani di studio.
 * Delega le chiamate ai relativi gestori (GestioneStudenti, GestioneDocente, ecc.).
 */
public class FacadeConservatorio {
    
    private static FacadeConservatorio uniqueInstance;

    /**
     * Costruttore privato per il pattern Singleton.
     */
    private FacadeConservatorio() {}

    /**
     * Restituisce l'istanza singleton di FacadeConservatorio.
     * La crea se non esiste.
     * 
     * @return istanza singleton di FacadeConservatorio
     */
    public static FacadeConservatorio getInstance(){
        if(uniqueInstance == null) {
            uniqueInstance = new FacadeConservatorio();
        }
        return uniqueInstance;
    }
    
    /**
     * Restituisce lo studente corrispondente al username.
     * 
     * @param username username dello studente
     * @return oggetto Studente se trovato, null altrimenti
     */
    public Studente getStudenteByUsername(String username) {
        return GestioneStudenti.getInstance().getStudenteByUsername(username);
    }
    
    /**
     * Autentica uno studente controllando username e password.
     * 
     * @param username username dello studente
     * @param password password dello studente
     * @return true se autenticazione avvenuta con successo, altrimenti false
     * @throws LoginFailedException in caso di errore di autenticazione
     */
    public boolean autenticaStudente(String username, String password) throws LoginFailedException {
        return GestioneStudenti.getInstance().autenticazione(username, password);
    }

    private GestioneDocente gDocente = GestioneDocente.getInstance();

    /**
     * Aggiunge un nuovo docente al sistema.
     * 
     * @param nome nome del docente
     * @param cognome cognome del docente
     * @param password password del docente
     * @return stringa di successo o messaggio di errore
     */
    public String aggiungiDocente(String nome, String cognome, String password) {
        return gDocente.aggiungiDocente(nome, cognome, password);
    }
   
    /**
     * Inserisce un nuovo verbale associato a un docente e una data.
     * 
     * @param data data del verbale (es. data appello)
     * @param matricola matricola del docente
     * @return DataDTO contenente id verbale e lista dei corsi associati, o id negativi in caso di errori
     */
    public DataDTO InserisciVerbale(String data, String matricola){
        GestioneDocente gDoc= GestioneDocente.getInstance();
        GestioneCorsiDiStudio gCDS = GestioneCorsiDiStudio.getInstance();

        List<String> corsi = gCDS.findbyDocente(matricola , data);
        Docente docente = gDoc.checkDocente(matricola);

        if (docente == null) {
            return new DataDTO(-1, null); // docente non trovato
        }
        if (corsi == null) {
            return new DataDTO(-2, null); // nessun corso trovato
        }

        int id_verbale = docente.inserisciVerbale(data);
        return new DataDTO(id_verbale, corsi);
    }
    
    /**
     * Inserisce un esame associato a uno studente e un verbale.
     * 
     * @param matricola_studente matricola dello studente
     * @param voto voto dell'esame
     * @param lode true se con lode, false altrimenti
     * @param note note sull'esame
     * @param id_verbale identificativo del verbale associato
     * @return true se inserimento andato a buon fine, false altrimenti
     */
    public boolean InserisciEsame(String matricola_studente, int voto, boolean lode, String note, int id_verbale){
        GestioneVerbali gVerb = GestioneVerbali.getInstance();
        GestioneStudenti gStud = GestioneStudenti.getInstance();
        
        boolean esito = gStud.checkStudente(matricola_studente);
        
        if (esito) {
            gVerb.inserisciEsame(matricola_studente, voto, lode, note, id_verbale);
            return true;
        }
        
        return false;
    }
    
    // Gestione Piano di Studi - Delega a GestionePianiDiStudio
    private GestionePianiDiStudio gestionePiano = GestionePianiDiStudio.getInstance();
    
    // Gestione Corsi di Studio - Delega a GestioneCorsiDiStudio
    private GestioneCorsiDiStudio gestioneCorsi = GestioneCorsiDiStudio.getInstance();
    
    // Gestione Corsi Selezionati - Delega a GestioneCorsiSelezionati
    private GestioneCorsiSelezionati gestioneCorsiSelezionati = GestioneCorsiSelezionati.getInstance();
    
    /**
     * Crea un nuovo piano di studi.
     * 
     * @param idPiano ID univoco del piano
     * @param nomePiano nome descrittivo del piano
     * @return true se creazione avvenuta con successo, false altrimenti
     */
    public boolean creaNuovoPianoDiStudi(int idPiano, String nomePiano) {
        return gestionePiano.creaNuovoPianoDiStudi(idPiano, nomePiano);
    }
    
    /**
     * Salva il piano di studi con i corsi selezionati.
     * 
     * @param idpiano ID del piano di studi
     * @param corsiSelezionati lista di corsi selezionati
     * @return true se il salvataggio ha successo, false altrimenti
     */
    public boolean salvaPianoDiStudi(int idpiano, List<CorsoDiStudio> corsiSelezionati) {
        if (corsiSelezionati != null) {
            return gestionePiano.salvaPianoDiStudi(idpiano, corsiSelezionati);
        }
        return false;
    }
    
    // Metodi delegati per la gestione corsi selezionati
    
    public boolean aggiungiCorso(CorsoDiStudio corso) {
        return gestioneCorsiSelezionati.aggiungiCorso(corso);
    }
    
    public boolean rimuoviCorso(CorsoDiStudio corso) {
        return gestioneCorsiSelezionati.rimuoviCorso(corso);
    }
    
    public boolean rimuoviCorsoPerCodice(String codice) {
        return gestioneCorsiSelezionati.rimuoviCorsoPerCodice(codice);
    }
    
    public List<CorsoDiStudio> getCorsiSelezionati() {
        return gestioneCorsiSelezionati.getCorsiSelezionati();
    }
    
    public void svuotaPianoStudi() {
        gestioneCorsiSelezionati.svuotaPianoStudi();
    }
    
    public int getTotaleCFU() {
        return gestioneCorsiSelezionati.getTotaleCFU();
    }
    
    public int getNumeroCorsiSelezionati() {
        return gestioneCorsiSelezionati.getNumeroCorsiSelezionati();
    }
    
    public CorsoDiStudio cercaCorsoSelezionatoPerCodice(int codice) {
        return gestioneCorsiSelezionati.cercaCorsoSelezionatoPerCodice(codice);
    }
    
    public boolean isCorsoSelezionato(int codice) {
        return gestioneCorsiSelezionati.isCorsoSelezionato(codice);
    }
    
    public boolean isPianoDiStudiValido() {
        return gestioneCorsiSelezionati.isPianoDiStudiValido();
    }
    
    public String getSommarioPianoStudi() {
        return gestioneCorsiSelezionati.getSommarioPianoStudi();
    }
    
    /**
     * Seleziona un corso tramite GUI.
     * 
     * @param CFU crediti formativi universitari del corso
     * @param codice codice identificativo del corso
     * @param denominazione nome del corso
     * @return true se il corso è stato selezionato correttamente, false altrimenti
     */
    public boolean selezionaCorsoPerGUI(int CFU, String codice, String denominazione) {
        return gestioneCorsiSelezionati.selezionaCorsoPerGUI(CFU, codice, denominazione);
    }
    
    /**
     * Stampa le statistiche sui corsi selezionati.
     */
    public void stampaStatistiche() {
        gestioneCorsiSelezionati.stampaStatistiche();
    }
    
    /**
     * Mostra i corsi selezionati come stringa formattata.
     * 
     * @return stringa rappresentante i corsi selezionati
     */
    public String mostraCorsiSelezionati() {
        return gestioneCorsiSelezionati.mostraCorsiSelezionati();
    }

    /**
     * Ottiene i corsi disponibili per categoria Canto.
     * 
     * @return lista dei corsi di Canto
     */
    public List<CorsoDiStudio> getCorsiPerCanto() {
        return gestioneCorsi.getCorsiPerCanto();
    }
    
    /**
     * Ottiene i corsi disponibili per categoria Strumento.
     * 
     * @return lista dei corsi di Strumento
     */
    public List<CorsoDiStudio> getCorsiPerStrumento() {
        return gestioneCorsi.getCorsiPerStrumento();
    }
    
    /**
     * Ottiene i corsi disponibili per categoria Composizione.
     * 
     * @return lista dei corsi di Composizione
     */
    public List<CorsoDiStudio> getCorsiPerComposizione() {
        return gestioneCorsi.getCorsiPerComposizione();
    }
    
    /**
     * Ottiene i corsi disponibili per categoria Musica d'Insieme.
     * 
     * @return lista dei corsi di Musica d'Insieme
     */
    public List<CorsoDiStudio> getCorsiPerInsieme() {
        return gestioneCorsi.getCorsiPerInsieme();
    }
    
    /**
     * Ottiene i corsi disponibili per categoria Didattica.
     * 
     * @return lista dei corsi di Didattica
     */
    public List<CorsoDiStudio> getCorsiPerDidattica() {
        return gestioneCorsi.getCorsiPerDidattica();
    }
}
