# This Source Code Form is subject to the terms of the Mozilla Public License,
# v. 2.0. If a copy of the MPL was not distributed with this file, You can
# obtain one at https://www.bahmni.org/license/mplv2hd.
#
# Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
# graphic logo is a trademark of OpenMRS Inc.

#!/bin/sh -x

TEMP_LOCATION=/tmp/deploy_idgen_webservice_omod
OMOD_LOCATION=/home/jss/.OpenMRS/modules

sudo rm -f $OMOD_LOCATION/idgen-webservices-*.omod
sudo su - jss -c "cp -f $TEMP_LOCATION/* $OMOD_LOCATION"
