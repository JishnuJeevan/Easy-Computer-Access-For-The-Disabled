# Easy-Computer-Access-For-The-Disabled
B.Tech mini project create by me and my class mates Anu Joseph, Wreni Stanley and Farah Shahabdeen. <br>
Done at Albertian Institute of Science and Technology. <br>
Under the guidance of Mr. Jeswin Roy D'Couth Assistant Professor, Computer Science Department, AISAT. <br>
During the academic year 2017 - 2018 <br>
Semester 7 and 8. <br>
## Abstract
Easy Computer Access for the Disabled (ECAD) is software that can be used to help paralyzed people for easily manipulating their computer system. People who are paralyzed will be having difficulty to write text using the keyboard as they will have difficulty going over to the entire keys in the keyboard. Our software can help them to use their computer system by providing keyboard and mouse simulation that is designed for people with paralysis. The mouse need not be dragged to the required key or to the required function. They can use the left and right mouse button to type the text. The right mouse button is used to scan through the list of keys and functions while the left mouse button is used to select the scanned key or function. A database is used to store a set of words that the user can choose from it. Here suggestions are provided for the most frequently used words, so that they don’t have to write the entire word. They can just select the required word from the database. New words written by the user are added into the database. The user interface is a 6X7 matrix which contains the following buttons list, letter from a–z, number, space, backspace, open/close talk window, speak, mouse actions, shortcuts, navigations, other buttons, symbol set 1, symbol set 2, symbol set 3, open application, switch tabs, open/close settings.
## Prerequisites
Once you have cloned the repository, make sure you do the following to run the project. <br>
1. Make sure you have Netbeans or Eclipse IDE for Java Installed. <br>
2. For DatabaseSQLite.java to work, do the follwing: <br>
2.1. In the "resource" folder there will be "libraries" folder. <br>
2.2. In "libraries" folder there will be a "sqlite-jdbc-3.16.1.jar" file. <br>
2.3. Configure JRE System Library and add the "sqlite-jdbc-3.16.1.jar" file to the JRE System library. <br>
3. For AudioPlayer.java and TextToSpeech.java to work do the follwing: <br>
3.1. In the "resource" folder there will be "libraries" folder. <br>
3.2. In "libraries" folder there will be a many jar file. <br>
3.3. Configure JRE System Library and add those jar file to the JRE System library. <br>
4. For DaemonThread.java (for eye blink detection) to work do the follwing: <br>
4.1. In the "resource" folder there will be "opencvlib" folder. <br>
4.2. In "libraries" folder there will be a many jar file. <br>
4.3. Configure JRE System Library and add those jar file to the JRE System library. <br>
4.4. NOTE: This feature is not in perfect working condition. It has some bug. We implemented it as a concept to show something like it can be done. In the "resource" folder, there is a "opencvdll" folder having "opencv_java341.dll" file. You may want to add it to system path or configure it in some way so that eye blink detection work properly.This is where you might get some error, if the file is not loaded properly. 
## Authors and Instituions
1. Jishnu Jeevan - Albertian Institute of Science and Technology, Kalamassery.
2. Wreni Stanley - Albertian Institute of Science and Technology, Kalamassery.
3. Farah Shahabdeen - Albertian Institute of Science and Technology, Kalamassery.
4. Anu Joseph - Albertian Institute of Science and Technology, Kalamassery.
## Reference
More reference can be found in the project report that is presented with this repository. But here I will be providing the reference for the code used. We were not able to cite these when writting the project report, as where in a hurry to finish the project. It was a mistake on our part. So I have added the reference here:
1. Mary TTS Library - http://mary.dfki.de/
2. Code for AudioPlayer.java and TextToSpeech.java - https://github.com/goxr3plus/Java-Text-To-Speech-Tutorial
3. Open CV Cascade Classifier - https://docs.opencv.org/3.4/db/d28/tutorial_cascade_classifier.html
4. Code for Daemon Thread (Real time face detection) - https://github.com/tahaemara/real-time-face-detection-using-opencv-with-java/blob/master/src/gui/FaceDetection.java
## Further Acknowledgment
This work is inspired by the work done by the engineers at Intel. The have created a software called Assistive Context-Aware Toolkit (ACAT), which has been used by the late physicists Stephen Hawking for communication.
If you want to know more about the work done by Intel, you can check it here - https://github.com/intel/acat
## NOTE
1. This is an extension of the mini project that we did during B.Tech 6th semester - https://github.com/JishnuJeevan/Speech-Assistant. <br>
2. Also in the report you will see lack of proper citations. This was also done due to the inexperience in our part, when writing a report. So all the reference and code that were not cited in the report can be found here in the README.md file. <br>
3. We are providing the original report that was submited after the completion of the project. <br>
4. Make sure you cite this repository if you are going to use it. <br>
5. If you want to make any changes, it will be better if you leave this repository as it is and don't make any changes. <br>
6. If you do want to make changes, make a copy of this repository and make changes to that copy and upload that copy. Make sure that you cite this repository if you use it. <br>
