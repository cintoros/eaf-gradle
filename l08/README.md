# Lektion 8: AOP
In dieser Lektion wollen wir die Möglichkeiten kennenlernen *nicht-funktionale Anforderungen* als Komponenten zu implementieren und in die Enterprise Applikation einzubinden. Die *aspektorientierte Programmierung (AOP)* stellt uns dafür die geeigneten Ansatz bereit und *Spring AOP* eine geeignete Infrastruktur. In nicht-funktionale Anforderung wirdin diesem Kontext als Aspekt bezeichnet.

Es geht in dieser Lektion um folgende Fragen:
- Wie können Aspekte als Komponenten implementiert werden?
- Wo kann man einen Aspekt in den Programmablauf einbinden?
- Welceh Möglichkeiten bietet AOP?
- Wie setzt das Spring Framework AOP um?
- Wo sind die Grenzen von AOP bzw. von Spring AOP?
- Wie kann OOP und AOP kombiniert werden?

## Einleitung
Da AOP ein Kernkonzept des Spring Framework und im *Core* enthalten ist, findet man die relevanten Informationen in der entsprechenden [Spring Reference Documentation](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/index.html). Insbesonders das Kapitel [5. Aspect Oriented Programming with Spring](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#aop) beschreibt AOP und die Umsetzung in Spring detailliert.

## Theorie
Die notwenigen Theorie-Grundlagen sind in den Slides abgedeckt. Wichtig sind:
- Begriffe kennen und verstehen
- AOP vs. OOP erklären können
- Umsetzung von AOP in Spring erklären können: Proxy!

## Arbeitsblätter und Übungen
**Arbeiten sie in einem 2er-Team. Es macht mehr Spass!**

### AB4
Mit diesem Arbeitsblatt üben sie die verschiedenen Konzept von Spring AOP: *Advice Type*, *Pointcut Expression*, *Access Input Parameter* und *Access Response*. 

Ausgangspunkt ist das Projekt im Order `ab4/initial/movierental.jpa`.

### UB3
In dieser Übung werden sie eine neue nicht-funktionale Anforderung mit AOP umsetzen. Der Use Case ist wie folgt:

> Sie sind als Entwickler für die "movierental"-Applikation verantwortlich. Der Kunde haben nun entschlossen, dass ab heute keine Kinderfilme (PriceCategory=Children) mehr im Programm aufgenommen werden. Da aber die Installationsbasis der Client-Applikationen hoch ist, will der Kunde nicht alle Clients an die neue Anforderung anpassen. Sie schlagen deshalb vor, dass sie bei der Server-Applikation entsprechende Create und Update Requests abfangen und falls notwendig, eine PriceCategory=Children in eine PriceCategory=Regular umwandeln. Für diese nicht-funktionale Anforderungen wollen sie einen Interceptor als Aspekt mit Spring AOP implementieren.
