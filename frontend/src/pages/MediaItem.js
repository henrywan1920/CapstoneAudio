import React, { Player } from "webvtt-player";

const MediaItem = ({audioResources}) => {
    return (
        <div>
            {<Player audio={audioResources.audio} transcript={audioResources.subtitle} />}
        </div>
    );
}

export default MediaItem;