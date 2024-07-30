import { Injectable } from '@nestjs/common';
import { EventEmitter2 } from '@nestjs/event-emitter';
import { Model } from 'mongoose';
import { Product } from 'src/schemas/product.schema';
import { BaseRepository } from 'src/shared/base.repository';

@Injectable()
export class ProductRepository extends BaseRepository<Product> {
  constructor(model: Model<Product>, emitter: EventEmitter2) {
    super(model, emitter);
  }
}
