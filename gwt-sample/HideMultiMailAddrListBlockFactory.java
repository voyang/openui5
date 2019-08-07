package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.factories;

import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.HideMultiMailAddrListBlock;
import carapace.web2.gwt.client.basic.aisyc.common.factories.AisycFactory;

public class HideMultiMailAddrListBlockFactory extends AisycFactory<HideMultiMailAddrListBlock> {

    public HideMultiMailAddrListBlockFactory(){
        super();
    }

    public HideMultiMailAddrListBlock create(String aspId, String cid) {
        return new HideMultiMailAddrListBlock(aspId, cid);
    }

    public HideMultiMailAddrListBlock create() {
        return new HideMultiMailAddrListBlock();
    }
}
