package com.example.OnRange;

import com.google.gson.JsonObject;

public class User {
    AdditionalParams AdditionalParamsObject;
    private AdditionalParams additionalParams;
    private String customAttributes;
    private String address;
    private String endpointProtocolId;
    private String groupId;
    private String groupName;
    private String identifier;
    private String serialNumber;
    private String protocol;
    private String imsi;


    public User(AdditionalParams additionalParams, String address, String groupName, String identifier, String serialNumber,String protocol) {

        this.additionalParams = additionalParams;
        this.customAttributes = customAttributes;
        this.address = address;
        this.endpointProtocolId =  endpointProtocolId;
        this.groupId =groupId;
        this.imsi= imsi;
        this.groupName = groupName;
        this.identifier = identifier;
        this.serialNumber = serialNumber;
        this.protocol = protocol;

    }
// Getter Methods

    public AdditionalParams getAdditionalParams() {
        return AdditionalParamsObject;
    }

    public String getAddress() {
        return address;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getProtocol() {
        return protocol;
    }

    // Setter Methods

    public void AdditionalParams(AdditionalParams additionalParamsObject) {
        this.AdditionalParamsObject = additionalParamsObject;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
class AdditionalParams {
    private String adaptationLayerName;
    private String devEui;
    private String appEui;
    private String appKey;
    private String activation;
    private String classType;
    private String rfRegion;
    private String adrEnabled;
    private String profile;
    private String regParamsRevision;
    private String macVersion;
    private String fcntUp;
    private String geolocation;
    private String latitude;
    private String longitude;
    private String altitude;

    public AdditionalParams(String adaptationLayerName, String devEui, String appKey, String appEui, String activation, String classType, String rfRegion, String adrEnabled, String profile, String regParamsRevision, String macVersion, String fcntUp, String geolocation, String latitude, String longitude, String altitude) {
        this.adaptationLayerName = adaptationLayerName;
        this.devEui = devEui;
        this.appEui = appEui;
        this.appKey = appKey;
        this.activation = activation;
        this.classType = classType;
        this.rfRegion = rfRegion;
        this.adrEnabled = adrEnabled;
        this.profile = profile;
        this.regParamsRevision = regParamsRevision;
        this.macVersion = macVersion;
        this.fcntUp = fcntUp;
        this.geolocation = geolocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

// Getter Methods

    public String getAdaptationLayerName() {
        return adaptationLayerName;
    }

    public String getDevEui() {
        return devEui;
    }

    public String getAppEui() {
        return appEui;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getActivation() {
        return activation;
    }

    public String getClassType() {
        return classType;
    }

    public String getRfRegion() {
        return rfRegion;
    }

    public String getAdrEnabled() {
        return adrEnabled;
    }

    public String getProfile() {
        return profile;
    }

    public String getRegParamsRevision() {
        return regParamsRevision;
    }

    public String getMacVersion() {
        return macVersion;
    }

    public String getFcntUp() {
        return fcntUp;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    // Setter Methods

    public void setAdaptationLayerName(String adaptationLayerName) {
        this.adaptationLayerName = adaptationLayerName;
    }

    public void setDevEui(String devEui) {
        this.devEui = devEui;
    }

    public void setAppEui(String appEui) {
        this.appEui = appEui;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public void setRfRegion(String rfRegion) {
        this.rfRegion = rfRegion;
    }

    public void setAdrEnabled(String adrEnabled) {
        this.adrEnabled = adrEnabled;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setRegParamsRevision(String regParamsRevision) {
        this.regParamsRevision = regParamsRevision;
    }

    public void setMacVersion(String macVersion) {
        this.macVersion = macVersion;
    }

    public void setFcntUp(String fcntUp) {
        this.fcntUp = fcntUp;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String toString(){
        return
                adaptationLayerName +", "+ devEui +", " + appEui +", " + appKey +", " + activation +", " + classType +", " + rfRegion +", " + adrEnabled +", " + profile +", " + regParamsRevision +", " + macVersion +", " + fcntUp
                        + geolocation +", " + latitude +", " + longitude +", " + altitude;


    }
}