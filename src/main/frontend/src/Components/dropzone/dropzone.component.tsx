import axios from 'axios';
import React, {useCallback, useMemo} from 'react';
import {useDropzone} from 'react-dropzone';
import { UserProfileModel } from '../../models/user-profile';

import './dropzone.styles.css'


 type DropzonePropsType = {
    userProfileId : string
}


const baseStyle = {
  flex: 1,
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  justifyContent : 'center',
  padding: '20px',
  borderWidth: 2,
  borderRadius: 2,
  borderColor: '#eeeeee',
  borderStyle: 'dashed',
  backgroundColor: '#fafafa',
  color: '#bdbdbd',
  outline: 'none',
  transition: 'border .24s ease-in-out'
};

const focusedStyle = {
  borderColor: '#2196f3'
};

const acceptStyle = {
  borderColor: '#00e676'
};

const rejectStyle = {
  borderColor: '#ff1744'
};

export const FileDropzone = (props : DropzonePropsType) => {

  const onDrop = useCallback((acceptedFiles : any) => {
    const {userProfileId} = props
    const file = acceptedFiles[0];
    const formData = new FormData();
    formData.append("file", file)

    axios.post(
      `http://localhost:8081/api/v1/user-profile/${userProfileId}/image/upload`,
      formData,
      {
        headers : {
          "Content-Type": "multipart/form-data"
        }
      }
    ).then(() => console.log("file uploaded successfully")
    ).catch(err => console.log(err))
  }, [])


  const {
    getRootProps,
    getInputProps,
    isFocused,
    isDragAccept,
    isDragReject
  } = useDropzone({accept: {'image/*': []} , onDrop});

  const style : any = useMemo(() => ({
    ...baseStyle,
    ...(isFocused ? focusedStyle : {}),
    ...(isDragAccept ? acceptStyle : {}),
    ...(isDragReject ? rejectStyle : {})
  }), [
    isFocused,
    isDragAccept,
    isDragReject
  ]);

  return (
    <div className="container">
      <div {...getRootProps({style})}>
        <input {...getInputProps()} />
        <p>Drag 'n' drop profile image here, or click to select files</p>
        
      </div>
    </div>
  );
}