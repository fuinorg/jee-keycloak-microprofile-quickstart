#!/bin/bash

hostsFile="/etc/hosts"

if grep -q "jee-keycloak-microprofile-quickstart" "$hostsFile"; then
    echo "$hostsFile already contains project entries..."
    exit 1
fi

# Install NGINX
sudo apt-get install nginx --assume-yes
sudo cp conf/etc/nginx/proxy.conf /etc/nginx/proxy.conf
sudo cp conf/etc/nginx/sites-enabled/* /etc/nginx/sites-enabled
sudo invoke-rc.d nginx reload

# Append service names to hosts
echo "" | sudo tee -a "$hostsFile"
echo "# The following entries belong to the jee-keycloak-microprofile-quickstart" | sudo tee -a "$hostsFile"
echo "127.0.0.1   jkmq-postgres" | sudo tee -a "$hostsFile"
echo "127.0.0.1   jkmq-keycloak" | sudo tee -a "$hostsFile"
echo "127.0.0.1   jkmq-service" | sudo tee -a "$hostsFile"
echo "127.0.0.1   jkmq-swagger-ui" | sudo tee -a "$hostsFile"
echo "127.0.0.1   jkmq-jaeger" | sudo tee -a "$hostsFile"
echo "127.0.0.1   jkmq-pgadmin4" | sudo tee -a "$hostsFile"
echo "" | sudo tee -a "$hostsFile"
