import { ITrxAccount } from 'app/shared/model/trx-account.model';
import { IQueryParams } from 'app/shared/util/service-util';
import axios from 'axios';

const apiUrl = 'api/trx-accounts';

export const getEntities = (params: IQueryParams = {}) => {
  const { page, query, size, sort } = params;
  const requestPath = [ apiUrl ];
  const queryString: string[] = [];

  if (page || query || size || sort) {
    requestPath.push('?');

    if (page) {
      queryString.push(`page=${page}`);
    }
    if (query) {
      queryString.push(query);
    }
    if (size) {
      queryString.push(`size=${size}`);
    }
    if (sort) {
      queryString.push(`sort=${sort}`);
    }
  }

  const requestUrl = requestPath.join('') + queryString.join('&');
  return axios.get<ITrxAccount[]>(requestUrl);
};
