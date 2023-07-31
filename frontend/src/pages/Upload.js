import React, { useState } from "react";
import { Buffer } from "buffer";

const baseURL = "http://localhost:5000";
// const baseURL = "http://audio-transcribe-services.us-east-2.elasticbeanstalk.com";
const fileUploadURL = baseURL + "/api/audio";

// const Upload = () => {
//   const [selectedFile, setSelectedFile] = useState(null);
//   const handleFileChange = (e) => {
//     setSelectedFile(e.target.files[0]);
//   };

//   const handleUpload = async () => {
//     if (!selectedFile) {
//       alert("Please first select a file");
//       return;
//     }

//     const formData = new FormData();
//     formData.append("content", selectedFile);
//     formData.append("playlist", "FrenchB2");
//     formData.append("mediaFileName", "TEF_3_T1_L1.mp3");
//     formData.append("transcriptFileName", "TEF_3_T1_L1");
//     formData.append("language", "French");

//     try {
//       let username = "anna123@outlook.com";
//       let password = "audio123";
//       // Replace this URL with your server-side endpoint for handling file uploads
//       const response = await fetch(fileUploadURL, {
//         method: "POST",
//         credentials: "include",
//         mode: "no-cors",
//         headers: {
//           Authorization:
//             "Basic " +
//             Buffer.from(username + ":" + password).toString("base64"),
//           "Content-Type": "application/json",
//         },
//         body: formData,
//       });

//       if (response.ok) {
//         alert("File upload is  successfully");
//         console.log(response.json());
//       } else {
//         alert("Failed to upload the file due to errors");
//       }
//     } catch (error) {
//       console.error("Error while uploading the file:", error);
//       alert("Error occurred while uploading the file");
//     }
//   };

//   return (
//     <div>
//       <h1 className="subTitle">Upload Audio</h1>

//       <form className="fileUploadForm" onSubmit={handleSubmit}>
//         <div className="field">
//             <label htmlFor="playlist">Playlist:</label>
//             <input
//             type="text"
//             id="playlist"
//             name="playlist"
//             value={fileData.playlist}
//             onChange={handleInputChange}
//             />
//         </div>
//         <div className="field">
//             <label htmlFor="audioFile">Select Audio File:</label>
//             <input type="file" id="audioFile" onChange={handleFileChange} /><br/><br/>
//         </div>
//         <input type="submit" value="Upload" />
//       </form>
//     </div>
//   );
// };

const Upload = () => {
  const[fileData, setFileData] = useState({
      playlist: 'default',
      mediaFileName: "",
      transcriptFileName: "",
      language: "English",
      content: null
  })

  const handleInputChange = (event) => {
    // const { name, value } = event.target;
    const inputFile = event.target.files[0];
    setFileData((prevFileData) => ({
      ...prevFileData,
      playlist: event.target.playlist,
      language: event.target.language,
      mediaFileName: inputFile.name,
      transcriptName: getTranscriptNameFrom(inputFile.name),
      content: inputFile
    }));
  };

const getTranscriptNameFrom = (inputFileName) => {
  const temp = inputFileName.split(".");
  return temp[0];
}

const handleSubmit = (event) => {
  event.preventDefault();

  const requestBody = {
    "playlist": fileData.playlist,
    "mediaFileName": fileData.mediaFileName,
    "transcriptFileName": fileData.transcriptFileName,
    "language": fileData.language,
    "content": fileData.content
  };
  let username = 'anna123@outlook.com';
  let password = 'audio123';

  fetch(fileUploadURL, {
    method: 'POST',
    body: JSON.stringify(requestBody),
    headers: {
      Authorization: 'Basic ' + Buffer.from(username + ':' + password).toString('base64'),
      'Content-Type': 'multipart/form-data'
    },
  })
  .then((response) => {
    if (response.ok) {
      console.log(response.json());
    }
    else {
      console.log("Something wrong happened");
      console.log(response.json());
    }
  })
  .catch((error) => {
    console.error(error); // Handle any error that occurred during the request
  });
};

return (
  <div>
    <h1 className="subTitle">Upload Audio</h1>

    <form className="fileUploadForm" onSubmit={handleSubmit}>
      <div className="field">
          <label htmlFor="playlist">Playlist:</label>
          <input type="text" id="playlist" name="playlist" value={fileData.playlist}
            onChange={handleInputChange}
          />
      </div>
      <div className="field">
          <label htmlFor="audioFile">Select Audio File:</label>
          <input type="file" id="audioFile" onChange={handleInputChange} /><br/><br/>
      </div>
      <input type="submit" value="Upload" />
    </form>
  </div>
);
}
export default Upload;
