import { motion } from "framer-motion";

import AnimatedBackground from "../AnimatedBackground";
import TechItem, { ITechItem } from "./Item";
import styles from "./styles.module.css";

const TechStack = ({ tech }: { tech: ITechItem[] }) => {
  return (
    <div className="relative min-h-[400px] w-full overflow-hidden">
      <div className="absolute left-0 top-0 z-[1] h-full w-full">
        <AnimatedBackground variant="default" />
      </div>
      <div className="relative z-[2] p-4">
        <motion.h2
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          // className="text-5xl text-center mb-12 bg-gradient-to-r from-white to-[#a8a8a8]"
          className={styles.techTitle}
        >
          Tech Stack
        </motion.h2>
        <div className="mx-auto grid max-w-[1000px] grid-cols-[repeat(auto-fit,minmax(100px,1fr))] gap-8 px-4 md:grid-cols-[repeat(auto-fit,minmax(120px,1fr))]">
          {tech.map((item: ITechItem, index: number) => (
            <TechItem item={item} index={index} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default TechStack;
