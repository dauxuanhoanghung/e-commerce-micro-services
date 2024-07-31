import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { BaseSchema } from 'src/shared/base.schema';

@Schema()
export class Attribute extends BaseSchema {
  @Prop({ type: String })
  name: string;

  @Prop({ type: String })
  type: string;

  @Prop({ type: [String] })
  options: string[];

  @Prop({ type: String })
  description: string;
}

export const AttributeSchema = SchemaFactory.createForClass(Attribute);
