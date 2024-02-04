import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../images/LegalLogo.svg';

const NavBar = () => {
    
    return (
        <nav>
            <ul>
                <li>
                    {/* <Link to="/">Home</Link> */}
                    <a href="#App-header">Home</a>
                </li>
                <li>
                    {/* <Link to="/search">Search</Link> */}
                    <a href="#App-search">Search</a>
                </li>
                <li>
                    {/* <Link to="/about">About</Link> */}
                    <a href="#App-about">About</a>
                </li>
            </ul>
            <div className="nav-right">

                <img src={logo} className='nav-icon' alt='Legal Questioner'/> 
                Legal Questioner 
            </div>
        </nav>
    );
}

export default NavBar;