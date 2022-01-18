import dayjs from "dayjs";
import { APP_DATE_FORMAT, APP_LOCAL_DATETIME_FORMAT, APP_LOCAL_DATE_FORMAT } from "../../config/constants";

export const formatDateTime: (value:string) => string = (value: string) => {
  return dayjs(value).format(APP_DATE_FORMAT);
};

export const formatDateTimeISO: (value:string) => string = (value: string) => {
  return dayjs(value, APP_LOCAL_DATE_FORMAT).toISOString();
};

export const formatCurrency: (value: number) => string = (value: number) => {
  return new Intl.NumberFormat('id-ID', { style: 'currency', currency: 'IDR' }).format(value);
};