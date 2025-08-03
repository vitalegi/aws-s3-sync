# AWS S3 Sync

Utility tool to simplify usage of S3 from Unix systems where architecture is not yet supported by the official cli, like Raspberry Pi.

## Pre-requirements

- Java 17
- Maven

## Build

```
mvn clean package
```

## Run

### Compile and run with maven

```
mvn clean compile exec:java "-Dexec.args=$arg1 $arg2 ..."
```

### Run from the target folder

```
java -cp target/aws-s3-sync-jar-with-dependencies.jar $arg1 $arg2 ...
```

### Run the distribution version

```
java -cp aws-s3-sync.jar $arg1 $arg2 ...
```

### Run application

```
java -cp target/aws-s3-sync-jar-with-dependencies.jar [options]
```

### Sample run

```
java -cp target/aws-s3-sync-jar-with-dependencies.jar --auth src/test/resources/auth1.properties --region AP_EAST_1 --bucket my-bucket --path src/test/resources/sample.txt --target /test/123/
```

## Options

- `--auth path/to/auth.properties`
- `--region $region`
- `--bucket $bucket`
- `--target $targetDir`
- `--file $filePath`

## Contributions

Thanks, but no.

## Bugs

Keep them.

## Support

You are on your own.