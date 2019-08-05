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

To run this as a web server use these arguments `server src/main/resources/config-prod.yaml` and go to http://localhost:8089/swagger

You can then test your code using Swagger or the built in unit and integration tests.

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
Fill in connection data information in the ConnectionData class in the authentication/credentials package as per the documentation in the Connection Data file.
If this integration has a session, fill in the SessionCredentialContainer. This class keeps just a JSON node. So to get your fields easily, just make a helper get method for each field.
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


## 9 Fetches

This basically works just like metadata fetches. One class to write fetch instructions, one class to parse the responses, and some built in unit and integration tests to help you develop it.

There are four types of fetches.

Full fetches - get all records for an entity
Incremental fetches - get all records for an entity since the last fetch
Time Limited Fetch - get all records between two timestamps
Sample Fetch - return a limited sample of data

They all pretty much work the same way. It just changes by how you query for information. So we will just work on full fetches and you should be able to extend that to the rest.

In Fetch Setup, look for the performFullFetchSetup method. And then it just works like the metadata fetch! Fill the FetchSetupData class with 

## 10 Extract Data From the Fetch Response

Once you get something back from the integration, just like Metadata, it might be any format. And quite frequently it's a normal format that is easy to parse but with the actual entity information just embedded in it somewhere.

So all this does is take that response, pull out the piece that is the entity information (and that matches the defined metadata), and returns it as JSON. You don't need to parse it or anything, that will be done automatically using the metadata information you defined in the last step.

Usually it's already JSON and it's simple. If it's XML, then you'll need to translate that XML to JSON.



