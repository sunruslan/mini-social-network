import React from 'react';
import {NavLink} from "react-router-dom";
import style from './Header.module.css';
import {getAuthUserDataTC, logoutTC} from "../../redux/app-reducer";
import {connect} from "react-redux";
import {appAPI, usersAPI} from "../../api/api";

const Header = (props) => {
    return (
        <div>
            <div className={style.loginBlock}>
                {props.isAuth ?
                    <div>
                        {props.name}
                        <button onClick={props.logout}>Logout</button>
                    </div> :
                    <div>
                        <NavLink to={'/auth/signin'}>Sign in</NavLink>
                        <NavLink to={'/auth/signup'}>Sign up</NavLink>
                    </div>
                }
            </div>
            <NavLink to={'/users'}>Пользователи</NavLink>
            <NavLink to={'/profile'}>Профиль</NavLink>
            <NavLink to={'/followings'}>Мои подписки</NavLink>
            <NavLink to={'/settings'}>Настройки</NavLink>
        </div>
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