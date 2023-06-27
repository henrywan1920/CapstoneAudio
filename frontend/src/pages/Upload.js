import React, {useState} from "react";
const Upload = () => {

    const [selectedFile, setSelectedFile] = useState(false);

    const handleFileChange = (event) => {
        const file = event.target.files[0];
        setSelectedFile(file);
    };

    const handleUpload = () => {
        if (selectedFile) {
        const formData = new FormData();
        formData.append('file', selectedFile);

        // fetch('/upload', {
        //     method: 'POST',
        //     body: formData
        // })
        //     .then(response => {
        //     // upload successfully
        //     console.log('文件上传成功');
        //     })
        //     .catch(error => {
        //     // upload failed
        //     console.error('文件上传失败:', error);
        //     });
        // }
        };
    }

    return ( 
        <>
        <h2>Please upload your audio file</h2>
            <input type="file" onChange={handleFileChange}></input><br></br>
            <button onClick={handleUpload}>Upload</button>
        </>
     );
}
 
export default Upload;