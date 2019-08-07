package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail;

/**
*
* 概略
* <p>複数オプション一覧ブロック</p>
*
* @author $Author$
* @version $Revision$ $Date$
*/
public class HideMultiOptionListBlock extends HideMultiOptionListBlockUI  {

    /**
     *
     * コンストラクタ
     */
    public HideMultiOptionListBlock(String aspId, String cid){
        this.aspId = aspId;
        this.cid = cid;

        constructorLastProcess(HideMultiOptionListBlock.class);
    }

    /**
     * 概略
     * <p>イベント設定。</p>
     */
    @Override
    protected void setEvents(){
        super.setEvents();
    }
}
