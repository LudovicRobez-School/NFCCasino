# NFCCASINO

![Github](https://github.com/LudovicRobez/NFCCasino/blob/master/client/NFCCasino/app/src/main/res/drawable/nfccasino.png)

## Equipe :  
	Server: 			ROBEZ Ludovic
	Application console: 	FOUCAULT Marc
	Application Android: 	VESIC Gregory

### Avant de lancer le Projet :
	
	1/ S'assurer d'avoir Git-Bash d'installer
	2/ Ajouter à la variable Path séparée d'un ";" avec la valeur précédente, la valeur :
		- "%PROGRAMFILES(x86)%\Git\bin;%PROGRAMFILES(x86)%\Git\cmd" (modifier le chemin si différent !)
	3/ Installer maven console :
		- https://maven.apache.org/
	4/ Installer Android Studio
		- https://developer.android.com/
	4/ Installer MySQL WorkBench
		- https://www.mysql.com/fr/products/workbench/
	5/ Créer une connexion
		- NFCCASINO
		- root/ root/
		- 127.0.0.1:3306
	6/ Se connecter sur NFCCASINO
	7/ Lancer le script 
		- https://github.com/LudovicRobez/NFCCasino/blob/master/database/nfccasino.sql

### Pour lancer le Projet :

	1/ Lancer MySQL WorkBench
		- Se connecter sur NFCCASINO
	2/ Lancer la console CMD depuis /server et lancer le server :
		- Au clavier : Windows + R
		- Saisir : cmd
		- Saisir : mvn clean package
		- Saisir : mvn jetty:run
	3/ Lancer la console CMD et récupérer l'adresse IP du réseau local du poste
		- Au clavier Windows + R
		- Saisir : cmd
		- Saisir : ipconfig
		- Récupérer "Adresse IPv4"
	4/ Lancer le projet sous Android Studio
		- Importer un Projet
		- NFCCASINO
	5/ Saisir l'adresse IPv4 dans la variable "private static String urlServer" :
		- https://github.com/LudovicRobez/NFCCasino/blob/master/client/NFCCasino/app/src/main/java/com/example/asus/nfccasino/User.java
		- https://github.com/LudovicRobez/NFCCasino/blob/master/client/NFCCasino/app/src/main/java/com/example/asus/nfccasino/CreditCard.java
		- https://github.com/LudovicRobez/NFCCasino/blob/master/client/NFCCasino/app/src/main/java/com/example/asus/nfccasino/Payment.java
	6/ Compiler et générer l'application
		- Sélectionner l'onglet "Run"
		- Sélectionner l'option "Run"
	7/ Sélectionner votre smartphone
		- S'assurer que le téléphone a le mode débug d'activer
			- Rendez-vous dans les paramètres.
			- Tout en bas, allez dans À propos de l'appareil.
			- Appuyez plusieurs fois (sept fois) sur la case Numéro de build, jusqu'à ce qu'un message s'affiche "Vous êtes maintenant un développeur".
	8/ Lancer l'application via votre Smartphone
		- Pour le moindre détails sur l'utilisation, voir : https://github.com/LudovicRobez/NFCCasino/blob/master/Rapports/Notice_Utilisation_NFCCasino.pdf