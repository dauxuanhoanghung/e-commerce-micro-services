import BrowserOnly from "@docusaurus/BrowserOnly";
import Link from "@docusaurus/Link";
import useDocusaurusContext from "@docusaurus/useDocusaurusContext";
import { motion } from "framer-motion";
import { useEffect } from "react";

import styles from "./styles.module.css";

const Banner: React.FC = () => {
  const { siteConfig } = useDocusaurusContext();

  useEffect(() => {
    const initializeParticles = () => {
      const particlesElement = document.getElementById("particles-js");
      if (particlesElement && typeof particlesJS !== "undefined") {
        particlesJS("particles-js", {
          particles: {
            number: { value: 80, density: { enable: true, value_area: 800 } },
            color: { value: "#ffffff" },
            shape: { type: "circle" },
            opacity: {
              value: 0.5,
              random: false,
              animation: {
                enable: true,
                speed: 1,
                minimumValue: 0.1,
                sync: false,
              },
            },
            size: {
              value: 3,
              random: true,
              animation: {
                enable: true,
                speed: 2,
                minimumValue: 0.1,
                sync: false,
              },
            },
            lineLinked: {
              enable: true,
              distance: 150,
              color: "#ffffff",
              opacity: 0.4,
              width: 1,
            },
            move: {
              enable: true,
              speed: 1,
              direction: "none",
              random: false,
              straight: false,
              outMode: "out",
              bounce: false,
            },
          },
          interactivity: {
            detectOn: "canvas",
            events: {
              onHover: { enable: true, mode: "repulse" },
              onClick: { enable: true, mode: "push" },
              resize: true,
            },
          },
          retina_detect: true,
        });
      } else {
        setTimeout(initializeParticles, 100);
      }
    };

    initializeParticles();
  }, []);

  return (
    <BrowserOnly fallback={<Loading />}>
      {() => (
        <div className={styles.bannerContainer}>
          <div id="particles-js" className={styles.particles} />
          <div className="relative z-[2] flex h-screen items-center justify-center">
            <motion.div
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.8 }}
              className="mx-auto w-full max-w-[1280px] p-8 text-center text-white"
            >
              <motion.h1
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ delay: 0.3 }}
                className="mb-6 bg-gradient-to-r from-white to-gray-400 bg-clip-text text-[clamp(3rem,8vw,5rem)] font-extrabold text-transparent [text-shadow:0_0_30px_rgba(255,255,255,0.3)]"
              >
                Learn. Build. Inspire.
              </motion.h1>
              <motion.div
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ delay: 0.5 }}
                className={styles.bannerSubtitle}
              >
                <span className={styles.gradientText}>{siteConfig.title}</span>
              </motion.div>
              <motion.h2
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ delay: 0.7 }}
                className={styles.bannerRewardTotal}
              >
                Everyday is another day to be better than you were yesterday
              </motion.h2>
              <motion.div
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ delay: 0.9 }}
                className={styles.buttonGroup}
              >
                <Link to="/docs/intro">
                  <button className={styles.primaryButton}>
                    Documentation
                    <span className={styles.buttonIcon}>📚</span>
                  </button>
                </Link>
                <a href="#tech-stack" className={styles.secondaryButton}>
                  Learn more!
                  <span className={styles.buttonIcon}>↓</span>
                </a>
              </motion.div>
            </motion.div>
          </div>
        </div>
      )}
    </BrowserOnly>
  );
};

const Loading: React.FC = () => (
  <div className="flex min-h-[200px] items-center justify-center">
    <div className="border-primary h-16 w-16 animate-spin rounded-full border-b-2 border-t-2"></div>
  </div>
);

export default Banner;
