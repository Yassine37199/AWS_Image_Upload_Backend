
import React from 'react'
import { UserProfileModel } from '../../models/user-profile'
import { FileDropzone } from '../dropzone/dropzone.component'


type UserProfilePropsType = {
    profile : UserProfileModel
}


export const UserProfile = (props : UserProfilePropsType) => {

    const {username , userProfileId , userProfileImageLink} = props.profile

    return (
    <> 
    <br />
    <h1>{username}</h1>
    <h2>{userProfileId}</h2>
    <br />
    <FileDropzone userProfileId={userProfileId} />
  </> )
}