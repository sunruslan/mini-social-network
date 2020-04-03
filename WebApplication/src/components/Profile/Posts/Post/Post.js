import React from "react";

const Post = ({title, content, updateTime, ...props}) => {
    return (
        <div>
            {updateTime}
            {title}
            {content}
        </div>
    )
}

export default Post;