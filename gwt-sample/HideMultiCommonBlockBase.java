/*
 * $Header$
 * $Revision$
 * $Date$
 */
package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.events.SetResultCompletedEventArgs;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.message.value.ResultMessageValue;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.widgetlogic.AutomationStory;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.widgetlogic.DomconfPattern;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.widgetlogic.EAutomationStoryEntryType;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.widgetlogic.EAutomationStoryStatus;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.widgetlogic.IRequestEnable;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.widgetlogic.PostDispRequestStoryEntry;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.widgetlogic.PostDomconfRequestStoryEntry;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.widgetlogic.PostSearchRequestStoryEntry;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.widgetlogic.PostUpdateRequestStoryEntry;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.xml.ConditionXPartsBuilder;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.xml.CreateConditionXPartsProcessArgs;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.xml.ICreateConditionXPartsProcess;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.widgets.AisycContainer;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.widgets.AisycPanelCore;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.widgets.IAisycPanel;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.events.AisycDataGridCellValueChangedEventArgs;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.AisycDataGrid;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.model.AisycDataGridColumnInfo;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.model.AisycDataGridRowData;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.automation.specific.constants.MultiMailAddsDataGridColumnConst;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.automation.specific.constants.MultiOptionDataGridColumnConst;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.automation.specific.constants.MultiTelNumDataGridColumnConst;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.constants.MultiContactListConst;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.value.MultiContactListTabValue;
import carapace.web2.gwt.client.basic.aisyc.common.widgets.AisycBlockBase;
import carapace.web2.gwt.client.basic.common.constants.SystemConst;
import carapace.web2.gwt.client.basic.common.dialog.ConfirmClosedArgs;
import carapace.web2.gwt.client.basic.common.dialog.ConfirmDialog;
import carapace.web2.gwt.client.basic.common.enums.EAppOperatingStatus;
import carapace.web2.gwt.client.basic.common.enums.EConfirmResult;
import carapace.web2.gwt.client.basic.common.events.ErrorCatchedEventArgs;
import carapace.web2.gwt.client.basic.common.events.ExtEvent;
import carapace.web2.gwt.client.basic.common.events.ExtEventArgs;
import carapace.web2.gwt.client.basic.common.events.ProcessCompletedEventArgs;
import carapace.web2.gwt.client.basic.common.events.SSClickHandler;
import carapace.web2.gwt.client.basic.common.events.ShowBusyIndicatorEvent;
import carapace.web2.gwt.client.basic.common.events.ShowBusyIndicatorEventHandlerArgs;
import carapace.web2.gwt.client.basic.common.events.core.LogicalEventArgs;
import carapace.web2.gwt.client.basic.common.events.core.SSLogicalEventHandler;
import carapace.web2.gwt.client.basic.common.util.inst.ValueWrapper;
import carapace.web2.gwt.client.basic.common.util.inst.delegates.IActionWithArgs1;
import carapace.web2.gwt.client.basic.common.util.statical.ConvertUtil;
import carapace.web2.gwt.client.basic.common.util.statical.StringUtil;
import carapace.web2.gwt.client.basic.common.value.CpWordProperties;
import carapace.web2.gwt.client.basic.common.widgets.CpTabItem.ICpTabItemContent;
import carapace.web2.gwt.client.basic.common.widgets.ExtButtonGBS3;

/**
 * 概略
 * <p>複数連絡先ブロックの共通部分</p>
 *
 * @author $Author$
 * @version $Revision$ $Date$
 */
public abstract class HideMultiCommonBlockBase extends AisycBlockBase<AisycContainer> implements ICpTabItemContent{

    /**************************************************************************************************
    * 定数、変数宣言、コンストラクタ
    *************************************************************************************************/

    /** ASPID */
    protected String aspId;
    /** CID */
    protected String cid;

    /** ロードフラグ */
    private boolean isLoaded = false;

    /** タブの設定情報 */
    protected MultiContactListTabValue tabInfo;

