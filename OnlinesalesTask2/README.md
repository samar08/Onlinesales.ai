Webapi that I used:
I have used the free api provided by mathjs.org (https://api.mathjs.org/) for finding the result of each expression

Assumptions
i) I am assuming that the api only supports "webapi.limitpersecond" number of requests per second. I have specified this on application.properties file. User can edit this number
ii) The size of the dataset is 500, and the user can change this by modifying the "datasize.multiplier" in application.properties. If user want to change the data they can do so by modifying the data in RequestController.java
iii) The application runs on default 8080 port

iv) the main idea of the project is that , I am running all the webapi.limitpersecond no. api calls concurrently using Threadpool of that size and CompletableFuture and once those requests are complete then only I move to the next set of webapi.limitpersecond calls to mimic the scenario that publicAPI can only handle webapi.limitpersecond calls per second.

v) Also if there is an invalid expression instead of exiting, my application will properly handle the exception prints appropriate error message for that request and returns output of other valid requests. I have covered this functionality in the the unit test case.
