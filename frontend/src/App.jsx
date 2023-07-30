// This is the main file of the application
// The application is a Todo List whose data is stored in a Spring Boot backend whose database is connected to a PostgreSQL database
// The application is built using ReactJS and the UI is designed using Bootstrap
import {useState} from "react";
import { Container } from "react-bootstrap";
import TodoList from "./components/TodoList";
import AddTodoForm from "./components/AddTodoForm";
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const [todos, setTodos] = useState([]);

  const handleAddTodo = (todo) => {
    setTodos([...todos, todo]);
  };

  return (
    <>
      <Container>
        <h1>Todo App</h1>
        <TodoList />
        <AddTodoForm onAddTodo={handleAddTodo}/>
      </Container>
    </>
  );
}

export default App;
