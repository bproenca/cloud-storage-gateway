# Intro

This project is a Gateway to Object Storage API, through pre-authenticated requests

# Config

First create a pre-authenticated request using the [Console](https://docs.oracle.com/en-us/iaas/Content/Object/Tasks/usingpreauthenticatedrequests.htm)

Then use those info to configure the YML file:
```yml
cloud:
  endpoint: ${S3_ENDPOINT:https://objectstorage.sa-saopaulo-1.oraclecloud.com}
  # pre-authenticated request PATH (will be appended to the endpoint)
  preauthreq: ${S3_PRE_AUTH_REQ}
```

# Run

```console
mvn spring-boot:run -Dspring-boot.run.arguments="--S3_ENDPOINT=? --S3_PRE_AUTH_REQ=?"
```