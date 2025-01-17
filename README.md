**GradePoint-Pro**                                                                                                                                         

**Project Overview :**                                                                                                                 GradePoint-Pro is a Java-based desktop application designed to simplify academic performance management. Built using the Swing library, the application features a login page for user authentication and backend database connectivity for enhanced functionality. It utilizes a CardLayout to navigate between multiple panels seamlessly:

_**1. Login Page:**_ User authentication to ensure secure access.                                                                                 
_**2. Home Page:**_ A welcome screen with navigation options to access other features.                                                            
_**3. GPA Calculator:**_ Calculate semester GPA based on input grades and credit hours.                                                            
_**4. CGPA Calculator:**_ Compute overall CGPA using semester-wise GPA and credit hours.                                                           

**Key Features**                                                                                                                                                     
**1. User Authentication:**
Secure login functionality with backend database integration.                                                                                  
Verifies credentials before granting access to the main application.                                                                            

**2. Input Collection:**
Allows users to input course details, grades, and credit hours.
Validates inputs to ensure data correctness and completeness.                                                                                      

**3. GPA Computation:**
Calculates semester GPA using a weighted average of grades and credit hours.                                                                                          
Formula:                                                                                                                                                              
**GPA = ∑( Grade Points × Credit Hours ) /Total Credit Hours**

 
**4. CGPA Computation:**
Aggregates semester GPAs to compute the overall CGPA.                                                                                                                  
Formula:                                                                                                                                                              
 **CGPA = ∑(GPA of each semester × Credit Hours )/Total Credit Hours**

**5. Error Handling:** 
Detects invalid inputs (e.g., missing credit hours, out-of-range grades).
Prompts the user to correct errors for reliable calculations.


**6. Output Display:**
Presents GPA and CGPA results in an easy-to-read format.
Includes semester-wise performance trends for better insights.

**7. Database Integration:**
Stores user credentials securely in the database.
Manages and retrieves calculation history for returning users.

**8. Customizability:**
Supports various grading systems to accommodate different academic frameworks.

**Future Enhancements :**                                                                                                                                            
_**1. Role-Based Access:**_ Support for student and admin roles.

_**2. Cloud Integration:**_ Enable synchronization of data across devices.

_**3. Analytics Dashboard:**_ Visualize GPA and CGPA trends with charts and graphs.
