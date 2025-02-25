# IDS_24_25
## Descrizione del Progetto

Il progetto *Filiera_Francoletti_Belardinelli_Raiola* è un'applicazione Spring Boot che simula una filiera integrata in cui diversi attori interagiscono per gestire prodotti ed eventi.  
In questo sistema:
- *Venditori* creano e pubblicano prodotti (inizialmente non verificati).
- *Curatori* verificano i prodotti, rendendoli visibili sulla piattaforma solo se conformi.
- *Acquirenti* possono navigare e acquistare i prodotti, gestendo un carrello e completando i pagamenti, ottenendo una ricevuta.
- *Animatori* organizzano eventi e invitano i venditori a partecipare, promuovendo l’attività della filiera.

## Funzionalità Principali

- *Creazione e Gestione dei Prodotti:*  
  I venditori possono creare prodotti (con uno stato iniziale non verificato) e solo dopo la verifica da parte del curatore, i prodotti diventano visibili.
  
- *Verifica dei Prodotti:*  
  I curatori possono visualizzare l’elenco dei prodotti in attesa di verifica e, dopo averli controllati, impostare lo stato di un prodotto a “verificato”, rendendolo disponibile sulla piattaforma.
  
- *Gestione del Carrello e Pagamenti:*  
  Gli acquirenti possono aggiungere prodotti al carrello, rimuoverli e completare l’acquisto generando una ricevuta.
  
- *Gestione degli Eventi:*  
  Gli animatori possono creare eventi e invitare i venditori a partecipare, visualizzando tutti gli eventi creati.
