import React from "react";
import {Input, Textarea} from "../../common/FormsControls/FormsControls";
import {Field, reduxForm} from "redux-form";
import {signUpTC} from "../../../redux/app-reducer";
import {connect} from "react-redux";
import {withAuthRedirect} from "../../../HOCs/withAuthRedirect";
import {reqiuredField, checkSize} from "../../../utils/validators/validators";

const SignUpForm = (props) => {
    return (
        <form className="col-md-12"  onSubmit={props.handleSubmit}>
            <div className="form-row m-0 p-0">

    <div className="form-group col-md-6">
        <label>User nickname</label>
        <Field className="form-control" component={Input} name={'nickname'} validate={[reqiuredField, checkSize]}/>
    </div>
    <div className="form-group  col-md-6">
        <label>Password</label>
        <Field className="form-control" component={Input} name={'password'}
               type={'password'} validate={[reqiuredField,checkSize]}/>
    </div>
            </div>
            <div className="form-row">

                <div className="form-group col-md-6">
                    <label>User location</label>
                    <Field className="form-control" component={Input} name={'location'}/>
                </div>


                <div className="form-group col-md-6">
                    <label>Profile avatar picture</label>
                    <Field className="form-control" component={Input} name={'profilePicUrl'} />
                </div>
            </div>

            <div className="form-group col-md-12">
                    <label>Personal information</label>
                    <Field className="form-control" component={Textarea} name={'about'}/>
            </div>
            <div className="form-row col-md-12">
                <label className="col-sm-1 my-1">Gender</label>
                <div className="col-sm-2 my-1">
                    <Field className="form-check form-check-inline" component={Input} name={'gender'} type={'radio'} value={'F'}
                           text={'Female'}/>
                </div>
                <div className="col-sm-2 my-1">
                    <Field className="form-check form-check-inline" component={Input} name={'gender'} type={'radio'} value={'M'}
                           text={'Male'}/>
                </div>


            </div>
            <div className="col-md-12">

                <button  type="submit" className="btn btn-primary">Sign up</button>
            </div>
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
            <SignUpReduxForm onSubmit={onSubmit}/>
        </div>
    )
}

const mapDispatchToProps = {
    signUp: signUpTC
};

export default withAuthRedirect('/profile', true)(connect(null, mapDispatchToProps)(SignUp));