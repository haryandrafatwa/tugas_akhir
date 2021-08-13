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
    @SerializedName("plot_pembimbing")
    private int plot_pembimbing;

    @Expose
    @SerializedName("plot_penguji")
    private int plot_penguji;

    @Expose
    @SerializedName("nip_pembimbing_1")
    private String nip_pembimbing_1;

    @Expose
    @SerializedName("nip_pembimbing_2")
    private String nip_pembimbing_2;

    @Expose
    @SerializedName("nama_pembimbing_1")
    private String nama_pembimbing_1;

    @Expose
    @SerializedName("nama_pembimbing_2")
    private String nama_pembimbing_2;

    @Expose
    @SerializedName("nip_penguji_1")
    private String nip_penguji_1;

    @Expose
    @SerializedName("nip_penguji_2")
    private String nip_penguji_2;

    @Expose
    @SerializedName("nama_penguji_1")
    private String nama_penguji_1;

    @Expose
    @SerializedName("nama_penguji_2")
    private String nama_penguji_2;

    @Expose
    @SerializedName("nilai_pembimbing_1")
    private String nilai_pembimbing_1;

    @Expose
    @SerializedName("nilai_pembimbing_2")
    private String nilai_pembimbing_2;

    @Expose
    @SerializedName("nilai_penguji_1")
    private String nilai_penguji_1;

    @Expose
    @SerializedName("nilai_penguji_2")
    private String nilai_penguji_2;

    @Expose
    @SerializedName("nilai_total")
    private String nilai_total;

    @Expose
    @SerializedName("sk_expired")
    private String sk_expired;

    @Expose
    @SerializedName("sk_status")
    private int sk_status;

    @Expose
    @SerializedName("sidang_tanggal")
    private String sidang_tanggal;

    @Expose
    @SerializedName("sidang_status")
    private String sidang_status;

    @Expose
    @SerializedName("sidang_review")
    private String sidang_review;

    @Expose
    @SerializedName("username")
    private String username;

    @Expose
    @SerializedName("bimbingan_sum")
    private int bimbingan_sum;

    @Expose
    @SerializedName("pending_sum")
    private int pending_sum;

    public Mahasiswa(String mhs_nim, String mhs_nama, String angkatan, String mhs_kontak, String mhs_foto, String judul, String judul_inggris, String mhs_email,
                     int plot_pembimbing, int plot_penguji, String nip_pembimbing_1, String nip_pembimbing_2, String nama_pembimbing_1, String nama_pembimbing_2,
                     String nip_penguji_1, String nip_penguji_2, String nama_penguji_1, String nama_penguji_2, String nilai_pembimbing_1, String nilai_pembimbing_2,
                     String nilai_penguji_1, String nilai_penguji_2, String nilai_total, String sk_expired, int sk_status, String sidang_tanggal, String sidang_status, String sidang_review,
                     String username, int bimbingan_sum, int pending_sum) {
        this.mhs_nim = mhs_nim;
        this.mhs_nama = mhs_nama;
        this.angkatan = angkatan;
        this.mhs_kontak = mhs_kontak;
        this.mhs_foto = mhs_foto;
        this.judul = judul;
        this.judul_inggris = judul_inggris;
        this.mhs_email = mhs_email;
        this.plot_pembimbing = plot_pembimbing;
        this.plot_penguji = plot_penguji;
        this.nip_pembimbing_1 = nip_pembimbing_1;
        this.nip_pembimbing_2 = nip_pembimbing_2;
        this.nama_pembimbing_1 = nama_pembimbing_1;
        this.nama_pembimbing_2 = nama_pembimbing_2;
        this.nip_penguji_1 = nip_penguji_1;
        this.nip_penguji_2 = nip_penguji_2;
        this.nama_penguji_1 = nama_penguji_1;
        this.nama_penguji_2 = nama_penguji_2;
        this.nilai_pembimbing_1 = nilai_pembimbing_1;
        this.nilai_pembimbing_2 = nilai_pembimbing_2;
        this.nilai_penguji_1 = nilai_penguji_1;
        this.nilai_penguji_2 = nilai_penguji_2;
        this.nilai_total = nilai_total;
        this.sk_expired = sk_expired;
        this.sk_status = sk_status;
        this.sidang_tanggal = sidang_tanggal;
        this.sidang_status = sidang_status;
        this.sidang_review = sidang_review;
        this.username = username;
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

    public int getPlot_pembimbing() {
        return plot_pembimbing;
    }

    public void setPlot_pembimbing(int plot_pembimbing) {
        this.plot_pembimbing = plot_pembimbing;
    }

    public int getPlot_penguji() {
        return plot_penguji;
    }

    public void setPlot_penguji(int plot_penguji) {
        this.plot_penguji = plot_penguji;
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

    public String getNilai_total() {
        return nilai_total;
    }

    public void setNilai_total(String nilai_total) {
        this.nilai_total = nilai_total;
    }

    public String setSk_detail(){
        String detail = null;
        if (this.sk_status == 1){
            detail = App.self().getResources().getString(R.string.status_sk_pasif);
        }else{
            if (this.sk_expired != null){
                Locale locale = new Locale("in", "ID");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
                try {
                    Date date = format.parse(this.sk_expired);
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
        }
        return detail;
    }


    public String setSidangStatus(){
        try {
            Locale locale = new Locale("in", "ID");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
            Date date = format.parse(sidang_tanggal);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String month_name = new SimpleDateFormat("MMMM", locale).format(calendar.getTime());
            return "Sidang pada " + calendar.get(Calendar.DATE)+" "+month_name+" "+calendar.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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

    public String getSidang_tanggal() {
        return sidang_tanggal;
    }

    public void setSidang_tanggal(String sidang_tanggal) {
        this.sidang_tanggal = sidang_tanggal;
    }

    public String getSidang_status() {
        return sidang_status;
    }

    public void setSidang_status(String sidang_status) {
        this.sidang_status = sidang_status;
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

    public String getNama_pembimbing_1() {
        return nama_pembimbing_1;
    }

    public void setNama_pembimbing_1(String nama_pembimbing_1) {
        this.nama_pembimbing_1 = nama_pembimbing_1;
    }

    public String getNama_pembimbing_2() {
        return nama_pembimbing_2;
    }

    public void setNama_pembimbing_2(String nama_pembimbing_2) {
        this.nama_pembimbing_2 = nama_pembimbing_2;
    }

    public String getNip_penguji_1() {
        return nip_penguji_1;
    }

    public void setNip_penguji_1(String nip_penguji_1) {
        this.nip_penguji_1 = nip_penguji_1;
    }

    public String getNip_penguji_2() {
        return nip_penguji_2;
    }

    public void setNip_penguji_2(String nip_penguji_2) {
        this.nip_penguji_2 = nip_penguji_2;
    }

    public String getNama_penguji_1() {
        return nama_penguji_1;
    }

    public void setNama_penguji_1(String nama_penguji_1) {
        this.nama_penguji_1 = nama_penguji_1;
    }

    public String getNama_penguji_2() {
        return nama_penguji_2;
    }

    public void setNama_penguji_2(String nama_penguji_2) {
        this.nama_penguji_2 = nama_penguji_2;
    }

    public String getNilai_pembimbing_1() {
        return nilai_pembimbing_1;
    }

    public void setNilai_pembimbing_1(String nilai_pembimbing_1) {
        this.nilai_pembimbing_1 = nilai_pembimbing_1;
    }

    public String getNilai_pembimbing_2() {
        return nilai_pembimbing_2;
    }

    public void setNilai_pembimbing_2(String nilai_pembimbing_2) {
        this.nilai_pembimbing_2 = nilai_pembimbing_2;
    }

    public String getNilai_penguji_1() {
        return nilai_penguji_1;
    }

    public void setNilai_penguji_1(String nilai_penguji_1) {
        this.nilai_penguji_1 = nilai_penguji_1;
    }

    public String getNilai_penguji_2() {
        return nilai_penguji_2;
    }

    public void setNilai_penguji_2(String nilai_penguji_2) {
        this.nilai_penguji_2 = nilai_penguji_2;
    }

    public String getSidang_review() {
        return sidang_review;
    }

    public void setSidang_review(String sidang_review) {
        this.sidang_review = sidang_review;
    }

    public static Creator<Mahasiswa> getCREATOR() {
        return CREATOR;
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
        dest.writeInt(this.plot_pembimbing);
        dest.writeInt(this.plot_penguji);
        dest.writeString(this.username);
        dest.writeString(this.sk_expired);
        dest.writeInt(this.sk_status);
        dest.writeString(this.sidang_status);
        dest.writeString(this.sidang_tanggal);
        dest.writeString(this.nip_pembimbing_1);
        dest.writeString(this.nip_pembimbing_2);
        dest.writeString(this.nama_pembimbing_1);
        dest.writeString(this.nama_pembimbing_2);
        dest.writeString(this.nilai_pembimbing_1);
        dest.writeString(this.nilai_pembimbing_2);
        dest.writeString(this.nip_penguji_1);
        dest.writeString(this.nip_penguji_2);
        dest.writeString(this.nama_penguji_1);
        dest.writeString(this.nama_penguji_2);
        dest.writeString(this.nilai_penguji_1);
        dest.writeString(this.nilai_penguji_2);
        dest.writeString(this.nilai_total);
        dest.writeString(this.sidang_review);
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
        this.plot_pembimbing = in.readInt();
        this.plot_penguji = in.readInt();
        this.username = in.readString();
        this.sk_expired = in.readString();
        this.sk_status = in.readInt();
        this.sidang_status = in.readString();
        this.sidang_tanggal = in.readString();
        this.nip_pembimbing_1 = in.readString();
        this.nip_pembimbing_2 = in.readString();
        this.nama_pembimbing_1 = in.readString();
        this.nama_pembimbing_2 = in.readString();
        this.nilai_pembimbing_1 = in.readString();
        this.nilai_pembimbing_2 = in.readString();
        this.nip_penguji_1 = in.readString();
        this.nip_penguji_2 = in.readString();
        this.nama_penguji_1 = in.readString();
        this.nama_penguji_2 = in.readString();
        this.nilai_penguji_1 = in.readString();
        this.nilai_penguji_2 = in.readString();
        this.nilai_total = in.readString();
        this.sidang_review = in.readString();
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
