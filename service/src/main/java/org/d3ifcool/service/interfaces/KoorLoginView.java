package org.d3ifcool.service.interfaces;

import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.KoordinatorPa;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 24/03/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public interface KoorLoginView {

    void showProgress();

    void hideProgress();

    void onRequestSuccess(String message, KoordinatorPa koordinatorPa);

    void onRequestError(String message);
}
