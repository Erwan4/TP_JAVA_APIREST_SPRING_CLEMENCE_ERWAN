# Clipper API — Spring Boot

> API de gestion des magasins, usines et stocks de trombones.

Ce service constitue le **backend Clipper**, utilisé par le service e-commerce **client-clipper** développé avec Quarkus.

Le projet permet notamment de gérer les magasins, les usines, les stocks et les expéditions de trombones.

Le service Spring Boot est indépendant du service Quarkus. Les deux services possèdent leur propre logique et leur propre base de données. La communication entre les deux services se fait par **HTTP/REST**.

---

# 1. Prérequis

| Dépendance | Rôle |
|---|---|
| **JDK** | Exécution de l'application Spring Boot |
| **Maven** | Compilation et lancement du projet |
| **Docker Desktop** | Exécution de MySQL |
| **WSL** | Exécution de Docker Compose depuis le terminal utilisé pour le projet |
| **MySQL 8.4** | Base de données de Clipper |
| **Client-clipper Quarkus** | Service e-commerce utilisant cette API |

Le projet Spring Boot utilise **MySQL 8.4**.

---

# 2. Base de données MySQL

La base de données est exécutée dans un conteneur Docker.

Le fichier `docker-compose.yml` contient notamment :

```yaml
services:
  mysql:
    image: mysql:8.4
    container_name: clipper-mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: clipper
      MYSQL_USER: clipper
      MYSQL_PASSWORD: clipper
    ports:
      - "3306:3306"
Informations de connexion
Paramètre	Valeur
Database	clipper
User	clipper
Password	clipper
Port	3306
```
La configuration Spring Boot se trouve dans application.yml :
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/clipper
    username: clipper
    password: clipper
```
# 3. Lancer MySQL

Le conteneur MySQL doit être démarré avant de lancer Spring Boot.

Depuis WSL, dans le dossier du projet :

docker compose up -d

Si le conteneur clipper-mysql existe déjà mais est simplement arrêté :

docker start clipper-mysql

Vérifier que MySQL fonctionne :

docker ps

Le conteneur clipper-mysql doit apparaître avec un statut Up.

Important : le projet Spring Boot ne crée pas automatiquement cette base avec Quarkus Dev Services. MySQL est géré séparément avec Docker.

# 4. Ordre de démarrage du système complet

Pour tester l'ensemble du projet avec Quarkus :

Étape 1 — Démarrer MySQL

Depuis WSL dans le projet Spring :

docker compose up -d
Étape 2 — Démarrer Clipper Spring Boot
./mvnw spring-boot:run

L'API doit être disponible sur :

http://localhost:8080

Vérification

http://localhost:8080/api/stores

Étape 3 — Démarrer Client-clipper Quarkus

Dans le projet Quarkus :

./mvnw quarkus:dev

Quarkus est disponible sur :

http://localhost:8081

Swagger UI

http://localhost:8081/q/swagger-ui

À partir de là, le parcours complet peut être testé depuis le client Quarkus.

# 5.Endpoints principaux de Spring
Stores

L'API expose les endpoints suivants :

Méthode	Endpoint
POST	/api/stores
GET	/api/stores
GET	/api/stores/{id}
PUT	/api/stores/{id}
PATCH	/api/stores/{id}
DELETE	/api/stores/{id}

Les magasins permettent notamment de gérer :

leur nom ;
leur adresse ;
leurs coordonnées géographiques ;
leur stock ;
leurs usines disponibles ;
leurs expéditions.
5. Modifications apportées au projet Clipper

Le projet Spring Boot a été repris comme base afin de compléter l'API Clipper et de permettre son utilisation par le client e-commerce client-clipper développé avec Quarkus.

La fonctionnalité Purchase permet ainsi de faire le lien entre le parcours de commande côté Quarkus et la gestion du stock côté Spring Boot.