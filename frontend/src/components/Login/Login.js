import React from "react";
import {Field, reduxForm} from "redux-form";
import {loginTC} from "../../redux/app-reducer";
import {connect} from "react-redux";
import {Redirect} from "react-router-dom";
import {reqiuredField} from "../../utils/validators/validators";
import {Input} from "../common/FormsControls/FormsControls";

const LoginForm = (props) => {

    return (
        <div>
            <form onSubmit={props.handleSubmit}>
                <div>
                    <Field component={Input} name={'email'} placeholder={'Email'} validate={[reqiuredField]}/>
                </div>
                <div>
                    <Field component={Input} name={'password'} placeholder={'Password'} type={'password'} validate={[reqiuredField]}/>
                </div>
                <div>
                    {props.error}
                </div>
                <div>
                    <button>Login</button>
                </div>
            </form>
        </div>
    );
};

const LoginReduxForm = reduxForm({form: 'login'})(LoginForm);

const Login = (props) => {

    const onSubmit = (formData) => {
        props.login(formData.email, formData.password);
    };

    if (props.isAuth) {
        return <Redirect to={'/profile'}/>;
    }

    return (
        <div>
            <h1>Login</h1>
            <LoginReduxForm onSubmit={onSubmit}/>
        </div>
    );
};

const mapStateToProps = (state) => ({
    isAuth: state.app.isAuth
});

const mapDispatchToProps = {
    login: loginTC
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);