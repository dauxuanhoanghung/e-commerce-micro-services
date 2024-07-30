import { Controller, Get, Post, Req } from '@nestjs/common';
import { Request } from 'express';

import { ApiResponse } from 'src/shared/interfaces/generic.response';
import { ProductService } from './product.service';

@Controller('products')
export class ProductController {
  constructor(private readonly productService: ProductService) {}

  @Get()
  async findAll(@Req() request: Request): Promise<ApiResponse> {
    console.log(request);
    return {
      code: 200,
      message: 'Success',
      timestamp: new Date().getTime(),
      data: [
        {
          id: 1,
          name: 'Product 1',
          price: 100,
        },
        {
          id: 2,
          name: 'Product 2',
          price: 200,
        },
      ],
    };
  }

  @Get(':id')
  async findById(): Promise<ApiResponse> {
    return {
      code: 200,
      message: 'Success',
      timestamp: new Date().getTime(),
      data: {
        id: 1,
        name: 'Product 1',
        price: 100,
      },
    };
  }

  @Post()
  async create(): Promise<ApiResponse> {
    return {
      code: 200,
      message: 'Success',
      timestamp: new Date().getTime(),
      data: {
        id: 1,
        name: 'Product 1',
        price: 100,
      },
    };
  }
}
