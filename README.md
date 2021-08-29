# AddressBookMaster
Address Book Application using SpringBoot
REST APIs for simple Address Book Application using Spring Boot 2.5.4, Java 11, Jpa, H2 database, Documentation using Open Api. Following seven REST APIs are created.

![image](https://user-images.githubusercontent.com/34703659/131245722-45e7ebfc-55a9-4349-a0b1-b8563284903b.png)


**1. Create AddressBook API**
Method : Post
URL : http://localhost:8080/book
Body : Json
{
  "name":"BentleighBook"
}

**2. Get All AddressBook** 
Method : GET
URL : http://localhost:8080/book

**3. Create Contact API**
Method : Post
URL : http://localhost:8080/book/contact
Body : Json
{
  "firstName":"a",
  "lastName":"p",
  "phoneNumber":"0455555555",
  "book":{"id":1}
}

**4. Get Contact by Id**
Method : GET
URL : http://localhost:8080/book/contact/{id}

**5. Get Contact by Address Book Id**
Method : GET
URL : http://localhost:8080/book/contact?bookId=1

**6. Get All Contact**
Method : GET
URL : http://localhost:8080/book/contact

**7. Get All Unique Contact**
Method : GET
URL : http://localhost:8080/book/contact/unique

**8. Delete Contact by Id**
Method : DELETE
URL : http://localhost:8080/book/contact/{id} 

**9. Open Api Document** 
URL : http://localhost:8080/v3/api-docs
