package mibh.mis.tmsland.realm;

import io.realm.RealmObject;

/**
 * Created by ponlakiss on 05/13/2016.
 */
public class ListMain extends RealmObject {

    private String listId = "LIST_ID";
    private String listName = "LIST_NAME";
    private String valueDate = "VALUE_DATE";
    private String serverDate = "SERVERDATE";
    private String status = "STATUS";

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getServerDate() {
        return serverDate;
    }

    public void setServerDate(String serverDate) {
        this.serverDate = serverDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
