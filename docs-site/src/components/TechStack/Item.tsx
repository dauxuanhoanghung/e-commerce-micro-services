import { motion } from "framer-motion";
import styles from "./styles.module.css";

export interface ITechItem {
  name: string;
  icon: string;
}

interface TechItemProps {
  item: ITechItem;
  index: number;
}

const TechItem: React.FC<TechItemProps> = ({ item, index }: TechItemProps) => {
  return (
    <motion.div
      key={item.name}
      initial={{ opacity: 0, scale: 0.5 }}
      whileInView={{ opacity: 1, scale: 1 }}
      transition={{ duration: 0.25 }}
      viewport={{ once: true }}
      whileHover={{ scale: 1.1 }}
      className="flex cursor-pointer flex-col items-center justify-center gap-4 rounded-xl border border-white/10 bg-white/5 p-6 backdrop-blur-sm"
    >
      <div className="relative h-16 w-16">
        <img
          src={item.icon}
          alt={item.name}
          title={item.name}
          className="relative z-10 h-full w-full object-contain drop-shadow-[0_0_8px_rgba(255,255,255,0.3)]"
        />
        <div className={styles.glowEffect} />
      </div>
      <span className="text-center text-[0.9rem] text-white opacity-90">
        {item.name}
      </span>
    </motion.div>
  );
};

export default TechItem;
