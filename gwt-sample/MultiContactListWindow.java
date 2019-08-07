package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.xml.client.Document;

import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.AisycDataGrid;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.model.AisycDataGridRowData;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.common.events.MailEvent;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.common.events.MailEventArgs;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.common.events.MailEventHandler;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.common.events.PhoneEvent;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.common.events.PhoneEventArgs;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.common.events.PhoneEventHandler;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.constants.MultiContactListConst;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.factories.HideMultiMailAddrListBlockFactory;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.factories.HideMultiOptionListBlockFactory;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.factories.HideMultiTelNumListBlockFactory;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.value.MultiContactListTabValue;
import carapace.web2.gwt.client.basic.common.constants.MessageConst;
import carapace.web2.gwt.client.basic.common.dialog.AlertDialog;
import carapace.web2.gwt.client.basic.common.events.CancelEventArgs;
import carapace.web2.gwt.client.basic.common.events.ExtEvent;
import carapace.web2.gwt.client.basic.common.events.ExtEventArgs;
import carapace.web2.gwt.client.basic.common.events.ExtEventHandler;
import carapace.web2.gwt.client.basic.common.events.SSClickHandler;
import carapace.web2.gwt.client.basic.common.events.SSCloseHandler;
import carapace.web2.gwt.client.basic.common.events.ShowBusyIndicatorEventHandler;
import carapace.web2.gwt.client.basic.common.events.ShowBusyIndicatorEventHandlerArgs;
import carapace.web2.gwt.client.basic.common.events.core.SSLogicalEventHandler;
import carapace.web2.gwt.client.basic.common.util.inst.delegates.IFuncWithArgs0;
import carapace.web2.gwt.client.basic.common.util.statical.ConvertUtil;
import carapace.web2.gwt.client.basic.common.util.statical.StringUtil;
import carapace.web2.gwt.client.basic.common.value.CpWordProperties;
import carapace.web2.gwt.client.basic.common.widgets.CpTabItem;
import carapace.web2.gwt.client.basic.common.widgets.ExtTabListItemGBS3;

public class MultiContactListWindow extends MultiContactListWindowUI  {

    private static final HideMultiTelNumListBlockFactory hideMultiTelNumListBlockFactory = GWT.create(HideMultiTelNumListBlockFactory.class);
    private static final HideMultiMailAddrListBlockFactory hideMultiMailAddrListBlockFactory = GWT.create(HideMultiMailAddrListBlockFactory.class);
    private static final HideMultiOptionListBlockFactory hideMultiOptionListBlockFactory = GWT.create(HideMultiOptionListBlockFactory.class);

    private final String DIALOG_TITLE = "caracrm.fixword.subwindow.multi_contact_list.title";
    /** ダイアログのタイトル  */
    protected String getWindowTitle(){
        return CpWordProperties.getGlobalWordProperty(DIALOG_TITLE);
    };

    /**************************************************************************************************
    * 定数、変数宣言、コンストラクタ
    *************************************************************************************************/

    private String aspId;
    private String cid;

    /** 受付履歴ID */
    private String receptHistId;

    /** 対応履歴SEQ */
    private String supportHistSeq;

    /** 編集中がある。 */
    private boolean hasEditingData = false;

    /** タブ情報一覧 */
    private List<MultiContactListTabValue> tabInfoList;

    /** 部門情報一覧 */
    private List<AisycDataGridRowData> deptList;

//    /// <summary>
//    /// 更新成功用イベント
//    /// </summary>
//    public event EventHandler<ExtensionEventArgs<XDocument>> updateMultContactListEvent;
//
//    /// <summary>
//    /// LayeredPopupArea
//    /// </summary>
//    private LayeredPopupArea layeredPopupArea;

//    private List<AisycDataGridRowData> deptList;
//    private List<AisycDataGridRowData> tabInfoList;

    /** 電話発信ボタン押下イベント */
    public final PhoneEvent phoneConfirmEvent = new PhoneEvent(this);
    private PhoneEventHandler phoneConfirmEventHandler = new PhoneEventHandler() {
        @Override
        public void invoke(Object sender, PhoneEventArgs args) {
            phoneConfirmEvent.invoke(args);
        }
    };

    /** メールボタン押下イベント */
    public final MailEvent mailButtonClickEvent = new MailEvent(this);
    private MailEventHandler mailButtonClickEventHandler = new MailEventHandler() {
        @Override
        public void invoke(Object sender, MailEventArgs args) {
            mailButtonClickEvent.invoke(args);
        }
    };

