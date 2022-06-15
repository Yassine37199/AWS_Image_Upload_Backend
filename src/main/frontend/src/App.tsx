import { useState , useEffect } from 'react'
import logo from './logo.svg'
import './App.css'
import axios from 'axios'
import { FileDropzone } from './Components/dropzone/dropzone.component'



type UserProfileType = {
  userProfileId : string,
  username : string,
  userProfileImageLink : string
}

function App() {

  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8081/api/v1/user-profile")
    .then((res:any) => {
      setUserProfiles(res.data);
      console.log(userProfiles) 
    })
  };

  useEffect(() => {
    fetchUserProfiles();
  }, [])



  return (
    <div className="App">
      <FileDropzone />
      {
        userProfiles.map((profile : UserProfileType) => (
          <>
            <h1>{profile.username}</h1>
            <h2>{profile.userProfileId}</h2>
          </>
        ))
      }
    </div>
  )
}

export default App
