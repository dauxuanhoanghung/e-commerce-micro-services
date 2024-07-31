import { Prop, Schema } from '@nestjs/mongoose';
import { BaseSchema } from 'src/shared/base.schema';

@Schema()
export class Tag extends BaseSchema {
  @Prop({ type: String })
  name: string;

  @Prop({ type: String })
  description: string;
}
