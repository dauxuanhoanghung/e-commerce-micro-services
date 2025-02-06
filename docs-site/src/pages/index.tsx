import useDocusaurusContext from "@docusaurus/useDocusaurusContext";
import Layout from "@theme/Layout";
import clsx from "clsx";

import { useEffect } from "react";
import Banner from "../components/Banner";
import TechStack from "../components/TechStack";
import styles from "./index.module.css";

import Tech from "../data/tech.json";

export default function Home(): JSX.Element {
  const { siteConfig } = useDocusaurusContext();
  useEffect(() => {
    // 確保 particles.js 已加載
    if (window.particlesJS) {
      console.log("particles.js loaded");
    }

    // 添加平滑滾動
    const handleClick = (e) => {
      const link = e.target.closest("a");
      if (link && link.hash) {
        e.preventDefault();
        const element = document.querySelector(link.hash);
        if (element) {
          element.scrollIntoView({
            behavior: "smooth",
            block: "start",
          });
        }
      }
    };

    document.addEventListener("click", handleClick);
    return () => document.removeEventListener("click", handleClick);
  }, []);

  return (
    <Layout
      title={`${siteConfig.title}`}
      description="Description will go into a meta tag in <head />"
    >
      <header className={clsx("hero hero--primary", styles.heroBanner)}>
        <div className="container">
          <Banner />
        </div>
      </header>
      <main>
        <section id="tech-stack" className="relative">
          <div className="z-9999 relative">
            <TechStack tech={Tech} />
          </div>
        </section>
      </main>
    </Layout>
  );
}
