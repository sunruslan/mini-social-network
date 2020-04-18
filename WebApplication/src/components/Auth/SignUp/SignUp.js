import React from "react";
import {Input, Textarea} from "../../common/FormsControls/FormsControls";
import {Field, reduxForm} from "redux-form";
import {signUpTC} from "../../../redux/app-reducer";
import {connect} from "react-redux";
import {withAuthRedirect} from "../../../HOCs/withAuthRedirect";
import {reqiuredField} from "../../../utils/validators/validators";

const SignUpForm = (props) => {
    return (
        <form onSubmit={props.handleSubmit}>
            <Field class="form-group" component={Input} name={'nickname'} placeholder={'Nickname'} validate={[reqiuredField]}/>
            <Field class="form-group" component={Input} name={'password'} placeholder={'Password'} type={'password'} validate={[reqiuredField]}/>
            <Field class="form-group" component={Textarea} name={'about'} placeholder={'About Me'}/>
            <Field class="form-group" component={Input} name={'location'} placeholder={'Location'}/>
            <Field class="form-group" component={Input} name={'profilePicUrl'} placeholder={'Avatar URL'}/>
            <Field class="form-group" component={Input} name={'gender'} type={'radio'} value={'M'} text={'Male'}/>
            <Field class="form-group" component={Input} name={'gender'} type={'radio'} value={'F'} text={'Female'}/>
            <button  type="button" class="btn btn-primary">Sign up</button>
        </form>
    );
};

const SignUpReduxForm = reduxForm({form: 'signUp'})(SignUpForm);

const SignUp = (props) => {
    const onSubmit = (formData) => {
        formData.gender = formData.gender === 'M';
        props.signUp(formData);
    }

    return (
        <div>
            <h1>Sign up</h1>
            <SignUpReduxForm onSubmit={onSubmit}/>
        </div>
    )
}

const mapDispatchToProps = {
    signUp: signUpTC
};

export default withAuthRedirect('/profile', true)(connect(null, mapDispatchToProps)(SignUp));