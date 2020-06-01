# YazlabII3

## Algorithm
Edmonds-Karp algorithm

### Augmenting Path
- The path found must be a shortest path that has available capacity. This can be found by a breadth-first search
- That every time at least one of the E edges becomes saturated (an edge which has the maximum possible flow)
- That the distance from the saturated edge to the source along the augmenting path must be longer than last time it was saturated, and that the length is at most |V|.
[source](https://en.wikipedia.org/wiki/Edmonds%E2%80%93Karp_algorithm)

## TODOs, Problems, Research, Notes
### Class Design
graph builder inits graph and add visual attributes

#### Problems
how logic and view will seperated in meaningful and efficent manner?

### Do research
- Mincut/Maxflow

### TODO
understand java collections, lists

### Graph File Format
TGF file format

### Maybe Reusable Classes
- Visual Helper
- TGFReader

## Sunum 

### İsterler
1 tane giriş olacak 1 tane çıkış olacak
max flow mix cut hesaplanacak
kaynak ve hedefi kullanıcı girecek

### Arayüz
arayüz çözümü göstermeli 
node sayısını aldıkdan sonra grafı çizmeli, algorıtma sonucunu gösterecek şekilde birdaha çizmeli
çözüme göre nodların ve edgelerin gösterimleri gösterimleri değişmeli
sadece graf girilecek başka başka girdi olmayacak
graf çizdirmek için hazır kütüphaneler kullanılabilir