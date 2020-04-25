import React from "react";
import Post from "./Post/Post";
import {appAPI, profileAPI} from "../../../api/api";
import Paginator from "../../common/Paginator/Paginator";
import PostForm from "./PostForm/PostForm";
import {addPostTC, deletePostTC, getPostsTC} from "../../../redux/profile-reducer";
import {connect} from "react-redux";

const Posts = ({posts, ...props}) => {
    const postsList = posts.map(u => {
        return (
            <li className="list-group-item">
                <div key={u.postId}>
                    <Post title={u.title} content={u.content} updateTime={u.updated_at}/>
                    {props.isOwner ?
                        <button type="button" class="btn btn-danger" onClick={(e) => props.onDeletePost(u.postId)}>Delete post</button>
                        : ''}
                </div>
            </li>
        );
    });

    return (
        <div>
            <ul className="list-group">
                {postsList}
            </ul>
        </div>
    );
}

class PostsContainer extends React.Component {
    componentDidMount() {
        this.setPage(1);
        // this.props.getPosts(this.props.nickname, this.props.currentPage, this.props.pageSize);
    }

    setPage = (page) => {
        this.props.getPosts(this.props.nickname, page, this.props.pageSize);
    }

    render() {
        const isOwner = this.props.currentAuthorizedUser === this.props.nickname;
        return (
            <div>
                <h5>Posts</h5>
                <Paginator totalItemsCount={this.props.totalCount} currentPage={this.props.currentPage}
                           onPageChanged={this.setPage} pageSize={this.props.pageSize} portionSize={5}/>
                <Posts posts={this.props.posts} onDeletePost={this.props.deletePost}
                       isOwner={isOwner} currentPage={this.props.currentPage} nickname={this.props.nickname}/>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    posts: state.profilePage.posts,
    nickname: state.profilePage.profile.nickname,
    currentAuthorizedUser: state.app.name,
    currentPage: state.profilePage.currentPage,
    pageSize: state.profilePage.pageSize,
    totalCount: state.profilePage.totalCount
});

const mapDispatchToProps = {
    getPosts: getPostsTC,
    addPost: addPostTC,
    deletePost: deletePostTC
};

export default connect(mapStateToProps, mapDispatchToProps)(PostsContainer);