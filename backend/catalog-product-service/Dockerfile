FROM node:20-alpine AS development
WORKDIR /src/catalog-product-service
COPY --chown=node:node package*.json ./
RUN npm ci -f
COPY --chown=node:node . .
USER node
RUN npm run start:dev

FROM node:20-alpine AS build
WORKDIR /usr/src/app
COPY --chown=node:node package*.json ./
COPY --chown=node:node --from=development /src/catalog-product-service/node_modules ./node_modules
COPY --chown=node:node . .
RUN npm run build
RUN npm ci -f --only=production && npm cache clean --force
USER node

FROM node:20-alpine AS production
ENV NODE_ENV production
COPY --chown=node:node --from=build /src/catalog-product-service/node_modules ./node_modules
COPY --chown=node:node --from=build /src/catalog-product-service/dist ./dist
CMD [ "node", "dist/main.js" ]