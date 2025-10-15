# barriSense-Backend

Backend per a la gestió de feedbacks i queixes sobre barris. Desenvolupat amb Spring Boot.

## Funcionalitats principals

- Gestió de queixes (feedbacks) associades a barris concrets.
- Recuperació de totes les queixes de la base de dades.
- Comptatge de queixes per barri.
- Inicialització de dades de prova per facilitar el desenvolupament.
- Endpoints REST per accedir i manipular la informació de feedbacks.

## Instal·lació en local

1. **Prerequisits:**
   - Java 25
   - Maven
   - H2
   

2. **Clonar el repositori**
   ```bash
   git clone https://github.com/Apani13/barriSense-Backend.git
   cd barriSense-Backend/barrisense-feedback-api
   ```

3. **Configuració de la base de dades**

   Per defecte, el projecte pot estar configurat per utilitzar H2. Si vols utilitzar una altra base de dades, edita el fitxer `src/main/resources/application.properties` i configura l’URL, l’usuari i la contrasenya. (Es pot passar a MySQl tot canviant l'application.properties)

4. **Executar l’aplicació**

   Amb Maven:
   ```bash
   mvn spring-boot:run
   ```

   O bé, compila i executa el `.jar`:
   ```bash
   mvn package
   java -jar target/barrisense-feedback-api-*.jar
   ```

  
5. **Accés**
   - L’aplicació s’executarà per defecte a [http://localhost:8080](http://localhost:8080)

## Endpoints principals (REST API)

> Tots els endpoints tenen la base `/api/feedbacks`

### Obtenir totes les queixes
- **GET** `/api/feedbacks`
- Retorna una llista de totes les queixes registrades.

### Obtenir una queixa per ID
- **GET** `/api/feedbacks/{id}`
- Retorna la informació d’una queixa concreta (ID).

### Comptar queixes per barri
- **GET** `/api/feedbacks/count/by-neighborhood/{hoodId}`
- Retorna el nombre de queixes associades a un barri específic.

### Comptar totes les queixes agrupades per barri
- **GET** `/api/feedbacks/count/by-neighborhood/all`
- Retorna una llista amb el número de queixes per cada barri.

### Afegir una nova queixa
- **POST** `/api/feedbacks`
- Crea una nova entrada de feedback. Cal enviar un JSON amb els camps necessaris (usuari, barri, contingut...).

### Obtenir totes les queixes d’un barri
- **GET** `/api/feedbacks/by-neighborhood/{hoodId}`
- Retorna totes les queixes associades a un barri.

## Notes addicionals

- El sistema inicialitza dades de prova automàticament si la base de dades és buida.
- El backend permet CORS per defecte des de `localhost:4200`, `localhost:3000` i `localhost:5173` per facilitar el desenvolupament front-end.

## Desenvolupament

- **Tecnologies:** Java 25, Spring Boot, Spring Data JPA, Maven
- **Directori principal del codi:** `barrisense-feedback-api`

## Contacte

Per dubtes o suggeriments, obre una Issue al repositori o contacta amb l’equip de BarriSense.
