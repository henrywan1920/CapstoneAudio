import React, { useState } from "react";
import { Buffer } from "buffer";

// const baseURL = "http://localhost:5000";
const baseURL =
  "http://audio-transcribe-services.us-east-2.elasticbeanstalk.com";
const fileUploadURL = baseURL + "/api/audio";

const Upload = () => {
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
    formData.append("playlist", "FrenchB2");
    formData.append("mediaFileName", "TEF_3_T1_L1.mp3");
    formData.append("transcriptFileName", "TEF_3_T1_L1");
    formData.append("language", "French");

    try {
      let username = "anna123@outlook.com";
      let password = "audio123";
      // Replace this URL with your server-side endpoint for handling file uploads
      const response = await fetch(fileUploadURL, {
        method: "POST",
        credentials: "include",
        mode: "no-cors",
        headers: {
          Authorization:
            "Basic " +
            Buffer.from(username + ":" + password).toString("base64"),
          "Content-Type": "application/json",
        },
        body: formData,
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
      <h1 className="subTitle">Upload Audio</h1>
      <input type="file" onChange={handleFileChange} />
      <button onClick={handleUpload}>Upload</button>
    </div>
  );
};
export default Upload;
