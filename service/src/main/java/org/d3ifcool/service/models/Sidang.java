package org.d3ifcool.service.models;

import android.os.Parcel;
import android.os.Parcelable;

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
public class Sidang implements Parcelable {

    @Expose
    @SerializedName("sidang_id")
    private String sidang_id;

    @Expose
    @SerializedName("sidang_review")
    private String sidang_review;

    @Expose
    @SerializedName("sidang_tanggal")
    private String sidang_tanggal;

    @Expose
    @SerializedName("nilai_proposal")
    private int nilai_proposal;

    @Expose
    @SerializedName("nilai_penguji_1")
    private int nilai_penguji_1;

    @Expose
    @SerializedName("nilai_penguji_2")
    private int nilai_penguji_2;

    @Expose
    @SerializedName("nilai_pembimbing")
    private int nilai_pembimbing;

    @Expose
    @SerializedName("nilai_sidang")
    private int nilai_sidang;

    @Expose
    @SerializedName("nilai_total")
    private String nilai_total;


    @Expose
    @SerializedName("sidang_status")
    private String sidang_status;

    @Expose
    @SerializedName("proyek_akhir_id")
    private int proyek_akhir_id;

    public Sidang(String sidang_id, String sidang_review, String sidang_tanggal, int nilai_proposal, int nilai_penguji_1, int nilai_penguji_2, int nilai_pembimbing, int nilai_sidang, String nilai_total, String sidang_status, int proyek_akhir_id) {
        this.sidang_id = sidang_id;
        this.sidang_review = sidang_review;
        this.sidang_tanggal = sidang_tanggal;
        this.nilai_proposal = nilai_proposal;
        this.nilai_penguji_1 = nilai_penguji_1;
        this.nilai_penguji_2 = nilai_penguji_2;
        this.nilai_pembimbing = nilai_pembimbing;
        this.nilai_sidang = nilai_sidang;
        this.nilai_total = nilai_total;
        this.sidang_status = sidang_status;
        this.proyek_akhir_id = proyek_akhir_id;
    }

    protected Sidang(Parcel in) {
        sidang_id = in.readString();
        sidang_review = in.readString();
        sidang_tanggal = in.readString();
        nilai_proposal = in.readInt();
        nilai_penguji_1 = in.readInt();
        nilai_penguji_2 = in.readInt();
        nilai_pembimbing = in.readInt();
        nilai_sidang = in.readInt();
        nilai_total = in.readString();
        sidang_status = in.readString();
        proyek_akhir_id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sidang_id);
        dest.writeString(sidang_review);
        dest.writeString(sidang_tanggal);
        dest.writeInt(nilai_proposal);
        dest.writeInt(nilai_penguji_1);
        dest.writeInt(nilai_penguji_2);
        dest.writeInt(nilai_pembimbing);
        dest.writeInt(nilai_sidang);
        dest.writeString(nilai_total);
        dest.writeString(sidang_status);
        dest.writeInt(proyek_akhir_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Sidang> CREATOR = new Creator<Sidang>() {
        @Override
        public Sidang createFromParcel(Parcel in) {
            return new Sidang(in);
        }

        @Override
        public Sidang[] newArray(int size) {
            return new Sidang[size];
        }
    };

    public String getSidang_id() {
        return sidang_id;
    }

    public void setSidang_id(String sidang_id) {
        this.sidang_id = sidang_id;
    }

    public String getSidang_review() {
        return sidang_review;
    }

    public void setSidang_review(String sidang_review) {
        this.sidang_review = sidang_review;
    }

    public String getSidang_tanggal() {
        return sidang_tanggal;
    }

    public void setSidang_tanggal(String sidang_tanggal) {
        this.sidang_tanggal = sidang_tanggal;
    }

    public String getNilai_total() {
        return nilai_total;
    }

    public void setNilai_total(String nilai_total) {
        this.nilai_total = nilai_total;
    }

    public String getSidang_status() {
        return sidang_status;
    }

    public void setSidang_status(String sidang_status) {
        this.sidang_status = sidang_status;
    }

    public int getProyek_akhir_id() {
        return proyek_akhir_id;
    }

    public void setProyek_akhir_id(int proyek_akhir_id) {
        this.proyek_akhir_id = proyek_akhir_id;
    }

    public int getNilai_proposal() {
        return nilai_proposal;
    }

    public void setNilai_proposal(int nilai_proposal) {
        this.nilai_proposal = nilai_proposal;
    }

    public int getNilai_penguji_1() {
        return nilai_penguji_1;
    }

    public void setNilai_penguji_1(int nilai_penguji_1) {
        this.nilai_penguji_1 = nilai_penguji_1;
    }

    public int getNilai_penguji_2() {
        return nilai_penguji_2;
    }

    public void setNilai_penguji_2(int nilai_penguji_2) {
        this.nilai_penguji_2 = nilai_penguji_2;
    }

    public int getNilai_pembimbing() {
        return nilai_pembimbing;
    }

    public void setNilai_pembimbing(int nilai_pembimbing) {
        this.nilai_pembimbing = nilai_pembimbing;
    }

    public int getNilai_sidang() {
        return nilai_sidang;
    }

    public void setNilai_sidang(int nilai_sidang) {
        this.nilai_sidang = nilai_sidang;
    }
}
