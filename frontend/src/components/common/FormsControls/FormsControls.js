import React from "react";
import style from './FormsControls.module.css';

// export const withControlled = (Component) => {
//     debugger;
//     const Wrapped = ({input, meta, ...props}) => {
//         const isError = meta.touched && meta.error;
//         return (
//             <div className={ isError ? style.error: undefined}>
//                 <Component {...input} {...props}/>
//                 {isError && <span>{meta.error}</span>}
//             </div>
//         );
//     };
//     return Wrapped;
// };

export const Input = ({input, meta, ...props}) => {
    const isError = meta.touched && meta.error;
    return (
        <div className={isError ? style.error: undefined}>
            <input {...input} {...props}/>
            {isError && <span>{meta.error}</span>}
        </div>
    );
};