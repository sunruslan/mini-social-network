import React from "react";

const Post = ({title, content, updateTime, ...props}) => {
    return (
        <div>
            <h5>{title}</h5>
            <p>{content}</p>
        </div>
    )
}

export default Post;