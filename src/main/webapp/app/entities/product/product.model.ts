import { IProductCategory } from 'app/entities/product-category/product-category.model';
import { SizeCloth } from 'app/entities/enumerations/size-cloth.model';

export interface IProduct {
  id?: number;
  name?: string;
  description?: string | null;
  price?: number;
  sizeCloth?: SizeCloth;
  imageContentType?: string | null;
  image?: string | null;
  productCategory?: IProductCategory | null;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string | null,
    public price?: number,
    public sizeCloth?: SizeCloth,
    public imageContentType?: string | null,
    public image?: string | null,
    public productCategory?: IProductCategory | null
  ) {}
}

export function getProductIdentifier(product: IProduct): number | undefined {
  return product.id;
}
