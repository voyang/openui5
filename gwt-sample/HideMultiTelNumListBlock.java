package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;

import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.core.logic.widgetlogic.EDataResultDeployMode;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.AisycDataGrid;
import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.model.AisycDataGridRowData;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.automation.specific.constants.DeptDataGridColumnConst;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.automation.specific.constants.MultiOptionDataGridColumnConst;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.automation.specific.constants.MultiTelNumDataGridColumnConst;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.common.events.PhoneEvent;
import carapace.web2.addons.caracrm.gwt.client.crm.basic.common.events.PhoneEventArgs;
import carapace.web2.gwt.client.basic.common.dialog.AlertDialog;
import carapace.web2.gwt.client.basic.common.events.SSClickHandler;
import carapace.web2.gwt.client.basic.common.util.statical.ConvertUtil;
import carapace.web2.gwt.client.basic.common.util.statical.StringUtil;
import carapace.web2.gwt.client.basic.common.util.statical.validations.OtherValidationUtil;
import carapace.web2.gwt.client.basic.common.value.CpWordProperties;

/**
 *
 * 概略
 * <p>複数電話番号一覧ブロック</p>
 *
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class HideMultiTelNumListBlock extends HideMultiTelNumListBlockUI  {

    /** 電話発信ボタン押下イベント */
    public final PhoneEvent phoneConfirmEvent = new PhoneEvent(this);

    /** 部門情報*/
    private List<AisycDataGridRowData> deptList;

    public String addNewTelNo;
    /**
     * @return addNewTelNo
     */
    public String getAddNewTelNo() {
        return addNewTelNo;
    }

    /**
     * @param addNewTelNo 設定する addNewTelNo
     */
    public void setAddNewTelNo(String addNewTelNo) {
        this.addNewTelNo = addNewTelNo;
    }

    /**
     *
     * コンストラクタ
     */
    public HideMultiTelNumListBlock(String aspId, String cid){
        this.aspId = aspId;
        this.cid = cid;

        constructorLastProcess(HideMultiTelNumListBlock.class);
    }

    /**
     * コンストラクタ
     */
    public HideMultiTelNumListBlock() {

        constructorLastProcess(HideMultiTelNumListBlock.class);
    }

    /**
     * 設定タブ情報からブロックに反映する.
     */
    @Override
    protected void initFromTabInfo(){
        super.initFromTabInfo();

        // hideMultiTelNumListBlockUI_callBtn.setEnabled(isCanEdit());
        // hideMultiTelNumListBlockUI_callBtn.setVisible(isCanEdit());
        hideMultiTelNumListBlockUI_callBtn.setEnabled(false);
        hideMultiTelNumListBlockUI_telnumGrid.setReadOnly(!isCanEdit());
    }

    /**
     * 概略
     * <p>イベント設定。</p>
     */
    @Override
    protected void setEvents(){
        super.setEvents();

        //電話発信ボタンのイベント。
        hideMultiTelNumListBlockUI_callBtn.click.addHandler( new SSClickHandler() {

            @Override
            public void onClick(Object sender, ClickEvent event) {
                onCallButtonClick();
            }
        });
    }


    /**
     * 発信ボタンの処理。
     */
    private void onCallButtonClick(){

        int selectIndex = getDataGrid().getSelectedIndex();
        if(selectIndex < 0){

            //行が選択されていない場合
            AlertDialog alert = new AlertDialog(CpWordProperties.getGlobalWordProperty("caracrm.message.error.phone_number_not_selected"), null);
            alert.show();

        }else{
            //行が選択されている場合
            AisycDataGridRowData rowData = getDataGrid().getSelectedItem();

            //電話番号の取得。記号(#,*)を使用出来るように通常の電話番号でかける
            String telNum = rowData.getCellValueStringEmpty(MultiTelNumDataGridColumnConst.COLUMN_DEFINE.TEL_NUM);
            String srchTelNum = ConvertUtil.refineTelNumberOnly(rowData.getCellValueStringEmpty(MultiTelNumDataGridColumnConst.COLUMN_DEFINE.SRCH_TEL_NUM), true);

          //部門番号も取得。
          String deptSeq = rowData.getCellValueStringEmpty(MultiTelNumDataGridColumnConst.COLUMN_DEFINE.DEPT_SEQ);

          if (StringUtil.equals("", telNum)){
              //電話番号が入力されていない場合。
              AlertDialog alert = new AlertDialog(CpWordProperties.getGlobalWordProperty("caracrm.message.error.phone_number_not_input"), null);
              alert.show();

          }else if (!StringUtil.equals("", srchTelNum)){

              //電話番号が正しい形式でない場合
              if (OtherValidationUtil.isPhoneNo(srchTelNum, true) == false){
                  AlertDialog alert = new AlertDialog(CpWordProperties.getGlobalWordProperty("caracrm.message.error.phone_number_format_invalid"), null);
                  alert.show();

              }else{
                  //電話番号が入力されている場合
                  PhoneEventArgs phoneEventArgs = new PhoneEventArgs();
                  phoneEventArgs.setTelNum(srchTelNum);;

                  //部門SEQ(あれば)
                  if (!StringUtil.isNullOrEmpty(deptSeq)){
                      for(AisycDataGridRowData deptInfo : deptList){

                          String listDeptSeq = deptInfo.getCellValueStringEmpty(DeptDataGridColumnConst.COLUMN_DEFINE.DEPT_SEQ);
                          String listDeptName = deptInfo.getCellValueStringEmpty(DeptDataGridColumnConst.COLUMN_DEFINE.DEPT_NAME);

                          if (StringUtil.equals(deptSeq, listDeptSeq)){
                              phoneEventArgs.setDeptSeq(listDeptSeq);
                              phoneEventArgs.setDeptName(listDeptName);
                          }
                      }
                  }

                  //イベント発生する。
                  phoneConfirmEvent.invoke(phoneEventArgs);
              }
          }else{
              AlertDialog alert = new AlertDialog(CpWordProperties.getGlobalWordProperty("caracrm.message.error.phone_number_format_invalid"), null);
              alert.show();
          }
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
    public void initializeSrchReqCompletedProcessTel(){
        //連絡先一覧に着信電話番号が存在しなかった場合
        Boolean isExists = false;
        if(!StringUtil.isNullOrEmpty(addNewTelNo)){
             AisycDataGrid dataGrid = getDataGrid();
             List<AisycDataGridRowData> telList = (List<AisycDataGridRowData>)dataGrid.getRowData();
             for(AisycDataGridRowData element : telList){
                 String telNo = String.valueOf(element.getCellValue(MultiTelNumDataGridColumnConst.TEL_NUM));
                 if (addNewTelNo.equals(telNo)) {
                     isExists = true;
                     break;
                 }
             }
             if(!isExists){
                AisycDataGridRowData rowData = dataGrid.insertRowData(dataGrid.getRowCount(),false);
                rowData.setCellValue(MultiOptionDataGridColumnConst.COLUMN_DEFINE.OPTION_NAME, addNewTelNo);
                rowData.setCellValue(MultiOptionDataGridColumnConst.COLUMN_DEFINE.REMARK, "");
                dataGrid.reflesh();
             }
        }


    }
    public void initializeSrchReqCompletedProcess(){
    	hideMultiTelNumListBlockUI_callBtn.setEnabled(hideMultiTelNumListBlockUI_telnumGrid.getRowDataCount() > 0 ? true :false);
    }
}
