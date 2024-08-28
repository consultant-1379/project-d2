FROM node:16

# Create app directory
WORKDIR /usr/src/app


# Configuration for EUISDK
RUN npm config set @eui:registry=https://arm.seli.gic.ericsson.se/artifactory/api/npm/proj-e-uisdk-npm-local/ \
	&& npm config set @eds:registry=https://arm.rnd.ki.sw.ericsson.se/artifactory/api/npm/proj-eds-npm-local/ \
	&& npm config set registry=https://arm.seli.gic.ericsson.se/artifactory/api/npm/proj-e-uisdk-npm/ \
	&& npm config set proxy=null \
	&& npm config set https-proxy=null


# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
COPY package*.json ./

RUN npm install
# If you are building your code for production
# RUN npm ci --only=production

# Bundle app source
COPY . .
RUN mkdir -p /client/libs

EXPOSE 8080

CMD [ "npm", "start" ]