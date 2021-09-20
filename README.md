# lab 26

**Homepage**

 it  has a heading at the top of the page, an image to mock the “my tasks” view, and buttons at the bottom of the page to allow going to the “add tasks” and “all tasks” page.

 ## This is the home page
![image1](screenshots/homepage.png)
<br>

**Add a Task**

On the “Add a Task” page, allow users to type in details about a new task, specifically a title and a body. When users click the “submit” button, show a “submitted!” label on the page.

## This is the all Tasks page

![image2](screenshots/alltask.png)
<br>


**All Tasks**

The all tasks page has just an image with a back button.



## This is the Add task page

![image3](screenshots/addtask.png)

<hr>

## lab 27

# Modefied Home page 
It has a three extra buttons and thay navigate the user to detail page , and setting button and it naviate the user to settings page .
<br>
![home page image](screenshots/homepagelab27.png)


## The Detail Page
 It has a title at the top of the page, and a Lorem Ipsum description.
 <br>
![image](screenshots/homeworktaskLab27.png)
![image](screenshots/gameworktasklab27.png)
![image](screenshots/coffeworktasklab27.png)

## Settings Page
 It allows users to enter their username and hit save.
 <br>
![image](screenshots/settingslab27.png)

<hr>

# lab 28

# Modefied Home page 
It has a Recycler view that dislay the data on it  .
<br>
![home page image](screenshots/home28.png)

<hr>

# lab 29

<hr>

The data is got from the form and saving it on the database and then get it from data base and render it on the home page instead of having them as a hard code .

![home page image](screenshots/home29.png)
![add task image](screenshots/addtask29.png)

<hr>

# lab 31

<hr>

It Is added  UI tests for taskmaster application, and polish for remaining feature tasks from previous labs.

its include tests for views displayed on the pages ,  tests for textView content , and some tests for moving between pages .


<hr>

# lab 32

<hr>

**Add Task Form** <br>

add task functionality Modified to save the data entered in as a Task to graphQL. <br>
(No changes in UI)<br>

**Homepage** <br>

homepage’s RecyclerView refactored to display all Task entities in graphQL.<br>
(No changes in UI)<br>

here is my queries in the cloud<br>

![GraphQl](screenshots/grapgql.png)


<hr>

# lab 33

<hr>

Feature Tasks<br>
Tasks Are Owned By Teams<br>
Create a second entity for a team, which has a name and a list of tasks. Update your tasks to be owned by a <br>team.<br>

Manually created three teams by running a mutation exactly three times in your code. (You do NOT need to allow <br>the user to create new teams.)<br>

Add Task Form<br>
Modify your Add Task form to include either a Spinner or Radio Buttons for which team that task belongs to.<br>

Settings Page<br>
In addition to a username, allow the user to choose their team on the Settings page. Use that Team to display <br>only that team’s tasks on the homepage.<br>


# Lab: 42 - Location

Location
When the user adds a task, their location should be retrieved and included as part of the saved Task.

Displaying Location
On the Task Detail activity, the location of a Task should be displayed if it exists.