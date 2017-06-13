# PLA
## Journal de bord

### Jour 1

Discussion du but du jeu.<return>
  * Base à détruire. Création de robots après avoir ramassé les opérateurs.<return>
Première hiérarchie.
![Architecture version 1](/md/archiv1.jpg)<return>
Répartition des tâches :
* Fathin Syuhada/Anouar : Base
* Najwa/Joffrey : Graphisme
* Thomas : Map
* Paul : Personnages

### Jour 2

#### Matin

Cours sur l'environnement graphique + sur les compétences

#### Après-midi

Discussion sur l'apport d'une nouvelle fonctionnalité pour le héros, afin de limiter ses pouvoirs : Décision de la mise en place de points d'actions <return>
Décision de modification de l'organisation des classes : <return>
![Architecture version 2](/md/archiv2.jpg)<return>
Répartition des tâches pour la journée :
* Najwa/Joffrey : Graphisme
* Thomas : Map/Cases
* Fathin Syuhada/Anouar : Actifs
* Paul : Parser

Précision sur l'utilisation du git : <return>
* Toujours Pull avant de travailler sur un fichier ! <return>
* Toujours prévenir que l'on va travailler sur un fichier <return>
* Toujours push une fois la fonctionnalité implémentée <return>

### Jour 3

#### Matin

Cours sur git + chaque équipe a présenté son jeu

#### Après-midi

Suite aux remarques, discussion sur le changement de la séquence du jeu. Soit on décide de faire la séquence suivante :
1. Phase choix Joueur 1
2. Phase choix Joueur 2
3. Phase d'action (les choix des deux joueurs sont exécutés)

Soit, on choisit la seconde version :

1. Phase choix Joueur 1
2. Phase action Joueur 1
3. Phase choix Joueur 2
4. Phase action Joueur 2

Discussion, puis suite au vote, la seconde version est choisie. Quoi qu'il en soit, ce choix n'est pas déterminant pour le codage du projet, puisque la base technique reste très semblable.

Second point de débat : Comment arreter les phases d'actions ? Encore une fois, deux solutions : <return>
* On donne également des points d'actions aux robots, puis ils éxecutent leurs "programme", puis s'arretent une fois leurs points d'actions ecoulés
* On définit un timer, au bout duquel tous les robots s'arretent. Cette dernière solution permet de laisser le jeu se dérouler un peu plus et de ne pas avoir un gameplay trop saccadé. Encore une fois, la décision sera prise plus tard.

Présentation de l'écran de jeu :

![Ecran de jeu ](/md/ecran.png)

Répartition pour ce jour, ainsi que pour le weekend : <return>
* Najwa : IHM
* Fathin Syuhada : Affichage du héros à l'écran
* Thomas : Déplacement du Héros + Déplacement des robots (dans un premier temps, selon x, puis selon y)
* Paul : Arbre des actions pour les robots
* Joffrey : Détection des touches pour le déplacement du Héros
* Anouar : Tests

### Jour 4 (Mardi 06/06)

#### Matin

- Cours sur les présentations
- Discussion dans l'équipe sur le MVC.
- Objectifs de la journée :
* Avoir un héros qui apparait et qui bouge
* Structure arbre pour les robots fonctionnels
* Graphique qui fonctionne en coordination avec la Map
* Implémentation des tests

#### Après Midi

Point sur ce qui est fonctionnel et ce sur quoi il y a des bugs :
* On arrive à creer une fenetre avec les boutons. Il y a un problème mineur sur l'affichage de la map dans la fenetre.
* Dans GameModel : La structure de mémoire pour la map est fonctionelle. Concernant le déplacement du héros, il faut corriger une bug quand on bouge avec z et s.
* La structure de l'arbre est en place, il n'y a plus qu'à implementer le parcours de l'arbre.

Tâches à venir :
* Creation de l'inventaire du héros et de la méthode pickUp pour les compétences
* Parcours de l'arbre + implémentation des différentes actions.
* Déplacement des robots.
* Points de vie pour le héros et les bases.

