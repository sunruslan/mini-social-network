import React from "react";
import defaultAva from '../../../assets/images/UserLogo.png';
import styles from './ProfileInfo.module.css';

const ProfileInfo = ({profile, ...props}) => {
    return (
        <div>
            <img className={styles.userAvatar}
                 src={profile.profilePicUrl === 'string' ? defaultAva : profile.profilePicUrl}/>
            <ul className={styles.ProfileInfo}>
                <li><b>Username: </b>{profile.nickname}</li>
                <li><b>About me: </b>{profile.about}</li>
                <li><b>Location: </b>{profile.location}</li>
                <li><b>Gender: </b>{profile.gender ? 'Male' : 'Female'}</li>
            </ul>
        </div>
    );
}

export default ProfileInfo;