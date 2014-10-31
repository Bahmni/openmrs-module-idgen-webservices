#!/bin/sh -ex

mvn clean install -DskipTests

PATH_OF_CURRENT_SCRIPT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $PATH_OF_CURRENT_SCRIPT/vagrant/vagrant_functions.sh

#All config is here
BASE_DIR=$PATH_OF_CURRENT_SCRIPT/..
TEMP_FOLDER=/tmp/deploy_idgen_webservice_omod
SCRIPTS_DIR=$PATH_OF_CURRENT_SCRIPT/vagrant
OMOD_FILE=$BASE_DIR/omod/target/idgen-webservices-*.omod

# Kill tomcat
run_in_vagrant -f "$SCRIPTS_DIR/tomcat_stop.sh"

# Setup environment
run_in_vagrant -f "$SCRIPTS_DIR/setup_environment.sh"

# Copy Ref App War file to Vagrant tmp
scp_to_vagrant $OMOD_FILE $TEMP_FOLDER

#Deploy them from Vagrant /tmp to appropriate location
run_in_vagrant -f "$SCRIPTS_DIR/deploy_omod.sh"

# Restart tomcat
run_in_vagrant -f "$SCRIPTS_DIR/tomcat_start.sh"