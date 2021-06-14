package org.d3ifcool.finpro.core.api

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl
import org.d3ifcool.finpro.core.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // Bimbingan
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_BIMBINGAN)
    fun createBimbingan(
            @Field("bimbingan_review") bimbingan_review: String?,
            @Field("bimbingan_kehadiran") bimbingan_kehadiran: String?,
            @Field("bimbingan_tanggal") bimbingan_tanggal: String?,
            @Field("bimbingan_status") bimbingan_status: String?,
            @Field("proyek_akhir_id") proyek_akhir_id: Int
    ): Call<Bimbingan?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_BIMBINGAN + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_BIMBINGAN)
    fun updateBimbingan(@Path(FinproUrl.VAR_BIMBINGAN) bimbingan_id: String?,
                        @Field("bimbingan_review") bimbingan_review: String?,
                        @Field("bimbingan_tanggal") bimbingan_tanggal: String?
    ): Call<Bimbingan?>?

    @GET(FinproUrl.URL_BIMBINGAN)
    fun getBimbingan(): Call<List<Bimbingan?>?>?

    @POST(FinproUrl.URL_BIMBINGAN + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_BIMBINGAN)
    fun deleteBimbingan(@Path(FinproUrl.VAR_BIMBINGAN) bimbingan_id: String?): Call<Bimbingan?>?

    @GET(FinproUrl.URL_BIMBINGAN + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER + FinproUrl.PARAMETER_QUERY)
    fun searchBimbinganAllBy(
            @Path(FinproUrl.VAR_PARAMS) parameter: String?,
            @Path(FinproUrl.VAR_QUERY) query: String?
    ): Call<List<Bimbingan?>?>?

    @GET(FinproUrl.URL_BIMBINGAN + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER + FinproUrl.PARAMETER_QUERY)
    fun searchBimbinganBy(
            @Path(FinproUrl.VAR_PARAMS) parameter: String?,
            @Path(FinproUrl.VAR_QUERY) query: String?
    ): Call<Bimbingan?>?

    @GET(FinproUrl.URL_BIMBINGAN + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER_1 + FinproUrl.PARAMETER_QUERY_1 + FinproUrl.BASE_PARAMETER_2 + FinproUrl.PARAMETER_QUERY_2)
    fun searchBimbinganAllByTwo(
            @Path(FinproUrl.VAR_PARAMS + "1") parameter1: String?,
            @Path(FinproUrl.VAR_QUERY + "1") query1: String?,
            @Path(FinproUrl.VAR_PARAMS + "2") parameter2: String?,
            @Path(FinproUrl.VAR_QUERY + "2") query2: String?
    ): Call<List<Bimbingan?>?>?

    @GET(FinproUrl.URL_BIMBINGAN + FinproUrl.PATH_SEARCH + FinproUrl.PATH_SIAP_SIDANG + FinproUrl.PARAMETER_BIMBINGAN)
    fun searchSiapSidang(@Path(FinproUrl.VAR_BIMBINGAN) jumlah_bimbingan: Int): Call<List<SiapSidang?>?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_BIMBINGAN + FinproUrl.PATH_UPDATE + FinproUrl.PATH_STATUS + FinproUrl.PARAMETER_BIMBINGAN)
    fun updateBimbinganStatus(@Path(FinproUrl.VAR_BIMBINGAN) bimbingan_id: String?, @Field("bimbingan_status") bimbingan_status: String?): Call<Bimbingan?>?

    // Dosen
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_DOSEN)
    fun createDosen(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Field("dsn_nip") dsn_nip: String?,
            @Field("dsn_nama") dsn_nama: String?,
            @Field("dsn_kode") dsn_kode: String?
    ): Call<Dosen?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_DOSEN + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_DOSEN)
    fun updateDosen(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_DOSEN) dsn_nip: String?,
        @Field("dsn_nama") dsn_nama: String?,
        @Field("dsn_kode") dsn_kode: String?,
        @Field("dsn_kontak") dsn_kontak: String?,
        @Field("dsn_email") dsn_email: String?,
        @Field("kuota_bimbingan") kuota_bimbingan: Int,
        @Field("kuota_reviewer")kuota_reviewer: Int
    ): Call<Dosen?>?

    @POST(FinproUrl.URL_DOSEN + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_DOSEN)
    fun deleteDosen(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_DOSEN) dsn_nip: String?
    ): Call<Dosen?>?

    @Headers("Connection: close")
    @GET(FinproUrl.URL_DOSEN + FinproUrl.PATH_ALL)
    fun getAllDosen(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<List<Dosen?>?>?

    @Headers("Connection: close")
    @GET(FinproUrl.URL_DOSEN)
    fun getDosen(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<Dosen?>?

    @GET(FinproUrl.URL_DOSEN + FinproUrl.PARAMETER_DOSEN)
    fun getDosenByParameter(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_DOSEN) dsn_nip: String?
    ): Call<Dosen?>?

    // Plotting
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_PLOTTING)
    fun createPlotting(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Field("nip_pembimbing_1") nip_pembimbing_1: String?,
            @Field("nip_pembimbing_2") nip_pembimbing_2: String?
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_PLOTTING + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_PLOTTING)
    fun updatePlotting(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_PLOTTING) plot_id: Int,
        @Field("nip_pembimbing_1") nip_pembimbing_1: String?,
        @Field("nip_pembimbing_2") nip_pembimbing_2: String?
    ): Call<ResponseBody?>?

    @GET(FinproUrl.URL_PLOTTING)
    fun getPlotting(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<List<Plotting?>?>?

    @GET(FinproUrl.URL_PLOTTING + FinproUrl.PARAMETER_PLOTTING)
    fun getPlottingByParamter(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Path(FinproUrl.VAR_PLOTTING) plot_id: Int?
    ): Call<Plotting?>?

    @Headers("Connection:close")
    @POST(FinproUrl.URL_PLOTTING + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_PLOTTING)
    fun deletePlotting(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_PLOTTING) plotting_id: Int
    ): Call<ResponseBody?>?

    @Multipart
    @POST(FinproUrl.URL_PLOTTING + FinproUrl.PATH_UPLOAD)
    fun uploadFormPlotting(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Part file: MultipartBody.Part?
    ): Call<ResponseBody?>?

    @Headers("Connection:close")
    @GET(FinproUrl.URL_CHECKFORMPLOT)
    fun checkFormPlot(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
    ): Call<ResponseBody>?

    @Headers("Connection:close")
    @Streaming
    @GET(FinproUrl.URL_DOWNLOADFORMPLOT)
    fun downloadFormPlot(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
    ): Call<ResponseBody>?

    @Headers("Connection:close")
    @GET(FinproUrl.URL_DELETEFORMPLOT)
    fun deleteFormPlot(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
    ): Call<ResponseBody>?

    // Informasi
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_INFORMASI)
    fun createInformasi(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Field("informasi_judul") informasi_judul: String?,
            @Field("informasi_isi") informasi_isi: String?,
            @Field("penerbit") penerbit: String?
    ): Call<Informasi?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_INFORMASI + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_INFORMASI)
    fun updateInformasi(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Path(FinproUrl.VAR_INFORMASI) informasi_id: Int,
            @Field("informasi_judul") informasi_judul: String?,
            @Field("informasi_isi") informasi_isi: String?
    ): Call<Informasi?>?

    @Headers("Connection:close")
    @GET(FinproUrl.URL_INFORMASI)
    fun getInformasi(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<List<Informasi?>?>?

    // Ga perlu pake @FormUrlEncoded karena tidak menggunakan field
    @POST(FinproUrl.URL_INFORMASI + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_INFORMASI)
    fun deleteInformasi(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Path(FinproUrl.VAR_INFORMASI) informasi_id: Int
    ): Call<Informasi?>?

    // Judul
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_JUDUL_PA)
    fun createJudul(
            @Field("judul_nama") judul_nama: String?,
            @Field("kategori_id") kategori_id: Int,
            @Field("judul_deskripsi") judul_deskripsi: String?,
            @Field("dsn_nip") dsn_nip: String?,
            @Field("judul_status") judul_status: String?
    ): Call<Judul?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_JUDUL_PA + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_JUDUL)
    fun updateJudul(
            @Path(FinproUrl.VAR_JUDUL) judul_id: Int,
            @Field("judul_nama") judul_nama: String?,
            @Field("kategori_id") kategori_id: Int,
            @Field("judul_deskripsi") judul_deskripsi: String?
    ): Call<Judul?>?

    @GET(FinproUrl.URL_JUDUL_PA)
    fun getJudul(): Call<List<Judul?>?>?

    @POST(FinproUrl.URL_JUDUL_PA + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_JUDUL)
    fun deleteJudul(@Path(FinproUrl.VAR_JUDUL) judul_id: Int): Call<Judul?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_JUDUL_PA + FinproUrl.PATH_UPDATE + FinproUrl.PATH_STATUS + FinproUrl.PARAMETER_JUDUL)
    fun updateStatusJudul(@Path(FinproUrl.VAR_JUDUL) judul_id: Int,
                          @Field("judul_status") judul_status: String?): Call<Judul?>?

    @GET(FinproUrl.URL_JUDUL_PA + FinproUrl.PATH_SEARCH + FinproUrl.BASE_PARAMETER + FinproUrl.PARAMETER_QUERY)
    fun searchJudulBy(
            @Path(FinproUrl.VAR_PARAMS) parameter: String?,
            @Path(FinproUrl.VAR_QUERY) query: String?
    ): Call<List<Judul?>?>?

    @GET(FinproUrl.URL_JUDUL_PA + FinproUrl.PATH_SEARCH + FinproUrl.BASE_PARAMETER_1 + FinproUrl.PARAMETER_QUERY_1 + FinproUrl.BASE_PARAMETER_2 + FinproUrl.PARAMETER_QUERY_2)
    fun searchJudulByTwo(
            @Path(FinproUrl.VAR_PARAMS + "1") parameter1: String?,
            @Path(FinproUrl.VAR_QUERY + "1") query1: String?,
            @Path(FinproUrl.VAR_PARAMS + "2") parameter2: String?,
            @Path(FinproUrl.VAR_QUERY + "2") query2: String?
    ): Call<List<Judul?>?>?

    @GET(FinproUrl.URL_JUDUL_PA + FinproUrl.PATH_SEARCH + FinproUrl.PATH_MAHASISWA + FinproUrl.BASE_PARAMETER + FinproUrl.PARAMETER_QUERY)
    fun searchJudulMahasiswaBy(
            @Path(FinproUrl.VAR_PARAMS) parameter: String?,
            @Path(FinproUrl.VAR_QUERY) query: String?
    ): Call<List<Judul?>?>?

    // Kategori Judul
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_KATEGORI_JUDUL)
    fun createKategoriJudul(
            @Field("kategori_nama") kategori_nama: String?
    ): Call<KategoriJudul?>?


    @FormUrlEncoded
    @POST(FinproUrl.URL_KATEGORI_JUDUL + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_KATEGORI_JUDUL)
    fun updateKategoriJudul(
            @Path(FinproUrl.VAR_KATEGORI_JUDUL) kategori_judul: Int,
            @Field("kategori_nama") kategori_nama: String?
    ): Call<KategoriJudul?>?

    @GET(FinproUrl.URL_KATEGORI_JUDUL)
    fun getKategoriJudul(): Call<List<KategoriJudul?>?>?

    @POST(FinproUrl.URL_KATEGORI_JUDUL + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_KATEGORI_JUDUL)
    fun deleteKategoriJudul(@Path(FinproUrl.VAR_KATEGORI_JUDUL) kategori_judul: Int): Call<KategoriJudul?>?

    // Kegiatan
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_KEGIATAN)
    open fun createKegiatan(
            @Field("kegiatan") kegiatan: String?,
            @Field("tanggal_mulai") tanggal_mulai: String?,
            @Field("tanggal_berakhir") tanggal_berakhir: String?,
            @Field("pelaku") pelaku: String?,
            @Field("keterangan") keterangan: String?
    ): Call<Kegiatan?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_KEGIATAN + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_KEGIATAN)
    fun updateKegiatan(
            @Path(FinproUrl.VAR_KEGIATAN) kegiatan_id: Int,
            @Field("kegiatan") kegiatan: String?,
            @Field("tanggal_mulai") tanggal_mulai: String?,
            @Field("tanggal_berakhir") tanggal_berakhir: String?,
            @Field("pelaku") pelaku: String?,
            @Field("keterangan") keterangan: String?
    ): Call<Kegiatan?>?

    @GET(FinproUrl.URL_KEGIATAN)
    fun getKegiatan(): Call<List<Kegiatan?>?>?

    @POST(FinproUrl.URL_KEGIATAN + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_KEGIATAN)
    fun deleteKegiatan(@Path(FinproUrl.VAR_KEGIATAN) kegiatan_id: Int): Call<Kegiatan?>?

    // Koor
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_KOORDINATOR_PA)
    fun createKoor(
            @Field("koor_nip") koor_nip: String?,
            @Field("koor_nama") koor_nama: String?,
            @Field("koor_kontak") koor_kontak: String?,
            @Field("koor_foto") koor_foto: String?,
            @Field("koor_email") koor_email: String?
    ): Call<Koordinator?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_KOORDINATOR_PA + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_KOOR)
    fun updateKoor(
            @Path(FinproUrl.VAR_KOORDINATOR_PA) username: String?,
            @Field("koor_nip") koor_nip: String?,
            @Field("koor_nama") koor_nama: String?,
            @Field("koor_kode") koor_kode: String?,
            @Field("koor_kontak") koor_kontak: String?,
            @Field("koor_email") koor_email: String?
    ): Call<Koordinator?>?

    @GET(FinproUrl.URL_KOORDINATOR_PA)
    fun getKoor(): Call<List<Koordinator?>?>?

    @POST(FinproUrl.URL_KOORDINATOR_PA + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_KOOR)
    fun deleteKoor(@Path(FinproUrl.VAR_KOORDINATOR_PA) koor_nip: String?): Call<Koordinator?>?

    @GET(FinproUrl.URL_KOORDINATOR_PA + FinproUrl.PARAMETER_KOOR)
    fun getKoorByParameter(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Path(FinproUrl.VAR_KOORDINATOR_PA) username_koor: String?
    ): Call<Koordinator?>?


    // Kuota Dosen
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_KUOTA_DOSEN)
    open fun createKuotaDosen(
            @Field("kuota_variable") kuota_variable: String?,
            @Field("kuota_value") kuota_value: String?
    ): Call<KuotaDosen?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_MONEV + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_MONEV)
    fun updateKuotaDosen(
            @Path(FinproUrl.VAR_KUOTA_DOSEN) kuota_id: Int,
            @Field("kuota_variable") kuota_variable: String?,
            @Field("kuota_value") kuota_value: String?
    ): Call<KuotaDosen?>?

    @GET(FinproUrl.URL_MONEV)
    fun getKuotaDosen(): Call<List<KuotaDosen?>?>?

    @POST(FinproUrl.URL_MONEV + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_MONEV)
    fun deleteKuotaDosen(@Path(FinproUrl.VAR_KUOTA_DOSEN) kuota_id: Int): Call<KuotaDosen?>?


    // Mahasiswa
    // ---------------------------------------------------------------------------------------------

    @Headers("Connection: close")
    @FormUrlEncoded
    @POST(FinproUrl.URL_MAHASISWA)
    fun createMahasiswa(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Field("mhs_nim") mhs_nim: String?,
            @Field("mhs_nama") mhs_nama: String?
    ): Call<Mahasiswa?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_MAHASISWA)
    fun updateMahasiswa(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Path(FinproUrl.VAR_MAHASISWA) nim_mhs: String?,
            @Field("mhs_nama") mhs_nama: String?,
            @Field("mhs_angkatan") angkatan: String?,
            @Field("mhs_kontak") mhs_kontak: String?,
            @Field("mhs_email") mhs_email: String?,
            @Field("judul") judul: String?,
            @Field("judul_inggris") judul_inggris: String?
    ): Call<Mahasiswa?>?

    @Headers("Connection: close")
    @FormUrlEncoded
    @POST(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_UPDATE + "/" + FinproUrl.VAR_PEMBIMBING + FinproUrl.PARAMETER_MAHASISWA)
    fun addPembimbing(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Field("plot_id") plot_id: Int?,
            @Path(FinproUrl.VAR_MAHASISWA) mhs_nim: String?,
    ): Call<Mahasiswa?>?

    @Headers("Connection: close")
    @GET(FinproUrl.URL_MAHASISWA)
    fun getMahasiswa(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<List<Mahasiswa?>?>?

    @POST(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_MAHASISWA)
    fun deleteMahasiswa(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_MAHASISWA) mhs_nim: String?
    ): Call<Mahasiswa?>?

    @GET(FinproUrl.URL_MAHASISWA + FinproUrl.PARAMETER_MAHASISWA)
    fun getMahasiswaByParameter(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_MAHASISWA) username_mhs: String?
    ): Call<Mahasiswa?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_UPDATE + FinproUrl.PATH_JUDUL + FinproUrl.PARAMETER_MAHASISWA)
    fun updateJudulMahasiswa(
            @Path(FinproUrl.VAR_MAHASISWA) mhs_nim: String?,
            @Field("judul_id") judul: Int
    ): Call<Mahasiswa?>?

    @Multipart
    @POST(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_UPDATE + FinproUrl.PATH_SKTA + FinproUrl.PARAMETER_MAHASISWA)
    fun updateSKTA(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Path(FinproUrl.VAR_MAHASISWA) mhs_nim: String?,
            @Part file: MultipartBody.Part?
    ): Call<ResponseBody?>?

    // Monev
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_MONEV)
    fun createMonev(
            @Field("monev_kategori") monev_kategori: String?,
            @Field("jumlah_bimbingan") jumlah_bimbingan: String?
    ): Call<Monev?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_MONEV + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_MONEV)
    fun updateMonev(
            @Path(FinproUrl.VAR_MONEV) monev_id: Int,
            @Field("monev_kategori") kategori: String?,
            @Field("jumlah_bimbingan") jumlah_bimbingan: String?
    ): Call<Monev?>?

    @GET(FinproUrl.URL_MONEV)
    fun getMonev(): Call<List<Monev?>?>?

    @POST(FinproUrl.URL_MONEV + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_MONEV)
    fun deleteMonev(@Path(FinproUrl.VAR_MONEV) monev_id: Int): Call<Monev?>?

    // Monev Detail
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_MONEV_DETAIL)
    fun createDetailMonev(
            @Field("monev_nilai") monev_nilai: Int,
            @Field("monev_tanggal") monev_tanggal: String?,
            @Field("monev_ulasan") monev_ulasan: String?,
            @Field("monev_id") monev_id: Int,
            @Field("proyek_akhir_id") proyek_akhir_id: Int
    ): Call<DetailMonev?>?

    @GET(FinproUrl.URL_MONEV_DETAIL)
    fun getDetailMonev(): Call<List<DetailMonev?>?>?

    @POST(FinproUrl.URL_MONEV_DETAIL + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_MONEV_DETAIL)
    fun deleteDetailMonev(@Path(FinproUrl.VAR_MONEV_DETAIL) monev_detail_id: Int): Call<DetailMonev?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_MONEV_DETAIL + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_MONEV_DETAIL)
    fun updateDetailMonev(@Path(FinproUrl.VAR_MONEV_DETAIL) monev_detail_id: Int,
                          @Field("monev_nilai") monev_nilai: Int,
                          @Field("monev_tanggal") monev_tanggal: String?,
                          @Field("monev_ulasan") monev_ulasan: String?
    ): Call<DetailMonev?>?

    @GET(FinproUrl.URL_MONEV_DETAIL + FinproUrl.PATH_SEARCH + FinproUrl.BASE_PARAMETER_1 + FinproUrl.PARAMETER_QUERY_1 + FinproUrl.BASE_PARAMETER_2 + FinproUrl.PARAMETER_QUERY_2)
    fun searchDetailMonevByTwo(
            @Path(FinproUrl.VAR_PARAMS + "1") parameter1: String?,
            @Path(FinproUrl.VAR_QUERY + "1") query1: String?,
            @Path(FinproUrl.VAR_PARAMS + "2") parameter2: String?,
            @Path(FinproUrl.VAR_QUERY + "2") query2: String?
    ): Call<List<DetailMonev?>?>?

    @GET(FinproUrl.URL_MONEV_DETAIL + FinproUrl.PATH_SEARCH + FinproUrl.BASE_PARAMETER + FinproUrl.PARAMETER_QUERY)
    fun searchDetailMonevBy(
            @Path(FinproUrl.VAR_PARAMS) parameter: String?,
            @Path(FinproUrl.VAR_QUERY) query: String?
    ): Call<List<DetailMonev?>?>?

    // Notifikasi
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_NOTIFIKASI)
    fun createNotifikasi(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Field("notifikasi_kategori") notifikasi_kategori: String?,
            @Field("notifikasi_deskripsi") notifikasi_deskripsi: String?,
            @Field("notifikasi_dari") notifikasi_dari: String?,
            @Field("notifikasi_untuk") notifikasi_untuk: String?
    ): Call<Notifikasi?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_NOTIFIKASI + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_NOTIFIKASI)
    fun updateNotifikasi(
            @Path(FinproUrl.VAR_NOTIFIKASI) notifikasi_id: Int,
            @Field("notifikasi_baca") notifikasi_baca: Int
    ): Call<Notifikasi?>?

    @GET(FinproUrl.URL_NOTIFIKASI + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER + FinproUrl.PARAMETER_QUERY)
    fun searchNotifikasiBy(
            @Path(FinproUrl.VAR_PARAMS) parameter: String?,
            @Path(FinproUrl.VAR_QUERY) query: String?
    ): Call<List<Notifikasi?>?>?

    @GET(FinproUrl.URL_NOTIFIKASI + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER_1 + FinproUrl.PARAMETER_QUERY_1 + FinproUrl.BASE_PARAMETER_2 + FinproUrl.PARAMETER_QUERY_2)
    fun searchNotifikasiBy2(
            @Path(FinproUrl.VAR_PARAMS + "1") parameter1: String?,
            @Path(FinproUrl.VAR_QUERY + "1") query1: String?,
            @Path(FinproUrl.VAR_PARAMS + "2") parameter2: String?,
            @Path(FinproUrl.VAR_QUERY + "2") query2: String?
    ): Call<List<Notifikasi?>?>?

    @GET(FinproUrl.URL_NOTIFIKASI + FinproUrl.PATH_SORT + FinproUrl.PARAMETER_QUERY_1 + FinproUrl.PARAMETER_QUERY_2)
    fun sortNotifikasi(
            @Path(FinproUrl.VAR_QUERY + "1") query1: String?,
            @Path(FinproUrl.VAR_QUERY + "2") query2: String?
    ): Call<List<Notifikasi?>?>?

    @GET(FinproUrl.URL_NOTIFIKASI)
    fun getNotifikasi(): Call<List<Notifikasi?>?>?

    @POST(FinproUrl.URL_NOTIFIKASI + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_NOTIFIKASI)
    fun deleteNotifikasi(@Path(FinproUrl.VAR_NOTIFIKASI) notifikasi_id: Int): Call<Notifikasi?>?

    // Proyek Akhir
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_PROYEK_AKHIR)
    fun createProyekAkhir(
            @Field("judul_id") id_judul: Int,
            @Field("mhs_nim") mhs_nim: String?,
            @Field("nama_tim") nama_tim: String?
    ): Call<ProyekAkhir?>?


    @FormUrlEncoded
    @POST(FinproUrl.URL_PROYEK_AKHIR + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_PROYEK_AKHIR)
    fun updateProyekAkhir(
            @Path(FinproUrl.VAR_PROYEK_AKHIR) proyek_akhir_id: Int,
            @Field("judul_id") id_judul: Int,
            @Field("mhs_nim") mhs_nim: String?,
            @Field("dsn_nip") dsn_nip: String?,
            @Field("nama_tim") nama_tim: String?
    ): Call<ProyekAkhir?>?

    @GET(FinproUrl.URL_PROYEK_AKHIR)
    fun getProyekAkhir(): Call<List<ProyekAkhir?>?>?

    @POST(FinproUrl.URL_PROYEK_AKHIR + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_PROYEK_AKHIR)
    fun deleteProyekAkhir(@Path(FinproUrl.VAR_PROYEK_AKHIR) proyek_akhir: Int): Call<ProyekAkhir?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_PROYEK_AKHIR + FinproUrl.PATH_UPDATE + FinproUrl.PATH_NILAI + FinproUrl.PARAMETER_PROYEK_AKHIR)
    fun updateNilai(
            @Path(FinproUrl.VAR_PROYEK_AKHIR) proyek_akhir_id: Int,
            @Field("nilai_total") nilai_total: Double
    ): Call<ProyekAkhir?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_PROYEK_AKHIR + FinproUrl.PATH_UPDATE + FinproUrl.PATH_DOSEN + FinproUrl.PARAMETER_PROYEK_AKHIR)
    fun updateDosenReviewer(
            @Path(FinproUrl.VAR_PROYEK_AKHIR) proyek_akhir_id: Int,
            @Field("dsn_nip") nip_dosen: String?
    ): Call<ProyekAkhir?>?

    @GET(FinproUrl.URL_PROYEK_AKHIR + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER + FinproUrl.PARAMETER_QUERY)
    fun searchAllProyekAkhirBy(
            @Path(FinproUrl.VAR_PARAMS) parameter: String?,
            @Path(FinproUrl.VAR_QUERY) query: String?
    ): Call<List<ProyekAkhir?>?>?

    @GET(FinproUrl.URL_PROYEK_AKHIR + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER_1 + FinproUrl.PARAMETER_QUERY_1 + FinproUrl.BASE_PARAMETER_2 + FinproUrl.PARAMETER_QUERY_2)
    fun searchAllProyekAkhirByTwo(
            @Path(FinproUrl.VAR_PARAMS + "1") parameter1: String?,
            @Path(FinproUrl.VAR_QUERY + "1") query1: String?,
            @Path(FinproUrl.VAR_PARAMS + "2") parameter2: String?,
            @Path(FinproUrl.VAR_QUERY + "2") query2: String?
    ): Call<List<ProyekAkhir?>?>?

    @GET(FinproUrl.URL_PROYEK_AKHIR + FinproUrl.PATH_SEARCH + FinproUrl.PATH_DISTINCT + FinproUrl.BASE_PARAMETER + FinproUrl.PARAMETER_QUERY)
    fun searchDistinctProyekAkhirBy(
            @Path(FinproUrl.VAR_PARAMS) parameter: String?,
            @Path(FinproUrl.VAR_QUERY) query: String?
    ): Call<List<ProyekAkhir?>?>?

    @GET(FinproUrl.URL_PROYEK_AKHIR + FinproUrl.PATH_SEARCH + FinproUrl.PATH_DISTINCT + FinproUrl.BASE_PARAMETER_1 + FinproUrl.PARAMETER_QUERY_1 + FinproUrl.BASE_PARAMETER_2 + FinproUrl.PARAMETER_QUERY_2)
    fun searchDistinctProyekAkhirByTwo(
            @Path(FinproUrl.VAR_PARAMS + "1") parameter1: String?,
            @Path(FinproUrl.VAR_QUERY + "1") query1: String?,
            @Path(FinproUrl.VAR_PARAMS + "2") parameter2: String?,
            @Path(FinproUrl.VAR_QUERY + "2") query2: String?
    ): Call<List<ProyekAkhir?>?>?

    // Sidang
    // ---------------------------------------------------------------------------------------------

    @GET(FinproUrl.URL_SIDANG)
    fun getSidang(): Call<List<Sidang?>?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_SIDANG)
    fun createSidang(
            @Field("sidang_review") sidang_review: String?,
            @Field("sidang_tanggal") sidang_tanggal: String?,
            @Field("nilai_proposal") nilai_proposal: Int,
            @Field("nilai_penguji_1") nilai_penguji_1: Int,
            @Field("nilai_penguji_2") nilai_penguji_2: Int,
            @Field("nilai_pembimbing") nilai_pembimbing: Int,
            @Field("nilai_total") nilai_total: Double,
            @Field("sidang_status") sidang_status: String?,
            @Field("proyek_akhir_id") proyek_akhir_id: Int
    ): Call<Sidang?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_SIDANG + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_SIDANG)
    fun updateSidang(
            @Path(FinproUrl.VAR_SIDANG) sidang_id: String?,
            @Field("sidang_review") sidang_review: String?,
            @Field("sidang_tanggal") sidang_tanggal: String?,
            @Field("nilai_proposal") nilai_proposal: Int,
            @Field("nilai_penguji_1") nilai_penguji_1: Int,
            @Field("nilai_penguji_2") nilai_penguji_2: Int,
            @Field("nilai_pembimbing") nilai_pembimbing: Int,
            @Field("nilai_total") nilai_total: Double,
            @Field("sidang_status") sidang_status: String?
    ): Call<Sidang?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_SIDANG)
    fun deleteSidang(
            @Path("sidang") sidang_id: String?
    ): Call<Sidang?>?

    @GET(FinproUrl.URL_SIDANG + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER + FinproUrl.PARAMETER_QUERY)
    fun searchAllSidangBy(
            @Path(FinproUrl.VAR_PARAMS) parameter: String?,
            @Path(FinproUrl.VAR_QUERY) query: String?
    ): Call<List<Sidang?>?>?

    @GET(FinproUrl.URL_SIDANG + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER_1 + FinproUrl.PARAMETER_QUERY_1 + FinproUrl.BASE_PARAMETER_2 + FinproUrl.PARAMETER_QUERY_2)
    fun searchAllSidangByTwo(
            @Path(FinproUrl.VAR_PARAMS + "1") parameter1: String?,
            @Path(FinproUrl.VAR_QUERY + "1") query1: String?,
            @Path(FinproUrl.VAR_PARAMS + "2") parameter2: String?,
            @Path(FinproUrl.VAR_QUERY + "2") query2: String?
    ): Call<List<Sidang?>?>?

    // User
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_LOGIN)
    fun setLogin(
            @Field("username") username: String?,
            @Field("password") password: String?): Call<User?>?

    @GET(FinproUrl.URL_USER + FinproUrl.PARAMETER_USER)
    fun getUserBy(
            @Path(FinproUrl.VAR_USER) username: String?
    ):Call<ResponseBody>?

    @POST(FinproUrl.URL_LOGOUT)
    fun logout(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ):Call<ResponseBody>?
}