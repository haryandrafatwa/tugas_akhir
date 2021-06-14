package org.d3ifcool.finpro.core.components;

import android.content.Context;

import org.d3ifcool.finpro.core.mediators.interfaces.prodi.LoginMediator;

public class ProgressDialog extends android.app.ProgressDialog implements Component {

    private LoginMediator mediator;

    public ProgressDialog(Context context) {
        super(context);
    }

    @Override
    public void setMediator(LoginMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String getName() {
        return "ProgressDialog";
    }
}
