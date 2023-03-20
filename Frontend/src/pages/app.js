import React from 'react';
import ReactDOM from 'react-dom';
import UserAccountPage from './UserAccountPage';

const React = require('react');
const ReactDOM = require('react-dom');
const UserAccountPage = require('./UserAccountPage');

const main = () => {
    ReactDOM.render(<UserAccountPage />, document.getElementById('root'));
};

window.addEventListener('DOMContentLoaded', main);

module.exports = main;