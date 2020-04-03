import React from "react";
import {NavLink} from "react-router-dom";
import defaultAva from "../../../assets/images/UserLogo.png";
import styles from './User.module.css';

const User = ({nickname, profilePicUrl, following, follow, unfollow, ...props}) => {
    return (
        <div>
            <NavLink to={'/profile/' + nickname}>
                <img className={styles.userAvatar} src={profilePicUrl === 'string' ? defaultAva : profilePicUrl}/>
            </NavLink>
            {nickname}
            {following ? <button onClick={() => {
                    unfollow(nickname)
                }}>Unfollow</button> :
                <button onClick={() => {
                    follow(nickname)
                }}>Follow</button>}
        </div>
    );
};

export default User;