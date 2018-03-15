#!/bin/bash
 
mvn clean 
find . -name '.DS_Store' -type f -delete

fromName=Starter
lowercaseFromName=$(echo "$fromName" | tr '[:upper:]' '[:lower:]')
uppercaseFromName=$(echo "$fromName" | tr '[:lower:]' '[:upper:]')
capitolizedFromName=$(tr '[:lower:]' '[:upper:]' <<< ${lowercaseFromName:0:1})${lowercaseFromName:1}
echo "Renaming files with the strings $lowercaseFromName and and $uppercaseFromName and $capitolizedFromName"

toName=UsermindSdk
lowercaseToName=$(echo "$toName" | tr '[:upper:]' '[:lower:]')
uppercaseToName=$(echo "$toName" | tr '[:lower:]' '[:upper:]')
capitolizedToName=$(tr '[:lower:]' '[:upper:]' <<< ${lowercaseToName:0:1})${lowercaseToName:1}
echo "Renaming files to $lowercaseToName and and $uppercaseToName and $capitolizedToName"
  
find . -name "*$capitolizedFromName*" | while read f; do mv "$f" "${f//$capitolizedFromName/$capitolizedToName}"; done
find . -name "*$lowercaseFromName*" | while read f; do mv "$f" "${f//$lowercaseFromName/$lowercaseToName}"; done
find . -name "*$uppercaseFromName*" | while read f; do mv "$f" "${f//$uppercaseFromName/$uppercaseToName}"; done
 
grep -rl "$capitolizedFromName" * | grep -v .sh | xargs -I@ sed -i "" "s/$capitolizedFromName/$capitolizedToName/g" @
grep -rl "$lowercaseFromName" * | grep -v .sh | xargs -I@ sed -i "" "s/$lowercaseFromName/$lowercaseToName/g" @
grep -rl "*$uppercaseFromName" * | grep -v .sh | xargs -I@ sed -i "" "s/$uppercaseFromName/$uppercaseToName/g" @