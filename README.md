![Logo DIETI](https://github.com/user-attachments/assets/b7927067-e6cc-465f-ad9f-895c19b598e1)

# Conservatory Management Software

Progetto di Ingegneria del Software a.a. 2024/2025, seguito dalla prof. A.R. Fasolino.
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
## SPECIFICHE INFORMALI

Si vuole realizzare un sistema per la gestione dei corsi di laurea di un conservatorio, che offra diverse funzionalità per studenti, docenti e segreteria.

# LOGO

<img width="1024" height="1024" alt="Image" src="https://github.com/user-attachments/assets/a4f7e6e2-ca41-4aca-b99f-af90076faf82" />

## TECNOLOGIE UTILIZZATE
- **IDE**: 
  - Visual Studio Code
  - Eclipse IDE
- **Database**:
  - MySQL
- **Connettività DB**:
  - JDBC
- **Interfaccia Grafica**: 
  - JFrame
  - WindowBuilder
- **Testing**: 
  - JUnit 4

## DESCRIZIONE SISTEMA

Il sistema prevede cinque tipologie di corsi di studio: canto, strumento, composizione, musica d’insieme e didattica della musica. Per i corsi di canto, sono offerti i corsi di studio in canto lirico e canto jazz. Per i corsi di studi in strumento sono disponibili i corsi di pianoforte, violino, chitarra, ecc. Un corso può essere propedeutico a zero o più corsi.

Gli studenti possono scegliere di aderire a piani di studio personalizzati, scegliendo un insieme di corsi da includere nel proprio piano di studi. A tal fine, lo studente deve autenticarsi al sistema, visualizzare l’insieme dei corsi disponibili, e creare il proprio piano di studi personalizzato aggiungendo ad esso un insieme di corsi a scelta.

La segreteria studenti utilizza il sistema per aggiungere e modificare docenti e corsi, e in ogni anno accademico per associare un docente all’edizione del corso di quell’anno. Un corso ha un codice, una denominazione e un numero di crediti formativi (CFU). I docenti hanno una matricola, un cognome e un nome. (Per semplicità, non è prevista suddivisione di un corso in più cattedre.)

I dati degli studenti iscritti (comprese le loro credenziali di accesso, username, password e PIN) provengono dall’anagrafe degli studenti del Conservatorio: periodicamente, il sistema preleva i dati dal sistema Anagrafe Studenti.

Il sistema permette ai docenti di mantenere un registro che memorizza gli esami sostenuti dagli studenti per un determinato corso, riportando la valutazione in trentesimi, l’eventuale lode, le note del docente in testo libero e la data del superamento dell’esame.

Una volta completato un appello di esami in una determinata data, il docente procede alla verbalizzazione degli esami superati aprendo una “camicia elettronica” (lista degli studenti esaminati con voto) che costituisce il verbale e riporta un codice identificativo univoco, la data dell’appello, ed il codice del corso. L’inserimento degli esami superati nel verbale è effettuato dai docenti, i quali inseriranno la matricola ed il voto per ciascuno studente.

All’atto della chiusura della camicia, il sistema verifica per ognuno degli studenti il conseguimento da parte dello studente degli esami propedeutici. Nel caso in cui almeno un esame propedeutico non sia stato sostenuto da uno studente, il sistema annulla l’esame e fornisce un messaggio di errore, altrimenti gli esami vengono memorizzati nel sistema.

Gli studenti possono accedere al sistema per visualizzare gli esami sostenuti con i voti. Inoltre, possono visualizzare la media aritmetica e la media ponderata.
