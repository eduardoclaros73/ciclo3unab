import { Component } from 'react';

export default class Logout extends Component {
    componentDidMount() {
        this.props.handleLogout();
    }

    render() {
        return null;
    }
}
