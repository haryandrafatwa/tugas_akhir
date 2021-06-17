package org.d3ifcool.finpro.core.models;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.IdRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    @SerializedName("judul")
    private String judul;

    @Expose
    @SerializedName("judul_inggris")
    private String judul_inggris;

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

    @Expose
    @SerializedName("bimbingan_sum")
    private int bimbingan_sum;

    @Expose
    @SerializedName("pending_sum")
    private int pending_sum;

    public Mahasiswa(String mhs_nim, String mhs_nama, String angkatan, String mhs_kontak, String mhs_foto, String mhs_email, String judul, String judul_inggris, int plot_id,
                     String sk_expired, int sk_status, String username, String nip_pembimbing_1, String nip_pembimbing_2, int bimbingan_sum, int pending_sum) {
        this.mhs_nim = mhs_nim;
        this.mhs_nama = mhs_nama;
        this.angkatan = angkatan;
        this.mhs_kontak = mhs_kontak;
        this.mhs_foto = mhs_foto;
        this.mhs_email = mhs_email;
        this.judul = judul;
        this.judul_inggris = judul_inggris;
        this.plot_id = plot_id;
        this.sk_expired = sk_expired;
        this.sk_status = sk_status;
        this.username = username;
        this.nip_pembimbing_1 = nip_pembimbing_1;
        this.nip_pembimbing_2 = nip_pembimbing_2;
        this.bimbingan_sum = bimbingan_sum;
        this.pending_sum = pending_sum;
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

    public String setSk_detail(){
        String detail = null;
        if (sk_status == 1){
            detail = App.self().getResources().getString(R.string.status_sk_pasif);
        }else{
            Locale locale = new Locale("in", "ID");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
            try {
                Date date = format.parse(sk_expired);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String month_name = new SimpleDateFormat("MMMM", locale).format(calendar.getTime());
                String tempJudul = calendar.get(Calendar.DATE)+" "+month_name+" "+calendar.get(Calendar.YEAR);
                if(sk_status == 2){
                    detail = App.self().getResources().getString(R.string.status_sk_aktif)+" "+tempJudul;
                }else if(sk_status == 3){
                    detail = App.self().getResources().getString(R.string.status_sk_kadaluwarsa)+" "+tempJudul+" )";
                }else{
                    detail = App.self().getResources().getString(R.string.status_sk_perpanjang);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return detail;
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

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getJudul_inggris() {
        return judul_inggris;
    }

    public void setJudul_inggris(String judul_inggris) {
        this.judul_inggris = judul_inggris;
    }

    public int getBimbingan_sum() {
        return bimbingan_sum;
    }

    public void setBimbingan_sum(int bimbingan_sum) {
        this.bimbingan_sum = bimbingan_sum;
    }

    public int getPending_sum() {
        return pending_sum;
    }

    public void setPending_sum(int pending_sum) {
        this.pending_sum = pending_sum;
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
        dest.writeString(this.judul);
        dest.writeString(this.judul_inggris);
        dest.writeInt(this.plot_id);
        dest.writeString(this.username);
        dest.writeString(this.sk_expired);
        dest.writeInt(this.sk_status);
        dest.writeString(this.nip_pembimbing_1);
        dest.writeString(this.nip_pembimbing_2);
        dest.writeInt(this.bimbingan_sum);
        dest.writeInt(this.pending_sum);
    }

    protected Mahasiswa(Parcel in) {
        this.mhs_nim = in.readString();
        this.mhs_nama = in.readString();
        this.angkatan = in.readString();
        this.mhs_kontak = in.readString();
        this.mhs_foto = in.readString();
        this.mhs_email = in.readString();
        this.judul = in.readString();
        this.judul_inggris = in.readString();
        this.plot_id = in.readInt();
        this.username = in.readString();
        this.sk_expired = in.readString();
        this.sk_status = in.readInt();
        this.nip_pembimbing_1 = in.readString();
        this.nip_pembimbing_2 = in.readString();
        this.bimbingan_sum = in.readInt();
        this.pending_sum = in.readInt();
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
