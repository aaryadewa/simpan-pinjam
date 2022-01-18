import React from 'react';
import { NavLink as Link } from 'react-router-dom';
import { DropdownItem, DropdownMenu, DropdownToggle, NavbarBrand, NavItem, NavLink, UncontrolledDropdown } from 'reactstrap';


export const Brand = () => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <span className="brand-title">Simpan Pinjam</span>
  </NavbarBrand>
);

export const Home = () => (
  <NavItem>
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <span>Home</span>
    </NavLink>
  </NavItem>
);

export const ExtUserMenu = () => (
  <NavItem>
    <NavLink tag={Link} to="/ext-users" className="d-flex align-items-center">
      <span>Users</span>
    </NavLink>
  </NavItem>
);

export const TrxAccountMenu = () => (
  <NavItem>
    <NavLink tag={Link} to="/trx-accounts" className="d-flex align-items-center">
      <span>Account Transactions</span>
    </NavLink>
  </NavItem>
);

export const TrxHistoryMenu = () => (
  <NavItem>
    <NavLink tag={Link} to="/trx-histories" className="d-flex align-items-center">
      <span>Transaction Reports</span>
    </NavLink>
  </NavItem>
);
