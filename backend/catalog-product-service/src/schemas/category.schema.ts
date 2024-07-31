import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Types } from 'mongoose';
import { BaseSchema } from 'src/shared/base.schema';

@Schema()
export class Category extends BaseSchema {
  @Prop({ type: String })
  name: string;

  @Prop({ type: String })
  description: string;

  @Prop({ type: Number })
  level: number;

  @Prop({ type: Types.ObjectId })
  parentId: Types.ObjectId;
}

export const CategorySchema = SchemaFactory.createForClass(Category);
