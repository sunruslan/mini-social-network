import React from "react";
import SignIn from './SignIn/SignIn';
import SignUp from "./SignUp/SignUp";
import {withRouter} from "react-router-dom";

const Auth = (props) => {
    switch (props.match.params.authMode) {
        case 'signin':
            return <SignIn/>;
        case 'signup':
            return <SignUp/>;
        default:
            return;
    }
};

export default withRouter(Auth);