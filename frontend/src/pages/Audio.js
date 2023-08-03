import React, { useState, useEffect } from "react";



const Audio = () => {
  const audiosData = {
    "status": 200,
    "message": "Get all the audios associated with a customer",
    "timeStamp": 1691022777344,
    "audios": {
      "FrenchB2": [
        "TEF_9_T1_1"
      ],
      "EnglishA1": [
        "Celpip_9_T1_11",
        "Celpip_9_T1_12",
        "Celpip_9_T1_13"
      ]
    }
  };

//   const [audiosData, setAudiosData] = useState([]);

//   useEffect(() => {
//     fetch("http://localhost:5000/dummy/api/audios")
//       .then((response) => response.json())
//       .then((data) => setAudiosData(data.audiosData))
//       .catch((error) => console.error("Error fetching data:", error));
//   }, []);

  return (
    <div>
      <h1 className="subTitle">Dashboard</h1>
      {Object.keys(audiosData.audios).map((language) => (
        <div className="dashboard" key={language}>
          <h2>{language}</h2>
          <ul>
            {audiosData.audios[language].map((audio) => (
              <li key={audio}>{audio}</li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
};

 
export default Audio;