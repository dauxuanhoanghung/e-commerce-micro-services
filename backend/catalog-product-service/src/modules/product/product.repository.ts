import { Injectable } from '@nestjs/common';
import { EventEmitter2 } from '@nestjs/event-emitter';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { Product } from 'src/schemas/product.schema';
import { BaseRepository } from 'src/shared/base.repository';

@Injectable()
export class ProductRepository extends BaseRepository<Product> {
  constructor(
    @InjectModel(Product.name, 'catalog-product') model: Model<Product>,
    emitter: EventEmitter2,
  ) {
    super(model, emitter);
  }
}