    /** 編集データありイベント　*/
    public final ExtEvent<Boolean> hasDataChangedEvent = new ExtEvent<>(this);

    /** 処理中画面制御イベント　*/
    public final ShowBusyIndicatorEvent showBusyEvent = new ShowBusyIndicatorEvent(this);

    /** 更新成功用イベント */
    public final ExtEvent<Document> updateMultContactListEvent = new ExtEvent<>(this);

    /**
     * 概略
     * <p>イベント設定。</p>
     */
    protected void setEvents(){
        super.setEvents();
        //行追加ボタン
        getInsertButton().click.addHandler( new SSClickHandler() {
            @Override
            public void onClick(Object sender, ClickEvent event) {
                doAddRow();
            }
        });

        //行削除ボタン
        getDeleteButton().click.addHandler( new SSClickHandler() {
            @Override
            public void onClick(Object sender, ClickEvent event) {
                doDeleleRow();
            }
        });

        //保存ボタン
        getSaveButton().click.addHandler( new SSClickHandler() {
            @Override
            public void onClick(Object sender, ClickEvent event) {
                multiTelUpdate(sender, event);
            }
        });

        //UPボタン
        getUpButton().click.addHandler( new SSClickHandler() {
            @Override
            public void onClick(Object sender, ClickEvent event) {
                moveRowDown();
                onHasDataChangedEvent(true);
            }
        });

        //ダウンボタン
        getDownButton().click.addHandler( new SSClickHandler() {
            @Override
            public void onClick(Object sender, ClickEvent event) {
                moveRowUp();
                onHasDataChangedEvent(true);
            }
        });

        SSLogicalEventHandler<SetResultCompletedEventArgs> dg_setDispDataResultCompletedHandler = null;
        dg_setDispDataResultCompletedHandler = new SSLogicalEventHandler<SetResultCompletedEventArgs>() {

            @Override
            public void invoke(Object sender, SetResultCompletedEventArgs args) {
                //getDataGrid().getColumn(columnName)
            }
        };
        getDataGrid().setDispDataResultCompleted.addHandler(dg_setDispDataResultCompletedHandler);

        //データグリッド変更したイベント。
        getDataGrid().dataChanged.addHandler( new SSLogicalEventHandler<LogicalEventArgs>() {
            @Override
            public void invoke(Object sender, LogicalEventArgs args) {
                onHasDataChangedEvent(true);
            }
        });

        // データグリッドのCELLチェンジイベント
        getDataGrid().cellValueChanged.addHandler( new SSLogicalEventHandler<AisycDataGridCellValueChangedEventArgs>(){
            @Override
            public void invoke(Object sender, AisycDataGridCellValueChangedEventArgs args) {
                // 検索用データ設定処理
                setSearchData(args);
            }
        });

    }

    /**
     * 概略
     * <p>ブロック初期化。</p>
     */
    protected void doInit(){

        if(isLoaded == false){
            initFromTabInfo();
            doSearch();
        }
        isLoaded = true;
    }

    /**
     * 概略
     * <p>ブロック再表示。</p>
     */
    protected void doReShow(){

        AisycDataGrid grid = getDataGrid();
        grid.redraw();
    }

