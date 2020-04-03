import React from 'react';
import Paginator from "../common/Paginator/Paginator";
import User from "./User/User";

const Users = (props) => {
    const usersList = props.users.map(u => {
        return (
            <div key={u.nickname}>
                <User {...u} follow={props.follow} unfollow={props.unfollow} />
            </div>
        )
    });

    return (
        <div>
            <Paginator totalItemsCount={props.totalUsersCount} currentPage={props.currentPage}
                       onPageChanged={props.setPage} pageSize={props.pageSize} portionSize={10} />

            {usersList}
        </div>
    );
};

export default Users;