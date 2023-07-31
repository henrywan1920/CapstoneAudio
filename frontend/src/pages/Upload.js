import React, { Component } from "react";
import { Buffer } from "buffer";
// import axios from "axios";

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

// const Upload = () => {
//   const[fileData, setFileData] = useState({
//       playlist: 'default',
//       mediaFileName: "",
//       transcriptFileName: "",
//       language: "English",
//       content: null
//   })

//   const handlePlaylistChange = (event) => {
//     // const { name, value } = event.target;
//     setFileData((prevFileData) => ({
//       ...prevFileData,
//       playlist: event.target.playlist
//     }));
//   };

//   const handleFileChange = (event) => {
//     // const { name, value } = event.target;
//     const inputFile = event.target.files[0];
//     setFileData((prevFileData) => ({
//       ...prevFileData,
//       mediaFileName: inputFile.name,
//       transcriptName: getTranscriptNameFrom(inputFile.name),
//       content: inputFile
//     }));
//   };

// const getTranscriptNameFrom = (inputFileName) => {
//   const temp = inputFileName.split(".");
//   return temp[0];
// }

// const handleSubmit = (event) => {
//   event.preventDefault();
//   setFileData((prevFileData) => ({
//     ...prevFileData,
//     language: "English"
//   }));
//   const requestBody = {
//     "playlist": fileData.playlist,
//     "mediaFileName": fileData.mediaFileName,
//     "transcriptFileName": fileData.transcriptFileName,
//     "language": fileData.language,
//     "content": fileData.content
//   };
//   let username = 'anna123@outlook.com';
//   let password = 'audio123';

//   fetch(fileUploadURL, {
//     method: 'POST',
//     body: JSON.stringify(requestBody),
//     headers: {
//       Authorization: 'Basic ' + Buffer.from(username + ':' + password).toString('base64'),
//       'Content-Type': 'multipart/form-data'
//     },
//   })
//   .then((response) => {
//     if (response.ok) {
//       console.log(response.json());
//     }
//     else {
//       console.log("Something wrong happened");
//       console.log(response.json());
//     }
//   })
//   .catch((error) => {
//     console.error(error); // Handle any error that occurred during the request
//   });
// };

// return (
//   <div>
//     <h1 className="subTitle">Upload Audio</h1>

//     <form className="fileUploadForm" encType="multipart/form-data" onSubmit={handleSubmit} >
//       <div className="field">
//           <label htmlFor="playlist">Playlist:</label>
//           <input type="text" id="playlist" name="playlist" value={fileData.playlist}
//             onChange={handlePlaylistChange}
//           />
//       </div>
//       <div className="field">
//           <label htmlFor="audioFile">Select Audio File:</label>
//           <input type="file" id="audioFile" onChange={handleFileChange} /><br/><br/>
//       </div>
//       <input type="submit" value="Upload" />
//     </form>
//   </div>
// );
// }

class Upload extends Component {

  // API Endpoints
  custom_file_upload_url = fileUploadURL;

  constructor(props) {
    super(props);
    this.username = "anna123@outlook.com";
    this.password = "audio123";
    this.state = {
      playlist: 'default',
      mediaFileName: "",
      transcriptFileName: "",
      language: "English",
      content: null
    }
  }

  getTranscriptNameFrom = (inputFileName) => {
    const temp = inputFileName.split(".");
    return temp[0];
  }

  handlePlaylistChange = (event) => {
    this.setState((prevFileData) => ({
      ...prevFileData,
      playlist: event.target.value
    }));
  };

  handleLanguageChange = (event) => {
    this.setState((prevFileData) => ({
      ...prevFileData,
      language: event.target.value
    }));
  };

  handleFileChange = (event) => {
    const inputFile = event.target.files[0];
    this.setState((prevFileData) => ({
      ...prevFileData,
      mediaFileName: inputFile.name,
      transcriptFileName: this.getTranscriptNameFrom(inputFile.name),
      content: inputFile
    }));
  };

  handleSubmit = (event) => {
    event.preventDefault();
    if (this.state.content !== null) {
      let formData = new FormData();
      console.log("Playlist: " + this.state.playlist);
      formData.append("playlist", this.state.playlist);
      formData.append("mediaFileName", this.state.mediaFileName);
      formData.append("transcriptFileName", this.state.transcriptFileName);
      formData.append("language", this.state.language);
      formData.append("content", this.state.content);
      fetch("http://localhost:5000/api/audio", {
        method: "POST",
        credentials: 'include',
        mode: 'no-cors',
        headers: {
            'Authorization': 'Basic ' + Buffer.from(this.username + ':' + this.password).toString('base64'),
            'Content-Type': 'application/json'
        },
        body: formData
      })
      .then(res => {
        if (res.status === 200) {
          console.log(`Success` + res.data);
        }
        else {
          console.log(`Failed` + res.data);
        }
      })
      .catch(err => {
          console.log(err);
      })
    }
  }

  render() {
    return (
      <div>
        <h1 className="subTitle">Upload Audio</h1>
        <form className="fileUploadForm">
          <div className="field">
            <label htmlFor="playlist">Playlist:</label>
            <input type="text" id="playlist" name="playlist" value={this.state.playlist}
              onChange={this.handlePlaylistChange}
            />
          </div>
          <div className="field">
            <label htmlFor="language">Language:</label>
            <input type="text" id="language" name="language" value={this.state.language}
              onChange={this.handleLanguageChange}
            />
          </div>
          <div className="field">
            <label htmlFor="audioFile">Select Audio File:</label>
            <input type="file" id="audioFile" onChange={this.handleFileChange} /><br/><br/>
          </div>
          <input type="submit" onClick={this.handleSubmit} value="Upload" />
        </form>
      </div>
    );
  }
}

export default Upload;