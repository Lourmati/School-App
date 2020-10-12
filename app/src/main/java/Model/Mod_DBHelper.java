package Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Presenter.Pres_MetierEtudiant;
import Presenter.Pres_TableauDeBord;

public class Mod_DBHelper {

    private final String API = "https://api3.defiphotos.tk/api/";

    private String  access_token = "";
    private String  token_type = "";
    private String role_id = "";

    public enum Table {
        SECTIONS("sections"),
        QUESTIONS_DEFAULT("questions-defaut"),
        QUESTIONS_GROUP ("questions-groupe"),
        QUESTIONS_PER("questions-personalisees"),
        QUESTIONS("questions"),
        COMMENTS("commentaires"),
        CURRENT_USER("auth/compte"),
        USERS("comptes"),
        ROLES("roles"),
        INTERNSHIPS("stages"),
        INTERNSHIPS_YEARS("annees-scolaire"),
        STUDENTS("etudiants"),
        TEACHERS("enseignants");

        private String type;
        Table(String sections) {
            type = sections;
        }

        public String getType() {
            return type;
        }
    }

    private final String DATABASE = "DATABASE";

    private int comptItem;
    private SharedPreferences TempDB;

    private Context loginContext;

    public Mod_DBHelper(Context loginContext){
        this.loginContext = loginContext;
    }

