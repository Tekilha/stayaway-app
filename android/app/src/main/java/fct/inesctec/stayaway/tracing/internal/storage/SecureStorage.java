/*
 * Copyright (c) 2020 INESC TEC <https://www.inesctec.pt>
 *
 * This Source Code Form is subject to the terms of the European Union
 * Public License, v. 1.2. If a copy of the EUPL was not distributed with
 * this file, You can obtain one at https://opensource.org/licenses/EUPL-1.2.
 *
 * SPDX-License-Identifier: EUPL-1.2
 */

/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package fct.inesctec.stayaway.tracing.internal.storage;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SecureStorage {

    private static final String PREFERENCES = "SecureStorage";

    private static final String KEY_INFORM_TIME_REQ = "inform_time_req";
    private static final String KEY_INFORM_CODE_REQ = "inform_code_req";
    private static final String KEY_INFORM_TOKEN_REQ = "inform_token_req";
    private static final String KEY_LAST_SHOWN_CONTACT_ID = "last_shown_contact_id";
    private static final String KEY_LAST_CONFIG_LOAD_SUCCESS = "last_config_load_success";
    private static final String KEY_LAST_CONFIG_LOAD_SUCCESS_APP_VERSION = "last_config_load_success_app_version";
	private static final String KEY_LAST_CONFIG_LOAD_SUCCESS_SDK_INT = "last_config_load_success_sdk_int";
    private static final String KEY_CONFIG_INFOBOX_TITLE = "ghettobox_title";
    private static final String KEY_CONFIG_INFOBOX_TEXT = "ghettobox_text";
    private static final String KEY_CONFIG_INFOBOX_LINK = "ghettobox_link";
    private static final String KEY_CONFIG_INFOBOX_ID = "ghettobox_id";
    private static final String KEY_T_DUMMY = "KEY_T_DUMMY";

    private static SecureStorage instance;

    private SharedPreferences prefs;

    private SecureStorage(@NonNull Context context) {
        try {
            String masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            this.prefs = EncryptedSharedPreferences
                    .create(PREFERENCES, masterKeys, context, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (GeneralSecurityException | IOException e) {
            this.prefs = null;
            e.printStackTrace();
        }
    }

    public static SecureStorage getInstance(Context context) {
        if (instance == null) {
            instance = new SecureStorage(context);
        }
        return instance;
    }

    public void saveInformTimeAndCodeAndToken(String informCode, String informToken) {
        prefs.edit().putLong(KEY_INFORM_TIME_REQ, System.currentTimeMillis())
                .putString(KEY_INFORM_CODE_REQ, informCode)
                .putString(KEY_INFORM_TOKEN_REQ, informToken)
                .apply();
    }

    public void clearInformTimeAndCodeAndToken() {
        prefs.edit().remove(KEY_INFORM_TIME_REQ)
                .remove(KEY_INFORM_CODE_REQ)
                .remove(KEY_INFORM_TOKEN_REQ)
                .apply();
    }

    public long getLastInformRequestTime() {
        return prefs.getLong(KEY_INFORM_TIME_REQ, 0);
    }

    public String getLastInformToken() {
        return prefs.getString(KEY_INFORM_TOKEN_REQ, null);
    }


    public int getLastShownContactId() {
        return prefs.getInt(KEY_LAST_SHOWN_CONTACT_ID, -1);
    }

    public void setLastShownContactId(int contactId) {
        prefs.edit().putInt(KEY_LAST_SHOWN_CONTACT_ID, contactId).apply();
    }

    public long getLastConfigLoadSuccess() {
        return prefs.getLong(KEY_LAST_CONFIG_LOAD_SUCCESS, 0);
    }

    public void setLastConfigLoadSuccess(long time) {
        prefs.edit().putLong(KEY_LAST_CONFIG_LOAD_SUCCESS, time).apply();
    }

    public int getLastConfigLoadSuccessAppVersion() {
		return prefs.getInt(KEY_LAST_CONFIG_LOAD_SUCCESS_APP_VERSION, 0);
	}

	public void setLastConfigLoadSuccessAppVersion(int appVersion) {
		prefs.edit().putInt(KEY_LAST_CONFIG_LOAD_SUCCESS_APP_VERSION, appVersion).apply();
	}

	public int getLastConfigLoadSuccessSdkInt() {
		return prefs.getInt(KEY_LAST_CONFIG_LOAD_SUCCESS_SDK_INT, 0);
	}

	public void setLastConfigLoadSuccessSdkInt(int sdkInt) {
		prefs.edit().putInt(KEY_LAST_CONFIG_LOAD_SUCCESS_SDK_INT, sdkInt).apply();
	}

    public void setInfoboxTitle(String title) {
        prefs.edit().putString(KEY_CONFIG_INFOBOX_TITLE, title).apply();
    }

    public String getInfoboxTitle() {
        return prefs.getString(KEY_CONFIG_INFOBOX_TITLE, null);
    }

    public void setInfoboxText(String text) {
        prefs.edit().putString(KEY_CONFIG_INFOBOX_TEXT, text).apply();
    }

    public String getInfoboxText() {
        return prefs.getString(KEY_CONFIG_INFOBOX_TEXT, null);
    }

    public void setInfoboxLink(String link) {
        prefs.edit().putString(KEY_CONFIG_INFOBOX_LINK, link).apply();
    }

    public String getInfoboxLink() {
        return prefs.getString(KEY_CONFIG_INFOBOX_LINK, null);
    }

    public void setInfoboxId(String id) {
        prefs.edit().putString(KEY_CONFIG_INFOBOX_ID, id).apply();
    }

    public String getInfoboxId() {
        return prefs.getString(KEY_CONFIG_INFOBOX_ID, null);
    }

    public long getTDummy() { return prefs.getLong(KEY_T_DUMMY, -1); }

	public void setTDummy(long time) {
		prefs.edit().putLong(KEY_T_DUMMY, time).apply();
	}
}
