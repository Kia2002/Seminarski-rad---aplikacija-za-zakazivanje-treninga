# Personal Training Scheduler

A Java-based client-server application for managing clients, trainers, and personal training session scheduling. Built as a university software engineering project.

## Overview

The Personal Training Scheduler provides a desktop interface for fitness facilities to manage their trainer roster, client base, and session bookings. The system is split into three modules — a shared common library, a server application with a configurable MySQL connection, and a client application for end users.

## Tech Stack

| Component | Technology |
|---|---|
| Language | Java |
| Database | MySQL 8.0+ |
| Connectivity | JDBC (MySQL Connector/J) |
| Architecture | Client/Server (two separate runnable applications) |

**Modules:**

| Module | Description |
|---|---|
| `PSProjectCommon` | Shared data models and utilities used by both server and client |
| `PSProjectServer` | Server application; manages the database connection and handles client requests |
| `PSProjectClient` | Client application; provides the user-facing GUI |

## Prerequisites

Before running the application, ensure the following are installed and configured:

- **JDK 23** or later
- **MySQL Server 8.0+**
- **MySQL Connector/J** driver (JDBC)
- An IDE such as IntelliJ IDEA, Eclipse, or NetBeans

## Database Setup

The database schema is not tracked in the repository. You must create it manually using the provided SQL script.

1. Locate the `baza.sql` file in the project root.
2. Start your MySQL Server instance.
3. Import the script using your preferred client (MySQL Workbench, SQLYog, phpMyAdmin) or via the command line:
   ```bash
   mysql -u root -p < baza.sql
   ```

The script will:
- Create a database named `projekat`
- Create all required tables (`trener`, `klijent`, `evidencija_treninga`, etc.)
- Seed initial test data for trainers and clients

## Running the Application

The server must be started and configured before launching the client.

### Step 1 — Start and Configure the Server

1. Import all three modules into your IDE.
2. In the `PSProjectServer` module, run `main/Main.java`.
3. The server management window will open.
4. Navigate to the **Configuration** tab and update the following fields to match your local MySQL setup:
   - Port
   - Database URL
   - Username
   - Password
5. Click **Save** to persist the configuration to `config.properties`.
6. Switch to the management tab and click **Start Server**.

### Step 2 — Start the Client

1. In the `PSProjectClient` module, run `main/Main.java`.
2. The login window will open.
3. Use one of the test credentials below to sign in.

> **Note:** Ensure the port configured on the server is not blocked by a firewall or occupied by another process (default: `9000`).

## Test Credentials

The following trainer accounts are pre-seeded by the database script:

| Username | Password | Email |
|---|---|---|
| `marko123` | `marko123` | marko@gmail.com |
| `nikola123` | `nikola123` | nikola@gmail.com |
| `aleksa123` | `aleksa123` | aleksa@gmail.com |
| `filip123` | `filip123` | filip@gmail.com |

## License

This project is intended for educational and portfolio purposes.
