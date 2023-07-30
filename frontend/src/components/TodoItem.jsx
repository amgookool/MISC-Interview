// TodoItem.js
import { useState } from 'react';
import { Card, Button, Form } from 'react-bootstrap';
import PropTypes from 'prop-types';

const TodoItem = ({ todo }) => {
  const [isEditing, setIsEditing] = useState(false);
  const [editedTitle, setEditedTitle] = useState(todo.name);
  const [editedDescription, setEditedDescription] = useState(todo.description);

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSave = () => {
    // Here, you can make the API call to update the todo on the backend
    // For the sake of this example, I'll just update the state locally
    setEditedTitle(editedTitle.trim());
    setEditedDescription(editedDescription.trim());
    setIsEditing(false);
  };

  const handleDelete = () => {
    // Here, you can make the API call to delete the todo on the backend
    // For the sake of this example, I'll just call a function to handle deletion
    deleteTodo();
  };

  const deleteTodo = () => {
    // Implement your deletion logic here
    console.log(`Deleting todo with ID: ${todo.id}`);
    // For a real implementation, you should call the backend API to delete the todo
  };

  return (
    <Card>
      <Card.Body>
        {isEditing ? (
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
            <Button variant="success" onClick={handleSave}>
              Save
            </Button>
            <Button variant="secondary" onClick={() => setIsEditing(false)}>
              Cancel
            </Button>
          </Form>
        ) : (
          <>
            <Card.Title>{todo.name}</Card.Title>
            <Card.Text>{todo.description}</Card.Text>
            <Button variant="primary" onClick={handleEdit}>
              Edit
            </Button>
            <Button variant="danger" onClick={handleDelete}>
              Delete
            </Button>
          </>
        )}
      </Card.Body>
    </Card>
  );
};

TodoItem.propTypes = {
  todo: PropTypes.shape({
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
  }).isRequired,
};


export default TodoItem;
