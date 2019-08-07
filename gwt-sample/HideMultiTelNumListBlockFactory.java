package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.factories;

import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.HideMultiTelNumListBlock;
import carapace.web2.gwt.client.basic.aisyc.common.factories.AisycFactory;

public class HideMultiTelNumListBlockFactory extends AisycFactory<HideMultiTelNumListBlock> {

    public HideMultiTelNumListBlockFactory(){
        super();
    }

    public HideMultiTelNumListBlock create(String aspId, String cid){
        return new HideMultiTelNumListBlock(aspId, cid);
    }

    public HideMultiTelNumListBlock create(){
        return new HideMultiTelNumListBlock();
    }

}
