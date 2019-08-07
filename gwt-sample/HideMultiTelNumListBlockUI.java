package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.AisycDataGrid;
import carapace.web2.gwt.client.basic.common.widgets.ExtButtonGBS3;

public class HideMultiTelNumListBlockUI extends HideMultiCommonBlockBase {

    //▼========== UiBinder 自動生成部分 ==========
    private static HideMultiTelNumListBlockUIUiBinder uiBinder = GWT.create(HideMultiTelNumListBlockUIUiBinder.class);

    interface HideMultiTelNumListBlockUIUiBinder extends UiBinder<Widget, HideMultiTelNumListBlockUI> {
    }
    //▲========== UiBinder 自動生成部分 ==========

    //▼========== UiField ==========
    @UiField
    public ExtButtonGBS3 hideMultiTelNumListBlockUI_insertBtn;

    @UiField
    public ExtButtonGBS3 hideMultiTelNumListBlockUI_deleteBtn;

    @UiField
    public ExtButtonGBS3 hideMultiTelNumListBlockUI_saveBtn;

    @UiField
    public ExtButtonGBS3 hideMultiTelNumListBlockUI_upBtn;

    @UiField
    public ExtButtonGBS3 hideMultiTelNumListBlockUI_downBtn;

    @UiField
    public AisycDataGrid hideMultiTelNumListBlockUI_telnumGrid;

    @UiField
    public ExtButtonGBS3 hideMultiTelNumListBlockUI_callBtn;
    //▲========== UiField ==========

    /**
     *
     * コンストラクタ
     */
    public HideMultiTelNumListBlockUI() {
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
     * 複数電話番号一覧データグリッドを取得します。
     *
     * @return 複数電話番号一覧データグリッド
     */
    public AisycDataGrid getDataGrid() {
        return this.hideMultiTelNumListBlockUI_telnumGrid;
    }

    /** 行追加ボタン */
    protected ExtButtonGBS3 getInsertButton() {
        return hideMultiTelNumListBlockUI_insertBtn;
    }

    /** 保存ボタン */
    protected ExtButtonGBS3 getSaveButton() {
        return hideMultiTelNumListBlockUI_saveBtn;
    }

    /** 行削除ボタン */
    protected ExtButtonGBS3 getDeleteButton() {
        return hideMultiTelNumListBlockUI_deleteBtn;
    }

    /** 行移動（上）ボタン */
    protected ExtButtonGBS3 getUpButton() {
        return hideMultiTelNumListBlockUI_upBtn;
    }

    /** 行移動（下）ボタン */
    protected ExtButtonGBS3 getDownButton() {
        return hideMultiTelNumListBlockUI_downBtn;
    }

}
