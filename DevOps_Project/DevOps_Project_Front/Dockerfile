#### Stage 1: Build the angular application
FROM node as build

# Configure the main working directory inside the docker image.
# This is the base directory used in any further RUN, COPY, and ENTRYPOINT
# commands.
WORKDIR /app

# Copy the package.json as well as the package-lock.json and install
# the dependencies. This is a separate step so the dependencies
# will be cached unless changes to one of those two files
# are made.
# COPY package*.json .

# Copy the main application
COPY . .
RUN npm install

# Arguments
# ARG configuration=production

# Build the application
RUN npm run build --prod

#### Stage 2, use the compiled app, ready for production with Nginx
FROM nginx:alpine


# Copy our custom nginx config
COPY src/nginx/etc/conf.d/default.conf /etc/nginx/conf/default.conf

# Copy the angular build from Stage 1
COPY --from=build app/dist/angular-qlsv-demo usr/share/nginx/html


# Expose port 80 to the Docker host, so we can access it
# from the outside.
EXPOSE 80

ENTRYPOINT ["nginx","-g","daemon off;"]
