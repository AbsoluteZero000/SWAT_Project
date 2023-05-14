import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';

import Login from './pages/Login';
import Register from './pages/Register';
import ResturantMenu from './pages/CreateResturantMenu';
import Layout from './pages/Layout';

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route exact path="/" element={<Layout />}/>
          <Route exact path='/login' element={<Login />} />
          <Route exact path='/register' element={<Register />} />
          <Route exact path='/createMenu' element={<ResturantMenu />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
