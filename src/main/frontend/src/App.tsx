import { useState , useEffect } from 'react'
import logo from './logo.svg'
import './App.css'
import axios from 'axios'
import { FileDropzone } from './Components/dropzone/dropzone.component'
import { UserProfileModel } from './models/user-profile'
import { UserProfile } from './Components/user-profile/user-profile.component'



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
    <div className="App">      {
        userProfiles.map((profile : UserProfileModel) => (
          <UserProfile key={profile.userProfileId} profile={profile} />
        ))
      }
    </div>
  )
}

export default App
