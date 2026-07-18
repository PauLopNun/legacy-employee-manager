# Legacy Employee Manager

A Proof of Concept (PoC) demonstrating the integration between native Java applications and legacy Oracle PL/SQL business logic.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Language-Java%2017-blue.svg)](https://oracle.com/java)
[![JDBC](https://img.shields.io/badge/Database-JDBC-orange.svg)](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
[![Oracle](https://img.shields.io/badge/Database-Oracle%20XE-red.svg)](https://www.oracle.com/database/)
[![Docker](https://img.shields.io/badge/Infrastructure-Docker-2496ED.svg)](https://www.docker.com/)

## Overview

This repository explores the mechanics of legacy enterprise architectures. In systems with years of production history, business logic is frequently encapsulated directly within the database (using Stored Procedures and Triggers) rather than in the application's service layer.

Understanding how pure Java communicates with these components via JDBC is a fundamental prerequisite for modernizing and migrating legacy systems toward contemporary architectures such as Spring Boot and Angular.

---

## Features

- **Native Secure Connection:** Implementation of Oracle connectivity by isolating database credentials in a `config.properties` file.
- **Database Logic Execution:** Usage of `CallableStatement` to invoke PL/SQL stored procedures directly from Java.
- **`OUT` Parameter Handling:** Extraction of values calculated by the database (e.g., salary increment simulation).
- **Cursor Reading (`REF_CURSOR`):** Manual mapping and retrieval of datasets returned by a PL/SQL procedure.

---

## Tech Stack

| Component | Technology |
|------------|------------|
| **Language** | Java 17 |
| **Build Tool** | Maven |
| **Database** | Oracle XE (Docker) |
| **Connectivity** | JDBC (`Connection`, `CallableStatement`, `ResultSet`) |
| **Database Logic** | PL/SQL (Stored Procedures, REF CURSOR, Exception Handling) |
| **Infrastructure** | Docker Compose |

---

## Project Structure

```text
legacy-employee-manager/
├── db/
│   ├── 01_create_table.sql
│   ├── 02_procedure_subir_sueldo.sql
│   ├── 03_cursor_empleados.sql
│   └── 04_excepciones.sql
├── docker/
│   └── docker-compose.yml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── legacy/
│       │           └── LegacyApp.java
│       └── resources/
│           └── config.properties.example
├── .gitignore
├── pom.xml
└── README.md
```

> **Note:** `config.properties` is intentionally excluded from version control and must be created locally from `config.properties.example`.

---

## Getting Started

### Prerequisites

- JDK 17 or later
- Maven
- Docker & Docker Compose
- Oracle SQL client (e.g. DBeaver or IntelliJ Database Tools)

### Run locally

#### 1. Start the Oracle XE container

```bash
cd docker
docker compose up -d
```

#### 2. Initialize the database

Connect to your local Oracle XE instance and execute the SQL scripts inside the `/db` directory in numerical order:

1. `01_create_table.sql`
2. `02_procedure_subir_sueldo.sql`
3. `03_cursor_empleados.sql`
4. `04_excepciones.sql`

#### 3. Configure the application

Copy:

```text
src/main/resources/config.properties.example
```

to:

```text
src/main/resources/config.properties
```

Then edit `config.properties` and provide your local Oracle database credentials.

#### 4. Run the application

Compile the project with Maven and execute `LegacyApp` from your IDE.

---

## Contact

**Pau López Núñez**

- **Email:** paulopeznunez@gmail.com
- **LinkedIn:** [paulopnun](https://www.linkedin.com/in/paulopnun/)
- **GitHub:** [@PauLopNun](https://github.com/PauLopNun)