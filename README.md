# 🚦 TrafineSUPMAP - Backend Microservices

Ce backend Java Spring Boot est organisé en microservices et entièrement containerisé via Docker.

## 📦 Services inclus

| Service          | Port | Description                  |
| ---------------- | ---- | ---------------------------- |
| auth-service     | 8081 | Authentification (JWT)       |
| user-service     | 8082 | Gestion des utilisateurs     |
| route-service    | 8083 | Calcul d'itinéraires (mocké) |
| incident-service | 8084 | Signalements & validation    |
| postgres         | 5432 | Base de données PostgreSQL   |

---

## 🚀 Lancement

```bash
docker-compose up --build
```

---

## 🔐 Exemple : Authentification

### 🔸 Enregistrement

```http
POST /auth/register
Content-Type: application/json

{
  "email": "user@trafine.com",
  "password": "user123"
}
```

### 🔸 Connexion

```http
POST /auth/login
Content-Type: application/json

{
  "email": "user@trafine.com",
  "password": "user123"
}
```

⟶ Réponse : token JWT à inclure dans les requêtes suivantes.

---

## 👤 Exemple : User Service

```http
GET /users
Authorization: Bearer <token>
```

```http
PUT /users/1
Authorization: Bearer <token>
Content-Type: application/json

{
  "email": "updated@trafine.com",
  "role": "ADMIN"
}
```

---

## 🚨 Exemple : Incident Service

```http
POST /incidents
Authorization: Bearer <token>
Content-Type: application/json

{
  "type": "Accident",
  "description": "Accident sur la route A7",
  "latitude": 45.764,
  "longitude": 4.8357
}
```

```http
PATCH /incidents/1/validate
Authorization: Bearer <token>
```

---

## 🗺️ Exemple : Route Service

```http
GET /routes?from=Paris&to=Lyon
Authorization: Bearer <token>
```

```http
GET /routes/alternatives?from=Paris&to=Lyon
Authorization: Bearer <token>
```

---

## 🧪 Swagger

Chaque service expose sa documentation Swagger :

- http://localhost:8081/swagger-ui.html
- http://localhost:8082/swagger-ui.html
- http://localhost:8083/swagger-ui.html
- http://localhost:8084/swagger-ui.html

---

## 🧰 Accès PostgreSQL

- URL : `jdbc:postgresql://localhost:5432/trafine_db`
- Utilisateur : `trafine`
- Mot de passe : `trafinepwd`

---

## ✉️ Contact

Pour toute question, contactez Cheikh Sylla.
