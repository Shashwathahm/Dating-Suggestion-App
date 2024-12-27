# Dating Suggestion App

This project is a full-stack dating recommendation engine designed to help users find the best matches based on gender, age, and shared interests. The project consists of two main components:

1. **Backend**: Spring Boot application that handles user data, matchmaking logic, and APIs.
2. **Frontend**: React application that provides an intuitive and responsive user interface for interacting with the system.

---

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Frontend Details](#frontend-details)
- [Adding Swagger to Backend](#adding-swagger-to-backend)
- [Project Structure](#project-structure)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

---

## Features
- User registration with name, age, gender, and interests.
- Matchmaking engine based on gender preference, age proximity, and interest overlap.
- Responsive UI with animations and toasts.
- Home icon refreshes the page for easy navigation.
- Real-time feedback with loading spinners and success/error toasts.

---

## Technologies

**Backend**:
- Java 21
- Spring Boot
- MongoDB
- Gradle

**Frontend**:
- React
- Axios
- React Icons
- React Toastify
- CSS (responsive and animated)

---

## Installation

### Prerequisites
- Java 17+
- Node.js (v18+)
- MongoDB
- Gradle

### Clone the Repository
```bash
$ git clone https://github.com/username/dating-suggestion-app.git
$ cd dating-suggestion-app
```

---

## Running the Application

### Backend
```bash
$ cd dating-suggestion-app-backend
$ ./gradlew bootRun
```

- Application will run on `http://localhost:8081`

### Frontend
```bash
$ cd dating-suggestion-app-frontend
$ npm install
$ npm start
```
- Frontend will be available at `http://localhost:3000`

---

## API Endpoints

### User Management
- `POST /api/users` - Add a new user
- `GET /api/users/matches` - Fetch top matches for a user

---

## Frontend Details
- Users can add themselves to the platform and search for matches.
- Beautiful and professional UI with animated elements.
- Real-time feedback through toast notifications and loading states.
- Match cards display results in uppercase with custom fonts.

---

## Project Structure

```
/dating-suggestion-app
│
├── dating-suggestion-app-backend
│   ├── src
│   │   ├── main
│   │   │   ├── java (Spring Boot code)
│   │   │   └── resources
│   │   │       └── application.properties
│   │   └── test (unit tests)
│   └── build.gradle
│
└── dating-suggestion-app-frontend
    ├── src
    │   ├── components
    │   ├── App.js
    │   ├── App.css
    │   └── index.js
    └── package.json
```

---

## Screenshots

![UI Screenshot](screenshots/ui.png)

---

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

---

## License
This project is licensed under the MIT License - see the LICENSE file for details.

