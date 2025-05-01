# Capitals-Quiz API

A simple RESTful API to generate a multiple choice quiz about countries, in Asia, and its capital cities.

---

## API Usage

### Add a Country
**POST** `/country`

**Request Body:**
```json
{
  "continent": "Asia"
  "name": "Philippines"
  "capital": "Manila"
}
```

You need to send a JSON object with the **continent**, **name**, **capital** of a country.  



### Get countries
**GET** `/country`							- return the list of all countries.

**GET** `/country/{id}`						- returns the country with {id}.

**GET** `/country/continent/{continent}`	- returns the list of all countries registered as {continent}.

**GET** `/country/quiz/quiz={q}&choice={c}`	- returns q number of questions with c number of choices each.


### Instructions before using
1. **Create a Database in MySQL:**

	Open your MySQL client or use the command line to create a database. For example:
	```sql
	CREATE DATABASE country;
	```
	
2. **Configure Database Connection:**

	- Navigate to src/main/resources in your project.

	- Open the application.properties file.

		- Update the following properties with your MySQL database information:
	
		- spring.datasource.url=jdbc:mysql://localhost:3306/{your_database_name}

			- Replace {your_database_name} with the name of the database you created in Step 1 (e.g., country).

		- spring.datasource.username={your_mysql_username}

			- Replace {your_mysql_username} with your MySQL connection username (e.g., root).

		- spring.datasource.password={your_mysql_password}

			- Replace {your_mysql_password} with your MySQL connection password.

3. Run the Application:

	- After configuring the application.properties file, you can run your Spring Boot application.

	- The application will automatically create the tables in the database.



### Notes:

- Ensure that your MySQL server is running before attempting to connect.

- You can change the database name, username, and password as needed, but make sure to update the application.properties accordingly.


---

### Coming soon
- Generate questions outside Asia.
- Generate continent-specific questions.
- Different types of quizzes (e.g., Identification, True or False).