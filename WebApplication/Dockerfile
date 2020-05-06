FROM node:8
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
EXPOSE 3000
ENV DEBUG=*
COPY package.json ./
COPY package-lock.json ./
RUN npm install
COPY ./ ./
CMD ["npm", "start"]
