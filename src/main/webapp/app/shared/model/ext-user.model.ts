export interface IExtUser {
  id?: number;
  name?: string;
  firstName?: string;
  lastName?: string | null;
  birthDate?: string | null;
  address?: string | null;
}

export const defaultValue: Readonly<IExtUser> = {};
