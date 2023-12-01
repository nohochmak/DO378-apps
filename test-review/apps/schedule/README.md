
## Run derby database in local host

----------------------------------------------
2023 Nov 29 - Dev notes
NativeScheduleResourceIT.java:  @NativeImageTest --> @QuarkusIntegrationTest   see https://quarkus.io/guides/building-native-image


----------------------------------------------
Go to `src/main/docker`

Build the Derby image ...

`podman build ...` or `make build`

Run the Derby image listening in 1527 port.

`podman run -p 1527:1527 db-derby` or `make run`
