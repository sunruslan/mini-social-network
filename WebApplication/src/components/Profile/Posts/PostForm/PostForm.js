import React from "react";
import {Field, reduxForm} from "redux-form";
import {Input, Textarea} from "../../../common/FormsControls/FormsControls";
import {addPostTC} from "../../../../redux/profile-reducer";
import {connect} from "react-redux";
import {reqiuredField} from "../../../../utils/validators/validators";

const Form = (props) => {
    return (
        <div>
            <form onSubmit={props.handleSubmit}>
                <Field component={Input} name={'title'} placeholder={'Post title'} validate={[reqiuredField]}/>
                <Field component={Textarea} name={'content'} placeholder={'Post content'} validate={[reqiuredField]}/>
                <button>Add post</button>
            </form>
        </div>
    );
}

const PostReduxForm = reduxForm({form: 'addPost'})(Form);

const PostForm = (props) => {
    const onSubmit = (formData) => {
        props.addPost(props.nickname, formData.title, formData.content);
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