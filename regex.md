## REGEX HOW TO
**regole base:**

* `[…]` Insieme di caratteri validi;
* `[^…]` Insieme negato di caratteri validi;
* `–` Intervallo;
* `&&` Intersezione;
* `.` Qualunque carattere;
* `+` Concatenazione;
* `^regex` il carattere ^ indica che il match deve essere all’inizio della linea
* `regex$` il carattere $ indica che il match deve essere alla fine della linea
* `regex*` Cardinalità multipla (0 o più occorrenze dell’espressione regex);
* `regex{n}` Cardinalità multipla (esattamente n occorrenze dell’espressione regex);
* `regex{n,}` Cardinalità multipla (almeno n occorrenze dell’espressione regex);
* `regex{n,m}` Cardinalità multipla (almeno n occorrenze dell’espressione regex, ma non più di m).

**Esempi**

* `[abc]`  Espressione che contiene un solo carattere tra a, b, o c;
* `[^abc]`  Espressione che contiene un solo carattere, tutti sono validi, ad esclusione di a, b, o c;
* `[a-z]`  Espressione che contiene un solo carattere tra quelli del range a-z;
* `[a-d[m-p]]`  Espressione che contiene un solo carattere tra quelli del range a-d e del range m-p (unione);
* `[a-z&&[def]]`  Espressione che contiene un solo carattere tra quelli comuni del range a-z e dell’insieme (d, e, f) (intersezione);
* `[a-z&&[^bc]]`  Espressione che contiene un solo carattere tra quelli comuni del range a-z e dell’insieme negato (b, f) (intersezione);
* `[a-z&&[^m-p]]`  Espressione che contiene un solo carattere tra quelli comuni del range a-z e del range negato (m-p) (intersezione).
* `[x|y]`  Espressione che contiene un solo carattere che può essere x oppure y

**Quantificatori**
* `a?` 	il carattere “a” occorre zero o una volta
* `a*` 	il carattere “a” occorre zero o più volte
* `a+` 	il carattere “a” occorre una o più volte
* `a{n}` 	il carattere “a” occorre esattamente n volte
* `a{n,}` 	il carattere “a” occorre n o più volte
* `a{n,m}` 	il carattere “a” occorre almeno n volte ma non più di m volte

### REGEX notevoli

**linea vuota:**
```
^$
```

**indirizzo email:**
```
[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}
```

**data in formato gg/mm/aaaa:**
```
(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\d\d
```

**ora in formato 12 ore:**
```
(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)
```

**ora in formato 24 ore:**
```
([01]?[0-9]|2[0-3]):[0-5][0-9]
```

**url http:**
```
http\://[a-zA-Z0-9\-\.]+\.[a-zA-Z]{2,3}(/\S*)?
```

**codice fiscale:**
```
[a-zA-Z]{6}\d\d[a-zA-Z]\d\d[a-zA-Z]\d\d\d[a-zA-Z]
```

**nome utente:**
```
^[a-z0-9_-]{3,15}$
```
(nome utente formato da soli caratteri alfanumerici più  _ e – di lungezza min 3 e max 15)

**password:**
```
((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})
```
(password che deve contenere un numero, un carattere minuscolo, uno maiuscolo e un carattere speciale tra @#$% e deve avere lunghezza min 8 e max 20)

**tag HTML:**
```
<([A-Z][A-Z0-9]*)\b[^>]*>(.*?)
```

**uno specifico tag HTML:**
```
<TAG\b[^>]*>(.*?)
```

**codice esadecimale colore:**
```
^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$
```

**estensione di un file immagine:**
```
([^\s]+(\.(?i)(jpg|png|gif|bmp))$)
```

**Indirizzo IP:**
```
^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.
([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$
```
