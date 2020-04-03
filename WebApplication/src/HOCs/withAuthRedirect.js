import React from "react";
import {Redirect} from "react-router-dom";
import {connect} from "react-redux";

const mapStateToProps = (state) => ({
    isAuth: state.app.isAuth
})

export const withAuthRedirect = (redirectPath, isAuthRequired=false) => (Component) => {
    class RedirectComponent extends React.Component {
        render() {
            let isAuth = this.props.isAuth;
            if (!isAuthRequired) {
                isAuth = !isAuth;
            }
            if (isAuth) {
                return <Redirect to={redirectPath}/>
            }
            return <Component {...this.props}/>
        }
    }
    return connect(mapStateToProps, null)(RedirectComponent);
};


