// import { Player } from "webvtt-player";
// import { useState, useEffect } from "react";
// import audioFilePath from "./img/Celpip_9_T1_11.mp3";
// import vttFilePath from "./img/Celpip_9_T1_11.vtt";

// export default function Player1() {
//   const [audioFile, setAudioFile] = useState(null);
//   const [vttFile, setVttFile] = useState(null);

//   useEffect(() => {
//     setAudioFile(audioFilePath);
//     setVttFile(vttFilePath);
//   }, []);

//   return (
//     <div className="Player1">
//       {audioFile && vttFile && (
//         <Player audio={audioFile} transcript={vttFile} />
//       )}
//     </div>
//   );
// }
import React, { Player } from "webvtt-player";
import { useEffect } from "react";

export default function Player1() {
  const audioFileUrl =
    "https://audio-capstone.s3.us-east-2.amazonaws.com/pool/john573_gmail.com/EnglishA1/Celpip_9_T1_11.mp3";
  const vttFileUrl =
    "https://audio-capstone.s3.us-east-2.amazonaws.com/pool/john573_gmail.com/EnglishA1/Celpip_9_T1_11.srt";
  //can't load srt or vtt url, 403 error (permission problem)

  useEffect(() => {}, []);

  return (
    <div className="Player1">
      {<Player audio={audioFileUrl} transcript={vttFileUrl} />}
    </div>
  );
}
