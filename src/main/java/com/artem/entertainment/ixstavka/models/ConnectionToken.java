package com.artem.entertainment.ixstavka.models;

public class ConnectionToken {
    String Url;
    String ConnectionToken;
    String ConnectionId;
    String KeepAliveTimeout;
    String DisconnectTimeout;
    String ConnectionTimeout;
    String TryWebSockets;
    String ProtocolVersion;
    String TransportConnectTimeout;
    String LongPollDelay;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getConnectionToken() {
        return ConnectionToken;
    }

    public void setConnectionToken(String connectionToken) {
        ConnectionToken = connectionToken;
    }

    public String getConnectionId() {
        return ConnectionId;
    }

    public void setConnectionId(String connectionId) {
        ConnectionId = connectionId;
    }

    public String getKeepAliveTimeout() {
        return KeepAliveTimeout;
    }

    public void setKeepAliveTimeout(String keepAliveTimeout) {
        KeepAliveTimeout = keepAliveTimeout;
    }

    public String getDisconnectTimeout() {
        return DisconnectTimeout;
    }

    public void setDisconnectTimeout(String disconnectTimeout) {
        DisconnectTimeout = disconnectTimeout;
    }

    public String getConnectionTimeout() {
        return ConnectionTimeout;
    }

    public void setConnectionTimeout(String connectionTimeout) {
        ConnectionTimeout = connectionTimeout;
    }

    public String getTryWebSockets() {
        return TryWebSockets;
    }

    public void setTryWebSockets(String tryWebSockets) {
        TryWebSockets = tryWebSockets;
    }

    public String getProtocolVersion() {
        return ProtocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        ProtocolVersion = protocolVersion;
    }

    public String getTransportConnectTimeout() {
        return TransportConnectTimeout;
    }

    public void setTransportConnectTimeout(String transportConnectTimeout) {
        TransportConnectTimeout = transportConnectTimeout;
    }

    public String getLongPollDelay() {
        return LongPollDelay;
    }

    public void setLongPollDelay(String longPollDelay) {
        LongPollDelay = longPollDelay;
    }
}
