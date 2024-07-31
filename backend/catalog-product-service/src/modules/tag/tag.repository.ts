import { Injectable } from '@nestjs/common';
import { EventEmitter2 } from '@nestjs/event-emitter';
import { Model } from 'mongoose';
import { Tag } from 'src/schemas/tag.schema';
import { BaseRepository } from 'src/shared/base.repository';

@Injectable()
export class TagRepository extends BaseRepository<Tag> {
  constructor(model: Model<Tag>, emitter: EventEmitter2) {
    super(model, emitter);
  }
}