    /**
     * 設定タブ情報からブロックに反映する.
     */
    protected void initFromTabInfo(){

        String paging = this.tabInfo.getPaging();

        int pageSize = SystemConst.GRID_DISP_ROW_CNT_DEFAULT;
        ValueWrapper<Integer> val = new ValueWrapper<>();
        if(ConvertUtil.tryParseInteger(paging, val)){
            pageSize = val.getValue();
        }
        if (getDataGrid().getTargetPager() != null){
            if (pageSize > 0){
                //ページサイズの設定。
                getDataGrid().getTargetPager().setPageSize(pageSize);
            }else if(pageSize == 0){
                //ページングをさせない。
                getDataGrid().getTargetPager().setVisible(false);
                getDataGrid().getTargetPager().setPageSize(Integer.MAX_VALUE);
            }else{
                //ページサイズの設定。
                getDataGrid().getTargetPager().setPageSize(SystemConst.GRID_DISP_ROW_CNT_DEFAULT);
            }
        }

        if(!StringUtil.equals(tabInfo.getKbn(), MultiContactListConst.MULTTEL_KBN) &&
                !StringUtil.equals(tabInfo.getKbn(), MultiContactListConst.MAILADDS_INFO_KBN)){

            getDataGrid().setEmbedPoint(tabInfo.getEmbedPoint());
        }

        //ボタンの制御。
        boolean isEditOk = isCanEdit();
        getInsertButton().setEnabled(isEditOk);
        getInsertButton().setVisible(isEditOk);

        getSaveButton().setEnabled(isEditOk);
        getSaveButton().setVisible(isEditOk);

        getDeleteButton().setEnabled(isEditOk);
        getDeleteButton().setVisible(isEditOk);

        getUpButton().setEnabled(isEditOk);
        getUpButton().setVisible(isEditOk);

        getDownButton().setEnabled(isEditOk);
        getDownButton().setVisible(isEditOk);

    }

    /**
     * 変更可否の判定
     */
    protected boolean isCanEdit(){

        boolean retVal = false;
        retVal = StringUtil.equals(tabInfo.getCanEditFlg(), MultiContactListConst.CANEDITFLG_YES);
        return retVal;
    }
    /**
     * 検索処理.
     */
    private void doSearch(){
        AutomationStory story = getInitializeStoryInfo();
        AisycPanelCore core = this.getLayoutRoot().getPanelCore();
//        if (this.layeredPopupArea != null)
//        {
//            core.SetMessageLayer(this.layeredPopupArea.GetMessageLayer());
//            core.SetBusyLayer(this.layeredPopupArea.GetBusyLayer());
//        }
        core.automationExecute(story);
    }

    /// <summary>
    /// 初期表示処理時の AutomationStory を取得
    /// </summary>
    /// <returns>AutomationStory</returns>
    private AutomationStory getInitializeStoryInfo(){
        DomconfPattern comconfPtn = DomconfPattern.DEFAULT;
        AutomationStory story = new AutomationStory(this.aspId);

        //ドメインコンフィグリクエスト
        PostDomconfRequestStoryEntry dcReqEntry = (PostDomconfRequestStoryEntry)story.addNewEntry(EAutomationStoryEntryType.DOMCONF_REQUEST);
        dcReqEntry.init(this.getLayoutRoot(), comconfPtn);
        dcReqEntry.errorCatched.addHandler( new SSLogicalEventHandler<ErrorCatchedEventArgs>() {
            @Override
            public void invoke(Object sender, ErrorCatchedEventArgs args) {
                addResultMessageDocument((Document)args.getErrorContents(), args.getAppOperatingStatus());
            }
        });

        //表示項目リクエスト
        PostDispRequestStoryEntry dispReqEntry = (PostDispRequestStoryEntry)story.addNewEntry(EAutomationStoryEntryType.DISP_REQUEST);
        dispReqEntry.init(this.getLayoutRoot());
        dispReqEntry.setDomconfPattern(comconfPtn);
        dispReqEntry.errorCatched.addHandler( new SSLogicalEventHandler<ErrorCatchedEventArgs>() {

            @Override
            public void invoke(Object sender, ErrorCatchedEventArgs args) {
                addResultMessageDocument((Document)args.getErrorContents(), args.getAppOperatingStatus());
            }
        });

        //検索リクエスト
        PostSearchRequestStoryEntry srchReqEntry = (PostSearchRequestStoryEntry)story.addNewEntry(EAutomationStoryEntryType.SEARCH_REQUEST);
        srchReqEntry.init(this.getLayoutRoot(), new ICreateConditionXPartsProcess() {
            @Override
            public Element invoke(CreateConditionXPartsProcessArgs e) {
                return createConditionXParts(e);
            }
        });
        srchReqEntry.setDomconfPattern(comconfPtn);
        srchReqEntry.completed.addHandler( new SSLogicalEventHandler<ProcessCompletedEventArgs>() {

            @Override
            public void invoke(Object sender, ProcessCompletedEventArgs args) {
                initializeSrchReqCompletedProcess(sender, args);
                initializeSrchReqCompletedProcessTel();
                initializeSrchReqCompletedProcess();

            }
        });
        srchReqEntry.errorCatched.addHandler( new SSLogicalEventHandler<ErrorCatchedEventArgs>() {

            @Override
            public void invoke(Object sender, ErrorCatchedEventArgs args) {
                addResultMessageDocument((Document)args.getErrorContents(), args.getAppOperatingStatus());
            }
        });
        story.finished.addHandler( new SSLogicalEventHandler<LogicalEventArgs>() {

            @Override
            public void invoke(Object sender, LogicalEventArgs args) {
                automationStoryFinishedProcess(sender, args);
            }
        });
        return story;
    }

