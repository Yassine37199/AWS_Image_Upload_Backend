
import React from 'react'
import { UserProfileModel } from '../../models/user-profile'
import { FileDropzone } from '../dropzone/dropzone.component'

import './user-profile.styles.css';


type UserProfilePropsType = {
    profile : UserProfileModel
}


export const UserProfile = (props : UserProfilePropsType) => {

    const {username , userProfileId , userProfileImageLink} = props.profile

    return (
    <> 
    {userProfileId ? <img src={`http://localhost:8081/api/v1/user-profile/${userProfileId}/image/download`} /> 
                    : null}
    <br />
    <h1>{username}</h1>
    <h2>{userProfileId}</h2>
    <br />
    <FileDropzone userProfileId={userProfileId} />
  </> )
}