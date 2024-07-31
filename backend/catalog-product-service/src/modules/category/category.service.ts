import { Injectable } from '@nestjs/common';

import { Category } from 'src/schemas/category.schema';
import { CategoryRepository } from './category.repository';
import { NewCategoryDto } from './dto/category.new';
import { UpdateCategoryDto } from './dto/category.update';

@Injectable()
export class CategoryService {
  constructor(private readonly categoryRepository: CategoryRepository) {}

  async create(newCategory: NewCategoryDto): Promise<Category> {
    return await this.categoryRepository.create(newCategory);
  }

  async findAll(): Promise<Category[]> {
    return await this.categoryRepository.findAll();
  }

  async findById(id: string): Promise<Category> {
    return await this.categoryRepository.findById(id);
  }

  async update(
    id: string,
    updatedCategory: UpdateCategoryDto,
  ): Promise<Category> {
    return await this.categoryRepository.update(id, updatedCategory);
  }

  async delete(id: string): Promise<Category> {
    return await this.categoryRepository.delete(id);
  }
}
