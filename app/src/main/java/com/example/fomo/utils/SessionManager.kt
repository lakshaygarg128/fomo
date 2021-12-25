package com.example.fomo.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.fomo.utils.Constants.PREF_NAME

class SessionManager(  // Context
    var _context: Context
) {
    // Shared Preferences
    var pref: SharedPreferences

    // Editor for Shared preferences
    var editor: SharedPreferences.Editor

    // Shared pref mode
    var MODE_MULTI_PROCESS = 0

    // Sharedpref file name
    @JvmName("getPref1")
    fun getPref(): SharedPreferences {
        return _context.getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS)
    }

    /**
     * Set the String data in the preferences.
     */
    fun putStringData(keyname: String?, value: String?) {
        editor.putString(keyname, value)
        editor.commit()
    }

    /**
     * @return the string data from the prefs
     */
    fun getStringData(keyName: String?): String? {
        return pref.getString(keyName, "")
    }

    /**
     * Set the int data in the preferences.
     */
    fun putIntData(keyname: String?, value: Int) {
        editor.putInt(keyname, value)
        editor.commit()
    }

    /**
     * @return the boolean data from the prefs
     */
    fun getIntData(keyName: String?): Int {
        return pref.getInt(keyName, 0)
    }

    /**
     * Set the boolean data in the preferences.
     */
    fun putBooleanData(keyname: String?, value: Boolean) {
        editor.putBoolean(keyname, value)
        editor.commit()
    }

    /**
     * @return the boolean data from the prefs
     */
    fun getBooleanData(keyName: String?): Boolean {
        return pref.getBoolean(keyName, false)
    }

    /**
     * Set the long data in the preferences.
     */
    fun putLongData(keyname: String?, value: Long) {
        editor.putLong(keyname, value)
        editor.commit()
    }

    /**
     * @return the long data from the prefs
     */
    fun getLongData(keyName: String?): Long {
        return pref.getLong(keyName, 99)
    }

    /**
     * remove data from pref
     *
     * @param keyName
     */
    fun removeData(keyName: String?) {
        editor.remove(keyName)
        editor.commit()
    }

    fun removeAll() {
        editor.clear()
        editor.apply()
    } /*

    //Save arrayList of Model type
    public void saveAssignedLocationsToSharedPrefs(List<Locations> LocationModel) {
        Gson gson = new Gson();
        String jsonLocation = gson.toJson(LocationModel);
        editor.putString("LocationArray", jsonLocation);
        editor.commit();
    }

    //get arrayList of Model type
    public ArrayList<Locations> getAssignedLocationsFromSharedPrefs() {
        List<Locations> LocationData;

        String jsonLocation = pref.getString("LocationArray", null);
        Gson gson = new Gson();
        Locations[] LocationItems = gson.fromJson(jsonLocation,
                Locations[].class);

        LocationData = Arrays.asList(LocationItems);
        LocationData = new ArrayList<Locations>(LocationData);

        return (ArrayList<Locations>) LocationData;
    }
*/

    // Constructor
    init {
        pref = _context.getSharedPreferences(PREF_NAME, MODE_MULTI_PROCESS)
        editor = pref.edit()
    }
}