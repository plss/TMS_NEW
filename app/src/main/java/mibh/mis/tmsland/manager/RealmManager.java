package mibh.mis.tmsland.manager;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import mibh.mis.tmsland.realm.HashtagMain;
import mibh.mis.tmsland.realm.HashtagValue;
import mibh.mis.tmsland.realm.ImageTms;
import mibh.mis.tmsland.realm.ListMain;
import mibh.mis.tmsland.utils.Utils;

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
                            String commentImg,
                            String truckId,
                            String driverId,
                            String firstname,
                            String lastname,
                            String locationName) {

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
        imageTms.setDateImg(Utils.getInstance().getCurrentDateTime());
        imageTms.setStatUpd("INACTIVE");
        imageTms.setCommentImg(commentImg);
        imageTms.setTruckId(truckId);
        imageTms.setDriverId(driverId);
        imageTms.setFirstname(firstname);
        imageTms.setLastname(lastname);
        imageTms.setLocationName(locationName);
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
        realm.beginTransaction();
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

    public void upsertHashtagMain(HashtagMain hashtagMain) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(hashtagMain);
        realm.commitTransaction();
        realm.close();
    }

    public void upsertHashtagValue(List<HashtagValue> hashtagValueList) {
        for (HashtagValue hashtagValue : hashtagValueList) {
            Log.d("TEST", "upsertHashtagValue: " + hashtagValue.getListName());
        }
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(hashtagValueList);
        realm.commitTransaction();
        realm.close();
    }

    public void upsertListMain(List<ListMain> listMains) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(listMains);
        realm.commitTransaction();
        realm.close();
    }

    public List<HashtagMain> getLastUpdateByTbName(String tbName) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<HashtagMain> result = realm.where(HashtagMain.class)
                .equalTo("tbName", tbName)
                .findAll();
        List<HashtagMain> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public List<HashtagValue> getHashtagValue(String groupId, String typeId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<HashtagValue> result = realm.where(HashtagValue.class)
                .equalTo("groupId", groupId)
                .equalTo("typeId", typeId)
                .equalTo("status", "Active")
                .findAll();
        List<HashtagValue> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public List<ListMain> getListMain() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ListMain> result = realm.where(ListMain.class)
                .equalTo("status", "Active")
                .findAll();
        List<ListMain> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

}
