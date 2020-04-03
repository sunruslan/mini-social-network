import React from "react";
import {compose} from "redux";
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";
import {getProfileTC} from "../../redux/profile-reducer";
import Preloader from "../common/Preloader/Preloader";
import ProfileInfo from "./ProfileInfo/ProfileInfo";
import Posts from "./Posts/Posts";

const Profile = (props) => {
    return (
        <div>
            <ProfileInfo profile={props.profile} />
            <Posts />
        </div>
    );
};

class ProfileContainer extends React.Component {
    refreshProfile = (username) => {
        if (!username) {
            username = this.props.currentAuthorizedUser;
            if (!username) {
                this.props.history.push('/auth/signin');
            }
        }
        this.props.getUserProfile(username);
    }

    componentDidMount() {
        let username = this.props.match.params.username;
        this.refreshProfile(username);
    }

    componentDidUpdate(prevProps) {
        let username = this.props.match.params.username;
        if (username !== prevProps.match.params.username || this.props.currentAuthorizedUser !== prevProps.currentAuthorizedUser) {
            this.refreshProfile(username);
        }
    }

    render() {
        if (this.props.isFetching) {
            return <Preloader/>;
        }
        return (
            <div>
                <Profile profile={this.props.profile}/>
            </div>
        );
    }
}

const mapStateToProps = (state) => ({
    profile: state.profilePage.profile,
    currentAuthorizedUser: state.app.name,
    isFetching: state.profilePage.isFetching
});

const mapDispatchToProps = {
    getUserProfile: getProfileTC
};

export default compose(connect(mapStateToProps, mapDispatchToProps), withRouter)(ProfileContainer);