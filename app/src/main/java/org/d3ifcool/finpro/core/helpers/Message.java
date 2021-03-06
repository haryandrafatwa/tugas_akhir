package org.d3ifcool.finpro.core.helpers;

import android.net.Uri;

import org.d3ifcool.finpro.core.components.Components;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.JadwalKegiatan;
import org.d3ifcool.finpro.core.models.Koordinator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.core.models.Sidang;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class Message implements Components {
    private String component, event, text, color, url, extraIntent, secondExtraIntent;
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
    private Bimbingan bimbingan;
    private Informasi informasi;
    private JadwalKegiatan jadwalKegiatan;
    private Sidang sidang;
    private List<JadwalKegiatan> kegiatanList;

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

    public Message setExtraIntent(String extraIntent) {
        this.extraIntent = extraIntent;
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

    public Message setSidang(Sidang sidang) {
        this.sidang = sidang;
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

    public Message setBimbingan(Bimbingan bimbingan) {
        this.bimbingan = bimbingan;
        return this;
    }

    public void setInformasi(Informasi informasi) {
        this.informasi = informasi;
    }

    public Message setJadwalKegiatan(JadwalKegiatan jadwalKegiatan) {
        this.jadwalKegiatan = jadwalKegiatan;
        return this;
    }

    public void setKegiatanList(List<JadwalKegiatan> kegiatanList) {
        this.kegiatanList = kegiatanList;
    }

    public String getSecondExtraIntent() {
        return secondExtraIntent;
    }

    public Message setSecondExtraIntent(String secondExtraIntent) {
        this.secondExtraIntent = secondExtraIntent;
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

    public String getExtraIntent() {
        return extraIntent;
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

    public Bimbingan getBimbingan() {
        return bimbingan;
    }

    public Informasi getInformasi() {
        return informasi;
    }

    public JadwalKegiatan getJadwalKegiatan() {
        return jadwalKegiatan;
    }

    public Sidang getSidang() {
        return sidang;
    }

    public List<JadwalKegiatan> getKegiatanList() {
        return kegiatanList;
    }
}
