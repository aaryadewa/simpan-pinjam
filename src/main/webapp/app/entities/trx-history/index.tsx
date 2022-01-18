import React from 'react';
import { Outlet } from 'react-router-dom';

export const TrxHistoryIndex = () => {
  return (
    <div className="trx-history">
      <Outlet />
    </div>
  )
};

export default TrxHistoryIndex;
