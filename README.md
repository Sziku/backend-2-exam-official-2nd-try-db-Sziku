Hogyan készül egy rádiós lejátszási toplista? Még szép, hogy adatbázis segítségével!
Lássuk, neked sikerül-e?

# Adatbázis

Az adatbázisban egyetlen tábla van `music_broadcast` néven, az alábbi oszlopokkal:

- `artist` varchar
- `song` varchar
- `times_aired` int

Az adatbázisba havonta kerül be, hogy egy-egy számot hányszor játszottak a rádiók. 
Az egyes hónapok adatai külön sorba kerülnek, így egy előadő egy száma többször is szerepelhet. Például:

| artist         | song             | times_aired |
|----------------|------------------|------------:|
| Chris Isaak    | Let Me Down Easy | 40          |
| Apey & the Pea | Nazareth         | 20          |
| Son Lux        | Ransom           | 20          |
| Radiohead      | You              | 5           |
| Son Lux        | Dream State      | 25          |
| Chris Isaak    | Let Me Down Easy | 20          |

*FONTOS:* Nem kell adatbázist elindítani vagy létrehozni. Az adatbázist a teszt esetek automatikusan létrehozzák ls feltöltik adatokkal, nektek csak kapcsolódni kell hozzá a megadott adatok alapján (DB_URL, USERNAME, PASSWORD) és lekérdezni belőle a szükséges adatokat

# Java alkalmazás

Implementáld a `RadioCharts` osztályt, ami konstruktor paraméterben megkapja az adatbázis eléréshez szükséges adatokat (url, user, password). Az osztályban hozd létre az alábbi metódusokat:

- `getArtists()`: visszatér az összes előadóval, ABC sorrendben, ismétlődések nélkül.
- `getMostPlayedSong()`: adja vissza annak a számnak a címét a nevét, amelyiket az évben a legtöbbször játszottak a rádiók.
- `getMostActiveArtist()`: adja vissza annak az előadónak a nevét, akinek a legtöbb száma szerepel az adatbázisban (ugyanaz a szám többször lejátszva egynek számít).

Ha nincsenek lejátszási adatok, vagy hiba történik, akkor adjon vissza mindkét metódus (`getMostPlayedSong, 
getMostActiveArtist`) üres `String`-et.
Döntetlen esetén az ABC sorrend szerint előbbi előadót, vagy számot kell visszaadni.

## Pontozás

Egy feladatra 0 pontot ér, ha:

- nem fordul le
- lefordul, de egy teszteset sem fut le sikeresen.
- ha a forráskód olvashatatlan, nem felel meg a konvencióknak, nem követi a clean code alapelveket.

0 pont adandó továbbá, ha:

- kielégíti a teszteseteket, de a szöveges követelményeknek nem felel meg

Pontokat a további működési funkciók megfelelősségének arányában kell adni a vizsgafeladatra:

- 5 pont: az adott projekt lefordul, néhány teszteset sikeresen lefut, és ezek funkcionálisan is helyesek. Azonban több
  teszteset nem fut le, és a kód is olvashatatlan.
- 10 pont: a projekt lefordul, a tesztesetek legtöbbje lefut, ezek funkcionálisan is helyesek, és a clean code elvek
  nagyrészt betartásra kerültek.
- 20 pont: ha a projekt lefordul, a tesztesetek lefutnak, funkcionálisan helyesek, és csak apróbb funkcionális vagy
  clean code hibák szerepelnek a megoldásban.

Gyakorlati pontozás a project feladatokhoz:

- Alap pontszám egy feladatra(max 20): lefutó egység tesztek száma / összes egység tesztek száma * 20, feltéve, hogy a
  megoldás a szövegben megfogalmazott feladatot valósítja meg
- Clean kód, programozási elvek, bevett gyakorlat, kód formázás megsértéséért - pontlevonás jár. Szintén pontlevonás
  jár, ha valaki a feladatot nem a leghatékonyabb módszerrel oldja meg - amennyiben ez értelmezhető.