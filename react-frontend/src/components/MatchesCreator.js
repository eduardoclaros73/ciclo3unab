import React, { Component } from 'react';

export default class MatchesCreator extends Component {
  constructor(props) {
    super(props);
    this.state = {
      local: '-1',
      visitante: '-1',
      fecha: '',
      golesLocal: '',
      golesVisitante: '',
    };
  }

  handleChange = event => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  }

  handleCreateClick = async _ => {
    const { handlers } = this.props;

    if (this.state.local === '-1' || this.state.visitante === '-1') {
      alert('Por favor seleccione los equipos local y visitante');
      return;
    }

    if (this.state.local === this.state.visitante) {
      alert('El equipo local y visitante deben ser diferentes');
      return;
    }

    if (!this.state.golesLocal || !this.state.golesVisitante) {
      alert('Por favor ingrese el marcado de cada equipo');
      return;
    }

    if (this.state.golesLocal < 0 || this.state.golesVisitante < 0) {
      alert('Los marcadores deben ser numeros positivos');
      return;
    }

    if (!this.state.fecha) {
      alert('Por favor seleccione la fecha del encuentro');
      return;
    }
  
    if (await handlers.handleCreateClick(this.state))
      this.setState({
        local: '-1',
        visitante: '-1',
        fecha: '',
        golesLocal: '',
        golesVisitante: '',
      });
  }

  render() {
    const { equipos } = this.props.data;

    return (
      <tr>
        <td>
          <select
            name="local"
            value={this.state.local}
            onChange={this.handleChange}
          >
            <option value="-1">Seleccione un equipo</option>
            { equipos.map(equipo => <option key={equipo.id} value={equipo.id}>{equipo.nombre}</option>) }
          </select>
        </td>
        <td>
          <select
            name="visitante"
            value={this.state.visitante}
            onChange={this.handleChange}
          >
            <option value="-1">Seleccione un equipo</option>
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
          />
        </td>
        <td>
          <input
            type="number"
            name="golesVisitante"
            placeholder="Ingrese goles visitante"
            value={this.state.golesVisitante}
            onChange={this.handleChange}
          />
        </td>
        <td>
          <input
            type="date"
            name="fecha"
            placeholder="Ingrese fecha"
            value={this.state.fecha}
            onChange={this.handleChange}
          />
        </td>
        <td>
          <button
            className="btn-blue"
            onClick={this.handleCreateClick}
          >
            Crear
          </button>
        </td>
      </tr>
    );
  }
}
