IDEA plugin pro podporu experimentálního procesního jazyka pro SmartCase. Plugin zajišťuje překlad .sc syntaxe do .json, která je běžná pro SmartCase. Kromě toho umožňuje i upload přeloženého souboru.

Motivace:
* zpřehlednit zápis procesu
* zmenšit velikost souborů
* umožnit sdílení částí procesu
* umožnit tvorbu unit-testů
* umožnit ladění procesu

## Základní struktura 
Předpokládá se standardní struktura projektu vč. modulů, může být libivolný typ, testováno na Java modulech. 
Modul obsahuje jeden nebo více souborů, primární je soubor s příponou .sc, který obsahuje definici procesu 
(dříve .json soubor).

```java
process Test1 {
 ...   
}
```

## Atributy
Atributy se zapisují v Java-like syntaxi. Josu použity běžné datové typy, atributy mohou obsahovat komentáře - 
ty se následně přenáší do pole "description".

```java
process Test1 {

   // First name
   String firstName = "John Doe";
}
```

## Tasky
Tasky se zapisují do samostatných souborů, v .json nebo .sc tvaru. Umístění souboru může být i v nadřazené struktuře
(pak se použije např. "../task1.json".

```java 
process Test1 {

   // Zadání požadavku
   task Task1 from "task1.json";
}

```

## Transitions
Transitions se zapisují v rámci souboru s definicí procesu.  

```java 
process Test1 {

   // Start to zadání
   transition START -> T1 {
     ...
   }
}

```

## Testy
Testy jsou součástí zápisu procesu. Předpokládá se, že syntaxe testu bude odpovídat testovaný oblasti:
* test transition mezi tasky/aktivitami
* test volání služeb (automatické úkoly)
* atd.

Testy je možné spouštět v rámci vývoje, nejsou součástí výsledného .json souboru.
Je možné do kódu testu umístit breakpoint a test krokovat, zkoumat/měnit proměnné apod.

```java 
process Test1 {

   // Kontrola transition 
   test start2T1 {
     ...
   }
}

```

## Editor
Fíčurky co se budou hodit
* syntax coloring
* instantní zvýrazňování chyb
* proklik na otevření souboru s definicí tasku
* folding testů a transitions
* komentáře v kódu
