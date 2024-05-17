# Todo-List Application

This is a simple Todo-List application developed using Java, Spring Boot, and Maven. The application allows users to manage their tasks effectively. Users can create, update, and delete tasks. Each task has a name, description, creation date, conclusion date, and a completion status.

## Features

- User Authentication: Users can register and login to the application.
- Task Management: Users can create new tasks, mark tasks as completed, and delete tasks.
- The application currently uses an in-memory H2 database.

## How to Use

1. **Clone the repository**: Start by cloning the repository to your local machine using the command `git clone https://github.com/LuizCordista/spring-todo.git`.

2. **Install Dependencies**: Navigate to the project directory and run `mvn install` to install all the necessary dependencies.

4. **Run the Application**: Run the application using the command `mvn spring-boot:run`.

5. **Access the Application**: The application will be accessible at `http://localhost:8080`.

## API Endpoints

- `POST /auth/login`: Authenticate a user.
- `POST /auth/register`: Register a new user.
- `GET /api/tarefas`: Get all tasks for the authenticated user.
- `POST /api/tarefas`: Create a new task.
- `PUT /api/tarefas/{id}/concluir`: Toggle the completion status of a task.
- `DELETE /api/tarefas/{id}`: Delete a task.

Please note that all the `/api` endpoints require the user to be authenticated.

## Contributing

Contributions are welcome. Please feel free to submit a pull request or open an issue.
