informazioni per l'installazione

il progetto é stato sviluppato e testato su 
sistema microsoft windows.

prima dell'installazione é necessario 
installare postgress [postgresql-42.0.0.jre7.jar]
per la gestione del database.

la porta necessaria a stabilire il collegamento
é quella di default 5432, le 
credenziali:
un postgres
pw postgres

per compilare e lanciare il programma
installare ANT [apache-ant-1.10.1-bin]
e settare le variabili di 
sistema aggiungendo alla chiave path 
il percorso per arrivare alla cartella BIN 
di Ant.

installare JAVA [jre-8u121-windows-x64]
 e ripetere il medesimo procedimento
con riferimento alla cartella Bin di JAVA.

lanciare la SHELL di windws e aprire la cartella
dove risiede il file build.xml 

digitare: ant build.xml

il programma ad ogni avvio controlla l'esistenza 
del database e nel caso non ci fosse lo crea.
Medesimo controllo ad ogni avvio viene effettuato 
per l'esistenza delle tabelle.

i tipi di server ai quali ci si puo collegare sono
local, lan o www (macchina in servizio solo per 
il giorno dell'appello di laboratorio b dalla casa 
dello studente De Salvatore).

