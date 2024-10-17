FROM node:20-alpine AS deps
COPY package.json package-lock.json ./
RUN npm ci

FROM node:20-alpine AS builder
WORKDIR /web-app
COPY . .
COPY --from=deps node_modules ./node_modules
RUN npm run build

FROM node:20-alpine AS runner
WORKDIR /web-app
COPY --from=builder /web-app/package.json .
COPY --from=builder /web-app/package-lock.json .
COPY --from=builder /web-app/next.config.js ./
COPY --from=builder /web-app/public ./public
COPY --from=builder /web-app/.next/standalone ./
COPY --from=builder /web-app/.next/static ./.next/static
EXPOSE 3000
ENTRYPOINT ["npm", "start"]
