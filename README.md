# Integrations sample
Usermindsdk project. Find all uses of the word "Usermindsdk" and replace it with your project name.


Turn off an inspection
IntelliJ -> Preferences
Editor->Inspections
Spring->Spring Core->Code
Uncheck Autowiring for Bean Class


To create a new project, copy it to a new directory.
Clean up if needed
sudo rm -r .idea
rm usermindsdk.iml
mvn clean

Once you have copied it, you'll want to rename it (and everything inside it) to your new project name. To do that, run the two commands below, 
Replace Usermindsdk with your project name - not an official Usermind name, but something short. So for example, use $capitolizedName, and not "integrations-worker-$lowercaseName" and run:

```
LC_ALL=C find . -type f  -exec sed -i '' s/Usermindsdk/$capitolizedName/ {} +
LC_ALL=C find . -type f  -exec sed -i '' s/usermindsdk/$capitolizedName/ {} +
```

Now open the project in IntelliJ.
