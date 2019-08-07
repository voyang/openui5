package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.AisycDataGrid;
import carapace.web2.gwt.client.basic.common.widgets.ExtButtonGBS3;

public class HideMultiOptionListBlockUI extends HideMultiCommonBlockBase {

    //▼========== UiBinder 自動生成部分 ==========
    private static HideMultiOptionListBlockUIUiBinder uiBinder = GWT.create(HideMultiOptionListBlockUIUiBinder.class);

    interface HideMultiOptionListBlockUIUiBinder extends UiBinder<Widget, HideMultiOptionListBlockUI> {
    }
    //▲========== UiBinder 自動生成部分 ==========

    //▼========== UiField ==========
    @UiField
    public ExtButtonGBS3 hideMultiOptionListBlockUI_insertBtn;

    @UiField
    public ExtButtonGBS3 hideMultiOptionListBlockUI_deleteBtn;

    @UiField
    public ExtButtonGBS3 hideMultiOptionListBlockUI_saveBtn;

    @UiField
    public ExtButtonGBS3 hideMultiOptionListBlockUI_upBtn;

    @UiField
    public ExtButtonGBS3 hideMultiOptionListBlockUI_downBtn;

    @UiField
    public AisycDataGrid hideMultiOptionListBlockUI_optionGrid;
    //▲========== UiField ==========

    /**
     *
     * コンストラクタ
     */
    public HideMultiOptionListBlockUI() {
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
     * 複数オプション一覧データグリッドを取得します。
     * @return 複数オプション一覧データグリッド
     */
    protected AisycDataGrid getDataGrid(){
        return this.hideMultiOptionListBlockUI_optionGrid;
    }

    /** 行追加ボタン */
    protected ExtButtonGBS3 getInsertButton(){
        return hideMultiOptionListBlockUI_insertBtn;
    }

    /** 保存ボタン */
    protected ExtButtonGBS3 getSaveButton(){
        return hideMultiOptionListBlockUI_saveBtn;
    }

    /** 行削除ボタン */
    protected ExtButtonGBS3 getDeleteButton(){
        return hideMultiOptionListBlockUI_deleteBtn;
    }

    /** 行移動（上）ボタン */
    protected ExtButtonGBS3 getUpButton(){
        return hideMultiOptionListBlockUI_upBtn;
    }

    /** 行移動（下）ボタン */
    protected ExtButtonGBS3 getDownButton(){
        return hideMultiOptionListBlockUI_downBtn;
    }

}
