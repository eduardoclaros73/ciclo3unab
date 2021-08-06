import React, { Component, Fragment } from 'react';

export default class MatchesRecord extends Component {
  constructor(props) {
    super(props);
    this.state = {
      ...props.data.partido,
      isEditable: false,
    };
  }

  handleChange = event => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  }

  handleEditClick = _ => {
    this.setState({
      isEditable: true,
    });
  }

  handleSaveClick = _ => {
    this.setState({
      isEditable: false,
    });
    
    this.props.handlers.handleSaveClick({
      id: this.state.id,
      local: this.state.local,
      visitante: this.state.visitante,
      fecha: this.state.fecha,
      golesLocal: this.state.golesLocal,
      golesVisitante: this.state.golesVisitante,
    });
  }

  handleCancelClick = _ => {
    this.setState({
      ...this.props.data.partido,
      isEditable: false,
    });
  }

  handleChange = event => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  }

  _formatDate = date => {
    return (
      date.getUTCDate().toString().padStart(2, '0') +
      '-' +
      (date.getUTCMonth() + 1).toString().padStart(2, '0') +
      '-' +
      date.getUTCFullYear()
    );
  }

  render() {
    const { equipos } = this.props.data;
    const now = new Date(new Date().toDateString());
    const date = new Date(this.state.fecha);

    return (
      <tr>
        <td>
          <select defaultValue={this.state.local} disabled>
            { equipos.map(equipo => <option key={equipo.id} value={equipo.id}>{equipo.nombre}</option>) }
          </select>
        </td>
        <td>
          <select defaultValue={this.state.visitante} disabled>
            { equipos.map(equipo => <option key={equipo.id} value={equipo.id}>{equipo.nombre}</option>) }
          </select>
        </td>
        <td>
          <input
            type="number"
            name="golesLocal"
            placeholder="Ingrese goles local"
            value={this.state.golesLocal}
            onChange={this.handleChange}
            disabled={!this.state.isEditable}
          />
        </td>
        <td>
          <input
            type="number"
            name="golesVisitante"
            placeholder="Ingrese goles visitante"
            value={this.state.golesVisitante}
            onChange={this.handleChange}
            disabled={!this.state.isEditable}
          />
        </td>
        <td>{this._formatDate(date)}</td>
        <td>
          {
            !this.state.isEditable && (+date + 86400000) >= +now && (
              <button
                className="btn-orange"
                onClick={this.handleEditClick}
              >
                Editar
              </button>
            )
          }
          { !this.state.isEditable && (+date + 86400000) < +now && <p>Ninguna</p> }
          {
            this.state.isEditable && (
              <Fragment>
                <button
                  className="btn-blue"
                  onClick={this.handleSaveClick}
                >
                  Guardar
                </button>
                <button
                  className="btn-red"
                  onClick={this.handleCancelClick}
                >
                  Cancelar
                </button>
              </Fragment>
            )
          }
        </td>
      </tr>
    );
  }
}
