import React from 'react';
import ReactDOM from 'react-dom';

import AppComponent from './app';

const rootEl = document.getElementById('root');

const render = Component =>
  // eslint-disable-next-line react/no-render-return-value
  ReactDOM.render(
    <Component />,
    rootEl
  );

render(AppComponent);
