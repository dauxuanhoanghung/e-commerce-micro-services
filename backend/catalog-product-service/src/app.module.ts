import { Module } from '@nestjs/common';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { EventEmitterModule } from '@nestjs/event-emitter';
import { MongooseModule } from '@nestjs/mongoose';

import configuration from './config/configuration';
import { AttributeModule } from './modules/attribute/attribute.module';
import { CategoryModule } from './modules/category/category.module';
import { ProductModule } from './modules/product/product.module';
import { TagModule } from './modules/tag/tag.module';

@Module({
  imports: [
    ConfigModule.forRoot({
      cache: true,
      envFilePath: ['.env', '.env.sample'],
      isGlobal: true,
      load: [configuration],
    }),
    EventEmitterModule.forRoot(),
    MongooseModule.forRootAsync({
      connectionName: 'catalog-product',
      imports: [ConfigModule],
      inject: [ConfigService],
      useFactory: (configService: ConfigService) => ({
        uri: configService.get<string>('DATABASE_URI'),
        dbName: configService.get<string>('DATABASE_NAME'),
        retryAttempts: 5,
        retryDelay: 1000,
      }),
    }),
    ProductModule,
    CategoryModule,
    AttributeModule,
    TagModule,
  ],
})
export class AppModule {}
