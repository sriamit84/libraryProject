# libraryProject
This project is to create/update/delete library and books in that library- built using Springboot and React

Steps to run the application:

Go to \libraryProj_UI and run npm install followed by npm start
UI application will start running on http://localhost:3000

Go to \libraryProj_Server and run mvn spring-boot:run , it will download all the dependency jars and run the application.

APIs to create/update/delete/get library and book are given below.


Books:

GET/POST

http://localhost:8080/libraries/{libraryId}/books

PUT/DELETE
http://localhost:8080/libraries/{libraryId}/books/{bookId}

Post and Put request :

{
	"authorName":"Amit Srivastava",
	"content":"The Vinci Code",
	"bookName":"The Vinci Code"
}


Libraries:

GET/POST

http://localhost:8080/libraries

PUT/DELETE
http://localhost:8080/libraries/{libraryId}

Post and Put request :

{
"libraryName":"My Library",
"description":"My Library"
}



