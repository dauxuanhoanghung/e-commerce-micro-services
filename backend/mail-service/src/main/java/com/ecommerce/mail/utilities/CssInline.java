package com.ecommerce.mail.utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CssInline {
    private static final String STYLE = "style";
    private static final String CLASS = "class";


    public String inline(String html) {

        Document doc = Jsoup.parse(html);
        Elements styles = doc.select(STYLE);
        Map<String, String> cssRules = new HashMap<>();
        // Extract CSS rules from <style> tags
        for (Element style : styles) {
            this.parseCssRules(style.html(), cssRules);
            style.remove(); // Remove the <style> tag after processing
        }

        // Apply CSS rules as inline styles
        Elements allElements = doc.getAllElements();
        for (Element element : allElements) {
            String classAttr = element.attr(CLASS);
            if (classAttr.isEmpty()) {
                continue;
            }
            StringBuilder inlineStyle = new StringBuilder();
            for (String className : classAttr.split("\\s+")) {
                String cssRule = cssRules.get("." + className);
                if (cssRule != null && !cssRule.isBlank()) {
                    inlineStyle.append(cssRule).append(";");
                }
            }
            if (!inlineStyle.isEmpty()) {
                String existingStyles = element.attr(STYLE);
                if (existingStyles.isEmpty()) {
                    element.attr(STYLE, inlineStyle.toString().trim());
                } else {
                    element.attr(STYLE, existingStyles + ";" + inlineStyle.toString().trim());
                }
                element.removeAttr(CLASS);
            }
        }

        return doc.html();
    }

    /**
     * Parse CSS rules from a string and add them to a map
     * @param css The CSS string in style tag
     * @param cssRules map to contains CSS rules
     */
    private void parseCssRules(String css, Map<String, String> cssRules) {
        String[] rules = css.split("}");
        for (String rule : rules) {
            String[] parts = rule.split("\\{");
            if (parts.length < 2) continue;
            String selectors = parts[0].trim();
            String properties = parts[1].trim();
            for (String selector : selectors.split(",")) {
                // Check that if exists, append the new properties to the existing ones
                if (cssRules.containsKey(selector.trim())) {
                    properties = cssRules.get(selector.trim()) + ";" + properties;
                }
                cssRules.put(selector.trim(), properties);
            }
        }
    }
}
