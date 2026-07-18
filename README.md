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

- **Native Secure Connection:** Implementation of Oracle connectivity isolating credentials via a `config.properties` file.
- **Database Logic Execution:** Usage of `CallableStatement` to invoke PL/SQL stored procedures directly from Java.
- **`OUT` Parameter Handling:** Extraction of mathematical results calculated by the database (e.g., salary increment simulation).
- **Cursor Reading (`REF_CURSOR`):** Manual mapping and retrieval of complex datasets returned by a PL/SQL procedure to the Java client.

---

## Tech Stack

| Component | Technology |
|------------|------------|
| **Language** | Java 17 (Native, no abstractions) |
| **Database** | Oracle XE (via Docker) |
| **Connectivity** | JDBC (`Connection`, `CallableStatement`, `ResultSet`) |
| **Database Logic** | PL/SQL (Stored Procedures, Cursors) |
| **Infrastructure** | Docker Compose |

---

## Project Structure

```text
legacy-employee-manager/
├── .gitignore
├── README.md
├── docker/
│   └── docker-compose.yml            # Oracle XE infrastructure
├── db/
│   ├── 01_create_table.sql           # Schema initialization
│   └── 02_procedure_subir_sueldo.sql # PL/SQL business logic
└── src/
    ├── config.properties.example     # Credential template
    └── com/
        └── legacy/
            └── LegacyApp.java        # Java JDBC implementation
```

---

## Getting Started

### Prerequisites

- JDK 17+
- Docker
- Docker Compose

### Run locally

#### 1. Initialize the database container

```bash
cd docker
docker compose up -d
```

#### 2. Execute SQL scripts

Connect to the local Oracle instance (for example, using **DBeaver** or **IntelliJ Database Tools**) and execute the scripts located in the `/db` directory in numerical order.

#### 3. Configure credentials

Copy the example configuration file:

```bash
cp src/config.properties.example src/config.properties
```

Then edit `src/config.properties` and fill in your local Oracle database connection details.

#### 4. Execute the application

Compile and run `LegacyApp.java` to view the JDBC and PL/SQL integration in the console.

---

## Contact

**Pau López Núñez**

- **Email:** paulopeznunez@gmail.com
- **LinkedIn:** paulopnun
- **GitHub:** [@PauLopNun](https://github.com/PauLopNun)