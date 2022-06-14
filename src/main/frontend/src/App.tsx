import { useState , useEffect } from 'react'
import logo from './logo.svg'
import './App.css'
import axios from 'axios'


function App() {

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8081/api/v1/user-profile")
    .then(res => console.log(res))
  }

  useEffect(() => {
    fetchUserProfiles();
  }, [fetchUserProfiles])



  return (
    <div className="App">
      
    </div>
  )
}

export default App
