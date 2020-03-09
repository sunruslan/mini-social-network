import React from "react";
import {compose} from "redux";
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";
import {getProfileTC} from "../../redux/profile-reducer";
import Preloader from "../common/Preloader/Preloader";

const Profile = (props) => {
    return (
        <div>
            <h1>{props.profile.fullName}</h1>
            <img src={props.profile.photos.large} />
        </div>
    );
};

class ProfileContainer extends React.Component {
    componentDidMount() {
        let userId = this.props.match.params.userId;
        if (!userId && this.props.currentUserId) {
            userId = this.props.currentUserId;
        }
        this.props.getUserProfile(userId);
    }

    render() {
        if (!this.props.profile) {
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
    currentUserId: state.app.userId
});

const mapDispatchToProps = {
    getUserProfile: getProfileTC
};

const RouterProfileContainer = compose(connect(mapStateToProps, mapDispatchToProps), withRouter)(ProfileContainer);

export default RouterProfileContainer;