    /**
     * 概略
     * <p>検索条件情報を生成する</p>
     * @param e
     * @return
     */
    private Element createConditionXParts(CreateConditionXPartsProcessArgs e){
        ConditionXPartsBuilder builder = new ConditionXPartsBuilder(e);
        builder.append("param_asp_id", this.aspId);
        builder.append("param_cid", this.cid);
        return builder.toElement();
    }

    /**
     *
     * 概略
     * <p>リクエスト結果メッセージド追加処理</p>
     * @param msgDoc リクエスト結果メッセージ
     * @param appOperatingStatus アプリケーション稼動状態
     */
    private void addResultMessageDocument(Document msgDoc, EAppOperatingStatus appOperatingStatus){
        ResultMessageValue value = new ResultMessageValue(appOperatingStatus);
        value.setMessageDocument(msgDoc);
        this.getLayoutRoot().getPanelCore().getResultMessageList().add(value);
    }

    /**
     *
     * 概略
     * <p>初期表示処理時の検索リクエスト完了イベントハンドラ</p>
     * <pre>
     * 【改版履歴】
     * 2016/06/30 693-toan
     * </pre>
     *
     * @param sender 発生元
     * @param e 引数
     */
    private void initializeSrchReqCompletedProcess(Object sender, ProcessCompletedEventArgs e){
    }
    public void initializeSrchReqCompletedProcessTel(){

    }
    public void initializeSrchReqCompletedProcess() { }

    /**
     *
     * 概略
     * <p>AutomationStory 終了イベントハンドラ</p>
     * @param sender 発生元
     * @param args 引数
     */
    private void automationStoryFinishedProcess(Object sender, LogicalEventArgs args){
        AisycPanelCore core = this.getLayoutRoot().getPanelCore();
        if (core.getResultMessageList().size() > 0){
            core.showResultMessage();
        }

        IAisycPanel panel = null;
        IRequestEnable requestEnable = null;

        panel = this.getLayoutRoot();
        requestEnable = (IRequestEnable)panel;
        requestEnable.setSearchRequestEnabled(true);
    }

    /**************************************************************************************************
     * 更新　ボタンロジック関連　<電話番号一覧>
     *************************************************************************************************/
    /**
     *
     * 概略
     * <p>更新ボタン押下処理</p>
     * @param sender
     * @param e
     */
    protected void multiTelUpdate(Object sender, ClickEvent e){

        IActionWithArgs1<ConfirmClosedArgs> confirmAction = new IActionWithArgs1<ConfirmClosedArgs>() {

            @Override
            public void invoke(ConfirmClosedArgs args) {
                multTelUpdateDataExecute(args);
            }
        };
        ConfirmDialog confirm = new ConfirmDialog(CpWordProperties.getGlobalWordProperty("caracrm.message.confirm.save_info"), e, confirmAction, (Focusable)sender);
        confirm.showRelativeTo((UIObject)sender);
    }

