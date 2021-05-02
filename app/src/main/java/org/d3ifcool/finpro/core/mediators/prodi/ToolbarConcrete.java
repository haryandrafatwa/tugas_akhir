package org.d3ifcool.finpro.core.mediators.prodi;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.ProdiMediator;

public class ToolbarConcrete implements ProdiMediator {
    @Override
    public void Notify(int id) {
        switch (id){
            case R.id.toolbar_menu_ubah:
                break;
        }
    }

    @Override
    public void message(String component, String event) {

    }
}
