// TodoList.js
import { useState, useEffect } from "react";
import axios from "axios";
import { Card, Button, Form } from "react-bootstrap";

const TodoList = () => {
  const backendUrl = "http://localhost:8080"; // The URL of the backend
  // The useState hook is used to store the todos in the state of the component
  const [todos, setTodos] = useState([]); // The initial value of the todos is an empty array
  const [editingTodoId, setEditingTodoId] = useState(null); // The initial value of the editingTodoId is null
  const [editedTitle, setEditedTitle] = useState(""); // The initial value of the editedTitle is an empty string
  const [editedDescription, setEditedDescription] = useState(""); // The initial value of the editedDescription is an empty string

  // The useEffect hook is used to fetch the todos from the backend
  useEffect(() => {
    fetchTodos();
  }, []);

  // The fetchTodos function is an async function that uses the axios library to make a GET request to the /api/items endpoint of the backend
  const fetchTodos = async () => {
    try {
      const response = await axios.get(backendUrl + "/api/items");
      setTodos(response.data);
    } catch (error) {
      console.error("Error fetching todos:", error);
    }
  };

  // The handleEditTodo function is used to set the editingTodoId, editedTitle and editedDescription in the state of the component
  const handleEditTodo = (todo) => {
    setEditingTodoId(todo.id);
    setEditedTitle(todo.name);
    setEditedDescription(todo.description);
  };

  // The handleSaveEdit function is an async function that uses the axios library to make a PUT request to the /api/items/:id endpoint of the backend
  const handleSaveEdit = async (todoId) => {
    try {
      await axios.put(backendUrl + `/api/items/${todoId}`, {
        name: editedTitle,
        description: editedDescription,
      });
      setEditingTodoId(null);
      fetchTodos(); // Refresh the todos after successful update
    } catch (error) {
      console.error("Error updating todo:", error);
    }
  };

  // The handleCancelEdit function is used to set the editingTodoId to null
  const handleCancelEdit = () => {
    setEditingTodoId(null);
  };

  // The handleDeleteTodo function is an async function that uses the axios library to make a DELETE request to the /api/items/{id} endpoint of the backend
  const handleDeleteTodo = async (todoId) => {
    try {
      await axios.delete(backendUrl + `/api/items/${todoId}`);
      fetchTodos(); // Refresh the todos after successful delete
    } catch (error) {
      console.error("Error deleting todo:", error);
    }
  };

  return (
    <div>
      {todos.length === 0 ? (
        <p>No todos found</p>
      ) : (
        todos.map((todo) => (
          <Card key={todo.id} className="mb-2">
            <Card.Body>
              {editingTodoId === todo.id ? (
                <Form>
                  <Form.Group controlId={`editTodoTitle${todo.id}`}>
                    <Form.Control
                      type="text"
                      value={editedTitle}
                      onChange={(e) => setEditedTitle(e.target.value)}
                      required
                    />
                  </Form.Group>
                  <Form.Group controlId={`editTodoDescription${todo.id}`}>
                    <Form.Control
                      as="textarea"
                      rows={3}
                      value={editedDescription}
                      onChange={(e) => setEditedDescription(e.target.value)}
                      required
                    />
                  </Form.Group>
                  <Button
                    variant="success"
                    onClick={() => handleSaveEdit(todo.id)}
                  >
                    Save
                  </Button>
                  <Button variant="secondary" onClick={handleCancelEdit}>
                    Cancel
                  </Button>
                </Form>
              ) : (
                <>
                  <Card.Title>{todo.name}</Card.Title>
                  <Card.Text>{todo.description}</Card.Text>
                  <Button
                    variant="primary"
                    onClick={() => handleEditTodo(todo)}
                    disabled={editingTodoId !== null}
                  >
                    Edit
                  </Button>
                  <Button
                    variant="danger"
                    onClick={() => handleDeleteTodo(todo.id)}
                    disabled={editingTodoId !== null}
                  >
                    Delete
                  </Button>
                </>
              )}
            </Card.Body>
          </Card>
        ))
      )}
    </div>
  );
};

export default TodoList;