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
        totalCount: 0,
        pageSize: 5
    }

    setPage = (page) => {
        this.setState({currentPage: page});
        this.getFollowings(page);
    }

    getFollowings = (page=1) => {
        usersAPI.getFollowings(page, this.state.pageSize).then(response => {
            this.setState({
                followings: response.data.data["_2"],
                isFetching: false,
                totalCount: response.data.data["_1"]
            });
        });
    }

    componentDidMount() {
        this.setPage(1);
        // this.getFollowings(this.state.currentPage);
    }

    onUnfollow = (username) => {
        let page = this.state.currentPage;
        if (page > Math.ceil((this.state.totalCount - 1) / this.state.pageSize)) {
            page -= 1;
        }
        usersAPI.unfollow(username).then(response => {
            if (response.data.statusCode === 200) {
                this.setPage(page);
            }
        })
    }

    render() {
        if (this.state.isFetching) {
            return <Preloader/>
        }
        return (
            <div>
                <Paginator portionSize={5} pageSize={this.state.pageSize} onPageChanged={this.setPage}
                           currentPage={this.state.currentPage} totalItemsCount={this.state.totalCount} />
                <Users users={this.state.followings} unfollow={this.onUnfollow}/>
            </div>
        )
    }
}

export default withAuthRedirect('/auth/signin')(Followings);