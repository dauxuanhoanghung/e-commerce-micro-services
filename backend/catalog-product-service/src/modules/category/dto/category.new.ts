import { IsNotEmpty, IsNumber } from 'class-validator';

export class NewCategoryDto {
  @IsNotEmpty({ message: 'Name is required' })
  name: string;
  description?: string;
  @IsNumber(
    { allowNaN: false, allowInfinity: false },
    { message: 'Level is required' },
  )
  level: number;
  parentId?: string;
}
