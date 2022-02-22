GENERATORE HIBERNATE by Bass91

-Copiare i file nella cartella del progetto
-Modificare HIBERNATEgenerator sulla base dei commenti presenti al suo interno
-Eseguire

Cosa fa:
-generazione hbm.xml (incompleto, leggere sotto)
-generazione bean relativo

Cosa NON fa (FARE A MANO SULLA BASE DEI COMMENTI!!):
-generazione dei metodi setter/getter
-creazione dei set, di tabelle aggiuntive e relazioni (1-n,n-m,ecc...)
-import automatici
-modifica del file HibernateTestWithTables (tabelle da scrivere a mano)


Generatore Relazioni Hibernate by adl
Passi da seguire:
1) Generare .java e .hbm.xml usando il generatore precedente
2) Generare le associazioni e copia incollarle nei file
3) Spostare tutti i file nel package hibernate
4) Generare i getter e setter stramite eclipse
5) Generare i costruttori (opzionale)
6) Modificare hibernate.cfg.xml
7) Svolgere l' esercizio
	- creare entita' di prova
	- generare relazioni
	- fare esercizio