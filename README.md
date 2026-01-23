# TietorakenteetJaAlgoritmit-Harjoitustyo
Harjoitustyö erilaisten järjestysalgoritmien vertailuun

## Raportti

### Johdanto
Harjoitustyössä hyödynnettiin funktioreferenssiä, jonka avulla ohjelma voitiin kirjoittaa 
modulaarisesti, ja algoritmien vertailu oli helppoa. tiedostossa on myös TimerUtil-luokka, jota
hyödynsin ajan mittaamiseen.  
alla eri algoritmien vertailuja satunnaisella, nousevalla ja laskevalla datalla.

### Testitulokset
**N=10000**
| Algoritmi | Random data | Nouseva data | Laskeva data |
|-----------|-------------|--------------|--------------|
|**InsertionSort**|78ms|0ms|130ms|
|**Arrays.Sort**|12ms|0ms|0ms|
|**BubbleSort**|177ms|0ms|101ms|
|**ShellSort**|4ms|2ms|1ms|
|**QuickSort**|3ms|6ms|1ms|
|**MergeSort**|5ms|2ms|1ms|
|**MergeSort(opt)**|3ms|1ms|3ms|

**N=500000**
| Algoritmi | Random data | Nouseva data | Laskeva data |
|-----------|-------------|--------------|--------------|
|**InsertionSort**| 100406ms | 4ms | 200819ms |
|**Arrays.Sort**| 75ms | 12ms | 3ms|
|**BubbleSort**| 368952ms | 0ms | 232236ms|
|**ShellSort**| 110ms | 20ms | 26ms|
|**QuickSort**| 95ms | 21ms | 32ms|
|**MergeSort**| 156ms | 41ms | 40ms|
|**MergeSort(opt)**| 98ms | 21ms | 33ms|

### Tulosten analysointi
Tulokset näyttävät loogisilta, ja vastaavat hyvin teoreettista analyysiä algoritmien aikakompleksisuudesta.

#### Neliölliset algoritmit
InsertionSort ja BubbleSort antoivat sellaisia tuloksia, kuten pitääkin. Satunnaista ja laskevaa dataa käsitellessä ne olivat $O(n^2)$, kuten oli odotettavissa.
Nousevaa dataa käsitellessä puolestaan on huomattavissa poikkeus, jossa InsertionSortin aika oli lähes $O(n)$ ja BubbleSortin suoritusaika oli alle mittatarkkuuden.

#### Tehokkaat algoritmit
QuickSort, MergeSort, ShellSort ja Arrays.sort suoriutuivat mittauksista odotetusti kymmenissä tai sadoissa millisekunteissa. Javan sisäänrakennettu Arrays.sort on selkeästi erittäin hyvin optimoitu, ja päihittää usein perustoteutukset. ShellSort yllätti nousevissa ja laskevissa testitilanteissa, ja annetuilla datamäärillä kilpaili tasapäisesti Muiden nopeiden algoritmien kanssa, jopa ohittaen optimoidun MergeSortin.

### MergeSortin optimointi
MergeSort antoi optimoituna jopa merkittäviä tuloksia, esimerkiksi satunnaisdatalla se päihitti optimoimattoman MergeSortin olemalla n. 1.6 kertaa nopeampi. Tämä selittyy kahdella optimoinnilla:
    
**1. Muistinhallinnan optimointi:**  
Optimoidussa MergeSortissa käytetään kahden aputaulukon sijaan vain yhtä aputaulukkoa, sen avulla vähennetään Garbage Collectionin kuormittamista ja aikaa muistin varaamisessa.

**2. Hybridialgoritmi:**  
Pienillä osataulukoilla algoritmi vaihtaa InsertionSorttiin. Vaikka InsertionSort on teoreettisesti $O(n^2)$, Se on kuitenkin hyvin pienillä datamäärillä usein nopeampi. Tämän avulla voidaan vähentää rekursiokutsujen määrää, joka näkyy suorituskyvyn parantumisena.

### Yhteenveto
- Jos muistinkäyttö on kriittistä, QuickSort tai SHellSort ovat parhaita vaihtoehtoja. Ne eivät kuitenkaan ole stabiileja joten vakautta, sekä nopeutta tarvittaessa optimoitu MergeSort on erinomainen.  
- Jos data on lähes järjestyksessä, tai sitä on pieni määrä, InsertionSort on varteenotettava vaihtoehto.
- BubbleSort on lähes hyödytön, eikä sitä kannata käyttää oikein missään tilanteessa.
- Arrays.sort on käytännössä turvallisin, ja paras yleistyökalu.