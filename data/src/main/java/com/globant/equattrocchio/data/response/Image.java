package com.globant.equattrocchio.data.response;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Table(name = "Images", id = "id")
public class Image extends Model {
    @SerializedName("id")
    @Expose
    @Column(name = "ImageId")
    private Integer imageId;

    @SerializedName("url")
    @Expose
    @Column(name = "Url")
    private String url;

    @SerializedName("large_url")
    @Expose
    @Column(name = "LargeUrl")
    private String largeUrl;

    @SerializedName("source_id")
    @Expose
    @Column(name = "SourceId")
    private String sourceId;

    @SerializedName("site")
    @Expose
    @Column(name = "Site")
    private String site;

    @SerializedName("copyright")
    @Expose
    @Column(name = "Copyright")
    private String copyright;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
