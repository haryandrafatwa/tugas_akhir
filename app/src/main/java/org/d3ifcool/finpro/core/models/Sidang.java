package org.d3ifcool.finpro.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("jam_mulai")
    private String jam_mulai;

    @Expose
    @SerializedName("jam_berakhir")
    private String jam_berakhir;

    @Expose
    @SerializedName("nilai_penguji_1_clo1")
    private String nilai_penguji_1_clo1;

    @Expose
    @SerializedName("nilai_penguji_1_clo2")
    private String nilai_penguji_1_clo2;

    @Expose
    @SerializedName("nilai_penguji_1_clo3")
    private String nilai_penguji_1_clo3;

    @Expose
    @SerializedName("nilai_penguji_2_clo1")
    private String nilai_penguji_2_clo1;

    @Expose
    @SerializedName("nilai_penguji_2_clo2")
    private String nilai_penguji_2_clo2;

    @Expose
    @SerializedName("nilai_penguji_2_clo3")
    private String nilai_penguji_2_clo3;

    @Expose
    @SerializedName("nilai_pembimbing_1_clo1")
    private String nilai_pembimbing_1_clo1;

    @Expose
    @SerializedName("nilai_pembimbing_1_clo2")
    private String nilai_pembimbing_1_clo2;

    @Expose
    @SerializedName("nilai_pembimbing_1_clo3")
    private String nilai_pembimbing_1_clo3;

    @Expose
    @SerializedName("nilai_pembimbing_2_clo1")
    private String nilai_pembimbing_2_clo1;

    @Expose
    @SerializedName("nilai_pembimbing_2_clo2")
    private String nilai_pembimbing_2_clo2;

    @Expose
    @SerializedName("nilai_pembimbing_2_clo3")
    private String nilai_pembimbing_2_clo3;

    @Expose
    @SerializedName("nilai_total")
    private String nilai_total;


    @Expose
    @SerializedName("sidang_status")
    private String sidang_status;

    @Expose
    @SerializedName("mhs_nim")
    private String mhs_nim;

    public Sidang(String sidang_id, String sidang_review, String sidang_tanggal, String jam_mulai, String jam_berakhir, String nilai_penguji_1_clo1,
                  String nilai_penguji_1_clo2, String nilai_penguji_1_clo3, String nilai_penguji_2_clo1, String nilai_penguji_2_clo2, String nilai_penguji_2_clo3,
                  String nilai_pembimbing_1_clo1, String nilai_pembimbing_1_clo2, String nilai_pembimbing_1_clo3, String nilai_pembimbing_2_clo1, String nilai_pembimbing_2_clo2,
                  String nilai_pembimbing_2_clo3, String nilai_total, String sidang_status, String mhs_nim) {
        this.sidang_id = sidang_id;
        this.sidang_review = sidang_review;
        this.sidang_tanggal = sidang_tanggal;
        this.jam_mulai = jam_mulai;
        this.jam_berakhir = jam_berakhir;
        this.nilai_penguji_1_clo1 = nilai_penguji_1_clo1;
        this.nilai_penguji_1_clo2 = nilai_penguji_1_clo2;
        this.nilai_penguji_1_clo3 = nilai_penguji_1_clo3;
        this.nilai_penguji_2_clo1 = nilai_penguji_2_clo1;
        this.nilai_penguji_2_clo2 = nilai_penguji_2_clo2;
        this.nilai_penguji_2_clo3 = nilai_penguji_2_clo3;
        this.nilai_pembimbing_1_clo1 = nilai_pembimbing_1_clo1;
        this.nilai_pembimbing_1_clo2 = nilai_pembimbing_1_clo2;
        this.nilai_pembimbing_1_clo3 = nilai_pembimbing_1_clo3;
        this.nilai_pembimbing_2_clo1 = nilai_pembimbing_2_clo1;
        this.nilai_pembimbing_2_clo2 = nilai_pembimbing_2_clo2;
        this.nilai_pembimbing_2_clo3 = nilai_pembimbing_2_clo3;
        this.nilai_total = nilai_total;
        this.sidang_status = sidang_status;
        this.mhs_nim = mhs_nim;
    }

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

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_berakhir() {
        return jam_berakhir;
    }

    public void setJam_berakhir(String jam_berakhir) {
        this.jam_berakhir = jam_berakhir;
    }

    public String getNilai_penguji_1_clo1() {
        return nilai_penguji_1_clo1;
    }

    public void setNilai_penguji_1_clo1(String nilai_penguji_1_clo1) {
        this.nilai_penguji_1_clo1 = nilai_penguji_1_clo1;
    }

    public String getNilai_penguji_1_clo2() {
        return nilai_penguji_1_clo2;
    }

    public void setNilai_penguji_1_clo2(String nilai_penguji_1_clo2) {
        this.nilai_penguji_1_clo2 = nilai_penguji_1_clo2;
    }

    public String getNilai_penguji_1_clo3() {
        return nilai_penguji_1_clo3;
    }

    public void setNilai_penguji_1_clo3(String nilai_penguji_1_clo3) {
        this.nilai_penguji_1_clo3 = nilai_penguji_1_clo3;
    }

    public String getNilai_penguji_2_clo1() {
        return nilai_penguji_2_clo1;
    }

    public void setNilai_penguji_2_clo1(String nilai_penguji_2_clo1) {
        this.nilai_penguji_2_clo1 = nilai_penguji_2_clo1;
    }

    public String getNilai_penguji_2_clo2() {
        return nilai_penguji_2_clo2;
    }

    public void setNilai_penguji_2_clo2(String nilai_penguji_2_clo2) {
        this.nilai_penguji_2_clo2 = nilai_penguji_2_clo2;
    }

    public String getNilai_penguji_2_clo3() {
        return nilai_penguji_2_clo3;
    }

    public void setNilai_penguji_2_clo3(String nilai_penguji_2_clo3) {
        this.nilai_penguji_2_clo3 = nilai_penguji_2_clo3;
    }

    public String getNilai_pembimbing_1_clo1() {
        return nilai_pembimbing_1_clo1;
    }

    public void setNilai_pembimbing_1_clo1(String nilai_pembimbing_1_clo1) {
        this.nilai_pembimbing_1_clo1 = nilai_pembimbing_1_clo1;
    }

    public String getNilai_pembimbing_1_clo2() {
        return nilai_pembimbing_1_clo2;
    }

    public void setNilai_pembimbing_1_clo2(String nilai_pembimbing_1_clo2) {
        this.nilai_pembimbing_1_clo2 = nilai_pembimbing_1_clo2;
    }

    public String getNilai_pembimbing_1_clo3() {
        return nilai_pembimbing_1_clo3;
    }

    public void setNilai_pembimbing_1_clo3(String nilai_pembimbing_1_clo3) {
        this.nilai_pembimbing_1_clo3 = nilai_pembimbing_1_clo3;
    }

    public String getNilai_pembimbing_2_clo1() {
        return nilai_pembimbing_2_clo1;
    }

    public void setNilai_pembimbing_2_clo1(String nilai_pembimbing_2_clo1) {
        this.nilai_pembimbing_2_clo1 = nilai_pembimbing_2_clo1;
    }

    public String getNilai_pembimbing_2_clo2() {
        return nilai_pembimbing_2_clo2;
    }

    public void setNilai_pembimbing_2_clo2(String nilai_pembimbing_2_clo2) {
        this.nilai_pembimbing_2_clo2 = nilai_pembimbing_2_clo2;
    }

    public String getNilai_pembimbing_2_clo3() {
        return nilai_pembimbing_2_clo3;
    }

    public void setNilai_pembimbing_2_clo3(String nilai_pembimbing_2_clo3) {
        this.nilai_pembimbing_2_clo3 = nilai_pembimbing_2_clo3;
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

    public String getMhs_nim() {
        return mhs_nim;
    }

    public void setMhs_nim(String mhs_nim) {
        this.mhs_nim = mhs_nim;
    }

    public static Creator<Sidang> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sidang_id);
        dest.writeString(this.sidang_review);
        dest.writeString(this.sidang_tanggal);
        dest.writeString(this.nilai_penguji_1_clo1);
        dest.writeString(this.nilai_penguji_1_clo2);
        dest.writeString(this.nilai_penguji_1_clo3);
        dest.writeString(this.nilai_penguji_2_clo1);
        dest.writeString(this.nilai_penguji_2_clo2);
        dest.writeString(this.nilai_penguji_2_clo3);
        dest.writeString(this.nilai_pembimbing_1_clo1);
        dest.writeString(this.nilai_pembimbing_1_clo2);
        dest.writeString(this.nilai_pembimbing_1_clo3);
        dest.writeString(this.nilai_pembimbing_2_clo1);
        dest.writeString(this.nilai_pembimbing_2_clo2);
        dest.writeString(this.nilai_pembimbing_2_clo3);
        dest.writeString(this.nilai_total);
        dest.writeString(this.sidang_status);
        dest.writeString(this.mhs_nim);
    }

    protected Sidang(Parcel in) {
        this.sidang_id = in.readString();
        this.sidang_review = in.readString();
        this.sidang_tanggal = in.readString();
        this.nilai_penguji_1_clo1 = in.readString();
        this.nilai_penguji_1_clo2 = in.readString();
        this.nilai_penguji_1_clo3 = in.readString();
        this.nilai_penguji_2_clo1 = in.readString();
        this.nilai_penguji_2_clo2 = in.readString();
        this.nilai_penguji_2_clo3 = in.readString();
        this.nilai_pembimbing_1_clo1 = in.readString();
        this.nilai_pembimbing_1_clo2 = in.readString();
        this.nilai_pembimbing_1_clo3 = in.readString();
        this.nilai_pembimbing_2_clo1 = in.readString();
        this.nilai_pembimbing_2_clo2 = in.readString();
        this.nilai_pembimbing_2_clo3 = in.readString();
        this.nilai_total = in.readString();
        this.sidang_status = in.readString();
        this.mhs_nim = in.readString();
    }

    public static final Creator<Sidang> CREATOR = new Creator<Sidang>() {
        @Override
        public Sidang createFromParcel(Parcel source) {
            return new Sidang(source);
        }

        @Override
        public Sidang[] newArray(int size) {
            return new Sidang[size];
        }
    };
}
