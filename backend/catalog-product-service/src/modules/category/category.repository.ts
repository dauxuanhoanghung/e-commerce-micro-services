import { Injectable } from '@nestjs/common';
import { EventEmitter2 } from '@nestjs/event-emitter';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { Category } from 'src/schemas/category.schema';
import { BaseRepository } from 'src/shared/base.repository';

@Injectable()
export class CategoryRepository extends BaseRepository<Category> {
  constructor(
    @InjectModel(Category.name, 'catalog-product') model: Model<Category>,
    emitter: EventEmitter2,
  ) {
    super(model, emitter);
  }
}
