import { Config } from "tailwindcss";

const config: Config = {
  corePlugins: {
    preflight: false, // This disables Tailwind's base styles completely
  },
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {},
  plugins: [],
};

export default config;
