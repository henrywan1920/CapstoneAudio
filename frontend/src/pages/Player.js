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
import { Suspense, useEffect } from "react";
import { json, defer, useRouteLoaderData, Await } from "react-router-dom";
import MediaItem from "./MediaItem";

const baseURL = "http://localhost:5000";

function MediaDetailPage() {
  const { audioResources } = useRouteLoaderData('media-player');

  // const audioFileUrl = "https://audio-capstone.s3.us-east-2.amazonaws.com/pool/john573_gmail.com/EnglishA1/Celpip_9_T1_11.mp3";
  // const vttFileUrl = "https://audio-capstone.s3.us-east-2.amazonaws.com/pool/john573_gmail.com/EnglishA1/Celpip_9_T1_11.srt";
  //can't load srt or vtt url, 403 error (permission problem)

  useEffect(() => {}, []);

  return (
    <div>
      <Suspense fallback={<p style={{ textAlign: 'center' }}>Loading...</p>}>
        <Await resolve={audioResources}>
          {(loadedAudioResources) => <MediaItem audioResources={loadedAudioResources} />}
        </Await>
      </Suspense>
    </div>
  );
};

export default MediaDetailPage;

const getPlayerUrlFrom = (playlist, mediaFileName) => {
  let playerUrl = baseURL + "/api/playlist/" + playlist + "/audio/" + mediaFileName;
  return playerUrl;  
}

const loadAudio = async (playlist, mediaFileName) => {
  const username = 'anna123@outlook.com';
  const password = 'audio123';
  const playerUrl = getPlayerUrlFrom(playlist, mediaFileName);
  const response = await fetch(playerUrl, {
    method: "GET",
    credentials: 'include',
    headers: {
        'Authorization': 'Basic ' + btoa(username + ':' + password),
    }
  });
  if (!response.ok) {
    throw json(
      { message: response.message },
      {
        status: 500,
      }
    );
  }
  else {
    const resData = await response.json();
    const audioResources = {
      audio: resData.audio,
      subtitle: resData.subtitle
    }
    return audioResources;
  }
};

export function loader({ request, params }) {
  const playlist = params.playlist;
  const mediaFileName = params.mediaFileName;
  return defer({
    audioResources: loadAudio(playlist, mediaFileName),
  });
}