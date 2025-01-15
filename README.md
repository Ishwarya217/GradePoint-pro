# GradePoint-pro
**Project Overview**
The project consists of a Java-based desktop application built with the
Swing library. It employs a CardLayout to manage three distinct panels:
the Home Page, GPA Calculator, and CGPA Calculator. 

Each panel provides specific functionality:
1. Home Page: Serves as the welcome screen and includes navigation options to other panels.
2. GPA Calculator: Allows users to input subject grades and credit hours to compute their semester GPA.
3. CGPA Calculator: Lets users input semester-wise GPAs and credit hours to calculate their overall CGPA.

**The key functions of the GPA and CGPA Calculator System are as follows:**

**1. Input Collection:**

o The system allows users to input essential data, including
course names, grades obtained, and credit hours for each
subject.
o It validates the data to ensure correctness and completeness
before proceeding with the calculations.

**2. Computation of GPA:**

o The system calculates GPA for a single semester based on a
weighted average of grades and their corresponding credit
hours.

o Formula used:
GPA=∑(Grade Points * Credit hrs)/total Credit hrs

**3. Computation of CGPA:**

o For multiple semesters, the system computes CGPA by
aggregating all GPA scores over the semesters and their
respective credit hours.

o Formula used:
CGPA=∑(GPA’s Earned in all semester)/Total
no.of.Semester

**4. Error Handling:**

o The system detects invalid inputs (e.g., grades outside the
grading scale or missing credit hours) and prompts the user to
correct them.
o This feature enhances the reliability of the calculations.

**5. Output Display:**

o After computation, the system displays the GPA and CGPA in
a clear, easy-to-read format.
o It also provides additional insights, such as semester-wise
performance trends, if included as an enhancement.

**6. Data Management:**

o The system is designed to temporarily store user inputs and
computation results for the session.
o It does not retain sensitive information, ensuring data privacy
and security.

**7. Customizability:**

o The application can be configured to support various grading
systems, making it adaptable to different academic
frameworks.
