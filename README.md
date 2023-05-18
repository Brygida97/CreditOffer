# CreditOffer

Aplikacja CreditOffer pozwala na wybór przez klienta oferty najbardziej zbliżonej jego oczekiwaniom. Aplikacja ta została stworzona za pomocą frameworku Spring Boot. 

## Opis 

Aplikacja CreditOffer pozwala klientom na wyszukiwanie dostępnych ofert kredytowych, które zostały podzielone ze względu na bank, w który są one oferowane oraz ich typ. Głównym celem aplikacji jest prostota w użytkowaniu, jak i intuicyjność. Pozwoli ona na wybór najlepszej według klienta oferty, a w sytuacji braku takowej wysłanie do niego powiadomienia email, gdy się pojawi. 

## Funkcje 

- Przeglądanie ofert pod kątem:
  - banku udzielającego 
  - rodzaju oferty 
  - wysokości prowizji podawanej w procentach 
  - wysokości RRSO
  - okresu podawanego w miesiącach 
  - maksymalnej i minimalnej kwoty 
- Wysłanie powiadomienia w formie maila, który poinformuje go o dodaniu poszukiwanej przez niego oferty 
- Możliwość uzupełniania ofert nowymi 

## Struktura projektu

W skład struktury projektu wchodzą:
- Encje:
  - Client, która zawiera w sobię informacje takie jak id, imię, nazwisko, email
  - Bank, która zawiera w sobię informacje takie jak id, nazwa banku
  - TypeOfLoan, która zawiera w sobię informacje takie jak id, typ kredytu 
  - Offer, która zawiera w sobię informacje takie jak id, nazwa, maksymalna i minimalne kwota, prowizja w procentach, RRSO, URL danej oferty, okres na jaki może zostać zawarty kredyt podany w miesiącach, bank oraz typ kredytu
  W wyżej wymienionych encjach występują następujące relacje: 
  - OneToMany - Bank do Offer
  - OneToMany - TypeOfLoan do Offer 
- Warstwy serwisów 
- Repozytoria Spring Data JPA
- Klasy transferowe (DTO)
- Mappery 

## Instrukcja instalacji 

Aplikacja CreditOffer została napisana w SpringBoot. 
Należy:
1. Zweryfikować posiadanie na komputerze Java Development Kit (JDK), Maven, MySQL.
2. Skopiować repozytorium na swój komputer.
3. Skonfigurować bazę danych poprzez: 
  * stworznie bazy danych o nazwie "CreditOfferFinal"
  * zaktualizowanie pliku application.properties poprzez ustawienie nazwy użytkownika oraz hasła 
4. Uruchomienie aplikcji
