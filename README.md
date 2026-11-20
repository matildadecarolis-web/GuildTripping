# 🛡️ GuildTripping

GuildTripping è un gioco di ruolo gestionale a turni sviluppato in Java.
Il giocatore guida una gilda di tre eroi e ha 15 turni per prepararli all'**Assalto dei Goblin**.
Per ogni turno bisogna decidere come impiegare gli eroi tra addestramento, missioni e recupero, gestendo energia, salute e oro e affrontando imprevisti casuali.
Al termine dell'ultimo turno la forza della gilda, sommata al tiro di un dado, decide la vittoria o la sconfitta. 

---

## 🚀 Come eseguire il progetto

### Prerequisiti
- Java 25 (LTS)
- Gradle

### Istruzioni

```bash
git clone https://github.com/matildadecarolis-web/GuildTripping.git
cd GuildTripping
```

### Build del progetto
Dalla cartella principale del progetto:

```bash
./gradlew build
```

### Esecuzione
```bash
./gradlew run
```


---

## 🤖 Uso di strumenti di IA

Durante lo sviluppo ho utilizzato **Claude (Anthropic)** come tutor e revisore.
In particolare l'IA è stata usata per:

- revisionare il codice scritto da me rispetto ai principi SOLID e al clean code;
- supporto per testare il gioco e per bilanciare i valori, assicurando un gameplay fluido;
- risoluzione mirata di errori in fase di compilazione e supporto nella configurazione di Gradle.

📌 Per una descrizione più dettagliata delle scelte progettuali e dell'uso dell'AI, consultare la Wiki del repository.
