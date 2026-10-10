# 🔄 Pipeline CI/CD — Jakarta EE, Jenkins, Tomcat et Docker

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-Servlets-007396)
![Maven](https://img.shields.io/badge/Maven-build-C71A36?logo=apachemaven)
![Jenkins](https://img.shields.io/badge/Jenkins-pipeline-D24939?logo=jenkins&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Hub-2496ED?logo=docker&logoColor=white)

Mise en place complète d'une chaîne d'**intégration et de déploiement continus** pour une application web Jakarta EE : chaque `git push` déclenche automatiquement le build, le déploiement sur Tomcat et la publication d'une image Docker.

> Projet final — cours **420-A18 Déploiement et outils d'intégration continue** — Collège de Bois-de-Boulogne, AEC Analyste-programmeur, 2026.

---

## 🧩 L'application

Petite application web de gestion d'étudiants (nom, prénom, spécialité) : création et consultation, accessible sur `/etudiants`.

## ⚙️ Le pipeline

```
 git push ──► GitHub ──(webhook via ngrok)──► Jenkins
                                                │
             ┌──────────────────────────────────┼───────────────────────────┐
             ▼                                  ▼                           ▼
     1. Build Maven                   2. Déploiement Tomcat         3. Image Docker
     mvn clean package                plugin « Deploy to            docker build
     (compilation + tests)            container » → /tp-finale      4. Push Docker Hub
```

| Étape (Jenkinsfile) | Ce qu'elle fait |
|---|---|
| **Build Maven** | `mvn -B clean package` : compilation, tests, création du `.war` |
| **Déploiement Tomcat** | Déploie le `.war` sur un Tomcat local (identifiants stockés dans Jenkins) |
| **Build image Docker** | Construit l'image à partir de `tomcat:10.1-jdk21`, étiquetée `latest` et avec le numéro de build |
| **Push Docker Hub** | Se connecte avec des identifiants Jenkins sécurisés et pousse l'image |

Aucun mot de passe n'est écrit dans le code : Tomcat et Docker Hub utilisent les **credentials Jenkins** (`withCredentials`).

## 🛠️ Technologies

Java 21 · Jakarta EE (Servlets, CDI) · Maven · JUnit 5 · Git / GitHub · Jenkins (pipeline déclaratif, webhook) · ngrok · Tomcat 10 · Docker · Docker Hub

## 🚀 Lancer l'application

### Avec Docker (le plus simple)

```bash
docker run -p 8080:8080 6401185/a18_hanenchtourou_final_1318:latest
```

Puis ouvrir http://localhost:8080/tp-finale/etudiants

### Depuis le code

```bash
git clone https://github.com/hanen91-ch/cicd-jakarta-jenkins-docker.git
cd cicd-jakarta-jenkins-docker
./mvnw clean package          # mvnw.cmd sous Windows
docker build -t tp-finale .
docker run -p 8080:8080 tp-finale
```

## 📁 Fichiers clés

| Fichier | Rôle |
|---|---|
| [`Jenkinsfile`](Jenkinsfile) | Définition du pipeline (4 étapes) |
| [`Dockerfile`](Dockerfile) | Image Tomcat 10.1 + JDK 21 avec le `.war` |
| [`pom.xml`](pom.xml) | Dépendances et build Maven |
| `src/main/java/a14/tp_finale/` | Servlet, modèle `Etudiant`, service |

## 👩‍💻 Auteure

**Hanen Chtourou** — Analyste-programmeure
[LinkedIn](https://linkedin.com/in/hanen-chtourou-45097a104) · [GitHub](https://github.com/hanen91-ch)