    /**
     *
     * 概略
     * <p>更新ボタン押下の確認後の処理</p>
     * @param eventArgs
     */
    private void multTelUpdateDataExecute(ConfirmClosedArgs eventArgs){

        if (eventArgs.getConfirmResult() == EConfirmResult.OK){

            //処理中開始。
            showBusyEvent.invoke(new ShowBusyIndicatorEventHandlerArgs(true));

            @SuppressWarnings("unchecked")
            List<AisycDataGridRowData> orderList = (List<AisycDataGridRowData>)getDataGrid().getRowData();
            int dispOrder = 1;
            for(AisycDataGridRowData element : orderList){
                if(element.isDelete()){
                    continue;
                }
                element.setCellValue(MultiOptionDataGridColumnConst.COLUMN_DEFINE.DISP_ORDER, dispOrder + "");
                dispOrder++;
            }

            // 複数電話番号一覧 更新処理
            AutomationStory updateStory = getTelUpdateStoryInfo();
            updateStory.finished.addHandler(new SSLogicalEventHandler<LogicalEventArgs>() {
                @Override
                public void invoke(Object sender, LogicalEventArgs args) {
                    //処理中解除。
                    showBusyEvent.invoke(new ShowBusyIndicatorEventHandlerArgs(false));
                }
            });
            AisycPanelCore core = this.getLayoutRoot().getPanelCore();
            core.automationExecute(updateStory);
        }
    }

    /**
     *
     * 概略
     * <p>一覧の更新時の AutomationStory を取得</p>
     * <pre>
     * 【改版履歴】
     * 2016/06/30 693-toan
     * </pre>
     *
     * @return 一覧の更新の自動化情報
     */
    private AutomationStory getTelUpdateStoryInfo(){
        DomconfPattern comconfPtn = DomconfPattern.DEFAULT;
        AutomationStory story = new AutomationStory(this.aspId);
        AisycPanelCore core = this.getLayoutRoot().getPanelCore();

        //更新リクエスト
        PostUpdateRequestStoryEntry updReqEntry = (PostUpdateRequestStoryEntry)story.addNewEntry(EAutomationStoryEntryType.UPDATE_REQUEST);
        updReqEntry.init(this.getLayoutRoot(),  new ICreateConditionXPartsProcess() {
            @Override
            public Element invoke(CreateConditionXPartsProcessArgs e) {
                return createConditionXParts(e);
            }
        }, core.getSearchResultDocument());
        updReqEntry.setDomconfPattern(comconfPtn);
        updReqEntry.errorCatched.addHandler(new SSLogicalEventHandler<ErrorCatchedEventArgs>() {

            @Override
            public void invoke(Object sender, ErrorCatchedEventArgs args) {
                addResultMessageDocument((Document)args.getErrorContents(), args.getAppOperatingStatus());
            }
        });
        updReqEntry.completed.addHandler( new SSLogicalEventHandler<ProcessCompletedEventArgs>() {

            @Override
            public void invoke(Object sender, ProcessCompletedEventArgs args) {
                addResultMessageDocument((Document)args.getResult(), args.getAppOperatingStatus());
            }
        });

        //再検索処理
        //検索リクエスト
        PostSearchRequestStoryEntry srchReqEntry = (PostSearchRequestStoryEntry)story.addNewEntry(EAutomationStoryEntryType.SEARCH_REQUEST);
        srchReqEntry.init(this.getLayoutRoot(),  new ICreateConditionXPartsProcess() {
            @Override
            public Element invoke(CreateConditionXPartsProcessArgs e) {
                return createConditionXParts(e);
            }
        });
        srchReqEntry.setDomconfPattern(comconfPtn);
        srchReqEntry.completed.addHandler(new SSLogicalEventHandler<ProcessCompletedEventArgs>() {

            @Override
            public void invoke(Object sender, ProcessCompletedEventArgs args) {
                initializeSrchReqCompletedProcess(sender, args);
            }
        });
        srchReqEntry.errorCatched.addHandler( new SSLogicalEventHandler<ErrorCatchedEventArgs>() {

            @Override
            public void invoke(Object sender, ErrorCatchedEventArgs args) {
                addResultMessageDocument((Document)args.getErrorContents(), args.getAppOperatingStatus());
            }
        });

        story.finished.addHandler( new SSLogicalEventHandler<LogicalEventArgs>() {

            @Override
            public void invoke(Object sender, LogicalEventArgs args) {
                multTelUpdateStoryFinishedProcess(sender, args);;
            }
        });

        return story;
    }

