import homeDesign from './images/HomeDesign.svg';
import React from "react";
import './App.css';
import NavBar from './components/NavBar.js';
import axios from 'axios';

function App() {

  const loadSearchResults = async () => {
    const response = await axios.post('http://localhost:3000/api/search', { body: { "input": "Tegh" } });
    console.log(response);
  }

  return (
    <div className="App">
      <NavBar />
      <header id="Home" className='Home'>
        <div className='text-center'>
          <p className='header'>Get Legal Advice Quickly & Effectively</p>
          <p className='body'>We aim to provide everyone with the tools to be legally literate. Created for the GDSC Solutions Challenge 2024</p>
        </div>
        <img src={homeDesign} className='Home-graphic' alt='homepage graphic'/>
      </header>
      <header id='Search' className='Search'>
        <p className='text-3xl font-bold underline'>
          This is the app bro
        </p>
      </header>
      <header id="About" className='About'>
        <button onClick={loadSearchResults}>Search!</button>
      </header>
    </div>
  );
}

export default App;
