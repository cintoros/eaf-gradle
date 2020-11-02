# Lektion 9: Transactions & Transaction Strategy
In dieser Lektion werden wir mit den *Transactions* eine konkrete Umsetzung von AOP im Spring Framework nutzen (siehe Abbildung 1). 

<img src="tx.png"/>

Abbildung 1: Implementation des Transaction-Supports mit Spring AOP

Wir werden
- Transactions
- Transaction Strategy

vertieft behandeln. Diese beiden Themen wurden bereits in der JPA Lektion kurz angesprochen.

Es geht in dieser Lektion um folgende Fragen:
- Wo sind sinnvolle Transaktionsgrenzen?
- Welche Möglichkeiten bietet Spring, um die Transaktion zu kontrollieren?
- Muss jede Operation in einer Transaktion laufen?
- Gibt es eine sinnvolle Transaktionsstrategie für Enterprise Applikationen?

## Einleitung
Um das Transaktionshandling in einer Spring Enterprise Applikation einzuführen, gibt es im Spring-Umfeld mehrere Möglichkeiten. Die meistverwendete Variante nutzt die Annotation `@Transactional`. Diese Annotation wird auch in dieser Lektion zentral sein.

**Reminder**: In JPA werden die Entitäten im `PersistenceContext` verwaltet. Die Manipulationen an diesen JPA-Entitäten müssen in einer Transaktion eingehüllt werden. Das betrifft alle "schreibenden" Methoden (persist, remove, merge, refresh, flush, refresh). 

Die `@Transactional` Annotation macht das Transaktionshandling in Spring relativ einfach. Es genügt die entsprechende Methode mit dieser Annotation zu kennzeichnen und das Transaktionshandling wird automatisch durchgeführt, d.h. eine Transaktion wird beim Aufruf der Methode gestartet und beim Verlassen der Methode wird die Transaktion mit `commit` beendet - oder es wird ein `rollback` ausgelöst.

Voraussetzungen für das korrekte Verhalten sind:
1.	Die mit `@Transactional` annotierten Methoden müssen "public" sein.
2.	Das Transaction Management muss aktiviert sein. Dies wird mit der Annotatiopn `@EnableTransactionManagement` erreicht. Falls Spring Boot jedoch im CLASSPATH eine Abhängigkeit von `spring-data-*` oder `spring-tx` findet, wird das Transaction Management per Konvention aktiviert.
3. Die Aufruf muss von ausserhalb der Instanz (=Target) kommen (siehe AOP).

**Hinweis**: Obwohl `@Transactional` sowohl in Spring als auch in JavaEE (Package `javax.transaction`) vorhanden ist, werden wir die Annotation von Spring Framework verwenden. Es ist im Allgemeinen eine bessere Praxis, da es für Spring-Applikationen natürlicher ist und gleichzeitig mehr Optionen wie Timeout, Isolierung usw. bietet.

## Theorie
Die notwendigen Theorie-Grundlagen sind in den Slides abgedeckt.
Wichtig ist:
- *Transaction Strategy* verstehen, erklären und einsetzen können

Hier aber dennoch ein Link auf einen interessanten [Blog-Post](https://codete.com/blog/5-common-spring-transactional-pitfalls/)

## Arbeitsblätter und Übungen
**Arbeiten sie in einem 2er-Team. Es macht mehr Spass!**

### AB5
Mit diesem Arbeitsblatt sollen sie verschiedene Aspekte der Spring Transaktionen implementieren und erfahren, wie diese *Transaktionen* in einer Spring Enterprise Applikation funktionieren.

Beachten sie, dass in diesem Arbeitsblatt nicht mit der `movierental.jpa` Applikation gearbeitet wird. In `ab5/initial` steht ihnen eine kleinere, einfachere Applikation mit entsprechenden Unit-Tests zur Verfügung.

### UB4
Zum Abschluss sollen sie mit dieser Übung die Transaktionsstrategie aus den Slides, mit den Erfahrungen aus AB5, in der Enterprise Applikation `movierental.jpa` umsetzen.

In `ub4/complete` werden sie eine Lösung für die Gesamtapplikation `movierental` finden, die sie parallel zur Docker-Variante starten können. Der `server.port` ist auf `8090` gesetzt und die Client-Applikation hat ein entsprechendes File `application.properties` im Projekt-Root, das sie nutzen können.
