import type * as Preset from "@docusaurus/preset-classic";
import type { Config } from "@docusaurus/types";
import { themes as prismThemes } from "prism-react-renderer";

import autoprefixer from "autoprefixer";
import tailwindCss from "tailwindcss";

const config: Config = {
  title: "Ecommerce Platform Docs",
  tagline: "Ecommerce Platform Docs",
  favicon: "img/favicon.ico",

  // Set the production url of your site here
  url: "https://your-docusaurus-site.example.com",
  baseUrl: "/",
  customFields: {
    teamEmail: process.env.EMAIL,
  },
  organizationName: "dauxuanhoanghung",
  projectName: "e-commerce-micro-services",
  onBrokenLinks: "throw",
  onBrokenMarkdownLinks: "warn",
  i18n: {
    defaultLocale: "en",
    locales: ["en", "vi"],
  },
  plugins: [
    async function tailwindCssPlugin(context, options) {
      return {
        name: "docusaurus-tailwindcss",
        configurePostCss(postcssOptions) {
          postcssOptions.plugins.push(tailwindCss);
          postcssOptions.plugins.push(autoprefixer);
          return postcssOptions;
        },
      };
    },
  ],
  presets: [
    [
      "classic",
      {
        docs: {
          sidebarPath: "./sidebars.ts",
          editUrl:
            "https://github.com/dauxuanhoanghung/e-commerce-micro-services/tree/main/docs-site/",
        },
        blog: {
          showReadingTime: true,
          feedOptions: {
            type: ["rss", "atom"],
            xslt: true,
          },
          // editUrl:
          //   "https://github.com/dauxuanhoanghung/e-commerce-micro-services/tree/main/docs-site/",
          // Useful options to enforce blogging best practices
          onInlineTags: "warn",
          onInlineAuthors: "warn",
          onUntruncatedBlogPosts: "warn",
        },
        theme: {
          customCss: ["./src/css/tailwind.css", "./src/css/custom.css"],
        },
      } satisfies Preset.Options,
    ],
  ],
  themeConfig: {
    // algolia: {
    //   apiKey: "29f77e2902a78a311439153dc3d3b0eb",
    //   indexName: "ecommerce",
    //   contextualSearch: true,
    //   placeholder: "Search in Ecommerce Platform Docs",
    //   appId: "DQKSXOERPV",
    // },
    image: "img/docusaurus-social-card.jpg",
    navbar: {
      title: "Ecommerce Platform Docs",
      logo: {
        alt: "Logo",
        src: "img/logo.svg",
      },
      items: [
        {
          type: "docSidebar",
          sidebarId: "tutorialSidebar",
          position: "left",
          label: "Tutorial",
        },
        {
          type: "localeDropdown",
          position: "right",
        },
        { to: "/blog", label: "Blog", position: "right" },
        {
          href: "https://github.com/dauxuanhoanghung/e-commerce-micro-services",
          className: "vpi-social-github",
          siteID: "github",
          position: "right",
        },
      ],
    },
    footer: {
      style: "dark",
      links: [
        {
          title: "Docs",
          items: [
            {
              label: "Tutorial",
              to: "/docs/intro",
            },
            {
              label: "Architect",
              to: "/docs/architectures",
            },
            {
              label: "Installation",
              to: "/docs/installation",
            },
          ],
        },
        {
          title: "More",
          items: [
            {
              label: "Blog",
              to: "/blog",
            },
            {
              label: "GitHub",
              href: "https://github.com/dauxuanhoanghung/e-commerce-micro-services/tree/main/docs-site/",
            },
          ],
        },
      ],
      copyright: `Copyright © ${new Date().getFullYear()} Ecommerce Platform Micro Service. Built with Docusaurus.`,
    },
    prism: {
      theme: prismThemes.github,
      darkTheme: prismThemes.dracula,
    },
  } satisfies Preset.ThemeConfig,
  scripts: [
    {
      src: "https://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js",
      async: true,
    },
  ],
};

export default config;
