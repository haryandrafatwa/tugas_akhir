package org.d3ifcool.finpro.core.components;

import android.content.Context;

import org.d3ifcool.finpro.core.mediators.LoginMediator;

public class SessionManager extends org.d3ifcool.finpro.core.helpers.SessionManager implements Component {

    private LoginMediator mediator;

    public SessionManager(Context context) {
        super(context);
    }

    @Override
    public void setMediator(LoginMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String getName() {
        return "SessionManager";
    }
}
