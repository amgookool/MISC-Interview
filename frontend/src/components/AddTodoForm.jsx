// AddTodoForm.js
import { useState } from "react";
import { Form, Button } from "react-bootstrap";
import axios from "axios";
import PropTypes from "prop-types";

const AddTodoForm = ({ onAddTodo }) => {
  const backendUrl = "http://localhost:8080"; // The URL of the backend
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const newTodo = { name, description };
      const response = await axios.post(backendUrl + "/api/items", newTodo);
      onAddTodo(response.data);
      setName("");
      setDescription("");
    } catch (error) {
      console.error("Error adding todo:", error);
    }
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group controlId="todoName">
        <Form.Label>Name</Form.Label>
        <Form.Control
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
      </Form.Group>
      <Form.Group controlId="todoDescription">
        <Form.Label>Description</Form.Label>
        <Form.Control
          as="textarea"
          rows={3}
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />
      </Form.Group>
      <Button variant="primary" type="submit">
        Submit
      </Button>
    </Form>
  );
};

AddTodoForm.propTypes = {
  onAddTodo: PropTypes.func.isRequired,
};

export default AddTodoForm;
