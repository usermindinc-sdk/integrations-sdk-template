# Integrations SDK

# Overview
Simply get this template repository, and check out the develop branch. Then run sdkrename.sh to create a new worker, open that in IntelliJ, and fill out the TODOs. Do the Authentication ones first, then the Metadata ones, and finally the Fetch ones.

```sdkrename.sh integration_name```

And then to pull out something that is hidden below - once you write some code, you can test it with the Swagger API or else an integration test. You can find the Swagger interface at
 ```http://localhost:8089``` 
 and look at the integration tests in 
 ```src/test/java/com/usermind/usermindsdk/fetch/fetchsetup/FetchSetupSdktemplateIT.java```
Those integration tests run a full fetch and extract the data, thus exercising a lot of the code. The integration and unit tests built into the code make this much easier to develop - and when they work, you have most likely done this correctly.

Most other structures are designed to have the compiler help you get things correct. 

# Details

Start by testing the Rest API calls (or other calls) in Curl or Postman. Before you start this you should be able to authenticate, fetch metadata if available, and issue a call that returns the full set of data. Ideally you'll also be able to issue a call to get a set of data that has been updated after a given timestamp as well.

Then before you begin coding, put the results of those calls inside your starter project.

## 1. Test Credentials
In the file TestClassFactory, fill in the methods getWorkingTestCredentials and getNonWorkingTestCredentials with a valid credential structure that will validate and fail validation respectively. This will be of the format:

`{"credentials":{"appId":"aaa","appSecret":"aaa"}}`

For now, a different Usermind service will authenticate and will give you that credential block. That should contain the results of your authentication as fields inside the credential block. Those are the fields you'll need to know in order to make and authenticate calls. Putting them in this class will enable the unit and integration tests to work.

## 2. Resource Files in the test package
When you authenticate, you will get a token back of some sort. Put that token in authentication/credentials/token.json.
Try bad credentials, and put that response in authentication/credentials/invalidtoken.json.
If you can fetch Metadata from this integration, put the result of that call in metadata/MetadataFromIntegration.json and delete MetadataCreatedInCode.json. If you can't, then delete MetadataFromIntegration.json.
Fetch an entity, and put that result in fetch/entity.json. You might want to rename that file to the name of the entity, but then make sure you change the test classes to match.

Now you're ready to write the authentication code.

## 3. Credential Classes 
Fill in credential information in the Credential Container class in the authentication/credentials package as per the documentation in the Credential Container file. That file keeps just a JSON node. So to get your fields easily, just make a helper get method for each field.
Fill in the same information in the resource registration/IntegrationConfiguration.json in the auth section. Follow the example in the JSON. 
If this integration has a session, fill in the SessionCredentialContainer just as you did the Credential Container, but for the session information instead.
If you do not have sessions, delete the Session Credential Container and the Session Credential Manager.

## 4 Authentication Code
In AuthenticationService, fill in the validate method. You'll be given the credentials. See if they work, use them to get a list of available entities to fetch, and return.
If there is a session, you'll need to fill in the Session Credential Manager class's "getNewSession" method as well.

To test this, there are built in unit and integration tests for the Authentication Service. The Integration Test will just work and let you walk through your code as it calls the Integration and understand the behaviour. The unit test will work once you've adjusted the mock rest server so it fields your calls. I find it easiest to use the integration test at first, and then fix up the unit tests to work appropriately afterwards. Credentials expire - making the unit test work is WELL worth the effort when the Integration Test stops working later.


DO NOT CONTINUE UNTIL THE AUTHENTICATION CODE WORKS.

The next thing that is needed is fetching the metadata. And to fetch it, there is one more authentication piece.

## 5 Entity Information
Fill in the Entity Information class as shown in the template. This simply lists the entities, and the primary key for each one as well as the field to use when fetching only recently updated data.

## 6 Fetch Metadata
You don't actually fetch the metadata - the orchestration layer does. This code simply gives the orchestration layer the information on how to do that. Then the orchestration layer can handle errors and retry them, alerting, etc. See the documentation in the class for how to do this.

OR - maybe the integration doesn't supply metadata. In that case, you will have to describe it here yourself. See the examples in the code.

## 7 Add Authentication Information
Sessions expire. So putting the information in when you write the fetch causes a problem - what happens in the orchestration layer if it has expired? So the authentication information is NOT added in the step above. The orchestration layer waits until it's about to call for the data, and then it passes the link and headers into the Add Authentication Information class. So in that class, add the token to the headers or to the API, and return them. See examples in the class.

## 8 Translate the response 
So coming back is SOMETHING. Maybe Json. Maybe XML. So in the class Convert Metadata To Standard Format, take that blob and parse it into classes that the SDK can understand. See examples in the file.

If you had to describe it yourself in step 6, then you will not need to do anything in the class. The templated code is correct.

To test this, there are built in unit and integration tests. The conversion class has its own. The rest can be called in the Integration test to see live data, but the unit test will work as is (though you should improve the assertions.)

