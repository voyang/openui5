package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.widgets.AisycContainer;
import carapace.web2.gwt.client.basic.aisyc.common.widgets.AisycPopupWindowBase;
import carapace.web2.gwt.client.basic.common.widgets.CpTabControl;
import carapace.web2.gwt.client.basic.common.widgets.ExtButtonGBS3;

public class MultiContactListWindowUI extends AisycPopupWindowBase<AisycContainer> {

    //▼========== UiBinder 自動生成部分 ==========
    private static MultiContactListWindowUIUiBinder uiBinder = GWT.create(MultiContactListWindowUIUiBinder.class);

    interface MultiContactListWindowUIUiBinder extends UiBinder<Widget, MultiContactListWindowUI> {
    }
    //▲========== UiBinder 自動生成部分 ==========

    //▼========== UiField ==========
    @UiField
    public CpTabControl multiContactListWindowUI_mainTabCtrl;
    @UiField
    public ExtButtonGBS3 multiContactListWindowUI_closelBtn;
    //▲========== UiField ==========

    /**
     *
     * コンストラクタ
     */
    public MultiContactListWindowUI() {
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

}