    /**
     *
     * 概略
     * <p>電話一覧AutomationStory 終了イベントハンドラ</p>
     * @param sender 発生元
     * @param args 引数
     */
    private void multTelUpdateStoryFinishedProcess(Object sender, LogicalEventArgs args){

        // メッセージを表示する
        showResultMessage();

        IAisycPanel panel = null;
        IRequestEnable requestEnable = null;

        panel = this.getLayoutRoot();
        requestEnable = (IRequestEnable)panel;
        requestEnable.setSearchRequestEnabled(true);

        Document dbVal = this.getLayoutRoot().getPanelCore().getSearchResultDocument();
        onUpdateMultContactListEvent(new ExtEventArgs<Document>(dbVal, sender, args));

        //メッセージなしの場合、編集中なしを通知する。
        AutomationStory story = (AutomationStory)sender;
        if(story.getStatus() == EAutomationStoryStatus.SUCCESS){
            onHasDataChangedEvent(false);
        }
    }

    /**
     *
     * 概略
     * <p>リクエスト結果メッセージを表示します。</p>
     * @return true:メッセージあり、false:メッセージなし
     *
     */
    protected void showResultMessage(){
        AisycPanelCore core = this.getLayoutRoot().getPanelCore();
        if (core.getResultMessageList().size() > 0){
            core.showResultMessage();
        }
    }

    /**
     * 選択中の行を下に移動する。
     */
    protected void moveRowDown(){

        AisycDataGrid dataGrid = getDataGrid();
        dataGrid.moveRowDown();
    }
    /**
     * 選択中の行を上に移動する。
     */
    protected void moveRowUp(){

        AisycDataGrid dataGrid = getDataGrid();
        dataGrid.moveRowUp();
    }

    /**
     * 行を追加する。
     */
    protected void doAddRow(){

        AisycDataGrid dataGrid = getDataGrid();
        AisycDataGridRowData rowData = dataGrid.insertRowData();

        rowData.setCellValue(MultiOptionDataGridColumnConst.COLUMN_DEFINE.OPTION_NAME, "");
        rowData.setCellValue(MultiOptionDataGridColumnConst.COLUMN_DEFINE.REMARK, "");
    }

    /**
     * 選択中の行を削除する。
     */
    protected void doDeleleRow(){

        AisycDataGrid dataGrid = getDataGrid();
        if(0 <= dataGrid.getSelectedIndex()){
            dataGrid.deleteRowData();
        }
    }

    /**
     * 編集中の通知。
     * @param changed
     */
    protected void onHasDataChangedEvent(boolean changed){
        hasDataChangedEvent.invoke(new ExtEventArgs<Boolean>(changed));
    }

