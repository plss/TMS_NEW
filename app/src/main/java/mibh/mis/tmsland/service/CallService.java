package mibh.mis.tmsland.service;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import LibClass.SOAPWebserviceProperty;

/**
 * Created by ponlakiss on 05/17/2016.
 */
public class CallService {

    private final String NAMESPACE = "http://tempuri.org/";
    private final String urlNick = "http://www.mibholding.com/InterfaceTmsView.svc";
    private final String urlMarch = "http://www.mibholding.com/TMSMOBILE.asmx";
    private String SOAP_ACTION_NICK = "http://tempuri.org/IInterfaceTmsView/";
    private String SOAP_ACTION_MARCH = "http://tempuri.org/";
    private String METHOD_NAME = "";
    private SOAPWebserviceProperty soap_property = null;

    public String callWork(String TruckId, String DriverEmpID) {
        try {
            METHOD_NAME = "Get_DataWorkOpen";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Truck_id", TruckId);
            request.addProperty("Emp_id", DriverEmpID);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error CallWork", e.toString());
            return "error";
        }
    }

    public String callPlan(String TruckId, String DriverEmpID) {
        try {
            METHOD_NAME = "GetPlanOpenWorkAuth";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlNick;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("DriverEmpId", DriverEmpID);
            request.addProperty("TruckId", TruckId);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlNick);
            androidHttpTransport.call(SOAP_ACTION_NICK + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error", e.toString());
            return "error";
        }
    }

    public String checkLogin(String TruckId, String DriverEmpID) {
        try {
            METHOD_NAME = "getAuthLoginArrange";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlNick;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("DriverEmpId", DriverEmpID);
            request.addProperty("TruckId", TruckId);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlNick);
            androidHttpTransport.call(SOAP_ACTION_NICK + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error CallLogin", e.toString());
            return "error";
        }
    }

    public String callFuel(String WOHEADER_DOCID) {
        try {
            METHOD_NAME = "Get_FuelData";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("WOHEADER_DOCID", WOHEADER_DOCID);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error CallFuel", e.toString());
            return "error";
        }
    }

    public String getActiveVersion() {
        try {
            METHOD_NAME = "GetActiveVersion";
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlNick;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("AppId", "M004");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlNick);
            androidHttpTransport.call(SOAP_ACTION_NICK + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error version", e.toString());
        }
        return "error";
    }

    public String genNewFuel(String WOHEADER_DOCID, String EMP_ID) {
        try {
            METHOD_NAME = "GenNewFuel";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("WOHEADER_DOCID", WOHEADER_DOCID);
            request.addProperty("EMP_ID", EMP_ID);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error GenNewFuel", e.toString());
            return "error";
        }
    }

