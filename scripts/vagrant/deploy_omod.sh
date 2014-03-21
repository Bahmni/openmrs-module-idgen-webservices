#!/bin/sh -x

TEMP_LOCATION=/tmp/deploy_idgen_webservice_omod
OMOD_LOCATION=/home/jss/.OpenMRS/modules

sudo rm -f $OMOD_LOCATION/idgen-webservices-*.omod
sudo su - jss -c "cp -f $TEMP_LOCATION/* $OMOD_LOCATION"
