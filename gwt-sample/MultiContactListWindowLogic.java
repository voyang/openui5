/*
 * $Header$
 * $Revision$
 * $Date$
 */
package carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.logic;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;

import carapace.web2.addons.caracrm.gwt.client.aisyc.basic.automation.generic.widgets.model.AisycDataGridRowData;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.MultiContactListWindow;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.constants.MultiContactListConst;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.factories.MultiContactListWindowFactory;
import carapace.web2.addons.caracrm.gwt.client.crm.block.widgets.multitelmail.value.MultiContactListTabValue;
import carapace.web2.gwt.client.basic.common.constants.SystemConst;
import carapace.web2.gwt.client.basic.common.dialog.AlertDialog;
import carapace.web2.gwt.client.basic.common.events.ExtEvent;
import carapace.web2.gwt.client.basic.common.events.ExtEventArgs;
import carapace.web2.gwt.client.basic.common.log.CpLogger;
import carapace.web2.gwt.client.basic.common.util.statical.AsyncHttpUtil;
import carapace.web2.gwt.client.basic.common.util.statical.AsyncHttpUtil.AsyncHttpCallback;
import carapace.web2.gwt.client.basic.common.util.statical.AsyncHttpUtil.AsyncHttpCallbackArgs;
import carapace.web2.gwt.client.basic.common.util.statical.ConvertUtil;
import carapace.web2.gwt.client.basic.common.util.statical.GwtXmlUtil;
import carapace.web2.gwt.client.basic.common.util.statical.StringUtil;

