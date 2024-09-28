import type { MetadataRoute } from 'next';

export default function robots(): MetadataRoute.Robots {
  return {
    rules: {
      userAgent: '*',
      allow: '/',
      disallow: ['/admin', '/seller'],
    },
    sitemap: `${process.env.NODE_ENV}/sitemap.xml`,
  };
}
