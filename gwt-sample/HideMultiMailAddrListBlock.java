package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail;

import com.google.gwt.event.dom.client.ClickEvent;

import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.widgetlogic.EDataResultDeployMode;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.AisycDataGrid;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.model.AisycDataGridRowData;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.automation.specific.constants.MultiMailAddsDataGridColumnConst;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.common.events.MailEvent;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.common.events.MailEventArgs;
import carapace.web2.gwt.client.basic.common.events.SSClickHandler;
import carapace.web2.gwt.client.basic.common.util.statical.ConvertUtil;

/**
*
* 概略
* <p>複数メール一覧ブロック</p>
*
* @author $Author$
* @version $Revision$ $Date$
*/
public class HideMultiMailAddrListBlock extends HideMultiMailAddrListBlockUI  {

    /** メールボタンクリックイベント */
    public MailEvent mailButtonClickEvent = new MailEvent(this);

    /**
     *
     * コンストラクタ
     */
    public HideMultiMailAddrListBlock(String aspId, String cid){
        this.aspId = aspId;
        this.cid = cid;

        constructorLastProcess(HideMultiMailAddrListBlock.class);
    }

    /**
     * コンストラクタ
     */
    public HideMultiMailAddrListBlock() {
        constructorLastProcess(HideMultiMailAddrListBlock.class);
    }

    /**
     * 設定タブ情報からブロックに反映する.
     */
    @Override
    protected void initFromTabInfo(){
        super.initFromTabInfo();

        // hideMultiMailAddrListBlockUI_maiBtn.setEnabled(isCanEdit());
        // hideMultiMailAddrListBlockUI_maiBtn.setVisible(isCanEdit());
        hideMultiMailAddrListBlockUI_maiBtn.setEnabled(false);
        hideMultiMailAddrListBlockUI_mailGrid.setReadOnly(!isCanEdit());
    }

    /**
     * 概略
     * <p>イベント設定。</p>
     */
    @Override
    protected void setEvents(){
        super.setEvents();

        //メールボタンのイベント。
        hideMultiMailAddrListBlockUI_maiBtn.click.addHandler(new SSClickHandler() {
            @Override
            public void onClick(Object sender, ClickEvent event) {
                onMailButtonClick();
            }
        });
    }

    /**
     * 行を追加する。
     */
    @Override
    protected void doAddRow(){

        AisycDataGrid dataGrid = getDataGrid();
        AisycDataGridRowData rowData = dataGrid.insertRowData();

        rowData.setCellValue(MultiMailAddsDataGridColumnConst.COLUMN_DEFINE.MAIL_ADDR, "");
        rowData.setCellValue(MultiMailAddsDataGridColumnConst.COLUMN_DEFINE.REMARK, "");
    }

    /**
     * MailButtonClick イベントを発生させます。
     */
    private void onMailButtonClick(){

        AisycDataGridRowData rowData = getDataGrid().getSelectedItem();
        if (rowData != null) {
            // メールアドレスを取得する
            String mailAddr = ConvertUtil.toString(rowData.getCellValue(MultiMailAddsDataGridColumnConst.COLUMN_DEFINE.MAIL_ADDR));
            // EventArgsを生成
            MailEventArgs args = new MailEventArgs();
            args.setToAddr(mailAddr);
            // クリックイベントを実行する
            mailButtonClickEvent.invoke(args);
        }
    }

    /**
     * 概略
     * <p>各種リクエスト制御をデフォルト設定に戻します。</p>
     */
    public void restoreDefaultRequestEnabled() {
        this.getLayoutRoot().setDispRequestEnabled(true);
        this.getLayoutRoot().setSearchRequestEnabled(true);
        this.getLayoutRoot().setUpdateRequestEnabled(true);
        this.getLayoutRoot().setUpdateCheckOnlyRequestEnabled(false);
        this.getLayoutRoot().setDeleteRequestEnabled(true);
        this.getLayoutRoot().setDispResultDeployMode(EDataResultDeployMode.FULL);
        this.getLayoutRoot().setSearchResultDeployMode(EDataResultDeployMode.FULL);
        this.getLayoutRoot().setUpdateResultDeployMode(EDataResultDeployMode.FULL);
        this.getLayoutRoot().setDeleteResultDeployMode(EDataResultDeployMode.FULL);
    }
    public void initializeSrchReqCompletedProcess(){
    	hideMultiMailAddrListBlockUI_maiBtn.setEnabled(hideMultiMailAddrListBlockUI_mailGrid.getRowDataCount() > 0 ? true :false);
    }
}
