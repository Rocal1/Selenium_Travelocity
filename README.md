# Travelocity
Web Automation Testing Exam

Web Automation 
Introduction 
We are going to use the Travelocity webpage for this exam, on this page you will need to interact with different web controls and to create different functions and methods to work with dates, searching flights, etc. 
We strongly recommend you to think about the solution before starting to code, think how are you going to organize the code, which functions you will need and how are you going to organize your pages. 
Also, please consider that web pages can change, so if you find that an step we describe here is not 100% reproducible, you can use common sense to do your automated test case without modifying the original functionality.

## Exercises 
For the exercises you must create a maven project and use everything you learned in the training, applying the best practices and standards mentioned in the videos. 

### Exercise 1 
Begin the process of booking a flight till the complete credit card information page. 

##### Precondition​: The user is not logged in. 
##### Steps: 
1. Search for a flight from LAS to LAX, 1 adult (clicking on Flight/Roundtrip). Dates should be at least two month in the future and MUST​ be selected using the datepicker calendar. 2. Verify the result page using the following: 
 - a. There is a box that allow you to order by Price, Departure, Arrival and Duration. 
 - b. The select button is present on every result 
 - c. Flight duration is present on every result 
 - d. The flight detail and baggage fees is present on every result 
3. Sort by duration > shorter. Verify the list was correctly sorted. 
a. From this step select elements on the list using the button Select (don’t use the elements with Hotel Select Flight + Hotel) 
4. In the page (Select your departure to Los Angeles), select the first result. 5. In the new page (Select your departure to Las Vegas), select the third result. a. if exists the pop-up asking for “flight with a Hotel”, select “no, thanks, maybe later” 6. Verify trip details in the new page, by: 
 - a. Trip total price is present 
 - b. Departure and return information is present 
 - c. Price guarantee text is present 
7. Press Continue Booking button. 
8. Verify the “Who’s travelling” page is opened by choosing at least 5 validations to be performed. 
### Exercise 2 
Begin the process of booking a flight with hotel and car. 

#### Precondition​: The user is not logged in. 
#### Steps:

1. Go to “Flight + Hotel” page. Verify the page is correctly opened. 
2. Search for a flight from LAS to LAX, 1 adult. Date should be at least two month in the future and MUST ​be selected using the datepicker calendar. The trip must last 13 days. 
3. Verify results page by choosing at least 5 validations to be performed. 
4. Sort by price. Verify the results were correctly sorted. 
5. Select the first result with at least 3 stars. 
6. In the new page, verify the hotel is the selected in the previous step by choosing at least 3 validations to be performed. 
7. Select the first room option 
8. In the new page,(Now select your departing flight), select the first result. 
9. In the new page (Now select your return flight), select the third result. 
10. Select a car 
11. Verify Trip Details by choosing at least 5 validations to be performed. 
 - a. Press Continue button. 
12. Verify the trip details are still correct. Continue 
13. Verify the “Who’s travelling” page is opened by choosing at least 5 validations to be performed. 

### Exercise 3 
Verify that search by hotel name works properly. 

##### Precondition​: The user is not logged in. 
##### Steps: 

1. Go to Hotels page. (Clicking on menu ”Hotels/Hotel Only”) 
2. Complete “Going to” field with the word “Montevideo, Uruguay”. Do the Search 
3. Verify that: 
 - a. Sponsored results appear first 
 - b. You have the option of receive a discount by entering your email address 

### Exercise 4 
Verify that the error message displayed when looking for a hotel in incorrect dates is correct. 

##### Precondition​: The user is not logged in. 
##### Steps: 

1. Click on “Flight + Hotel” option.
2. Complete all the fields. 
3. Select the checkbox “I only need a hotel for part of my stay” 
4. Complete the new dates fields with dates that are not included in the period of the flight dates. Do the Search 
5. Verify the following error message is displayed: “Your partial check-in and check-out dates must fall within your arrival and departure dates. Please review your dates.” 

### Exercise 5 
Cruises discount is displayed 

##### Precondition​: The user is not logged in. 
##### Steps: 

1. Go to Cruises page. 
2. In the Going to drop down select “Europe” 
3. In the “Departure month” dropdown select a month. Do the Search 
4. Verify the Filter information selected before appears in the refine results section below each dropdown. 
5. In the “Cruise Length” filter, select “10-14 nights” (Verify this information is displayed below the dropdown). 
6. Verify that result page shows cruises with and without discounts 
7. Select the cruise option with more discount, pressing the show dates button first 8. Validate that cruise information is displayed for the selected one
