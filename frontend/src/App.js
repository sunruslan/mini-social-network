import React from 'react';
import Users from './components/Users/UsersContainer';
import {Route} from "react-router-dom";
import Header from "./components/Header/Header";
import Profile from "./components/Profile/Profile";
import Login from "./components/Login/Login";

const App = (props) => {
  return (
      <div>
        <Header/>
        <Route path={'/profile/:userId?'} render={() => <Profile/>}/>
        <Route path={'/users'} render={() => <Users/>}/>
        <Route path={'/login'} render={() => <Login/>}/>
      </div>
  );
};

export default App;
