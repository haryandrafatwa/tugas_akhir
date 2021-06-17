package org.d3ifcool.finpro.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JadwalKegiatan implements Parcelable {
    @Expose
    @SerializedName("jadwal_id")
    private int id;

    @Expose
    @SerializedName("nama_kegiatan")
    private String nama_kegiatan;

    @Expose
    @SerializedName("tanggal_mulai")
    private String tanggal_mulai;

    @Expose
    @SerializedName("tanggal_berakhir")
    private String tanggal_berakhir;

    public JadwalKegiatan(int id, String nama_kegiatan, String tanggal_mulai, String tanggal_berakhir) {
        this.id = id;
        this.nama_kegiatan = nama_kegiatan;
        this.tanggal_mulai = tanggal_mulai;
        this.tanggal_berakhir = tanggal_berakhir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_kegiatan() {
        return nama_kegiatan;
    }

    public void setNama_kegiatan(String nama_kegiatan) {
        this.nama_kegiatan = nama_kegiatan;
    }

    public String getTanggal_mulai() {
        return tanggal_mulai;
    }

    public void setTanggal_mulai(String tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public String getTanggal_akhir() {
        return tanggal_berakhir;
    }

    public void setTanggal_akhir(String tanggal_akhir) {
        this.tanggal_berakhir = tanggal_akhir;
    }

    protected JadwalKegiatan(Parcel in) {
        id = in.readInt();
        nama_kegiatan = in.readString();
        tanggal_mulai = in.readString();
        tanggal_berakhir = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama_kegiatan);
        dest.writeString(tanggal_mulai);
        dest.writeString(tanggal_berakhir);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<JadwalKegiatan> CREATOR = new Creator<JadwalKegiatan>() {
        @Override
        public JadwalKegiatan createFromParcel(Parcel in) {
            return new JadwalKegiatan(in);
        }

        @Override
        public JadwalKegiatan[] newArray(int size) {
            return new JadwalKegiatan[size];
        }
    };
}
