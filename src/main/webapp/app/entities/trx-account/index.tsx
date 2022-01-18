import React from 'react';
import { Outlet } from 'react-router-dom';

export const TrxAccountIndex = () => {
  return (
    <div className="trx-account">
      <Outlet />
    </div>
  )
};

export default TrxAccountIndex;
