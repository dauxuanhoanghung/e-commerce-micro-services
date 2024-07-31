import { VersioningType } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { NestFactory } from '@nestjs/core';

import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule, {
    logger: ['error', 'warn', 'log', 'debug', 'verbose', 'fatal'],
    cors: false,
  });
  const config: ConfigService = app.get<ConfigService>(ConfigService);
  /**
   * Set global prefix for all routes '/catalog/api'
   */
  const contextPath = config.get<string>('CONTEXT_PATH');
  app.setGlobalPrefix(contextPath + '/api');
  /**
   * Add versioning type to URI for all routes '/v1'
   */
  app.enableVersioning({
    type: VersioningType.URI,
    defaultVersion: config.get('VERSION') || '1',
  });
  await app.listen(config.get<number>('PORT'));
}
bootstrap();
