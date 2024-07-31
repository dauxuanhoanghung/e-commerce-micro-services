import { EventEmitter2 } from '@nestjs/event-emitter';
import { Document, Model } from 'mongoose';

export abstract class BaseRepository<T extends Document, ID = string> {
  protected readonly eventPrefix: string;
  private static AFTER_CREATED = 'created';
  private static AFTER_UPDATED = 'updated';
  private static AFTER_DELETED = 'deleted';
  constructor(
    private readonly model: Model<T>,
    private readonly emitter: EventEmitter2,
    eventPrefix?: string,
  ) {
    this.eventPrefix = eventPrefix || model.modelName.toLowerCase();
  }

  private emitEvent(action: string, payload: any) {
    this.emitter.emit(`${this.eventPrefix}.${action}`, payload);
  }

  async create(data: any): Promise<T> {
    const model = new this.model(data);
    await model.save();
    this.emitEvent(BaseRepository.AFTER_CREATED, model);
    return model;
  }

  async findAll(): Promise<T[]> {
    return this.model.find().exec();
  }

  async findById(id: ID): Promise<T | null> {
    return this.model.findById(id).exec();
  }

  async update(id: ID, data: any): Promise<T | null> {
    const model = await this.model.findById(id).exec();
    if (!model) {
      return null;
    }
    model.set(data);
    await model.save();
    this.emitEvent(BaseRepository.AFTER_UPDATED, model);
    return model;
  }

  async delete(obj: ID | T): Promise<T | null> {
    let id = obj;
    if (typeof obj !== 'string') {
      id = obj['id'];
    }
    const model = await this.model.findByIdAndDelete(id).exec();
    if (!model) {
      return null;
    }
    this.emitEvent(BaseRepository.AFTER_DELETED, model);
    return model;
  }
}
