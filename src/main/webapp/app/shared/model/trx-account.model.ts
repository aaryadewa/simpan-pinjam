import { IExtUser } from 'app/shared/model/ext-user.model';
import { TransactionType } from 'app/shared/model/enumerations/transaction-type.model';

export interface ITrxAccount {
  id?: number;
  amount?: number;
  trxDate?: string;
  trxType?: TransactionType;
  user?: IExtUser;
}

export const defaultValue: Readonly<ITrxAccount> = {};
