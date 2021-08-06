import React from 'react';
import MatchesRecord from './MatchesRecord';
import MatchesCreator from "./MatchesCreator";

function MatchesTable(props) {
  const { equipos, partidos, isViewMoreActive } = props.data;
  const { handleViewMoreClick, handleCreateClick, handleSaveClick } = props.handlers;
  
  return (
    <table>
      <thead>
        <tr>
          <th>Local</th>
          <th>Visitante</th>
          <th>Goles local</th>
          <th>Goles visitante</th>
          <th>Fecha</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        {
          partidos.length > 0 ? (
            partidos.map(partido => (
              <MatchesRecord
                key={partido.id}
                data={{
                  partido,
                  equipos,
                }}
                handlers={{
                  handleSaveClick,
                }}
              />
            ))
          ) : (
            <tr>
              <td colSpan="6">No hay partidos creados</td>
            </tr>
          )
        }
        {
          !!isViewMoreActive && (
            <tr>
              <td colSpan="6">
                <button
                  className="btn-orange"
                  onClick={handleViewMoreClick}
                >
                  Ver mas
                </button>
              </td>
            </tr>
          )
        }
        <MatchesCreator
          data={{
            equipos,
          }}
          handlers={{
            handleCreateClick,
          }}
        />
      </tbody>
    </table>
  );
}

export default MatchesTable;
