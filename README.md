# Bulletin Board System - Client Side

CS3201 Programming Assignment <br />
Student Name: Cho Tsz Yan & Ng Lai Ying <br />
Student ID: 56345814 & 56273090 <br /><br />

//You must make it clear how to compile and run your code by explaining it in your readme text file. The development environment should also be indicated in this file. //
 
## Description
This project aims to build the client side of the Bulletin Board System, which performs the text exchange between the client and server.  <br />
In this project, the server program is provided and its default port number is 16000 running on IP address 192.168.0.1. There are three commands which are POST, READ and QUIT, executed by the client for the communication to the server. <br />

## Development Enviornment 
This program is developed in JAVA languagae under MS Windows with two computers. The IDE of the program is the Visual Studio Code. Besides, Github connected to the IDE is used for the share of code. <br />

## Building Process
In this program, the library 'java.net' is mainly used for the function of the client. <br /><br />

To establish the connection between the client and server, the java class Socket is implemented. After setting the default port number 16000, the Socket is built through accepting the port and inputted ip address. Thus, there is the isconnected() function of the Socket class to return the connection status from the server if the connection is sucessful. If the value is true, the "Connection status: success" is printed, else "Connection status: failed" is printed.<br /><br />





