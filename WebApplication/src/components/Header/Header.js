import React from 'react';
import {NavLink} from "react-router-dom";
import style from './Header.module.css';
import {getAuthUserDataTC, logoutTC} from "../../redux/app-reducer";
import {connect} from "react-redux";
import {appAPI, usersAPI} from "../../api/api";

const Header = (props) => {
    return (
        <nav class="navbar navbar-light bg-light" >
        <div>
        {props.isAuth ?
            <div>
                <div  className="col-md-6">
                    <NavLink className={style.profileBlock} to={'/users'}>Пользователи</NavLink>
                    <NavLink className={style.profileBlock} to={'/profile'}>Профиль</NavLink>
                    <NavLink className={style.profileBlock} to={'/followings'}>Мои подписки</NavLink>
                </div>

                <h5 className="col-md-5">{props.name}</h5>
                <button className="col-md-1 btn btn-warning" onClick={props.logout}>Logout</button>
            </div>:
                <div  className={style.loginBlock}>
                    <NavLink to={'/auth/signin'}>Sign in</NavLink>
                    <NavLink to={'/auth/signup'}>Sign up</NavLink>
                </div>
        }
</div>

        </nav>

    );
};

class HeaderContainer extends React.Component {
    componentDidMount() {
        this.props.getAuthUserData();
    }

    render() {
        return (
            <div>
                <Header {...this.props}/>
            </div>
        );
    }
}

const mapStateToProps = (state) => ({
    isAuth: state.app.isAuth,
    name: state.app.name
});

const mapDispatchToProps = {
    getAuthUserData: getAuthUserDataTC,
    logout: logoutTC
};

export default connect(mapStateToProps, mapDispatchToProps)(HeaderContainer);