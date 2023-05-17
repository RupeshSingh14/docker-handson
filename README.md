# DOCKER
```
Author: RUPESH SINGH
Version: 1.0.0
Description: Contains handson docker projects from beginner to expert level from context of a developer, docker commands and their usage.
```
[[_TOC_]]

## Docker Jargons

### Dockerfile
human readable instructions for the app to run. contains all defined dependencies, runtime, etc.  Like a class in java or build.gradle

### Build
creating snapshot of Dockerfile. like gradle build

### Image
A specific snapshot. Lightweight executable VM. like .class or .jar

### Tag 
Image version. like jar version

### Container
Instance of image. Running the light weight VM. We can create multiple containers from an image. Like instance in java

### DockerHub 
Image registry. Like Maven repository.

### Commands and Usage

`docker version`: to check version of client and server installed on machine. both are required.

`docker-compose`: to run docker specific commands

`docker system prune -af`: to clean the system from temp files, cache, container and images build by docker in the machine. It removes all these.

`docker system prune -f`: it does not removes images.

`docker images`: shows the list of images in the machine

`docker run [image-name]`: to create a container of the image. Like Person p1 = new Person() in java.

```
docker pull [image-name] - to pull the image from DockerHub
docker pull ubuntu - to pull the latest image of ubuntu from dockerhub
```

`docker ps`: to  check on all running containers

`docker ps -a`: to check on all containers that are running,ran and stopped ie.. everything

An example of the docker workflow, when we run a docker image ie.. hello-world image available in dockerhub

```
docker run hello-world

Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
2db29710123e: Pulling fs layer
2db29710123e: Verifying Checksum
2db29710123e: Download complete
2db29710123e: Pull complete
Digest: sha256:6e8b6f026e0b9c419ea0fd02d3905dd0952ad1feea67543f525c73a0a790fefb
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
```

`docker run -it ubuntu`: To prevent ubuntu container from exiting and keeping it running, we can run it in interactive mode

```
winpty docker run -it ubuntu
```
winpty is used in case of running it from windows bash, not required otherwise. (-it - to open in interactive mode with standard output linked.)

```
Example - 
$ winpty docker run -it ubuntu
root@c8e4c3531f94:/#
root@c8e4c3531f94:/# su
root@c8e4c3531f94:/# cd /
root@c8e4c3531f94:/# ls
bin  boot  dev  etc  home  lib  lib32  lib64  libx32  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
root@c8e4c3531f94:/# date
Mon Feb 20 16:20:33 UTC 2023
root@c8e4c3531f94:/# mkdir rupesh
root@c8e4c3531f94:/# echo "docker is nice" > /rupesh/docker.txt
root@c8e4c3531f94:/# cat /rupesh/docker.txt
docker is nice
root@c8e4c3531f94:/# exit
exit
root@c8e4c3531f94:/# ls
bin  boot  dev  etc  home  lib  lib32  lib64  libx32  media  mnt  opt  proc  root  run  rupesh  sbin  srv  sys  tmp  usr  var
root@c8e4c3531f94:/# exit
exit
```
The container is stopped and exited once we exit it. And upon running the ubuntu image again, we get a new container of it.

```
docker run -it --name=c1 ubuntu
Ex -
$ winpty docker run -it --name=c1 ubuntu
root@3e1ef90c580c:/# mkdir rupesh
root@3e1ef90c580c:/# ls
bin  boot  dev  etc  home  lib  lib32  lib64  libx32  media  mnt  opt  proc  root  run  rupesh  sbin  srv  sys  tmp  usr  var
root@3e1ef90c580c:/# exit
exit
```
Every container created gets a random name assigned to it if not provided. This can be assigned by us and can be used later.

```
$ docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES

$ docker ps -a
CONTAINER ID   IMAGE     COMMAND       CREATED          STATUS                       PORTS     NAMES
3e1ef90c580c   ubuntu    "/bin/bash"   26 seconds ago   Exited (0) 9 seconds ago               c1
dfeaff1c34a8   ubuntu    "/bin/bash"   11 minutes ago   Exited (127) 6 minutes ago             distracted_bell
c8e4c3531f94   ubuntu    "/bin/bash"   15 minutes ago   Exited (0) 11 minutes ago              kind_bhabha
```

