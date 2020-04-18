import React from "react";
import {NavLink} from "react-router-dom";
import defaultAva from "../../../assets/images/UserLogo.png";
import styles from './User.module.css';

const User = ({nickname, profilePicUrl, following, follow, unfollow, ...props}) => {
    return (
        <div className="col-md-12 html-editor-align-center">
            <NavLink className="col-md-1" to={'/profile/' + nickname}>
                <img  className={styles.userAvatar} src={profilePicUrl === 'string' ? defaultAva : profilePicUrl}/>
            </NavLink>
            <h4 className="col-md-4">
                {nickname}
            </h4>
            {following ? <button type="button" class="btn btn-danger" onClick={() => {
                    unfollow(nickname)
                }}>Unfollow</button> :
                <button type="button" class="btn btn-success" onClick={() => {
                    follow(nickname)
                }}>Follow</button>}
        </div>
    );
};

export default User;