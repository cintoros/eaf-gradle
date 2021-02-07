# Lektion 13: Application Security
In dieser Lektion liegt der Fokus in der Implementation der der nicht-funktionale Anforderung *Security* und wie diese Anforderung mit Hilfe des Frameworks "Spring Security" umgesetzt werden kann, um die Enterprise Applikation "movierental.jpa" vor unberichtigtem Zugriff zu schützen.

Es geht in dieser Lektion um folgende Fragen:
* Wie wird eine Authentifizierung in den Web Service integriert?
* Wie wird eine Autorisierung in den Web Service aktiviert?
* Kann OAuth2 zur Authentifizierung eingesetzt werden?
* Wie können die verschiedenen Layer der Enterprise Applikation geschützt werden?

## Einleitung
Security ist ein grosses und umfangreiches Thema. Deshalb kann in dieser Lektion nur ein Einblick in das Framework "Spring Security" gegeben werden, das normalerweise bei einer Spring Applikation zum Einsatz kommt. Zusätzlich werden einige Konventionen von Spring Security in Spring Boot genutzt.

In erster Linie soll aber ersichtlich werden, wie die nicht-funktionale Anforderung *Security* in eine Spring Applikation integriert wird.

[Spring Security]() ist ein eigenständiges Projekt innerhalb des Spring Ecosystems. Eine ausführliche Dokumentation zum Framework findet man in der entsprechenden [Reference Documentation](https://docs.spring.io/spring-security/site/docs/5.4.2/reference/html5/).

## Ressourcen
Die Slides zu dieser Lektion gibt es in 2 Versionen:
* eine druckbare Version liegt auf dem AD
* eine vertonte Version finden sie auf diesem [SWITCHtube Channel](https://tube.switch.ch/channels/d7e129eb)

Auch die Arbeitsblätter und Übungen finden sie auf dem AD.

## Theorie
Da das Thema komplex ist, hat Spring zusätzliche Guides und Tutorials erstellt:
* [Spring Security Architecture](https://spring.io/guides/topicals/spring-security-architecture/): Dieser Guide fasst die wesentlichen Design Aspekte von Spring Security zusammen. Für die Beispiele werden die Konventionen von Spring Boot verwendet.
* [Spring Security Reference](https://docs.spring.io/spring-security/site/docs/5.4.2/reference/html5/): Die Referenz Dokumentation von Spring Security. Sie umfangreich. Interessant ist auch das Kapitel [Samples](https://docs.spring.io/spring-security/site/docs/5.4.2/reference/html5/#samples).
* Spring Boot [Security](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-security): Dieses Kapitel beschreibt die Integration von Spring Security in Spring Boot und erklärt die verwendet Konventionen.
* [Five Awkward Things about Spring Security that actually Make Sense](https://freecontent.manning.com/five-awkward-things-about-spring-security-that-actually-make-sense/): Interessanter Auszug aus dem Buch "Spring Security in Action".

Zu *OAuth2* findet man:
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/): Dieser Guide zeigt anhand unterschiedlicher Applikation wir OAuth mit einer Spring Applikation genutzt werden kann.
* [The Simplest Guide To OAuth 2.0](https://darutk.medium.com/the-simplest-guide-to-oauth-2-0-8c71bd9a15bb): Ausführlicher Blog-Beitrag über die Funktionsweise von OAuth2 mit vielen Bildern.

## Arbeitsblätter und Übungen
**Arbeiten sie in einem 2er-Team. Es macht mehr Spass!**

### AB6
Der Fokus dieses Arbeitsblattes ist die *Authentifzierung über OAuth2*. Dabei wird [GitHub](https://github.com/) als *Authorization Server* und *Resource Server* eingesetzt.

Nutzen sie das entsprechende Video [Einführung AB66](https://tube.switch.ch/videos/e8aa117a), wo der Umgang mit GitHub erklärt wird.

### UB13
Der Fokus in dieser Übung ist die *Autorisierung* in den beiden Varianten:
* Web Security
* Method Security

Im Gegensatz zu AB6 wird nun eine Authentifizierung mit *HTTP Basic* eingesetzt, so dass alle JUnit Tests und auch die Client Applikation `movierental.client` erfolgreich genutzt werden können - trotz geschütztem Web Service.

Ausgangspunkt ist das Projekt im Ordner `ub13/initial/movierental.jpa`.