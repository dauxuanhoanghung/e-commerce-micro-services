import { Module } from '@nestjs/common';
import { EurekaClientService } from './client.service';

@Module({
  providers: [EurekaClientService],
  exports: [EurekaClientService],
})
export class EurekaClientModule {}
