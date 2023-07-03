import React, { useState } from "react";

const SingleFileUpload = () => {
   const [selectedFile, setSelectedFile] = useState(null);
   const handleFileChange = (e) => {
      setSelectedFile(e.target.files[0]);
   };

   const handleUpload = async () => {
      if (!selectedFile) {
         alert("Please first select a file");
         return;
      }

      const formData = new FormData();
      formData.append("content", selectedFile);
      formData.append("playlist", "EnglishA1");
      formData.append("mediaFileName", "Celpip_9_T1_11.mp3");
      formData.append("transcriptFileName", "Celpip_9_T1_11.srt");
      formData.append("language", "English");

      try {
        let username = 'john573@gmail.com';
        let password = 'audio123';
         // Replace this URL with your server-side endpoint for handling file uploads
         const response = await fetch("http://localhost:5000/api/audio", {
            method: "POST",
            credentials: 'include',
            mode: 'no-cors',
            headers: {
                'Authorization': 'Basic ' + window.btoa(username + ':' + password),
                'Content-Type': 'application/json'
            },
            body: formData
         });

         if (response.ok) {
            alert("File upload is  successfully");
            console.log(response.json());
         } else {
            alert("Failed to upload the file due to errors");
         }
      } catch (error) {
         console.error("Error while uploading the file:", error);
         alert("Error occurred while uploading the file");
      }
   };

   return (
   <div>
      <h2>Single File Upload</h2>
      <input type="file" onChange={handleFileChange} />
      <button onClick={handleUpload}>Upload</button>
   </div>
   );
};
export default SingleFileUpload;