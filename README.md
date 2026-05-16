# Helpdesk Backend API

### 1. Wymagania
*   Java 21
*   Docker + Docker Compose

### 2. Szybki start (Docker)
W głównym katalogu projektu uruchamiamy komendę:
```bash
docker compose up --build
```
To zbuduje aplikację i postawi bazę danych MySQL.

### 3. Odpalanie lokalne
Jeśli chcemy uruchamiać projekt np. przez IntelliJ lub terminal bez pełnego Dockera:

1.  **Uruchamiamy bazę:** `docker compose up mysql-db` (żeby nie instalować MySQL lokalnie).
2.  **Ustawiamy zmienną:** Aplikacja potrzebuje klucza `JWT_SECRET` (min. 32 znaki).
3.  **Uruchamiamy aplikację:** Używamy `./mvnw spring-boot:run` lub przycisku "Run" w IDE.

### 4. Dostęp
*   **Aplikacja:** [http://localhost:8080](http://localhost:8080)
*   **Swagger:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
*   **Baza:** Port 3306, user: `helpdesk_user`, hasło: `helpdesk_password` (Zgodnie z docker-compose).

### 5. Uwagi
*   Zasoby w `/api/tickets` są chronione. Najpierw trzeba się zarejestrować/zalogować w `/api/auth`, skopiować token i używać go w nagłówku `Authorization`.
*   CORS jest wyłączony.
