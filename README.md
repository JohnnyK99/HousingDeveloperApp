# HOUSING DEVELOPER APPLICATION

Java project, with the use of threads, data structures, inheritance and more.

Manage your housing developments, buildings, apartments and their tenants.

All user interaction is performed through the console.


###User requirements:
- a developer may own any number of housing developments 
- every development may consist of multiple buildings
- every building has a finite number of apartments
- an apartment may have multiple tenants, but always the first person who starts the lease is the renter, responsible for the payments
- the renter has a possibility to lease a parking space in the same housing development
- the total number of rooms (apartments and parking spaces) leased by one person cannot be greater than five
- the renter may register and deregister tenants to apartments, as well as manage vehicles and things in parking spaces
- every lease has a start and finish date
- when a lease has expired, a debt file is issued and stored in the renter's record
- the app implements time passage mechanism
  - every five seconds a day is added to the current date
  - every ten seconds all the leases are checked in order to control possible expirations or terminations
- when a lease is renewed or terminated, the debt file gets deleted
- if a lease is not renewed or terminated in 30 days time, the file stays in the record and the room is cleared:
  - in case of an apartment, all tenants get evicted
  - in case of a parking space containing a vehicle, the vehicle is removed and the lease is renewed for two months
  - in case of a parking space not containing a vehicle, all the contains are removed
-  a person with more than three debt files among rooms in the same development cannot lease another room in this development, an Exception gets thrown with a list of all rooms leased by this person
- vehicles are divided into categories, such as:
  - off-road car
  - city car
  - boat
  - motorcycle
  - amphibian
- every vehicle should have a characteristic individual to their type
- there should be an option to save a development's state to a file, in a clear, aesthetic way, with the rooms sorted in ascending order by their size.
- the user should be able to access all functionalities through the console

Project idea: mgr Sławomir Dańczak, PJATK