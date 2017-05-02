package mibh.mis.tmsland.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ponlakiss on 05/13/2016.
 */
public class HashtagMain extends RealmObject {

    @PrimaryKey
    private String tbName;
    private String lastUpdate;

    public String getTbName() {
        return tbName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
