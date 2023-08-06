import { Link } from 'react-router-dom';

// class AudiosList extends Component {
//   constructor(props) {
//     super(props);
//     this.audios = props.audios;
//   }

//   render() {
//     return (
//       <div>
//         {Object.keys(this.audios).map((playlist) => (
//           <div className="dashboard" key={playlist}>
//             <h2>{playlist}</h2>
//             <ul>
//               {this.audios[playlist].map((audio) => 
//                 <li key={audio}>{audio}</li>
//               )}
//             </ul>
//           </div>
//         ))}
//       </div>
//     );
//   }
// }

const AudiosList = (props) => {
  const audios = props.audios;
  console.log(audios);
  return (
    <div>
      {Object.keys(audios).map((playlist) => (
        <div className="dashboard" key={playlist}>
          <h2>{playlist}</h2>
          <ul>
            {audios[playlist].map((audio) => 
              <li key={audio}>
                <Link to={`${playlist}/${audio}`}>{audio}</Link>
              </li>
            )}
          </ul>
        </div>
      ))}
    </div>
  );
}

export default AudiosList;