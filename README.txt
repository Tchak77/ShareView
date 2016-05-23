Application ShareView

I - Installation de l'application

Pour installer l'application, l'utilisateur doit posséder le logiciel Android Studio ou un éditeur équivalent.
Il peut ensuite à l'aide du SDK 21 compiler l'application et installer l'APK sur un appareil Android.

II - Configuration au lancement

Au lancement de l'application, l'utilisateur est invité à entrer un pseudonyme. Celui-ci permettra de l'identifier dans les différents tableaux pour la liste des utilisateurs et le chat.
Il peut de plus configurer l'adresse IP et le port du serveur utilisé par l'application. Par défaut, celui-ci est configuré à celle utilisée lors du développement.

III - Créer ou rejoindre un projet

Une fois la configuration effectuée et l'application connectée à un serveur en fonctionnement, l'utilisateur a le choix entre créer un nouveau tableau et rejoindre un tableau existant.

Si l'utilisateur décide de créer un nouveau tableau, une fenêtre lui demandant le nom du tableau apparait et le tableau est créé.
Sinon, l'utilisateur voit apparaitre la liste des tableaux existants qu'il peut alors rejoindre.

IV - Fonctionnalité sur un tableau

	a) Dessiner

	L'utilisateur peut dessiner sur la partie Board du tableau. Il a accès à l'aide de la palette à plusieurs formes : Ellipse, Rectangle, Ligne, Polyligne et Texte.
	Pour créer une forme, l'utilisateur doit sélectionner dans la palette la forme souhaitée et appuyer sur le tableau jusqu'à ce qu'il ait fini sa forme.

	b) Modifier les options

	L'utilisateur a accès à de nombreuses options à travers l'onglet Option du tableau. Ces options permettent de configurer :
		- La taille du texte
		- L'épaisseur des lignes et polylignes
		- La couleur des formes (bleu, rouge, vert, noir et blanc)
		- La couleur du contour des formes (bleu, rouge, vert, noir et blanc) et son épaisseur
		- La visualisation de l'historique du tableau

	c) Communiquer avec les utilisateurs

	L'utilisateur peut dans l'onglet Chat communiquer avec les autres utilisateurs présents sur le tableau. Il peut renseigner un texte et l'envoyer. Tous les utilisateurs se connectant recevront la liste des messages.

	d) Visualiser les utilisateurs connectés

	L'onglet User permet de visualiser les utilisateurs connectés sur le tableau actuellement.

	e) Fonctionnalités supplémentaires

	L'utilisateur peut à l'aide du menu : 
		- Exporter les formes dessinées sous forme de JSON
		- Partager les formes à l'aide des applications proposant ce service
		- Quitter le tableau

V - Modifications apportées depuis le rapport / la soutenance

 - Ajout des bordures aux formes
 - Ajout d'un chat fonctionnel
 - Ajout de la liste des utilisateurs
 - Ajout de l'export
 - Ajout des partages
 - Ajout de la documentation Java
 - Ajout du slider pour voir l'historique du tableau
