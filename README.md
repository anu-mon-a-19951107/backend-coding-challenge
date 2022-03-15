# Java Coding Challenge - Remarks updated by Anu Antony

## Introduction
Given is a Spring Boot application that implements a REST service for manipulating ads and customers. This application is already runnable, but does not do anything useful, yet. 
Your task is to make it more useful. The scenario of this application is a very simplified version of mobile.de's domain - we have customers, and these customers want to list ads 
on our platform.

It is very likely that you will not be able to finish all subtasks in the given time frame. You are basically free to choose which subtasks you work on, but keep in mind that some of the subtasks depend on others. They are listed here in no particular order. Some of the subtasks will have a bigger impact on the overall assessment than others.

You have one week to finish this challenge.

--------------------------------------------------------------------------------
## I used MongoDB as the Database, Thymeleaf,Bootstrap and jQuery for front-end
--------------------------------------------------------------------------------


## Tasks - Remarks
1. The classes in this artifact are all in one package called `de.mobile`. Create a proper package structure and move the classes to where they belong. - Done

2. Make the domain objects `MobileAd` and `MobileCustomer` persist, either on a local `mysqld` or a local `mongodb`. -Done

3. Currently the application only handles read use cases for ads. Extend the application so that ads can also be created. - Done

4. There is a `MobileCustomer` class in the artifact. Extend the application so that it is also possible to create and delete customers. - Done

5. Make sure that the ad related usecases become customer-aware (meaning that an ad needs a customer in every case). - Done

6. Add validation to the calls that create new entities. The following rules should apply: - Done
   - An ad needs a customer id, a make name, a model name and a category. - Done
   - A customer needs a formally valid email address, a firstname and a lastname composed of alphanumeric characters. - Done
   
7. Configure logging properly so that log messages are logged to a file. - Done ,see mobile.de.app.log file

8. Implement a proper error handling for 404 and 500, with different error messages. Please describe how to reproduce both error statuses. - Done

	- 404 can be recreated while performing ad/get/{adId} by giving and invalid adId parameter. eg: the adId is not present in the database table "mobileAd"
		This will throw NullPointerException and displays "Entered Inavlid data" message.  The 404 error actually for page not found , this error can be displayed when
		you are trying with a wrong URL value.
	- 500 can be recreated when perform /create operation.This can be done by using postman, by adding wrong information
	
	- An error controller is written to handle the 404 white label error.
	- Also the application has a custom exception handler back-end level for dealing 500 and 404 error.
	
I initially developed back-end to test with post-man , later only I developed front-end 

9. Change the project so that it builds an executable jar. - Done (the jar can be found in /target folder)

10. Create a simple HTML/Javascript application that talks to the above REST service. It should be able to list all ads. Making it look pretty would be a plus. - Done
		-Used thymeleaf, bootstrap,jquery for achieving this
11. If the ad data and the customer data were not accessible from the database but from other RESTful services, what approach would you use to integrate these services? 
	
	- One rest service can be accessed from another using the spring boot's RestTemplate client.
	- RestTemplate client is desined to call both internal and external apis.
	- WebClient is a client to RestTemplate , which provides an efficient way to connect to other rest apis.
	
12. If you decide not to write tests for your work for reasons of timeboxing, please spend a few minutes to describe what parts of the application you would write tests for, 
and what aspects these tests would cover, and what role they play in the development process. - Not Done
	
	- Create ad - In the small application I developed , this is the only module has more than one service calls. And it has the more validation checks, So I would like to write the	
		unit test case for this module as whole.
	- Create Customer - This module has customer validation and its has to be error free and need exception tolerance.
	- Since I belive out of the services, only these two a vital to this application which I developed.

I primarily focused to develop the backend area, so I did not get more time for focusing in overall beauty and look of it.
	
Good Luck!

