import React from 'react';
import { Route, Routes as RouterRoutes } from 'react-router-dom';
import ExtUser from './entities/ext-user';
import ExtUserList from './entities/ext-user/ext-user';
import ExtUserDetail from './entities/ext-user/ext-user-detail';
import TrxAccount from './entities/trx-account';
import TrxAccountList from './entities/trx-account/trx-account';
import TrxHistory from './entities/trx-history';
import TrxHistoryList from './entities/trx-history/trx-history';
import Home from './modules/home/home';
import { withRouter } from './shared/util/component-wrapper';


const Routes = () => {
  const UserDetail = withRouter(ExtUserDetail);

  return (
    <div className="view-routes">
      <RouterRoutes>
        <Route path="/" element={<Home />} />

        <Route path="ext-users" element={<ExtUser />}>
          <Route index element={<ExtUserList />} />
          <Route path=":id" element={
            <UserDetail />
          } />
        </Route>

        <Route path="trx-accounts" element={<TrxAccount />}>
          <Route index element={<TrxAccountList />} />
        </Route>

        <Route path="trx-histories" element={<TrxHistory />}>
          <Route index element={<TrxHistoryList />} />
        </Route>
        
        <Route
          path="*"
          element="{
            <p>404 Page Not Found</p>
          }"
        />
      </RouterRoutes>
    </div>
  );
};

export default Routes;
