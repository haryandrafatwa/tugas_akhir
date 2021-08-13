package org.d3ifcool.finpro.core.mediators;

import android.opengl.Visibility;
import android.widget.TextView;

import org.d3ifcool.finpro.core.components.Component;

public interface LoginMediator {
    void registerComponent(Component component);

    //todo SessionManager
    String getSessionToken();
    String getSessionPengguna();
    void createSession(String username, String pengguna, String token);

    //todo ProgressDialog
    void setMessagePD(String message);
    void showPD();
    void dismissPD();

    //todo TextView
    void setTextView(TextView textView);
    void setText(String text);
    void setTextColor(String color);
    void setEnabledTextView(boolean isEnalbed);
    void setVisibilityTV(int visible);
}
