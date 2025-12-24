# Guide de Démarrage - Mini Projet IoT

Ce projet est une application Spring Boot complète pour la gestion d'objets connectés (IoT).

## 📂 Emplacement du projet
Le projet a été généré sur votre ordinateur dans le dossier :

`C:\Users\GIS\.gemini\antigravity\scratch\iot-mini-projet`
Une archive ZIP a également été créée :
`C:\Users\GIS\.gemini\antigravity\scratch\iot-mini-projet.zip`

## 🚀 Comment ouvrir le projet

### Option 1 : IntelliJ IDEA (Recommandé)
1.  Ouvrez IntelliJ IDEA.
2.  Cliquez sur **File** > **Open**.
3.  Naviguez vers le dossier `C:\Users\GIS\.gemini\antigravity\scratch\iot-mini-projet` et sélectionnez le fichier `pom.xml`.
4.  Cliquez sur **OK** et choisissez **Open as Project**.
5.  Attendez que IntelliJ télécharge les dépendances (cela peut prendre quelques minutes).

### Option 2 : Eclipse
1.  Ouvrez Eclipse.
2.  Cliquez sur **File** > **Import**.
3.  Choisissez **Maven** > **Existing Maven Projects** et cliquez sur **Next**.
4.  Cliquez sur **Browse** et sélectionnez le dossier `C:\Users\GIS\.gemini\antigravity\scratch\iot-mini-projet`.
5.  Cochez le fichier `pom.xml` et cliquez sur **Finish**.

## ▶️ Comment lancer l'application
1.  Dans votre IDE, cherchez la classe `IotMiniProjetApplication.java` (dans `src/main/java/com/example/iot`).
2.  Faites un clic droit sur le fichier > **Run 'IotMiniProjetApplication'**.
3.  Attendez de voir le message `Started IotMiniProjetApplication` dans la console.

## 🌐 Comment tester

### 1. Tableau de Bord (Interface Web)
Ouvrez votre navigateur et allez à l'adresse :
👉 **http://localhost:8080/**

*   **Utilisateur** : `admin`
*   **Mot de passe** : `password`

Vous verrez la liste des objets connectés (des données de test sont générées automatiquement). Cliquez sur "Détails" pour voir l'historique de télémétrie.

### 2. Documentation API (Swagger)
Pour voir et tester les API REST, allez à :
👉 **http://localhost:8080/swagger-ui.html**

Vous pourrez y tester les endpoints :
*   `GET /api/devices` : Lister les devices
*   `POST /api/devices` : Créer un device
*   `POST /api/devices/{id}/telemetry` : Envoyer une mesure

### 3. Base de données (Console H2)
Pour voir les tables de la base de données directement :
👉 **http://localhost:8080/h2-console**

*   **JDBC URL** : `jdbc:h2:mem:iotdb`
*   **User Name** : `sa`
*   **Password** : `password`
*   Cliquez sur **Connect**.

## 📝 Fonctionnalités implémentées
*   **Architecture Spring Boot** : Modèle-Vue-Contrôleur (MVC) + API REST.
*   **Entités** : `IoTDevice` (Objet) et `Telemetry` (Mesures), reliés par une relation 1-N.
*   **Sécurité** : Connexion obligatoire (Basic Auth / Form Login).
*   **Documentation** : Swagger OpenAPI intégré.
*   **Frontend** : Pages HTML dynamiques avec Thymeleaf et Bootstrap. (Très joli !)

## 📦 Rendu du Projet
Pour rendre ce projet :
1.  Assurez-vous d'avoir tout sauvegardé.
2.  Zippez le dossier `iot-mini-projet`.
3.  Incluez ce fichier `GUIDE_DEMARRAGE.md` et le `RAPPORT_PROJET.md`.

