package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.AisycDataGrid;
import carapace.web2.gwt.client.basic.common.widgets.ExtButtonGBS3;

public class HideMultiMailAddrListBlockUI extends HideMultiCommonBlockBase {

    //▼========== UiBinder 自動生成部分 ==========
    private static HideMultiMailAddrListBlockUIUiBinder uiBinder = GWT
            .create(HideMultiMailAddrListBlockUIUiBinder.class);

    interface HideMultiMailAddrListBlockUIUiBinder extends UiBinder<Widget, HideMultiMailAddrListBlockUI> {
    }
    //▲========== UiBinder 自動生成部分 ==========

    //▼========== UiField ==========
    @UiField
    public ExtButtonGBS3 hideMultiMailAddrListBlockUI_insertBtn;

    @UiField
    public ExtButtonGBS3 hideMultiMailAddrListBlockUI_deleteBtn;

    @UiField
    public ExtButtonGBS3 hideMultiMailAddrListBlockUI_saveBtn;

    @UiField
    public ExtButtonGBS3 hideMultiMailAddrListBlockUI_upBtn;

    @UiField
    public ExtButtonGBS3 hideMultiMailAddrListBlockUI_downBtn;

    @UiField
    public AisycDataGrid hideMultiMailAddrListBlockUI_mailGrid;

    @UiField
    public ExtButtonGBS3 hideMultiMailAddrListBlockUI_maiBtn;
    //▲========== UiField ==========


    /**
     *
     * コンストラクタ
     */
    public HideMultiMailAddrListBlockUI() {
    }

    /**
     *
     * 概略
     * <p>ウィジェットを初期化します</p>
     * @see carapace.web2.gwt.client.basic.aisyc.common.widgets.AisycBlockBase#initWidget()
     */
    @Override
    protected void initWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    /**
     * 複数メール一覧データグリッドを取得します。
     * @return 複数メール一覧データグリッド
     */
    @Override
    public AisycDataGrid getDataGrid() {
        return this.hideMultiMailAddrListBlockUI_mailGrid;
    }

    /** 行追加ボタン */
    @Override
    protected ExtButtonGBS3 getInsertButton() {
        return hideMultiMailAddrListBlockUI_insertBtn;
    }

    /** 保存ボタン */
    @Override
    protected ExtButtonGBS3 getSaveButton() {
        return hideMultiMailAddrListBlockUI_saveBtn;
    }

    /** 行削除ボタン */
    @Override
    protected ExtButtonGBS3 getDeleteButton() {
        return hideMultiMailAddrListBlockUI_deleteBtn;
    }

    /** 行移動（上）ボタン */
    @Override
    protected ExtButtonGBS3 getUpButton() {
        return hideMultiMailAddrListBlockUI_upBtn;
    }

    /** 行移動（下）ボタン */
    @Override
    protected ExtButtonGBS3 getDownButton() {
        return hideMultiMailAddrListBlockUI_downBtn;
    }

}
