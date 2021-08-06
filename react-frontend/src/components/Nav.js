import React, { Fragment } from 'react';
import { Link } from "react-router-dom";

function Nav(props) {
    return (
        <nav>
        <ul>
          {
            !props.isLoggedIn && (
              <Fragment>
                <li>
                  <Link to="/login">Inicio de sesion</Link>
                </li>
                <li>
                  <Link to="/register">Registro</Link>
                </li>
              </Fragment>
            )
          }
          {
            props.isLoggedIn && (
              <Fragment>
                <li>
                  <Link to="/logout">Cerrar sesion</Link>
                </li>
              </Fragment>
            )
          }
        </ul>
      </nav>
    );
}

export default Nav;
