# COMP7506 Smart Phone Apps Development Group Project
## Project name: HKU Directory
## MySQL Database Related
### Credentials

URL: nuc.hkumars.potatoma.com:3306   [*HKU Wi-Fi or VPN needed]

Web Management: http://nuc.hkumars.potatoma.com/phpmyadmin

Database name: comp7506

Username: root or potatoma

Password: potatoma123
### Android Implementation
Class ```connectSQL``` is implemented with necessary credentials included. It connects to MySQL database and fetch requested records from it in a new thread, so that no front-end operations are blocked.

#### Usage:

in ```onCreate```:
```
connectSQL sql = new connectSQL(); //instantiate sql object
sql.execute("SQL query here") //for instance, sql.execute("SELECT * FROM people")；
```

in ```onPostExecute```, records returned are stored in ```List<Map<String, String>> result``` param, which is an arraylist containing multiple maps. You might want to implement what to do with records returned in this callback function.

Use ```result.get(index)``` to find certain row, and use ```result.get(index).get(column_name) ```to further navigate to certain attribute.

![](./images/Untitled%202.jpg)
![](./images/Untitled%203.jpg)
### Refactored MySQL Database connection
#### Usage(JDBCUtils.java):
```
public static Connection getConn() {
        Connection connection = null;
        try {
            Class.forName(driver); // load class dynamically
            String url = "...."; // try to connect database with URL
            connection = DriverManager.getConnection(url, user, password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
```
## Frontend Related
### Login/Register UI
### Main Page
### Contact Info Page
### Map Direction Page
