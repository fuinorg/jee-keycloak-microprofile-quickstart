# Chrome in a Docker container

## Prepare

Download security settings 

```
wget https://raw.githubusercontent.com/jfrazelle/dotfiles/master/etc/docker/seccomp/chrome.json -O ~/chrome.json
```

## Run

Allow temporarily access to the X-session

```
xhost local:root
```

Start using Docker Compose

```
docker-compose -f docker-compose-chrome.yml up
```

After you finished your work

```
docker-compose -f docker-compose-chrome.yml rm
```
