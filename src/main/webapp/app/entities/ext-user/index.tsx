import React from 'react';
import { Outlet } from 'react-router-dom';

export const ExtUserIndex = () => {
  return (
    <div className="ext-user">
      <Outlet />
    </div>
  )
};

export default ExtUserIndex;
