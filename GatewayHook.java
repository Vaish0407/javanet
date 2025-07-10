package system.score.vms;

import com.inductiveautomation.ignition.common.licensing.LicenseState;
import com.inductiveautomation.ignition.common.script.ScriptManager;
import com.inductiveautomation.ignition.common.script.hints.PropertiesFileDocProvider;
import com.inductiveautomation.ignition.gateway.model.AbstractGatewayModuleHook;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GatewayHook extends AbstractGatewayModuleHook {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void setup(GatewayContext gatewayContext) {


    }

    @Override
    public void startup(LicenseState licenseState) {
      //  Logger.info("ModuleGatewayHook starting up");

    }

    @Override
    public void shutdown() {
      //  Logger.info("ModuleGatewayHook shutting down");

    }

    @Override
    public void initializeScriptManager(ScriptManager manager) {
        super.initializeScriptManager(manager);

        manager.addScriptModule(
                "system.score.vms",
                new VLiteMain(),
                new PropertiesFileDocProvider());
    }

}