    public void ConnectUser(final EditText email, final EditText password){
        DisconnectUser();

        JSONObject parameters = new JSONObject();
        try {
            parameters.put("email", email.getText().toString().trim());
            parameters.put("mot_de_passe", password.getText().toString().trim());
            parameters.put("remember_me", false);
        } catch (Exception e) {
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API + "auth/connexion",
                parameters,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("onResponse", response.toString());
                        access_token = findString(response, "access_token");
                        token_type = findString(response, "token_type");
                        role_id = findString(response, "role_id");


                        if(Integer.parseInt(role_id) == 2) openTableauDeBord();
                        else openMetierEtudiant();
                        // TEMPORARY
                        // if(Integer.parseInt(role_id) == 2) openMetierEtudiant();
                        // else openMetierEtudiant();

                        obtenirInfo(API+ Table.SECTIONS.getType(), Table.SECTIONS.getType());
                        obtenirInfo(API+ Table.QUESTIONS_DEFAULT.getType(), Table.QUESTIONS_DEFAULT.getType());
                        obtenirInfo(API+ Table.QUESTIONS_PER.getType(), Table.QUESTIONS_PER.getType());
                        obtenirInfo(API+ Table.QUESTIONS_GROUP.getType(), Table.QUESTIONS_GROUP.getType());
                        obtenirInfo(API+ Table.QUESTIONS.getType(), Table.QUESTIONS.getType());
                        obtenirInfo(API+ Table.COMMENTS.getType(), Table.COMMENTS.getType());
                        obtenirInfo(API+ Table.CURRENT_USER.getType(), Table.CURRENT_USER.getType());
                        obtenirInfo(API+ Table.USERS.getType(), Table.USERS.getType());
                        obtenirInfo(API+ Table.ROLES.getType(), Table.ROLES.getType());
                        obtenirInfo(API+ Table.INTERNSHIPS.getType(), Table.INTERNSHIPS.getType());
                        obtenirInfo(API+ Table.INTERNSHIPS_YEARS.getType(), Table.INTERNSHIPS_YEARS.getType());
                        obtenirInfo(API+ Table.CURRENT_USER.getType(), Table.CURRENT_USER.getType());
                        obtenirInfo(API+ Table.STUDENTS.getType(), Table.STUDENTS.getType());
                        obtenirInfo(API+ Table.TEACHERS.getType(), Table.TEACHERS.getType());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.toString());

                        email.setError("Invalid");
                        password.setError("Invalid");
                    }
                });
        VolleySingleton.getInstance(loginContext).addToRequestQueue(request);
    }

    public void AddUser(String nom, String prenom, String email, String mot_de_passe, String mot_de_passe_confirmations){
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("nom", nom);
            parameters.put("prenom", prenom);
            parameters.put("email", email);
            parameters.put("mot_de_passe", mot_de_passe);
            parameters.put("mot_de_passe_confirmation", mot_de_passe_confirmations);
        } catch (Exception e) {
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API + Table.USERS,
                parameters,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("onResponse", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.toString());
                    }
                });
        VolleySingleton.getInstance(loginContext).addToRequestQueue(request);
    }

    public void AddStage(String internshipName){
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("nom_stage", internshipName);
        } catch (Exception e) {
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API + Table.INTERNSHIPS,
                parameters,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("onResponse", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.toString());
                    }
                });
        VolleySingleton.getInstance(loginContext).addToRequestQueue(request);
    }

    public void AddDefaultQuestion(String question){
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("question", question);
        } catch (Exception e) {
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API + Table.QUESTIONS_DEFAULT,
                parameters,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("onResponse", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.toString());
                    }
                });
        VolleySingleton.getInstance(loginContext).addToRequestQueue(request);
    }

    public void AddGroupQuestion(String question){
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("question", question);
        } catch (Exception e) {
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API + Table.QUESTIONS_GROUP,
                parameters,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("onResponse", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.toString());
                    }
                });
        VolleySingleton.getInstance(loginContext).addToRequestQueue(request);
    }

    public void AddPerQuestion(String question){
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("question", question);
        } catch (Exception e) {
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API + Table.QUESTIONS_PER,
                parameters,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("onResponse", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.toString());
                    }
                });
        VolleySingleton.getInstance(loginContext).addToRequestQueue(request);
    }

    public void AddComment(String commentType, String data){
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("comment_type", commentType);
            parameters.put("data", data);
        } catch (Exception e) {
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API + Table.COMMENTS,
                parameters,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("onResponse", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.toString());
                    }
                });
        VolleySingleton.getInstance(loginContext).addToRequestQueue(request);
    }

    public static String findString(JSONObject jObj, String findKey) {
        String finalValue = "";
        try {
            finalValue = findValue(jObj, findKey);
        } catch(JSONException e) {

        }
        return finalValue;
    }

    private void obtenirInfo(String url, final String nom) {
        if (access_token.isEmpty()) {
            return;
        }

        TempDB = getSharedPreference(Context.MODE_PRIVATE);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override public void onResponse(JSONObject response) {
                        Log.i("onResponse", response.toString());
                        JSONArray array = null;
                        try {
                            array = response.getJSONArray("data");
                        } catch (JSONException e) {

                        }
                        if (array != null) {
                            parseJSONArray(array, nom);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.toString());
                    }
                })
        {
            @Override public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", token_type + " " + access_token);
                return headers;
            }
        };
        VolleySingleton.getInstance(loginContext).addToRequestQueue(request);
    }

    private SharedPreferences getSharedPreference(int PrefContext) {

        return loginContext.getSharedPreferences(DATABASE, PrefContext);

    }

    private void parseJSONArray(JSONArray array, String nom) {
        if (array == null || array.length() == 0) {
            return;
        }
        for (int i = 0; i < array.length(); i++) {
            JSONObject  obj = null;
            try {
                obj = array.getJSONObject(i);
            } catch(Exception e)
            {

            }
            enumerateObjet(obj, nom);
        }
    }

    public void enumerateObjet(JSONObject jsonObject, String nom) {
        if (jsonObject == null) {
            return;
        }

        String id = "";
        String SetData = "";

        Iterator<String> keys = jsonObject.keys();

        SharedPreferences.Editor edit = TempDB.edit();

        while(keys.hasNext()) {
            String key = keys.next();
            Object obj = null;
            try {
                obj = jsonObject.get(key);
            } catch (JSONException e) {

            }
            if (obj != null) {
                if (obj instanceof JSONObject) {
                    enumerateObjet((JSONObject) obj, nom);
                } else {

                    if(key.matches("^id$")){
                        id = obj.toString();
                    }

                    SetData += key+"="+obj.toString()+";";
                    //Log.d("enumerateObjet", nom + "_" + id +"========" + SetData);
                    edit.putString(nom+"_"+id, SetData);

                }
            }
        }

        edit.commit();
    }

    public static String findValue(JSONObject jObj, String findKey) throws JSONException {
        String finalValue = "";
        if (jObj == null) {
            return finalValue;
        }

        if (findKey.isEmpty()) {
            return finalValue;
        }

        Object obj = jObj.get(findKey);
        if (obj != null) {
            finalValue = obj.toString();
        }
        return finalValue;
    }

    public void DisconnectUser(){
        if (access_token.isEmpty()) {
            return;
        }
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API+"auth/deconnexion",
                null,
                new Response.Listener<JSONObject>() {
                    @Override public void onResponse(JSONObject response) {
                        Log.i("onResponse", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.toString());
                    }
                });
        VolleySingleton.getInstance(loginContext).addToRequestQueue(request);
    }

    public String GetData(Table tableName,String id){
        TempDB = getSharedPreference(Context.MODE_APPEND);
        String data = TempDB.getString(tableName.getType()+"_"+id, "not_found");
        return data;
    }
    public String GetDataColumn(Table tableName,String id, String ColumnName){

        String data = GetData(tableName, id);
        String[] Columns = data.split(";");

        String value= "not_found";

        if(data != "not_found") {
            for (String field : Columns) {

                if (field.contains(ColumnName)) {
                    String[] Column = field.split("=");
                    value = Column[1];
                }
            }
        }

        return value;
    }

    public List<String> GetTableContent(Table tableName){
        TempDB = getSharedPreference(Context.MODE_APPEND);
        String data = "";
        List<String> tab = new ArrayList<>();
        int id = 1;

        while(data != "not_found") {

            String strId = Integer.toString(id);
            data = TempDB.getString(tableName.getType() + "_" + strId, "not_found");

            if(data != "not_found") {
                tab.add(data);
            }

            id++;
        }

        return tab;
    }
    private void openMetierEtudiant() {
        Intent intent = new Intent(loginContext, Pres_MetierEtudiant.class);
        loginContext.startActivity(intent);
    }

    private void openTableauDeBord() {
        Intent intent = new Intent(loginContext, Pres_TableauDeBord.class);
        loginContext.startActivity(intent);
    }


}
