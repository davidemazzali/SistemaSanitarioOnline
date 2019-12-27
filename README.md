# Progetto corso Introduzione alla Programmazione Web 2018/2019 - Sistemi di Servizi Sanitari per il cittadino

### LINK CHE SPIEGA COME IMPOSTARE IL DEFAULT USER IN POSTGRESQL:
https://chartio.com/resources/tutorials/how-to-set-the-default-user-password-in-postgresql/


### Hint su DAO

Se vi serve retrievare delle cose dal db, tenete a mente alcune cose:
* (esempio, giusto per essere sicuro) se vi serve sapere quali sono i pazienti di un medico, NON dovete fare UserDAO.getAll(), e poi fare un for per cercare tra questi quelli che hanno medicodibase=un certo id. Le query fatte dal db sono molto più efficienti!

* se vi servono dei dati dal db e non c'è un metodo che ve li dia, bisogna aggiungerlo. Quello che dovete fare è aggiungere la firma del metodo in EntitàDAO, e poi implementarlo in JDBCEntitaDAO
* In ogni JDBCEntitaDAO c'è un metodo privato che se gli passate un resultset vi ritorna un oggetto di tipo Entita. Il problema è che il corpo di questo modo è dipendente dai campi che selectate nella query, quindi se lo usate e non funziona, questo potrebbe essere il motivo
- per usare i dao e i factory,guardate come ha fatto chirico. In pratica ci sono da usare quasi sempre le interfacce (quindi EntitaDAO, non JDBCEntitaDAO), in modo da avere indipendenza dell'implementazione e dal db

### CREDENZIALI HEROKU

* **Host**: ec2-46-137-159-254.eu-west-1.compute.amazonaws.com
* **Database** d153tkq61mq1d8
* **User** vxlrlcdqeyksmt
* **Port** 5432
* **Password** f670a6e09622e7bce18e5877ccb6082cf7ed0153e8a9759d5acc8ab1467ff503
* **URI** postgres://vxlrlcdqeyksmt:f670a6e09622e7bce18e5877ccb6082cf7ed0153e8a9759d5acc8ab1467ff503@ec2-46-137-159-254.eu-west-1.compute.amazonaws.com:5432/d153tkq61mq1d8
