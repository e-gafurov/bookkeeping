package com.ris.egafurov.bookkeeping;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ServiceOFD {

    private static final String FIND_FISCAL = "https://consumer.1-ofd.ru/api/tickets/find-ticket";
    private static final String GET_TIKET = "https://consumer.1-ofd.ru/api/tickets/ticket";
    private static final class Fields {
        public static final String FISCALDRIVEID = "fiscalDriveId";
        public static final String MFISCALDOCUMENTNUMBER = "mFiscalDocumentNumber";
        public static final String MFISCALID = "mFiscalId";
    }

    private Context mContext;
    private String mFiscalDriveId;
    private String mFiscalDocumentNumber;
    private String mFiscalId;
    private JSONObject lastResult;

    //fiscalDriveId: "9288000100035206", fiscalDocumentNumber: "54802", fiscalId: "1977932697" - информация для отладки
    public ServiceOFD(Context context, String fiscalDriveId, String fiscalDocumentNumber, String fiscalId)
    {
        mContext = context;
        mFiscalDriveId = fiscalDriveId;
        mFiscalDocumentNumber = fiscalDocumentNumber;
        mFiscalId = fiscalId;
    }

    public Expense GetExpense()
    {
        Expense expense = new Expense();
        GetDataFromService(FIND_FISCAL, getFiscalData());
        return expense;
    }

    private JSONObject getFiscalData(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Fields.FISCALDRIVEID, mFiscalDriveId);
            jsonObject.put(Fields.MFISCALDOCUMENTNUMBER, mFiscalDocumentNumber);
            jsonObject.put(Fields.MFISCALID, mFiscalId);
        } catch (JSONException ex) {
            Log.e("getDataFiscal", ex.getMessage());
        }
        return jsonObject;
    }


    private JSONObject GetDataFromService(String uri, JSONObject params){
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        //JSONObject result = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, uri, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("GetResponse", response.toString());
                        lastResult = response;
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("getDataFiscal", error.getMessage());
                    }
                });
        requestQueue.add(jsonObjectRequest);
        return lastResult;
    }
}
