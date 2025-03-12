System requires Maven to be installed, and would work best using vscode

if you have git installed,


git clone -b java21-version --single-branch https://github.com/Gotoline-one/javaConway_.001.git  <br>
cd javaConway_.001  <br>
code . <br>

this should open vscode up and it should read the pom.xml and other
config files to set up your enviroment. 



to build and run: 
Reference: <https://openjfx.io/openjfx-docs/#maven>

(Windows)

Ensure these Enviroment variable are setup:
     PATH_TO_FX="path\to\javafx-sdk-23.0.2\lib"  
     PATH_TO_FX_MODS="path\to\javafx-jmods-23.0.2"


to run using Maven: 
    mvn clean javafx:run

to create a stand alone with maven
    mvn clean javafx:jlink

run standalone made with javafx:jlink : 
    .\target\conway\bin\launcher.bat
