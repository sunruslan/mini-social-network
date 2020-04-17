import React from "react";

const Post = ({title, content, updateTime, ...props}) => {
    return (
        <div>
            Title: {title}
            Content: {content}
        </div>
    )
}

export default Post;