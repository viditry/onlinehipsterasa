import { IUser } from 'app/entities/user/user.model';
import { IProductOrder } from 'app/entities/product-order/product-order.model';
import { Gender } from 'app/entities/enumerations/gender.model';

export interface ICustomer {
  id?: number;
  firstName?: string;
  lastName?: string;
  gender?: Gender;
  email?: string | null;
  phone?: string;
  adressLine1?: string;
  adressLine2?: string | null;
  city?: string;
  country?: string;
  user?: IUser;
  orders?: IProductOrder[] | null;
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public gender?: Gender,
    public email?: string | null,
    public phone?: string,
    public adressLine1?: string,
    public adressLine2?: string | null,
    public city?: string,
    public country?: string,
    public user?: IUser,
    public orders?: IProductOrder[] | null
  ) {}
}

export function getCustomerIdentifier(customer: ICustomer): number | undefined {
  return customer.id;
}
