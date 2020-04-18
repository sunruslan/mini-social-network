import React from "react";
import {usersAPI} from "../../api/api";
import Users from "../Users/Users";
import Preloader from "../common/Preloader/Preloader";
import Paginator from "../common/Paginator/Paginator";
import {withAuthRedirect} from "../../HOCs/withAuthRedirect";

class Followings extends React.Component {
    state = {
        followings: [],
        currentPage: 1,
        isFetching: true,
        totalCount: 0
    }

    setPage = (page) => {
        this.setState({currentPage: page});
        this.getFollowings(page);
    }

    getFollowings = (page=1, count=10) => {
        usersAPI.getFollowings(page,count).then(response => {
            this.setState({
                followings: response.data.data.value,
                isFetching: false,
                totalCount: response.data.data.key
            });
        });
    }

    componentDidMount() {
        this.getFollowings(this.state.currentPage);
    }

    onUnfollow = (username) => {
        usersAPI.unfollow(username).then(response => {
            if (response.data.statusCode === 200) {
                this.getFollowings(this.state.currentPage);
            }
        })
    }

    render() {
        if (this.state.isFetching) {
            return <Preloader/>
        }
        return (
            <div>
                <Paginator portionSize={5} pageSize={10} onPageChanged={this.setPage}
                           currentPage={this.state.currentPage} totalItemsCount={this.state.totalCount} />
                <Users users={this.state.followings} unfollow={this.onUnfollow}/>
            </div>
        )
    }
}

export default withAuthRedirect('/auth/signin')(Followings);