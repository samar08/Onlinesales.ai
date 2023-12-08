Webapi that I used:
I have used the free api provided by mathjs.org (https://api.mathjs.org/) for finding the result of each expression

Assumptions
i) I am assuming that the api only supports "webapi.limitpersecond" number of requests per second. I have specified this on application.properties file. User can edit this number
ii) The size of the dataset is 500, and the user can change this by modifying the "datasize.multiplier" in application.properties
iii) The application runs on default 8080 port