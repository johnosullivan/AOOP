# AOOP

## Facility Management System

#### Project Dependencies

- MongoDB - <a href="https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/">Install Here</a>
- Java 8 <a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html">Install Here</a>
- JUnit 4.+ <a href="https://github.com/junit-team/junit4/wiki/getting-started">Install Here</a>

After importing the project into Eclipse, navigate to the "Project Properties" and then to Java libraries. Next, click on "Add External Jars," and within the project root directory is a folder labeled "jars" which contains all the mongo drivers to connect and make query in Java. Import the following:

- bson-3.6.3.jar
- mongodb-driver-3.6.3.jar
- mongodb-driver-core-3.6.3.jar

Once those three Jars have been included, the project is ready to run the client and JUnit tests. The UML diagram is located <a href="https://github.com/johnosullivan/AOOP/blob/master/AOOP.png">Here.</a>

## Project Features

#### Facility Object

- public String getFacilityInformation()
- public boolean assignFacilityToUse(final FacilityUser facilityUser, final LocalDateTime start,final LocalDateTime end)
- public boolean isInUseDuringInterval(final LocalDateTime start, final LocalDateTime end)


#### FacilityManager

- public ArrayList<Facility> listFacilities()
- public ArrayList<Facility> requestAvailableCapacity(Integer capslimit)
- public ArrayList<Facility> vacateFacility(final LocalDateTime start, final LocalDateTime end)
- public Facility addNewFacility(Facility facility)
- public void addFacilityDetail(Facility facility, DetailType type,Object obj)
- public boolean removeFacility(Facility facility)
- public TreeMap<String, Long> listActualUsage()
- public Double calcUsageRate(Facility facility, LocalDateTime datetime)

#### MaintenanceManager

- public boolean makeFacilityMaintRequest(MaintenanceRequest maintenance)
- public boolean scheduleMaintenance(MaintenanceRequest maintenance)
- public Double calcMaintenanceCostForFacility(Facility facility)
- public Double calcProblemRateForFacility(Facility facility)
- public Double calcDownTimeForFacility(Facility facility)
- public ArrayList<MaintenanceRequest> listMaintRequests()
- public ArrayList<MaintenanceRequest> listMaintenance()
- public ArrayList<MaintenanceRequest> listFacilityProblems()

