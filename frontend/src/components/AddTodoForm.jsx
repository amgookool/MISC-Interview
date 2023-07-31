// AddTodoForm.js
import { useState } from "react"; // React hooks for function components
import { Form, Button } from "react-bootstrap"; // React Bootstrap components
import axios from "axios"; // Axios library to make HTTP requests
import PropTypes from "prop-types"; // For checking types of variables

// Form for adding a new todo
// The onAddTodo function is passed as a prop to the component from the parent component
const AddTodoForm = ({ onAddTodo }) => {
  const backendUrl = "http://192.168.100.141:8080"; // The URL of the backend
  const [name, setName] = useState(""); // React hook for the name of the todo
  const [description, setDescription] = useState(""); // React hook for the description of the todo

  // Function to handle the submit event of the form
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent the default behavior of the form

    try {
      const newTodo = { name, description }; // Create a new todo object
      const response = await axios.post(backendUrl + "/api/items", newTodo); // Send a POST request to the backend
      // Call the onAddTodo function passed as a prop to the component to add the new todo to the list of todos in the parent component
      onAddTodo(response.data);
      setName(""); // Reset the name field of the form
      setDescription(""); // Reset the description field of the form
    } catch (error) {
      console.error("Error adding todo:", error);
    }
  };

  return (
    // Form for adding a new todo
    <Form onSubmit={handleSubmit}>
      {/* Name Element of the form*/}
      <Form.Group controlId="todoName">
        <Form.Label>Name</Form.Label>
        <Form.Control
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
      </Form.Group>

      {/* Description element of the form */}
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

      {/* Submit Button */}
      <Button variant="primary" type="submit">
        Submit
      </Button>
    </Form>
  );
};

// Check the type of the onAddTodo prop
AddTodoForm.propTypes = {
  onAddTodo: PropTypes.func.isRequired,
};

export default AddTodoForm;
