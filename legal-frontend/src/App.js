import React, { useState } from "react";
import axios from 'axios';
import './App.css';
import homeDesign from './images/HomeDesign.svg';
import NavBar from './components/NavBar.js';
import SearchBar from './components/SearchBar.js';
import AboutSection from "./components/AboutSection.js";

function App() {
  const [searchInput, setSearchInput] = useState("");

  const loadSearchResults = async ({ searchInput }) => {
    const response = await axios.post('http://localhost:3000/api/search', { body: { "input": searchInput } });
    console.log(response);
  }

  return (
    <div className="App">

      <NavBar />
      <header id="Home" className='Home'>
        <div className='text-center'>
          <p className='header'>Get Legal Advice Quickly & Effectively</p>
          <p className='body'>We aim to provide everyone with the tools to be legally literate. Created for the GDSC Solutions Challenge 2024.</p>
        </div>
        <img src={homeDesign} className='Home-graphic' alt='homepage graphic' />
      </header>
      <header id='Search' className='Search'>

        <div className='text-center'>
          <p className='header mb-10'>Decode Legalese in Seconds</p>
          <p className='body mb-16'>Empower yourself with our AI tool that distills legal documents into bite-sized information, tailored to your specific questions.</p>
        </div>

        <SearchBar searchInput={searchInput} setSearchInput={setSearchInput} />

        <div className="my-10">
          <button className="bg-transparent hover:bg-slate-900 text-slate-950 font-serif hover:text-white py-2 px-4 border border-slate-950"
            onClick={loadSearchResults}>
            Search!
          </button>
        </div>
      </header>

      <header id="About" className='About'>

        <p className='header mt-40'>Our Story</p>
        <div className="mt-10">
          <AboutSection />
        </div>
      </header>
    </div>
  );
}

export default App;
