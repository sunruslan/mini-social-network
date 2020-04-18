import React from "react";
import style from './FormsControls.module.css';

export const Input = ({input, meta, ...props}) => {
    const isError = meta.touched && meta.error;
    return (
        <div className={isError ? style.error: undefined}>
            <input {...input} {...props}/>
            {props.text}
        </div>
    );
};

export const Textarea = ({input, meta, ...props}) => {
    const isError = meta.touched && meta.error;
    return (
        <div className={isError ? style.error: undefined}>
            <textarea {...input} {...props}/>
            {props.text}
        </div>
    );
};