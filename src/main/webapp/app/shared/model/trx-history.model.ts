import { TransactionType } from 'app/shared/model/enumerations/transaction-type.model';

export interface ITrxHistory {
  id?: string;
  accountNo?: number;
  userName?: string;
  firstName?: string;
  lastName?: string | null;
  amount?: number;
  trxDate?: string;
  trxType?: TransactionType;
}

export const defaultValue: Readonly<ITrxHistory> = {};
