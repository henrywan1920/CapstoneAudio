import { json, defer, useLoaderData, Await, redirect } from "react-router-dom";
import React, { Suspense } from 'react';
import AudiosList from "./AudiosList";
import getCredential from "../tools/storage";

const baseURL = "http://localhost:5000";
// const baseURL = "http://audio-transcribe-services.us-east-2.elasticbeanstalk.com";
const playlistURL = baseURL + "/api/audios";

const Audio = () => {
  const { audios } = useLoaderData();

  return (
    <div>
      <Suspense fallback={<p style={{ textAlign: 'center' }}>Loading...</p>}>
        <Await resolve={audios}>
          {(loadedAudios) => <AudiosList audios={loadedAudios} />}
        </Await>
      </Suspense>
    </div>
  );
};

export default Audio;

const loadAudios = async () => {
  const credential = getCredential();
  if (!credential || !credential.username || !credential.password) {
    return redirect("/");
  }
  const username = credential.username;
  const password = credential.password;
  const response = await fetch(playlistURL, {
    method: 'GET',
    credentials: 'include',
    headers: {
      'Authorization': 'Basic ' + btoa(username + ':' + password)
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
    return resData.audios;
  }
}

export function loader() {
  return defer({
    audios: loadAudios(),
  });
}