package com.beksultan.e_trans.db;

import android.content.SharedPreferences;
import com.beksultan.e_trans.App;
import com.beksultan.e_trans.model.user.User;
import static com.beksultan.e_trans.Constant.KEY;
import static com.beksultan.e_trans.Constant.USER_IS_LOGGED;
import static com.beksultan.e_trans.utils.StringUtils.isOk;

public class UserPreferences {

    public static void set(User user) {
        SharedPreferences preferences = App.getInstance().getPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        if (isOk(user.token)) editor.putString(KEY, user.getAuthToken());
        editor.putBoolean(USER_IS_LOGGED, true);
        editor.apply();
    }

    public static User get() {
        SharedPreferences preferences = App.getInstance().getPreferences();
        User user = new User();
        user.token = preferences.getString(KEY, "");
        return user;
    }

    public static void clear() {
        SharedPreferences preferences = App.getInstance().getPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY, "");
        editor.putBoolean(USER_IS_LOGGED, false);
        editor.apply();    }
}
