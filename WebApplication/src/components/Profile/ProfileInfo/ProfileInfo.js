import React from "react";
import defaultAva from '../../../assets/images/UserLogo.png';
import styles from './ProfileInfo.module.css';

const ProfileInfo = ({profile, ...props}) => {
    return (
        <div>
            <img className={styles.userAvatar} src={profile.profilePicUrl === 'string' ? defaultAva: profile.profilePicUrl}/>
            <b>Username: </b>{profile.nickname}
            <b>About me: </b>{profile.about}
            <b>Location: </b>{profile.location}
            <b>Gender: </b>{profile.gender ? 'Male' : 'Female'}
        </div>
    );
}

export default ProfileInfo;