```
docker start -ia container_name 
Ex -
$ docker start --help

Usage:  docker start [OPTIONS] CONTAINER [CONTAINER...]

Start one or more stopped containers

Options:
  -a, --attach               Attach STDOUT/STDERR and forward signals
      --detach-keys string   Override the key sequence for detaching a
                             container
  -i, --interactive          Attach container's STDIN

$ docker start -ia c1
root@3e1ef90c580c:/# ls
ls
bin   dev  home  lib32  libx32  mnt  proc  run     sbin  sys  usr
boot  etc  lib   lib64  media   opt  root  rupesh  srv   tmp  var
root@3e1ef90c580c:/# exit  
```
to restart a stopped container.

```
docker stop [name or container id] - it takes some time to stop 
docker kill [name or container id] - its kills immediately and used more
```
to stop the running containers

```
docker exec [container name] [command] 
$ docker start -ia c1
root@aa83d4c4895e:/# ls

Then in a different window
$ docker exec c1 date
Mon Feb 20 16:56:13 UTC 2023
```
to execute the provided command on a running container. just like run command but does not creates new container

```
docker Image name format - [registry-host:port]/[username]/[image-name][:tag]
                              docker.io           libraray               latest

Example
$ docker pull hello-world
Using default tag: latest
latest: Pulling from library/hello-world
2db29710123e: Pulling fs layer
2db29710123e: Download complete
2db29710123e: Pull complete
Digest: sha256:6e8b6f026e0b9c419ea0fd02d3905dd0952ad1feea67543f525c73a0a790fefb
Status: Downloaded newer image for hello-world:latest
docker.io/library/hello-world:latest
```                              					  
**if not specified, docker server assumes registry to be docker.io, from its library and the latest tag** 
(latest is just a tag and not compulsory the lastest image available in hub)

```
docker pull open-jdk:[tag_name]
docker pull nginx:perl
```
We can refer docker hub and pull the image of any software, say jdk which suits us based on tag -

When we run the nginx container, it runs on the port 80 of the container which can only be accessibe from inside this container. 
Example of server only listening to container port
```
$ winpty docker exec -it ab60a7718c33 bash
root@ab60a7718c33:/# curl http://localhost:80
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
<style>
html { color-scheme: light dark; }
body { width: 35em; margin: 0 auto;
font-family: Tahoma, Verdana, Arial, sans-serif; }
</style>
</head>
<body>
<h1>Welcome to nginx!</h1>
<p>If you see this page, the nginx web server is successfully installed and
working. Further configuration is required.</p>

<p>For online documentation and support please refer to
<a href="http://nginx.org/">nginx.org</a>.<br/>
Commercial support is available at
<a href="http://nginx.com/">nginx.com</a>.</p>

<p><em>Thank you for using nginx.</em></p>
</body>
</html>
root@ab60a7718c33:/#
```

For accessing it from outside we need to do port mapping.
```
docker run -p host-port:container-port nginx - to map the host port to a container port
docker run -p host-port1:container-port1 -p host-port2:container-port2 [image-name] - to map mutiple ports for a container 
$ docker run -p 8111:80 -p 8999:80 nginx
$ docker run -p 8111:80 nginx
```

Deattached mode is for running the container in background
-d, --detach   Run container in background and print container ID

`docker run -d -p 8081:80 nginx`

To check the container generated logs

`docker logs [container id / container name]`

* docker volume mapping - mounting volumes to containers
With this even if container is deleted or image is deleted, the mounted volume will be persisted and later be used to mount it to some other new container, thus preserving the state.

```
docker run -v /host/path:/container-path image - to map specific directory to a container directory (use absolute path)
```

to run multiple host paths to container paths
```
docker run                        
-v /users/rupesh/temp1:/a/b/c1
-v /users/rupesh/temp2:a/b/c1 
[image-name]

docker run -v d:/rupesh/temp:a/b/c image  - for windows users  
```

