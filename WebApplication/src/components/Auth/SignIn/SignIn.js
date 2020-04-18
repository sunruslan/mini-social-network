import React from "react";
import {Field, reduxForm} from "redux-form";
import {loginTC} from "../../../redux/app-reducer";
import {connect} from "react-redux";
import {reqiuredField} from "../../../utils/validators/validators";
import {Input} from "../../common/FormsControls/FormsControls";
import {withAuthRedirect} from "../../../HOCs/withAuthRedirect";

const LoginForm = (props) => {
    return (
        <form onSubmit={props.handleSubmit}>
            <div>
                <Field component={Input} name={'nickname'} placeholder={'Nickname'} validate={[reqiuredField]}/>
            </div>
            <div>
                <Field component={Input} name={'password'} placeholder={'Password'} type={'password'} validate={[reqiuredField]}/>
            </div>
            <div>
                {props.error}
            </div>
            <div>
                <button className={'btn btn-primary'}>Login</button>
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
            <h1>Login</h1>
            <LoginReduxForm onSubmit={onSubmit}/>
        </div>
    );
};

const mapDispatchToProps = {
    login: loginTC
};

export default withAuthRedirect('/profile', true)(connect(null, mapDispatchToProps)(SignIn));