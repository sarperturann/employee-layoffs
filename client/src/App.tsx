import { Container } from '@mui/material';
import React from 'react';
import './App.css';
import EmployeeTable from './components/EmployeeTable';
import TopBar from './components/TopBar';

function App() {
  return (
    <div className="App">
      <TopBar/>
      <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
        <EmployeeTable/>
      </Container>
    </div>
  );
}

export default App;
