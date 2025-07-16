package entity;

import dao.DAOEsame;

/**
 * Rappresenta un esame sostenuto da uno studente, con voto, eventuale lode, note e riferimento al verbale.
 */
public class Esame {

    private int id_esame;
    private int valutazione;
    private boolean lode;
    private String note;
    private int idVerbale;
    private String matricola_studente;
    private DAOEsame daoEs;

    /**
     * Costruttore per un esame con ID esistente.
     * 
     * @param id ID univoco dell'esame
     * @param v voto conseguito all'esame
     * @param l true se con lode, false altrimenti
     * @param note note aggiuntive sull'esame
     * @param id_v ID del verbale associato
     */
    public Esame(int id, int v, boolean l, String note, int id_v) {
        this.id_esame = id;
        this.lode = l;
        this.valutazione = v;
        this.note = note;
        this.idVerbale = id_v;
    }

    /**
     * Costruttore per un nuovo esame da inserire, crea anche l'oggetto DAOEsame per il salvataggio.
     * 
     * @param matricolaStudente matricola dello studente che ha sostenuto l'esame
     * @param v voto conseguito all'esame
     * @param l true se con lode, false altrimenti
     * @param note note aggiuntive sull'esame
     * @param id_v ID del verbale associato
     */
    public Esame(String matricolaStudente, int v, boolean l, String note, int id_v) {
        this.lode = l;
        this.valutazione = v;
        this.note = note;
        this.idVerbale = id_v;
        this.matricola_studente = matricolaStudente;

        int newlode = l ? 1 : 0;

        this.daoEs = new DAOEsame(matricolaStudente, v, newlode, note, id_v);
    }
}
