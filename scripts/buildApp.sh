cd ..
javac -cp 'lib/gson-2.8.2.jar' -d bin src/*/**.java
jar -cvfm App.jar scripts/MANIFEST.MF bin/* lib/gson-2.8.2.jar
cd ..
