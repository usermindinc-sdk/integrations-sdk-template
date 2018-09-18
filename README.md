# Integrations SDK

#Overview
Simply get this template repository, and check out the develop branch. Then run sdkrename.sh to create a new worker, open that in IntelliJ, and fill out the TODOs.

```sdkrename.sh integration_name```

#Details

In your new integration, follow these steps.

Things you'll need:

#Registration

To do this, you'll need to know how to authenticate. That will tell you the information needed to make a connection.
Go to the file 
```src/resources/com/usermind/usermindsdk/registration/IntegrationConfiguration.json```

Fill in the auth section with the authentication information you'll need the user to enter.

#Authentication

This will result in the authentication service working, test it by using the Rest API.

###Credential Container

First, put the same key information you used in the Registration Step into 
```com/usermind/usermindsdk/authentication/CredentialContainerSdktemplate.java```

This is essentially a helper class to make it easy to remember and use the correct authentication fields - ie, your development environment can now help.

###Authentication Service

To make the authenticator work, write the 'validate' method in 
```com/usermind/usermindsdk/authentication/AuthenticationServiceSdktemplate.java```

Essentially, connect and get metadata. If it fails, let it throw an exception. If it succeeds, return a list of the entities you can fetch.

###Session Information

As a part of that, if this integration keeps a session, put a class to track the session information in the authentication package.

###Add Authentication Information 
When fetches are made, authentication information is added at the last minute. This class gets the web call that is about to be make, and is expected to adjust it to have updated authentication.

To do this, just fill in:
```com/usermind/usermindsdk/authentication/AddAuthenticationInformationSdktemplate.java```


#Fetches

###Fetch Setup
    
This is essentially giving the orchestration code instructions about how to run a fetch. FetchSetupData is a class that consists of different subclasses that track the instruction types - hit these REST Apis, just write this data, and in the future other things - read this database, etc. 

It is in the file    
```com/usermind/usermindsdk/fetch/fetchsetup/FetchSetupSdktemplate.java```

All the fetches are similar, but there are different calls for full fetch, incremental fetch, etc.

#Extractor
The problem we hit next is that when you make the Rest API call, etc, the data needs to be massaged or translated before we can use it. And since there's no standards here, we can't automate how to do that.

###Extract Data
Fill in this class to take whatever comes back from the integration and pull it out.
```com/usermind/usermindsdk/fetch/fetchoperations/ExtractDataFromSdktemplateResponse.java```

If it is a list with ten items, then return the ten different items. And if it's something that isn't JSON, then convert it to JSON so that it can be consumed by the remaining infrastructure. See the template class for some more information on what exactly that means.
###Metadata Conversion 
Metadata has it's own call as it needs a differently formatted return. Metadata fetches go through the call above to split them up, and then get fed to
```com/usermind/usermindsdk/fetch/metadatafetch/ConvertMetadataToStandardFormatSdktemplate.java```

Basically this method requires you to go through the metadata coming back and break it into classes that tell our upstream services what exactly that data is.

###Entity Information
* It has one helper class that tells us the primary key in that entity, and also specifies the date field to use for incremental fetches.
```com/usermind/usermindsdk/fetch/metadatafetch/EntityInformationSdktemplate.java```


#Rate Control 
Not done yet. But when it is, rate control logic will be here.
```com/usermind/usermindsdk/workerinformation/RateControlSdktemplate.java```


#Create the Kubernetes pod
While in the staging Kubernetes cluster, go to the Kubernetes directory and run

```kubectl create -f service-staging.yaml -n integrations```

Do the same for production using service-prod.yaml.

#Testing

###Credentials and Authentication
Put a copy of working test credentials in the test class factory. That will help when running integration tests that actually connect.
```com/usermind/usermindsdk/TestClassFactory.java```

And put a copy of the authentication response in a JSON file. This can be the session information if that is what this integration does.
```com/usermind/usermindsdk/authentication/token.json```

That should MOSTLY make the authentication tests work.
```com/usermind/usermindsdk/authentication/AuthenticationServiceSdktemplateTest.java```


###Extractor   
Put the same authentication response from above in the Extractor unit tests, inside the file (coming soon - will be the same file as above)
```com/usermind/usermindsdk/fetch/fetchoperations/ExtractDataFromSdktemplateResponseTest.java```

###Fetches
There is an integration test that will hit a live third party server and run a full fetch.
```com/usermind/usermindsdk/fetch/fetchsetup/FetchSetupSdktemplateIT.java```

And a small unit test for full fetches that needs to be extended:
```com/usermind/usermindsdk/fetch/fetchsetup/FetchSetupSdktemplateTest.java```

###Registration
A unit test to cover registrations is also included.
```com/usermind/usermindsdk/registration/RegistrationSdktemplateTest.java```