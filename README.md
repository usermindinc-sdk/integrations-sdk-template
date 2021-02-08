# Integrations SDK

# Process Overview
This is a template to help you create an SDK. And SDK does several things:

* Authentication - a call to confirm if the supplied connection information is valid and to generate the credentials for oauth type of integrations.
* Metadata Fetch - this is data about the data. If you fetch a database table, for example, the metadata would list the data fields and their types (String, date, boolean, etc.) Most of the time you can get metadata from the integration itself, if not then you can specify it in the SDK.
* Fetch - this pulls data from the third party integration. We refer to the data as entities where an entity is defined as a standalone set of records - for example, each table in the database would be an entity. So if an integration has an API that returns customer information that would be an entity, and if it had an API returning product data that would be a second entity.
* Actions - this pushes data to the third party integration.

# Documentation
We have an internal wiki with information on every SDK:
https://usermind.atlassian.net/wiki/spaces/LOH/pages/443940868/Third+Party+Integration+Information
This SDK will not be considered complete without documentation. If you make REST calls, then using Postman export the calls as curl calls and put them in the wiki - that means other developers can make those calls in Postman if needed to diagnose issues. Document any oddities that would be good to know if something goes wrong, and if there is an non-intuitive setup process document that as well. Please use screenshots if they are useful!

Document it as you go, NOT at the end. When you set things up is the right time to take screenshots, people do not tend to go back and do it. Similarly if you hit something odd add it to the documentation - if you leave everything to the very end, you'll forget all the little odd details which are the things most useful to have recorded.

And finally - TEST ERROR CASES. Get useful error messages and return them - if something fails and we see an error message saying "this failed" we will not be very pleased with you. Most of the time you get an error response from the integration telling you exactly why something failed - make sure you pull that out and return it! 

Unit tests are a minimum as error cases can be integration specific - so add some, and add failure cases!

#Code Quality Metrics
We use Sonar to check for unit test coverage and code quality. Try for 80% or higher unit test coverage - if it is close it may be acceptable (we do allow flexibility on a case by case basis) but when we make infrastructure changes this is how we ensure each SDK still works, so unit test coverage is key. If you run Sonar locally, you can run a scan and view the results with this command:
```mvn -T C2.0 clean org.jacoco:jacoco-maven-plugin:prepare-agent package -Dse.eris.notnull.instrument=false -Dmaven.test.failure.ignore=true && mvn sonar:sonar```
For internal developers, our build pipeline runs Sonar automatically and writes results internally to sonar.usermind.com.

In addition to unit test metrics, we expect Sonar to report 0 bugs, 0 vulnerabilities, and we do expect a pass at code smells as well but don't have specific metric requirements for that.

# Development Overview
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
In the file TestClassFactory, fill in the methods getWork ingTestCredentials and getNonWorkingTestCredentials with a valid credential structure that will validate and fail validation respectively. This will be of the format:

`{"credentials":{"appId":"aaa","appSecret":"aaa"}}`

For now, a different Usermind service will authenticate and will give you that credential block. That should contain the results of your authentication as fields inside the credential block. Those are the fields you'll need to know in order to make and authenticate calls. Putting them in this class will enable the unit and integration tests to work.

### 1.1 OAuth Authentication/Generate Credentials
Any integration which supports OAuth authentication with Authorization Code grant, then we need to extend couple of Classes from base library.
OAuth with Authorization code grant is a 3 legged dance and most of the methods are written in SDK base library.

There are 2 classes which needs to updated to avail the OAuth functionality written in SDK base library.

#### a. SdktemplateConnectionData class should extend the class OAuthConnectionData.

OAuthConnectionData has few abstract methods which needs to be implemented in SdktemplateConnectionData class
- **Method 1 - getClientId()**
- **Method 2 - getClientSecret()**
- **Method 3 - setClientId(String clientId)**
- **Method 4 - setClientSecret(String clientSecret)**

OAuth process involves clientId and clientSecret. More details of this can be found here - https://tools.ietf.org/html/rfc6749#section-2.2
We need to supply these details to Orchestration layer. So implementation of these getters and setters helps Orchestration classes to access these details.  

- **Method 5 - parseAccessToken(ObjectNode tokenResponse)**

This method will be used in Connection Creation process from UI.
When OAuth grant call succeeds, we need to parse the response body as it might be different for each integration.
We need to convert the response body to ConnectionData class.
Objective of this method is to parse the response body and assign the values to its class variables.


#### b. AuthenticationServiceSdktemplate class should extend the class OAuthService.

OAuthService has 2 methods with default implementation. Override these methods if required.

- **Method 1: initiateOAuthRequest** -
This method generates the request uri which will be used to launch the login page to grant the access. 
- **Method 2: grantCode** -
This method is used to request a grant access to OAuth server with an authorization code received from previous step.


## 2. Resource Files in the test package
When you authenticate, you will get a token back of some sort. Put that token in authentication/credentials/token.json.
Try bad credentials, and put that response in authentication/credentials/invalidtoken.json.
If you can fetch Metadata from this integration, put the result of that call in metadata/MetadataFromIntegration.json and delete MetadataCreatedInCode.json. If you can't, then delete MetadataFromIntegration.json.
Fetch an entity, and put that result in fetch/entity.json. You might want to rename that file to the name of the entity, but then make sure you change the test classes to match.

Now you're ready to write the authentication code.

## 3. Credential Classes 
Fill in connection data information in the ConnectionData class in the authentication/credentials package as per the documentation in the Connection Data file.
If this integration has a session, fill in the SessionCredentialContainer. This class keeps just a JSON node. So to get your fields easily, just make a helper get method for each field.
If you do not have sessions, delete the SdktemplateSession and the SdktemplateSessionManager. If you remove the classes and rebuild, you'll find the references to them that you'll need to remove as well as unit test classes for the Session that you can delete.

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



