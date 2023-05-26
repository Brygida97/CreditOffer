# CreditOffer

Aplikacja CreditOffer pozwala na wybór przez klienta oferty najbardziej zbliżonej jego oczekiwaniom. Aplikacja ta została stworzona za pomocą frameworku Spring Boot. 

## Opis 

Aplikacja CreditOffer pozwala klientom na wyszukiwanie dostępnych ofert kredytowych, które zostały podzielone ze względu na bank, w który są one oferowane oraz ich typ. Głównym celem aplikacji jest prostota w użytkowaniu, jak i intuicyjność. Pozwoli ona na wybór najlepszej według klienta oferty, a w sytuacji braku takowej wysłanie do niego powiadomienia email, gdy się pojawi. 

## Funkcje 

- Przeglądanie ofert.
- Możliwość uzupełniania ofert nowymi. 
- Wysyłanie maila do wszystkich klientów w bazie podczas tworzenia nowej oferty
- Filtrowanie przez klienta ofert poprzez wskazanie kwoty kredytu, jego okresu, maksymalnego RRSO i prowizji, w przypadku braku znalezienia odpowieniej oferty, wyszukanie zostaje zapisane do tablicy i czeka na pojawienie się odpowiedniej oferty
- Tworzenie nowej oferty i wysyłanie jej mailem do klientów, którzy nie mogli znaleźć odpowieniej oferty, a nowa dodawana spełnia warunki ich wyszukiwania. Po wysyłce maila z odpowiadającą ofertą wyszukanie zostanie usunięte z bazy.
- Wyszukanie 3 ofert z najniższym RRSO.
- Wyszukanie 3 ofert z najniższą prowizją.
- Wyszukanie maksymalnie 3 ofert, które znajdują się w liście 3 ofert z najniższym RRSO i najniższą prowizją.
- Zliczenie ilości ofert dla każdego banku.

## Struktura projektu

W skład struktury projektu wchodzą:
- Encje:
  - Client, która zawiera w sobię informacje takie jak id, imię, nazwisko, email
  - Bank, która zawiera w sobię informacje takie jak id, nazwa banku
  - TypeOfLoan, która zawiera w sobię informacje takie jak id, typ kredytu 
  - Offer, która zawiera w sobię informacje takie jak id, nazwa, maksymalna i minimalne kwota, prowizja w procentach, RRSO, URL danej oferty, okres na jaki może zostać zawarty kredyt podany w miesiącach, bank oraz typ kredytu
  - SearchHistory, która zawiera w sobie informacje takie jak id, kwota, maksymalne RRSO, maksymalną prowizje oraz okres kredytowania oraz klienta
  W wyżej wymienionych encjach występują następujące relacje: 
  - OneToMany - Bank do Offer
  - OneToMany - TypeOfLoan do Offer 
  - ManyToOne - SearchHostory do Client
- Warstwy serwisów 
- Repozytoria Spring Data JPA
- Klasy transferowe (DTO)
- Mappery 
- Swagger 
- Kontrolery 
- Pliki z rozszerzeniem http, które można używać do puszczania żądań HTTP na różne endpointy.

## Instrukcja instalacji 

Aplikacja CreditOffer została napisana w SpringBoot. 
Należy:
1. Zweryfikować posiadanie na komputerze Java Development Kit (JDK), Maven, MySQL.
2. Skopiować repozytorium na swój komputer.
3. Skonfigurować bazę danych poprzez: 
  * stworznie bazy danych o nazwie "CreditOfferFinal"
  * zaktualizowanie pliku application.properties poprzez ustawienie nazwy użytkownika oraz hasła 
4. Skonfigurować dane potrzebne do wysyłki maili 
  * wprowadzenie w pliku application.properties swojego maila oraz hasła 
6. Uruchomienie aplikcji
