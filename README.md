# Projekt Bazy Danych
### *Michał Kurleto*
## Generowanie faktur PDF dla bazy Northwind (mySQL)

W aplikacji podajemy z jakiego okresu czasu bierzemy zamówienia do faktury oraz dla jakiej firmy. Program automatycznie generuje odpowiednią fakturę i umieszcza ją w folderze **invoices**.

- Wszystkie zamodelowane encje z bazy Northwind znajdują się w folderze **/src/entities**

- Klasa **OrderData** odpowiada za pobieranie i przechowywanie danych z zamówień potrzebnych do faktury. Dane są pobierane za pomocą zapytania do bazy danych.

- Klasa **Invoice** odpowiada agregację zamówień i przygotowanie faktury

- Klasa **InvoicePDFBuilder** tworzy fakturę w formacie PDF.

### Przykładowa faktura znajduje się w folderze **invoces**

## Schemat Bazy Danych
![Schemat Bazy](resources/baza.jpg)
