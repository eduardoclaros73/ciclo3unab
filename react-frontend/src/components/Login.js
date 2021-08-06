import React, { Component } from 'react';

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            correo: '',
            password: '',
        };
    }

    handleSubmit = async event => {
      event.preventDefault();
      const response = await fetch(process.env.REACT_APP_LOGIN_URL, {
        method: 'POST',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/json',
          Accept: 'application/json',
        },
        body: JSON.stringify({
            correo: this.state.correo,
            password: this.state.password,
        }),
      });

      if (response.status === 200) {
        this.props.handleLogin(response.headers.get('Authorization'));
      } else {
        alert('La combinacion de correo y password no es correcta');
      }
    }
  
    handleChange = event => {
      this.setState({
        [event.target.name]: event.target.value,
      });
    }

    render() {
        return (
          <form onSubmit={this.handleSubmit}>
            <h2>Inicio de sesion</h2>
            <label>
              Correo
            </label>
            <input
              name="correo"
              type="email"
              maxLength="50"
              placeholder="Ingrese correo"
              value={this.state.correo}
              onChange={this.handleChange}
              required
            />
            <label>
              Password
            </label>
            <input
              name="password"
              type="password"
              maxLength="10"
              placeholder="Ingrese password"
              value={this.state.password}
              onChange={this.handleChange}
              required
            />
            <button className="btn-blue" type="submit">Iniciar sesion</button>
          </form>
        );
    }
}
