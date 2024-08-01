import { Module } from '@nestjs/common';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { EventEmitterModule } from '@nestjs/event-emitter';
import { MongooseModule } from '@nestjs/mongoose';
import { WinstonModule } from 'nest-winston';
import * as winston from 'winston';

import configuration from './config/configuration';
import { AttributeModule } from './modules/attribute/attribute.module';
import { CategoryModule } from './modules/category/category.module';
import { EurekaClientModule } from './modules/eureka/client.module';
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
    EurekaClientModule,
    EventEmitterModule.forRoot(),
    MongooseModule.forRootAsync({
      connectionName: 'catalog-product',
      imports: [ConfigModule],
      inject: [ConfigService],
      useFactory: async (config: ConfigService) => ({
        uri: config.get<string>('DATABASE_URI'),
        dbName: config.get<string>('DATABASE_NAME'),
        user: config.get<string>('DATABASE_USER'),
        pass: config.get<string>('DATABASE_PASSWORD'),
        retryAttempts: 5,
        retryDelay: 1000,
      }),
    }),
    WinstonModule.forRoot({
      transports: [
        new winston.transports.Console({
          format: winston.format.combine(
            winston.format.timestamp(),
            winston.format.colorize(),
            winston.format.printf(
              (info) => `[${info.timestamp}] ${info.level}: ${info.message}`,
            ),
          ),
        }),
        new winston.transports.File({
          filename: 'error.log',
          level: 'error',
        }),
        new winston.transports.File({
          filename: 'combined.log',
        }),
      ],
    }),
    ProductModule,
    CategoryModule,
    AttributeModule,
    TagModule,
  ],
})
export class AppModule {}
