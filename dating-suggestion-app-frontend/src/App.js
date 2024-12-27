import React, { useState } from 'react';
import axios from 'axios';
import './App.css';
import { FaHeart } from 'react-icons/fa';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  const [username, setUsername] = useState('');
  const [gender, setGender] = useState('Male');
  const [age, setAge] = useState('');
  const [interests, setInterests] = useState('');
  const [count, setCount] = useState(1);
  const [matches, setMatches] = useState([]);
  const [loading, setLoading] = useState(false);

  const handleSearch = async () => {
    setLoading(true);
    try {
      const response = await axios.get('http://localhost:8081/api/users/matches', {
        params: { username, count },
      });
      setMatches(response.data);
      toast.success('Matches fetched successfully!', { autoClose: 1000 });
    } catch (error) {
      console.error('Error fetching matches:', error);
      toast.error('Failed to fetch matches', { autoClose: 1000 });
    }
    setLoading(false);
  };

  const handleAddUser = async () => {
    setLoading(true);
    try {
      const newUser = {
        username,
        gender,
        age: parseInt(age),
        interests: interests.split(',').map(item => item.trim()),
      };
      await axios.post('http://localhost:8081/api/users', newUser);
      toast.success('User added successfully!', { autoClose: 1000 });
    } catch (error) {
      console.error('Error adding user:', error);
      toast.error('Failed to add user', { autoClose: 1000 });
    }
    setLoading(false);
  };

  const refreshPage = () => {
    window.location.reload();
  };

  return (
    <div className="app-container">
      <nav className="navbar">
        <FaHeart className="nav-icon" onClick={refreshPage} />
        <h1>Dating Match Finder</h1>
      </nav>
      <div className="content">
        <h1 className="title">Find Your Best Matches</h1>
        <div className="form-container">
          <input
            type="text"
            placeholder="Enter your username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="input-field"
          />
          <input
            type="number"
            placeholder="Age"
            value={age}
            onChange={(e) => setAge(e.target.value)}
            className="input-field"
          />
          <select value={gender} onChange={(e) => setGender(e.target.value)} className="input-field">
            <option value="Male">Male</option>
            <option value="Female">Female</option>
          </select>
          <input
            type="text"
            placeholder="Interests (comma separated)"
            value={interests}
            onChange={(e) => setInterests(e.target.value)}
            className="input-field"
          />
          <button onClick={handleAddUser} className="add-button">Add User</button>
        </div>

        <h2 className="title">Search Matches</h2>
        <div className="form-container">
          <input
            type="text"
            placeholder="Enter your username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="input-field"
          />
          <input
            type="number"
            placeholder="Number of matches"
            value={count}
            onChange={(e) => setCount(Math.max(0, e.target.value))}
            className="input-field"
          />
          <button onClick={handleSearch} className="search-button">Find Matches</button>
        </div>

        {loading ? (
          <div className="loading">Loading...</div>
        ) : (
          <div className="matches-container">
            {matches.length > 0 ? (
              matches.map((user, index) => (
                <div key={index} className="match-card animate">
                  <h2>{user.username.toUpperCase()}</h2>
                  <p><strong>GENDER:</strong> {user.gender.toUpperCase()}</p>
                  <p><strong>AGE:</strong> {user.age}</p>
                  <p><strong>INTERESTS:</strong> {user.interests.join(', ').toUpperCase()}</p>
                </div>
              ))
            ) : (
              <div>No matches found</div>
            )}
          </div>
        )}
      </div>
      <footer className="footer">&copy; 2024 Dating App. All rights reserved.</footer>
      <ToastContainer />
    </div>
  );
}

export default App;
