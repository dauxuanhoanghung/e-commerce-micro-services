export interface ApiResponse<T = any> {
  code: number;
  message: string;
  timestamp: string | number;
  data: T;
}

export interface PaginationResponse<T = any> {
  data: Array<T>;
}
