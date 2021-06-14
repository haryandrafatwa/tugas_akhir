package org.d3ifcool.finpro.core.components;

import org.d3ifcool.finpro.core.mediators.interfaces.prodi.LoginMediator;

public interface Component {
    void setMediator(LoginMediator mediator);
    String getName();
}
