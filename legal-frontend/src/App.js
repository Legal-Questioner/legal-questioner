import homeDesign from './images/HomeDesign.svg';
import React, { Component } from "react";
import { Route, NavLink, HashRouter } from "react-router-dom"; 
import './App.css';
import NavBar from './components/NavBar.js';

function App() {
  return (
    <div className="App">
      <NavBar />
      <header className="App-header">
        <img src={homeDesign}/>
      </header>
      <header className="App-search">
        <p>
          This is the app bro
        </p>
      </header>
      <header className="App-about">
        
      </header>
    </div>
  );
}

export default App;