    /** 処理中画面の制御ハンドラー。*/
    private ShowBusyIndicatorEventHandler showBusyHandler = new ShowBusyIndicatorEventHandler() {

        @Override
        public void invoke(Object sender, ShowBusyIndicatorEventHandlerArgs args) {
            if(args.isShowhide()){
                extDialogBoxGWT.busy();
            }else{
                extDialogBoxGWT.free();
            }
        }
    };

    /** 更新成功用イベント */
    public final ExtEvent<Document> updateMultContactListEvent = new ExtEvent<Document>(this);

    /**
     * ブロックマップ
     * <pre>
     * キー：顧客特定区分
     * 値　：顧客特定区分に対応するブロックインスタンス
     * </pre>
     */
    private Map<String, HideMultiCommonBlockBase> blockMap = new HashMap<String, HideMultiCommonBlockBase>();

    /**
     * コンストラクタ
     */
    public MultiContactListWindow(
            String paramAspId,
            String paramCid,
            String receptHistId,
            String supportHistSeq,
            List<MultiContactListTabValue> tabInfoList,
            List<AisycDataGridRowData> deptList){

        super();

        this.aspId = paramAspId;
        this.cid = paramCid;
        this.receptHistId = receptHistId;
        this.supportHistSeq = supportHistSeq;
        this.tabInfoList = tabInfoList;
        this.deptList = deptList;

        constructorLastProcess(MultiContactListWindow.class);
    }

    /**
     * 概略
     * <p>概要・説明</p>
     * <pre>
     * 【改版履歴】
     * 2016/06/21 693-toan
     * </pre>
     *
     * @see com.google.gwt.user.client.ui.Widget#onLoad()
     */
    @Override
    protected void onLoad() {
        super.onLoad();

//        multContactListWindowLoaded();
    }

    /**
     * 概略
     * <p>顧客特定区分に該当するデータグリッドを取得します。</p>
     *
     * @param custIdentifyKbn 顧客特定区分
     * @return データグリッド
     */
    public AisycDataGrid getDataGrid(String custIdentifyKbn) {
        HideMultiCommonBlockBase block = this.blockMap.get(custIdentifyKbn);
        return block != null ? block.getDataGrid() : null;
    }

    /**
     * 概略
     * <p>画面の初期化する。タブ情報を作成する。</p>
     * <pre>
     * 【改版履歴】
     * 2016/07/01 693-toan
     * 2017/12/14 661-kimura #1003823 #1003820 #1003824 #1003821
     * </pre>
     *
     */
    protected void initProcessBeforeSetEvents(){
        super.initProcessBeforeSetEvents();

        String firstTabId = null;

        //このタイミングでブロックが生成されるので、この中でタブの表示設定、グリッドのReadOnlyフラグの設定、ページングの設定を行う。
        for(MultiContactListTabValue tabInfo : this.tabInfoList){

            //表示フラグがtrueの場合のみ、表示する。
            boolean dispFlg = ConvertUtil.strToBoolean(tabInfo.getMultiContactDispFlg());
            if (!dispFlg){
                continue;
            }
            String tabKbn = tabInfo.getKbn();
            String tabKbnName = tabInfo.getKbnNameDisp();
            CpTabItem tabItem = null;
            HideMultiCommonBlockBase block = null;

            if(StringUtil.equals(tabKbn, MultiContactListConst.MULTTEL_KBN)){
                block = hideMultiTelNumListBlockFactory.create(this.aspId, this.cid);

                block.setTabInfo(tabInfo);

                tabItem = createTabItem(tabKbnName, block);
                setTabItemEvents(block);

                //発信ボタンのイベントを連動する。
                ((HideMultiTelNumListBlock)block).phoneConfirmEvent.addHandler(phoneConfirmEventHandler);
                block.showBusyEvent.addHandler(showBusyHandler);

                this.blockMap.put(tabKbn, block);
            }else if(StringUtil.equals(tabKbn, MultiContactListConst.MAILADDS_INFO_KBN)){
                block = hideMultiMailAddrListBlockFactory.create(this.aspId, this.cid);
                block.setTabInfo(tabInfo);

                tabItem = createTabItem(tabKbnName, block);
                setTabItemEvents(block);

                //メールボタンのイベントを連動する。
                ((HideMultiMailAddrListBlock )block).mailButtonClickEvent.addHandler(mailButtonClickEventHandler);
                block.showBusyEvent.addHandler(showBusyHandler);

                this.blockMap.put(tabKbn, block);
            }else{
                block = hideMultiOptionListBlockFactory.create(this.aspId, this.cid);
                block.setTabInfo(tabInfo);

                tabItem = createTabItem(tabKbnName, block);
                setTabItemEvents(block);
                block.showBusyEvent.addHandler(showBusyHandler);

                this.blockMap.put(tabKbn, block);
            }

            //初期選択タブを設定する。
            if (StringUtil.isNullOrEmpty(firstTabId) && tabItem != null) {
                firstTabId = tabItem.getTabId();
            }

            //TODO グリッドのReadOnlyフラグを設定する。。
            if (ConvertUtil.strToBoolean(tabInfo.getCanEditFlg())){
                //block.getDataGrid().setReadOnly(true)
            }else{
                //block.getDataGrid().setReadOnly(false)
            }

            //タブアイテムを追加する。
            if (tabItem != null){
                multiContactListWindowUI_mainTabCtrl.addTabItem(tabItem);
            }

        }
        //デフォルト・アクティブ。
        if (!StringUtil.isNullOrEmpty(firstTabId)){
            multiContactListWindowUI_mainTabCtrl.setActive(firstTabId);
        }
    }

