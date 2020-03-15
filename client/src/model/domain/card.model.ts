import {Category} from './category.model';

export class Card {
  id: number;
  name: string;
  category: Category;
  stockQuantity: number;
  value: number;
  status: number;
}
