package mibh.mis.tmsland.realm;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ponlakiss on 05/13/2016.
 */
public class HashtagValue extends RealmObject {

    @PrimaryKey
    @SerializedName("LIST_ID")  private String listId;
    @SerializedName("LIST_NAME")  private String listName;
    @SerializedName("GROUP_ID")  private String groupId;
    @SerializedName("TYPE_ID")  private String typeId;
    @SerializedName("SERVERDATE")  private String serverDate;
    @SerializedName("STATUS")  private String status;

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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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
