# PokémonGPT

## Présentation du projet

Ce projet est l'aboutissement de notre module de développement mobile. Il est censé imiter une application de type "Pokémon Go".
Il est construit principalement en Java

## Fonctionalités implémentées

- Pokédex : Regroupe tout les Pokémons de la première génération, avec leur numéro ainsi que leur type. Les différents pokémons sont cliquables pour ouvrir une fiche plus détaillée et plus grande.
- La Carte : La Carte est géo-localisée, avec une génération de Pokémons aléatoires autour de soi. Lors du déplacement, les Pokémons trop lointains sont effacés au profit de nouveaux.
- Capture : Les Pokémons sur la carte peuvent être cliquées par l'utilisateur, ce qui entraîne la capture.
- Page utilisateur : Les données de l'utilisateur sont mises à jours si celui-ci venait à changer ses données. On affiche également les pokémons capturés.
- Base de données : Une base de données a été créée mais non implémentée.

## Bugs trouvés
- Carte : Le marqueur du position du joueur change de position lorsque l'on zoom ou dézoom.
- Carte : La carte est reload à chaque fois que l'on change de page, ce qui entraîne la regénération des pokémons. Stocker les coordonnées des pokémons dans la BDD permettrait de corriger le problème.

### Contributeurs

Chloé Baillivet
Louis-Joachin Messié

