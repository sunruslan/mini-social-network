import React from 'react';
import s from './Users.module.css';
import {NavLink} from "react-router-dom";

const Users = (props) => {
    let pagesCount = Math.ceil(props.totalUsersCount / props.pageSize);
    let pages = [];
    for (let i = 1; i <= pagesCount; i++) {
        pages.push(i);
    }
    const usersList = props.users.map(u => {
        return (
            <div key={u.id}>
                <NavLink to={'/profile/' + u.id}><img className={s.userAvatar}
                     src={ u.photos.small ? u.photos.small: 'https://pngimage.net/wp-content/uploads/2018/06/user-logo-png-5.png'}/></NavLink>
                {u.name}
                {u.followed ? <button onClick={() => {
                        props.unfollow(u.id)
                    }}>Unfollow</button> :
                    <button onClick={() => {
                        props.follow(u.id)
                    }}>Follow</button>}
            </div>
        )
    });

    return (
        <div>
            {pages.map(p => <span key={p} className={p === props.currentPage ? s.isActive: undefined}
                                  onClick={() => {props.setPage(p)}}>{p} </span>)}
            {usersList}
        </div>
    );
};

export default Users;