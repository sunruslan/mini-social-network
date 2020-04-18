import React from 'react';
import Users from './components/Users/UsersContainer';
import {Route} from "react-router-dom";
import Header from "./components/Header/Header";
import Profile from "./components/Profile/Profile";
import Auth from "./components/Auth/Auth";
import Followings from "./components/Followings/Followings";

const App = (props) => {
  return (
      <div class="container">
        <Header/>
        <Route path={'/profile/:username?'} render={() => <Profile/>}/>
        <Route path={'/users'} render={() => <Users/>}/>
        <Route path={'/auth/:authMode'} render={() => <Auth/>}/>
        <Route path={'/followings'} render={() => <Followings/>}/>
      </div>
  );
};

export default App;