/**
 * 概略
 * <p>複数連絡先一覧ロジック</p>
 *
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class MultiContactListWindowLogic {

    /**
     * ログ出力クラス
     */
    private static final CpLogger logger = CpLogger.getLogger(MultiContactListWindowLogic.class);

    private static String RESULT_CUSTIDENTIFYKBNLIST_PATH = "/result/list[@name='custIdentifyKbnList']/row";
    private static String RESULT_CODE_PATH = "/result/field[@name='resultCode']";

    /** ASPID */
    private String aspId;
    /** CID */
    private String cid;
    /** 受付履歴ID */
    private String receptHistId;
    /** 対応履歴SEQ */
    private String supportHistSeq;

    /** */
    private List<AisycDataGridRowData> deptList;
    /** */
    private List<MultiContactListTabValue> dataList = null;

    private static final MultiContactListWindowFactory contactListWindowFactory = GWT.create(MultiContactListWindowFactory.class);

    /** 複数連絡先一覧インスタンス作成完了イベント */
    public final ExtEvent<MultiContactListWindow> multiContactListWindowCreatedEvent = new ExtEvent<>(this);

    /**
     *
     * 概略
     * <p>複数連絡先ブロックを作成する。</p>
     * <pre>
     * 【改版履歴】
     * 2016/06/30 693-toan
     * </pre>
     *
     * @param paramAspId　：ASPID
     * @param paramCid　：CID
     * @param paramDeptList　：部門情報
     */
    public void create(String paramAspId, String paramCid, List<AisycDataGridRowData> paramDeptList){
        try{
            this.aspId = paramAspId;
            this.cid = paramCid;
            this.deptList = paramDeptList;

            getTabInfoList();
        }catch (Exception ex){
            logger.fatal("複数連絡先ブロック作成:" + SystemConst.FIX_MESSAGE_SYSTEM_ERROR, ex);
            AlertDialog alert =  new AlertDialog(SystemConst.FIX_MESSAGE_SYSTEM_ERROR);
            alert.show();
            onMultiContactListWindowCreated(new ExtEventArgs<MultiContactListWindow>(null));
        }
    }

    /**
     *
     * 概略
     * <p>タブ情報を取得する</p>
     * <pre>
     * 【改版履歴】
     * 2016/06/30 693-toan
     * </pre>
     *
     */
    private void getTabInfoList(){
        AsyncHttpCallback callBack = new AsyncHttpCallback() {

            @Override
            public void onCallback(AsyncHttpCallbackArgs args) {
                getTabInfoListAfterExecute(args);
            }
        };
        AsyncHttpUtil.post(callBack,
                "multContactList_getTabInfoList.do", "requestXML=");
    }

    /**
     *
     * 概略
     * <p>タブ情報を取得する処理実施</p>
     * <pre>
     * 【改版履歴】
     * 2016/06/30 693-toan
     * </pre>
     *
     * @param args
     */
    private void getTabInfoListAfterExecute(AsyncHttpCallbackArgs args){
        String methodName = "#getTabInfoListAfterExecute(AsyncHttpCallbackArgs args):";

        //先にエラーが発生したかどうかが必要です。
        if (args.getError() != null){

            logger.fatal(methodName, args.getError());
            AlertDialog alert = new AlertDialog(SystemConst.FIX_MESSAGE_ASYNC_HTTP_ERROR);
            alert.show();

            onMultiContactListWindowCreated(new ExtEventArgs<MultiContactListWindow>(null));
            return;

        }else if (!StringUtil.isNullOrEmpty(args.getResult())){

            setTab(args.getResult());

        }else{
            logger.fatal(methodName + MultiContactListConst.TAB_INFO_NULL_MESSAGE);
            AlertDialog alert = new AlertDialog(MultiContactListConst.TAB_INFO_NULL_MESSAGE);
            alert.show();

            onMultiContactListWindowCreated(new ExtEventArgs<MultiContactListWindow>(null));
            return;
        }
    }

    /**
     *
     * 概略
     * <p>タブ設定.</p>
     * <pre>
     * 【改版履歴】
     * 2016/06/30 693-toan
     * </pre>
     *
     * @param resultData：タブの設定情報（XMLドキュメント）
     */
    private void setTab(String resultData){

        Document xDocData = XMLParser.parse(resultData);
        Element rootElement = xDocData.getDocumentElement();

        String resultCd = null;
        List<Element> resultCdElements = GwtXmlUtil.getChildElementsByMixPath(rootElement, RESULT_CODE_PATH);
        if (resultCdElements != null && resultCdElements.size() > 0) {
            resultCd = ConvertUtil.nullToBlank(GwtXmlUtil.getElementText(resultCdElements.get(0)));
        }

        if (StringUtil.equals(resultCd, "success")){
            dataList = createTabInfoList(rootElement, RESULT_CUSTIDENTIFYKBNLIST_PATH);

            MultiContactListWindow telWin = contactListWindowFactory.create(aspId, cid, receptHistId, supportHistSeq, dataList, deptList);

            // メールインスタンス作成完了イベントを発生させます。
            onMultiContactListWindowCreated(new ExtEventArgs<MultiContactListWindow>(telWin));
            telWin.show();
        }else{
            logger.fatal(MultiContactListConst.TAB_INFO_NULL_MESSAGE);
            AlertDialog alert = new AlertDialog(MultiContactListConst.TAB_INFO_NULL_MESSAGE);
            alert.show();

            onMultiContactListWindowCreated(new ExtEventArgs<MultiContactListWindow>(null));
            return;
        }
    }

    /**
     *
     * 概略
     * <p>XMLドキュメントから検索結果コレクションを取得する.</p>
     * <pre>
     * 【改版履歴】
     * 2016/06/30 693-toan
     * </pre>
     *
     * @param rootElement：検索結果を含んだXMLドキュメント
     * @param xmlPath：XML内検索結果リストのパス
     * @return　検索結果コレクション
     */
    private List<MultiContactListTabValue> createTabInfoList(Element rootElement, String xmlPath){
        //Documentを要素リスト化
        List<Element> resultDataElements = GwtXmlUtil.getChildElementsByMixPath(rootElement, xmlPath);

        List<MultiContactListTabValue> rtnCollection = new ArrayList<MultiContactListTabValue>();

        int seqNo = 1;
        for(Element item : resultDataElements){
            //Elements内の要素をDictionary化
            MultiContactListTabValue data = new MultiContactListTabValue(item);
            data.setSeqNo(seqNo);

            rtnCollection.add(data);
            seqNo++;
        }

        return rtnCollection;
    }

    /**
     *
     * 概略
     * <p>複数連絡先一覧インスタンス作成完了イベントを発生させます。</p>
     * <pre>
     * 【改版履歴】
     * 2016/06/20 693-toan
     * </pre>
     *
     * @param args
     */
    private void onMultiContactListWindowCreated(ExtEventArgs<MultiContactListWindow> args){
        this.multiContactListWindowCreatedEvent.invoke(args);
    }
}