Since windows system was causing issue with windows path, use powershell to run.
```
docker run -it -v $PWD/Documents/learn/docker/volumes:/rupesh/ ubuntu
docker run -d -p 8111:80 -v $PWD/Documents/learn/docker/volumes:/usr/share/nginx/html nginx
```

* We can define on what level we want the mounting ie.. restricting it to a file or a directory or subdirectory
* A directory to directory and file to file mapping is only legit.
* We can also define restrictive permissions like read only access (ro) on whatever we mount.
* When we create multiple containers, by default Docker will place them in a default network known as Bridge network. Containers in this  network can talk to each other on this network if one knows ip address for the other. There is no name server invloved and and ips are recreated every time when container is stopped.
* Bridge network - default and custom/user defined network
* Host - works only on linux machine
* Overlay - in kubernetes

to check the ip of any container

`docker inspect <container-name>  (not the image name)`

* /etc/resolv.conf - to keep name server  and dns related configs. 
**if we try to ping the ip of any running container in the same brige, we will be able to connect.**
* we can create a custom bridge network and so any container running using that network can connect to each other using name server.

```
docker network ls - to check on available networks
docker network create custom-nw - creates a custom bride network named custom-nw
docker run -it --name=nginx --network=custom-nw nginx - to run the container on the custom network 
```

* Dockerfile is a normal text file which defines images build and execute process. It contains 

```
FROM [image] - this should be the first statement in Dockerfile. It specifies the base image for our docker image. Any image can extend other image
ADD [host-dir] [container-dir]   - ADD files from host directory to the image
COPY [host-dir] [container-dir]  - ADD is like COPY, but ADD can also accept urls to pull from remote.
RUN [command] - command to execute during the image build process. Useful to install any software or create directories etc.
ENV [key] [value] - sets an environment variable
WORKDIR [path] - creates a workspace/ default working directory. If we ignore this command, then root directory / would be used
EXPOSE [port] - Exposes port
CMD [command to execute when container starts]          - Both of them is used to execute command when container is created.
ENTRYPOINT [command to execute when container starts]  - this will restrict any othe command provided in run and will only run the code specified
```

`docker run container [command]`

the optional command if provided overrides the action cotainer has to do in case of CMD is used where as 
it restricts such behaviour in case if ENTRYPOINT is defined. When both ENTRYPOINT and CMD are used, if CMD is not provided the  ENTRYPOINT is done.

`docker build -t hello-docker .`

to build the image with name hello-docker with files in current directory (so '.' is used), else we can specify any other path from where to reference.

Steps to download and install java in an ubuntu container
```
docker ps -it ubuntu
uname -m - to know the underline system processor to download correct java archive

https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_linux-x64_bin.tar.gz
apt-get update - to install all the updates for ubuntu os
apt-get install curl wget  - to install curl and wget
curl https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_linux-x64_bin.tar.gz --output java17.tar.gz
tar -xvzf java17.tar.gz
rm java17.tar.gz
export PATH=$PATH:/jdk-17.0.2/bin

The above code is converted to Dockerfile as 
FROM ubuntu

WORKDIR /java/
RUN apt-get update
RUN apt-get install curl -y
RUN curl https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_linux-x64_bin.tar.gz --output java17.tar.gz
RUN tar -xvzf java17.tar.gz
RUN rm java17.tar.gz

ENV PATH $PATH:/java/jdk-17.0.2/bin

and then run with - docker build -t java17-install C:\Users\Documents\learn\docker\images
where location is current Dockerfile location
```

* We can use ADD instead of installing updates, curl and then curl java17 link
* When we build a new image with name already existing as a image name, pre existing name is voided to none. this gets deleted with prune -f command

* Copy in dockerfile can be used in below was also, for being specific or apply copy on certain type file etc.
```
COPY *.txt text/
COPY src/*.txt dest/
```

* We can pass environment variables by using -e -  docker run -e input=3 java-table.
* for muliple environment variables like -   docker run -e input=3 -e a=b -e c=3 java-table

