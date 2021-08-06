import React, { Component } from 'react';
import { Redirect } from "react-router-dom";

export default class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            nombre: '',
            correo: '',
            username: '',
            password: '',
            isRegistered: false,
        };
    }

    handleSubmit = async event => {
      event.preventDefault();
      let response = await fetch(process.env.REACT_APP_REGISTER_URL, {
        method: 'POST',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/json',
          Accept: 'application/json',
        },
        body: JSON.stringify({
          nombre: this.state.nombre,
          correo: this.state.correo,
          username: this.state.username,
          password: this.state.password,
        }),
      });

      if (response.status === 200) {
        alert('Cuenta creada de forma exitosa');
        this.setState({
          isRegistered: true,
        });
      } else {
        response = await response.json();
        alert(response.message);
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
            <h2>Registro de usuario</h2>
            <label>
              Nombre
            </label>
            <input
              name="nombre"
              type="text"
              maxLength="100"
              placeholder="Ingrese nombre"
              value={this.state.nombre}
              onChange={this.handleChange}
              required
            />
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
              Username
            </label>
            <input
              name="username"
              type="text"
              maxLength="10"
              placeholder="Ingrese username"
              value={this.state.username}
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
            <button className="btn-blue" type="submit">Registrar</button>
            {
              !!this.state.isRegistered && <Redirect to="/login" />
            }
          </form>
        );
    }
}
