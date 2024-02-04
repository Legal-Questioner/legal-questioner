# Legal Questioner

Created for the GDSC Solutions Challenge 2024

## Running The Server Backend

### Env File requirements

Running the application locally currently requires environment variables to be configured manually.

Create a `.env` file in the server project root (`legal-questioner/legalquestionizer`).

- `PROJECT_ID`: This is a required environmental variable, Get from GCP Console.
- `GEMINI_CHAR_LIMIT`: This is an optional environmental variable, if not set, is defaulted to 30000.
- `LOCATION`: This is an optional environmental variable, if not set, is defaulted to `us-central1`

The `.env` file should take the format
```
./legal-questioner/legalquestionizer/.env:

    PROJECT_ID={PROJECT_ID}
    GEMINI_CHAR_LIMIT={GEMINI_CHAR_LIMIT}
    LOCATION={LOCATION}
```

### Authenticating on GCI

Make sure you have the google cloud SDK installed. Run
```sh
gcloud config set project {PROJECT_ID} && gcloud auth login
```
Replacing `PROJECT_ID` with the project id (this will be same as the java environment variable) and `ACCOUNT` with the gmail account you use to access the GCP console. 

### Launching the server

Begin the persistent server with `cd ./legalquestionizer && ./gradlew bootRun` in a terminal.
