import { json, useNavigate, redirect } from "react-router-dom";
import axios from "axios";
import getCredential from "../tools/storage";

const baseURL = "http://localhost:5000";
// const baseURL = "http://audio-transcribe-services.us-east-2.elasticbeanstalk.com";

const fileUploadURL = baseURL + "/api/audio";

const Upload = () => {
  const navigate = useNavigate();

  const getTranscriptNameFrom = (inputFileName) => {
    const temp = inputFileName.split(".");
    return temp[0];
  }
  
  const handleSubmit = async (event) => {
    const credential = getCredential();
    if (!credential || !credential.username || !credential.password) {
      return redirect("/");
    }
    const username = credential.username;
    const password = credential.password;
    event.preventDefault();
    const inputFile = event.target.audioFile.files[0];
    let requestFormData = new FormData();
    requestFormData.append('playlist', event.target.playlist.value);
    requestFormData.append('mediaFileName', inputFile.name);
    requestFormData.append('transcriptFileName', getTranscriptNameFrom(inputFile.name));
    requestFormData.append('language', event.target.language.value);
    requestFormData.append('content', inputFile);
    const response = await axios.post(
      fileUploadURL, requestFormData, {
        headers: {
          'Authorization': 'Basic ' + btoa(username + ":" + password),
          'Content-Type': 'multipart/form-data'
        }
      }
    );
    if (response.status === 200) {
      navigate('/success', {state: {
        message: "Media file was uploaded successfully!"
      }});
    }
    else {
      throw json(
        { message: 'Could not upload audio file.' },
        {
          status: 500,
        }
      );
    }
  }

  return (
    <div>
      <h1 className="subTitle">Upload Audio</h1>
      <form action="/api/audio" method="POST" onSubmit={handleSubmit} className="form">
        <div className="field">
          <label htmlFor="playlist">Playlist:</label>
          <input type="text" id="playlist" name="playlist" defaultValue="EnglishA0" />
        </div>
        <div className="field">
          <label htmlFor="language">Language:</label>
          <input type="text" id="language" name="language" defaultValue="English" />
        </div>
        <div className="field">
          <label htmlFor="audioFile">Select Audio File:</label>
          <input type="file" id="audioFile" name="audioFile" /><br/><br/>
        </div>
        <input type="submit" value="Upload" />
      </form>
    </div>);
}

export default Upload;