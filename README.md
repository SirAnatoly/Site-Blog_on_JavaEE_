# Blog on JavaEE
<a href="http://anatoliykovalov.ddns.net/Site_Blog_on_JavaEE_war">DEMO:</a>
 http://anatoliykovalov.ddns.net/Site_Blog_on_JavaEE_war
 
 <a href="http://anatoliykovalov.ddns.net/Site_Blog_on_JavaEE_war">Video overview</a>
 
[![Watch the video](http://i.piccy.info/i9/3f1dc13958ada97a68392db8b017a1fb/1598604922/319685/1393884/5.png)](https://www.youtube.com/watch?v=NhMxseaeOdc)


<img src="http://i.piccy.info/i9/995efab22cc5cad8eb9c004dd016b42b/1598605444/155345/1393884/1.png" alt="mvc">


<img src="http://i.piccy.info/i9/5fe548995dca01676b9f63b23fcfe070/1598605577/64671/1393884/209182.png" alt="db er">


<h3>Technologies </h3>

Interface : HTML/CSS/JS/Bootstrap/jQuery.

JavaEE: Servlets, JSP, JSTL.

Apache Commons : dbcp2, lang3, dbutils, BeanUtils, email, validator.

Database: MySql.

Google OAuth.

Maven, Logback.

The application is built on MVC design pattern.



<h3>What does it do:</h3>
  <ul>
    <li>Displaying a list of article categories;</li>
    <li>Displays a list of all articles sorted in descending order of creation time;</li>
    <li>Displays a list of articles for the selected category, sorted in descending order of creation time;</li>
    <li>Search for articles by keyword in the article title and its text. (For simplicity, we mean searching by the exact word or phrase);</li>
    <li>Paginated display of information for the list of articles and search results;</li>
    <li>Displaying information about an article and updating the page-view counter;</li>
    <li>Integration with social services for sharing information about an article;</li>
    <li>Load more comments to an article;</li>
    <li>Writing a new comment to an article;</li>
    <li>Authentication via social networks, as the simplest and most widely used method today, using the example of Google+;</li>
    <li>Import a user profile (name, email, avatar) from Google+ when creating a comment;</li>
    <li>Sending a notification about a new comment to the blog owner's email address;</li>
    <li>Sending a Contact Form request to the blog owner's email address;</li>
    <li>Error handling: 404, runtime errors, data validation;</li>
    <li>Displaying simple pages using the about example.</li>
  </ul>

<h2>Base Blog Props</h2>
 <h3>1) File: Site-Blog_on_JavaEE_/src/main/resources/application.properties</h3>
 
<p>db.driver=com.mysql.cj.jdbc.Driver </p>
<p>db.url=jdbc:mysql://localhost:3306/Blog // Format</p>
<p>db.username=**** // Format</p>
<p>db.password=***** // Format</p>
<p>db.pool.initSize=5</p>
<p>db.pool.maxSize=10</p>
<p>social.googleplus.clientId=***** // Format</p>
<p>app.host=http://******* // Format</p>
<p>email.notificationEmail=xxx@mail.com // Format</p>
<p>email.sendTryCount=2</p>
<p>email.smtp.server=smtp.mail.com</p>
<p>email.smtp.port=465</p>
<p>email.fromEmail=xxx@mail.com // Format</p>
<p>email.smtp.username=xxx@mail.com // Format</p>
<p>email.smtp.password=xxx{password application email} // Format </p>


<h3>2) File: Site-Blog_on_JavaEE_/src/main/java/blog/Constants.java</h3>

public class Constants {

    public static final String CATEGORY_MAP = "CATEGORY_MAP";

    public static final int LIMIT_ARTICLES_PER_PAGE = *****;  // Format 

    public static final int LIMIT_COMMENTS_PER_PAGE = *****; // Format

}
