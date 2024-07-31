// src/eureka/eureka-client.service.ts
import { Injectable, OnModuleDestroy, OnModuleInit } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { Eureka } from 'eureka-js-client';

@Injectable()
export class EurekaClientService implements OnModuleInit, OnModuleDestroy {
  private readonly client: Eureka;

  constructor(private readonly config: ConfigService) {
    this.client = new Eureka({
      instance: {
        app: config.get<string>('SERVICE_NAME'),
        instanceId: '127.0.0.1',
        hostName: 'localhost',
        ipAddr: '127.0.0.1',
        statusPageUrl: 'http://localhost:3000',
        port: {
          $: 3000,
          '@enabled': true,
        },
        vipAddress: 'localhost',
        dataCenterInfo: {
          '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
          name: 'MyOwn',
        },
      },
      eureka: {
        host: config.get<string>('EUREKA_HOST') || '127.0.0.1',
        port: config.get<number>('EUREKA_PORT') || 8761,
        servicePath: '/eureka/apps/',
        maxRetries: 10,
        requestRetryDelay: 2000,
        fetchRegistry: true,
        registerWithEureka: true,
      },
      requestMiddleware: (requestOpts, done) => {
        requestOpts.auth = {
          user: config.get<string>('EUREKA_AUTH_USER'),
          password: config.get<string>('EUREKA_AUTH_PASS'),
        };
        done(requestOpts);
      },
    });
  }

  onModuleInit() {
    this.client.start((error) => {
      if (error) {
        console.log('Error connecting to Eureka server:', error);
      } else {
        console.log('Connected to Eureka server');
      }
    });
  }

  onModuleDestroy() {
    this.client.stop();
  }
}
