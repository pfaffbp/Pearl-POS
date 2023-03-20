import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import CreateUserPage from './CreateUserPage';
import UserAccountPage from './UserAccountPage';
import UpdateUserPage from './UpdateUserPage';

const App = () => {
    return (
        <Router>
            <Switch>
                <Route exact path="/create-user" component={CreateUserPage} />
                <Route exact path="/user-account" component={UserAccountPage} />
                <Route exact path="/update-user" component={UpdateUserPage} />
            </Switch>
        </Router>
    );
};

ReactDOM.render(<App />, document.getElementById('root'));
