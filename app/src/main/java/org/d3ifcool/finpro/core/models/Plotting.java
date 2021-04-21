package org.d3ifcool.finpro.core.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 27/01/2019.
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
public class Plotting implements Parcelable {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("nip_pembimbing_1")
    private String nip_pembimbing_1;

    @Expose
    @SerializedName("nama_pembimbing_1")
    private String nama_pembimbing_1;

    @Expose
    @SerializedName("kode_pembimbing_1")
    private String kode_pembimbing_1;

    @Expose
    @SerializedName("kode_pembimbing_2")
    private String kode_pembimbing_2;

    @Expose
    @SerializedName("nip_pembimbing_2")
    private String nip_pembimbing_2;

    @Expose
    @SerializedName("nama_pembimbing_2")
    private String nama_pembimbing_2;

    @Expose
    @SerializedName("success")
    private int success;

    @Expose
    @SerializedName("message")
    private String message;

    public Plotting(int success, String message) {
        this.success = success;
        this.message = message;
    }

    public Plotting(int id, String nip_pembimbing_1, String nama_pembimbing_1, String kode_pembimbing_1, String kode_pembimbing_2, String nip_pembimbing_2, String nama_pembimbing_2, String message, int success) {
        this.id = id;
        this.nip_pembimbing_1 = nip_pembimbing_1;
        this.nama_pembimbing_1 = nama_pembimbing_1;
        this.kode_pembimbing_1 = kode_pembimbing_1;
        this.kode_pembimbing_2 = kode_pembimbing_2;
        this.nip_pembimbing_2 = nip_pembimbing_2;
        this.nama_pembimbing_2 = nama_pembimbing_2;
        this.success = success;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNip_pembimbing_1() {
        return nip_pembimbing_1;
    }

    public void setNip_pembimbing_1(String nip_pembimbing_1) {
        this.nip_pembimbing_1 = nip_pembimbing_1;
    }

    public String getNama_pembimbing_1() {
        return nama_pembimbing_1;
    }

    public void setNama_pembimbing_1(String nama_pembimbing_1) {
        this.nama_pembimbing_1 = nama_pembimbing_1;
    }

    public String getKode_pembimbing_1() {
        return kode_pembimbing_1;
    }

    public void setKode_pembimbing_1(String kode_pembimbing_1) {
        this.kode_pembimbing_1 = kode_pembimbing_1;
    }

    public String getKode_pembimbing_2() {
        return kode_pembimbing_2;
    }

    public void setKode_pembimbing_2(String kode_pembimbing_2) {
        this.kode_pembimbing_2 = kode_pembimbing_2;
    }

    public String getNip_pembimbing_2() {
        return nip_pembimbing_2;
    }

    public void setNip_pembimbing_2(String nip_pembimbing_2) {
        this.nip_pembimbing_2 = nip_pembimbing_2;
    }

    public String getNama_pembimbing_2() {
        return nama_pembimbing_2;
    }

    public void setNama_pembimbing_2(String nama_pembimbing_2) {
        this.nama_pembimbing_2 = nama_pembimbing_2;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    protected Plotting(Parcel in) {
        id = in.readInt();
        nip_pembimbing_1 = in.readString();
        nama_pembimbing_1 = in.readString();
        kode_pembimbing_1 = in.readString();
        nip_pembimbing_2 = in.readString();
        nama_pembimbing_2 = in.readString();
        kode_pembimbing_2 = in.readString();
        success = in.readInt();
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nip_pembimbing_1);
        dest.writeString(nama_pembimbing_1);
        dest.writeString(kode_pembimbing_1);
        dest.writeString(nip_pembimbing_2);
        dest.writeString(nama_pembimbing_2);
        dest.writeString(kode_pembimbing_2);
        dest.writeInt(success);
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Plotting> CREATOR = new Creator<Plotting>() {
        @Override
        public Plotting createFromParcel(Parcel in) {
            return new Plotting(in);
        }

        @Override
        public Plotting[] newArray(int size) {
            return new Plotting[size];
        }
    };
}
