# api.access.java

Simplified access to SiteOffice API

#Requirements

* Java 1.8

#Example
```
* instantiate :
ApiAccess apiAccess = ApiAccess.getInstance().setUrl("url");

* login :
boolean isConnected = apiAccess.login("userName", "password", "deviceId");

* login with registered deviceId :
boolean isConnected = apiAccess.token("deviceId");

* call REST API :
String data = apiAccess.data("tablename");

* call API :
String api = apiAccess.api("controller", "method");

* logout :
boolean isDeconnected = apiAccess.logout("deviceId");
```