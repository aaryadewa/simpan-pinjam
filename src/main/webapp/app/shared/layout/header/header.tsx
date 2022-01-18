import React, { useState } from 'react';
import { Collapse, Nav, Navbar, NavbarToggler } from 'reactstrap';
import { Brand, ExtUserMenu, Home, TrxAccountMenu, TrxHistoryMenu } from './header-components';
import './header.scss';

const Header = () => {
  const [menuOpen, setMenuOpen] = useState(false);
  const toggleMenu = () => setMenuOpen(!menuOpen);

  return (
    <div id="app-header">
      <Navbar data-cy="navbar" dark expand="md" fixed="top" className="bg-primary">
        <NavbarToggler aria-label="Menu" onClick={toggleMenu} />
        <Brand />
        <Collapse isOpen={menuOpen} navbar>
          <Nav id="header-tabs" className="ms-auto" navbar>
            <Home />
            <ExtUserMenu />
            <TrxAccountMenu />
            <TrxHistoryMenu />
          </Nav>
        </Collapse>
      </Navbar>
    </div>
  );
};

export default Header;
