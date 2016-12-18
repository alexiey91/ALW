# Progetto Algoritmi per il Web
## **Metriche per reti sociali con attributi vettoriali**
Si possono associare informazioni ai nodi e egli archi di una rete sociale tramite attributi. Gli attributi
di un nodo possono contenere un insieme di informazioni su un soggetto della rete (es. nome, domicilio),
e gli attributi di un arco possono descrivere il tipo e la fonte della comunanza e dell’interazione (es. e-mail,
SMS, account Facebook, Twitter, Whatsapp). Questi ultimi sono in particolare vettori di caratteristiche,
poichè i soggetti di una rete sociale possono avere allo stesso tempo relazioni di diverso tipo. Gli obiettivi
principali di questo progetto sono:
- Applicare diverse metriche e diversi indicatori di un grafo all’analisi di reti sociali usando sia temi
descritti nella letteratura scientifica dell’ultimo decennio, sia concetti originali tuttora in fase di
studio, elaborazione e sperimentazione;
- Sfruttare accuratamente le relazioni di diverso tipo di cui ogni rete é costituita per comprendere
meglio il ruolo svolto da ogni entità della rete.

## In particolare, il progetto si può articolare nelle seguenti tematiche:

- *Metriche di base*. L’obiettivo principale é quello di implementare e valutare anche sperimentalmente
alcune metriche classiche, come ad esempio: dimensione, densità, grado dei nodi, numero e
dimensioni delle componenti connesse o fortemente connesse, numero di nodi raggiungibili, numero
di punti di articolazione o punti di articolazione forti (“articulation points” e “strong articulation
points”), numero di ponti o ponti forti (“bridges” e “strong bridges”).
- *Metriche avanzate*. L’obiettivo principale é quello di progettare, implementare e valutare anche
sperimentalmente nuove metriche più evolute, partendo anche da metriche esistenti, come ad
esempio: centro, diametro, funzione di vicinanza (“neighbourhood function”) e loro generalizzazioni.
Poichè il calcolo delle metriche precedenti è computazionalmente oneroso, e diventa impossibile
per reti di grandi dimensioni, si prevede l’utilizzo di algoritmi approssimati come ad esempio
HyperANF.

##Indici di centralità:

L’obiettivo principale è quello di progettare, implementare e valutare anche
sperimentalmente nuovi indici di centralità di un nodo, partendo da indici già noti nella letteratura
scientifica, come ad esempio: centralità di grado, di vicinanza, di intermediazione, di
autovettore e centralità basata su clustering. Si propone di progettare, implementare e
valutare anche sperimentalmente nuovi indici di forza di un arco, come ad esempio incorporamento
(“embeddedness”) e dispersione. La progettazione dei nuovi indici di centralità e di forza deve
essere guidata dalla necessità di individuare i soggetti e i legami più importanti presenti in una rete
sociale.
