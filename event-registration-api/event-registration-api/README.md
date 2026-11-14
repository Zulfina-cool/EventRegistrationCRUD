
# Event Registration API
## Laboratory Work 7 — Spring Boot REST Controller

## 1. Project Description

The Event Registration API is a RESTful web application developed using Spring Boot. The project demonstrates CRUD (Create, Read, Update, Delete) operations for managing events through HTTP requests.

The application allows clients to retrieve all events, search events by title, find a specific event by ID, create new events, update existing events, and delete events.

All event data is stored in an in-memory list using Java. No external database is required.

## 2. Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Gradle
- REST API
- JSON
- Postman
- Git and GitHub

## 3. Project Structure

```text
event-registration-api/
├── src/
│   └── main/
│       ├── java/
│       │   └── kz/iitu/event/registration/
│       │       ├── controller/
│       │       │   └── RegistrationController.java
│       │       ├── model/
│       │       │   └── EventInfo.java
│       │       ├── service/
│       │       │   └── EventService.java
│       │       └── EventRegistrationApiApplication.java
│       └── resources/
│           └── application.properties
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
└── README.md
```

## 4. Application Features

- Retrieve all available events.
- Search events by title using a query parameter.
- Retrieve an event by its unique ID.
- Create a new event with a server-assigned ID.
- Update the title and location of an existing event.
- Delete an existing event.
- Validate input data by rejecting missing or blank titles and locations.
- Return appropriate HTTP status codes.

## 5. API Endpoints

**Base URL:**

```text
http://localhost:8080
```

| HTTP Method | Endpoint | Description |
|---|---|---|
| GET | `/api/events` | Retrieve all events |
| GET | `/api/events/{id}` | Retrieve an event by ID |
| GET | `/api/events?title=quiz` | Search events by title |
| POST | `/api/events` | Create a new event |
| PUT | `/api/events/{id}` | Update an existing event |
| DELETE | `/api/events/{id}` | Delete an event |

### 5.1 GET — Retrieve All Events

**Request:**
```http
GET /api/events
```

Returns all available events as a JSON array.

### 5.2 GET — Retrieve Event by ID

**Request:**
```http
GET /api/events/1
```

Returns the event with the specified ID.

If the event does not exist, the API returns `404 Not Found`.

### 5.3 GET — Search Events by Title

**Request:**
```http
GET /api/events?title=quiz
```

Searches for events whose titles contain the specified text.

The search is case-insensitive. If no matching events are found, the API returns an empty JSON array.

### 5.4 POST — Create a New Event

**Request:**
```http
POST /api/events
```

**Request Body (JSON):**

```json
{
  "title": "Python Workshop",
  "location": "Room 202"
}
```

Creates a new event and automatically assigns a unique ID on the server.

Returns the created event with `201 Created`.

### 5.5 PUT — Update an Existing Event

**Request:**
```http
PUT /api/events/4
```

**Request Body (JSON):**

```json
{
  "title": "Advanced Python Workshop",
  "location": "Conference Hall"
}
```

Updates both the title and location of the existing event while preserving its ID.

Returns `200 OK` if the update is successful.

If the specified ID does not exist, the API returns `404 Not Found`.

### 5.6 DELETE — Delete an Event

**Request:**
```http
DELETE /api/events/4
```

Deletes the event with the specified ID.

Returns `204 No Content` if the deletion is successful.

If the event does not exist, the API returns `404 Not Found`.

## 6. Initial Event Data

The application starts with at least three predefined events:

| ID | Title | Location |
|---|---|---|
| 1 | Web Design Workshop | Almaty |
| 2 | JavaScript Quiz | Room 101 |
| 3 | IT Career Talk | Conference Hall |

New events receive automatically generated IDs. IDs are not reused after deletion during the same application run.

## 7. HTTP Status Codes

| Status Code | Meaning | Description |
|---|---|---|
| 200 OK | Successful | Request completed successfully |
| 201 Created | Created | A new event was successfully created |
| 204 No Content | Deleted | Event successfully deleted; no response body |
| 400 Bad Request | Invalid Request | Missing or blank title or location |
| 404 Not Found | Not Found | Requested event ID does not exist |

## 8. Running the Application

### Prerequisites

Make sure the following tools are installed:

- JDK 17 or compatible version
- IntelliJ IDEA or another Java IDE
- Gradle (the project includes a Gradle Wrapper)
- Postman or another HTTP client

### Step 1. Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

### Step 2. Open the Project

Open the project in IntelliJ IDEA and wait for Gradle to finish synchronizing dependencies.

### Step 3. Run the Application

Run the main Spring Boot application class:

`EventRegistrationApiApplication.java`

Alternatively, use the Gradle Wrapper.

**Windows:**

```bash
gradlew.bat bootRun
```

**macOS / Linux:**

```bash
./gradlew bootRun
```

### Step 4. Access the API

Once the application starts successfully, the API is available at:

```text
http://localhost:8080/api/events
```

Use Postman to test the HTTP endpoints.

## 9. Testing with Postman

The API was tested using Postman.

The following operations were verified:

1. GET request to retrieve the initial list of events.
2. GET request to retrieve an event by ID.
3. GET request to search events using the `title` query parameter.
4. GET request with a non-existing ID and a search with no matches.
5. POST request to create a new event using a JSON request body.
6. PUT request to update an existing event.
7. DELETE request to remove an event.
8. PUT and DELETE requests with non-existing IDs.
9. POST request with invalid input to verify the `400 Bad Request` response.
10. Verification that deleted IDs are not reused during the same application run.

## 10. Application Architecture

The application follows a simple layered architecture consisting of three main components.

**EventInfo (Model)**

Represents an event and contains its ID, title, and location.

**EventService (Service)**

Contains the business logic and manages the in-memory list of events. It is responsible for retrieving, creating, updating, searching, and deleting events.

**RegistrationController (Controller)**

Handles incoming HTTP requests and returns appropriate responses. It uses annotations such as `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@PathVariable`, `@RequestParam`, and `@RequestBody`.

The service is injected into the controller through constructor injection, ensuring separation of responsibilities.

## 11. Data Storage

The application uses an in-memory data structure to store events.

No external database is connected. Therefore:

- Event changes are available while the application is running.
- New events receive unique, automatically generated IDs.
- Deleted IDs are not reused during the current application run.
- All modifications disappear after restarting the application.
- The original sample events are restored when the application starts again.

## 12. Conclusion

During this laboratory work, a RESTful Event Registration API was implemented using Spring Boot.

The project demonstrates the practical use of HTTP methods (GET, POST, PUT, and DELETE), request mapping annotations, JSON request bodies, query parameters, path variables, constructor injection, and service-layer architecture.

The application was tested using Postman to verify CRUD operations, title-based searching, input validation, and appropriate HTTP response codes.

This laboratory work provided practical experience in developing and testing REST APIs using Spring Boot.
