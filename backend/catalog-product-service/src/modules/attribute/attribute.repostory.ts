import { Injectable } from '@nestjs/common';
import { EventEmitter2 } from '@nestjs/event-emitter';
import { Model } from 'mongoose';
import { Attribute } from 'src/schemas/attribute.schema';
import { BaseRepository } from 'src/shared/base.repository';

@Injectable()
export class AttributeRepository extends BaseRepository<Attribute> {
  constructor(model: Model<Attribute>, emitter: EventEmitter2) {
    super(model, emitter);
  }
}
