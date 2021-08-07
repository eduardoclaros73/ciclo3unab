import React, { Component, Fragment } from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import Nav from './components/Nav';
import Login from './components/Login';
import Logout from './components/Logout';
import Register from './components/Register';
import MatchesTable from './components/MatchesTable';

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      token: null,
      equipos: [],
      partidosDict: {},
      partidos: [],
      isViewMoreActive: false,
    };
  }

  componentDidMount() {
    const token = localStorage.getItem('token');
    if (!!token) this.handleLogin(token);
  }

  componentDidUpdate = (_, prevState) => {
    if (prevState.token !== this.state.token && this.state.token)
      this.fetchData();
  }

  handleLogin = token => {
    localStorage.setItem('token', token);
    this.setState({
      token,
    });
  }

  handleLogout = _ => {
    localStorage.removeItem('token');
    this.setState({
      token: null,
    });
  }

  _fetchEquipos = _ => {
    return fetch(process.env.REACT_APP_FETCH_EQUIPOS_URL, {
      method: 'GET',
      mode: 'cors',
      headers: {
        Accept: 'application/json',
        Authorization: this.state.token,
      },
    });
  }

  _fetchPartidos = _ => {
    return fetch(process.env.REACT_APP_FETCH_PARTIDOS_URL + this.state.partidos.length, {
      method: 'GET',
      mode: 'cors',
      headers: {
        Accept: 'application/json',
        Authorization: this.state.token,
      },
    });
  }

  fetchData = async _ => {
    const response = await Promise.allSettled([
      this._fetchEquipos(),
      this._fetchPartidos(),
    ]);

    let partidos = await response[1].value.json()
    const partidosDict = partidos.reduce((prev, curr) => {
      prev[curr.id] = curr;
      return prev;
    }, {});
    partidos = Object.values(partidosDict);

    this.setState({
      equipos: await response[0].value.json(),
      partidosDict,
      partidos,
      isViewMoreActive: partidos.length > 0,
    })
  }

  handleViewMore = async _ => {
    const response = await this._fetchPartidos();
    const partidos = await response.json();

    const partidosDict = partidos.reduce((prev, curr) => {
      prev[curr.id] = curr;
      return prev;
    }, this.state.partidosDict);

    this.setState({
      partidosDict,
      partidos: Object.values(partidosDict),
      isViewMoreActive: partidos.length > 0,
    })
  }

  handleCreate = async record => {
    let response = await fetch(process.env.REACT_APP_CREATE_PARTIDOS_URL, {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
        Accept: 'application/json',
        Authorization: this.state.token,
      },
      body: JSON.stringify({
        ...record,
      }),
    });

    if (response.status === 200) {
      const partido = await response.json();
      const partidosDict = this.state.partidos;
      partidosDict[partido.id] = partido;

      await this.setState({
        partidosDict,
        partidos: Object.values(partidosDict),
      });

      return true;
    } else {
      response = await response.json();
      alert(response.message);
      return false;
    }
  }

  handleSave = async record => {
    const response = await fetch(process.env.REACT_APP_UPDATE_PARTIDOS_URL + record.id, {
      method: 'PUT',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
        Accept: 'application/json',
        Authorization: this.state.token,
      },
      body: JSON.stringify({
        local: record.local,
        visitante: record.visitante,
        fecha: record.fecha,
        golesLocal: record.golesLocal,
        golesVisitante: record.golesVisitante,
      }),
    });

    const partido = await response.json();
    const partidosDict = this.state.partidos;
    partidosDict[partido.id] = partido;

    this.setState({
      partidosDict,
      partidos: Object.values(partidosDict),
    });
  }

  render() {
    return (
      <Router>
        <div>
          <header>
            <h1>Ciclo Tres UNAB - Partidos de Futbol</h1>
            <Nav isLoggedIn={!!this.state.token} />
          </header>
          <main>
            <Switch>
              {
                !this.state.token && (
                  <Fragment>
                    <Route path="/login">
                      <Login handleLogin={this.handleLogin} />
                    </Route>
                    <Route path="/register">
                      <Register />
                    </Route>
                    <Redirect to="/login" />
                  </Fragment>
                )
              }
              {
                this.state.token && (
                  <Fragment>
                    <Route path="/partidos">
                      <MatchesTable
                        data={{
                          isViewMoreActive: this.state.isViewMoreActive,
                          equipos: this.state.equipos,
                          partidos: this.state.partidos,
                        }}
                        handlers={{
                          handleViewMoreClick: this.handleViewMore,
                          handleCreateClick: this.handleCreate,
                          handleSaveClick: this.handleSave,
                        }}
                      />
                    </Route>
                    <Route path="/logout">
                      <Logout handleLogout={this.handleLogout} />
                    </Route>
                    <Redirect to="/partidos" />
                  </Fragment>
                )
              }
            </Switch>
          </main>
        </div>
      </Router>
    );
  }
}
