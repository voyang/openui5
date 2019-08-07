package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.factories;

import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.HideMultiOptionListBlock;
import carapace.web2.gwt.client.basic.aisyc.common.factories.AisycFactory;

public class HideMultiOptionListBlockFactory extends AisycFactory<HideMultiOptionListBlock> {

    public HideMultiOptionListBlockFactory(){
        super();
    }

    public HideMultiOptionListBlock create(String aspId, String cid){
        return new HideMultiOptionListBlock(aspId, cid);
    }
}
