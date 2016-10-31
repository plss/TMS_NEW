package mibh.mis.tmsland.manager;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import mibh.mis.tmsland.realm.ImageTms;

public class RealmManager {

    private static RealmManager instance;

    public static RealmManager getInstance() {
        if (instance == null)
            instance = new RealmManager();
        return instance;
    }

    private Context mContext;

    private RealmManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public void insertImage(String workId,
                            String groupType,
                            String typeImg,
                            String fileName,
                            String docItem,
                            String lat,
                            String lng,
                            String dateImg,
                            String statUpd,
                            String commentImg) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ImageTms imageTms = realm.createObject(ImageTms.class);
        imageTms.setWorkId(workId);
        imageTms.setGroupType(groupType);
        imageTms.setTypeImg(typeImg);
        imageTms.setFileName(fileName);
        imageTms.setDocItem(docItem);
        imageTms.setLat(lat);
        imageTms.setLng(lng);
        imageTms.setDateImg(dateImg);
        imageTms.setStatUpd(statUpd);
        imageTms.setCommentImg(commentImg);
        realm.commitTransaction();
        realm.close();

    }

    public List<ImageTms> getImageByField(String fieldName, String fieldValue) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ImageTms> result = realm.where(ImageTms.class)
                .equalTo(fieldName, fieldValue)
                .findAll();
        List<ImageTms> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public List<ImageTms> getImageByWorkIdAndGroupType(String workId, String groupType) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ImageTms> result = realm.where(ImageTms.class)
                .equalTo("workId", workId)
                .equalTo("groupType", groupType)
                .findAll();
        List<ImageTms> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public List<ImageTms> getImageByGroupType(String groupType) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ImageTms> result = realm.where(ImageTms.class)
                .equalTo("groupType", groupType)
                .findAll();
        List<ImageTms> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public List<ImageTms> getImageByGroupTypeAndWorkIdAndItem(String groupType, String workId, String docItem) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ImageTms> result = realm.where(ImageTms.class)
                .equalTo("workId", workId)
                .equalTo("groupType", groupType)
                .equalTo("docItem", docItem)
                .findAll();
        List<ImageTms> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public List<ImageTms> getImageByGroupTypeAndItem(String groupType, String docItem) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ImageTms> result = realm.where(ImageTms.class)
                .equalTo("groupType", groupType)
                .equalTo("docItem", docItem)
                .findAll();
        List<ImageTms> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public List<ImageTms> getImageInactive() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ImageTms> result = realm.where(ImageTms.class)
                .equalTo("statUpd", "INACTIVE")
                .findAll();
        List<ImageTms> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public List<ImageTms> getImageActiveAndNotWorkId(String workId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ImageTms> result = realm.where(ImageTms.class)
                .equalTo("workId", workId)
                .notEqualTo("statUpd", "ACTIVE")
                .findAll();
        List<ImageTms> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public List<ImageTms> getImageByWorkIdAndDocItem(String workId, String docItem) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ImageTms> result = realm.where(ImageTms.class)
                .equalTo("workId", workId)
                .equalTo("docItem", docItem)
                .findAll();
        List<ImageTms> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public List<ImageTms> getImageByWorkIdAndDocItemAndGroupTypeAndTypeImage(String workId, String docItem, String groupType, String typeImg) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ImageTms> result = realm.where(ImageTms.class)
                .equalTo("workId", workId)
                .equalTo("docItem", docItem)
                .equalTo("groupType", groupType)
                .equalTo("typeImg", typeImg)
                .findAll();
        List<ImageTms> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public boolean deleteByFilename(String filename) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        boolean result = realm.where(ImageTms.class)
                .equalTo("fileName", filename)
                .findAll().deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
        return result;
    }

    public void updateActiveByFilename(String filename) {
        Realm realm = Realm.getDefaultInstance();
        ImageTms result = realm.where(ImageTms.class)
                .equalTo("fileName", filename)
                .findFirst();
        result.setStatUpd("ACTIVE");
        realm.commitTransaction();
        realm.close();
    }

    public boolean clearImageTms() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        boolean result = realm.where(ImageTms.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
        return result;
    }

}
