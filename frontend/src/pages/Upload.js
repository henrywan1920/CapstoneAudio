import React, {useState} from "react";
import { Buffer } from 'buffer';
const Upload = () => {
    const[fileData, setFileData] = useState({
        playlist: 'default',
        fileName: "",
        language: "English",
        content: null
    })

    const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFileData((prevFileData) => ({
      ...prevFileData,
      [name]: value,
    }));
  };

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    setFileData((prevFileData) => ({
      ...prevFileData,
      fileName: file.name,
      content: file,
    }));
  };


    const handleSubmit = (event) => {
    event.preventDefault();
    const { playlist, fileName, language, content } = fileData;

    const reader = new FileReader();
    reader.onload = (event) => {
      const fileContent = event.target.result;

      const requestBody = {
        playlist,
        fileName: fileName,
        language,
        content: fileContent,
      };
      console.log(requestBody);
      let username = 'tom678@gmail.com';
      let password = 'audio123';
      fetch('http://localhost:5000/api/audio', {
        
        method: 'POST',
        body: JSON.stringify(requestBody),
        headers: {
          Authorization: 'Basic ' + Buffer.from(username + ':' + password).toString('base64'),
          'Content-Type': 'application/json'
        },
      })
        .then((response) => response.json())
        .then((data) => {
          console.log(data); // Handle the response data
        })
        .catch((error) => {
          console.error(error); // Handle any error that occurred during the request
        });
    };

    reader.readAsDataURL(content);
  };

  return (
    <div>
      <h1 className="subTitle">Upload Audio</h1>

      <form className="form" onSubmit={handleSubmit}>
        <div className="field">
            <label htmlFor="playlist">Playlist:</label>
            <input
            type="text"
            id="playlist"
            name="playlist"
            value={fileData.playlist}
            onChange={handleInputChange}
            />
        </div>
        <div className="field">
            <label htmlFor="audioFile">Select Audio File:</label>
            <input type="file" id="audioFile" onChange={handleFileChange} /><br/><br/>
        </div>
        <input type="submit" value="Upload" />
      </form>
    </div>
  );
}

export default Upload;