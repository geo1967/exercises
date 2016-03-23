# exercises

This repository contains experimental java projects created to explore various new features of the language and o/s frameworks.


## prime
Project to  prototype a  spring web service that will provide a range of generated prime numbers up to a maximum threshold using a choice of methodologies. A secondary goal was to explore the latest functional programming features such as streams and how they can be used with parallel processing with concurrent worker threads. The SpoofSegmentedSieve class is  an example of this, although it doesnt generate primes itself

Installation
-  Ensure java 1.8 and maven  3.3.9  are installed on your local machine

 `git clone https://github.com/geo1967/exercises`
 
 `cd xercises/eclipse-ws/prime`
 
 `mvn test`
 
 


Start the web service 
`mvn exec:exec`

Go to you web browser and enter the following urls

http://localhost:8080/prime/TrialDivision/100

http://localhost:8080/prime/SieveOfSundaram/100

http://localhost:8080/prime/CannedDataPrimes/100

 http://localhost:8080/prime/SpoofSegmentedSieve/100 

i.e web service mapping : "<host>:><port>/<method>/<algo-param>/<max-prime-limit> "






    
