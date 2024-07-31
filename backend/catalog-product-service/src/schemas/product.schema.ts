import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Types } from 'mongoose';
import { BaseSchema } from 'src/shared/base.schema';

@Schema({ _id: false })
export class Product extends BaseSchema {
  @Prop({ type: String })
  name: string;

  @Prop({ type: String })
  description: string;

  @Prop({ type: Number })
  price: number;

  @Prop({ type: String })
  sku: string;

  @Prop({ type: [Types.ObjectId], ref: 'Category' })
  categoryIds: Types.ObjectId[];

  @Prop({ type: [Types.ObjectId], ref: 'Tag' })
  tags: Types.ObjectId[];

  @Prop({ type: [Types.ObjectId], ref: 'Attribute' })
  attributes: Types.ObjectId[];

  @Prop({ type: [String] })
  images: string[];

  @Prop({ type: String })
  storeId: string;

  @Prop({ type: String })
  productType: string;

  @Prop({ type: Boolean })
  isActive: boolean;

  @Prop({ type: Boolean })
  isDeleted: boolean;

  @Prop({ type: Date })
  deletedAt: Date;
}

export const ProductSchema = SchemaFactory.createForClass(Product);

ProductSchema.index({ id: 1 }, { unique: true });
ProductSchema.index({ sku: 1 }, { unique: true });
