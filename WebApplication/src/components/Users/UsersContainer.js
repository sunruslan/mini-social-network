import React from 'react';
import {connect} from "react-redux";
import {
    followTC,
    getUsersTC,
    setPageAC, unfollowTC
} from "../../redux/users-reducer";
import Users from "./Users";
import Preloader from "../common/Preloader/Preloader";
import {withAuthRedirect} from "../../HOCs/withAuthRedirect";

export class CUsers extends React.Component {

    componentDidMount() {
        this.props.setPage(this.props.currentPage);
        this.props.getUsers(this.props.currentPage, this.props.pageSize);
    }

    onPageChanged = (page) => {
        this.props.setPage(page);
        this.props.getUsers(page, this.props.pageSize);
    }

    render = () => {
        return (
            <div>
                {this.props.isFetching ? <Preloader/> :
                    <div>
                        <Users totalUsersCount={this.props.totalUsersCount} pageSize={this.props.pageSize}
                               users={this.props.users} follow={this.props.follow} unfollow={this.props.unfollow}
                               setPage={this.onPageChanged} currentPage={this.props.currentPage}/>
                    </div>
                }
            </div>
        )
    }
}

let mapStateToProps = (state) => {
    return {
        users: state.usersPage.users,
        pageSize: state.usersPage.pageSize,
        totalUsersCount: state.usersPage.totalUsersCount,
        currentPage: state.usersPage.currentPage,
        isFetching: state.usersPage.isFetching
    };
};

let mapDispatchToProps = {
    follow: followTC,
    unfollow: unfollowTC,
    setPage: setPageAC,
    getUsers: getUsersTC
};

export default withAuthRedirect('/auth/signin')(connect(mapStateToProps, mapDispatchToProps)(CUsers));