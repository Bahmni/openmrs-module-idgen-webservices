#!/bin/sh -x

TEMP_LOCATION=/tmp/deploy_idgen_webservice_omod

if [[ ! -d $TEMP_LOCATION ]]; then
   mkdir $TEMP_LOCATION
fi

rm -rf $TEMP_LOCATION/*