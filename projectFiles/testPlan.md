# Test plan

## 1.	Introduction
### 1.1	Purpose
The purpose of the Iteration Test Plan is to gather all of the information necessary to plan and control the test effort for a given iteration. 
It describes the approach to testing the software.
This Test Plan for vnv supports the following objectives:
-	Identifies the items that should be targeted by the tests.
-	Identifies the motivation for and ideas behind the test areas to be covered.
-	Outlines the testing approach that will be used.
-	Identifies the required resources and provides an estimate of the test efforts.
### 1.2	Scope
This document describes the used tests, as they are unittests and functionality testing.
### 1.3	Intended Audience
This document is meant for internal use primarily.
### 1.4	Document Terminology and Acronyms
- **SRS**	Software Requirements Specification
- **n/a**	not applicable
- **tbd**	to be determined
### 1.5	 References
- [GitHub](https://github.com/SlaxXxX/tinfb4se)
- [Blog](https://tinfb4se.wordpress.com/)
- [Overall Use case diagram](https://github.com/SlaxXxX/tinfb4se/blob/master/projectFiles/OverallUseCaseDiagram.png)
- [Software Requirements Specification](https://github.com/SlaxXxX/tinfb4se/blob/master/projectFiles/SoftwareRequirementsSpecification.md)
- [Software Architecture Document](https://github.com/SlaxXxX/tinfb4se/blob/master/projectFiles/SoftwareArchitectureDocument.md)
- [UC Alter Path](https://github.com/SlaxXxX/tinfb4se/blob/master/projectFiles/useCases/Alter%20Path.md)
- [UC Close Game](https://github.com/SlaxXxX/tinfb4se/blob/master/projectFiles/useCases/Close%20Game.md)
- [UC Move Camera](https://github.com/SlaxXxX/tinfb4se/blob/master/projectFiles/useCases/Move%20Camera.md)
- [UC Navigate Menu](https://github.com/SlaxXxX/tinfb4se/blob/master/projectFiles/useCases/Navigate%20Menu.md)
- [UC Place Tower](https://github.com/SlaxXxX/tinfb4se/blob/master/projectFiles/useCases/Place%20Tower.md)
- [UC Place Unit](https://github.com/SlaxXxX/tinfb4se/blob/master/projectFiles/useCases/Place%20Unit.md)
- [UC Save Level](https://github.com/SlaxXxX/tinfb4se/blob/master/projectFiles/useCases/Save%20Level.md)
- [UC Select Level](https://github.com/SlaxXxX/tinfb4se/blob/master/projectFiles/useCases/Select%20Level.md)

## 2.	Evaluation Mission and Test Motivation
### 2.1	Background
By testing our project, we make sure that all changes to the sourcecode do not break the functionality. Also by integrating the test process in our deployment process, we make sure that only working versions of our project getting deployed. So the web application is always available.
### 2.2	Evaluation Mission
Our motivation in implementing tests came at an early stage to recognize the need for errors and to ensure the functionality and thus the outstanding quality of the software.
### 2.3	Test Motivators
Our motivation in implementing tests came at an early stage to recognize the need for errors and to ensure the functionality and thus the outstanding quality of the software.
Our testing is motivated by 
- quality risks 
- technical risks, 
- use cases 
- functional requirements
## 3.	Target Test Items
The listing below identifies those test items (software, hardware, and supporting product elements) that have been identified as targets for testing. This list represents what items will be tested. 
Items for Testing:
- java backend
- UI (Android-App & Desktop-Launcher)
## 4.	Outline of Planned Tests
### 4.1	Outline of Test Inclusions
Unit testing of the java backend and functional testing of the user-interface aswell as a installationtest from a user.
### 4.2	Outline of Other Candidates for Potential Inclusion
Integrationtesting of key elements that work on a wider scope are potential test szenarios aswell but these are not in scope if our current testing process.
## 5.	Test Approach
### 5.1	Testing Techniques and Types
#### 5.1.1	Function and Database Integrity Testing
n/a
#### 5.1.2	Unit Testing
|| |
|---|---|
|Technique Objective  	| Exercise functionality of model functions. Test for right data entry and right data output. |
|Technique 		|  Execute JUnit Test functions in our test classes |
|Oracles 		|  Each test expect the right value given in the assertequals function |
|Required Tools 	|  JUnit Test |
|Success Criteria	|    Testcoverage > ?%      |
|Special Considerations	|     -          |
#### 5.1.3	Business Cycle Testing
n/a
#### 5.1.4	User Interface Testing
Automated with use of Cucumber and Feature-Files
#### 5.1.5	Performance Profiling 
n/a
#### 5.1.6	Load Testing
n/a
#### 5.1.7	Stress Testing
n/a
#### 5.1.8	Volume Testing
n/a
#### 5.1.9	Security and Access Control Testing
n/a
#### 5.1.10	Failover and Recovery Testing
n/a
#### 5.1.11	Configuration Testing
n/a
#### 5.1.12	Installation Testing
n/a
## 6.	Entry and Exit Criteria
### 6.1	Test Plan
#### 6.1.1	Test Plan Entry Criteria
Building a new version of the software will execute the testprocess.
#### 6.1.2	Test Plan Exit Criteria
When all tests pass without throwing an exception.
## 7.	Deliverables
### 7.1	Test Evaluation Summaries
n/a
### 7.2	Reporting on Test Coverage
n/a
### 7.3	Perceived Quality Reports
n/a
### 7.4	Incident Logs and Change Requests
n/a
### 7.5	Smoke Test Suite and Supporting Test Scripts
n/a
### 7.6	Additional Work Products
n/a
#### 7.6.1	Detailed Test Results
n/a
#### 7.6.2	Additional Automated Functional Test Scripts
n/a
#### 7.6.3	Test Guidelines
n/a
#### 7.6.4	Traceability Matrices
n/a
## 8.	Testing Workflow
n/a
## 9.	Environmental Needs
[This section presents the non-human resources required for the Test Plan.]
### 9.1	Base System Hardware
n/a
### 9.2	Base Software Elements in the Test Environment
n/a
### 9.3	Productivity and Support Tools
n/a
## 10.	Responsibilities, Staffing, and Training Needs
### 10.1	People and Roles
n/a
### 10.2	Staffing and Training Needs
n/a
## 11.	Iteration Milestones
n/a