Note that the Integration Test will call the fetch, get the instructions, make the calls (which will call into the add authentication class) and then try to translate the results using the Conversion class - so the built in test will walk through everything you've written.

DO NOT CONTINUE UNTIL THE METADATA FETCH WORKS.




In your new integration, follow these steps. Note that the steps direct you to a class, the class will usually have more information in it.

Things you'll need:

# Registration

To do this, you'll need to know how to authenticate. That will tell you the information needed to make a connection.
Go to the file 
```src/resources/com/usermind/usermindsdk/registration/IntegrationConfiguration.json```

Fill in the auth section with the authentication information you'll need the user to enter.

# Authentication

This will result in the authentication service working, test it by using the Rest API.

### Credential Container

First, put the same key information you used in the Registration Step into 
```com/usermind/usermindsdk/authentication/CredentialContainerSdktemplate.java```

This is essentially a helper class to make it easy to remember and use the correct authentication fields - ie, your development environment can now help.

Then while you have it, go ahead and paste the credential string into the test factory class:
```src/test/java/com/usermind/usermindsdk/TestClassFactory.java```

### Authentication Service

To make the authenticator work, write the 'validate' method in 
```com/usermind/usermindsdk/authentication/AuthenticationServiceSdktemplate.java```

Essentially, connect and get metadata. If it fails, let it throw an exception. If it succeeds, return a list of the entities you can fetch.

### Session Information

As a part of that, if this integration keeps a session, flesh out the SessionCredentials and SessionInformation classes. If there is no session, then delete those classes instead.

### Add Authentication Information 
When fetches are made, authentication information is added at the last minute. This class gets the web call that is about to be make, and is expected to adjust it to have updated authentication.

To do this, just fill in:
```com/usermind/usermindsdk/authentication/AddAuthenticationInformationSdktemplate.java```


# Fetches

### Fetch Setup
    
This is essentially giving the orchestration code instructions about how to run a fetch. FetchSetupData is a class that consists of different subclasses that track the instruction types - hit these REST Apis, just write this data, and in the future other things - read this database, etc. 

It is in the file    
```com/usermind/usermindsdk/fetch/fetchsetup/FetchSetupSdktemplate.java```

All the fetches are similar, but there are different calls for full fetch, incremental fetch, etc.

# Extractor
The problem we hit next is that when you make the Rest API call, etc, the data needs to be massaged or translated before we can use it. And since there's no standards here, we can't automate how to do that.

### Extract Data
Fill in this class to take whatever comes back from the integration and pull it out.
```com/usermind/usermindsdk/fetch/fetchoperations/ExtractDataFromSdktemplateResponse.java```

If it is a list with ten items, then return the ten different items. And if it's something that isn't JSON, then convert it to JSON so that it can be consumed by the remaining infrastructure. See the template class for some more information on what exactly that means.
### Metadata Conversion 
Metadata has it's own call as it needs a differently formatted return. Metadata fetches go through the call above to split them up, and then get fed to
```com/usermind/usermindsdk/fetch/metadatafetch/ConvertMetadataToStandardFormatSdktemplate.java```

Basically this method requires you to go through the metadata coming back and break it into classes that tell our upstream services what exactly that data is.

### Entity Information
* It has one helper class that tells us the primary key in that entity, and also specifies the date field to use for incremental fetches.
```com/usermind/usermindsdk/fetch/metadatafetch/EntityInformationSdktemplate.java```


# Rate Control 
Not done yet. But when it is, rate control logic will be here.
```com/usermind/usermindsdk/workerinformation/RateControlSdktemplate.java```


#Create the Kubernetes pod
While in the staging Kubernetes cluster, go to the Kubernetes directory and run

```kubectl create -f service-staging.yaml -n integrations```

Do the same for production using service-prod.yaml.

# Testing

### Credentials and Authentication
Put a copy of working test credentials in the test class factory. That will help when running integration tests that actually connect.
```com/usermind/usermindsdk/TestClassFactory.java```

And put a copy of the authentication response in a JSON file. This can be the session information if that is what this integration does.
```com/usermind/usermindsdk/authentication/token.json```

That should MOSTLY make the authentication tests work.
```com/usermind/usermindsdk/authentication/AuthenticationServiceSdktemplateTest.java```


### Extractor   
Put the same authentication response from above in the Extractor unit tests, inside the file (coming soon - will be the same file as above)
```com/usermind/usermindsdk/fetch/fetchoperations/ExtractDataFromSdktemplateResponseTest.java```

### Fetches
There is an integration test that will hit a live third party server and run a full fetch.
```com/usermind/usermindsdk/fetch/fetchsetup/FetchSetupSdktemplateIT.java```

And a small unit test for full fetches that needs to be extended:
```com/usermind/usermindsdk/fetch/fetchsetup/FetchSetupSdktemplateTest.java```

### Registration
A unit test to cover registrations is also included.
```com/usermind/usermindsdk/registration/RegistrationSdktemplateTest.java```