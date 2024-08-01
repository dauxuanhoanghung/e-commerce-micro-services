interface ApplicationConfig {
  port: number;
  version: string | number;
  database: {
    uri?: string;
    host?: string;
    port?: number;
  };
  [key: string]: any;
}

export default (): ApplicationConfig => ({
  port: parseInt(process.env.APP_PORT, 10) || 3001,
  version: process.env.APP_VERSION || 1,
  database: {
    host: process.env.DATABASE_HOST,
    port: parseInt(process.env.DATABASE_PORT, 10) || 27012,
  },
});