Discussion technique :
- Comment identifier chaque compétence
  -> Mise en place d'un énumération (Competence)
- Nom du jeu :
  -> Après votes : le nom sera .... Mighty Retarded Robots

### Jour 5 (Mercredi 07/06)

Travail sur les différents composants du projet : graphique, map et arbre pour les robots

Discussion sur l'organisation des combats entre robots :
* Décision que les combats se feront tour à tour. Tout robot est capable de se défendre même si ce n'est pas son tour.
* Décision de mise en place de niveaux de vie pour les robots. On compte les points d'attaque, puis des points de défense puis on enlève la différence au niveau de vie du robot.
* Comme pour la défense, dès que le robot passe sur une compètence, il la ramasse. Ainsi, il sera défini trois méthodes pour soit se diriger vers la plus proche compétence, soit  vers le plus proche ennemi ou le plus proche membre de l'équipe.
* Dès le départ du jeu, on donnera au robot la capacité de se déplacer vers la plus poche compétence.

Répartition :

* Thomas & Anouar : Map + déplacement du robot
* Paul & Joffrey : Arbre du robot et Parser
* Fathin Syuhada & Najwa : Graphisme.

### Jour 6 (Jeudi 08/06)

Journée de partiel ALM

#### Matin

Travail sur les mêmes sujets que la veille + travail sur l'agrandissement de la carte (Najwa).

#### Après-midi

Partiel d'ALM + Préparation de la soutenance de mi projet

### Jour 7 (Vendredi 09/06)

Journée de soutenance mi-projet

Il a été définit le contrat suivant :

* Groupe 7 (OG,PB) : Mighty Retarded Robots

** Jeu : 2 bases, 2 joueurs, Se joue tour à tour

- But: destruction de la base de l'adversaire

- une phase stratégie, une phase d'actions.

-  Phase de stratégie:
       codage des robots. déplacement du joueur
       joueur limité dans son tour par des points de jeu,
       qui se décomptent lorsqu'il se déplace ou code des robots.
-  Phase d'actions
       les robots déroulent leur programme.
       phase limitée en temps
       combat corps a corps. (case adjacente)
       point d'attaques/défenses.
       points de vie.

** Carte : map + grande que l'écran, avec un fenêtre de vue qui l'on peut déplacer

** Compétences sur la map : actions et opérateurs.
 - ils peuvent être ramasser par les robots et le joueur
 - besace globale des compétences collectées (par les robots et le joueur).

*** les operateurs du sujet

            A>B (priorité)

            A&B  A|B

            A;B

*** les actions:

          recherche: compétence, ennemi, ami, base

          attaque, kamikaze

**  Javacc / parser

** Contrat
- le jeu décrit ci-dessus

*** Extensions
 1. mini-map de visualization de l'ensemble du terrain (petit insert sur l'écran)
 2. audio/musique
 3. path finding sur l'operateur de recherche pour les robots, aujourd'hui, un déplacement super simple.

 Objectifs du week-end :

 * Thomas : Déplacement du robot
 * Paul & Joffrey : Arbre du robot et Parser
 * Fathin Syuhada & Najwa : Graphisme
 * Anouar : Tests

### Jour 8 (Lundi 12/06)

#### Matin

Résultat du codage du week end : le déplacement des robots est amélioré mais toujours quelques problème, particulièrement sur le choix de la direction à prendre face à un obstacle. Concernant les actions du robot, elles sont codées, à tester. Coté graphisme, de plus en plus de bugs sont corrigés.

Définition des Objectifs de la journée :
* Mise en place du Parser (Urgence+++)
* Spawn des compétences
* Ramassage des C par les robots
* Mise en place de le mécanique de jeu (alternance des phases et tour) (cet objectif peut être ajusté à demain)

#### Après-midi

Codage du jeu...

### Jour 9 (Mardi 13/06)

Codage du jeu...

Définition des objectifs pour le lendemain (DEADLINE pour jeu V1 demain soir):

* Pause (break)
* Affichage inventaire
* Timer fin de periode choix Héros
* Modif des robots
* Information sur les étapes (où on en est, temps restant...)
