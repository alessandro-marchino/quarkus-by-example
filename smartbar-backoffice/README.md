# smartbar-backoffice

## MTLS - key creation

Generate keystore for SSL:

`keytool -genkeypair -alias smartbarbo -keyalg RSA -keypass sboadmin -storetype PKCS12 -storepass sboadmin -keystore smartbarbo.p12`

Generate clients:

`keytool -genkeypair -alias bob -keyalg RSA -keypass sboadmin -storetype PKCS12 -storepass sboadmin -keystore sbotrust.p12`

`keytool -genkeypair -alias alice -keyalg RSA -keypass sboadmin -storetype PKCS12 -storepass sboadmin -keystore sbotrust.p12`

Extract PEM data for client:

`openssl pkcs12 -in sbotrust.p12 -nokeys -nodes -out store.pem`

`openssl pkcs12 -in sbotrust.p12 -nocerts -nodes -out storekeys.pem`
