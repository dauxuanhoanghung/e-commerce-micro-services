export interface SearchCriteria {
  term: string;
  fields: string[];
  operator?: string;
  limit?: number;
  offset?: number;
  sort?: { [field: string]: number };
  filters?: { [field: string]: any };
}