    public String checkNotify(String TruckId) {
        try {
            METHOD_NAME = "Get_NotifyNewWork";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Truck_id", TruckId);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error Notify", e.toString());
            return "null";
        }
    }

    public String savePic(String json_photo, String json_Img_ct) {
        try {
            String URL_SAVEPIC = "http://www.mibholding.com/dabt.asmx";
            METHOD_NAME = "SavePhoto_json";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = URL_SAVEPIC;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("json_photo", json_photo);
            request.addProperty("json_Img_ct", json_Img_ct);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_SAVEPIC);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error SavePhoto", e.toString());
            return "error";
        }
    }

    public String saveLog(String MOBILE_IDREF, String DRIVER_ID, String DRIVER_NAME, String TRUCK_ID, String LAST_WORK) {
        try {
            METHOD_NAME = "Save_LogMobile";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("MOBILE_IDREF", MOBILE_IDREF);
            polydata.put("DRIVER_ID", DRIVER_ID);
            polydata.put("TRUCK_ID", TRUCK_ID);
            polydata.put("DRIVER_NAME", DRIVER_NAME);
            polydata.put("TRUCK_ID", TRUCK_ID);
            polydata.put("LAST_WORK", LAST_WORK);
            request.addProperty("Json_MobileData", polydata.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error", e.toString());
            return "error";
        }
    }

    public String setState(String WOHEADER_DOCID, String WOITEM_DOCID, String TRUCK_ID, String LAT_LNG, String LOCATION_NAME, String WORK_TYPE, String TYPE_IMG, String DRIVER_ID, String DRIVER_NAME, String FILE_NAME, String Comment) {
        try {
            METHOD_NAME = "Save_StateWork";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("WOHEADER_DOCID", WOHEADER_DOCID);
            polydata.put("WOITEM_DOCID", WOITEM_DOCID);
            polydata.put("TRUCK_ID", TRUCK_ID);
            polydata.put("LAT_LNG", LAT_LNG);
            polydata.put("LOCATION_NAME", LOCATION_NAME);
            polydata.put("WORK_TYPE", WORK_TYPE);
            polydata.put("TYPE_IMG", TYPE_IMG);
            polydata.put("DRIVER_ID", DRIVER_ID);
            polydata.put("DRIVER_NAME", DRIVER_NAME);
            polydata.put("FILE_NAME", FILE_NAME);
            polydata.put("COMMENT_PHOTO", Comment);

            request.addProperty("Json_StateWork", polydata.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error", e.toString());
            return "false";
        }
    }

    public String getHashtag(String date) {
        try {
            METHOD_NAME = "Get_data_hashtag";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("yyMMddHHmmss", date);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error getHashtag", e.toString());
            return "error";
        }
    }

    public String getLastWork(String truckid) {
        try {
            METHOD_NAME = "Get_lastWork";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Truck_Id", truckid);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error getLastWork", e.toString());
            return "error";
        }
    }

    public String getWorkList(String date) {
        try {
            METHOD_NAME = "Get_DataWorkList";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("yyMMddHHmmss", date);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error getWorkList", e.toString());
            return "error";
        }
    }

    public String saveCheckDriver(String LASTWORK, String ITEM, String EMP_ID, String NAME_SURNAME, String STATUSDRIVER, String STATUSDRIVER_TH, String LATLNG, String LOCATION_NAME, String COMMENT_DRI) {
        try {
            //Log.d("TEST savedirver", LASTWORK + " " + ITEM + " " + EMP_ID + " " + NAME_SURNAME + " " + STATUSDRIVER + " " + STATUSDRIVER_TH + " " + LATLNG + " " + LOCATION_NAME + " " + COMMENT_DRI);
            METHOD_NAME = "Save_CheckingDriver";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("LASTWORK", LASTWORK);
            polydata.put("ITEM", ITEM);
            polydata.put("EMP_ID", EMP_ID);
            polydata.put("NAME_SURNAME", NAME_SURNAME);
            polydata.put("STATUSDRIVER", STATUSDRIVER);
            polydata.put("STATUSDRIVER_TH", STATUSDRIVER_TH);
            polydata.put("LATLNG", LATLNG);
            polydata.put("LOCATION_NAME", LOCATION_NAME);
            polydata.put("COMMENT_DRI", COMMENT_DRI);
            //Log.d("TEST savedirver", polydata.toString());
            request.addProperty("Json_CheckingDriver", polydata.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error CheckingDriver", e.toString());
            return "error";
        }
    }

    public String saveCheckTruck(String LASTWORK, String ITEM, String EMP_ID, String TRUCK_ID, String STATUSTRUCK, String STATUSTRUCK_TH, String LATLNG, String LOCATION_NAME, String COMMENT_TCK) {
        try {
            METHOD_NAME = "Save_CheckingTruck";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("LASTWORK", LASTWORK);
            polydata.put("ITEM", ITEM);
            polydata.put("EMP_ID", EMP_ID);
            polydata.put("TRUCK_ID", TRUCK_ID);
            polydata.put("STATUSTRUCK", STATUSTRUCK);
            polydata.put("STATUSTRUCK_TH", STATUSTRUCK_TH);
            polydata.put("LATLNG", LATLNG);
            polydata.put("LOCATION_NAME", LOCATION_NAME);
            polydata.put("COMMENT_TCK", COMMENT_TCK);

            request.addProperty("Json_CheckingTruck", polydata.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error CheckingTruck", e.toString());
            return "error";
        }
    }

    public String getDriver(String empid, String truckid) {
        try {
            METHOD_NAME = "Get_Driver";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("EMP_ID", empid);
            request.addProperty("TruckId", truckid);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error getDriver", e.toString());
            return "error";
        }
    }

    public String savePlanAcceptReject(String PLHEADERDOCID, String TRUCKID, String STATUS, String REMARK) {
        try {
            METHOD_NAME = "Save_PlanAcceptReject";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("PLHEADERDOCID", PLHEADERDOCID);
            polydata.put("TRUCK_ID", TRUCKID);
            polydata.put("PLANWORK_STATUS", STATUS);
            polydata.put("TRUCK_ID", TRUCKID);
            polydata.put("PLANWORK_REMARK", REMARK);

            request.addProperty("JsonPlan", polydata.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.e("Error PlanAcceptReject", e.toString());
            return "error";
        }
    }

    public String saveOpenWork(String WO) {
        try {
            METHOD_NAME = "Save_OpenWork";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Plan_Id", WO);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.e("Error saveOpenWork", e.toString());
            return "error";
        }
    }

    public String Save_CloseWork(String WO) {
        try {
            METHOD_NAME = "Save_closeWork";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Work_id", WO);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.e("Error Save_closeWork", e.toString());
            return "error";
        }
    }

    public String Save_ReciveDoc(String WO) {
        try {
            METHOD_NAME = "Save_ReciveDoc";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("WorkId", WO);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.e("Error Save_ReciveDoc", e.toString());
            return "error";
        }
    }

    public String Save_ReqWork(String TRUCK_ID, String DRIVER_ID, String DRIVER_NAME, String LATLNG, String LOCATIONNAME, String STATUS, String STATUS_TH, String COMMENT_DRIVER) {
        try {
            METHOD_NAME = "Save_ReqWork";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("TRUCK_ID", TRUCK_ID);
            polydata.put("DRIVER_ID", DRIVER_ID);
            polydata.put("DRIVER_NAME", DRIVER_NAME);
            polydata.put("LATLNG", LATLNG);
            polydata.put("LOCATIONNAME", LOCATIONNAME);
            polydata.put("STATUS", STATUS);
            polydata.put("STATUS_TH", STATUS_TH);
            polydata.put("COMMENT_DRIVER", COMMENT_DRIVER);

            request.addProperty("JsonReqWork", polydata.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.e("Error Save_ReqWork", e.toString());
            return "error";
        }
    }

    public String getMileClose(String docId, String itemId, String trukId) {
        try {
            METHOD_NAME = "Get_MileClose";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            JSONObject polydata = new JSONObject();
            polydata.put("WOHEADER_DOCID", docId);
            polydata.put("WOITEM_DOCID", itemId);
            polydata.put("TRUCK_ID", trukId);
            request.addProperty("JsonWorkItem", polydata.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.d("Error getDriver", e.toString());
            return "error";
        }
    }

    public String getWeight(String TRUCK_ID, String WOHEADER_OPEN) {
        try {
            METHOD_NAME = "Get_Weight";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("TRUCK_ID", TRUCK_ID);
            polydata.put("WOHEADER_OPEN", WOHEADER_OPEN);

            request.addProperty("jsonParams", polydata.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.e("Error Get_Weight", e.toString());
            return "error";
        }
    }

    public String saveCloseEachWorkItem(String WOHEADER_DOCID, String WOITEM_DOCID, String TRUCK_ID, String AMTPRODUCT, String miles, String Receive_datein, String Receive_dateout, String DO) {
        try {
            METHOD_NAME = "Save_closeEachWorkItem";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("WOHEADER_DOCID", WOHEADER_DOCID);
            polydata.put("WOITEM_DOCID", WOITEM_DOCID);
            polydata.put("TRUCK_ID", TRUCK_ID);
            polydata.put("AMTPRODUCT", AMTPRODUCT);
            polydata.put("MILES", miles);
            polydata.put("Receive_datein", Receive_datein);
            polydata.put("Receive_dateout", Receive_dateout);
            polydata.put("DO", DO);

            request.addProperty("JsonWorkItem", polydata.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.e("Error closeEachWorkItem", e.toString());
            return "error";
        }
    }

    public String saveCloseLastWorkItem(String WOHEADER_DOCID, String WOITEM_DOCID, String TRUCK_ID, String AMTPRODUCT, String miles, String Receive_datein, String Receive_dateout, String DO, String statusClose, String statusCloseTh) {
        try {
            METHOD_NAME = "Save_closeEachWorkItem";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            JSONObject polydata = new JSONObject();
            polydata.put("WOHEADER_DOCID", WOHEADER_DOCID);
            polydata.put("WOITEM_DOCID", WOITEM_DOCID);
            polydata.put("TRUCK_ID", TRUCK_ID);
            polydata.put("AMTPRODUCT", AMTPRODUCT);
            polydata.put("MILES", miles);
            polydata.put("Receive_datein", Receive_datein);
            polydata.put("Receive_dateout", Receive_dateout);
            polydata.put("DO", DO);
            polydata.put("STATUS_CLOSE", statusClose);
            polydata.put("STATUS_CLOSE_TH", statusCloseTh);

            request.addProperty("JsonWorkItem", polydata.toString());
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            String resultData = result.toString();
            return resultData;
        } catch (Exception e) {
            Log.e("Error closeEachWorkItem", e.toString());
            return "error";
        }
    }

    public String getNews(String driverID) {
        try {
            METHOD_NAME = "Get_NewsFeed";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Sys", "TMSONMOBILE.WEB");
            request.addProperty("CusId", driverID);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error News", e.toString());
            return "error";
        }
    }

    public String saveToken(String driverId, String token) {
        try {
            METHOD_NAME = "Save_Token";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Driver_Id", driverId);
            request.addProperty("Token_Id", token);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error Token", e.toString());
            return "error";
        }
    }

    public String checkTail(String truckId, String tailId) {
        try {
            METHOD_NAME = "Save_SwapTail";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Truck_Id", truckId);
            request.addProperty("Tail_New", tailId);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error CheckTail", e.toString());
            return "error";
        }
    }

    public String saveNewsRead(String driverId) {
        try {
            METHOD_NAME = "Save_ReadNewsFeed";
            soap_property = new SOAPWebserviceProperty();
            soap_property.urlWebservice = urlMarch;
            soap_property.namespaceWebservice = NAMESPACE;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Sys", "TMSONMOBILE.WEB");
            request.addProperty("CusId", driverId);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(urlMarch);
            androidHttpTransport.call(SOAP_ACTION_MARCH + METHOD_NAME, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            Log.d("Error Save_ReadNewsFeed", e.toString());
            return "error";
        }
    }

}
