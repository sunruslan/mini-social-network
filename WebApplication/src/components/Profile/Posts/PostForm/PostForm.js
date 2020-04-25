import React from "react";
import {Field, reduxForm} from "redux-form";
import {Input, Textarea} from "../../../common/FormsControls/FormsControls";
import {addPostTC} from "../../../../redux/profile-reducer";
import {connect} from "react-redux";
import {checkSize, reqiuredField} from "../../../../utils/validators/validators";
import style from "./PostForm.module.css";

const Form = (props) => {
    return (
        <form onSubmit={props.handleSubmit}>
            <div className="form-group">
                    <Field className={style.FieldPost + " " + "form-control"} component={Input} name={'title'} placeholder={'Post title'} validate={[reqiuredField]}/>
                    <Field className={style.FieldPost + " " + "form-control"} component={Textarea} name={'content'} placeholder={'Post content'} validate={[reqiuredField]}/>
                    <button className="btn btn-success" >Add post</button>
            </div>
        </form>
    );
}

const PostReduxForm = reduxForm({form: 'addPost'})(Form);

const PostForm = (props) => {
    const onSubmit = (formData) => {
        props.addPost(formData.title, formData.content);
    }

    return (
        <div>
            <PostReduxForm onSubmit={onSubmit}/>
        </div>
    );
}

const mapDispatchToProps = ({
    addPost: addPostTC
});

export default connect(null, mapDispatchToProps)(PostForm);