* When we run CMD with plan format, a shell process runs for executing this when we run the container but when we run CMD in ["", ""] format, it does not runs a shell process and runs in ps process only. this leads to avoid creation of a process and is preferred. But some native shell commands 
do not work with exec, so we need to check if we are executing such commands like CMD ["echo", "$PATH"] will not work.

* Docker build happens in layers and so the options are cached from top to bottom. So whatever changes we do frequently should be tried to shift to bottom, so that build will happen fast and more cached layers will be used as is.

```
docker login - to log to docker hub
docker tag java-table rupesh714/java-table:v1 - creating a tag just like git branching works
docker push rupesh714/java-table - pushing to docker hub  
```

`docker compose`

Docker utility to create docker containers, networks, volume/port mappings, passing environment variables etc in a declarative way.


* A simple docker-compose.yaml to spin a ngnix container 
```
------------------------
version: "3.0"   - version number
services:        - list of services to run
  web-app:       - service name
	image: nginx - image name
	ports:       - port mapping
	- "8111:80"  - array of port mapping
------------------------
```

```
docker-compose up 
Example
PS C:\Users\612943559\Documents\learn\docker\docker-files\docker-compose1> docker-compose ps
NAME                        COMMAND                  SERVICE             STATUS              PORTS
docker-compose1-web-app-1   "/docker-entrypoint.…"   web-app             running             0.0.0.0:8111->80/tcp
```
to run the docker-compose. we have to be in the same directory
docker-compose created container has name as folder-name-service-name-i where i is for number of instance
it also creates a default network bridge with the name of folder-name-default, so that all services within it can share the same bridge

```
NETWORK ID     NAME                      DRIVER    SCOPE
bba2e50caf66   docker-compose1_default   bridge    local
```

`docker-compose down`: to remove the created container and related network.

**docker-compose.yaml file should always be at root of the project. Also, we can rename it as per our convienence to any other name like foo.yaml.** To run such file, we need to explicitly define the file name like -

```
docker-compose --f foo.yaml up

docker-compose up -d - to run the container in deattached mode.
docker-compose logs - to check the container logs
docker rmi <image_name> - to remove the image from machine
docker-compose build - to build the images
docker-compose up --build - to build the images as well as run them after build
```

* In docker compose we can have many services defined. In order to run a specific service out it, we can use `docker-compose up service-name`: helps in local development and testing

* We can define profiles in docker compose file for the services just like spring boot, which will run the services defined with the same 
profile only when profile command is used else only the ones with no profile will be started

`docker-compose --profile=app up`: runs the services having no profile set + services having profile set as app

* We can create multi stage dockerfile to build the entire process from scratch by dividing the entire process as set of different images and containers ie.. we can use some result output of one container build to use it to build some other container.

* A basic use case, using a java + gradle image to to build a container to build the app using src and build.gradle file and then using the jar created from this container to build a java image to run the final jar.

Dockerfile Example:  **docker build -t job-service .**

```
FROM gradle:jdk17-jammy AS BUILDER
WORKDIR build
COPY src src
COPY build.gradle build.gradle
#COPY /gradle/wrapper/ /gradle/wrapper/
COPY settings.gradle settings.gradle
RUN gradle clean build
#RUN $PWD

#-------------------------------------
FROM bellsoft/liberica-openjdk-alpine:17.0.6
WORKDIR /rupesh/app
COPY --from=BUILDER /home/gradle/build/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
```

For testing API's we can use mocker server (https://mock-server.com/) image running in docker container to make it act as server by configuring 
API payload to test clients.
UI dashboard - http://localhost:1080/mockserver/dashboard

## Avoiding pitfalls -
* Always ensure that network inbound and outbounds are in place ie.. an app inside container making a localhost client call will fail.
* Always Ensure gradle build and then running build docker image and running container in case of any change in code.
* Always Ensure removing the existing docker image after any change and building fresh one.
* Always ensure using context in docker compose is using multiple docker files placed at any location.


* WSL can enter a windows path from mnt/host like below
HOSTANME:/mnt/host/c/Users/userid/Documents/workspace/Intellij/docker#