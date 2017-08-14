package org.openmrs.module.idgen.webservices;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;

public class Activator extends BaseModuleActivator {
    
    private Log log = LogFactory.getLog(this.getClass());

    @Override
    public void started() {
        log.info("Started the " + IdgenWsConstants.MODULE_ARTIFACT_ID + " module");
    }

    @Override
    public void stopped() {
        log.info("Stopped the " + IdgenWsConstants.MODULE_ARTIFACT_ID + " module");
    }
}
