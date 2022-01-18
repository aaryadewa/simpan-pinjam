import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { Container } from 'reactstrap';
import './app.scss';
import './config/dayjs.ts';
import AppRoutes from './routes';
import Header from './shared/layout/header/header';



const baseHref = document.querySelector('base')?.getAttribute('href')?.replace(/\/$/, '');

export const App = () => {
  const paddingTop = '74px';
  return (
    <BrowserRouter basename={baseHref}>
      <Container className="app-container" style={{ paddingTop }}>
        <Header />
        <Container className="view-container" fluid>
          <AppRoutes />
        </Container>
      </Container>
    </BrowserRouter>
  );
};

export default App;
