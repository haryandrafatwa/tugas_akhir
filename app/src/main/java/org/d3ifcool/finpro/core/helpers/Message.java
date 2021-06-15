package org.d3ifcool.finpro.core.helpers;

import android.net.Uri;

import org.d3ifcool.finpro.core.components.Components;
import org.d3ifcool.finpro.core.mediators.interfaces.prodi.Mediator;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Koordinator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;

import java.util.ArrayList;

import okhttp3.ResponseBody;

public class Message implements Components {
    private String component, event, text, color, url;
    private boolean isEnabled;
    private Uri uri;
    private ArrayList item;
    private Mediator mediator;
    private int visibility;
    private ResponseBody body;
    private Class aClass;
    private Mahasiswa mahasiswa;
    private Plotting plotting;
    private Dosen dosen;
    private Koordinator koordinator;

    public Message() {
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String getName() {
        return "Message";
    }

    public Message setComponent(String component) {
        this.component = component;
        return this;
    }

    public Message setEvent(String event) {
        this.event = event;
        return this;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public Message setColor(String color) {
        this.color = color;
        return this;
    }

    public Message setUrl(String url) {
        this.url = url;
        return this;
    }

    public Message setEnabled(boolean enabled) {
        isEnabled = enabled;
        return this;
    }

    public Message setUri(Uri uri) {
        this.uri = uri;
        return this;
    }

    public Message setItem(ArrayList item) {
        this.item = item;
        return this;
    }

    public Message setVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public Message setResponseBody(ResponseBody responseBody) {
        this.body = responseBody;
        return this;
    }

    public Message setaClass(Class aClass) {
        this.aClass = aClass;
        return this;
    }

    public Message setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
        return this;
    }

    public Message setPlotting(Plotting plotting) {
        this.plotting = plotting;
        return this;
    }

    public Message setDosen(Dosen dosen) {
        this.dosen = dosen;
        return this;
    }

    public Message setKoordinator(Koordinator koordinator) {
        this.koordinator = koordinator;
        return this;
    }

    public String getComponent() {
        return component;
    }

    public String getEvent() {
        return event;
    }

    public String getText() {
        return text;
    }

    public String getColor() {
        return color;
    }

    public String getUrl() {
        return url;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public Uri getUri() {
        return uri;
    }

    public ArrayList getItem() {
        return item;
    }

    public int getVisibility() {
        return visibility;
    }

    public ResponseBody getBody() {
        return body;
    }

    public Class getaClass() {
        return aClass;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public Plotting getPlotting() {
        return plotting;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public Koordinator getKoordinator() {
        return koordinator;
    }
}