    /**
     * 概略
     * <p>タブ作成。</p>
     * <pre>
     * 【改版履歴】
     * 2016/07/01 693-toan
     * </pre>
     *
     * @param tabKbnName：タブ名
     * @param block：アブ内容
     * @return　タブアイテム
     */
    private CpTabItem createTabItem(String tabKbnName, HideMultiCommonBlockBase block){

        final CpTabItem tabItem = new CpTabItem(tabKbnName, block);
        block.hasDataChangedEvent.addHandler(new ExtEventHandler<Boolean>() {

            @Override
            public void invoke(Object sender, ExtEventArgs<Boolean> args) {
                Boolean changedFlg = args.getValue();
                if(changedFlg != null && changedFlg.booleanValue()){
                    tabItem.setChangedMark(true);
                }else{
                    tabItem.setChangedMark(false);
                }
            }
        });

        ExtTabListItemGBS3 tabListItem = tabItem.getTabListItem();
        tabListItem.showing.addHandler(new SSLogicalEventHandler<CancelEventArgs>() {
            @Override
            public void invoke(Object sender, CancelEventArgs args) {
                if (!args.isCancelable()) return;

                if (hasEditingData) {
                    args.setCancel(true);
                    AlertDialog dialog = new AlertDialog(MessageConst.TAB_SWITCHING_ALERT_FOR_EDITING);
                    dialog.show();
                }
            }
        });

        return tabItem;
    }

    /**
     * 概略
     * <p>ブロックのイベントを連動する。</p>
     * <pre>
     * 【改版履歴】
     * 2016/07/01 693-toan
     * </pre>
     *
     * @param block
     */
    private void setTabItemEvents(HideMultiCommonBlockBase block){

        //編集中のイベント。
        block.hasDataChangedEvent.addHandler(new ExtEventHandler<Boolean>() {

            @Override
            public void invoke(Object sender, ExtEventArgs<Boolean> args) {
                Boolean changedFlg = args.getValue();
                if(changedFlg != null && changedFlg.booleanValue()){
                    hasEditingData = true;
                }else{
                    hasEditingData = false;
                }
            }
        });

        // 連絡先一覧更新後の再描画情報セットイベント
        block.updateMultContactListEvent.addHandler(new ExtEventHandler<Document>() {
            @Override
            public void invoke(Object sender, ExtEventArgs<Document> args) {
                updateMultContactListEvent.invoke(args);
            }
        });

    }

    /**
     * イベントの設定。
     */
    protected void setEvents(){
        super.setEvents();

        //閉じるボタンのクリックイベント。
        multiContactListWindowUI_closelBtn.click.addHandler(new SSClickHandler() {
            @Override
            public void onClick(Object sender, ClickEvent event) {
                doCloseByOK();
            }
        });
        extDialogBoxGWT.close.addHandler(new SSCloseHandler<PopupPanel>() {
            @Override
            public void onClose(Object sender, CloseEvent<PopupPanel> event) {
            }
        });

        //編集中の内容があれば、クローズ前に確認する仕組みを仕掛ける。
        IFuncWithArgs0<Boolean> confirmCondition = new IFuncWithArgs0<Boolean>() {
            @Override
            public Boolean invoke() {
                return new Boolean(hasEditingData);
            }
        };
        String message = CpWordProperties.getGlobalWordProperty("caracrm.message.confirm.discard_edited_content");
        setConfirmDialogWhenClosing(confirmCondition, message, this.multiContactListWindowUI_closelBtn);
    }

    /**
     * 概略
     * <p>画面の表示開始</p>
     *
     */
    public void show(){
        extDialogBoxGWT.showWithBusy();
        extDialogBoxGWT.free();
    }
}
