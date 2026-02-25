# 🎓 Spring Boot School Backend

Ejemplo completo de un servicio backend desarrollado con **Spring Boot 3 + Java 17+** que permite administrar:

* 👤 Usuarios
* 👨‍🏫 Profesores
* 🎓 Estudiantes
* 📚 Grupos

El proyecto está construido siguiendo principios **SOLID** y una aproximación basada en **arquitectura hexagonal**, orientado a APIs REST modernas con seguridad stateless.

---

# 🚀 Tecnologías utilizadas

* Java 17+
* Spring Boot 3
* Spring Security 6
* JWT (JSON Web Token)
* BCrypt
* JPA / Hibernate
* PostgreSQL
* Docker & Docker Compose

---

# 🔐 Seguridad implementada

El proyecto utiliza un esquema de seguridad moderno basado en:

## 1️⃣ Autenticación

* Login con email y password
* Validación contra base de datos
* Password encriptado con BCrypt
* Generación de JWT firmado con HS256

## 2️⃣ Autorización

* Seguridad stateless (sin sesiones)
* Roles almacenados en base de datos (ADMIN, TEACHER)
* Uso de `@PreAuthorize` para control por rol
* Autorización contextual (ej. profesor solo ve sus grupos)

## 3️⃣ Flujo de autenticación

1. Usuario envía credenciales a `/auth/login`
2. Spring Security valida contra la base de datos
3. Se genera JWT con rol incluido
4. Cliente envía token en header:

```
Authorization: Bearer <token>
```

5. Filtro JWT valida firma y establece el SecurityContext
6. Spring evalúa permisos antes de ejecutar el endpoint

---

# 📂 Estructura del proyecto

```
src/main/java
 ├── application        # Casos de uso
 ├── domain             # Entidades de dominio
 ├── infrastructure
 │    ├── controller    # Controllers REST
 │    ├── persistence   # Entidades JPA y repositorios
 │    └── security      # Configuración JWT y Security
```

---

# ⚙️ Variables de entorno

El proyecto utiliza variables de entorno para configuración sensible.

## Requeridas

```
POSTGRES_DB=school
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_PORT=5432

JWT_SECRET=TU_SECRET_BASE64
JWT_EXPIRATION_TIME=3600000
```

### 🔑 Generar JWT_SECRET seguro

Debe ser Base64 representando al menos 32 bytes reales.

Ejemplo en Java:

```java
Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
System.out.println(Encoders.BASE64.encode(key.getEncoded()));
```

Copiar el valor generado en `JWT_SECRET`.

---

# ▶️ Ejecutar en local (sin Docker)

1. Crear base de datos PostgreSQL
2. Definir variables de entorno
3. Ejecutar:

```
mvn clean install
mvn spring-boot:run
```

Servidor disponible en:

```
http://localhost:8080
```

---

# 🐳 Ejecutar con Docker

## docker-compose.yml ejemplo

```yaml
version: '3.8'

services:
  db:
    image: postgres:15
    container_name: school-db
    environment:
      POSTGRES_DB: ${POSTGRES_DB}
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    ports:
      - "${POSTGRES_PORT}:5432"

  backend:
    build: .
    container_name: school-backend
    env_file:
      - .env
    ports:
      - "8080:8080"
    depends_on:
      - db
```

## Pasos

1. Crear archivo `.env` con variables necesarias
2. Ejecutar:

```
docker compose up --build
```

API disponible en:

```
http://localhost:8080
```

---

# 🧪 Flujo básico de prueba

1️⃣ Crear usuario ADMIN 2️⃣ Login en `/auth/login` 3️⃣ Copiar token 4️⃣ Usar token para crear profesores, estudiantes y grupos

---

# 🏗 Principios aplicados

* Arquitectura por capas con separación clara
* Principios SOLID
* Stateless security
* Control declarativo con anotaciones
* Configuración desacoplada por variables de entorno

---

# 🎯 Objetivo educativo

Este proyecto sirve como ejemplo completo para:

* Aprender Spring Boot moderno
* Implementar JWT correctamente
* Entender Spring Security 6
* Aplicar control de acceso por rol y contexto
* Desplegar servicios con Docker
