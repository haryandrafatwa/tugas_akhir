package org.d3ifcool.finpro.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 24/12/2018.
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
public class Mahasiswa implements Parcelable {

    @Expose
    @SerializedName("mhs_nim")
    private String mhs_nim;

    @Expose
    @SerializedName("mhs_nama")
    private String mhs_nama;

    @Expose
    @SerializedName("angkatan")
    private String angkatan;

    @Expose
    @SerializedName("mhs_kontak")
    private String mhs_kontak;
    @Expose
    @SerializedName("mhs_foto")
    private String mhs_foto;

    @Expose
    @SerializedName("mhs_email")
    private String mhs_email;

    @Expose
    @SerializedName("plot_id")
    private int plot_id;

    @Expose
    @SerializedName("nip_pembimbing_1")
    private String nip_pembimbing_1;

    @Expose
    @SerializedName("nip_pembimbing_2")
    private String nip_pembimbing_2;

    @Expose
    @SerializedName("sk_expired")
    private String sk_expired;

    @Expose
    @SerializedName("sk_status")
    private int sk_status;

    @Expose
    @SerializedName("username")
    private String username;

    public Mahasiswa(String mhs_nim, String mhs_nama, String angkatan, String mhs_kontak, String mhs_foto, String mhs_email, int plot_id, String sk_expired, int sk_status, String username, String nip_pembimbing_1, String nip_pembimbing_2) {
        this.mhs_nim = mhs_nim;
        this.mhs_nama = mhs_nama;
        this.angkatan = angkatan;
        this.mhs_kontak = mhs_kontak;
        this.mhs_foto = mhs_foto;
        this.mhs_email = mhs_email;
        this.plot_id = plot_id;
        this.sk_expired = sk_expired;
        this.sk_status = sk_status;
        this.username = username;
        this.nip_pembimbing_1 = nip_pembimbing_1;
        this.nip_pembimbing_2 = nip_pembimbing_2;
    }

    public String getMhs_nim() {
        return mhs_nim;
    }

    public void setMhs_nim(String mhs_nim) {
        this.mhs_nim = mhs_nim;
    }

    public String getMhs_nama() {
        return mhs_nama;
    }

    public void setMhs_nama(String mhs_nama) {
        this.mhs_nama = mhs_nama;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public String getMhs_kontak() {
        return mhs_kontak;
    }

    public void setMhs_kontak(String mhs_kontak) {
        this.mhs_kontak = mhs_kontak;
    }

    public String getMhs_foto() {
        return mhs_foto;
    }

    public void setMhs_foto(String mhs_foto) {
        this.mhs_foto = mhs_foto;
    }

    public String getMhs_email() {
        return mhs_email;
    }

    public void setMhs_email(String mhs_email) {
        this.mhs_email = mhs_email;
    }

    public int getPlot_id() {
        return plot_id;
    }

    public void setPlot_id(int plot_id) {
        this.plot_id = plot_id;
    }

    public String getNip_pembimbing_1() {
        return nip_pembimbing_1;
    }

    public void setNip_pembimbing_1(String nip_pembimbing_1) {
        this.nip_pembimbing_1 = nip_pembimbing_1;
    }

    public String getNip_pembimbing_2() {
        return nip_pembimbing_2;
    }

    public void setNip_pembimbing_2(String nip_pembimbing_2) {
        this.nip_pembimbing_2 = nip_pembimbing_2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSk_expired() {
        return sk_expired;
    }

    public void setSk_expired(String sk_expired) {
        this.sk_expired = sk_expired;
    }

    public int getSk_status() {
        return sk_status;
    }

    public void setSk_status(int sk_status) {
        this.sk_status = sk_status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mhs_nim);
        dest.writeString(this.mhs_nama);
        dest.writeString(this.angkatan);
        dest.writeString(this.mhs_kontak);
        dest.writeString(this.mhs_foto);
        dest.writeString(this.mhs_email);
        dest.writeInt(this.plot_id);
        dest.writeString(this.username);
        dest.writeString(this.sk_expired);
        dest.writeInt(this.sk_status);
        dest.writeString(this.nip_pembimbing_1);
        dest.writeString(this.nip_pembimbing_2);
    }

    protected Mahasiswa(Parcel in) {
        this.mhs_nim = in.readString();
        this.mhs_nama = in.readString();
        this.angkatan = in.readString();
        this.mhs_kontak = in.readString();
        this.mhs_foto = in.readString();
        this.mhs_email = in.readString();
        this.plot_id = in.readInt();
        this.username = in.readString();
        this.sk_expired = in.readString();
        this.sk_status = in.readInt();
        this.nip_pembimbing_1 = in.readString();
        this.nip_pembimbing_2 = in.readString();
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel source) {
            return new Mahasiswa(source);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };
}
