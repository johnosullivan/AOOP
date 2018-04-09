# AOOP

## Facility Management System

#### Project Dependencies

- MongoDB - <a href="https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/">Install Here</a>
- Java 8 <a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html">Install Here</a>
- JUnit 4.+ <a href="https://github.com/junit-team/junit4/wiki/getting-started">Install Here</a>

After importing the project into Eclipse, navigate to the "Project Properties" and then to Java libraries. Next, click on "Add External Jars," and within the project root directory is a folder labeled "jars" which contains all the mongo drivers to connect and make a query in Java. Import the following:

- bson-3.6.3.jar
- mongodb-driver-3.6.3.jar
- mongodb-driver-core-3.6.3.jar
- spring 4.3.9 (compatible with Java 8)

Once those three Jars have been included, the project is ready to run the client and JUnit tests. The UML/DB diagram is located <a href="https://github.com/johnosullivan/AOOP/blob/master/AOOP.png">Here.</a> To run the test client and junit tests please ensure mongodb is running in the terminal like so,
```
mongod --dbpath .
```
.

## Clients

-  edu.comp373.view.FacilityClient_WithDAOAccess.java
-  edu.comp373.view.FacilityClient_WithSpring.java
-  edu.comp373.view.FacilityClient_WithSpringAndDAO.java
-  edu.comp373.view.FacilityClient_WithSpringPatterns.java

There are currenlty three clients that interface with the Facility manager system. "FacilityClient_WithDAOAccess" requires a mongoDB session to run on the localhost. "FacilityClient_WithSpring" uses the spring container configuration for dependency injection along with application context. "FacilityClient_WithSpringAndDAO" uses both of the requirements above to operate correctly. "FacilityClient_WithSpringPatterns" uses the spring framework and patterns in a client demo. The dependency relationship using XML is located at ```META-INF/app-context.xml```

## Patterns

<b>Bridge Pattern</b> - The bridge patterns were used in the project with the different user types, like the Inspector and FacilityUser. The bridge pattern allowed for the abstraction and implemention to be designed independently along with the client code to access the abstract parts without being worried about the concrete implementaion in the case of the Users in the system (Inspector/FacilityUser). The implementation of the pattern can be found in the following Java source files: <i>FacilityUser.java, Inspector.java, User.java, UserAbstract.java, and UserInterface.java.</i>

<b>Observer Pattern</b> - The observer patterns, which are behavioral patterns, were utilized in the bridge to allow users to attach to different Requests (Inspections/MaintenanceRequest/Reservation), in which any changes to the state of said request will notify any of the users (Observers) who are attached to it. This required the modification of the different requests to be abstract so they could meet the requirements of the Subject in the Observer Pattern. The observer patterns solve the problem of having the Users knowing when Request is updated in state. The implementation of the pattern can be found in the following Java source files: <i>FacilityUser.java, Inspector.java, User.java, UserAbstract.java, UserInterface.java, Observer.java, InspectionInterface.java, MaintenanceRequestInterface.java, ReservationInterface.java, and Request.java.</i>    

<b>FacilityClient_WithSpringPatterns</b> - The demo will begin with two users using the concrete implementaion of Inspector and FacilityUser. Next, two requests are going to get instances from the spring getBean method. The two different users then attach to the request, where the request will retain all the observers (aka Users). Once the requests are updated it will ```notifyAllObservers``` and print an update message from the concrete user implemention.

## Features

#### Facility.java

- public String getFacilityInformation()
- public boolean assignFacilityToUse(final FacilityUser facilityUser, final LocalDateTime start,final LocalDateTime end)
- public boolean isInUseDuringInterval(final LocalDateTime start, final LocalDateTime end)
- public ArrayList<Inspection> listInspections()


#### FacilityManager.java

- public ArrayList<Facility> listFacilities()
- public ArrayList<Facility> requestAvailableCapacity(Integer capslimit)
- public ArrayList<Facility> vacateFacility(final LocalDateTime start, final LocalDateTime end)
- public Facility addNewFacility(Facility facility)
- public void addFacilityDetail(Facility facility, DetailType type,Object obj)
- public boolean removeFacility(Facility facility)
- public TreeMap<String, Long> listActualUsage()
- public Double calcUsageRate(Facility facility, LocalDateTime datetime)

#### MaintenanceManager.java

- public boolean makeFacilityMaintRequest(MaintenanceRequest maintenance)
- public boolean scheduleMaintenance(MaintenanceRequest maintenance)
- public Double calcMaintenanceCostForFacility(Facility facility)
- public Double calcProblemRateForFacility(Facility facility)
- public Double calcDownTimeForFacility(Facility facility)
- public ArrayList<MaintenanceRequest> listMaintRequests()
- public ArrayList<MaintenanceRequest> listMaintenance()
- public ArrayList<MaintenanceRequest> listFacilityProblems()

#### Additional Supporting Classes

- Address.java / AddressInterface.java
- FacilityInterface.java
- LocalTimeRange.java
- Location.java / LocationInterface.java
- Inspection.java / InspectionInterfacce.java
- MaintenanceRequest.java / MaintenanceRequestInterface.java
- FacilityManagerInterface.java
- MaintenanceManagerInterface.java
- Reservation.java / ReservationInterface.java
- FacilityUser.java / Inspector.java / UserInterface.java
