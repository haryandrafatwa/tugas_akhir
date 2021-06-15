package org.d3ifcool.finpro.core.mediators.prodi;

import android.graphics.Color;
import android.widget.TextView;

import org.d3ifcool.finpro.core.components.Component;
import org.d3ifcool.finpro.core.components.ProgressDialog;
import org.d3ifcool.finpro.core.components.SessionManager;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.LoginMediator;

public class LoginConcrete implements LoginMediator {

    private ProgressDialog progressDialog;
    private SessionManager sessionManager;
    private TextView textView;

    @Override
    public void registerComponent(Component newComponent) {
        newComponent.setMediator(this);
        switch (newComponent.getName()){
            case "ProgressDialog":
                progressDialog = (ProgressDialog) newComponent;
                break;
            case "SessionManager":
                sessionManager = (SessionManager) newComponent;
                break;
        }
    }

    @Override
    public String getSessionToken() {
        return sessionManager.getSessionToken();
    }

    @Override
    public String getSessionPengguna() {
        return sessionManager.getSessionPengguna();
    }

    public void createSession(String username, String pengguna, String token) {
        sessionManager.createSession(username, pengguna, token);
    }

    public void setMessagePD(String message) {
        progressDialog.setMessage(message);
    }

    public void showPD() {
        progressDialog.show();
    }

    public void dismissPD() {
        progressDialog.dismiss();
    }

    @Override
    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void setText(String text) {
        textView.setText(text);
    }

    @Override
    public void setTextColor(String color) {
        textView.setTextColor(Color.parseColor(color));
    }

    @Override
    public void setEnabledTextView(boolean isEnalbed) {
        this.textView.setEnabled(isEnalbed);
    }

    @Override
    public void setVisibilityTV(int visible) {
        this.textView.setVisibility(visible);
    }
}
