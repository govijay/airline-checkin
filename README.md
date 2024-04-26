<h1> Airline Check-In system - Using of DB transaction locks (mysql) </h1>

<table>
    <tr>
        <td>
        <h2> Description </h2>
        <p> This project is a simple implementation of an airline check-in system. It uses a MySQL database to store the information of the passengers and the seats. The system is designed to be used by the airline staff to check-in passengers. The system uses database transaction locks to ensure that only one staff member can check-in a passenger at a time. This prevents the system from double booking a seat. </p>
        </td>
    </tr>
</table>

<h2> Technologies Used </h2>
<ul>
    <li> Java 17 </li>
    <li> MySQL </li>
</ul>

<h2> How to Run </h2>
<ol>
    <li> Clone the repository </li>
    <li> Create a MySQL database and run the SQL script in the `database` folder to create the necessary tables </li>
    <li> Update the `src/main/resources/application.properties` file with your database connection details </li>
    <li> mvn clean install and Run the `Main` class in the `src/main/java/com/airline/checkin` package 
using java -jar ./target/checkin-1.0-SNAPSHOT-jar-with-dependencies.jar > log.txt
 </li>
</ol>

<h2> Issues: </h2>
<ul>
    <li> <p>
The system does not handle the case where same seat is assigned to multiple passengers. 
Refer to log.txt for the last run details. Unassigned seat query "FOR UPDATE SKIP LOCKED" is not working as expected.
</p>  </li>
</ul>



