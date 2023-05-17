#using docker file as docker compose is unable to set expectations in mock server
FROM mockserver/mockserver:mockserver-5.11.2

COPY ./config/init2.json config/init.json

ENV MOCKSERVER_PROPERTY_FILE "/config/mockserver.properties"
ENV MOCKSERVER_PERSISTED_EXPECTATIONS_PATH "/config/init.json"
ENV MOCKSERVER_INITIALIZATION_JSON_PATH "/config/init.json"
ENV MOCKSERVER_PERSIST_EXPECTATIONS "TRUE"

EXPOSE 1080