    public void setAspId(String aspId) {
        this.aspId = aspId;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public MultiContactListTabValue getTabInfo() {
        return tabInfo;
    }

    public void setTabInfo(MultiContactListTabValue tabInfo) {
        this.tabInfo = tabInfo;
    }

    /** データ一覧のコントロール */
    protected abstract AisycDataGrid getDataGrid();

    /** 行追加ボタン */
    protected abstract ExtButtonGBS3 getInsertButton();
    /** 保存ボタン */
    protected abstract ExtButtonGBS3 getSaveButton();
    /** 行削除ボタン */
    protected abstract ExtButtonGBS3 getDeleteButton();
    /** 行移動（上）ボタン */
    protected abstract ExtButtonGBS3 getUpButton();
    /** 行移動（下）ボタン */
    protected abstract ExtButtonGBS3 getDownButton();

    /**
     * 概略
     * <p>概要・説明</p>
     * <pre>
     * 【改版履歴】
     * 2016/06/30 693-toan
     * </pre>
     *
     * @see carapace.web2.gwt.client.basic.common.widgets.CpTabItem.ICpTabItemContent#onInit()
     */
    public void onInitShow() {
        doInit();
    }

    /**
     * 概略
     * <p>概要・説明</p>
     * <pre>
     * 【改版履歴】
     * 2016/06/30 693-toan
     * </pre>
     *
     * @see carapace.web2.gwt.client.basic.common.widgets.CpTabItem.ICpTabItemContent#onReShow()
     */
    public void onReShow() {
        doReShow();
    }

    /**
    *
    * 概略
    * <p>複数連絡先一覧更新成功用イベント</p>
    * <pre>
    * 【改版履歴】
    * 2017/01/06 746-ide
    * </pre>
    *
    * @param args
    */
   private void onUpdateMultContactListEvent(ExtEventArgs<Document> args){
       this.updateMultContactListEvent.invoke(args);
   }

   /**
   *
   * 概略
   * <p>検索用データ設定処理</p>
   * <pre>
   * 【改版履歴】
   * 2017/01/11 746-ide
   * </pre>
   *
   */
   public void setSearchData(AisycDataGridCellValueChangedEventArgs args){

       if(tabInfo == null){
           return;
       }

       // 変更されたタブにより処理を分ける
       if(StringUtil.equals(tabInfo.getKbn(), MultiContactListConst.MULTTEL_KBN)){
           // 電話番号一覧変更時
           if(StringUtil.equals(args.getColumnName(),MultiTelNumDataGridColumnConst.TEL_NUM)){
               // 変更後の値を取得
               String strTelNum = args.getNewCellValue().toString();
               AisycDataGridRowData row = (AisycDataGridRowData)args.getRowData();
               if (!StringUtil.isNullOrEmpty(strTelNum)){
                   // 検索用電話番号へセットする
                   String srchTellNum = ConvertUtil.refineTelNumberOnly(strTelNum, false);
                   row.setCellValue(MultiTelNumDataGridColumnConst.SRCH_TEL_NUM, srchTellNum);
               }
           }
       }
       else if(StringUtil.equals(tabInfo.getKbn(), MultiContactListConst.MAILADDS_INFO_KBN)){
           // メールアドレス一覧変更時
           if(StringUtil.equals(args.getColumnName(),MultiMailAddsDataGridColumnConst.MAIL_ADDR)){
               // 変更後の値を取得
               String strMailAddr = args.getNewCellValue().toString();
               AisycDataGridRowData row = (AisycDataGridRowData)args.getRowData();
               if (!StringUtil.isNullOrEmpty(strMailAddr)){
                   // 検索用メールアドレスへセットする
                   row.setCellValue(MultiMailAddsDataGridColumnConst.SRCH_MAIL_ADDR, strMailAddr);
               }
           }
       }
       else{
           // 上記以外のタブ変更時
           String key = "";
           String srcKey = "";

           AisycDataGridRowData row = (AisycDataGridRowData)args.getRowData();

           Map<String, AisycDataGridColumnInfo> colInfoMap = getDataGrid().getColumnInfoMap();
           for (Iterator<Map.Entry<String, AisycDataGridColumnInfo>> ite = colInfoMap.entrySet().iterator(); ite.hasNext();) {
               Map.Entry<String, AisycDataGridColumnInfo> entry = ite.next();
               srcKey = entry.getKey();
               if(srcKey.contains(MultiContactListConst.SRC_KEY)){
                   key = srcKey.replace(MultiContactListConst.SRC_KEY, "");
                   break;
               }
           }

           String keyValue = row.getCellValue(key).toString();
           if (!StringUtil.isNullOrEmpty(keyValue)){
               row.setCellValue(srcKey, keyValue);
           }
       }
   }
}
