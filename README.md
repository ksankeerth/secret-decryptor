# Secret Decryptor

This tool decrypts secrets from a base64-encoded encrypted format using a JKS (Java Keystore) file.

## Step 1: Clone the Repository



## Step 2: Prepare Input

Create a file where each line contains a base64-encoded encrypted secret. You can obtain the base64-encoded secrets from deployment.toml of WSO2 products.
Example:

toml

```
[secrets]
server_secret = "rOBahUU3QxhtzQTm6idqUCagXkBdzKlWgTOU1g5kk1PclJf5/CddfSlcOu1QMcmWzx5cMgrStEXV+3qA5QUsDtg3VPsZ7u8O3dGtNIrbOm8ZH9WPwkwID0F5IUQ/z6EEY9PydjFkNVduuUThIJi6x7oVFg5aBTqfIw389D7/5sSzz770wm6uJV0IZCKsRg90VknDL0f9MtX8Od16HZ4Quc+HNJwUzGLi7AHbtrNbgOswhpwTBWv2n6nVaYoJYP8c+/zrtPepDoN/oPObgqnbkDBFpDxKyd+buthYJRBN8/oCrA7eIcBL23hjj2FirPCnkDo75IhEzUtmNuxiYMuqPA=="

synapse_secret = "ljiAoPPEFaUtvE/dAJARoBXY3K7z0OfFp4A6aVv1gLb+mm9ALL8jwYopoJG8Jy6l0Kdy7p9zcDaBdHgw4XO1oGdP6am7A6EbTUs2trWPlcI8T9u8pbQGR92zQhNc6SPxfDnsswUep7UfxUPkQUEUe1CUk1rcnCEjdGM7VGXhX9DANJZWxImSG0jV0qDDLcjmwQfaaA2BlRhgLXTvsTpSjerkCIOMzviZBwQ7Bi3keQdF6GQP4Q+xTQ0Li5QPeAkI18xS1aaI2V9r3VXtAOmwYBZ1FEy9cJXFFwZGCw9b/sFib+NyF93xTGW6sVtCFw5vRzOqN5cBOEW/8Qob1TVzAA=="
```

In the text file (e.g., secrets.txt), keep one secret per line:
Example:

text
```
rOBahUU3QxhtzQTm6idqUCagXkBdzKlWgTOU1g5kk1PclJf5/CddfSlcOu1QMcmWzx5cMgrStEXV+3qA5QUsDtg3VPsZ7u8O3dGtNIrbOm8ZH9WPwkwID0F5IUQ/z6EEY9PydjFkNVduuUThIJi6x7oVFg5aBTqfIw389D7/5sSzz770wm6uJV0IZCKsRg90VknDL0f9MtX8Od16HZ4Quc+HNJwUzGLi7AHbtrNbgOswhpwTBWv2n6nVaYoJYP8c+/zrtPepDoN/oPObgqnbkDBFpDxKyd+buthYJRBN8/oCrA7eIcBL23hjj2FirPCnkDo75IhEzUtmNuxiYMuqPA==

ljiAoPPEFaUtvE/dAJARoBXY3K7z0OfFp4A6aVv1gLb+mm9ALL8jwYopoJG8Jy6l0Kdy7p9zcDaBdHgw4XO1oGdP6am7A6EbTUs2trWPlcI8T9u8pbQGR92zQhNc6SPxfDnsswUep7UfxUPkQUEUe1CUk1rcnCEjdGM7VGXhX9DANJZWxImSG0jV0qDDLcjmwQfaaA
```

## Step 3: Run the JAR

Execute the following command in the terminal:

```
java -jar secret-decryptor-1.0-SNAPSHOT.jar keystore.jks myalias mypassword secrets.txt decrypted_output.txt
```

Parameters:

    Alias: Same as the internal keystore’s alias.
    keystore.jks: The absolute path to the internal keystore (JKS) file.
    mypassword: The password for the keystore.
    secrets.txt: The input file containing all secrets (one per line).
    decrypted_output.txt: The output file where decrypted values will be stored.

Repeat this process for each environment's deployment.toml file.
Output

The decrypted values will be stored in decrypted_output.txt with each line containing the original secret and its decrypted plaintext value.