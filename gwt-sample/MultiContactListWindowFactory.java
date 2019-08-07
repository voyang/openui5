package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.factories;

import java.util.List;

import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.model.AisycDataGridRowData;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.MultiContactListWindow;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.value.MultiContactListTabValue;
import carapace.web2.gwt.client.basic.aisyc.common.factories.AisycFactory;

public class MultiContactListWindowFactory extends AisycFactory<MultiContactListWindow> {

    public MultiContactListWindowFactory(){
        super();
    }

    public MultiContactListWindow create(
            String paramAspId,
            String paramCid,
            String receptHistId,
            String supportHistSeq,
            List<MultiContactListTabValue> tabInfoList,
            List<AisycDataGridRowData> deptInfoList) {

        return new MultiContactListWindow(paramAspId, paramCid, receptHistId, supportHistSeq, tabInfoList, deptInfoList);
    }

}
