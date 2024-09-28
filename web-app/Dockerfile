FROM node:20-alpine as builder
WORKDIR /web-app

COPY package.json package-lock.json ./
RUN npm ci
COPY . .
RUN npm run build

FROM node:18-alpine as runner
WORKDIR /web-app
COPY --from=builder /web-app/package.json .
COPY --from=builder /web-app/package-lock.json .
COPY --from=builder /web-app/next.config.js ./
COPY --from=builder /web-app/public ./public
COPY --from=builder /web-app/.next/standalone ./
COPY --from=builder /web-app/.next/static ./.next/static
EXPOSE 3000
ENTRYPOINT ["npm", "start"]
