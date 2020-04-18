import React from "react";
import {Field, reduxForm} from "redux-form";
import {loginTC} from "../../../redux/app-reducer";
import {connect} from "react-redux";
import {reqiuredField} from "../../../utils/validators/validators";
import {Input} from "../../common/FormsControls/FormsControls";
import {withAuthRedirect} from "../../../HOCs/withAuthRedirect";

const LoginForm = (props) => {
    return (
        <form className="px-4 py-3" onSubmit={props.handleSubmit}>
            <div className="form-row align-items-center">
                <div className="col-sm-3 my-1">
                <Field component={Input} className="form-control" placeholder="Nickname" name={'nickname'}  validate={[reqiuredField]}/>
            </div>
                <div className="col-sm-3 my-1">
                    <Field component={Input} className="form-control" placeholder="Password" name={'password'} type={'password'} validate={[reqiuredField]}/>
                </div>
            <div className="col-auto my-1">
                <button type="submit" className="btn btn-primary">Sign In</button>
            </div>
            </div>
        </form>
    );
};

const LoginReduxForm = reduxForm({form: 'signIn'})(LoginForm);

const SignIn = (props) => {

    const onSubmit = (formData) => {
        props.login(formData.nickname, formData.password);
    };

    return (
        <div>
            <LoginReduxForm onSubmit={onSubmit}/>
        </div>
    );
};

const mapDispatchToProps = {
    login: loginTC
};

export default withAuthRedirect('/profile', true)(connect(null, mapDispatchToProps)(SignIn));