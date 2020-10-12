package com.staging.emexcart.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Sharedpreference {

    SharedPreferences pref;
    public SharedPreferences.Editor editor;
    Context _context;
    private static final String PREF_NAME = "emecCart";
    public static final String USER_DETAILS = "user_details";
    public static final String LOGINSTATUS = "login_status";
    public static final String USER_ID = "user_id";
    public static final String USER_MAIL = "user_mail";

    public Sharedpreference(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void saveLoginStatus(boolean loginStatus) {
        editor.putBoolean(LOGINSTATUS, loginStatus);
        editor.commit();
    }

    public boolean getLoginStatus() {
        boolean loginStatus = pref.getBoolean(LOGINSTATUS, false);
        return loginStatus;
    }
    public String getUserID(){
        String userID = pref.getString(USER_ID,"");
        return userID;
    }
    public void saveUserID(String userID){
        editor.putString(USER_ID,userID);
        editor.commit();
    }

    public String getUserMail(){
        String userMail = pref.getString(USER_MAIL,"");
        return userMail;
    }
    public void saveUserMail(String userMail){
        editor.putString(USER_MAIL,userMail);
        editor.commit();
    }
    public void removeData() {
        editor.remove(LOGINSTATUS);
        editor.commit();

    }
}
