package org.d3ifcool.finpro.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Bimbingan implements Parcelable {

    @Expose
    @SerializedName("bimbingan_id")
    private int bimbingan_id;

    @Expose
    @SerializedName("bimbingan_review")
    private String bimbingan_review;

    @Expose
    @SerializedName("bimbingan_tanggal")
    private String bimbingan_tanggal;

    @Expose
    @SerializedName("bimbingan_status")
    private String bimbingan_status;

    @Expose
    @SerializedName("mhs_nim")
    private String mhs_nim;

    @Expose
    @SerializedName("dsn_nip")
    private String dsn_nip;

    @Expose
    @SerializedName("dsn_nama")
    private String dsn_nama;

    public Bimbingan(int bimbingan_id, String bimbingan_review, String bimbingan_tanggal, String bimbingan_status, String mhs_nim, String dsn_nip, String dsn_nama) {
        this.bimbingan_id = bimbingan_id;
        this.bimbingan_review = bimbingan_review;
        this.bimbingan_tanggal = bimbingan_tanggal;
        this.bimbingan_status = bimbingan_status;
        this.mhs_nim = mhs_nim;
        this.dsn_nip = dsn_nip;
        this.dsn_nama = dsn_nama;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dsn_nip);
        dest.writeString(this.dsn_nama);
        dest.writeString(this.mhs_nim);
        dest.writeString(this.bimbingan_review);
        dest.writeString(this.bimbingan_status);
        dest.writeString(this.bimbingan_tanggal);
        dest.writeInt(this.bimbingan_id);
    }

    protected Bimbingan(Parcel in) {
        this.dsn_nip = in.readString();
        this.dsn_nama = in.readString();
        this.mhs_nim = in.readString();
        this.bimbingan_review = in.readString();
        this.bimbingan_status = in.readString();
        this.bimbingan_tanggal = in.readString();
        this.bimbingan_id = in.readInt();
    }

    public static final Creator<Bimbingan> CREATOR = new Creator<Bimbingan>() {
        @Override
        public Bimbingan createFromParcel(Parcel source) {
            return new Bimbingan(source);
        }

        @Override
        public Bimbingan[] newArray(int size) {
            return new Bimbingan[size];
        }
    };

    public String tglBimbingan(){
        Date date;
        Locale locale = new Locale("in", "ID");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
        try {
            date = format.parse(bimbingan_tanggal);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String month_name = new SimpleDateFormat("MMMM", locale).format(calendar.getTime());
            String parseDate = calendar.get(Calendar.DATE)+" "+month_name+" "+calendar.get(Calendar.YEAR);
            return parseDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return bimbingan_tanggal;
        }
    }

    public void setBimbingan_id(int bimbingan_id) {
        this.bimbingan_id = bimbingan_id;
    }

    public void setBimbingan_review(String bimbingan_review) {
        this.bimbingan_review = bimbingan_review;
    }

    public void setBimbingan_tanggal(String bimbingan_tanggal) {
        this.bimbingan_tanggal = bimbingan_tanggal;
    }

    public void setBimbingan_status(String bimbingan_status) {
        this.bimbingan_status = bimbingan_status;
    }

    public void setMhs_nim(String mhs_nim) {
        this.mhs_nim = mhs_nim;
    }

    public void setDsn_nip(String dsn_nip) {
        this.dsn_nip = dsn_nip;
    }

    public void setDsn_nama(String dsn_nama) {
        this.dsn_nama = dsn_nama;
    }

    public int getBimbingan_id() {
        return bimbingan_id;
    }

    public String getBimbingan_review() {
        return bimbingan_review;
    }

    public String getBimbingan_tanggal() {
        return bimbingan_tanggal;
    }

    public String getBimbingan_status() {
        return bimbingan_status;
    }

    public String getMhs_nim() {
        return mhs_nim;
    }

    public String getDsn_nip() {
        return dsn_nip;
    }

    public String getDsn_nama() {
        return dsn_nama;
    }
}
